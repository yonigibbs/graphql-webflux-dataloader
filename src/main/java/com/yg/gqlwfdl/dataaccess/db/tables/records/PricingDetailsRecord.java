/*
 * This file is generated by jOOQ.
 */
package com.yg.gqlwfdl.dataaccess.db.tables.records;


import com.yg.gqlwfdl.dataaccess.db.tables.PricingDetails;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class PricingDetailsRecord extends UpdatableRecordImpl<PricingDetailsRecord> implements Record5<Long, String, Long, Long, Long> {

    private static final long serialVersionUID = 716529280;

    /**
     * Setter for <code>public.pricing_details.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.pricing_details.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.pricing_details.description</code>.
     */
    public void setDescription(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.pricing_details.description</code>.
     */
    public String getDescription() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.pricing_details.vat_rate</code>.
     */
    public void setVatRate(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.pricing_details.vat_rate</code>.
     */
    public Long getVatRate() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.pricing_details.discount_rate</code>.
     */
    public void setDiscountRate(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.pricing_details.discount_rate</code>.
     */
    public Long getDiscountRate() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.pricing_details.preferred_payment_method</code>.
     */
    public void setPreferredPaymentMethod(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.pricing_details.preferred_payment_method</code>.
     */
    public Long getPreferredPaymentMethod() {
        return (Long) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, Long, Long, Long> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, Long, Long, Long> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return PricingDetails.PRICING_DETAILS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return PricingDetails.PRICING_DETAILS.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return PricingDetails.PRICING_DETAILS.VAT_RATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return PricingDetails.PRICING_DETAILS.DISCOUNT_RATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field5() {
        return PricingDetails.PRICING_DETAILS.PREFERRED_PAYMENT_METHOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component3() {
        return getVatRate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component4() {
        return getDiscountRate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component5() {
        return getPreferredPaymentMethod();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getVatRate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getDiscountRate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value5() {
        return getPreferredPaymentMethod();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetailsRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetailsRecord value2(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetailsRecord value3(Long value) {
        setVatRate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetailsRecord value4(Long value) {
        setDiscountRate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetailsRecord value5(Long value) {
        setPreferredPaymentMethod(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PricingDetailsRecord values(Long value1, String value2, Long value3, Long value4, Long value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PricingDetailsRecord
     */
    public PricingDetailsRecord() {
        super(PricingDetails.PRICING_DETAILS);
    }

    /**
     * Create a detached, initialised PricingDetailsRecord
     */
    public PricingDetailsRecord(Long id, String description, Long vatRate, Long discountRate, Long preferredPaymentMethod) {
        super(PricingDetails.PRICING_DETAILS);

        set(0, id);
        set(1, description);
        set(2, vatRate);
        set(3, discountRate);
        set(4, preferredPaymentMethod);
    }
}
