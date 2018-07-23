/*
 * This file is generated by jOOQ.
 */
package com.yg.gqlwfdl.yg.db.public_.tables;


import com.yg.gqlwfdl.yg.db.public_.Indexes;
import com.yg.gqlwfdl.yg.db.public_.Keys;
import com.yg.gqlwfdl.yg.db.public_.Public;
import com.yg.gqlwfdl.yg.db.public_.tables.records.PricingDetailsRecord;

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
public class PricingDetails extends TableImpl<PricingDetailsRecord> {

    private static final long serialVersionUID = -664695927;

    /**
     * The reference instance of <code>PUBLIC.PRICING_DETAILS</code>
     */
    public static final PricingDetails PRICING_DETAILS = new PricingDetails();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PricingDetailsRecord> getRecordType() {
        return PricingDetailsRecord.class;
    }

    /**
     * The column <code>PUBLIC.PRICING_DETAILS.ID</code>.
     */
    public final TableField<PricingDetailsRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.PRICING_DETAILS.DESCRIPTION</code>.
     */
    public final TableField<PricingDetailsRecord, String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PRICING_DETAILS.VAT_RATE</code>.
     */
    public final TableField<PricingDetailsRecord, Long> VAT_RATE = createField("VAT_RATE", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PRICING_DETAILS.DISCOUNT_RATE</code>.
     */
    public final TableField<PricingDetailsRecord, Long> DISCOUNT_RATE = createField("DISCOUNT_RATE", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PRICING_DETAILS.PREFERRED_PAYMENT_METHOD</code>.
     */
    public final TableField<PricingDetailsRecord, Long> PREFERRED_PAYMENT_METHOD = createField("PREFERRED_PAYMENT_METHOD", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.PRICING_DETAILS</code> table reference
     */
    public PricingDetails() {
        this(DSL.name("PRICING_DETAILS"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.PRICING_DETAILS</code> table reference
     */
    public PricingDetails(String alias) {
        this(DSL.name(alias), PRICING_DETAILS);
    }

    /**
     * Create an aliased <code>PUBLIC.PRICING_DETAILS</code> table reference
     */
    public PricingDetails(Name alias) {
        this(alias, PRICING_DETAILS);
    }

    private PricingDetails(Name alias, Table<PricingDetailsRecord> aliased) {
        this(alias, aliased, null);
    }

    private PricingDetails(Name alias, Table<PricingDetailsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> PricingDetails(Table<O> child, ForeignKey<O, PricingDetailsRecord> key) {
        super(child, key, PRICING_DETAILS);
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
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PricingDetailsRecord, Long> getIdentity() {
        return Keys.IDENTITY_PRICING_DETAILS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PricingDetailsRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_8;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PricingDetailsRecord>> getKeys() {
        return Arrays.<UniqueKey<PricingDetailsRecord>>asList(Keys.CONSTRAINT_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetails as(String alias) {
        return new PricingDetails(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetails as(Name alias) {
        return new PricingDetails(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PricingDetails rename(String name) {
        return new PricingDetails(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PricingDetails rename(Name name) {
        return new PricingDetails(name, null);
    }
}