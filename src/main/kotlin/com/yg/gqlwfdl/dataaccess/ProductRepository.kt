package com.yg.gqlwfdl.dataaccess

import com.yg.gqlwfdl.dataaccess.db.Tables.ORDER_LINE
import com.yg.gqlwfdl.dataaccess.db.Tables.PRODUCT
import com.yg.gqlwfdl.dataaccess.db.tables.records.ProductRecord
import com.yg.gqlwfdl.dataaccess.joins.GraphQLFieldToJoinMapper
import com.yg.gqlwfdl.dataaccess.joins.JoinRequest
import com.yg.gqlwfdl.dataaccess.joins.JoinedRecordToEntityConverterProvider
import com.yg.gqlwfdl.dataaccess.joins.RecordProvider
import com.yg.gqlwfdl.requestContext
import com.yg.gqlwfdl.services.Product
import com.yg.gqlwfdl.services.ProductOrderCount
import graphql.language.Field
import graphql.schema.DataFetchingEnvironment
import io.reactiverse.pgclient.PgPool
import io.reactiverse.pgclient.Row
import org.jooq.*
import org.jooq.impl.DSL.count
import org.springframework.stereotype.Repository
import java.util.concurrent.CompletableFuture

/**
 * Repository providing access to product information.
 */
interface ProductRepository : EntityRepository<Product, Long> {
    /**
     * Returns a [CompletableFuture] which, when completed, will provide a [List] of all [Product] objects with the
     * passed in IDs, along with their order counts, wrapped in an [ProductOrderCount] object.
     *
     * @param env The environment for the current GraphQL data fetch, if this method is called from such a context.
     */
    fun findWithOrderCount(ids: List<Long>, env: DataFetchingEnvironment? = null): CompletableFuture<List<ProductOrderCount>>

    /**
     * Gets the top [count] best selling products.
     *
     * @param env The GraphQL data fetching environment from which this call was made, if it was made from that context.
     * Can be null if not working within a GraphQL context.
     */
    fun findTopSelling(count: Int, env: DataFetchingEnvironment? = null): CompletableFuture<List<ProductOrderCount>>
}

/**
 * Concrete implementation of [ProductRepository], which uses a database for its data.
 */
