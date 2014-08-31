/**
 * SalesOrderCreditmemoEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderCreditmemoEntity  implements java.io.Serializable {
    private java.lang.String updated_at;

    private java.lang.String created_at;

    private java.lang.String increment_id;

    private java.lang.String transaction_id;

    private java.lang.String global_currency_code;

    private java.lang.String base_currency_code;

    private java.lang.String order_currency_code;

    private java.lang.String store_currency_code;

    private java.lang.String cybersource_token;

    private java.lang.String invoice_id;

    private java.lang.String billing_address_id;

    private java.lang.String shipping_address_id;

    private java.lang.String state;

    private java.lang.String creditmemo_status;

    private java.lang.String email_sent;

    private java.lang.String order_id;

    private java.lang.String tax_amount;

    private java.lang.String shipping_tax_amount;

    private java.lang.String base_tax_amount;

    private java.lang.String base_adjustment_positive;

    private java.lang.String base_grand_total;

    private java.lang.String adjustment;

    private java.lang.String subtotal;

    private java.lang.String discount_amount;

    private java.lang.String base_subtotal;

    private java.lang.String base_adjustment;

    private java.lang.String base_to_global_rate;

    private java.lang.String store_to_base_rate;

    private java.lang.String base_shipping_amount;

    private java.lang.String adjustment_negative;

    private java.lang.String subtotal_incl_tax;

    private java.lang.String shipping_amount;

    private java.lang.String base_subtotal_incl_tax;

    private java.lang.String base_adjustment_negative;

    private java.lang.String grand_total;

    private java.lang.String base_discount_amount;

    private java.lang.String base_to_order_rate;

    private java.lang.String store_to_order_rate;

    private java.lang.String base_shipping_tax_amount;

    private java.lang.String adjustment_positive;

    private java.lang.String store_id;

    private java.lang.String hidden_tax_amount;

    private java.lang.String base_hidden_tax_amount;

    private java.lang.String shipping_hidden_tax_amount;

    private java.lang.String base_shipping_hidden_tax_amnt;

    private java.lang.String shipping_incl_tax;

    private java.lang.String base_shipping_incl_tax;

    private java.lang.String base_customer_balance_amount;

    private java.lang.String customer_balance_amount;

    private java.lang.String bs_customer_bal_total_refunded;

    private java.lang.String customer_bal_total_refunded;

    private java.lang.String base_gift_cards_amount;

    private java.lang.String gift_cards_amount;

    private java.lang.String gw_base_price;

    private java.lang.String gw_price;

    private java.lang.String gw_items_base_price;

    private java.lang.String gw_items_price;

    private java.lang.String gw_card_base_price;

    private java.lang.String gw_card_price;

    private java.lang.String gw_base_tax_amount;

    private java.lang.String gw_tax_amount;

    private java.lang.String gw_items_base_tax_amount;

    private java.lang.String gw_items_tax_amount;

    private java.lang.String gw_card_base_tax_amount;

    private java.lang.String gw_card_tax_amount;

    private java.lang.String base_reward_currency_amount;

    private java.lang.String reward_currency_amount;

    private java.lang.String reward_points_balance;

    private java.lang.String reward_points_balance_refund;

    private java.lang.String creditmemo_id;

    private SalesOrderCreditmemoItemEntity[] items;

    private SalesOrderCreditmemoCommentEntity[] comments;

    public SalesOrderCreditmemoEntity() {
    }

    public SalesOrderCreditmemoEntity(
           java.lang.String updated_at,
           java.lang.String created_at,
           java.lang.String increment_id,
           java.lang.String transaction_id,
           java.lang.String global_currency_code,
           java.lang.String base_currency_code,
           java.lang.String order_currency_code,
           java.lang.String store_currency_code,
           java.lang.String cybersource_token,
           java.lang.String invoice_id,
           java.lang.String billing_address_id,
           java.lang.String shipping_address_id,
           java.lang.String state,
           java.lang.String creditmemo_status,
           java.lang.String email_sent,
           java.lang.String order_id,
           java.lang.String tax_amount,
           java.lang.String shipping_tax_amount,
           java.lang.String base_tax_amount,
           java.lang.String base_adjustment_positive,
           java.lang.String base_grand_total,
           java.lang.String adjustment,
           java.lang.String subtotal,
           java.lang.String discount_amount,
           java.lang.String base_subtotal,
           java.lang.String base_adjustment,
           java.lang.String base_to_global_rate,
           java.lang.String store_to_base_rate,
           java.lang.String base_shipping_amount,
           java.lang.String adjustment_negative,
           java.lang.String subtotal_incl_tax,
           java.lang.String shipping_amount,
           java.lang.String base_subtotal_incl_tax,
           java.lang.String base_adjustment_negative,
           java.lang.String grand_total,
           java.lang.String base_discount_amount,
           java.lang.String base_to_order_rate,
           java.lang.String store_to_order_rate,
           java.lang.String base_shipping_tax_amount,
           java.lang.String adjustment_positive,
           java.lang.String store_id,
           java.lang.String hidden_tax_amount,
           java.lang.String base_hidden_tax_amount,
           java.lang.String shipping_hidden_tax_amount,
           java.lang.String base_shipping_hidden_tax_amnt,
           java.lang.String shipping_incl_tax,
           java.lang.String base_shipping_incl_tax,
           java.lang.String base_customer_balance_amount,
           java.lang.String customer_balance_amount,
           java.lang.String bs_customer_bal_total_refunded,
           java.lang.String customer_bal_total_refunded,
           java.lang.String base_gift_cards_amount,
           java.lang.String gift_cards_amount,
           java.lang.String gw_base_price,
           java.lang.String gw_price,
           java.lang.String gw_items_base_price,
           java.lang.String gw_items_price,
           java.lang.String gw_card_base_price,
           java.lang.String gw_card_price,
           java.lang.String gw_base_tax_amount,
           java.lang.String gw_tax_amount,
           java.lang.String gw_items_base_tax_amount,
           java.lang.String gw_items_tax_amount,
           java.lang.String gw_card_base_tax_amount,
           java.lang.String gw_card_tax_amount,
           java.lang.String base_reward_currency_amount,
           java.lang.String reward_currency_amount,
           java.lang.String reward_points_balance,
           java.lang.String reward_points_balance_refund,
           java.lang.String creditmemo_id,
           SalesOrderCreditmemoItemEntity[] items,
           SalesOrderCreditmemoCommentEntity[] comments) {
           this.updated_at = updated_at;
           this.created_at = created_at;
           this.increment_id = increment_id;
           this.transaction_id = transaction_id;
           this.global_currency_code = global_currency_code;
           this.base_currency_code = base_currency_code;
           this.order_currency_code = order_currency_code;
           this.store_currency_code = store_currency_code;
           this.cybersource_token = cybersource_token;
           this.invoice_id = invoice_id;
           this.billing_address_id = billing_address_id;
           this.shipping_address_id = shipping_address_id;
           this.state = state;
           this.creditmemo_status = creditmemo_status;
           this.email_sent = email_sent;
           this.order_id = order_id;
           this.tax_amount = tax_amount;
           this.shipping_tax_amount = shipping_tax_amount;
           this.base_tax_amount = base_tax_amount;
           this.base_adjustment_positive = base_adjustment_positive;
           this.base_grand_total = base_grand_total;
           this.adjustment = adjustment;
           this.subtotal = subtotal;
           this.discount_amount = discount_amount;
           this.base_subtotal = base_subtotal;
           this.base_adjustment = base_adjustment;
           this.base_to_global_rate = base_to_global_rate;
           this.store_to_base_rate = store_to_base_rate;
           this.base_shipping_amount = base_shipping_amount;
           this.adjustment_negative = adjustment_negative;
           this.subtotal_incl_tax = subtotal_incl_tax;
           this.shipping_amount = shipping_amount;
           this.base_subtotal_incl_tax = base_subtotal_incl_tax;
           this.base_adjustment_negative = base_adjustment_negative;
           this.grand_total = grand_total;
           this.base_discount_amount = base_discount_amount;
           this.base_to_order_rate = base_to_order_rate;
           this.store_to_order_rate = store_to_order_rate;
           this.base_shipping_tax_amount = base_shipping_tax_amount;
           this.adjustment_positive = adjustment_positive;
           this.store_id = store_id;
           this.hidden_tax_amount = hidden_tax_amount;
           this.base_hidden_tax_amount = base_hidden_tax_amount;
           this.shipping_hidden_tax_amount = shipping_hidden_tax_amount;
           this.base_shipping_hidden_tax_amnt = base_shipping_hidden_tax_amnt;
           this.shipping_incl_tax = shipping_incl_tax;
           this.base_shipping_incl_tax = base_shipping_incl_tax;
           this.base_customer_balance_amount = base_customer_balance_amount;
           this.customer_balance_amount = customer_balance_amount;
           this.bs_customer_bal_total_refunded = bs_customer_bal_total_refunded;
           this.customer_bal_total_refunded = customer_bal_total_refunded;
           this.base_gift_cards_amount = base_gift_cards_amount;
           this.gift_cards_amount = gift_cards_amount;
           this.gw_base_price = gw_base_price;
           this.gw_price = gw_price;
           this.gw_items_base_price = gw_items_base_price;
           this.gw_items_price = gw_items_price;
           this.gw_card_base_price = gw_card_base_price;
           this.gw_card_price = gw_card_price;
           this.gw_base_tax_amount = gw_base_tax_amount;
           this.gw_tax_amount = gw_tax_amount;
           this.gw_items_base_tax_amount = gw_items_base_tax_amount;
           this.gw_items_tax_amount = gw_items_tax_amount;
           this.gw_card_base_tax_amount = gw_card_base_tax_amount;
           this.gw_card_tax_amount = gw_card_tax_amount;
           this.base_reward_currency_amount = base_reward_currency_amount;
           this.reward_currency_amount = reward_currency_amount;
           this.reward_points_balance = reward_points_balance;
           this.reward_points_balance_refund = reward_points_balance_refund;
           this.creditmemo_id = creditmemo_id;
           this.items = items;
           this.comments = comments;
    }


    /**
     * Gets the updated_at value for this SalesOrderCreditmemoEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderCreditmemoEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the created_at value for this SalesOrderCreditmemoEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderCreditmemoEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the increment_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return increment_id
     */
    public java.lang.String getIncrement_id() {
        return increment_id;
    }


    /**
     * Sets the increment_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param increment_id
     */
    public void setIncrement_id(java.lang.String increment_id) {
        this.increment_id = increment_id;
    }


    /**
     * Gets the transaction_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return transaction_id
     */
    public java.lang.String getTransaction_id() {
        return transaction_id;
    }


    /**
     * Sets the transaction_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param transaction_id
     */
    public void setTransaction_id(java.lang.String transaction_id) {
        this.transaction_id = transaction_id;
    }


    /**
     * Gets the global_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @return global_currency_code
     */
    public java.lang.String getGlobal_currency_code() {
        return global_currency_code;
    }


    /**
     * Sets the global_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @param global_currency_code
     */
    public void setGlobal_currency_code(java.lang.String global_currency_code) {
        this.global_currency_code = global_currency_code;
    }


    /**
     * Gets the base_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_currency_code
     */
    public java.lang.String getBase_currency_code() {
        return base_currency_code;
    }


    /**
     * Sets the base_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_currency_code
     */
    public void setBase_currency_code(java.lang.String base_currency_code) {
        this.base_currency_code = base_currency_code;
    }


    /**
     * Gets the order_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @return order_currency_code
     */
    public java.lang.String getOrder_currency_code() {
        return order_currency_code;
    }


    /**
     * Sets the order_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @param order_currency_code
     */
    public void setOrder_currency_code(java.lang.String order_currency_code) {
        this.order_currency_code = order_currency_code;
    }


    /**
     * Gets the store_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @return store_currency_code
     */
    public java.lang.String getStore_currency_code() {
        return store_currency_code;
    }


    /**
     * Sets the store_currency_code value for this SalesOrderCreditmemoEntity.
     * 
     * @param store_currency_code
     */
    public void setStore_currency_code(java.lang.String store_currency_code) {
        this.store_currency_code = store_currency_code;
    }


    /**
     * Gets the cybersource_token value for this SalesOrderCreditmemoEntity.
     * 
     * @return cybersource_token
     */
    public java.lang.String getCybersource_token() {
        return cybersource_token;
    }


    /**
     * Sets the cybersource_token value for this SalesOrderCreditmemoEntity.
     * 
     * @param cybersource_token
     */
    public void setCybersource_token(java.lang.String cybersource_token) {
        this.cybersource_token = cybersource_token;
    }


    /**
     * Gets the invoice_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return invoice_id
     */
    public java.lang.String getInvoice_id() {
        return invoice_id;
    }


    /**
     * Sets the invoice_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param invoice_id
     */
    public void setInvoice_id(java.lang.String invoice_id) {
        this.invoice_id = invoice_id;
    }


    /**
     * Gets the billing_address_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return billing_address_id
     */
    public java.lang.String getBilling_address_id() {
        return billing_address_id;
    }


    /**
     * Sets the billing_address_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param billing_address_id
     */
    public void setBilling_address_id(java.lang.String billing_address_id) {
        this.billing_address_id = billing_address_id;
    }


    /**
     * Gets the shipping_address_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return shipping_address_id
     */
    public java.lang.String getShipping_address_id() {
        return shipping_address_id;
    }


    /**
     * Sets the shipping_address_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param shipping_address_id
     */
    public void setShipping_address_id(java.lang.String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }


    /**
     * Gets the state value for this SalesOrderCreditmemoEntity.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this SalesOrderCreditmemoEntity.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the creditmemo_status value for this SalesOrderCreditmemoEntity.
     * 
     * @return creditmemo_status
     */
    public java.lang.String getCreditmemo_status() {
        return creditmemo_status;
    }


    /**
     * Sets the creditmemo_status value for this SalesOrderCreditmemoEntity.
     * 
     * @param creditmemo_status
     */
    public void setCreditmemo_status(java.lang.String creditmemo_status) {
        this.creditmemo_status = creditmemo_status;
    }


    /**
     * Gets the email_sent value for this SalesOrderCreditmemoEntity.
     * 
     * @return email_sent
     */
    public java.lang.String getEmail_sent() {
        return email_sent;
    }


    /**
     * Sets the email_sent value for this SalesOrderCreditmemoEntity.
     * 
     * @param email_sent
     */
    public void setEmail_sent(java.lang.String email_sent) {
        this.email_sent = email_sent;
    }


    /**
     * Gets the order_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return order_id
     */
    public java.lang.String getOrder_id() {
        return order_id;
    }


    /**
     * Sets the order_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param order_id
     */
    public void setOrder_id(java.lang.String order_id) {
        this.order_id = order_id;
    }


    /**
     * Gets the tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return tax_amount
     */
    public java.lang.String getTax_amount() {
        return tax_amount;
    }


    /**
     * Sets the tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param tax_amount
     */
    public void setTax_amount(java.lang.String tax_amount) {
        this.tax_amount = tax_amount;
    }


    /**
     * Gets the shipping_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return shipping_tax_amount
     */
    public java.lang.String getShipping_tax_amount() {
        return shipping_tax_amount;
    }


    /**
     * Sets the shipping_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param shipping_tax_amount
     */
    public void setShipping_tax_amount(java.lang.String shipping_tax_amount) {
        this.shipping_tax_amount = shipping_tax_amount;
    }


    /**
     * Gets the base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_tax_amount
     */
    public java.lang.String getBase_tax_amount() {
        return base_tax_amount;
    }


    /**
     * Sets the base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_tax_amount
     */
    public void setBase_tax_amount(java.lang.String base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }


    /**
     * Gets the base_adjustment_positive value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_adjustment_positive
     */
    public java.lang.String getBase_adjustment_positive() {
        return base_adjustment_positive;
    }


    /**
     * Sets the base_adjustment_positive value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_adjustment_positive
     */
    public void setBase_adjustment_positive(java.lang.String base_adjustment_positive) {
        this.base_adjustment_positive = base_adjustment_positive;
    }


    /**
     * Gets the base_grand_total value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_grand_total
     */
    public java.lang.String getBase_grand_total() {
        return base_grand_total;
    }


    /**
     * Sets the base_grand_total value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_grand_total
     */
    public void setBase_grand_total(java.lang.String base_grand_total) {
        this.base_grand_total = base_grand_total;
    }


    /**
     * Gets the adjustment value for this SalesOrderCreditmemoEntity.
     * 
     * @return adjustment
     */
    public java.lang.String getAdjustment() {
        return adjustment;
    }


    /**
     * Sets the adjustment value for this SalesOrderCreditmemoEntity.
     * 
     * @param adjustment
     */
    public void setAdjustment(java.lang.String adjustment) {
        this.adjustment = adjustment;
    }


    /**
     * Gets the subtotal value for this SalesOrderCreditmemoEntity.
     * 
     * @return subtotal
     */
    public java.lang.String getSubtotal() {
        return subtotal;
    }


    /**
     * Sets the subtotal value for this SalesOrderCreditmemoEntity.
     * 
     * @param subtotal
     */
    public void setSubtotal(java.lang.String subtotal) {
        this.subtotal = subtotal;
    }


    /**
     * Gets the discount_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return discount_amount
     */
    public java.lang.String getDiscount_amount() {
        return discount_amount;
    }


    /**
     * Sets the discount_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param discount_amount
     */
    public void setDiscount_amount(java.lang.String discount_amount) {
        this.discount_amount = discount_amount;
    }


    /**
     * Gets the base_subtotal value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_subtotal
     */
    public java.lang.String getBase_subtotal() {
        return base_subtotal;
    }


    /**
     * Sets the base_subtotal value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_subtotal
     */
    public void setBase_subtotal(java.lang.String base_subtotal) {
        this.base_subtotal = base_subtotal;
    }


    /**
     * Gets the base_adjustment value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_adjustment
     */
    public java.lang.String getBase_adjustment() {
        return base_adjustment;
    }


    /**
     * Sets the base_adjustment value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_adjustment
     */
    public void setBase_adjustment(java.lang.String base_adjustment) {
        this.base_adjustment = base_adjustment;
    }


    /**
     * Gets the base_to_global_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_to_global_rate
     */
    public java.lang.String getBase_to_global_rate() {
        return base_to_global_rate;
    }


    /**
     * Sets the base_to_global_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_to_global_rate
     */
    public void setBase_to_global_rate(java.lang.String base_to_global_rate) {
        this.base_to_global_rate = base_to_global_rate;
    }


    /**
     * Gets the store_to_base_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @return store_to_base_rate
     */
    public java.lang.String getStore_to_base_rate() {
        return store_to_base_rate;
    }


    /**
     * Sets the store_to_base_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @param store_to_base_rate
     */
    public void setStore_to_base_rate(java.lang.String store_to_base_rate) {
        this.store_to_base_rate = store_to_base_rate;
    }


    /**
     * Gets the base_shipping_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_shipping_amount
     */
    public java.lang.String getBase_shipping_amount() {
        return base_shipping_amount;
    }


    /**
     * Sets the base_shipping_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_shipping_amount
     */
    public void setBase_shipping_amount(java.lang.String base_shipping_amount) {
        this.base_shipping_amount = base_shipping_amount;
    }


    /**
     * Gets the adjustment_negative value for this SalesOrderCreditmemoEntity.
     * 
     * @return adjustment_negative
     */
    public java.lang.String getAdjustment_negative() {
        return adjustment_negative;
    }


    /**
     * Sets the adjustment_negative value for this SalesOrderCreditmemoEntity.
     * 
     * @param adjustment_negative
     */
    public void setAdjustment_negative(java.lang.String adjustment_negative) {
        this.adjustment_negative = adjustment_negative;
    }


    /**
     * Gets the subtotal_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @return subtotal_incl_tax
     */
    public java.lang.String getSubtotal_incl_tax() {
        return subtotal_incl_tax;
    }


    /**
     * Sets the subtotal_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @param subtotal_incl_tax
     */
    public void setSubtotal_incl_tax(java.lang.String subtotal_incl_tax) {
        this.subtotal_incl_tax = subtotal_incl_tax;
    }


    /**
     * Gets the shipping_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return shipping_amount
     */
    public java.lang.String getShipping_amount() {
        return shipping_amount;
    }


    /**
     * Sets the shipping_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param shipping_amount
     */
    public void setShipping_amount(java.lang.String shipping_amount) {
        this.shipping_amount = shipping_amount;
    }


    /**
     * Gets the base_subtotal_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_subtotal_incl_tax
     */
    public java.lang.String getBase_subtotal_incl_tax() {
        return base_subtotal_incl_tax;
    }


    /**
     * Sets the base_subtotal_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_subtotal_incl_tax
     */
    public void setBase_subtotal_incl_tax(java.lang.String base_subtotal_incl_tax) {
        this.base_subtotal_incl_tax = base_subtotal_incl_tax;
    }


    /**
     * Gets the base_adjustment_negative value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_adjustment_negative
     */
    public java.lang.String getBase_adjustment_negative() {
        return base_adjustment_negative;
    }


    /**
     * Sets the base_adjustment_negative value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_adjustment_negative
     */
    public void setBase_adjustment_negative(java.lang.String base_adjustment_negative) {
        this.base_adjustment_negative = base_adjustment_negative;
    }


    /**
     * Gets the grand_total value for this SalesOrderCreditmemoEntity.
     * 
     * @return grand_total
     */
    public java.lang.String getGrand_total() {
        return grand_total;
    }


    /**
     * Sets the grand_total value for this SalesOrderCreditmemoEntity.
     * 
     * @param grand_total
     */
    public void setGrand_total(java.lang.String grand_total) {
        this.grand_total = grand_total;
    }


    /**
     * Gets the base_discount_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_discount_amount
     */
    public java.lang.String getBase_discount_amount() {
        return base_discount_amount;
    }


    /**
     * Sets the base_discount_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_discount_amount
     */
    public void setBase_discount_amount(java.lang.String base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }


    /**
     * Gets the base_to_order_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_to_order_rate
     */
    public java.lang.String getBase_to_order_rate() {
        return base_to_order_rate;
    }


    /**
     * Sets the base_to_order_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_to_order_rate
     */
    public void setBase_to_order_rate(java.lang.String base_to_order_rate) {
        this.base_to_order_rate = base_to_order_rate;
    }


    /**
     * Gets the store_to_order_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @return store_to_order_rate
     */
    public java.lang.String getStore_to_order_rate() {
        return store_to_order_rate;
    }


    /**
     * Sets the store_to_order_rate value for this SalesOrderCreditmemoEntity.
     * 
     * @param store_to_order_rate
     */
    public void setStore_to_order_rate(java.lang.String store_to_order_rate) {
        this.store_to_order_rate = store_to_order_rate;
    }


    /**
     * Gets the base_shipping_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_shipping_tax_amount
     */
    public java.lang.String getBase_shipping_tax_amount() {
        return base_shipping_tax_amount;
    }


    /**
     * Sets the base_shipping_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_shipping_tax_amount
     */
    public void setBase_shipping_tax_amount(java.lang.String base_shipping_tax_amount) {
        this.base_shipping_tax_amount = base_shipping_tax_amount;
    }


    /**
     * Gets the adjustment_positive value for this SalesOrderCreditmemoEntity.
     * 
     * @return adjustment_positive
     */
    public java.lang.String getAdjustment_positive() {
        return adjustment_positive;
    }


    /**
     * Sets the adjustment_positive value for this SalesOrderCreditmemoEntity.
     * 
     * @param adjustment_positive
     */
    public void setAdjustment_positive(java.lang.String adjustment_positive) {
        this.adjustment_positive = adjustment_positive;
    }


    /**
     * Gets the store_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return store_id
     */
    public java.lang.String getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.String store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the hidden_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return hidden_tax_amount
     */
    public java.lang.String getHidden_tax_amount() {
        return hidden_tax_amount;
    }


    /**
     * Sets the hidden_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param hidden_tax_amount
     */
    public void setHidden_tax_amount(java.lang.String hidden_tax_amount) {
        this.hidden_tax_amount = hidden_tax_amount;
    }


    /**
     * Gets the base_hidden_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_hidden_tax_amount
     */
    public java.lang.String getBase_hidden_tax_amount() {
        return base_hidden_tax_amount;
    }


    /**
     * Sets the base_hidden_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_hidden_tax_amount
     */
    public void setBase_hidden_tax_amount(java.lang.String base_hidden_tax_amount) {
        this.base_hidden_tax_amount = base_hidden_tax_amount;
    }


    /**
     * Gets the shipping_hidden_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return shipping_hidden_tax_amount
     */
    public java.lang.String getShipping_hidden_tax_amount() {
        return shipping_hidden_tax_amount;
    }


    /**
     * Sets the shipping_hidden_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param shipping_hidden_tax_amount
     */
    public void setShipping_hidden_tax_amount(java.lang.String shipping_hidden_tax_amount) {
        this.shipping_hidden_tax_amount = shipping_hidden_tax_amount;
    }


    /**
     * Gets the base_shipping_hidden_tax_amnt value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_shipping_hidden_tax_amnt
     */
    public java.lang.String getBase_shipping_hidden_tax_amnt() {
        return base_shipping_hidden_tax_amnt;
    }


    /**
     * Sets the base_shipping_hidden_tax_amnt value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_shipping_hidden_tax_amnt
     */
    public void setBase_shipping_hidden_tax_amnt(java.lang.String base_shipping_hidden_tax_amnt) {
        this.base_shipping_hidden_tax_amnt = base_shipping_hidden_tax_amnt;
    }


    /**
     * Gets the shipping_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @return shipping_incl_tax
     */
    public java.lang.String getShipping_incl_tax() {
        return shipping_incl_tax;
    }


    /**
     * Sets the shipping_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @param shipping_incl_tax
     */
    public void setShipping_incl_tax(java.lang.String shipping_incl_tax) {
        this.shipping_incl_tax = shipping_incl_tax;
    }


    /**
     * Gets the base_shipping_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_shipping_incl_tax
     */
    public java.lang.String getBase_shipping_incl_tax() {
        return base_shipping_incl_tax;
    }


    /**
     * Sets the base_shipping_incl_tax value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_shipping_incl_tax
     */
    public void setBase_shipping_incl_tax(java.lang.String base_shipping_incl_tax) {
        this.base_shipping_incl_tax = base_shipping_incl_tax;
    }


    /**
     * Gets the base_customer_balance_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_customer_balance_amount
     */
    public java.lang.String getBase_customer_balance_amount() {
        return base_customer_balance_amount;
    }


    /**
     * Sets the base_customer_balance_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_customer_balance_amount
     */
    public void setBase_customer_balance_amount(java.lang.String base_customer_balance_amount) {
        this.base_customer_balance_amount = base_customer_balance_amount;
    }


    /**
     * Gets the customer_balance_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return customer_balance_amount
     */
    public java.lang.String getCustomer_balance_amount() {
        return customer_balance_amount;
    }


    /**
     * Sets the customer_balance_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param customer_balance_amount
     */
    public void setCustomer_balance_amount(java.lang.String customer_balance_amount) {
        this.customer_balance_amount = customer_balance_amount;
    }


    /**
     * Gets the bs_customer_bal_total_refunded value for this SalesOrderCreditmemoEntity.
     * 
     * @return bs_customer_bal_total_refunded
     */
    public java.lang.String getBs_customer_bal_total_refunded() {
        return bs_customer_bal_total_refunded;
    }


    /**
     * Sets the bs_customer_bal_total_refunded value for this SalesOrderCreditmemoEntity.
     * 
     * @param bs_customer_bal_total_refunded
     */
    public void setBs_customer_bal_total_refunded(java.lang.String bs_customer_bal_total_refunded) {
        this.bs_customer_bal_total_refunded = bs_customer_bal_total_refunded;
    }


    /**
     * Gets the customer_bal_total_refunded value for this SalesOrderCreditmemoEntity.
     * 
     * @return customer_bal_total_refunded
     */
    public java.lang.String getCustomer_bal_total_refunded() {
        return customer_bal_total_refunded;
    }


    /**
     * Sets the customer_bal_total_refunded value for this SalesOrderCreditmemoEntity.
     * 
     * @param customer_bal_total_refunded
     */
    public void setCustomer_bal_total_refunded(java.lang.String customer_bal_total_refunded) {
        this.customer_bal_total_refunded = customer_bal_total_refunded;
    }


    /**
     * Gets the base_gift_cards_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_gift_cards_amount
     */
    public java.lang.String getBase_gift_cards_amount() {
        return base_gift_cards_amount;
    }


    /**
     * Sets the base_gift_cards_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_gift_cards_amount
     */
    public void setBase_gift_cards_amount(java.lang.String base_gift_cards_amount) {
        this.base_gift_cards_amount = base_gift_cards_amount;
    }


    /**
     * Gets the gift_cards_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return gift_cards_amount
     */
    public java.lang.String getGift_cards_amount() {
        return gift_cards_amount;
    }


    /**
     * Sets the gift_cards_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param gift_cards_amount
     */
    public void setGift_cards_amount(java.lang.String gift_cards_amount) {
        this.gift_cards_amount = gift_cards_amount;
    }


    /**
     * Gets the gw_base_price value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_base_price
     */
    public java.lang.String getGw_base_price() {
        return gw_base_price;
    }


    /**
     * Sets the gw_base_price value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_base_price
     */
    public void setGw_base_price(java.lang.String gw_base_price) {
        this.gw_base_price = gw_base_price;
    }


    /**
     * Gets the gw_price value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_price
     */
    public java.lang.String getGw_price() {
        return gw_price;
    }


    /**
     * Sets the gw_price value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_price
     */
    public void setGw_price(java.lang.String gw_price) {
        this.gw_price = gw_price;
    }


    /**
     * Gets the gw_items_base_price value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_items_base_price
     */
    public java.lang.String getGw_items_base_price() {
        return gw_items_base_price;
    }


    /**
     * Sets the gw_items_base_price value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_items_base_price
     */
    public void setGw_items_base_price(java.lang.String gw_items_base_price) {
        this.gw_items_base_price = gw_items_base_price;
    }


    /**
     * Gets the gw_items_price value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_items_price
     */
    public java.lang.String getGw_items_price() {
        return gw_items_price;
    }


    /**
     * Sets the gw_items_price value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_items_price
     */
    public void setGw_items_price(java.lang.String gw_items_price) {
        this.gw_items_price = gw_items_price;
    }


    /**
     * Gets the gw_card_base_price value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_card_base_price
     */
    public java.lang.String getGw_card_base_price() {
        return gw_card_base_price;
    }


    /**
     * Sets the gw_card_base_price value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_card_base_price
     */
    public void setGw_card_base_price(java.lang.String gw_card_base_price) {
        this.gw_card_base_price = gw_card_base_price;
    }


    /**
     * Gets the gw_card_price value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_card_price
     */
    public java.lang.String getGw_card_price() {
        return gw_card_price;
    }


    /**
     * Sets the gw_card_price value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_card_price
     */
    public void setGw_card_price(java.lang.String gw_card_price) {
        this.gw_card_price = gw_card_price;
    }


    /**
     * Gets the gw_base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_base_tax_amount
     */
    public java.lang.String getGw_base_tax_amount() {
        return gw_base_tax_amount;
    }


    /**
     * Sets the gw_base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_base_tax_amount
     */
    public void setGw_base_tax_amount(java.lang.String gw_base_tax_amount) {
        this.gw_base_tax_amount = gw_base_tax_amount;
    }


    /**
     * Gets the gw_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_tax_amount
     */
    public java.lang.String getGw_tax_amount() {
        return gw_tax_amount;
    }


    /**
     * Sets the gw_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_tax_amount
     */
    public void setGw_tax_amount(java.lang.String gw_tax_amount) {
        this.gw_tax_amount = gw_tax_amount;
    }


    /**
     * Gets the gw_items_base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_items_base_tax_amount
     */
    public java.lang.String getGw_items_base_tax_amount() {
        return gw_items_base_tax_amount;
    }


    /**
     * Sets the gw_items_base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_items_base_tax_amount
     */
    public void setGw_items_base_tax_amount(java.lang.String gw_items_base_tax_amount) {
        this.gw_items_base_tax_amount = gw_items_base_tax_amount;
    }


    /**
     * Gets the gw_items_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_items_tax_amount
     */
    public java.lang.String getGw_items_tax_amount() {
        return gw_items_tax_amount;
    }


    /**
     * Sets the gw_items_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_items_tax_amount
     */
    public void setGw_items_tax_amount(java.lang.String gw_items_tax_amount) {
        this.gw_items_tax_amount = gw_items_tax_amount;
    }


    /**
     * Gets the gw_card_base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_card_base_tax_amount
     */
    public java.lang.String getGw_card_base_tax_amount() {
        return gw_card_base_tax_amount;
    }


    /**
     * Sets the gw_card_base_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_card_base_tax_amount
     */
    public void setGw_card_base_tax_amount(java.lang.String gw_card_base_tax_amount) {
        this.gw_card_base_tax_amount = gw_card_base_tax_amount;
    }


    /**
     * Gets the gw_card_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return gw_card_tax_amount
     */
    public java.lang.String getGw_card_tax_amount() {
        return gw_card_tax_amount;
    }


    /**
     * Sets the gw_card_tax_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param gw_card_tax_amount
     */
    public void setGw_card_tax_amount(java.lang.String gw_card_tax_amount) {
        this.gw_card_tax_amount = gw_card_tax_amount;
    }


    /**
     * Gets the base_reward_currency_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return base_reward_currency_amount
     */
    public java.lang.String getBase_reward_currency_amount() {
        return base_reward_currency_amount;
    }


    /**
     * Sets the base_reward_currency_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param base_reward_currency_amount
     */
    public void setBase_reward_currency_amount(java.lang.String base_reward_currency_amount) {
        this.base_reward_currency_amount = base_reward_currency_amount;
    }


    /**
     * Gets the reward_currency_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @return reward_currency_amount
     */
    public java.lang.String getReward_currency_amount() {
        return reward_currency_amount;
    }


    /**
     * Sets the reward_currency_amount value for this SalesOrderCreditmemoEntity.
     * 
     * @param reward_currency_amount
     */
    public void setReward_currency_amount(java.lang.String reward_currency_amount) {
        this.reward_currency_amount = reward_currency_amount;
    }


    /**
     * Gets the reward_points_balance value for this SalesOrderCreditmemoEntity.
     * 
     * @return reward_points_balance
     */
    public java.lang.String getReward_points_balance() {
        return reward_points_balance;
    }


    /**
     * Sets the reward_points_balance value for this SalesOrderCreditmemoEntity.
     * 
     * @param reward_points_balance
     */
    public void setReward_points_balance(java.lang.String reward_points_balance) {
        this.reward_points_balance = reward_points_balance;
    }


    /**
     * Gets the reward_points_balance_refund value for this SalesOrderCreditmemoEntity.
     * 
     * @return reward_points_balance_refund
     */
    public java.lang.String getReward_points_balance_refund() {
        return reward_points_balance_refund;
    }


    /**
     * Sets the reward_points_balance_refund value for this SalesOrderCreditmemoEntity.
     * 
     * @param reward_points_balance_refund
     */
    public void setReward_points_balance_refund(java.lang.String reward_points_balance_refund) {
        this.reward_points_balance_refund = reward_points_balance_refund;
    }


    /**
     * Gets the creditmemo_id value for this SalesOrderCreditmemoEntity.
     * 
     * @return creditmemo_id
     */
    public java.lang.String getCreditmemo_id() {
        return creditmemo_id;
    }


    /**
     * Sets the creditmemo_id value for this SalesOrderCreditmemoEntity.
     * 
     * @param creditmemo_id
     */
    public void setCreditmemo_id(java.lang.String creditmemo_id) {
        this.creditmemo_id = creditmemo_id;
    }


    /**
     * Gets the items value for this SalesOrderCreditmemoEntity.
     * 
     * @return items
     */
    public SalesOrderCreditmemoItemEntity[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this SalesOrderCreditmemoEntity.
     * 
     * @param items
     */
    public void setItems(SalesOrderCreditmemoItemEntity[] items) {
        this.items = items;
    }


    /**
     * Gets the comments value for this SalesOrderCreditmemoEntity.
     * 
     * @return comments
     */
    public SalesOrderCreditmemoCommentEntity[] getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this SalesOrderCreditmemoEntity.
     * 
     * @param comments
     */
    public void setComments(SalesOrderCreditmemoCommentEntity[] comments) {
        this.comments = comments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderCreditmemoEntity)) return false;
        SalesOrderCreditmemoEntity other = (SalesOrderCreditmemoEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.increment_id==null && other.getIncrement_id()==null) || 
             (this.increment_id!=null &&
              this.increment_id.equals(other.getIncrement_id()))) &&
            ((this.transaction_id==null && other.getTransaction_id()==null) || 
             (this.transaction_id!=null &&
              this.transaction_id.equals(other.getTransaction_id()))) &&
            ((this.global_currency_code==null && other.getGlobal_currency_code()==null) || 
             (this.global_currency_code!=null &&
              this.global_currency_code.equals(other.getGlobal_currency_code()))) &&
            ((this.base_currency_code==null && other.getBase_currency_code()==null) || 
             (this.base_currency_code!=null &&
              this.base_currency_code.equals(other.getBase_currency_code()))) &&
            ((this.order_currency_code==null && other.getOrder_currency_code()==null) || 
             (this.order_currency_code!=null &&
              this.order_currency_code.equals(other.getOrder_currency_code()))) &&
            ((this.store_currency_code==null && other.getStore_currency_code()==null) || 
             (this.store_currency_code!=null &&
              this.store_currency_code.equals(other.getStore_currency_code()))) &&
            ((this.cybersource_token==null && other.getCybersource_token()==null) || 
             (this.cybersource_token!=null &&
              this.cybersource_token.equals(other.getCybersource_token()))) &&
            ((this.invoice_id==null && other.getInvoice_id()==null) || 
             (this.invoice_id!=null &&
              this.invoice_id.equals(other.getInvoice_id()))) &&
            ((this.billing_address_id==null && other.getBilling_address_id()==null) || 
             (this.billing_address_id!=null &&
              this.billing_address_id.equals(other.getBilling_address_id()))) &&
            ((this.shipping_address_id==null && other.getShipping_address_id()==null) || 
             (this.shipping_address_id!=null &&
              this.shipping_address_id.equals(other.getShipping_address_id()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.creditmemo_status==null && other.getCreditmemo_status()==null) || 
             (this.creditmemo_status!=null &&
              this.creditmemo_status.equals(other.getCreditmemo_status()))) &&
            ((this.email_sent==null && other.getEmail_sent()==null) || 
             (this.email_sent!=null &&
              this.email_sent.equals(other.getEmail_sent()))) &&
            ((this.order_id==null && other.getOrder_id()==null) || 
             (this.order_id!=null &&
              this.order_id.equals(other.getOrder_id()))) &&
            ((this.tax_amount==null && other.getTax_amount()==null) || 
             (this.tax_amount!=null &&
              this.tax_amount.equals(other.getTax_amount()))) &&
            ((this.shipping_tax_amount==null && other.getShipping_tax_amount()==null) || 
             (this.shipping_tax_amount!=null &&
              this.shipping_tax_amount.equals(other.getShipping_tax_amount()))) &&
            ((this.base_tax_amount==null && other.getBase_tax_amount()==null) || 
             (this.base_tax_amount!=null &&
              this.base_tax_amount.equals(other.getBase_tax_amount()))) &&
            ((this.base_adjustment_positive==null && other.getBase_adjustment_positive()==null) || 
             (this.base_adjustment_positive!=null &&
              this.base_adjustment_positive.equals(other.getBase_adjustment_positive()))) &&
            ((this.base_grand_total==null && other.getBase_grand_total()==null) || 
             (this.base_grand_total!=null &&
              this.base_grand_total.equals(other.getBase_grand_total()))) &&
            ((this.adjustment==null && other.getAdjustment()==null) || 
             (this.adjustment!=null &&
              this.adjustment.equals(other.getAdjustment()))) &&
            ((this.subtotal==null && other.getSubtotal()==null) || 
             (this.subtotal!=null &&
              this.subtotal.equals(other.getSubtotal()))) &&
            ((this.discount_amount==null && other.getDiscount_amount()==null) || 
             (this.discount_amount!=null &&
              this.discount_amount.equals(other.getDiscount_amount()))) &&
            ((this.base_subtotal==null && other.getBase_subtotal()==null) || 
             (this.base_subtotal!=null &&
              this.base_subtotal.equals(other.getBase_subtotal()))) &&
            ((this.base_adjustment==null && other.getBase_adjustment()==null) || 
             (this.base_adjustment!=null &&
              this.base_adjustment.equals(other.getBase_adjustment()))) &&
            ((this.base_to_global_rate==null && other.getBase_to_global_rate()==null) || 
             (this.base_to_global_rate!=null &&
              this.base_to_global_rate.equals(other.getBase_to_global_rate()))) &&
            ((this.store_to_base_rate==null && other.getStore_to_base_rate()==null) || 
             (this.store_to_base_rate!=null &&
              this.store_to_base_rate.equals(other.getStore_to_base_rate()))) &&
            ((this.base_shipping_amount==null && other.getBase_shipping_amount()==null) || 
             (this.base_shipping_amount!=null &&
              this.base_shipping_amount.equals(other.getBase_shipping_amount()))) &&
            ((this.adjustment_negative==null && other.getAdjustment_negative()==null) || 
             (this.adjustment_negative!=null &&
              this.adjustment_negative.equals(other.getAdjustment_negative()))) &&
            ((this.subtotal_incl_tax==null && other.getSubtotal_incl_tax()==null) || 
             (this.subtotal_incl_tax!=null &&
              this.subtotal_incl_tax.equals(other.getSubtotal_incl_tax()))) &&
            ((this.shipping_amount==null && other.getShipping_amount()==null) || 
             (this.shipping_amount!=null &&
              this.shipping_amount.equals(other.getShipping_amount()))) &&
            ((this.base_subtotal_incl_tax==null && other.getBase_subtotal_incl_tax()==null) || 
             (this.base_subtotal_incl_tax!=null &&
              this.base_subtotal_incl_tax.equals(other.getBase_subtotal_incl_tax()))) &&
            ((this.base_adjustment_negative==null && other.getBase_adjustment_negative()==null) || 
             (this.base_adjustment_negative!=null &&
              this.base_adjustment_negative.equals(other.getBase_adjustment_negative()))) &&
            ((this.grand_total==null && other.getGrand_total()==null) || 
             (this.grand_total!=null &&
              this.grand_total.equals(other.getGrand_total()))) &&
            ((this.base_discount_amount==null && other.getBase_discount_amount()==null) || 
             (this.base_discount_amount!=null &&
              this.base_discount_amount.equals(other.getBase_discount_amount()))) &&
            ((this.base_to_order_rate==null && other.getBase_to_order_rate()==null) || 
             (this.base_to_order_rate!=null &&
              this.base_to_order_rate.equals(other.getBase_to_order_rate()))) &&
            ((this.store_to_order_rate==null && other.getStore_to_order_rate()==null) || 
             (this.store_to_order_rate!=null &&
              this.store_to_order_rate.equals(other.getStore_to_order_rate()))) &&
            ((this.base_shipping_tax_amount==null && other.getBase_shipping_tax_amount()==null) || 
             (this.base_shipping_tax_amount!=null &&
              this.base_shipping_tax_amount.equals(other.getBase_shipping_tax_amount()))) &&
            ((this.adjustment_positive==null && other.getAdjustment_positive()==null) || 
             (this.adjustment_positive!=null &&
              this.adjustment_positive.equals(other.getAdjustment_positive()))) &&
            ((this.store_id==null && other.getStore_id()==null) || 
             (this.store_id!=null &&
              this.store_id.equals(other.getStore_id()))) &&
            ((this.hidden_tax_amount==null && other.getHidden_tax_amount()==null) || 
             (this.hidden_tax_amount!=null &&
              this.hidden_tax_amount.equals(other.getHidden_tax_amount()))) &&
            ((this.base_hidden_tax_amount==null && other.getBase_hidden_tax_amount()==null) || 
             (this.base_hidden_tax_amount!=null &&
              this.base_hidden_tax_amount.equals(other.getBase_hidden_tax_amount()))) &&
            ((this.shipping_hidden_tax_amount==null && other.getShipping_hidden_tax_amount()==null) || 
             (this.shipping_hidden_tax_amount!=null &&
              this.shipping_hidden_tax_amount.equals(other.getShipping_hidden_tax_amount()))) &&
            ((this.base_shipping_hidden_tax_amnt==null && other.getBase_shipping_hidden_tax_amnt()==null) || 
             (this.base_shipping_hidden_tax_amnt!=null &&
              this.base_shipping_hidden_tax_amnt.equals(other.getBase_shipping_hidden_tax_amnt()))) &&
            ((this.shipping_incl_tax==null && other.getShipping_incl_tax()==null) || 
             (this.shipping_incl_tax!=null &&
              this.shipping_incl_tax.equals(other.getShipping_incl_tax()))) &&
            ((this.base_shipping_incl_tax==null && other.getBase_shipping_incl_tax()==null) || 
             (this.base_shipping_incl_tax!=null &&
              this.base_shipping_incl_tax.equals(other.getBase_shipping_incl_tax()))) &&
            ((this.base_customer_balance_amount==null && other.getBase_customer_balance_amount()==null) || 
             (this.base_customer_balance_amount!=null &&
              this.base_customer_balance_amount.equals(other.getBase_customer_balance_amount()))) &&
            ((this.customer_balance_amount==null && other.getCustomer_balance_amount()==null) || 
             (this.customer_balance_amount!=null &&
              this.customer_balance_amount.equals(other.getCustomer_balance_amount()))) &&
            ((this.bs_customer_bal_total_refunded==null && other.getBs_customer_bal_total_refunded()==null) || 
             (this.bs_customer_bal_total_refunded!=null &&
              this.bs_customer_bal_total_refunded.equals(other.getBs_customer_bal_total_refunded()))) &&
            ((this.customer_bal_total_refunded==null && other.getCustomer_bal_total_refunded()==null) || 
             (this.customer_bal_total_refunded!=null &&
              this.customer_bal_total_refunded.equals(other.getCustomer_bal_total_refunded()))) &&
            ((this.base_gift_cards_amount==null && other.getBase_gift_cards_amount()==null) || 
             (this.base_gift_cards_amount!=null &&
              this.base_gift_cards_amount.equals(other.getBase_gift_cards_amount()))) &&
            ((this.gift_cards_amount==null && other.getGift_cards_amount()==null) || 
             (this.gift_cards_amount!=null &&
              this.gift_cards_amount.equals(other.getGift_cards_amount()))) &&
            ((this.gw_base_price==null && other.getGw_base_price()==null) || 
             (this.gw_base_price!=null &&
              this.gw_base_price.equals(other.getGw_base_price()))) &&
            ((this.gw_price==null && other.getGw_price()==null) || 
             (this.gw_price!=null &&
              this.gw_price.equals(other.getGw_price()))) &&
            ((this.gw_items_base_price==null && other.getGw_items_base_price()==null) || 
             (this.gw_items_base_price!=null &&
              this.gw_items_base_price.equals(other.getGw_items_base_price()))) &&
            ((this.gw_items_price==null && other.getGw_items_price()==null) || 
             (this.gw_items_price!=null &&
              this.gw_items_price.equals(other.getGw_items_price()))) &&
            ((this.gw_card_base_price==null && other.getGw_card_base_price()==null) || 
             (this.gw_card_base_price!=null &&
              this.gw_card_base_price.equals(other.getGw_card_base_price()))) &&
            ((this.gw_card_price==null && other.getGw_card_price()==null) || 
             (this.gw_card_price!=null &&
              this.gw_card_price.equals(other.getGw_card_price()))) &&
            ((this.gw_base_tax_amount==null && other.getGw_base_tax_amount()==null) || 
             (this.gw_base_tax_amount!=null &&
              this.gw_base_tax_amount.equals(other.getGw_base_tax_amount()))) &&
            ((this.gw_tax_amount==null && other.getGw_tax_amount()==null) || 
             (this.gw_tax_amount!=null &&
              this.gw_tax_amount.equals(other.getGw_tax_amount()))) &&
            ((this.gw_items_base_tax_amount==null && other.getGw_items_base_tax_amount()==null) || 
             (this.gw_items_base_tax_amount!=null &&
              this.gw_items_base_tax_amount.equals(other.getGw_items_base_tax_amount()))) &&
            ((this.gw_items_tax_amount==null && other.getGw_items_tax_amount()==null) || 
             (this.gw_items_tax_amount!=null &&
              this.gw_items_tax_amount.equals(other.getGw_items_tax_amount()))) &&
            ((this.gw_card_base_tax_amount==null && other.getGw_card_base_tax_amount()==null) || 
             (this.gw_card_base_tax_amount!=null &&
              this.gw_card_base_tax_amount.equals(other.getGw_card_base_tax_amount()))) &&
            ((this.gw_card_tax_amount==null && other.getGw_card_tax_amount()==null) || 
             (this.gw_card_tax_amount!=null &&
              this.gw_card_tax_amount.equals(other.getGw_card_tax_amount()))) &&
            ((this.base_reward_currency_amount==null && other.getBase_reward_currency_amount()==null) || 
             (this.base_reward_currency_amount!=null &&
              this.base_reward_currency_amount.equals(other.getBase_reward_currency_amount()))) &&
            ((this.reward_currency_amount==null && other.getReward_currency_amount()==null) || 
             (this.reward_currency_amount!=null &&
              this.reward_currency_amount.equals(other.getReward_currency_amount()))) &&
            ((this.reward_points_balance==null && other.getReward_points_balance()==null) || 
             (this.reward_points_balance!=null &&
              this.reward_points_balance.equals(other.getReward_points_balance()))) &&
            ((this.reward_points_balance_refund==null && other.getReward_points_balance_refund()==null) || 
             (this.reward_points_balance_refund!=null &&
              this.reward_points_balance_refund.equals(other.getReward_points_balance_refund()))) &&
            ((this.creditmemo_id==null && other.getCreditmemo_id()==null) || 
             (this.creditmemo_id!=null &&
              this.creditmemo_id.equals(other.getCreditmemo_id()))) &&
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              java.util.Arrays.equals(this.comments, other.getComments())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUpdated_at() != null) {
            _hashCode += getUpdated_at().hashCode();
        }
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getIncrement_id() != null) {
            _hashCode += getIncrement_id().hashCode();
        }
        if (getTransaction_id() != null) {
            _hashCode += getTransaction_id().hashCode();
        }
        if (getGlobal_currency_code() != null) {
            _hashCode += getGlobal_currency_code().hashCode();
        }
        if (getBase_currency_code() != null) {
            _hashCode += getBase_currency_code().hashCode();
        }
        if (getOrder_currency_code() != null) {
            _hashCode += getOrder_currency_code().hashCode();
        }
        if (getStore_currency_code() != null) {
            _hashCode += getStore_currency_code().hashCode();
        }
        if (getCybersource_token() != null) {
            _hashCode += getCybersource_token().hashCode();
        }
        if (getInvoice_id() != null) {
            _hashCode += getInvoice_id().hashCode();
        }
        if (getBilling_address_id() != null) {
            _hashCode += getBilling_address_id().hashCode();
        }
        if (getShipping_address_id() != null) {
            _hashCode += getShipping_address_id().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getCreditmemo_status() != null) {
            _hashCode += getCreditmemo_status().hashCode();
        }
        if (getEmail_sent() != null) {
            _hashCode += getEmail_sent().hashCode();
        }
        if (getOrder_id() != null) {
            _hashCode += getOrder_id().hashCode();
        }
        if (getTax_amount() != null) {
            _hashCode += getTax_amount().hashCode();
        }
        if (getShipping_tax_amount() != null) {
            _hashCode += getShipping_tax_amount().hashCode();
        }
        if (getBase_tax_amount() != null) {
            _hashCode += getBase_tax_amount().hashCode();
        }
        if (getBase_adjustment_positive() != null) {
            _hashCode += getBase_adjustment_positive().hashCode();
        }
        if (getBase_grand_total() != null) {
            _hashCode += getBase_grand_total().hashCode();
        }
        if (getAdjustment() != null) {
            _hashCode += getAdjustment().hashCode();
        }
        if (getSubtotal() != null) {
            _hashCode += getSubtotal().hashCode();
        }
        if (getDiscount_amount() != null) {
            _hashCode += getDiscount_amount().hashCode();
        }
        if (getBase_subtotal() != null) {
            _hashCode += getBase_subtotal().hashCode();
        }
        if (getBase_adjustment() != null) {
            _hashCode += getBase_adjustment().hashCode();
        }
        if (getBase_to_global_rate() != null) {
            _hashCode += getBase_to_global_rate().hashCode();
        }
        if (getStore_to_base_rate() != null) {
            _hashCode += getStore_to_base_rate().hashCode();
        }
        if (getBase_shipping_amount() != null) {
            _hashCode += getBase_shipping_amount().hashCode();
        }
        if (getAdjustment_negative() != null) {
            _hashCode += getAdjustment_negative().hashCode();
        }
        if (getSubtotal_incl_tax() != null) {
            _hashCode += getSubtotal_incl_tax().hashCode();
        }
        if (getShipping_amount() != null) {
            _hashCode += getShipping_amount().hashCode();
        }
        if (getBase_subtotal_incl_tax() != null) {
            _hashCode += getBase_subtotal_incl_tax().hashCode();
        }
        if (getBase_adjustment_negative() != null) {
            _hashCode += getBase_adjustment_negative().hashCode();
        }
        if (getGrand_total() != null) {
            _hashCode += getGrand_total().hashCode();
        }
        if (getBase_discount_amount() != null) {
            _hashCode += getBase_discount_amount().hashCode();
        }
        if (getBase_to_order_rate() != null) {
            _hashCode += getBase_to_order_rate().hashCode();
        }
        if (getStore_to_order_rate() != null) {
            _hashCode += getStore_to_order_rate().hashCode();
        }
        if (getBase_shipping_tax_amount() != null) {
            _hashCode += getBase_shipping_tax_amount().hashCode();
        }
        if (getAdjustment_positive() != null) {
            _hashCode += getAdjustment_positive().hashCode();
        }
        if (getStore_id() != null) {
            _hashCode += getStore_id().hashCode();
        }
        if (getHidden_tax_amount() != null) {
            _hashCode += getHidden_tax_amount().hashCode();
        }
        if (getBase_hidden_tax_amount() != null) {
            _hashCode += getBase_hidden_tax_amount().hashCode();
        }
        if (getShipping_hidden_tax_amount() != null) {
            _hashCode += getShipping_hidden_tax_amount().hashCode();
        }
        if (getBase_shipping_hidden_tax_amnt() != null) {
            _hashCode += getBase_shipping_hidden_tax_amnt().hashCode();
        }
        if (getShipping_incl_tax() != null) {
            _hashCode += getShipping_incl_tax().hashCode();
        }
        if (getBase_shipping_incl_tax() != null) {
            _hashCode += getBase_shipping_incl_tax().hashCode();
        }
        if (getBase_customer_balance_amount() != null) {
            _hashCode += getBase_customer_balance_amount().hashCode();
        }
        if (getCustomer_balance_amount() != null) {
            _hashCode += getCustomer_balance_amount().hashCode();
        }
        if (getBs_customer_bal_total_refunded() != null) {
            _hashCode += getBs_customer_bal_total_refunded().hashCode();
        }
        if (getCustomer_bal_total_refunded() != null) {
            _hashCode += getCustomer_bal_total_refunded().hashCode();
        }
        if (getBase_gift_cards_amount() != null) {
            _hashCode += getBase_gift_cards_amount().hashCode();
        }
        if (getGift_cards_amount() != null) {
            _hashCode += getGift_cards_amount().hashCode();
        }
        if (getGw_base_price() != null) {
            _hashCode += getGw_base_price().hashCode();
        }
        if (getGw_price() != null) {
            _hashCode += getGw_price().hashCode();
        }
        if (getGw_items_base_price() != null) {
            _hashCode += getGw_items_base_price().hashCode();
        }
        if (getGw_items_price() != null) {
            _hashCode += getGw_items_price().hashCode();
        }
        if (getGw_card_base_price() != null) {
            _hashCode += getGw_card_base_price().hashCode();
        }
        if (getGw_card_price() != null) {
            _hashCode += getGw_card_price().hashCode();
        }
        if (getGw_base_tax_amount() != null) {
            _hashCode += getGw_base_tax_amount().hashCode();
        }
        if (getGw_tax_amount() != null) {
            _hashCode += getGw_tax_amount().hashCode();
        }
        if (getGw_items_base_tax_amount() != null) {
            _hashCode += getGw_items_base_tax_amount().hashCode();
        }
        if (getGw_items_tax_amount() != null) {
            _hashCode += getGw_items_tax_amount().hashCode();
        }
        if (getGw_card_base_tax_amount() != null) {
            _hashCode += getGw_card_base_tax_amount().hashCode();
        }
        if (getGw_card_tax_amount() != null) {
            _hashCode += getGw_card_tax_amount().hashCode();
        }
        if (getBase_reward_currency_amount() != null) {
            _hashCode += getBase_reward_currency_amount().hashCode();
        }
        if (getReward_currency_amount() != null) {
            _hashCode += getReward_currency_amount().hashCode();
        }
        if (getReward_points_balance() != null) {
            _hashCode += getReward_points_balance().hashCode();
        }
        if (getReward_points_balance_refund() != null) {
            _hashCode += getReward_points_balance_refund().hashCode();
        }
        if (getCreditmemo_id() != null) {
            _hashCode += getCreditmemo_id().hashCode();
        }
        if (getItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderCreditmemoEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderCreditmemoEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updated_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updated_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("created_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "created_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("increment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "increment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transaction_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("global_currency_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "global_currency_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_currency_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_currency_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order_currency_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_currency_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_currency_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_currency_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cybersource_token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cybersource_token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invoice_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "invoice_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billing_address_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billing_address_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_address_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_address_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditmemo_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creditmemo_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email_sent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email_sent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_adjustment_positive");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_adjustment_positive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_grand_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_grand_total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adjustment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adjustment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_subtotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_adjustment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_adjustment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_to_global_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_to_global_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_to_base_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_to_base_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adjustment_negative");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adjustment_negative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtotal_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_subtotal_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_adjustment_negative");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_adjustment_negative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grand_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grand_total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_discount_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_discount_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_to_order_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_to_order_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_to_order_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_to_order_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adjustment_positive");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adjustment_positive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hidden_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hidden_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_hidden_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_hidden_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_hidden_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_hidden_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_hidden_tax_amnt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_hidden_tax_amnt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_customer_balance_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_customer_balance_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_balance_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_balance_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bs_customer_bal_total_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bs_customer_bal_total_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_bal_total_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_bal_total_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_gift_cards_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_gift_cards_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gift_cards_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_cards_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_base_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_base_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_items_base_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_items_base_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_items_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_items_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_card_base_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_card_base_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_card_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_card_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_base_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_base_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_items_base_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_items_base_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_items_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_items_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_card_base_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_card_base_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gw_card_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gw_card_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_reward_currency_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_reward_currency_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_currency_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_currency_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_points_balance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_points_balance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_points_balance_refund");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_points_balance_refund"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditmemo_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creditmemo_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderCreditmemoItemEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderCreditmemoCommentEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
