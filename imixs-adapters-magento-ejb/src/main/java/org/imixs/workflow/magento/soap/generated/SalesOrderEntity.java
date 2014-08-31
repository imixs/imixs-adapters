/**
 * SalesOrderEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderEntity  implements java.io.Serializable {
    private java.lang.String increment_id;

    private java.lang.String parent_id;

    private java.lang.String store_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String is_active;

    private java.lang.String customer_id;

    private java.lang.String tax_amount;

    private java.lang.String shipping_amount;

    private java.lang.String discount_amount;

    private java.lang.String subtotal;

    private java.lang.String grand_total;

    private java.lang.String total_paid;

    private java.lang.String total_refunded;

    private java.lang.String total_qty_ordered;

    private java.lang.String total_canceled;

    private java.lang.String total_invoiced;

    private java.lang.String total_online_refunded;

    private java.lang.String total_offline_refunded;

    private java.lang.String base_tax_amount;

    private java.lang.String base_shipping_amount;

    private java.lang.String base_discount_amount;

    private java.lang.String base_subtotal;

    private java.lang.String base_grand_total;

    private java.lang.String base_total_paid;

    private java.lang.String base_total_refunded;

    private java.lang.String base_total_qty_ordered;

    private java.lang.String base_total_canceled;

    private java.lang.String base_total_invoiced;

    private java.lang.String base_total_online_refunded;

    private java.lang.String base_total_offline_refunded;

    private java.lang.String billing_address_id;

    private java.lang.String billing_firstname;

    private java.lang.String billing_lastname;

    private java.lang.String shipping_address_id;

    private java.lang.String shipping_firstname;

    private java.lang.String shipping_lastname;

    private java.lang.String billing_name;

    private java.lang.String shipping_name;

    private java.lang.String store_to_base_rate;

    private java.lang.String store_to_order_rate;

    private java.lang.String base_to_global_rate;

    private java.lang.String base_to_order_rate;

    private java.lang.String weight;

    private java.lang.String store_name;

    private java.lang.String remote_ip;

    private java.lang.String status;

    private java.lang.String state;

    private java.lang.String applied_rule_ids;

    private java.lang.String global_currency_code;

    private java.lang.String base_currency_code;

    private java.lang.String store_currency_code;

    private java.lang.String order_currency_code;

    private java.lang.String shipping_method;

    private java.lang.String shipping_description;

    private java.lang.String customer_email;

    private java.lang.String customer_firstname;

    private java.lang.String customer_lastname;

    private java.lang.String quote_id;

    private java.lang.String is_virtual;

    private java.lang.String customer_group_id;

    private java.lang.String customer_note_notify;

    private java.lang.String customer_is_guest;

    private java.lang.String email_sent;

    private java.lang.String order_id;

    private java.lang.String gift_message_id;

    private java.lang.String gift_message;

    private SalesOrderAddressEntity shipping_address;

    private SalesOrderAddressEntity billing_address;

    private SalesOrderItemEntity[] items;

    private SalesOrderPaymentEntity payment;

    private SalesOrderStatusHistoryEntity[] status_history;

    public SalesOrderEntity() {
    }

    public SalesOrderEntity(
           java.lang.String increment_id,
           java.lang.String parent_id,
           java.lang.String store_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String is_active,
           java.lang.String customer_id,
           java.lang.String tax_amount,
           java.lang.String shipping_amount,
           java.lang.String discount_amount,
           java.lang.String subtotal,
           java.lang.String grand_total,
           java.lang.String total_paid,
           java.lang.String total_refunded,
           java.lang.String total_qty_ordered,
           java.lang.String total_canceled,
           java.lang.String total_invoiced,
           java.lang.String total_online_refunded,
           java.lang.String total_offline_refunded,
           java.lang.String base_tax_amount,
           java.lang.String base_shipping_amount,
           java.lang.String base_discount_amount,
           java.lang.String base_subtotal,
           java.lang.String base_grand_total,
           java.lang.String base_total_paid,
           java.lang.String base_total_refunded,
           java.lang.String base_total_qty_ordered,
           java.lang.String base_total_canceled,
           java.lang.String base_total_invoiced,
           java.lang.String base_total_online_refunded,
           java.lang.String base_total_offline_refunded,
           java.lang.String billing_address_id,
           java.lang.String billing_firstname,
           java.lang.String billing_lastname,
           java.lang.String shipping_address_id,
           java.lang.String shipping_firstname,
           java.lang.String shipping_lastname,
           java.lang.String billing_name,
           java.lang.String shipping_name,
           java.lang.String store_to_base_rate,
           java.lang.String store_to_order_rate,
           java.lang.String base_to_global_rate,
           java.lang.String base_to_order_rate,
           java.lang.String weight,
           java.lang.String store_name,
           java.lang.String remote_ip,
           java.lang.String status,
           java.lang.String state,
           java.lang.String applied_rule_ids,
           java.lang.String global_currency_code,
           java.lang.String base_currency_code,
           java.lang.String store_currency_code,
           java.lang.String order_currency_code,
           java.lang.String shipping_method,
           java.lang.String shipping_description,
           java.lang.String customer_email,
           java.lang.String customer_firstname,
           java.lang.String customer_lastname,
           java.lang.String quote_id,
           java.lang.String is_virtual,
           java.lang.String customer_group_id,
           java.lang.String customer_note_notify,
           java.lang.String customer_is_guest,
           java.lang.String email_sent,
           java.lang.String order_id,
           java.lang.String gift_message_id,
           java.lang.String gift_message,
           SalesOrderAddressEntity shipping_address,
           SalesOrderAddressEntity billing_address,
           SalesOrderItemEntity[] items,
           SalesOrderPaymentEntity payment,
           SalesOrderStatusHistoryEntity[] status_history) {
           this.increment_id = increment_id;
           this.parent_id = parent_id;
           this.store_id = store_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.is_active = is_active;
           this.customer_id = customer_id;
           this.tax_amount = tax_amount;
           this.shipping_amount = shipping_amount;
           this.discount_amount = discount_amount;
           this.subtotal = subtotal;
           this.grand_total = grand_total;
           this.total_paid = total_paid;
           this.total_refunded = total_refunded;
           this.total_qty_ordered = total_qty_ordered;
           this.total_canceled = total_canceled;
           this.total_invoiced = total_invoiced;
           this.total_online_refunded = total_online_refunded;
           this.total_offline_refunded = total_offline_refunded;
           this.base_tax_amount = base_tax_amount;
           this.base_shipping_amount = base_shipping_amount;
           this.base_discount_amount = base_discount_amount;
           this.base_subtotal = base_subtotal;
           this.base_grand_total = base_grand_total;
           this.base_total_paid = base_total_paid;
           this.base_total_refunded = base_total_refunded;
           this.base_total_qty_ordered = base_total_qty_ordered;
           this.base_total_canceled = base_total_canceled;
           this.base_total_invoiced = base_total_invoiced;
           this.base_total_online_refunded = base_total_online_refunded;
           this.base_total_offline_refunded = base_total_offline_refunded;
           this.billing_address_id = billing_address_id;
           this.billing_firstname = billing_firstname;
           this.billing_lastname = billing_lastname;
           this.shipping_address_id = shipping_address_id;
           this.shipping_firstname = shipping_firstname;
           this.shipping_lastname = shipping_lastname;
           this.billing_name = billing_name;
           this.shipping_name = shipping_name;
           this.store_to_base_rate = store_to_base_rate;
           this.store_to_order_rate = store_to_order_rate;
           this.base_to_global_rate = base_to_global_rate;
           this.base_to_order_rate = base_to_order_rate;
           this.weight = weight;
           this.store_name = store_name;
           this.remote_ip = remote_ip;
           this.status = status;
           this.state = state;
           this.applied_rule_ids = applied_rule_ids;
           this.global_currency_code = global_currency_code;
           this.base_currency_code = base_currency_code;
           this.store_currency_code = store_currency_code;
           this.order_currency_code = order_currency_code;
           this.shipping_method = shipping_method;
           this.shipping_description = shipping_description;
           this.customer_email = customer_email;
           this.customer_firstname = customer_firstname;
           this.customer_lastname = customer_lastname;
           this.quote_id = quote_id;
           this.is_virtual = is_virtual;
           this.customer_group_id = customer_group_id;
           this.customer_note_notify = customer_note_notify;
           this.customer_is_guest = customer_is_guest;
           this.email_sent = email_sent;
           this.order_id = order_id;
           this.gift_message_id = gift_message_id;
           this.gift_message = gift_message;
           this.shipping_address = shipping_address;
           this.billing_address = billing_address;
           this.items = items;
           this.payment = payment;
           this.status_history = status_history;
    }


    /**
     * Gets the increment_id value for this SalesOrderEntity.
     * 
     * @return increment_id
     */
    public java.lang.String getIncrement_id() {
        return increment_id;
    }


    /**
     * Sets the increment_id value for this SalesOrderEntity.
     * 
     * @param increment_id
     */
    public void setIncrement_id(java.lang.String increment_id) {
        this.increment_id = increment_id;
    }


    /**
     * Gets the parent_id value for this SalesOrderEntity.
     * 
     * @return parent_id
     */
    public java.lang.String getParent_id() {
        return parent_id;
    }


    /**
     * Sets the parent_id value for this SalesOrderEntity.
     * 
     * @param parent_id
     */
    public void setParent_id(java.lang.String parent_id) {
        this.parent_id = parent_id;
    }


    /**
     * Gets the store_id value for this SalesOrderEntity.
     * 
     * @return store_id
     */
    public java.lang.String getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this SalesOrderEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.String store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the created_at value for this SalesOrderEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this SalesOrderEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the is_active value for this SalesOrderEntity.
     * 
     * @return is_active
     */
    public java.lang.String getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this SalesOrderEntity.
     * 
     * @param is_active
     */
    public void setIs_active(java.lang.String is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the customer_id value for this SalesOrderEntity.
     * 
     * @return customer_id
     */
    public java.lang.String getCustomer_id() {
        return customer_id;
    }


    /**
     * Sets the customer_id value for this SalesOrderEntity.
     * 
     * @param customer_id
     */
    public void setCustomer_id(java.lang.String customer_id) {
        this.customer_id = customer_id;
    }


    /**
     * Gets the tax_amount value for this SalesOrderEntity.
     * 
     * @return tax_amount
     */
    public java.lang.String getTax_amount() {
        return tax_amount;
    }


    /**
     * Sets the tax_amount value for this SalesOrderEntity.
     * 
     * @param tax_amount
     */
    public void setTax_amount(java.lang.String tax_amount) {
        this.tax_amount = tax_amount;
    }


    /**
     * Gets the shipping_amount value for this SalesOrderEntity.
     * 
     * @return shipping_amount
     */
    public java.lang.String getShipping_amount() {
        return shipping_amount;
    }


    /**
     * Sets the shipping_amount value for this SalesOrderEntity.
     * 
     * @param shipping_amount
     */
    public void setShipping_amount(java.lang.String shipping_amount) {
        this.shipping_amount = shipping_amount;
    }


    /**
     * Gets the discount_amount value for this SalesOrderEntity.
     * 
     * @return discount_amount
     */
    public java.lang.String getDiscount_amount() {
        return discount_amount;
    }


    /**
     * Sets the discount_amount value for this SalesOrderEntity.
     * 
     * @param discount_amount
     */
    public void setDiscount_amount(java.lang.String discount_amount) {
        this.discount_amount = discount_amount;
    }


    /**
     * Gets the subtotal value for this SalesOrderEntity.
     * 
     * @return subtotal
     */
    public java.lang.String getSubtotal() {
        return subtotal;
    }


    /**
     * Sets the subtotal value for this SalesOrderEntity.
     * 
     * @param subtotal
     */
    public void setSubtotal(java.lang.String subtotal) {
        this.subtotal = subtotal;
    }


    /**
     * Gets the grand_total value for this SalesOrderEntity.
     * 
     * @return grand_total
     */
    public java.lang.String getGrand_total() {
        return grand_total;
    }


    /**
     * Sets the grand_total value for this SalesOrderEntity.
     * 
     * @param grand_total
     */
    public void setGrand_total(java.lang.String grand_total) {
        this.grand_total = grand_total;
    }


    /**
     * Gets the total_paid value for this SalesOrderEntity.
     * 
     * @return total_paid
     */
    public java.lang.String getTotal_paid() {
        return total_paid;
    }


    /**
     * Sets the total_paid value for this SalesOrderEntity.
     * 
     * @param total_paid
     */
    public void setTotal_paid(java.lang.String total_paid) {
        this.total_paid = total_paid;
    }


    /**
     * Gets the total_refunded value for this SalesOrderEntity.
     * 
     * @return total_refunded
     */
    public java.lang.String getTotal_refunded() {
        return total_refunded;
    }


    /**
     * Sets the total_refunded value for this SalesOrderEntity.
     * 
     * @param total_refunded
     */
    public void setTotal_refunded(java.lang.String total_refunded) {
        this.total_refunded = total_refunded;
    }


    /**
     * Gets the total_qty_ordered value for this SalesOrderEntity.
     * 
     * @return total_qty_ordered
     */
    public java.lang.String getTotal_qty_ordered() {
        return total_qty_ordered;
    }


    /**
     * Sets the total_qty_ordered value for this SalesOrderEntity.
     * 
     * @param total_qty_ordered
     */
    public void setTotal_qty_ordered(java.lang.String total_qty_ordered) {
        this.total_qty_ordered = total_qty_ordered;
    }


    /**
     * Gets the total_canceled value for this SalesOrderEntity.
     * 
     * @return total_canceled
     */
    public java.lang.String getTotal_canceled() {
        return total_canceled;
    }


    /**
     * Sets the total_canceled value for this SalesOrderEntity.
     * 
     * @param total_canceled
     */
    public void setTotal_canceled(java.lang.String total_canceled) {
        this.total_canceled = total_canceled;
    }


    /**
     * Gets the total_invoiced value for this SalesOrderEntity.
     * 
     * @return total_invoiced
     */
    public java.lang.String getTotal_invoiced() {
        return total_invoiced;
    }


    /**
     * Sets the total_invoiced value for this SalesOrderEntity.
     * 
     * @param total_invoiced
     */
    public void setTotal_invoiced(java.lang.String total_invoiced) {
        this.total_invoiced = total_invoiced;
    }


    /**
     * Gets the total_online_refunded value for this SalesOrderEntity.
     * 
     * @return total_online_refunded
     */
    public java.lang.String getTotal_online_refunded() {
        return total_online_refunded;
    }


    /**
     * Sets the total_online_refunded value for this SalesOrderEntity.
     * 
     * @param total_online_refunded
     */
    public void setTotal_online_refunded(java.lang.String total_online_refunded) {
        this.total_online_refunded = total_online_refunded;
    }


    /**
     * Gets the total_offline_refunded value for this SalesOrderEntity.
     * 
     * @return total_offline_refunded
     */
    public java.lang.String getTotal_offline_refunded() {
        return total_offline_refunded;
    }


    /**
     * Sets the total_offline_refunded value for this SalesOrderEntity.
     * 
     * @param total_offline_refunded
     */
    public void setTotal_offline_refunded(java.lang.String total_offline_refunded) {
        this.total_offline_refunded = total_offline_refunded;
    }


    /**
     * Gets the base_tax_amount value for this SalesOrderEntity.
     * 
     * @return base_tax_amount
     */
    public java.lang.String getBase_tax_amount() {
        return base_tax_amount;
    }


    /**
     * Sets the base_tax_amount value for this SalesOrderEntity.
     * 
     * @param base_tax_amount
     */
    public void setBase_tax_amount(java.lang.String base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }


    /**
     * Gets the base_shipping_amount value for this SalesOrderEntity.
     * 
     * @return base_shipping_amount
     */
    public java.lang.String getBase_shipping_amount() {
        return base_shipping_amount;
    }


    /**
     * Sets the base_shipping_amount value for this SalesOrderEntity.
     * 
     * @param base_shipping_amount
     */
    public void setBase_shipping_amount(java.lang.String base_shipping_amount) {
        this.base_shipping_amount = base_shipping_amount;
    }


    /**
     * Gets the base_discount_amount value for this SalesOrderEntity.
     * 
     * @return base_discount_amount
     */
    public java.lang.String getBase_discount_amount() {
        return base_discount_amount;
    }


    /**
     * Sets the base_discount_amount value for this SalesOrderEntity.
     * 
     * @param base_discount_amount
     */
    public void setBase_discount_amount(java.lang.String base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }


    /**
     * Gets the base_subtotal value for this SalesOrderEntity.
     * 
     * @return base_subtotal
     */
    public java.lang.String getBase_subtotal() {
        return base_subtotal;
    }


    /**
     * Sets the base_subtotal value for this SalesOrderEntity.
     * 
     * @param base_subtotal
     */
    public void setBase_subtotal(java.lang.String base_subtotal) {
        this.base_subtotal = base_subtotal;
    }


    /**
     * Gets the base_grand_total value for this SalesOrderEntity.
     * 
     * @return base_grand_total
     */
    public java.lang.String getBase_grand_total() {
        return base_grand_total;
    }


    /**
     * Sets the base_grand_total value for this SalesOrderEntity.
     * 
     * @param base_grand_total
     */
    public void setBase_grand_total(java.lang.String base_grand_total) {
        this.base_grand_total = base_grand_total;
    }


    /**
     * Gets the base_total_paid value for this SalesOrderEntity.
     * 
     * @return base_total_paid
     */
    public java.lang.String getBase_total_paid() {
        return base_total_paid;
    }


    /**
     * Sets the base_total_paid value for this SalesOrderEntity.
     * 
     * @param base_total_paid
     */
    public void setBase_total_paid(java.lang.String base_total_paid) {
        this.base_total_paid = base_total_paid;
    }


    /**
     * Gets the base_total_refunded value for this SalesOrderEntity.
     * 
     * @return base_total_refunded
     */
    public java.lang.String getBase_total_refunded() {
        return base_total_refunded;
    }


    /**
     * Sets the base_total_refunded value for this SalesOrderEntity.
     * 
     * @param base_total_refunded
     */
    public void setBase_total_refunded(java.lang.String base_total_refunded) {
        this.base_total_refunded = base_total_refunded;
    }


    /**
     * Gets the base_total_qty_ordered value for this SalesOrderEntity.
     * 
     * @return base_total_qty_ordered
     */
    public java.lang.String getBase_total_qty_ordered() {
        return base_total_qty_ordered;
    }


    /**
     * Sets the base_total_qty_ordered value for this SalesOrderEntity.
     * 
     * @param base_total_qty_ordered
     */
    public void setBase_total_qty_ordered(java.lang.String base_total_qty_ordered) {
        this.base_total_qty_ordered = base_total_qty_ordered;
    }


    /**
     * Gets the base_total_canceled value for this SalesOrderEntity.
     * 
     * @return base_total_canceled
     */
    public java.lang.String getBase_total_canceled() {
        return base_total_canceled;
    }


    /**
     * Sets the base_total_canceled value for this SalesOrderEntity.
     * 
     * @param base_total_canceled
     */
    public void setBase_total_canceled(java.lang.String base_total_canceled) {
        this.base_total_canceled = base_total_canceled;
    }


    /**
     * Gets the base_total_invoiced value for this SalesOrderEntity.
     * 
     * @return base_total_invoiced
     */
    public java.lang.String getBase_total_invoiced() {
        return base_total_invoiced;
    }


    /**
     * Sets the base_total_invoiced value for this SalesOrderEntity.
     * 
     * @param base_total_invoiced
     */
    public void setBase_total_invoiced(java.lang.String base_total_invoiced) {
        this.base_total_invoiced = base_total_invoiced;
    }


    /**
     * Gets the base_total_online_refunded value for this SalesOrderEntity.
     * 
     * @return base_total_online_refunded
     */
    public java.lang.String getBase_total_online_refunded() {
        return base_total_online_refunded;
    }


    /**
     * Sets the base_total_online_refunded value for this SalesOrderEntity.
     * 
     * @param base_total_online_refunded
     */
    public void setBase_total_online_refunded(java.lang.String base_total_online_refunded) {
        this.base_total_online_refunded = base_total_online_refunded;
    }


    /**
     * Gets the base_total_offline_refunded value for this SalesOrderEntity.
     * 
     * @return base_total_offline_refunded
     */
    public java.lang.String getBase_total_offline_refunded() {
        return base_total_offline_refunded;
    }


    /**
     * Sets the base_total_offline_refunded value for this SalesOrderEntity.
     * 
     * @param base_total_offline_refunded
     */
    public void setBase_total_offline_refunded(java.lang.String base_total_offline_refunded) {
        this.base_total_offline_refunded = base_total_offline_refunded;
    }


    /**
     * Gets the billing_address_id value for this SalesOrderEntity.
     * 
     * @return billing_address_id
     */
    public java.lang.String getBilling_address_id() {
        return billing_address_id;
    }


    /**
     * Sets the billing_address_id value for this SalesOrderEntity.
     * 
     * @param billing_address_id
     */
    public void setBilling_address_id(java.lang.String billing_address_id) {
        this.billing_address_id = billing_address_id;
    }


    /**
     * Gets the billing_firstname value for this SalesOrderEntity.
     * 
     * @return billing_firstname
     */
    public java.lang.String getBilling_firstname() {
        return billing_firstname;
    }


    /**
     * Sets the billing_firstname value for this SalesOrderEntity.
     * 
     * @param billing_firstname
     */
    public void setBilling_firstname(java.lang.String billing_firstname) {
        this.billing_firstname = billing_firstname;
    }


    /**
     * Gets the billing_lastname value for this SalesOrderEntity.
     * 
     * @return billing_lastname
     */
    public java.lang.String getBilling_lastname() {
        return billing_lastname;
    }


    /**
     * Sets the billing_lastname value for this SalesOrderEntity.
     * 
     * @param billing_lastname
     */
    public void setBilling_lastname(java.lang.String billing_lastname) {
        this.billing_lastname = billing_lastname;
    }


    /**
     * Gets the shipping_address_id value for this SalesOrderEntity.
     * 
     * @return shipping_address_id
     */
    public java.lang.String getShipping_address_id() {
        return shipping_address_id;
    }


    /**
     * Sets the shipping_address_id value for this SalesOrderEntity.
     * 
     * @param shipping_address_id
     */
    public void setShipping_address_id(java.lang.String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }


    /**
     * Gets the shipping_firstname value for this SalesOrderEntity.
     * 
     * @return shipping_firstname
     */
    public java.lang.String getShipping_firstname() {
        return shipping_firstname;
    }


    /**
     * Sets the shipping_firstname value for this SalesOrderEntity.
     * 
     * @param shipping_firstname
     */
    public void setShipping_firstname(java.lang.String shipping_firstname) {
        this.shipping_firstname = shipping_firstname;
    }


    /**
     * Gets the shipping_lastname value for this SalesOrderEntity.
     * 
     * @return shipping_lastname
     */
    public java.lang.String getShipping_lastname() {
        return shipping_lastname;
    }


    /**
     * Sets the shipping_lastname value for this SalesOrderEntity.
     * 
     * @param shipping_lastname
     */
    public void setShipping_lastname(java.lang.String shipping_lastname) {
        this.shipping_lastname = shipping_lastname;
    }


    /**
     * Gets the billing_name value for this SalesOrderEntity.
     * 
     * @return billing_name
     */
    public java.lang.String getBilling_name() {
        return billing_name;
    }


    /**
     * Sets the billing_name value for this SalesOrderEntity.
     * 
     * @param billing_name
     */
    public void setBilling_name(java.lang.String billing_name) {
        this.billing_name = billing_name;
    }


    /**
     * Gets the shipping_name value for this SalesOrderEntity.
     * 
     * @return shipping_name
     */
    public java.lang.String getShipping_name() {
        return shipping_name;
    }


    /**
     * Sets the shipping_name value for this SalesOrderEntity.
     * 
     * @param shipping_name
     */
    public void setShipping_name(java.lang.String shipping_name) {
        this.shipping_name = shipping_name;
    }


    /**
     * Gets the store_to_base_rate value for this SalesOrderEntity.
     * 
     * @return store_to_base_rate
     */
    public java.lang.String getStore_to_base_rate() {
        return store_to_base_rate;
    }


    /**
     * Sets the store_to_base_rate value for this SalesOrderEntity.
     * 
     * @param store_to_base_rate
     */
    public void setStore_to_base_rate(java.lang.String store_to_base_rate) {
        this.store_to_base_rate = store_to_base_rate;
    }


    /**
     * Gets the store_to_order_rate value for this SalesOrderEntity.
     * 
     * @return store_to_order_rate
     */
    public java.lang.String getStore_to_order_rate() {
        return store_to_order_rate;
    }


    /**
     * Sets the store_to_order_rate value for this SalesOrderEntity.
     * 
     * @param store_to_order_rate
     */
    public void setStore_to_order_rate(java.lang.String store_to_order_rate) {
        this.store_to_order_rate = store_to_order_rate;
    }


    /**
     * Gets the base_to_global_rate value for this SalesOrderEntity.
     * 
     * @return base_to_global_rate
     */
    public java.lang.String getBase_to_global_rate() {
        return base_to_global_rate;
    }


    /**
     * Sets the base_to_global_rate value for this SalesOrderEntity.
     * 
     * @param base_to_global_rate
     */
    public void setBase_to_global_rate(java.lang.String base_to_global_rate) {
        this.base_to_global_rate = base_to_global_rate;
    }


    /**
     * Gets the base_to_order_rate value for this SalesOrderEntity.
     * 
     * @return base_to_order_rate
     */
    public java.lang.String getBase_to_order_rate() {
        return base_to_order_rate;
    }


    /**
     * Sets the base_to_order_rate value for this SalesOrderEntity.
     * 
     * @param base_to_order_rate
     */
    public void setBase_to_order_rate(java.lang.String base_to_order_rate) {
        this.base_to_order_rate = base_to_order_rate;
    }


    /**
     * Gets the weight value for this SalesOrderEntity.
     * 
     * @return weight
     */
    public java.lang.String getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this SalesOrderEntity.
     * 
     * @param weight
     */
    public void setWeight(java.lang.String weight) {
        this.weight = weight;
    }


    /**
     * Gets the store_name value for this SalesOrderEntity.
     * 
     * @return store_name
     */
    public java.lang.String getStore_name() {
        return store_name;
    }


    /**
     * Sets the store_name value for this SalesOrderEntity.
     * 
     * @param store_name
     */
    public void setStore_name(java.lang.String store_name) {
        this.store_name = store_name;
    }


    /**
     * Gets the remote_ip value for this SalesOrderEntity.
     * 
     * @return remote_ip
     */
    public java.lang.String getRemote_ip() {
        return remote_ip;
    }


    /**
     * Sets the remote_ip value for this SalesOrderEntity.
     * 
     * @param remote_ip
     */
    public void setRemote_ip(java.lang.String remote_ip) {
        this.remote_ip = remote_ip;
    }


    /**
     * Gets the status value for this SalesOrderEntity.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SalesOrderEntity.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the state value for this SalesOrderEntity.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this SalesOrderEntity.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the applied_rule_ids value for this SalesOrderEntity.
     * 
     * @return applied_rule_ids
     */
    public java.lang.String getApplied_rule_ids() {
        return applied_rule_ids;
    }


    /**
     * Sets the applied_rule_ids value for this SalesOrderEntity.
     * 
     * @param applied_rule_ids
     */
    public void setApplied_rule_ids(java.lang.String applied_rule_ids) {
        this.applied_rule_ids = applied_rule_ids;
    }


    /**
     * Gets the global_currency_code value for this SalesOrderEntity.
     * 
     * @return global_currency_code
     */
    public java.lang.String getGlobal_currency_code() {
        return global_currency_code;
    }


    /**
     * Sets the global_currency_code value for this SalesOrderEntity.
     * 
     * @param global_currency_code
     */
    public void setGlobal_currency_code(java.lang.String global_currency_code) {
        this.global_currency_code = global_currency_code;
    }


    /**
     * Gets the base_currency_code value for this SalesOrderEntity.
     * 
     * @return base_currency_code
     */
    public java.lang.String getBase_currency_code() {
        return base_currency_code;
    }


    /**
     * Sets the base_currency_code value for this SalesOrderEntity.
     * 
     * @param base_currency_code
     */
    public void setBase_currency_code(java.lang.String base_currency_code) {
        this.base_currency_code = base_currency_code;
    }


    /**
     * Gets the store_currency_code value for this SalesOrderEntity.
     * 
     * @return store_currency_code
     */
    public java.lang.String getStore_currency_code() {
        return store_currency_code;
    }


    /**
     * Sets the store_currency_code value for this SalesOrderEntity.
     * 
     * @param store_currency_code
     */
    public void setStore_currency_code(java.lang.String store_currency_code) {
        this.store_currency_code = store_currency_code;
    }


    /**
     * Gets the order_currency_code value for this SalesOrderEntity.
     * 
     * @return order_currency_code
     */
    public java.lang.String getOrder_currency_code() {
        return order_currency_code;
    }


    /**
     * Sets the order_currency_code value for this SalesOrderEntity.
     * 
     * @param order_currency_code
     */
    public void setOrder_currency_code(java.lang.String order_currency_code) {
        this.order_currency_code = order_currency_code;
    }


    /**
     * Gets the shipping_method value for this SalesOrderEntity.
     * 
     * @return shipping_method
     */
    public java.lang.String getShipping_method() {
        return shipping_method;
    }


    /**
     * Sets the shipping_method value for this SalesOrderEntity.
     * 
     * @param shipping_method
     */
    public void setShipping_method(java.lang.String shipping_method) {
        this.shipping_method = shipping_method;
    }


    /**
     * Gets the shipping_description value for this SalesOrderEntity.
     * 
     * @return shipping_description
     */
    public java.lang.String getShipping_description() {
        return shipping_description;
    }


    /**
     * Sets the shipping_description value for this SalesOrderEntity.
     * 
     * @param shipping_description
     */
    public void setShipping_description(java.lang.String shipping_description) {
        this.shipping_description = shipping_description;
    }


    /**
     * Gets the customer_email value for this SalesOrderEntity.
     * 
     * @return customer_email
     */
    public java.lang.String getCustomer_email() {
        return customer_email;
    }


    /**
     * Sets the customer_email value for this SalesOrderEntity.
     * 
     * @param customer_email
     */
    public void setCustomer_email(java.lang.String customer_email) {
        this.customer_email = customer_email;
    }


    /**
     * Gets the customer_firstname value for this SalesOrderEntity.
     * 
     * @return customer_firstname
     */
    public java.lang.String getCustomer_firstname() {
        return customer_firstname;
    }


    /**
     * Sets the customer_firstname value for this SalesOrderEntity.
     * 
     * @param customer_firstname
     */
    public void setCustomer_firstname(java.lang.String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }


    /**
     * Gets the customer_lastname value for this SalesOrderEntity.
     * 
     * @return customer_lastname
     */
    public java.lang.String getCustomer_lastname() {
        return customer_lastname;
    }


    /**
     * Sets the customer_lastname value for this SalesOrderEntity.
     * 
     * @param customer_lastname
     */
    public void setCustomer_lastname(java.lang.String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }


    /**
     * Gets the quote_id value for this SalesOrderEntity.
     * 
     * @return quote_id
     */
    public java.lang.String getQuote_id() {
        return quote_id;
    }


    /**
     * Sets the quote_id value for this SalesOrderEntity.
     * 
     * @param quote_id
     */
    public void setQuote_id(java.lang.String quote_id) {
        this.quote_id = quote_id;
    }


    /**
     * Gets the is_virtual value for this SalesOrderEntity.
     * 
     * @return is_virtual
     */
    public java.lang.String getIs_virtual() {
        return is_virtual;
    }


    /**
     * Sets the is_virtual value for this SalesOrderEntity.
     * 
     * @param is_virtual
     */
    public void setIs_virtual(java.lang.String is_virtual) {
        this.is_virtual = is_virtual;
    }


    /**
     * Gets the customer_group_id value for this SalesOrderEntity.
     * 
     * @return customer_group_id
     */
    public java.lang.String getCustomer_group_id() {
        return customer_group_id;
    }


    /**
     * Sets the customer_group_id value for this SalesOrderEntity.
     * 
     * @param customer_group_id
     */
    public void setCustomer_group_id(java.lang.String customer_group_id) {
        this.customer_group_id = customer_group_id;
    }


    /**
     * Gets the customer_note_notify value for this SalesOrderEntity.
     * 
     * @return customer_note_notify
     */
    public java.lang.String getCustomer_note_notify() {
        return customer_note_notify;
    }


    /**
     * Sets the customer_note_notify value for this SalesOrderEntity.
     * 
     * @param customer_note_notify
     */
    public void setCustomer_note_notify(java.lang.String customer_note_notify) {
        this.customer_note_notify = customer_note_notify;
    }


    /**
     * Gets the customer_is_guest value for this SalesOrderEntity.
     * 
     * @return customer_is_guest
     */
    public java.lang.String getCustomer_is_guest() {
        return customer_is_guest;
    }


    /**
     * Sets the customer_is_guest value for this SalesOrderEntity.
     * 
     * @param customer_is_guest
     */
    public void setCustomer_is_guest(java.lang.String customer_is_guest) {
        this.customer_is_guest = customer_is_guest;
    }


    /**
     * Gets the email_sent value for this SalesOrderEntity.
     * 
     * @return email_sent
     */
    public java.lang.String getEmail_sent() {
        return email_sent;
    }


    /**
     * Sets the email_sent value for this SalesOrderEntity.
     * 
     * @param email_sent
     */
    public void setEmail_sent(java.lang.String email_sent) {
        this.email_sent = email_sent;
    }


    /**
     * Gets the order_id value for this SalesOrderEntity.
     * 
     * @return order_id
     */
    public java.lang.String getOrder_id() {
        return order_id;
    }


    /**
     * Sets the order_id value for this SalesOrderEntity.
     * 
     * @param order_id
     */
    public void setOrder_id(java.lang.String order_id) {
        this.order_id = order_id;
    }


    /**
     * Gets the gift_message_id value for this SalesOrderEntity.
     * 
     * @return gift_message_id
     */
    public java.lang.String getGift_message_id() {
        return gift_message_id;
    }


    /**
     * Sets the gift_message_id value for this SalesOrderEntity.
     * 
     * @param gift_message_id
     */
    public void setGift_message_id(java.lang.String gift_message_id) {
        this.gift_message_id = gift_message_id;
    }


    /**
     * Gets the gift_message value for this SalesOrderEntity.
     * 
     * @return gift_message
     */
    public java.lang.String getGift_message() {
        return gift_message;
    }


    /**
     * Sets the gift_message value for this SalesOrderEntity.
     * 
     * @param gift_message
     */
    public void setGift_message(java.lang.String gift_message) {
        this.gift_message = gift_message;
    }


    /**
     * Gets the shipping_address value for this SalesOrderEntity.
     * 
     * @return shipping_address
     */
    public SalesOrderAddressEntity getShipping_address() {
        return shipping_address;
    }


    /**
     * Sets the shipping_address value for this SalesOrderEntity.
     * 
     * @param shipping_address
     */
    public void setShipping_address(SalesOrderAddressEntity shipping_address) {
        this.shipping_address = shipping_address;
    }


    /**
     * Gets the billing_address value for this SalesOrderEntity.
     * 
     * @return billing_address
     */
    public SalesOrderAddressEntity getBilling_address() {
        return billing_address;
    }


    /**
     * Sets the billing_address value for this SalesOrderEntity.
     * 
     * @param billing_address
     */
    public void setBilling_address(SalesOrderAddressEntity billing_address) {
        this.billing_address = billing_address;
    }


    /**
     * Gets the items value for this SalesOrderEntity.
     * 
     * @return items
     */
    public SalesOrderItemEntity[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this SalesOrderEntity.
     * 
     * @param items
     */
    public void setItems(SalesOrderItemEntity[] items) {
        this.items = items;
    }


    /**
     * Gets the payment value for this SalesOrderEntity.
     * 
     * @return payment
     */
    public SalesOrderPaymentEntity getPayment() {
        return payment;
    }


    /**
     * Sets the payment value for this SalesOrderEntity.
     * 
     * @param payment
     */
    public void setPayment(SalesOrderPaymentEntity payment) {
        this.payment = payment;
    }


    /**
     * Gets the status_history value for this SalesOrderEntity.
     * 
     * @return status_history
     */
    public SalesOrderStatusHistoryEntity[] getStatus_history() {
        return status_history;
    }


    /**
     * Sets the status_history value for this SalesOrderEntity.
     * 
     * @param status_history
     */
    public void setStatus_history(SalesOrderStatusHistoryEntity[] status_history) {
        this.status_history = status_history;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderEntity)) return false;
        SalesOrderEntity other = (SalesOrderEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.increment_id==null && other.getIncrement_id()==null) || 
             (this.increment_id!=null &&
              this.increment_id.equals(other.getIncrement_id()))) &&
            ((this.parent_id==null && other.getParent_id()==null) || 
             (this.parent_id!=null &&
              this.parent_id.equals(other.getParent_id()))) &&
            ((this.store_id==null && other.getStore_id()==null) || 
             (this.store_id!=null &&
              this.store_id.equals(other.getStore_id()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
            ((this.is_active==null && other.getIs_active()==null) || 
             (this.is_active!=null &&
              this.is_active.equals(other.getIs_active()))) &&
            ((this.customer_id==null && other.getCustomer_id()==null) || 
             (this.customer_id!=null &&
              this.customer_id.equals(other.getCustomer_id()))) &&
            ((this.tax_amount==null && other.getTax_amount()==null) || 
             (this.tax_amount!=null &&
              this.tax_amount.equals(other.getTax_amount()))) &&
            ((this.shipping_amount==null && other.getShipping_amount()==null) || 
             (this.shipping_amount!=null &&
              this.shipping_amount.equals(other.getShipping_amount()))) &&
            ((this.discount_amount==null && other.getDiscount_amount()==null) || 
             (this.discount_amount!=null &&
              this.discount_amount.equals(other.getDiscount_amount()))) &&
            ((this.subtotal==null && other.getSubtotal()==null) || 
             (this.subtotal!=null &&
              this.subtotal.equals(other.getSubtotal()))) &&
            ((this.grand_total==null && other.getGrand_total()==null) || 
             (this.grand_total!=null &&
              this.grand_total.equals(other.getGrand_total()))) &&
            ((this.total_paid==null && other.getTotal_paid()==null) || 
             (this.total_paid!=null &&
              this.total_paid.equals(other.getTotal_paid()))) &&
            ((this.total_refunded==null && other.getTotal_refunded()==null) || 
             (this.total_refunded!=null &&
              this.total_refunded.equals(other.getTotal_refunded()))) &&
            ((this.total_qty_ordered==null && other.getTotal_qty_ordered()==null) || 
             (this.total_qty_ordered!=null &&
              this.total_qty_ordered.equals(other.getTotal_qty_ordered()))) &&
            ((this.total_canceled==null && other.getTotal_canceled()==null) || 
             (this.total_canceled!=null &&
              this.total_canceled.equals(other.getTotal_canceled()))) &&
            ((this.total_invoiced==null && other.getTotal_invoiced()==null) || 
             (this.total_invoiced!=null &&
              this.total_invoiced.equals(other.getTotal_invoiced()))) &&
            ((this.total_online_refunded==null && other.getTotal_online_refunded()==null) || 
             (this.total_online_refunded!=null &&
              this.total_online_refunded.equals(other.getTotal_online_refunded()))) &&
            ((this.total_offline_refunded==null && other.getTotal_offline_refunded()==null) || 
             (this.total_offline_refunded!=null &&
              this.total_offline_refunded.equals(other.getTotal_offline_refunded()))) &&
            ((this.base_tax_amount==null && other.getBase_tax_amount()==null) || 
             (this.base_tax_amount!=null &&
              this.base_tax_amount.equals(other.getBase_tax_amount()))) &&
            ((this.base_shipping_amount==null && other.getBase_shipping_amount()==null) || 
             (this.base_shipping_amount!=null &&
              this.base_shipping_amount.equals(other.getBase_shipping_amount()))) &&
            ((this.base_discount_amount==null && other.getBase_discount_amount()==null) || 
             (this.base_discount_amount!=null &&
              this.base_discount_amount.equals(other.getBase_discount_amount()))) &&
            ((this.base_subtotal==null && other.getBase_subtotal()==null) || 
             (this.base_subtotal!=null &&
              this.base_subtotal.equals(other.getBase_subtotal()))) &&
            ((this.base_grand_total==null && other.getBase_grand_total()==null) || 
             (this.base_grand_total!=null &&
              this.base_grand_total.equals(other.getBase_grand_total()))) &&
            ((this.base_total_paid==null && other.getBase_total_paid()==null) || 
             (this.base_total_paid!=null &&
              this.base_total_paid.equals(other.getBase_total_paid()))) &&
            ((this.base_total_refunded==null && other.getBase_total_refunded()==null) || 
             (this.base_total_refunded!=null &&
              this.base_total_refunded.equals(other.getBase_total_refunded()))) &&
            ((this.base_total_qty_ordered==null && other.getBase_total_qty_ordered()==null) || 
             (this.base_total_qty_ordered!=null &&
              this.base_total_qty_ordered.equals(other.getBase_total_qty_ordered()))) &&
            ((this.base_total_canceled==null && other.getBase_total_canceled()==null) || 
             (this.base_total_canceled!=null &&
              this.base_total_canceled.equals(other.getBase_total_canceled()))) &&
            ((this.base_total_invoiced==null && other.getBase_total_invoiced()==null) || 
             (this.base_total_invoiced!=null &&
              this.base_total_invoiced.equals(other.getBase_total_invoiced()))) &&
            ((this.base_total_online_refunded==null && other.getBase_total_online_refunded()==null) || 
             (this.base_total_online_refunded!=null &&
              this.base_total_online_refunded.equals(other.getBase_total_online_refunded()))) &&
            ((this.base_total_offline_refunded==null && other.getBase_total_offline_refunded()==null) || 
             (this.base_total_offline_refunded!=null &&
              this.base_total_offline_refunded.equals(other.getBase_total_offline_refunded()))) &&
            ((this.billing_address_id==null && other.getBilling_address_id()==null) || 
             (this.billing_address_id!=null &&
              this.billing_address_id.equals(other.getBilling_address_id()))) &&
            ((this.billing_firstname==null && other.getBilling_firstname()==null) || 
             (this.billing_firstname!=null &&
              this.billing_firstname.equals(other.getBilling_firstname()))) &&
            ((this.billing_lastname==null && other.getBilling_lastname()==null) || 
             (this.billing_lastname!=null &&
              this.billing_lastname.equals(other.getBilling_lastname()))) &&
            ((this.shipping_address_id==null && other.getShipping_address_id()==null) || 
             (this.shipping_address_id!=null &&
              this.shipping_address_id.equals(other.getShipping_address_id()))) &&
            ((this.shipping_firstname==null && other.getShipping_firstname()==null) || 
             (this.shipping_firstname!=null &&
              this.shipping_firstname.equals(other.getShipping_firstname()))) &&
            ((this.shipping_lastname==null && other.getShipping_lastname()==null) || 
             (this.shipping_lastname!=null &&
              this.shipping_lastname.equals(other.getShipping_lastname()))) &&
            ((this.billing_name==null && other.getBilling_name()==null) || 
             (this.billing_name!=null &&
              this.billing_name.equals(other.getBilling_name()))) &&
            ((this.shipping_name==null && other.getShipping_name()==null) || 
             (this.shipping_name!=null &&
              this.shipping_name.equals(other.getShipping_name()))) &&
            ((this.store_to_base_rate==null && other.getStore_to_base_rate()==null) || 
             (this.store_to_base_rate!=null &&
              this.store_to_base_rate.equals(other.getStore_to_base_rate()))) &&
            ((this.store_to_order_rate==null && other.getStore_to_order_rate()==null) || 
             (this.store_to_order_rate!=null &&
              this.store_to_order_rate.equals(other.getStore_to_order_rate()))) &&
            ((this.base_to_global_rate==null && other.getBase_to_global_rate()==null) || 
             (this.base_to_global_rate!=null &&
              this.base_to_global_rate.equals(other.getBase_to_global_rate()))) &&
            ((this.base_to_order_rate==null && other.getBase_to_order_rate()==null) || 
             (this.base_to_order_rate!=null &&
              this.base_to_order_rate.equals(other.getBase_to_order_rate()))) &&
            ((this.weight==null && other.getWeight()==null) || 
             (this.weight!=null &&
              this.weight.equals(other.getWeight()))) &&
            ((this.store_name==null && other.getStore_name()==null) || 
             (this.store_name!=null &&
              this.store_name.equals(other.getStore_name()))) &&
            ((this.remote_ip==null && other.getRemote_ip()==null) || 
             (this.remote_ip!=null &&
              this.remote_ip.equals(other.getRemote_ip()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.applied_rule_ids==null && other.getApplied_rule_ids()==null) || 
             (this.applied_rule_ids!=null &&
              this.applied_rule_ids.equals(other.getApplied_rule_ids()))) &&
            ((this.global_currency_code==null && other.getGlobal_currency_code()==null) || 
             (this.global_currency_code!=null &&
              this.global_currency_code.equals(other.getGlobal_currency_code()))) &&
            ((this.base_currency_code==null && other.getBase_currency_code()==null) || 
             (this.base_currency_code!=null &&
              this.base_currency_code.equals(other.getBase_currency_code()))) &&
            ((this.store_currency_code==null && other.getStore_currency_code()==null) || 
             (this.store_currency_code!=null &&
              this.store_currency_code.equals(other.getStore_currency_code()))) &&
            ((this.order_currency_code==null && other.getOrder_currency_code()==null) || 
             (this.order_currency_code!=null &&
              this.order_currency_code.equals(other.getOrder_currency_code()))) &&
            ((this.shipping_method==null && other.getShipping_method()==null) || 
             (this.shipping_method!=null &&
              this.shipping_method.equals(other.getShipping_method()))) &&
            ((this.shipping_description==null && other.getShipping_description()==null) || 
             (this.shipping_description!=null &&
              this.shipping_description.equals(other.getShipping_description()))) &&
            ((this.customer_email==null && other.getCustomer_email()==null) || 
             (this.customer_email!=null &&
              this.customer_email.equals(other.getCustomer_email()))) &&
            ((this.customer_firstname==null && other.getCustomer_firstname()==null) || 
             (this.customer_firstname!=null &&
              this.customer_firstname.equals(other.getCustomer_firstname()))) &&
            ((this.customer_lastname==null && other.getCustomer_lastname()==null) || 
             (this.customer_lastname!=null &&
              this.customer_lastname.equals(other.getCustomer_lastname()))) &&
            ((this.quote_id==null && other.getQuote_id()==null) || 
             (this.quote_id!=null &&
              this.quote_id.equals(other.getQuote_id()))) &&
            ((this.is_virtual==null && other.getIs_virtual()==null) || 
             (this.is_virtual!=null &&
              this.is_virtual.equals(other.getIs_virtual()))) &&
            ((this.customer_group_id==null && other.getCustomer_group_id()==null) || 
             (this.customer_group_id!=null &&
              this.customer_group_id.equals(other.getCustomer_group_id()))) &&
            ((this.customer_note_notify==null && other.getCustomer_note_notify()==null) || 
             (this.customer_note_notify!=null &&
              this.customer_note_notify.equals(other.getCustomer_note_notify()))) &&
            ((this.customer_is_guest==null && other.getCustomer_is_guest()==null) || 
             (this.customer_is_guest!=null &&
              this.customer_is_guest.equals(other.getCustomer_is_guest()))) &&
            ((this.email_sent==null && other.getEmail_sent()==null) || 
             (this.email_sent!=null &&
              this.email_sent.equals(other.getEmail_sent()))) &&
            ((this.order_id==null && other.getOrder_id()==null) || 
             (this.order_id!=null &&
              this.order_id.equals(other.getOrder_id()))) &&
            ((this.gift_message_id==null && other.getGift_message_id()==null) || 
             (this.gift_message_id!=null &&
              this.gift_message_id.equals(other.getGift_message_id()))) &&
            ((this.gift_message==null && other.getGift_message()==null) || 
             (this.gift_message!=null &&
              this.gift_message.equals(other.getGift_message()))) &&
            ((this.shipping_address==null && other.getShipping_address()==null) || 
             (this.shipping_address!=null &&
              this.shipping_address.equals(other.getShipping_address()))) &&
            ((this.billing_address==null && other.getBilling_address()==null) || 
             (this.billing_address!=null &&
              this.billing_address.equals(other.getBilling_address()))) &&
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems()))) &&
            ((this.payment==null && other.getPayment()==null) || 
             (this.payment!=null &&
              this.payment.equals(other.getPayment()))) &&
            ((this.status_history==null && other.getStatus_history()==null) || 
             (this.status_history!=null &&
              java.util.Arrays.equals(this.status_history, other.getStatus_history())));
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
        if (getIncrement_id() != null) {
            _hashCode += getIncrement_id().hashCode();
        }
        if (getParent_id() != null) {
            _hashCode += getParent_id().hashCode();
        }
        if (getStore_id() != null) {
            _hashCode += getStore_id().hashCode();
        }
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getUpdated_at() != null) {
            _hashCode += getUpdated_at().hashCode();
        }
        if (getIs_active() != null) {
            _hashCode += getIs_active().hashCode();
        }
        if (getCustomer_id() != null) {
            _hashCode += getCustomer_id().hashCode();
        }
        if (getTax_amount() != null) {
            _hashCode += getTax_amount().hashCode();
        }
        if (getShipping_amount() != null) {
            _hashCode += getShipping_amount().hashCode();
        }
        if (getDiscount_amount() != null) {
            _hashCode += getDiscount_amount().hashCode();
        }
        if (getSubtotal() != null) {
            _hashCode += getSubtotal().hashCode();
        }
        if (getGrand_total() != null) {
            _hashCode += getGrand_total().hashCode();
        }
        if (getTotal_paid() != null) {
            _hashCode += getTotal_paid().hashCode();
        }
        if (getTotal_refunded() != null) {
            _hashCode += getTotal_refunded().hashCode();
        }
        if (getTotal_qty_ordered() != null) {
            _hashCode += getTotal_qty_ordered().hashCode();
        }
        if (getTotal_canceled() != null) {
            _hashCode += getTotal_canceled().hashCode();
        }
        if (getTotal_invoiced() != null) {
            _hashCode += getTotal_invoiced().hashCode();
        }
        if (getTotal_online_refunded() != null) {
            _hashCode += getTotal_online_refunded().hashCode();
        }
        if (getTotal_offline_refunded() != null) {
            _hashCode += getTotal_offline_refunded().hashCode();
        }
        if (getBase_tax_amount() != null) {
            _hashCode += getBase_tax_amount().hashCode();
        }
        if (getBase_shipping_amount() != null) {
            _hashCode += getBase_shipping_amount().hashCode();
        }
        if (getBase_discount_amount() != null) {
            _hashCode += getBase_discount_amount().hashCode();
        }
        if (getBase_subtotal() != null) {
            _hashCode += getBase_subtotal().hashCode();
        }
        if (getBase_grand_total() != null) {
            _hashCode += getBase_grand_total().hashCode();
        }
        if (getBase_total_paid() != null) {
            _hashCode += getBase_total_paid().hashCode();
        }
        if (getBase_total_refunded() != null) {
            _hashCode += getBase_total_refunded().hashCode();
        }
        if (getBase_total_qty_ordered() != null) {
            _hashCode += getBase_total_qty_ordered().hashCode();
        }
        if (getBase_total_canceled() != null) {
            _hashCode += getBase_total_canceled().hashCode();
        }
        if (getBase_total_invoiced() != null) {
            _hashCode += getBase_total_invoiced().hashCode();
        }
        if (getBase_total_online_refunded() != null) {
            _hashCode += getBase_total_online_refunded().hashCode();
        }
        if (getBase_total_offline_refunded() != null) {
            _hashCode += getBase_total_offline_refunded().hashCode();
        }
        if (getBilling_address_id() != null) {
            _hashCode += getBilling_address_id().hashCode();
        }
        if (getBilling_firstname() != null) {
            _hashCode += getBilling_firstname().hashCode();
        }
        if (getBilling_lastname() != null) {
            _hashCode += getBilling_lastname().hashCode();
        }
        if (getShipping_address_id() != null) {
            _hashCode += getShipping_address_id().hashCode();
        }
        if (getShipping_firstname() != null) {
            _hashCode += getShipping_firstname().hashCode();
        }
        if (getShipping_lastname() != null) {
            _hashCode += getShipping_lastname().hashCode();
        }
        if (getBilling_name() != null) {
            _hashCode += getBilling_name().hashCode();
        }
        if (getShipping_name() != null) {
            _hashCode += getShipping_name().hashCode();
        }
        if (getStore_to_base_rate() != null) {
            _hashCode += getStore_to_base_rate().hashCode();
        }
        if (getStore_to_order_rate() != null) {
            _hashCode += getStore_to_order_rate().hashCode();
        }
        if (getBase_to_global_rate() != null) {
            _hashCode += getBase_to_global_rate().hashCode();
        }
        if (getBase_to_order_rate() != null) {
            _hashCode += getBase_to_order_rate().hashCode();
        }
        if (getWeight() != null) {
            _hashCode += getWeight().hashCode();
        }
        if (getStore_name() != null) {
            _hashCode += getStore_name().hashCode();
        }
        if (getRemote_ip() != null) {
            _hashCode += getRemote_ip().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getApplied_rule_ids() != null) {
            _hashCode += getApplied_rule_ids().hashCode();
        }
        if (getGlobal_currency_code() != null) {
            _hashCode += getGlobal_currency_code().hashCode();
        }
        if (getBase_currency_code() != null) {
            _hashCode += getBase_currency_code().hashCode();
        }
        if (getStore_currency_code() != null) {
            _hashCode += getStore_currency_code().hashCode();
        }
        if (getOrder_currency_code() != null) {
            _hashCode += getOrder_currency_code().hashCode();
        }
        if (getShipping_method() != null) {
            _hashCode += getShipping_method().hashCode();
        }
        if (getShipping_description() != null) {
            _hashCode += getShipping_description().hashCode();
        }
        if (getCustomer_email() != null) {
            _hashCode += getCustomer_email().hashCode();
        }
        if (getCustomer_firstname() != null) {
            _hashCode += getCustomer_firstname().hashCode();
        }
        if (getCustomer_lastname() != null) {
            _hashCode += getCustomer_lastname().hashCode();
        }
        if (getQuote_id() != null) {
            _hashCode += getQuote_id().hashCode();
        }
        if (getIs_virtual() != null) {
            _hashCode += getIs_virtual().hashCode();
        }
        if (getCustomer_group_id() != null) {
            _hashCode += getCustomer_group_id().hashCode();
        }
        if (getCustomer_note_notify() != null) {
            _hashCode += getCustomer_note_notify().hashCode();
        }
        if (getCustomer_is_guest() != null) {
            _hashCode += getCustomer_is_guest().hashCode();
        }
        if (getEmail_sent() != null) {
            _hashCode += getEmail_sent().hashCode();
        }
        if (getOrder_id() != null) {
            _hashCode += getOrder_id().hashCode();
        }
        if (getGift_message_id() != null) {
            _hashCode += getGift_message_id().hashCode();
        }
        if (getGift_message() != null) {
            _hashCode += getGift_message().hashCode();
        }
        if (getShipping_address() != null) {
            _hashCode += getShipping_address().hashCode();
        }
        if (getBilling_address() != null) {
            _hashCode += getBilling_address().hashCode();
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
        if (getPayment() != null) {
            _hashCode += getPayment().hashCode();
        }
        if (getStatus_history() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatus_history());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatus_history(), i);
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
        new org.apache.axis.description.TypeDesc(SalesOrderEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("increment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "increment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parent_id"));
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
        elemField.setFieldName("created_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "created_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updated_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updated_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_active");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_id"));
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
        elemField.setFieldName("shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_amount"));
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
        elemField.setFieldName("subtotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal"));
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
        elemField.setFieldName("total_paid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_paid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_qty_ordered");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_qty_ordered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_online_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_online_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_offline_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_offline_refunded"));
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
        elemField.setFieldName("base_shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_amount"));
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
        elemField.setFieldName("base_subtotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal"));
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
        elemField.setFieldName("base_total_paid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_paid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_total_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_total_qty_ordered");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_qty_ordered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_total_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_total_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_total_online_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_online_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_total_offline_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_offline_refunded"));
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
        elemField.setFieldName("billing_firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billing_firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billing_lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billing_lastname"));
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
        elemField.setFieldName("shipping_firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billing_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billing_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_name"));
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
        elemField.setFieldName("store_to_order_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_to_order_rate"));
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
        elemField.setFieldName("base_to_order_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_to_order_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remote_ip");
        elemField.setXmlName(new javax.xml.namespace.QName("", "remote_ip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
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
        elemField.setFieldName("applied_rule_ids");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applied_rule_ids"));
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
        elemField.setFieldName("store_currency_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_currency_code"));
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
        elemField.setFieldName("shipping_method");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_method"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quote_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quote_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_virtual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_virtual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_group_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_group_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_note_notify");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_note_notify"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_is_guest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_is_guest"));
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
        elemField.setFieldName("gift_message_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_message_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gift_message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderAddressEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billing_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billing_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderAddressEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderItemEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payment"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderPaymentEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status_history");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status_history"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderStatusHistoryEntity"));
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