@Repository
class DBProductRepository(create: DSLContext,
                          connectionPool: PgPool,
                          recordToEntityConverterProvider: JoinedRecordToEntityConverterProvider,
                          graphQLFieldToJoinMapper: GraphQLFieldToJoinMapper,
                          recordProvider: RecordProvider)
    : DBEntityRepository<Product, Long, ProductRecord, QueryInfo<ProductRecord>>(
        create, connectionPool, recordToEntityConverterProvider, graphQLFieldToJoinMapper, recordProvider,
        PRODUCT, PRODUCT.ID),
        ProductRepository {

    override fun findWithOrderCount(ids: List<Long>, env: DataFetchingEnvironment?)
            : CompletableFuture<List<ProductOrderCount>> {

        return findWithOrderCount(env?.requestContext?.dataLoaderPrimerEntityCreationListener, env?.getJoinRequests()) {
            listOf(it.primaryTable.field(PRODUCT.ID).`in`(ids))
        }
    }

    override fun getRecord(queryInfo: QueryInfo<ProductRecord>, row: Row) = row.toProductRecord(queryInfo)

    override fun getEntity(queryInfo: QueryInfo<ProductRecord>, row: Row) = getRecord(queryInfo, row).toEntity()

    override fun find(entityCreationListener: EntityCreationListener?,
                      joinRequests: List<JoinRequest<out Any, ProductRecord, out Record>>?,
                      graphQLFields: List<Field>?,
                      conditionProvider: ((QueryInfo<ProductRecord>) -> List<Condition>)?)
            : CompletableFuture<List<Product>> {

        // Request included the "orderCount" GraphQL field: this means that we need to join to a nested SQL query
        // which will count the number of orders for each product. Call the findWithOrderCount, which will return
        // the products with their order counts, and will cause the order counts (ProductOrderCount)
        // to be cached with the data loader so that the ProductResolver has access to the values later. And before
        // returning the values, map them back to the Product entities themselves.
        return if (graphQLFields?.map { it.name }?.contains("orderCount") == true)
            findWithOrderCount(entityCreationListener, joinRequests, null, null, conditionProvider)
                    .thenApply { results -> results.map { it.entity } }
        else
        // Can just use base class's behaviour.
            super.find(entityCreationListener, joinRequests, graphQLFields, conditionProvider)
    }

    override fun findTopSelling(count: Int, env: DataFetchingEnvironment?) =
            findWithOrderCount(env?.requestContext?.dataLoaderPrimerEntityCreationListener, env?.getJoinRequests(),
                    SortOrder.DESC, count)

    /**
     * Runs a query for products, including the count of orders for each one. Uses [PRODUCT] as the main table, and
     * joins to a nested table which is a SELECT which joins [PRODUCT] to [ORDER_LINE], returning only the product ID
     * and counting the orders.
     *
     * Returns a [CompletableFuture] which will complete when the query returns results, and exposes a [List] of
     * [ProductOrderCount] objects.
     */
    private fun findWithOrderCount(entityCreationListener: EntityCreationListener?,
                                   joinRequests: List<JoinRequest<out Any, ProductRecord, out Record>>?,
                                   orderCountSortOrder: SortOrder? = null,
                                   limit: Int? = null,
                                   conditionProvider: ((QueryInfo<ProductRecord>) -> List<Condition>)? = null)
            : CompletableFuture<List<ProductOrderCount>> {

        val queryInfo = getQueryInfo()
        val orderCountField = count().`as`("order_count")
        val productsWithOrderCount = queryInfo.addJoinedTable(
                create
                        .select(PRODUCT.ID, orderCountField)
                        .from(PRODUCT).innerJoin(ORDER_LINE).on(PRODUCT.ID.eq(ORDER_LINE.PRODUCT))
                        .groupBy(PRODUCT.ID)
                        .asTable("products_with_orders"),
                queryInfo.primaryTable, "orderCount", false)

        return find(
                entityProvider = { qi, row ->
                    ProductOrderCount(getEntity(qi, row), qi.getInt(row, productsWithOrderCount, orderCountField))
                },
                queryInfo = queryInfo,
                entityCreationListener = entityCreationListener,
                joinRequests = joinRequests,
                conditionProvider = conditionProvider,
                customJoiner = { qi, select ->
                    select.innerJoin(productsWithOrderCount)
                            .on(qi.primaryTable.field(PRODUCT.ID).eq(productsWithOrderCount.field(PRODUCT.ID)))
                },
                orderBy = if (orderCountSortOrder == null) null else listOf(
                        if (orderCountSortOrder == SortOrder.DESC) orderCountField.desc() else orderCountField.asc()),
                limit = limit
        )
    }
}

/**
 * Converts a [ProductRecord] to its corresponding entity, a [Product].
 */
fun ProductRecord.toEntity() = Product(this.id, this.description, this.price, this.company)

/**
 * Gets a [ProductRecord] from this [Row], reading the data from the passed in [queryInfo]'s
 * [primaryTable][QueryInfo.primaryTable].
 *
 * @param queryInfo The object containing the information about the query that produced this row, so that the correct
 * aliased names for tables/fields can be found.
 */
fun Row.toProductRecord(queryInfo: QueryInfo<ProductRecord>) =
        this.toProductRecord(queryInfo, queryInfo.primaryTable)

/**
 * Gets a [ProductRecord] from this [Row], reading the data from the passed in [productTable].
 *
 * @param queryInfo The object containing the information about the query that produced this row, so that the correct
 * aliased names for tables/fields can be found.
 * @param productTable: The instance of the table from which the record is to be extracted.
 */
fun Row.toProductRecord(queryInfo: QueryInfo<out Record>,
                        productTable: Table<ProductRecord>): ProductRecord {

    return ProductRecord(
            queryInfo.getLong(this, productTable, PRODUCT.ID),
            queryInfo.getString(this, productTable, PRODUCT.DESCRIPTION),
            queryInfo.getDouble(this, productTable, PRODUCT.PRICE),
            queryInfo.getLong(this, productTable, PRODUCT.COMPANY))
}