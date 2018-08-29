/*
 * This file is generated by jOOQ.
 */
package com.yg.gqlwfdl.dataaccess.db.tables;


import com.yg.gqlwfdl.dataaccess.db.Indexes;
import com.yg.gqlwfdl.dataaccess.db.Keys;
import com.yg.gqlwfdl.dataaccess.db.Public;
import com.yg.gqlwfdl.dataaccess.db.tables.records.DiscountRateRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DiscountRate extends TableImpl<DiscountRateRecord> {

    private static final long serialVersionUID = 901985338;

    /**
     * The reference instance of <code>public.discount_rate</code>
     */
    public static final DiscountRate DISCOUNT_RATE = new DiscountRate();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DiscountRateRecord> getRecordType() {
        return DiscountRateRecord.class;
    }

    /**
     * The column <code>public.discount_rate.id</code>.
     */
    public final TableField<DiscountRateRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('discount_rate_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.discount_rate.description</code>.
     */
    public final TableField<DiscountRateRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.discount_rate.value</code>.
     */
    public final TableField<DiscountRateRecord, Double> VALUE = createField("value", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * Create a <code>public.discount_rate</code> table reference
     */
    public DiscountRate() {
        this(DSL.name("discount_rate"), null);
    }

    /**
     * Create an aliased <code>public.discount_rate</code> table reference
     */
    public DiscountRate(String alias) {
        this(DSL.name(alias), DISCOUNT_RATE);
    }

    /**
     * Create an aliased <code>public.discount_rate</code> table reference
     */
    public DiscountRate(Name alias) {
        this(alias, DISCOUNT_RATE);
    }

    private DiscountRate(Name alias, Table<DiscountRateRecord> aliased) {
        this(alias, aliased, null);
    }

    private DiscountRate(Name alias, Table<DiscountRateRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> DiscountRate(Table<O> child, ForeignKey<O, DiscountRateRecord> key) {
        super(child, key, DISCOUNT_RATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DISCOUNT_RATE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DiscountRateRecord, Long> getIdentity() {
        return Keys.IDENTITY_DISCOUNT_RATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DiscountRateRecord> getPrimaryKey() {
        return Keys.DISCOUNT_RATE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DiscountRateRecord>> getKeys() {
        return Arrays.<UniqueKey<DiscountRateRecord>>asList(Keys.DISCOUNT_RATE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscountRate as(String alias) {
        return new DiscountRate(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiscountRate as(Name alias) {
        return new DiscountRate(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DiscountRate rename(String name) {
        return new DiscountRate(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DiscountRate rename(Name name) {
        return new DiscountRate(name, null);
    }
}