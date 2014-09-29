/**
 * SalesOrderListEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderListEntity  implements java.io.Serializable {
    private java.lang.String increment_id;

    private java.lang.String store_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

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

    private java.lang.String coupon_code;

    private java.lang.String protect_code;

    private java.lang.String base_discount_canceled;

    private java.lang.String base_discount_invoiced;

    private java.lang.String base_discount_refunded;

    private java.lang.String base_shipping_canceled;

    private java.lang.String base_shipping_invoiced;

    private java.lang.String base_shipping_refunded;

    private java.lang.String base_shipping_tax_amount;

    private java.lang.String base_shipping_tax_refunded;

    private java.lang.String base_subtotal_canceled;

    private java.lang.String base_subtotal_invoiced;

    private java.lang.String base_subtotal_refunded;

    private java.lang.String base_tax_canceled;

    private java.lang.String base_tax_invoiced;

    private java.lang.String base_tax_refunded;

    private java.lang.String base_total_invoiced_cost;

    private java.lang.String discount_canceled;

    private java.lang.String discount_invoiced;

    private java.lang.String discount_refunded;

    private java.lang.String shipping_canceled;

    private java.lang.String shipping_invoiced;

    private java.lang.String shipping_refunded;

    private java.lang.String shipping_tax_amount;

    private java.lang.String shipping_tax_refunded;

    private java.lang.String subtotal_canceled;

    private java.lang.String subtotal_invoiced;

    private java.lang.String subtotal_refunded;

    private java.lang.String tax_canceled;

    private java.lang.String tax_invoiced;

    private java.lang.String tax_refunded;

    private java.lang.String can_ship_partially;

    private java.lang.String can_ship_partially_item;

    private java.lang.String edit_increment;

    private java.lang.String forced_do_shipment_with_invoice;

    private java.lang.String payment_authorization_expiration;

    private java.lang.String paypal_ipn_customer_notified;

    private java.lang.String quote_address_id;

    private java.lang.String adjustment_negative;

    private java.lang.String adjustment_positive;

    private java.lang.String base_adjustment_negative;

    private java.lang.String base_adjustment_positive;

    private java.lang.String base_shipping_discount_amount;

    private java.lang.String base_subtotal_incl_tax;

    private java.lang.String base_total_due;

    private java.lang.String payment_authorization_amount;

    private java.lang.String shipping_discount_amount;

    private java.lang.String subtotal_incl_tax;

    private java.lang.String total_due;

    private java.lang.String customer_dob;

    private java.lang.String customer_middlename;

    private java.lang.String customer_prefix;

    private java.lang.String customer_suffix;

    private java.lang.String customer_taxvat;

    private java.lang.String discount_description;

    private java.lang.String ext_customer_id;

    private java.lang.String ext_order_id;

    private java.lang.String hold_before_state;

    private java.lang.String hold_before_status;

    private java.lang.String original_increment_id;

    private java.lang.String relation_child_id;

    private java.lang.String relation_child_real_id;

    private java.lang.String relation_parent_id;

    private java.lang.String relation_parent_real_id;

    private java.lang.String x_forwarded_for;

    private java.lang.String customer_note;

    private java.lang.String total_item_count;

    private java.lang.String customer_gender;

    private java.lang.String hidden_tax_amount;

    private java.lang.String base_hidden_tax_amount;

    private java.lang.String shipping_hidden_tax_amount;

    private java.lang.String base_shipping_hidden_tax_amount;

    private java.lang.String hidden_tax_invoiced;

    private java.lang.String base_hidden_tax_invoiced;

    private java.lang.String hidden_tax_refunded;

    private java.lang.String base_hidden_tax_refunded;

    private java.lang.String shipping_incl_tax;

    private java.lang.String base_shipping_incl_tax;

    private java.lang.String base_customer_balance_amount;

    private java.lang.String customer_balance_amount;

    private java.lang.String base_customer_balance_invoiced;

    private java.lang.String customer_balance_invoiced;

    private java.lang.String base_customer_balance_refunded;

    private java.lang.String customer_balance_refunded;

    private java.lang.String base_customer_balance_total_refunded;

    private java.lang.String customer_balance_total_refunded;

    private java.lang.String gift_cards;

    private java.lang.String base_gift_cards_amount;

    private java.lang.String gift_cards_amount;

    private java.lang.String base_gift_cards_invoiced;

    private java.lang.String gift_cards_invoiced;

    private java.lang.String base_gift_cards_refunded;

    private java.lang.String gift_cards_refunded;

    private java.lang.String reward_points_balance;

    private java.lang.String base_reward_currency_amount;

    private java.lang.String reward_currency_amount;

    private java.lang.String base_reward_currency_amount_invoiced;

    private java.lang.String reward_currency_amount_invoiced;

    private java.lang.String base_reward_currency_amount_refunded;

    private java.lang.String reward_currency_amount_refunded;

    private java.lang.String reward_points_balance_refunded;

    private java.lang.String reward_points_balance_to_refund;

    private java.lang.String reward_salesrule_points;

    private java.lang.String firstname;

    private java.lang.String lastname;

    private java.lang.String telephone;

    private java.lang.String postcode;

    public SalesOrderListEntity() {
    }

    public SalesOrderListEntity(
           java.lang.String increment_id,
           java.lang.String store_id,
           java.lang.String created_at,
           java.lang.String updated_at,
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
           java.lang.String coupon_code,
           java.lang.String protect_code,
           java.lang.String base_discount_canceled,
           java.lang.String base_discount_invoiced,
           java.lang.String base_discount_refunded,
           java.lang.String base_shipping_canceled,
           java.lang.String base_shipping_invoiced,
           java.lang.String base_shipping_refunded,
           java.lang.String base_shipping_tax_amount,
           java.lang.String base_shipping_tax_refunded,
           java.lang.String base_subtotal_canceled,
           java.lang.String base_subtotal_invoiced,
           java.lang.String base_subtotal_refunded,
           java.lang.String base_tax_canceled,
           java.lang.String base_tax_invoiced,
           java.lang.String base_tax_refunded,
           java.lang.String base_total_invoiced_cost,
           java.lang.String discount_canceled,
           java.lang.String discount_invoiced,
           java.lang.String discount_refunded,
           java.lang.String shipping_canceled,
           java.lang.String shipping_invoiced,
           java.lang.String shipping_refunded,
           java.lang.String shipping_tax_amount,
           java.lang.String shipping_tax_refunded,
           java.lang.String subtotal_canceled,
           java.lang.String subtotal_invoiced,
           java.lang.String subtotal_refunded,
           java.lang.String tax_canceled,
           java.lang.String tax_invoiced,
           java.lang.String tax_refunded,
           java.lang.String can_ship_partially,
           java.lang.String can_ship_partially_item,
           java.lang.String edit_increment,
           java.lang.String forced_do_shipment_with_invoice,
           java.lang.String payment_authorization_expiration,
           java.lang.String paypal_ipn_customer_notified,
           java.lang.String quote_address_id,
           java.lang.String adjustment_negative,
           java.lang.String adjustment_positive,
           java.lang.String base_adjustment_negative,
           java.lang.String base_adjustment_positive,
           java.lang.String base_shipping_discount_amount,
           java.lang.String base_subtotal_incl_tax,
           java.lang.String base_total_due,
           java.lang.String payment_authorization_amount,
           java.lang.String shipping_discount_amount,
           java.lang.String subtotal_incl_tax,
           java.lang.String total_due,
           java.lang.String customer_dob,
           java.lang.String customer_middlename,
           java.lang.String customer_prefix,
           java.lang.String customer_suffix,
           java.lang.String customer_taxvat,
           java.lang.String discount_description,
           java.lang.String ext_customer_id,
           java.lang.String ext_order_id,
           java.lang.String hold_before_state,
           java.lang.String hold_before_status,
           java.lang.String original_increment_id,
           java.lang.String relation_child_id,
           java.lang.String relation_child_real_id,
           java.lang.String relation_parent_id,
           java.lang.String relation_parent_real_id,
           java.lang.String x_forwarded_for,
           java.lang.String customer_note,
           java.lang.String total_item_count,
           java.lang.String customer_gender,
           java.lang.String hidden_tax_amount,
           java.lang.String base_hidden_tax_amount,
           java.lang.String shipping_hidden_tax_amount,
           java.lang.String base_shipping_hidden_tax_amount,
           java.lang.String hidden_tax_invoiced,
           java.lang.String base_hidden_tax_invoiced,
           java.lang.String hidden_tax_refunded,
           java.lang.String base_hidden_tax_refunded,
           java.lang.String shipping_incl_tax,
           java.lang.String base_shipping_incl_tax,
           java.lang.String base_customer_balance_amount,
           java.lang.String customer_balance_amount,
           java.lang.String base_customer_balance_invoiced,
           java.lang.String customer_balance_invoiced,
           java.lang.String base_customer_balance_refunded,
           java.lang.String customer_balance_refunded,
           java.lang.String base_customer_balance_total_refunded,
           java.lang.String customer_balance_total_refunded,
           java.lang.String gift_cards,
           java.lang.String base_gift_cards_amount,
           java.lang.String gift_cards_amount,
           java.lang.String base_gift_cards_invoiced,
           java.lang.String gift_cards_invoiced,
           java.lang.String base_gift_cards_refunded,
           java.lang.String gift_cards_refunded,
           java.lang.String reward_points_balance,
           java.lang.String base_reward_currency_amount,
           java.lang.String reward_currency_amount,
           java.lang.String base_reward_currency_amount_invoiced,
           java.lang.String reward_currency_amount_invoiced,
           java.lang.String base_reward_currency_amount_refunded,
           java.lang.String reward_currency_amount_refunded,
           java.lang.String reward_points_balance_refunded,
           java.lang.String reward_points_balance_to_refund,
           java.lang.String reward_salesrule_points,
           java.lang.String firstname,
           java.lang.String lastname,
           java.lang.String telephone,
           java.lang.String postcode) {
           this.increment_id = increment_id;
           this.store_id = store_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
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
           this.coupon_code = coupon_code;
           this.protect_code = protect_code;
           this.base_discount_canceled = base_discount_canceled;
           this.base_discount_invoiced = base_discount_invoiced;
           this.base_discount_refunded = base_discount_refunded;
           this.base_shipping_canceled = base_shipping_canceled;
           this.base_shipping_invoiced = base_shipping_invoiced;
           this.base_shipping_refunded = base_shipping_refunded;
           this.base_shipping_tax_amount = base_shipping_tax_amount;
           this.base_shipping_tax_refunded = base_shipping_tax_refunded;
           this.base_subtotal_canceled = base_subtotal_canceled;
           this.base_subtotal_invoiced = base_subtotal_invoiced;
           this.base_subtotal_refunded = base_subtotal_refunded;
           this.base_tax_canceled = base_tax_canceled;
           this.base_tax_invoiced = base_tax_invoiced;
           this.base_tax_refunded = base_tax_refunded;
           this.base_total_invoiced_cost = base_total_invoiced_cost;
           this.discount_canceled = discount_canceled;
           this.discount_invoiced = discount_invoiced;
           this.discount_refunded = discount_refunded;
           this.shipping_canceled = shipping_canceled;
           this.shipping_invoiced = shipping_invoiced;
           this.shipping_refunded = shipping_refunded;
           this.shipping_tax_amount = shipping_tax_amount;
           this.shipping_tax_refunded = shipping_tax_refunded;
           this.subtotal_canceled = subtotal_canceled;
           this.subtotal_invoiced = subtotal_invoiced;
           this.subtotal_refunded = subtotal_refunded;
           this.tax_canceled = tax_canceled;
           this.tax_invoiced = tax_invoiced;
           this.tax_refunded = tax_refunded;
           this.can_ship_partially = can_ship_partially;
           this.can_ship_partially_item = can_ship_partially_item;
           this.edit_increment = edit_increment;
           this.forced_do_shipment_with_invoice = forced_do_shipment_with_invoice;
           this.payment_authorization_expiration = payment_authorization_expiration;
           this.paypal_ipn_customer_notified = paypal_ipn_customer_notified;
           this.quote_address_id = quote_address_id;
           this.adjustment_negative = adjustment_negative;
           this.adjustment_positive = adjustment_positive;
           this.base_adjustment_negative = base_adjustment_negative;
           this.base_adjustment_positive = base_adjustment_positive;
           this.base_shipping_discount_amount = base_shipping_discount_amount;
           this.base_subtotal_incl_tax = base_subtotal_incl_tax;
           this.base_total_due = base_total_due;
           this.payment_authorization_amount = payment_authorization_amount;
           this.shipping_discount_amount = shipping_discount_amount;
           this.subtotal_incl_tax = subtotal_incl_tax;
           this.total_due = total_due;
           this.customer_dob = customer_dob;
           this.customer_middlename = customer_middlename;
           this.customer_prefix = customer_prefix;
           this.customer_suffix = customer_suffix;
           this.customer_taxvat = customer_taxvat;
           this.discount_description = discount_description;
           this.ext_customer_id = ext_customer_id;
           this.ext_order_id = ext_order_id;
           this.hold_before_state = hold_before_state;
           this.hold_before_status = hold_before_status;
           this.original_increment_id = original_increment_id;
           this.relation_child_id = relation_child_id;
           this.relation_child_real_id = relation_child_real_id;
           this.relation_parent_id = relation_parent_id;
           this.relation_parent_real_id = relation_parent_real_id;
           this.x_forwarded_for = x_forwarded_for;
           this.customer_note = customer_note;
           this.total_item_count = total_item_count;
           this.customer_gender = customer_gender;
           this.hidden_tax_amount = hidden_tax_amount;
           this.base_hidden_tax_amount = base_hidden_tax_amount;
           this.shipping_hidden_tax_amount = shipping_hidden_tax_amount;
           this.base_shipping_hidden_tax_amount = base_shipping_hidden_tax_amount;
           this.hidden_tax_invoiced = hidden_tax_invoiced;
           this.base_hidden_tax_invoiced = base_hidden_tax_invoiced;
           this.hidden_tax_refunded = hidden_tax_refunded;
           this.base_hidden_tax_refunded = base_hidden_tax_refunded;
           this.shipping_incl_tax = shipping_incl_tax;
           this.base_shipping_incl_tax = base_shipping_incl_tax;
           this.base_customer_balance_amount = base_customer_balance_amount;
           this.customer_balance_amount = customer_balance_amount;
           this.base_customer_balance_invoiced = base_customer_balance_invoiced;
           this.customer_balance_invoiced = customer_balance_invoiced;
           this.base_customer_balance_refunded = base_customer_balance_refunded;
           this.customer_balance_refunded = customer_balance_refunded;
           this.base_customer_balance_total_refunded = base_customer_balance_total_refunded;
           this.customer_balance_total_refunded = customer_balance_total_refunded;
           this.gift_cards = gift_cards;
           this.base_gift_cards_amount = base_gift_cards_amount;
           this.gift_cards_amount = gift_cards_amount;
           this.base_gift_cards_invoiced = base_gift_cards_invoiced;
           this.gift_cards_invoiced = gift_cards_invoiced;
           this.base_gift_cards_refunded = base_gift_cards_refunded;
           this.gift_cards_refunded = gift_cards_refunded;
           this.reward_points_balance = reward_points_balance;
           this.base_reward_currency_amount = base_reward_currency_amount;
           this.reward_currency_amount = reward_currency_amount;
           this.base_reward_currency_amount_invoiced = base_reward_currency_amount_invoiced;
           this.reward_currency_amount_invoiced = reward_currency_amount_invoiced;
           this.base_reward_currency_amount_refunded = base_reward_currency_amount_refunded;
           this.reward_currency_amount_refunded = reward_currency_amount_refunded;
           this.reward_points_balance_refunded = reward_points_balance_refunded;
           this.reward_points_balance_to_refund = reward_points_balance_to_refund;
           this.reward_salesrule_points = reward_salesrule_points;
           this.firstname = firstname;
           this.lastname = lastname;
           this.telephone = telephone;
           this.postcode = postcode;
    }


    /**
     * Gets the increment_id value for this SalesOrderListEntity.
     * 
     * @return increment_id
     */
    public java.lang.String getIncrement_id() {
        return increment_id;
    }


    /**
     * Sets the increment_id value for this SalesOrderListEntity.
     * 
     * @param increment_id
     */
    public void setIncrement_id(java.lang.String increment_id) {
        this.increment_id = increment_id;
    }


    /**
     * Gets the store_id value for this SalesOrderListEntity.
     * 
     * @return store_id
     */
    public java.lang.String getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this SalesOrderListEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.String store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the created_at value for this SalesOrderListEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderListEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this SalesOrderListEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderListEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the customer_id value for this SalesOrderListEntity.
     * 
     * @return customer_id
     */
    public java.lang.String getCustomer_id() {
        return customer_id;
    }


    /**
     * Sets the customer_id value for this SalesOrderListEntity.
     * 
     * @param customer_id
     */
    public void setCustomer_id(java.lang.String customer_id) {
        this.customer_id = customer_id;
    }


    /**
     * Gets the tax_amount value for this SalesOrderListEntity.
     * 
     * @return tax_amount
     */
    public java.lang.String getTax_amount() {
        return tax_amount;
    }


    /**
     * Sets the tax_amount value for this SalesOrderListEntity.
     * 
     * @param tax_amount
     */
    public void setTax_amount(java.lang.String tax_amount) {
        this.tax_amount = tax_amount;
    }


    /**
     * Gets the shipping_amount value for this SalesOrderListEntity.
     * 
     * @return shipping_amount
     */
    public java.lang.String getShipping_amount() {
        return shipping_amount;
    }


    /**
     * Sets the shipping_amount value for this SalesOrderListEntity.
     * 
     * @param shipping_amount
     */
    public void setShipping_amount(java.lang.String shipping_amount) {
        this.shipping_amount = shipping_amount;
    }


    /**
     * Gets the discount_amount value for this SalesOrderListEntity.
     * 
     * @return discount_amount
     */
    public java.lang.String getDiscount_amount() {
        return discount_amount;
    }


    /**
     * Sets the discount_amount value for this SalesOrderListEntity.
     * 
     * @param discount_amount
     */
    public void setDiscount_amount(java.lang.String discount_amount) {
        this.discount_amount = discount_amount;
    }


    /**
     * Gets the subtotal value for this SalesOrderListEntity.
     * 
     * @return subtotal
     */
    public java.lang.String getSubtotal() {
        return subtotal;
    }


    /**
     * Sets the subtotal value for this SalesOrderListEntity.
     * 
     * @param subtotal
     */
    public void setSubtotal(java.lang.String subtotal) {
        this.subtotal = subtotal;
    }


    /**
     * Gets the grand_total value for this SalesOrderListEntity.
     * 
     * @return grand_total
     */
    public java.lang.String getGrand_total() {
        return grand_total;
    }


    /**
     * Sets the grand_total value for this SalesOrderListEntity.
     * 
     * @param grand_total
     */
    public void setGrand_total(java.lang.String grand_total) {
        this.grand_total = grand_total;
    }


    /**
     * Gets the total_paid value for this SalesOrderListEntity.
     * 
     * @return total_paid
     */
    public java.lang.String getTotal_paid() {
        return total_paid;
    }


    /**
     * Sets the total_paid value for this SalesOrderListEntity.
     * 
     * @param total_paid
     */
    public void setTotal_paid(java.lang.String total_paid) {
        this.total_paid = total_paid;
    }


    /**
     * Gets the total_refunded value for this SalesOrderListEntity.
     * 
     * @return total_refunded
     */
    public java.lang.String getTotal_refunded() {
        return total_refunded;
    }


    /**
     * Sets the total_refunded value for this SalesOrderListEntity.
     * 
     * @param total_refunded
     */
    public void setTotal_refunded(java.lang.String total_refunded) {
        this.total_refunded = total_refunded;
    }


    /**
     * Gets the total_qty_ordered value for this SalesOrderListEntity.
     * 
     * @return total_qty_ordered
     */
    public java.lang.String getTotal_qty_ordered() {
        return total_qty_ordered;
    }


    /**
     * Sets the total_qty_ordered value for this SalesOrderListEntity.
     * 
     * @param total_qty_ordered
     */
    public void setTotal_qty_ordered(java.lang.String total_qty_ordered) {
        this.total_qty_ordered = total_qty_ordered;
    }


    /**
     * Gets the total_canceled value for this SalesOrderListEntity.
     * 
     * @return total_canceled
     */
    public java.lang.String getTotal_canceled() {
        return total_canceled;
    }


    /**
     * Sets the total_canceled value for this SalesOrderListEntity.
     * 
     * @param total_canceled
     */
    public void setTotal_canceled(java.lang.String total_canceled) {
        this.total_canceled = total_canceled;
    }


    /**
     * Gets the total_invoiced value for this SalesOrderListEntity.
     * 
     * @return total_invoiced
     */
    public java.lang.String getTotal_invoiced() {
        return total_invoiced;
    }


    /**
     * Sets the total_invoiced value for this SalesOrderListEntity.
     * 
     * @param total_invoiced
     */
    public void setTotal_invoiced(java.lang.String total_invoiced) {
        this.total_invoiced = total_invoiced;
    }


    /**
     * Gets the total_online_refunded value for this SalesOrderListEntity.
     * 
     * @return total_online_refunded
     */
    public java.lang.String getTotal_online_refunded() {
        return total_online_refunded;
    }


    /**
     * Sets the total_online_refunded value for this SalesOrderListEntity.
     * 
     * @param total_online_refunded
     */
    public void setTotal_online_refunded(java.lang.String total_online_refunded) {
        this.total_online_refunded = total_online_refunded;
    }


    /**
     * Gets the total_offline_refunded value for this SalesOrderListEntity.
     * 
     * @return total_offline_refunded
     */
    public java.lang.String getTotal_offline_refunded() {
        return total_offline_refunded;
    }


    /**
     * Sets the total_offline_refunded value for this SalesOrderListEntity.
     * 
     * @param total_offline_refunded
     */
    public void setTotal_offline_refunded(java.lang.String total_offline_refunded) {
        this.total_offline_refunded = total_offline_refunded;
    }


    /**
     * Gets the base_tax_amount value for this SalesOrderListEntity.
     * 
     * @return base_tax_amount
     */
    public java.lang.String getBase_tax_amount() {
        return base_tax_amount;
    }


    /**
     * Sets the base_tax_amount value for this SalesOrderListEntity.
     * 
     * @param base_tax_amount
     */
    public void setBase_tax_amount(java.lang.String base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }


    /**
     * Gets the base_shipping_amount value for this SalesOrderListEntity.
     * 
     * @return base_shipping_amount
     */
    public java.lang.String getBase_shipping_amount() {
        return base_shipping_amount;
    }


    /**
     * Sets the base_shipping_amount value for this SalesOrderListEntity.
     * 
     * @param base_shipping_amount
     */
    public void setBase_shipping_amount(java.lang.String base_shipping_amount) {
        this.base_shipping_amount = base_shipping_amount;
    }


    /**
     * Gets the base_discount_amount value for this SalesOrderListEntity.
     * 
     * @return base_discount_amount
     */
    public java.lang.String getBase_discount_amount() {
        return base_discount_amount;
    }


    /**
     * Sets the base_discount_amount value for this SalesOrderListEntity.
     * 
     * @param base_discount_amount
     */
    public void setBase_discount_amount(java.lang.String base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }


    /**
     * Gets the base_subtotal value for this SalesOrderListEntity.
     * 
     * @return base_subtotal
     */
    public java.lang.String getBase_subtotal() {
        return base_subtotal;
    }


    /**
     * Sets the base_subtotal value for this SalesOrderListEntity.
     * 
     * @param base_subtotal
     */
    public void setBase_subtotal(java.lang.String base_subtotal) {
        this.base_subtotal = base_subtotal;
    }


    /**
     * Gets the base_grand_total value for this SalesOrderListEntity.
     * 
     * @return base_grand_total
     */
    public java.lang.String getBase_grand_total() {
        return base_grand_total;
    }


    /**
     * Sets the base_grand_total value for this SalesOrderListEntity.
     * 
     * @param base_grand_total
     */
    public void setBase_grand_total(java.lang.String base_grand_total) {
        this.base_grand_total = base_grand_total;
    }


    /**
     * Gets the base_total_paid value for this SalesOrderListEntity.
     * 
     * @return base_total_paid
     */
    public java.lang.String getBase_total_paid() {
        return base_total_paid;
    }


    /**
     * Sets the base_total_paid value for this SalesOrderListEntity.
     * 
     * @param base_total_paid
     */
    public void setBase_total_paid(java.lang.String base_total_paid) {
        this.base_total_paid = base_total_paid;
    }


    /**
     * Gets the base_total_refunded value for this SalesOrderListEntity.
     * 
     * @return base_total_refunded
     */
    public java.lang.String getBase_total_refunded() {
        return base_total_refunded;
    }


    /**
     * Sets the base_total_refunded value for this SalesOrderListEntity.
     * 
     * @param base_total_refunded
     */
    public void setBase_total_refunded(java.lang.String base_total_refunded) {
        this.base_total_refunded = base_total_refunded;
    }


    /**
     * Gets the base_total_qty_ordered value for this SalesOrderListEntity.
     * 
     * @return base_total_qty_ordered
     */
    public java.lang.String getBase_total_qty_ordered() {
        return base_total_qty_ordered;
    }


    /**
     * Sets the base_total_qty_ordered value for this SalesOrderListEntity.
     * 
     * @param base_total_qty_ordered
     */
    public void setBase_total_qty_ordered(java.lang.String base_total_qty_ordered) {
        this.base_total_qty_ordered = base_total_qty_ordered;
    }


    /**
     * Gets the base_total_canceled value for this SalesOrderListEntity.
     * 
     * @return base_total_canceled
     */
    public java.lang.String getBase_total_canceled() {
        return base_total_canceled;
    }


    /**
     * Sets the base_total_canceled value for this SalesOrderListEntity.
     * 
     * @param base_total_canceled
     */
    public void setBase_total_canceled(java.lang.String base_total_canceled) {
        this.base_total_canceled = base_total_canceled;
    }


    /**
     * Gets the base_total_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_total_invoiced
     */
    public java.lang.String getBase_total_invoiced() {
        return base_total_invoiced;
    }


    /**
     * Sets the base_total_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_total_invoiced
     */
    public void setBase_total_invoiced(java.lang.String base_total_invoiced) {
        this.base_total_invoiced = base_total_invoiced;
    }


    /**
     * Gets the base_total_online_refunded value for this SalesOrderListEntity.
     * 
     * @return base_total_online_refunded
     */
    public java.lang.String getBase_total_online_refunded() {
        return base_total_online_refunded;
    }


    /**
     * Sets the base_total_online_refunded value for this SalesOrderListEntity.
     * 
     * @param base_total_online_refunded
     */
    public void setBase_total_online_refunded(java.lang.String base_total_online_refunded) {
        this.base_total_online_refunded = base_total_online_refunded;
    }


    /**
     * Gets the base_total_offline_refunded value for this SalesOrderListEntity.
     * 
     * @return base_total_offline_refunded
     */
    public java.lang.String getBase_total_offline_refunded() {
        return base_total_offline_refunded;
    }


    /**
     * Sets the base_total_offline_refunded value for this SalesOrderListEntity.
     * 
     * @param base_total_offline_refunded
     */
    public void setBase_total_offline_refunded(java.lang.String base_total_offline_refunded) {
        this.base_total_offline_refunded = base_total_offline_refunded;
    }


    /**
     * Gets the billing_address_id value for this SalesOrderListEntity.
     * 
     * @return billing_address_id
     */
    public java.lang.String getBilling_address_id() {
        return billing_address_id;
    }


    /**
     * Sets the billing_address_id value for this SalesOrderListEntity.
     * 
     * @param billing_address_id
     */
    public void setBilling_address_id(java.lang.String billing_address_id) {
        this.billing_address_id = billing_address_id;
    }


    /**
     * Gets the billing_firstname value for this SalesOrderListEntity.
     * 
     * @return billing_firstname
     */
    public java.lang.String getBilling_firstname() {
        return billing_firstname;
    }


    /**
     * Sets the billing_firstname value for this SalesOrderListEntity.
     * 
     * @param billing_firstname
     */
    public void setBilling_firstname(java.lang.String billing_firstname) {
        this.billing_firstname = billing_firstname;
    }


    /**
     * Gets the billing_lastname value for this SalesOrderListEntity.
     * 
     * @return billing_lastname
     */
    public java.lang.String getBilling_lastname() {
        return billing_lastname;
    }


    /**
     * Sets the billing_lastname value for this SalesOrderListEntity.
     * 
     * @param billing_lastname
     */
    public void setBilling_lastname(java.lang.String billing_lastname) {
        this.billing_lastname = billing_lastname;
    }


    /**
     * Gets the shipping_address_id value for this SalesOrderListEntity.
     * 
     * @return shipping_address_id
     */
    public java.lang.String getShipping_address_id() {
        return shipping_address_id;
    }


    /**
     * Sets the shipping_address_id value for this SalesOrderListEntity.
     * 
     * @param shipping_address_id
     */
    public void setShipping_address_id(java.lang.String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }


    /**
     * Gets the shipping_firstname value for this SalesOrderListEntity.
     * 
     * @return shipping_firstname
     */
    public java.lang.String getShipping_firstname() {
        return shipping_firstname;
    }


    /**
     * Sets the shipping_firstname value for this SalesOrderListEntity.
     * 
     * @param shipping_firstname
     */
    public void setShipping_firstname(java.lang.String shipping_firstname) {
        this.shipping_firstname = shipping_firstname;
    }


    /**
     * Gets the shipping_lastname value for this SalesOrderListEntity.
     * 
     * @return shipping_lastname
     */
    public java.lang.String getShipping_lastname() {
        return shipping_lastname;
    }


    /**
     * Sets the shipping_lastname value for this SalesOrderListEntity.
     * 
     * @param shipping_lastname
     */
    public void setShipping_lastname(java.lang.String shipping_lastname) {
        this.shipping_lastname = shipping_lastname;
    }


    /**
     * Gets the billing_name value for this SalesOrderListEntity.
     * 
     * @return billing_name
     */
    public java.lang.String getBilling_name() {
        return billing_name;
    }


    /**
     * Sets the billing_name value for this SalesOrderListEntity.
     * 
     * @param billing_name
     */
    public void setBilling_name(java.lang.String billing_name) {
        this.billing_name = billing_name;
    }


    /**
     * Gets the shipping_name value for this SalesOrderListEntity.
     * 
     * @return shipping_name
     */
    public java.lang.String getShipping_name() {
        return shipping_name;
    }


    /**
     * Sets the shipping_name value for this SalesOrderListEntity.
     * 
     * @param shipping_name
     */
    public void setShipping_name(java.lang.String shipping_name) {
        this.shipping_name = shipping_name;
    }


    /**
     * Gets the store_to_base_rate value for this SalesOrderListEntity.
     * 
     * @return store_to_base_rate
     */
    public java.lang.String getStore_to_base_rate() {
        return store_to_base_rate;
    }


    /**
     * Sets the store_to_base_rate value for this SalesOrderListEntity.
     * 
     * @param store_to_base_rate
     */
    public void setStore_to_base_rate(java.lang.String store_to_base_rate) {
        this.store_to_base_rate = store_to_base_rate;
    }


    /**
     * Gets the store_to_order_rate value for this SalesOrderListEntity.
     * 
     * @return store_to_order_rate
     */
    public java.lang.String getStore_to_order_rate() {
        return store_to_order_rate;
    }


    /**
     * Sets the store_to_order_rate value for this SalesOrderListEntity.
     * 
     * @param store_to_order_rate
     */
    public void setStore_to_order_rate(java.lang.String store_to_order_rate) {
        this.store_to_order_rate = store_to_order_rate;
    }


    /**
     * Gets the base_to_global_rate value for this SalesOrderListEntity.
     * 
     * @return base_to_global_rate
     */
    public java.lang.String getBase_to_global_rate() {
        return base_to_global_rate;
    }


    /**
     * Sets the base_to_global_rate value for this SalesOrderListEntity.
     * 
     * @param base_to_global_rate
     */
    public void setBase_to_global_rate(java.lang.String base_to_global_rate) {
        this.base_to_global_rate = base_to_global_rate;
    }


    /**
     * Gets the base_to_order_rate value for this SalesOrderListEntity.
     * 
     * @return base_to_order_rate
     */
    public java.lang.String getBase_to_order_rate() {
        return base_to_order_rate;
    }


    /**
     * Sets the base_to_order_rate value for this SalesOrderListEntity.
     * 
     * @param base_to_order_rate
     */
    public void setBase_to_order_rate(java.lang.String base_to_order_rate) {
        this.base_to_order_rate = base_to_order_rate;
    }


    /**
     * Gets the weight value for this SalesOrderListEntity.
     * 
     * @return weight
     */
    public java.lang.String getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this SalesOrderListEntity.
     * 
     * @param weight
     */
    public void setWeight(java.lang.String weight) {
        this.weight = weight;
    }


    /**
     * Gets the store_name value for this SalesOrderListEntity.
     * 
     * @return store_name
     */
    public java.lang.String getStore_name() {
        return store_name;
    }


    /**
     * Sets the store_name value for this SalesOrderListEntity.
     * 
     * @param store_name
     */
    public void setStore_name(java.lang.String store_name) {
        this.store_name = store_name;
    }


    /**
     * Gets the remote_ip value for this SalesOrderListEntity.
     * 
     * @return remote_ip
     */
    public java.lang.String getRemote_ip() {
        return remote_ip;
    }


    /**
     * Sets the remote_ip value for this SalesOrderListEntity.
     * 
     * @param remote_ip
     */
    public void setRemote_ip(java.lang.String remote_ip) {
        this.remote_ip = remote_ip;
    }


    /**
     * Gets the status value for this SalesOrderListEntity.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SalesOrderListEntity.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the state value for this SalesOrderListEntity.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this SalesOrderListEntity.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the applied_rule_ids value for this SalesOrderListEntity.
     * 
     * @return applied_rule_ids
     */
    public java.lang.String getApplied_rule_ids() {
        return applied_rule_ids;
    }


    /**
     * Sets the applied_rule_ids value for this SalesOrderListEntity.
     * 
     * @param applied_rule_ids
     */
    public void setApplied_rule_ids(java.lang.String applied_rule_ids) {
        this.applied_rule_ids = applied_rule_ids;
    }


    /**
     * Gets the global_currency_code value for this SalesOrderListEntity.
     * 
     * @return global_currency_code
     */
    public java.lang.String getGlobal_currency_code() {
        return global_currency_code;
    }


    /**
     * Sets the global_currency_code value for this SalesOrderListEntity.
     * 
     * @param global_currency_code
     */
    public void setGlobal_currency_code(java.lang.String global_currency_code) {
        this.global_currency_code = global_currency_code;
    }


    /**
     * Gets the base_currency_code value for this SalesOrderListEntity.
     * 
     * @return base_currency_code
     */
    public java.lang.String getBase_currency_code() {
        return base_currency_code;
    }


    /**
     * Sets the base_currency_code value for this SalesOrderListEntity.
     * 
     * @param base_currency_code
     */
    public void setBase_currency_code(java.lang.String base_currency_code) {
        this.base_currency_code = base_currency_code;
    }


    /**
     * Gets the store_currency_code value for this SalesOrderListEntity.
     * 
     * @return store_currency_code
     */
    public java.lang.String getStore_currency_code() {
        return store_currency_code;
    }


    /**
     * Sets the store_currency_code value for this SalesOrderListEntity.
     * 
     * @param store_currency_code
     */
    public void setStore_currency_code(java.lang.String store_currency_code) {
        this.store_currency_code = store_currency_code;
    }


    /**
     * Gets the order_currency_code value for this SalesOrderListEntity.
     * 
     * @return order_currency_code
     */
    public java.lang.String getOrder_currency_code() {
        return order_currency_code;
    }


    /**
     * Sets the order_currency_code value for this SalesOrderListEntity.
     * 
     * @param order_currency_code
     */
    public void setOrder_currency_code(java.lang.String order_currency_code) {
        this.order_currency_code = order_currency_code;
    }


    /**
     * Gets the shipping_method value for this SalesOrderListEntity.
     * 
     * @return shipping_method
     */
    public java.lang.String getShipping_method() {
        return shipping_method;
    }


    /**
     * Sets the shipping_method value for this SalesOrderListEntity.
     * 
     * @param shipping_method
     */
    public void setShipping_method(java.lang.String shipping_method) {
        this.shipping_method = shipping_method;
    }


    /**
     * Gets the shipping_description value for this SalesOrderListEntity.
     * 
     * @return shipping_description
     */
    public java.lang.String getShipping_description() {
        return shipping_description;
    }


    /**
     * Sets the shipping_description value for this SalesOrderListEntity.
     * 
     * @param shipping_description
     */
    public void setShipping_description(java.lang.String shipping_description) {
        this.shipping_description = shipping_description;
    }


    /**
     * Gets the customer_email value for this SalesOrderListEntity.
     * 
     * @return customer_email
     */
    public java.lang.String getCustomer_email() {
        return customer_email;
    }


    /**
     * Sets the customer_email value for this SalesOrderListEntity.
     * 
     * @param customer_email
     */
    public void setCustomer_email(java.lang.String customer_email) {
        this.customer_email = customer_email;
    }


    /**
     * Gets the customer_firstname value for this SalesOrderListEntity.
     * 
     * @return customer_firstname
     */
    public java.lang.String getCustomer_firstname() {
        return customer_firstname;
    }


    /**
     * Sets the customer_firstname value for this SalesOrderListEntity.
     * 
     * @param customer_firstname
     */
    public void setCustomer_firstname(java.lang.String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }


    /**
     * Gets the customer_lastname value for this SalesOrderListEntity.
     * 
     * @return customer_lastname
     */
    public java.lang.String getCustomer_lastname() {
        return customer_lastname;
    }


    /**
     * Sets the customer_lastname value for this SalesOrderListEntity.
     * 
     * @param customer_lastname
     */
    public void setCustomer_lastname(java.lang.String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }


    /**
     * Gets the quote_id value for this SalesOrderListEntity.
     * 
     * @return quote_id
     */
    public java.lang.String getQuote_id() {
        return quote_id;
    }


    /**
     * Sets the quote_id value for this SalesOrderListEntity.
     * 
     * @param quote_id
     */
    public void setQuote_id(java.lang.String quote_id) {
        this.quote_id = quote_id;
    }


    /**
     * Gets the is_virtual value for this SalesOrderListEntity.
     * 
     * @return is_virtual
     */
    public java.lang.String getIs_virtual() {
        return is_virtual;
    }


    /**
     * Sets the is_virtual value for this SalesOrderListEntity.
     * 
     * @param is_virtual
     */
    public void setIs_virtual(java.lang.String is_virtual) {
        this.is_virtual = is_virtual;
    }


    /**
     * Gets the customer_group_id value for this SalesOrderListEntity.
     * 
     * @return customer_group_id
     */
    public java.lang.String getCustomer_group_id() {
        return customer_group_id;
    }


    /**
     * Sets the customer_group_id value for this SalesOrderListEntity.
     * 
     * @param customer_group_id
     */
    public void setCustomer_group_id(java.lang.String customer_group_id) {
        this.customer_group_id = customer_group_id;
    }


    /**
     * Gets the customer_note_notify value for this SalesOrderListEntity.
     * 
     * @return customer_note_notify
     */
    public java.lang.String getCustomer_note_notify() {
        return customer_note_notify;
    }


    /**
     * Sets the customer_note_notify value for this SalesOrderListEntity.
     * 
     * @param customer_note_notify
     */
    public void setCustomer_note_notify(java.lang.String customer_note_notify) {
        this.customer_note_notify = customer_note_notify;
    }


    /**
     * Gets the customer_is_guest value for this SalesOrderListEntity.
     * 
     * @return customer_is_guest
     */
    public java.lang.String getCustomer_is_guest() {
        return customer_is_guest;
    }


    /**
     * Sets the customer_is_guest value for this SalesOrderListEntity.
     * 
     * @param customer_is_guest
     */
    public void setCustomer_is_guest(java.lang.String customer_is_guest) {
        this.customer_is_guest = customer_is_guest;
    }


    /**
     * Gets the email_sent value for this SalesOrderListEntity.
     * 
     * @return email_sent
     */
    public java.lang.String getEmail_sent() {
        return email_sent;
    }


    /**
     * Sets the email_sent value for this SalesOrderListEntity.
     * 
     * @param email_sent
     */
    public void setEmail_sent(java.lang.String email_sent) {
        this.email_sent = email_sent;
    }


    /**
     * Gets the order_id value for this SalesOrderListEntity.
     * 
     * @return order_id
     */
    public java.lang.String getOrder_id() {
        return order_id;
    }


    /**
     * Sets the order_id value for this SalesOrderListEntity.
     * 
     * @param order_id
     */
    public void setOrder_id(java.lang.String order_id) {
        this.order_id = order_id;
    }


    /**
     * Gets the gift_message_id value for this SalesOrderListEntity.
     * 
     * @return gift_message_id
     */
    public java.lang.String getGift_message_id() {
        return gift_message_id;
    }


    /**
     * Sets the gift_message_id value for this SalesOrderListEntity.
     * 
     * @param gift_message_id
     */
    public void setGift_message_id(java.lang.String gift_message_id) {
        this.gift_message_id = gift_message_id;
    }


    /**
     * Gets the coupon_code value for this SalesOrderListEntity.
     * 
     * @return coupon_code
     */
    public java.lang.String getCoupon_code() {
        return coupon_code;
    }


    /**
     * Sets the coupon_code value for this SalesOrderListEntity.
     * 
     * @param coupon_code
     */
    public void setCoupon_code(java.lang.String coupon_code) {
        this.coupon_code = coupon_code;
    }


    /**
     * Gets the protect_code value for this SalesOrderListEntity.
     * 
     * @return protect_code
     */
    public java.lang.String getProtect_code() {
        return protect_code;
    }


    /**
     * Sets the protect_code value for this SalesOrderListEntity.
     * 
     * @param protect_code
     */
    public void setProtect_code(java.lang.String protect_code) {
        this.protect_code = protect_code;
    }


    /**
     * Gets the base_discount_canceled value for this SalesOrderListEntity.
     * 
     * @return base_discount_canceled
     */
    public java.lang.String getBase_discount_canceled() {
        return base_discount_canceled;
    }


    /**
     * Sets the base_discount_canceled value for this SalesOrderListEntity.
     * 
     * @param base_discount_canceled
     */
    public void setBase_discount_canceled(java.lang.String base_discount_canceled) {
        this.base_discount_canceled = base_discount_canceled;
    }


    /**
     * Gets the base_discount_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_discount_invoiced
     */
    public java.lang.String getBase_discount_invoiced() {
        return base_discount_invoiced;
    }


    /**
     * Sets the base_discount_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_discount_invoiced
     */
    public void setBase_discount_invoiced(java.lang.String base_discount_invoiced) {
        this.base_discount_invoiced = base_discount_invoiced;
    }


    /**
     * Gets the base_discount_refunded value for this SalesOrderListEntity.
     * 
     * @return base_discount_refunded
     */
    public java.lang.String getBase_discount_refunded() {
        return base_discount_refunded;
    }


    /**
     * Sets the base_discount_refunded value for this SalesOrderListEntity.
     * 
     * @param base_discount_refunded
     */
    public void setBase_discount_refunded(java.lang.String base_discount_refunded) {
        this.base_discount_refunded = base_discount_refunded;
    }


    /**
     * Gets the base_shipping_canceled value for this SalesOrderListEntity.
     * 
     * @return base_shipping_canceled
     */
    public java.lang.String getBase_shipping_canceled() {
        return base_shipping_canceled;
    }


    /**
     * Sets the base_shipping_canceled value for this SalesOrderListEntity.
     * 
     * @param base_shipping_canceled
     */
    public void setBase_shipping_canceled(java.lang.String base_shipping_canceled) {
        this.base_shipping_canceled = base_shipping_canceled;
    }


    /**
     * Gets the base_shipping_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_shipping_invoiced
     */
    public java.lang.String getBase_shipping_invoiced() {
        return base_shipping_invoiced;
    }


    /**
     * Sets the base_shipping_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_shipping_invoiced
     */
    public void setBase_shipping_invoiced(java.lang.String base_shipping_invoiced) {
        this.base_shipping_invoiced = base_shipping_invoiced;
    }


    /**
     * Gets the base_shipping_refunded value for this SalesOrderListEntity.
     * 
     * @return base_shipping_refunded
     */
    public java.lang.String getBase_shipping_refunded() {
        return base_shipping_refunded;
    }


    /**
     * Sets the base_shipping_refunded value for this SalesOrderListEntity.
     * 
     * @param base_shipping_refunded
     */
    public void setBase_shipping_refunded(java.lang.String base_shipping_refunded) {
        this.base_shipping_refunded = base_shipping_refunded;
    }


    /**
     * Gets the base_shipping_tax_amount value for this SalesOrderListEntity.
     * 
     * @return base_shipping_tax_amount
     */
    public java.lang.String getBase_shipping_tax_amount() {
        return base_shipping_tax_amount;
    }


    /**
     * Sets the base_shipping_tax_amount value for this SalesOrderListEntity.
     * 
     * @param base_shipping_tax_amount
     */
    public void setBase_shipping_tax_amount(java.lang.String base_shipping_tax_amount) {
        this.base_shipping_tax_amount = base_shipping_tax_amount;
    }


    /**
     * Gets the base_shipping_tax_refunded value for this SalesOrderListEntity.
     * 
     * @return base_shipping_tax_refunded
     */
    public java.lang.String getBase_shipping_tax_refunded() {
        return base_shipping_tax_refunded;
    }


    /**
     * Sets the base_shipping_tax_refunded value for this SalesOrderListEntity.
     * 
     * @param base_shipping_tax_refunded
     */
    public void setBase_shipping_tax_refunded(java.lang.String base_shipping_tax_refunded) {
        this.base_shipping_tax_refunded = base_shipping_tax_refunded;
    }


    /**
     * Gets the base_subtotal_canceled value for this SalesOrderListEntity.
     * 
     * @return base_subtotal_canceled
     */
    public java.lang.String getBase_subtotal_canceled() {
        return base_subtotal_canceled;
    }


    /**
     * Sets the base_subtotal_canceled value for this SalesOrderListEntity.
     * 
     * @param base_subtotal_canceled
     */
    public void setBase_subtotal_canceled(java.lang.String base_subtotal_canceled) {
        this.base_subtotal_canceled = base_subtotal_canceled;
    }


    /**
     * Gets the base_subtotal_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_subtotal_invoiced
     */
    public java.lang.String getBase_subtotal_invoiced() {
        return base_subtotal_invoiced;
    }


    /**
     * Sets the base_subtotal_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_subtotal_invoiced
     */
    public void setBase_subtotal_invoiced(java.lang.String base_subtotal_invoiced) {
        this.base_subtotal_invoiced = base_subtotal_invoiced;
    }


    /**
     * Gets the base_subtotal_refunded value for this SalesOrderListEntity.
     * 
     * @return base_subtotal_refunded
     */
    public java.lang.String getBase_subtotal_refunded() {
        return base_subtotal_refunded;
    }


    /**
     * Sets the base_subtotal_refunded value for this SalesOrderListEntity.
     * 
     * @param base_subtotal_refunded
     */
    public void setBase_subtotal_refunded(java.lang.String base_subtotal_refunded) {
        this.base_subtotal_refunded = base_subtotal_refunded;
    }


    /**
     * Gets the base_tax_canceled value for this SalesOrderListEntity.
     * 
     * @return base_tax_canceled
     */
    public java.lang.String getBase_tax_canceled() {
        return base_tax_canceled;
    }


    /**
     * Sets the base_tax_canceled value for this SalesOrderListEntity.
     * 
     * @param base_tax_canceled
     */
    public void setBase_tax_canceled(java.lang.String base_tax_canceled) {
        this.base_tax_canceled = base_tax_canceled;
    }


    /**
     * Gets the base_tax_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_tax_invoiced
     */
    public java.lang.String getBase_tax_invoiced() {
        return base_tax_invoiced;
    }


    /**
     * Sets the base_tax_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_tax_invoiced
     */
    public void setBase_tax_invoiced(java.lang.String base_tax_invoiced) {
        this.base_tax_invoiced = base_tax_invoiced;
    }


    /**
     * Gets the base_tax_refunded value for this SalesOrderListEntity.
     * 
     * @return base_tax_refunded
     */
    public java.lang.String getBase_tax_refunded() {
        return base_tax_refunded;
    }


    /**
     * Sets the base_tax_refunded value for this SalesOrderListEntity.
     * 
     * @param base_tax_refunded
     */
    public void setBase_tax_refunded(java.lang.String base_tax_refunded) {
        this.base_tax_refunded = base_tax_refunded;
    }


    /**
     * Gets the base_total_invoiced_cost value for this SalesOrderListEntity.
     * 
     * @return base_total_invoiced_cost
     */
    public java.lang.String getBase_total_invoiced_cost() {
        return base_total_invoiced_cost;
    }


    /**
     * Sets the base_total_invoiced_cost value for this SalesOrderListEntity.
     * 
     * @param base_total_invoiced_cost
     */
    public void setBase_total_invoiced_cost(java.lang.String base_total_invoiced_cost) {
        this.base_total_invoiced_cost = base_total_invoiced_cost;
    }


    /**
     * Gets the discount_canceled value for this SalesOrderListEntity.
     * 
     * @return discount_canceled
     */
    public java.lang.String getDiscount_canceled() {
        return discount_canceled;
    }


    /**
     * Sets the discount_canceled value for this SalesOrderListEntity.
     * 
     * @param discount_canceled
     */
    public void setDiscount_canceled(java.lang.String discount_canceled) {
        this.discount_canceled = discount_canceled;
    }


    /**
     * Gets the discount_invoiced value for this SalesOrderListEntity.
     * 
     * @return discount_invoiced
     */
    public java.lang.String getDiscount_invoiced() {
        return discount_invoiced;
    }


    /**
     * Sets the discount_invoiced value for this SalesOrderListEntity.
     * 
     * @param discount_invoiced
     */
    public void setDiscount_invoiced(java.lang.String discount_invoiced) {
        this.discount_invoiced = discount_invoiced;
    }


    /**
     * Gets the discount_refunded value for this SalesOrderListEntity.
     * 
     * @return discount_refunded
     */
    public java.lang.String getDiscount_refunded() {
        return discount_refunded;
    }


    /**
     * Sets the discount_refunded value for this SalesOrderListEntity.
     * 
     * @param discount_refunded
     */
    public void setDiscount_refunded(java.lang.String discount_refunded) {
        this.discount_refunded = discount_refunded;
    }


    /**
     * Gets the shipping_canceled value for this SalesOrderListEntity.
     * 
     * @return shipping_canceled
     */
    public java.lang.String getShipping_canceled() {
        return shipping_canceled;
    }


    /**
     * Sets the shipping_canceled value for this SalesOrderListEntity.
     * 
     * @param shipping_canceled
     */
    public void setShipping_canceled(java.lang.String shipping_canceled) {
        this.shipping_canceled = shipping_canceled;
    }


    /**
     * Gets the shipping_invoiced value for this SalesOrderListEntity.
     * 
     * @return shipping_invoiced
     */
    public java.lang.String getShipping_invoiced() {
        return shipping_invoiced;
    }


    /**
     * Sets the shipping_invoiced value for this SalesOrderListEntity.
     * 
     * @param shipping_invoiced
     */
    public void setShipping_invoiced(java.lang.String shipping_invoiced) {
        this.shipping_invoiced = shipping_invoiced;
    }


    /**
     * Gets the shipping_refunded value for this SalesOrderListEntity.
     * 
     * @return shipping_refunded
     */
    public java.lang.String getShipping_refunded() {
        return shipping_refunded;
    }


    /**
     * Sets the shipping_refunded value for this SalesOrderListEntity.
     * 
     * @param shipping_refunded
     */
    public void setShipping_refunded(java.lang.String shipping_refunded) {
        this.shipping_refunded = shipping_refunded;
    }


    /**
     * Gets the shipping_tax_amount value for this SalesOrderListEntity.
     * 
     * @return shipping_tax_amount
     */
    public java.lang.String getShipping_tax_amount() {
        return shipping_tax_amount;
    }


    /**
     * Sets the shipping_tax_amount value for this SalesOrderListEntity.
     * 
     * @param shipping_tax_amount
     */
    public void setShipping_tax_amount(java.lang.String shipping_tax_amount) {
        this.shipping_tax_amount = shipping_tax_amount;
    }


    /**
     * Gets the shipping_tax_refunded value for this SalesOrderListEntity.
     * 
     * @return shipping_tax_refunded
     */
    public java.lang.String getShipping_tax_refunded() {
        return shipping_tax_refunded;
    }


    /**
     * Sets the shipping_tax_refunded value for this SalesOrderListEntity.
     * 
     * @param shipping_tax_refunded
     */
    public void setShipping_tax_refunded(java.lang.String shipping_tax_refunded) {
        this.shipping_tax_refunded = shipping_tax_refunded;
    }


    /**
     * Gets the subtotal_canceled value for this SalesOrderListEntity.
     * 
     * @return subtotal_canceled
     */
    public java.lang.String getSubtotal_canceled() {
        return subtotal_canceled;
    }


    /**
     * Sets the subtotal_canceled value for this SalesOrderListEntity.
     * 
     * @param subtotal_canceled
     */
    public void setSubtotal_canceled(java.lang.String subtotal_canceled) {
        this.subtotal_canceled = subtotal_canceled;
    }


    /**
     * Gets the subtotal_invoiced value for this SalesOrderListEntity.
     * 
     * @return subtotal_invoiced
     */
    public java.lang.String getSubtotal_invoiced() {
        return subtotal_invoiced;
    }


    /**
     * Sets the subtotal_invoiced value for this SalesOrderListEntity.
     * 
     * @param subtotal_invoiced
     */
    public void setSubtotal_invoiced(java.lang.String subtotal_invoiced) {
        this.subtotal_invoiced = subtotal_invoiced;
    }


    /**
     * Gets the subtotal_refunded value for this SalesOrderListEntity.
     * 
     * @return subtotal_refunded
     */
    public java.lang.String getSubtotal_refunded() {
        return subtotal_refunded;
    }


    /**
     * Sets the subtotal_refunded value for this SalesOrderListEntity.
     * 
     * @param subtotal_refunded
     */
    public void setSubtotal_refunded(java.lang.String subtotal_refunded) {
        this.subtotal_refunded = subtotal_refunded;
    }


    /**
     * Gets the tax_canceled value for this SalesOrderListEntity.
     * 
     * @return tax_canceled
     */
    public java.lang.String getTax_canceled() {
        return tax_canceled;
    }


    /**
     * Sets the tax_canceled value for this SalesOrderListEntity.
     * 
     * @param tax_canceled
     */
    public void setTax_canceled(java.lang.String tax_canceled) {
        this.tax_canceled = tax_canceled;
    }


    /**
     * Gets the tax_invoiced value for this SalesOrderListEntity.
     * 
     * @return tax_invoiced
     */
    public java.lang.String getTax_invoiced() {
        return tax_invoiced;
    }


    /**
     * Sets the tax_invoiced value for this SalesOrderListEntity.
     * 
     * @param tax_invoiced
     */
    public void setTax_invoiced(java.lang.String tax_invoiced) {
        this.tax_invoiced = tax_invoiced;
    }


    /**
     * Gets the tax_refunded value for this SalesOrderListEntity.
     * 
     * @return tax_refunded
     */
    public java.lang.String getTax_refunded() {
        return tax_refunded;
    }


    /**
     * Sets the tax_refunded value for this SalesOrderListEntity.
     * 
     * @param tax_refunded
     */
    public void setTax_refunded(java.lang.String tax_refunded) {
        this.tax_refunded = tax_refunded;
    }


    /**
     * Gets the can_ship_partially value for this SalesOrderListEntity.
     * 
     * @return can_ship_partially
     */
    public java.lang.String getCan_ship_partially() {
        return can_ship_partially;
    }


    /**
     * Sets the can_ship_partially value for this SalesOrderListEntity.
     * 
     * @param can_ship_partially
     */
    public void setCan_ship_partially(java.lang.String can_ship_partially) {
        this.can_ship_partially = can_ship_partially;
    }


    /**
     * Gets the can_ship_partially_item value for this SalesOrderListEntity.
     * 
     * @return can_ship_partially_item
     */
    public java.lang.String getCan_ship_partially_item() {
        return can_ship_partially_item;
    }


    /**
     * Sets the can_ship_partially_item value for this SalesOrderListEntity.
     * 
     * @param can_ship_partially_item
     */
    public void setCan_ship_partially_item(java.lang.String can_ship_partially_item) {
        this.can_ship_partially_item = can_ship_partially_item;
    }


    /**
     * Gets the edit_increment value for this SalesOrderListEntity.
     * 
     * @return edit_increment
     */
    public java.lang.String getEdit_increment() {
        return edit_increment;
    }


    /**
     * Sets the edit_increment value for this SalesOrderListEntity.
     * 
     * @param edit_increment
     */
    public void setEdit_increment(java.lang.String edit_increment) {
        this.edit_increment = edit_increment;
    }


    /**
     * Gets the forced_do_shipment_with_invoice value for this SalesOrderListEntity.
     * 
     * @return forced_do_shipment_with_invoice
     */
    public java.lang.String getForced_do_shipment_with_invoice() {
        return forced_do_shipment_with_invoice;
    }


    /**
     * Sets the forced_do_shipment_with_invoice value for this SalesOrderListEntity.
     * 
     * @param forced_do_shipment_with_invoice
     */
    public void setForced_do_shipment_with_invoice(java.lang.String forced_do_shipment_with_invoice) {
        this.forced_do_shipment_with_invoice = forced_do_shipment_with_invoice;
    }


    /**
     * Gets the payment_authorization_expiration value for this SalesOrderListEntity.
     * 
     * @return payment_authorization_expiration
     */
    public java.lang.String getPayment_authorization_expiration() {
        return payment_authorization_expiration;
    }


    /**
     * Sets the payment_authorization_expiration value for this SalesOrderListEntity.
     * 
     * @param payment_authorization_expiration
     */
    public void setPayment_authorization_expiration(java.lang.String payment_authorization_expiration) {
        this.payment_authorization_expiration = payment_authorization_expiration;
    }


    /**
     * Gets the paypal_ipn_customer_notified value for this SalesOrderListEntity.
     * 
     * @return paypal_ipn_customer_notified
     */
    public java.lang.String getPaypal_ipn_customer_notified() {
        return paypal_ipn_customer_notified;
    }


    /**
     * Sets the paypal_ipn_customer_notified value for this SalesOrderListEntity.
     * 
     * @param paypal_ipn_customer_notified
     */
    public void setPaypal_ipn_customer_notified(java.lang.String paypal_ipn_customer_notified) {
        this.paypal_ipn_customer_notified = paypal_ipn_customer_notified;
    }


    /**
     * Gets the quote_address_id value for this SalesOrderListEntity.
     * 
     * @return quote_address_id
     */
    public java.lang.String getQuote_address_id() {
        return quote_address_id;
    }


    /**
     * Sets the quote_address_id value for this SalesOrderListEntity.
     * 
     * @param quote_address_id
     */
    public void setQuote_address_id(java.lang.String quote_address_id) {
        this.quote_address_id = quote_address_id;
    }


    /**
     * Gets the adjustment_negative value for this SalesOrderListEntity.
     * 
     * @return adjustment_negative
     */
    public java.lang.String getAdjustment_negative() {
        return adjustment_negative;
    }


    /**
     * Sets the adjustment_negative value for this SalesOrderListEntity.
     * 
     * @param adjustment_negative
     */
    public void setAdjustment_negative(java.lang.String adjustment_negative) {
        this.adjustment_negative = adjustment_negative;
    }


    /**
     * Gets the adjustment_positive value for this SalesOrderListEntity.
     * 
     * @return adjustment_positive
     */
    public java.lang.String getAdjustment_positive() {
        return adjustment_positive;
    }


    /**
     * Sets the adjustment_positive value for this SalesOrderListEntity.
     * 
     * @param adjustment_positive
     */
    public void setAdjustment_positive(java.lang.String adjustment_positive) {
        this.adjustment_positive = adjustment_positive;
    }


    /**
     * Gets the base_adjustment_negative value for this SalesOrderListEntity.
     * 
     * @return base_adjustment_negative
     */
    public java.lang.String getBase_adjustment_negative() {
        return base_adjustment_negative;
    }


    /**
     * Sets the base_adjustment_negative value for this SalesOrderListEntity.
     * 
     * @param base_adjustment_negative
     */
    public void setBase_adjustment_negative(java.lang.String base_adjustment_negative) {
        this.base_adjustment_negative = base_adjustment_negative;
    }


    /**
     * Gets the base_adjustment_positive value for this SalesOrderListEntity.
     * 
     * @return base_adjustment_positive
     */
    public java.lang.String getBase_adjustment_positive() {
        return base_adjustment_positive;
    }


    /**
     * Sets the base_adjustment_positive value for this SalesOrderListEntity.
     * 
     * @param base_adjustment_positive
     */
    public void setBase_adjustment_positive(java.lang.String base_adjustment_positive) {
        this.base_adjustment_positive = base_adjustment_positive;
    }


    /**
     * Gets the base_shipping_discount_amount value for this SalesOrderListEntity.
     * 
     * @return base_shipping_discount_amount
     */
    public java.lang.String getBase_shipping_discount_amount() {
        return base_shipping_discount_amount;
    }


    /**
     * Sets the base_shipping_discount_amount value for this SalesOrderListEntity.
     * 
     * @param base_shipping_discount_amount
     */
    public void setBase_shipping_discount_amount(java.lang.String base_shipping_discount_amount) {
        this.base_shipping_discount_amount = base_shipping_discount_amount;
    }


    /**
     * Gets the base_subtotal_incl_tax value for this SalesOrderListEntity.
     * 
     * @return base_subtotal_incl_tax
     */
    public java.lang.String getBase_subtotal_incl_tax() {
        return base_subtotal_incl_tax;
    }


    /**
     * Sets the base_subtotal_incl_tax value for this SalesOrderListEntity.
     * 
     * @param base_subtotal_incl_tax
     */
    public void setBase_subtotal_incl_tax(java.lang.String base_subtotal_incl_tax) {
        this.base_subtotal_incl_tax = base_subtotal_incl_tax;
    }


    /**
     * Gets the base_total_due value for this SalesOrderListEntity.
     * 
     * @return base_total_due
     */
    public java.lang.String getBase_total_due() {
        return base_total_due;
    }


    /**
     * Sets the base_total_due value for this SalesOrderListEntity.
     * 
     * @param base_total_due
     */
    public void setBase_total_due(java.lang.String base_total_due) {
        this.base_total_due = base_total_due;
    }


    /**
     * Gets the payment_authorization_amount value for this SalesOrderListEntity.
     * 
     * @return payment_authorization_amount
     */
    public java.lang.String getPayment_authorization_amount() {
        return payment_authorization_amount;
    }


    /**
     * Sets the payment_authorization_amount value for this SalesOrderListEntity.
     * 
     * @param payment_authorization_amount
     */
    public void setPayment_authorization_amount(java.lang.String payment_authorization_amount) {
        this.payment_authorization_amount = payment_authorization_amount;
    }


    /**
     * Gets the shipping_discount_amount value for this SalesOrderListEntity.
     * 
     * @return shipping_discount_amount
     */
    public java.lang.String getShipping_discount_amount() {
        return shipping_discount_amount;
    }


    /**
     * Sets the shipping_discount_amount value for this SalesOrderListEntity.
     * 
     * @param shipping_discount_amount
     */
    public void setShipping_discount_amount(java.lang.String shipping_discount_amount) {
        this.shipping_discount_amount = shipping_discount_amount;
    }


    /**
     * Gets the subtotal_incl_tax value for this SalesOrderListEntity.
     * 
     * @return subtotal_incl_tax
     */
    public java.lang.String getSubtotal_incl_tax() {
        return subtotal_incl_tax;
    }


    /**
     * Sets the subtotal_incl_tax value for this SalesOrderListEntity.
     * 
     * @param subtotal_incl_tax
     */
    public void setSubtotal_incl_tax(java.lang.String subtotal_incl_tax) {
        this.subtotal_incl_tax = subtotal_incl_tax;
    }


    /**
     * Gets the total_due value for this SalesOrderListEntity.
     * 
     * @return total_due
     */
    public java.lang.String getTotal_due() {
        return total_due;
    }


    /**
     * Sets the total_due value for this SalesOrderListEntity.
     * 
     * @param total_due
     */
    public void setTotal_due(java.lang.String total_due) {
        this.total_due = total_due;
    }


    /**
     * Gets the customer_dob value for this SalesOrderListEntity.
     * 
     * @return customer_dob
     */
    public java.lang.String getCustomer_dob() {
        return customer_dob;
    }


    /**
     * Sets the customer_dob value for this SalesOrderListEntity.
     * 
     * @param customer_dob
     */
    public void setCustomer_dob(java.lang.String customer_dob) {
        this.customer_dob = customer_dob;
    }


    /**
     * Gets the customer_middlename value for this SalesOrderListEntity.
     * 
     * @return customer_middlename
     */
    public java.lang.String getCustomer_middlename() {
        return customer_middlename;
    }


    /**
     * Sets the customer_middlename value for this SalesOrderListEntity.
     * 
     * @param customer_middlename
     */
    public void setCustomer_middlename(java.lang.String customer_middlename) {
        this.customer_middlename = customer_middlename;
    }


    /**
     * Gets the customer_prefix value for this SalesOrderListEntity.
     * 
     * @return customer_prefix
     */
    public java.lang.String getCustomer_prefix() {
        return customer_prefix;
    }


    /**
     * Sets the customer_prefix value for this SalesOrderListEntity.
     * 
     * @param customer_prefix
     */
    public void setCustomer_prefix(java.lang.String customer_prefix) {
        this.customer_prefix = customer_prefix;
    }


    /**
     * Gets the customer_suffix value for this SalesOrderListEntity.
     * 
     * @return customer_suffix
     */
    public java.lang.String getCustomer_suffix() {
        return customer_suffix;
    }


    /**
     * Sets the customer_suffix value for this SalesOrderListEntity.
     * 
     * @param customer_suffix
     */
    public void setCustomer_suffix(java.lang.String customer_suffix) {
        this.customer_suffix = customer_suffix;
    }


    /**
     * Gets the customer_taxvat value for this SalesOrderListEntity.
     * 
     * @return customer_taxvat
     */
    public java.lang.String getCustomer_taxvat() {
        return customer_taxvat;
    }


    /**
     * Sets the customer_taxvat value for this SalesOrderListEntity.
     * 
     * @param customer_taxvat
     */
    public void setCustomer_taxvat(java.lang.String customer_taxvat) {
        this.customer_taxvat = customer_taxvat;
    }


    /**
     * Gets the discount_description value for this SalesOrderListEntity.
     * 
     * @return discount_description
     */
    public java.lang.String getDiscount_description() {
        return discount_description;
    }


    /**
     * Sets the discount_description value for this SalesOrderListEntity.
     * 
     * @param discount_description
     */
    public void setDiscount_description(java.lang.String discount_description) {
        this.discount_description = discount_description;
    }


    /**
     * Gets the ext_customer_id value for this SalesOrderListEntity.
     * 
     * @return ext_customer_id
     */
    public java.lang.String getExt_customer_id() {
        return ext_customer_id;
    }


    /**
     * Sets the ext_customer_id value for this SalesOrderListEntity.
     * 
     * @param ext_customer_id
     */
    public void setExt_customer_id(java.lang.String ext_customer_id) {
        this.ext_customer_id = ext_customer_id;
    }


    /**
     * Gets the ext_order_id value for this SalesOrderListEntity.
     * 
     * @return ext_order_id
     */
    public java.lang.String getExt_order_id() {
        return ext_order_id;
    }


    /**
     * Sets the ext_order_id value for this SalesOrderListEntity.
     * 
     * @param ext_order_id
     */
    public void setExt_order_id(java.lang.String ext_order_id) {
        this.ext_order_id = ext_order_id;
    }


    /**
     * Gets the hold_before_state value for this SalesOrderListEntity.
     * 
     * @return hold_before_state
     */
    public java.lang.String getHold_before_state() {
        return hold_before_state;
    }


    /**
     * Sets the hold_before_state value for this SalesOrderListEntity.
     * 
     * @param hold_before_state
     */
    public void setHold_before_state(java.lang.String hold_before_state) {
        this.hold_before_state = hold_before_state;
    }


    /**
     * Gets the hold_before_status value for this SalesOrderListEntity.
     * 
     * @return hold_before_status
     */
    public java.lang.String getHold_before_status() {
        return hold_before_status;
    }


    /**
     * Sets the hold_before_status value for this SalesOrderListEntity.
     * 
     * @param hold_before_status
     */
    public void setHold_before_status(java.lang.String hold_before_status) {
        this.hold_before_status = hold_before_status;
    }


    /**
     * Gets the original_increment_id value for this SalesOrderListEntity.
     * 
     * @return original_increment_id
     */
    public java.lang.String getOriginal_increment_id() {
        return original_increment_id;
    }


    /**
     * Sets the original_increment_id value for this SalesOrderListEntity.
     * 
     * @param original_increment_id
     */
    public void setOriginal_increment_id(java.lang.String original_increment_id) {
        this.original_increment_id = original_increment_id;
    }


    /**
     * Gets the relation_child_id value for this SalesOrderListEntity.
     * 
     * @return relation_child_id
     */
    public java.lang.String getRelation_child_id() {
        return relation_child_id;
    }


    /**
     * Sets the relation_child_id value for this SalesOrderListEntity.
     * 
     * @param relation_child_id
     */
    public void setRelation_child_id(java.lang.String relation_child_id) {
        this.relation_child_id = relation_child_id;
    }


    /**
     * Gets the relation_child_real_id value for this SalesOrderListEntity.
     * 
     * @return relation_child_real_id
     */
    public java.lang.String getRelation_child_real_id() {
        return relation_child_real_id;
    }


    /**
     * Sets the relation_child_real_id value for this SalesOrderListEntity.
     * 
     * @param relation_child_real_id
     */
    public void setRelation_child_real_id(java.lang.String relation_child_real_id) {
        this.relation_child_real_id = relation_child_real_id;
    }


    /**
     * Gets the relation_parent_id value for this SalesOrderListEntity.
     * 
     * @return relation_parent_id
     */
    public java.lang.String getRelation_parent_id() {
        return relation_parent_id;
    }


    /**
     * Sets the relation_parent_id value for this SalesOrderListEntity.
     * 
     * @param relation_parent_id
     */
    public void setRelation_parent_id(java.lang.String relation_parent_id) {
        this.relation_parent_id = relation_parent_id;
    }


    /**
     * Gets the relation_parent_real_id value for this SalesOrderListEntity.
     * 
     * @return relation_parent_real_id
     */
    public java.lang.String getRelation_parent_real_id() {
        return relation_parent_real_id;
    }


    /**
     * Sets the relation_parent_real_id value for this SalesOrderListEntity.
     * 
     * @param relation_parent_real_id
     */
    public void setRelation_parent_real_id(java.lang.String relation_parent_real_id) {
        this.relation_parent_real_id = relation_parent_real_id;
    }


    /**
     * Gets the x_forwarded_for value for this SalesOrderListEntity.
     * 
     * @return x_forwarded_for
     */
    public java.lang.String getX_forwarded_for() {
        return x_forwarded_for;
    }


    /**
     * Sets the x_forwarded_for value for this SalesOrderListEntity.
     * 
     * @param x_forwarded_for
     */
    public void setX_forwarded_for(java.lang.String x_forwarded_for) {
        this.x_forwarded_for = x_forwarded_for;
    }


    /**
     * Gets the customer_note value for this SalesOrderListEntity.
     * 
     * @return customer_note
     */
    public java.lang.String getCustomer_note() {
        return customer_note;
    }


    /**
     * Sets the customer_note value for this SalesOrderListEntity.
     * 
     * @param customer_note
     */
    public void setCustomer_note(java.lang.String customer_note) {
        this.customer_note = customer_note;
    }


    /**
     * Gets the total_item_count value for this SalesOrderListEntity.
     * 
     * @return total_item_count
     */
    public java.lang.String getTotal_item_count() {
        return total_item_count;
    }


    /**
     * Sets the total_item_count value for this SalesOrderListEntity.
     * 
     * @param total_item_count
     */
    public void setTotal_item_count(java.lang.String total_item_count) {
        this.total_item_count = total_item_count;
    }


    /**
     * Gets the customer_gender value for this SalesOrderListEntity.
     * 
     * @return customer_gender
     */
    public java.lang.String getCustomer_gender() {
        return customer_gender;
    }


    /**
     * Sets the customer_gender value for this SalesOrderListEntity.
     * 
     * @param customer_gender
     */
    public void setCustomer_gender(java.lang.String customer_gender) {
        this.customer_gender = customer_gender;
    }


    /**
     * Gets the hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @return hidden_tax_amount
     */
    public java.lang.String getHidden_tax_amount() {
        return hidden_tax_amount;
    }


    /**
     * Sets the hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @param hidden_tax_amount
     */
    public void setHidden_tax_amount(java.lang.String hidden_tax_amount) {
        this.hidden_tax_amount = hidden_tax_amount;
    }


    /**
     * Gets the base_hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @return base_hidden_tax_amount
     */
    public java.lang.String getBase_hidden_tax_amount() {
        return base_hidden_tax_amount;
    }


    /**
     * Sets the base_hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @param base_hidden_tax_amount
     */
    public void setBase_hidden_tax_amount(java.lang.String base_hidden_tax_amount) {
        this.base_hidden_tax_amount = base_hidden_tax_amount;
    }


    /**
     * Gets the shipping_hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @return shipping_hidden_tax_amount
     */
    public java.lang.String getShipping_hidden_tax_amount() {
        return shipping_hidden_tax_amount;
    }


    /**
     * Sets the shipping_hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @param shipping_hidden_tax_amount
     */
    public void setShipping_hidden_tax_amount(java.lang.String shipping_hidden_tax_amount) {
        this.shipping_hidden_tax_amount = shipping_hidden_tax_amount;
    }


    /**
     * Gets the base_shipping_hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @return base_shipping_hidden_tax_amount
     */
    public java.lang.String getBase_shipping_hidden_tax_amount() {
        return base_shipping_hidden_tax_amount;
    }


    /**
     * Sets the base_shipping_hidden_tax_amount value for this SalesOrderListEntity.
     * 
     * @param base_shipping_hidden_tax_amount
     */
    public void setBase_shipping_hidden_tax_amount(java.lang.String base_shipping_hidden_tax_amount) {
        this.base_shipping_hidden_tax_amount = base_shipping_hidden_tax_amount;
    }


    /**
     * Gets the hidden_tax_invoiced value for this SalesOrderListEntity.
     * 
     * @return hidden_tax_invoiced
     */
    public java.lang.String getHidden_tax_invoiced() {
        return hidden_tax_invoiced;
    }


    /**
     * Sets the hidden_tax_invoiced value for this SalesOrderListEntity.
     * 
     * @param hidden_tax_invoiced
     */
    public void setHidden_tax_invoiced(java.lang.String hidden_tax_invoiced) {
        this.hidden_tax_invoiced = hidden_tax_invoiced;
    }


    /**
     * Gets the base_hidden_tax_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_hidden_tax_invoiced
     */
    public java.lang.String getBase_hidden_tax_invoiced() {
        return base_hidden_tax_invoiced;
    }


    /**
     * Sets the base_hidden_tax_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_hidden_tax_invoiced
     */
    public void setBase_hidden_tax_invoiced(java.lang.String base_hidden_tax_invoiced) {
        this.base_hidden_tax_invoiced = base_hidden_tax_invoiced;
    }


    /**
     * Gets the hidden_tax_refunded value for this SalesOrderListEntity.
     * 
     * @return hidden_tax_refunded
     */
    public java.lang.String getHidden_tax_refunded() {
        return hidden_tax_refunded;
    }


    /**
     * Sets the hidden_tax_refunded value for this SalesOrderListEntity.
     * 
     * @param hidden_tax_refunded
     */
    public void setHidden_tax_refunded(java.lang.String hidden_tax_refunded) {
        this.hidden_tax_refunded = hidden_tax_refunded;
    }


    /**
     * Gets the base_hidden_tax_refunded value for this SalesOrderListEntity.
     * 
     * @return base_hidden_tax_refunded
     */
    public java.lang.String getBase_hidden_tax_refunded() {
        return base_hidden_tax_refunded;
    }


    /**
     * Sets the base_hidden_tax_refunded value for this SalesOrderListEntity.
     * 
     * @param base_hidden_tax_refunded
     */
    public void setBase_hidden_tax_refunded(java.lang.String base_hidden_tax_refunded) {
        this.base_hidden_tax_refunded = base_hidden_tax_refunded;
    }


    /**
     * Gets the shipping_incl_tax value for this SalesOrderListEntity.
     * 
     * @return shipping_incl_tax
     */
    public java.lang.String getShipping_incl_tax() {
        return shipping_incl_tax;
    }


    /**
     * Sets the shipping_incl_tax value for this SalesOrderListEntity.
     * 
     * @param shipping_incl_tax
     */
    public void setShipping_incl_tax(java.lang.String shipping_incl_tax) {
        this.shipping_incl_tax = shipping_incl_tax;
    }


    /**
     * Gets the base_shipping_incl_tax value for this SalesOrderListEntity.
     * 
     * @return base_shipping_incl_tax
     */
    public java.lang.String getBase_shipping_incl_tax() {
        return base_shipping_incl_tax;
    }


    /**
     * Sets the base_shipping_incl_tax value for this SalesOrderListEntity.
     * 
     * @param base_shipping_incl_tax
     */
    public void setBase_shipping_incl_tax(java.lang.String base_shipping_incl_tax) {
        this.base_shipping_incl_tax = base_shipping_incl_tax;
    }


    /**
     * Gets the base_customer_balance_amount value for this SalesOrderListEntity.
     * 
     * @return base_customer_balance_amount
     */
    public java.lang.String getBase_customer_balance_amount() {
        return base_customer_balance_amount;
    }


    /**
     * Sets the base_customer_balance_amount value for this SalesOrderListEntity.
     * 
     * @param base_customer_balance_amount
     */
    public void setBase_customer_balance_amount(java.lang.String base_customer_balance_amount) {
        this.base_customer_balance_amount = base_customer_balance_amount;
    }


    /**
     * Gets the customer_balance_amount value for this SalesOrderListEntity.
     * 
     * @return customer_balance_amount
     */
    public java.lang.String getCustomer_balance_amount() {
        return customer_balance_amount;
    }


    /**
     * Sets the customer_balance_amount value for this SalesOrderListEntity.
     * 
     * @param customer_balance_amount
     */
    public void setCustomer_balance_amount(java.lang.String customer_balance_amount) {
        this.customer_balance_amount = customer_balance_amount;
    }


    /**
     * Gets the base_customer_balance_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_customer_balance_invoiced
     */
    public java.lang.String getBase_customer_balance_invoiced() {
        return base_customer_balance_invoiced;
    }


    /**
     * Sets the base_customer_balance_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_customer_balance_invoiced
     */
    public void setBase_customer_balance_invoiced(java.lang.String base_customer_balance_invoiced) {
        this.base_customer_balance_invoiced = base_customer_balance_invoiced;
    }


    /**
     * Gets the customer_balance_invoiced value for this SalesOrderListEntity.
     * 
     * @return customer_balance_invoiced
     */
    public java.lang.String getCustomer_balance_invoiced() {
        return customer_balance_invoiced;
    }


    /**
     * Sets the customer_balance_invoiced value for this SalesOrderListEntity.
     * 
     * @param customer_balance_invoiced
     */
    public void setCustomer_balance_invoiced(java.lang.String customer_balance_invoiced) {
        this.customer_balance_invoiced = customer_balance_invoiced;
    }


    /**
     * Gets the base_customer_balance_refunded value for this SalesOrderListEntity.
     * 
     * @return base_customer_balance_refunded
     */
    public java.lang.String getBase_customer_balance_refunded() {
        return base_customer_balance_refunded;
    }


    /**
     * Sets the base_customer_balance_refunded value for this SalesOrderListEntity.
     * 
     * @param base_customer_balance_refunded
     */
    public void setBase_customer_balance_refunded(java.lang.String base_customer_balance_refunded) {
        this.base_customer_balance_refunded = base_customer_balance_refunded;
    }


    /**
     * Gets the customer_balance_refunded value for this SalesOrderListEntity.
     * 
     * @return customer_balance_refunded
     */
    public java.lang.String getCustomer_balance_refunded() {
        return customer_balance_refunded;
    }


    /**
     * Sets the customer_balance_refunded value for this SalesOrderListEntity.
     * 
     * @param customer_balance_refunded
     */
    public void setCustomer_balance_refunded(java.lang.String customer_balance_refunded) {
        this.customer_balance_refunded = customer_balance_refunded;
    }


    /**
     * Gets the base_customer_balance_total_refunded value for this SalesOrderListEntity.
     * 
     * @return base_customer_balance_total_refunded
     */
    public java.lang.String getBase_customer_balance_total_refunded() {
        return base_customer_balance_total_refunded;
    }


    /**
     * Sets the base_customer_balance_total_refunded value for this SalesOrderListEntity.
     * 
     * @param base_customer_balance_total_refunded
     */
    public void setBase_customer_balance_total_refunded(java.lang.String base_customer_balance_total_refunded) {
        this.base_customer_balance_total_refunded = base_customer_balance_total_refunded;
    }


    /**
     * Gets the customer_balance_total_refunded value for this SalesOrderListEntity.
     * 
     * @return customer_balance_total_refunded
     */
    public java.lang.String getCustomer_balance_total_refunded() {
        return customer_balance_total_refunded;
    }


    /**
     * Sets the customer_balance_total_refunded value for this SalesOrderListEntity.
     * 
     * @param customer_balance_total_refunded
     */
    public void setCustomer_balance_total_refunded(java.lang.String customer_balance_total_refunded) {
        this.customer_balance_total_refunded = customer_balance_total_refunded;
    }


    /**
     * Gets the gift_cards value for this SalesOrderListEntity.
     * 
     * @return gift_cards
     */
    public java.lang.String getGift_cards() {
        return gift_cards;
    }


    /**
     * Sets the gift_cards value for this SalesOrderListEntity.
     * 
     * @param gift_cards
     */
    public void setGift_cards(java.lang.String gift_cards) {
        this.gift_cards = gift_cards;
    }


    /**
     * Gets the base_gift_cards_amount value for this SalesOrderListEntity.
     * 
     * @return base_gift_cards_amount
     */
    public java.lang.String getBase_gift_cards_amount() {
        return base_gift_cards_amount;
    }


    /**
     * Sets the base_gift_cards_amount value for this SalesOrderListEntity.
     * 
     * @param base_gift_cards_amount
     */
    public void setBase_gift_cards_amount(java.lang.String base_gift_cards_amount) {
        this.base_gift_cards_amount = base_gift_cards_amount;
    }


    /**
     * Gets the gift_cards_amount value for this SalesOrderListEntity.
     * 
     * @return gift_cards_amount
     */
    public java.lang.String getGift_cards_amount() {
        return gift_cards_amount;
    }


    /**
     * Sets the gift_cards_amount value for this SalesOrderListEntity.
     * 
     * @param gift_cards_amount
     */
    public void setGift_cards_amount(java.lang.String gift_cards_amount) {
        this.gift_cards_amount = gift_cards_amount;
    }


    /**
     * Gets the base_gift_cards_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_gift_cards_invoiced
     */
    public java.lang.String getBase_gift_cards_invoiced() {
        return base_gift_cards_invoiced;
    }


    /**
     * Sets the base_gift_cards_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_gift_cards_invoiced
     */
    public void setBase_gift_cards_invoiced(java.lang.String base_gift_cards_invoiced) {
        this.base_gift_cards_invoiced = base_gift_cards_invoiced;
    }


    /**
     * Gets the gift_cards_invoiced value for this SalesOrderListEntity.
     * 
     * @return gift_cards_invoiced
     */
    public java.lang.String getGift_cards_invoiced() {
        return gift_cards_invoiced;
    }


    /**
     * Sets the gift_cards_invoiced value for this SalesOrderListEntity.
     * 
     * @param gift_cards_invoiced
     */
    public void setGift_cards_invoiced(java.lang.String gift_cards_invoiced) {
        this.gift_cards_invoiced = gift_cards_invoiced;
    }


    /**
     * Gets the base_gift_cards_refunded value for this SalesOrderListEntity.
     * 
     * @return base_gift_cards_refunded
     */
    public java.lang.String getBase_gift_cards_refunded() {
        return base_gift_cards_refunded;
    }


    /**
     * Sets the base_gift_cards_refunded value for this SalesOrderListEntity.
     * 
     * @param base_gift_cards_refunded
     */
    public void setBase_gift_cards_refunded(java.lang.String base_gift_cards_refunded) {
        this.base_gift_cards_refunded = base_gift_cards_refunded;
    }


    /**
     * Gets the gift_cards_refunded value for this SalesOrderListEntity.
     * 
     * @return gift_cards_refunded
     */
    public java.lang.String getGift_cards_refunded() {
        return gift_cards_refunded;
    }


    /**
     * Sets the gift_cards_refunded value for this SalesOrderListEntity.
     * 
     * @param gift_cards_refunded
     */
    public void setGift_cards_refunded(java.lang.String gift_cards_refunded) {
        this.gift_cards_refunded = gift_cards_refunded;
    }


    /**
     * Gets the reward_points_balance value for this SalesOrderListEntity.
     * 
     * @return reward_points_balance
     */
    public java.lang.String getReward_points_balance() {
        return reward_points_balance;
    }


    /**
     * Sets the reward_points_balance value for this SalesOrderListEntity.
     * 
     * @param reward_points_balance
     */
    public void setReward_points_balance(java.lang.String reward_points_balance) {
        this.reward_points_balance = reward_points_balance;
    }


    /**
     * Gets the base_reward_currency_amount value for this SalesOrderListEntity.
     * 
     * @return base_reward_currency_amount
     */
    public java.lang.String getBase_reward_currency_amount() {
        return base_reward_currency_amount;
    }


    /**
     * Sets the base_reward_currency_amount value for this SalesOrderListEntity.
     * 
     * @param base_reward_currency_amount
     */
    public void setBase_reward_currency_amount(java.lang.String base_reward_currency_amount) {
        this.base_reward_currency_amount = base_reward_currency_amount;
    }


    /**
     * Gets the reward_currency_amount value for this SalesOrderListEntity.
     * 
     * @return reward_currency_amount
     */
    public java.lang.String getReward_currency_amount() {
        return reward_currency_amount;
    }


    /**
     * Sets the reward_currency_amount value for this SalesOrderListEntity.
     * 
     * @param reward_currency_amount
     */
    public void setReward_currency_amount(java.lang.String reward_currency_amount) {
        this.reward_currency_amount = reward_currency_amount;
    }


    /**
     * Gets the base_reward_currency_amount_invoiced value for this SalesOrderListEntity.
     * 
     * @return base_reward_currency_amount_invoiced
     */
    public java.lang.String getBase_reward_currency_amount_invoiced() {
        return base_reward_currency_amount_invoiced;
    }


    /**
     * Sets the base_reward_currency_amount_invoiced value for this SalesOrderListEntity.
     * 
     * @param base_reward_currency_amount_invoiced
     */
    public void setBase_reward_currency_amount_invoiced(java.lang.String base_reward_currency_amount_invoiced) {
        this.base_reward_currency_amount_invoiced = base_reward_currency_amount_invoiced;
    }


    /**
     * Gets the reward_currency_amount_invoiced value for this SalesOrderListEntity.
     * 
     * @return reward_currency_amount_invoiced
     */
    public java.lang.String getReward_currency_amount_invoiced() {
        return reward_currency_amount_invoiced;
    }


    /**
     * Sets the reward_currency_amount_invoiced value for this SalesOrderListEntity.
     * 
     * @param reward_currency_amount_invoiced
     */
    public void setReward_currency_amount_invoiced(java.lang.String reward_currency_amount_invoiced) {
        this.reward_currency_amount_invoiced = reward_currency_amount_invoiced;
    }


    /**
     * Gets the base_reward_currency_amount_refunded value for this SalesOrderListEntity.
     * 
     * @return base_reward_currency_amount_refunded
     */
    public java.lang.String getBase_reward_currency_amount_refunded() {
        return base_reward_currency_amount_refunded;
    }


    /**
     * Sets the base_reward_currency_amount_refunded value for this SalesOrderListEntity.
     * 
     * @param base_reward_currency_amount_refunded
     */
    public void setBase_reward_currency_amount_refunded(java.lang.String base_reward_currency_amount_refunded) {
        this.base_reward_currency_amount_refunded = base_reward_currency_amount_refunded;
    }


    /**
     * Gets the reward_currency_amount_refunded value for this SalesOrderListEntity.
     * 
     * @return reward_currency_amount_refunded
     */
    public java.lang.String getReward_currency_amount_refunded() {
        return reward_currency_amount_refunded;
    }


    /**
     * Sets the reward_currency_amount_refunded value for this SalesOrderListEntity.
     * 
     * @param reward_currency_amount_refunded
     */
    public void setReward_currency_amount_refunded(java.lang.String reward_currency_amount_refunded) {
        this.reward_currency_amount_refunded = reward_currency_amount_refunded;
    }


    /**
     * Gets the reward_points_balance_refunded value for this SalesOrderListEntity.
     * 
     * @return reward_points_balance_refunded
     */
    public java.lang.String getReward_points_balance_refunded() {
        return reward_points_balance_refunded;
    }


    /**
     * Sets the reward_points_balance_refunded value for this SalesOrderListEntity.
     * 
     * @param reward_points_balance_refunded
     */
    public void setReward_points_balance_refunded(java.lang.String reward_points_balance_refunded) {
        this.reward_points_balance_refunded = reward_points_balance_refunded;
    }


    /**
     * Gets the reward_points_balance_to_refund value for this SalesOrderListEntity.
     * 
     * @return reward_points_balance_to_refund
     */
    public java.lang.String getReward_points_balance_to_refund() {
        return reward_points_balance_to_refund;
    }


    /**
     * Sets the reward_points_balance_to_refund value for this SalesOrderListEntity.
     * 
     * @param reward_points_balance_to_refund
     */
    public void setReward_points_balance_to_refund(java.lang.String reward_points_balance_to_refund) {
        this.reward_points_balance_to_refund = reward_points_balance_to_refund;
    }


    /**
     * Gets the reward_salesrule_points value for this SalesOrderListEntity.
     * 
     * @return reward_salesrule_points
     */
    public java.lang.String getReward_salesrule_points() {
        return reward_salesrule_points;
    }


    /**
     * Sets the reward_salesrule_points value for this SalesOrderListEntity.
     * 
     * @param reward_salesrule_points
     */
    public void setReward_salesrule_points(java.lang.String reward_salesrule_points) {
        this.reward_salesrule_points = reward_salesrule_points;
    }


    /**
     * Gets the firstname value for this SalesOrderListEntity.
     * 
     * @return firstname
     */
    public java.lang.String getFirstname() {
        return firstname;
    }


    /**
     * Sets the firstname value for this SalesOrderListEntity.
     * 
     * @param firstname
     */
    public void setFirstname(java.lang.String firstname) {
        this.firstname = firstname;
    }


    /**
     * Gets the lastname value for this SalesOrderListEntity.
     * 
     * @return lastname
     */
    public java.lang.String getLastname() {
        return lastname;
    }


    /**
     * Sets the lastname value for this SalesOrderListEntity.
     * 
     * @param lastname
     */
    public void setLastname(java.lang.String lastname) {
        this.lastname = lastname;
    }


    /**
     * Gets the telephone value for this SalesOrderListEntity.
     * 
     * @return telephone
     */
    public java.lang.String getTelephone() {
        return telephone;
    }


    /**
     * Sets the telephone value for this SalesOrderListEntity.
     * 
     * @param telephone
     */
    public void setTelephone(java.lang.String telephone) {
        this.telephone = telephone;
    }


    /**
     * Gets the postcode value for this SalesOrderListEntity.
     * 
     * @return postcode
     */
    public java.lang.String getPostcode() {
        return postcode;
    }


    /**
     * Sets the postcode value for this SalesOrderListEntity.
     * 
     * @param postcode
     */
    public void setPostcode(java.lang.String postcode) {
        this.postcode = postcode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderListEntity)) return false;
        SalesOrderListEntity other = (SalesOrderListEntity) obj;
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
            ((this.store_id==null && other.getStore_id()==null) || 
             (this.store_id!=null &&
              this.store_id.equals(other.getStore_id()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
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
            ((this.coupon_code==null && other.getCoupon_code()==null) || 
             (this.coupon_code!=null &&
              this.coupon_code.equals(other.getCoupon_code()))) &&
            ((this.protect_code==null && other.getProtect_code()==null) || 
             (this.protect_code!=null &&
              this.protect_code.equals(other.getProtect_code()))) &&
            ((this.base_discount_canceled==null && other.getBase_discount_canceled()==null) || 
             (this.base_discount_canceled!=null &&
              this.base_discount_canceled.equals(other.getBase_discount_canceled()))) &&
            ((this.base_discount_invoiced==null && other.getBase_discount_invoiced()==null) || 
             (this.base_discount_invoiced!=null &&
              this.base_discount_invoiced.equals(other.getBase_discount_invoiced()))) &&
            ((this.base_discount_refunded==null && other.getBase_discount_refunded()==null) || 
             (this.base_discount_refunded!=null &&
              this.base_discount_refunded.equals(other.getBase_discount_refunded()))) &&
            ((this.base_shipping_canceled==null && other.getBase_shipping_canceled()==null) || 
             (this.base_shipping_canceled!=null &&
              this.base_shipping_canceled.equals(other.getBase_shipping_canceled()))) &&
            ((this.base_shipping_invoiced==null && other.getBase_shipping_invoiced()==null) || 
             (this.base_shipping_invoiced!=null &&
              this.base_shipping_invoiced.equals(other.getBase_shipping_invoiced()))) &&
            ((this.base_shipping_refunded==null && other.getBase_shipping_refunded()==null) || 
             (this.base_shipping_refunded!=null &&
              this.base_shipping_refunded.equals(other.getBase_shipping_refunded()))) &&
            ((this.base_shipping_tax_amount==null && other.getBase_shipping_tax_amount()==null) || 
             (this.base_shipping_tax_amount!=null &&
              this.base_shipping_tax_amount.equals(other.getBase_shipping_tax_amount()))) &&
            ((this.base_shipping_tax_refunded==null && other.getBase_shipping_tax_refunded()==null) || 
             (this.base_shipping_tax_refunded!=null &&
              this.base_shipping_tax_refunded.equals(other.getBase_shipping_tax_refunded()))) &&
            ((this.base_subtotal_canceled==null && other.getBase_subtotal_canceled()==null) || 
             (this.base_subtotal_canceled!=null &&
              this.base_subtotal_canceled.equals(other.getBase_subtotal_canceled()))) &&
            ((this.base_subtotal_invoiced==null && other.getBase_subtotal_invoiced()==null) || 
             (this.base_subtotal_invoiced!=null &&
              this.base_subtotal_invoiced.equals(other.getBase_subtotal_invoiced()))) &&
            ((this.base_subtotal_refunded==null && other.getBase_subtotal_refunded()==null) || 
             (this.base_subtotal_refunded!=null &&
              this.base_subtotal_refunded.equals(other.getBase_subtotal_refunded()))) &&
            ((this.base_tax_canceled==null && other.getBase_tax_canceled()==null) || 
             (this.base_tax_canceled!=null &&
              this.base_tax_canceled.equals(other.getBase_tax_canceled()))) &&
            ((this.base_tax_invoiced==null && other.getBase_tax_invoiced()==null) || 
             (this.base_tax_invoiced!=null &&
              this.base_tax_invoiced.equals(other.getBase_tax_invoiced()))) &&
            ((this.base_tax_refunded==null && other.getBase_tax_refunded()==null) || 
             (this.base_tax_refunded!=null &&
              this.base_tax_refunded.equals(other.getBase_tax_refunded()))) &&
            ((this.base_total_invoiced_cost==null && other.getBase_total_invoiced_cost()==null) || 
             (this.base_total_invoiced_cost!=null &&
              this.base_total_invoiced_cost.equals(other.getBase_total_invoiced_cost()))) &&
            ((this.discount_canceled==null && other.getDiscount_canceled()==null) || 
             (this.discount_canceled!=null &&
              this.discount_canceled.equals(other.getDiscount_canceled()))) &&
            ((this.discount_invoiced==null && other.getDiscount_invoiced()==null) || 
             (this.discount_invoiced!=null &&
              this.discount_invoiced.equals(other.getDiscount_invoiced()))) &&
            ((this.discount_refunded==null && other.getDiscount_refunded()==null) || 
             (this.discount_refunded!=null &&
              this.discount_refunded.equals(other.getDiscount_refunded()))) &&
            ((this.shipping_canceled==null && other.getShipping_canceled()==null) || 
             (this.shipping_canceled!=null &&
              this.shipping_canceled.equals(other.getShipping_canceled()))) &&
            ((this.shipping_invoiced==null && other.getShipping_invoiced()==null) || 
             (this.shipping_invoiced!=null &&
              this.shipping_invoiced.equals(other.getShipping_invoiced()))) &&
            ((this.shipping_refunded==null && other.getShipping_refunded()==null) || 
             (this.shipping_refunded!=null &&
              this.shipping_refunded.equals(other.getShipping_refunded()))) &&
            ((this.shipping_tax_amount==null && other.getShipping_tax_amount()==null) || 
             (this.shipping_tax_amount!=null &&
              this.shipping_tax_amount.equals(other.getShipping_tax_amount()))) &&
            ((this.shipping_tax_refunded==null && other.getShipping_tax_refunded()==null) || 
             (this.shipping_tax_refunded!=null &&
              this.shipping_tax_refunded.equals(other.getShipping_tax_refunded()))) &&
            ((this.subtotal_canceled==null && other.getSubtotal_canceled()==null) || 
             (this.subtotal_canceled!=null &&
              this.subtotal_canceled.equals(other.getSubtotal_canceled()))) &&
            ((this.subtotal_invoiced==null && other.getSubtotal_invoiced()==null) || 
             (this.subtotal_invoiced!=null &&
              this.subtotal_invoiced.equals(other.getSubtotal_invoiced()))) &&
            ((this.subtotal_refunded==null && other.getSubtotal_refunded()==null) || 
             (this.subtotal_refunded!=null &&
              this.subtotal_refunded.equals(other.getSubtotal_refunded()))) &&
            ((this.tax_canceled==null && other.getTax_canceled()==null) || 
             (this.tax_canceled!=null &&
              this.tax_canceled.equals(other.getTax_canceled()))) &&
            ((this.tax_invoiced==null && other.getTax_invoiced()==null) || 
             (this.tax_invoiced!=null &&
              this.tax_invoiced.equals(other.getTax_invoiced()))) &&
            ((this.tax_refunded==null && other.getTax_refunded()==null) || 
             (this.tax_refunded!=null &&
              this.tax_refunded.equals(other.getTax_refunded()))) &&
            ((this.can_ship_partially==null && other.getCan_ship_partially()==null) || 
             (this.can_ship_partially!=null &&
              this.can_ship_partially.equals(other.getCan_ship_partially()))) &&
            ((this.can_ship_partially_item==null && other.getCan_ship_partially_item()==null) || 
             (this.can_ship_partially_item!=null &&
              this.can_ship_partially_item.equals(other.getCan_ship_partially_item()))) &&
            ((this.edit_increment==null && other.getEdit_increment()==null) || 
             (this.edit_increment!=null &&
              this.edit_increment.equals(other.getEdit_increment()))) &&
            ((this.forced_do_shipment_with_invoice==null && other.getForced_do_shipment_with_invoice()==null) || 
             (this.forced_do_shipment_with_invoice!=null &&
              this.forced_do_shipment_with_invoice.equals(other.getForced_do_shipment_with_invoice()))) &&
            ((this.payment_authorization_expiration==null && other.getPayment_authorization_expiration()==null) || 
             (this.payment_authorization_expiration!=null &&
              this.payment_authorization_expiration.equals(other.getPayment_authorization_expiration()))) &&
            ((this.paypal_ipn_customer_notified==null && other.getPaypal_ipn_customer_notified()==null) || 
             (this.paypal_ipn_customer_notified!=null &&
              this.paypal_ipn_customer_notified.equals(other.getPaypal_ipn_customer_notified()))) &&
            ((this.quote_address_id==null && other.getQuote_address_id()==null) || 
             (this.quote_address_id!=null &&
              this.quote_address_id.equals(other.getQuote_address_id()))) &&
            ((this.adjustment_negative==null && other.getAdjustment_negative()==null) || 
             (this.adjustment_negative!=null &&
              this.adjustment_negative.equals(other.getAdjustment_negative()))) &&
            ((this.adjustment_positive==null && other.getAdjustment_positive()==null) || 
             (this.adjustment_positive!=null &&
              this.adjustment_positive.equals(other.getAdjustment_positive()))) &&
            ((this.base_adjustment_negative==null && other.getBase_adjustment_negative()==null) || 
             (this.base_adjustment_negative!=null &&
              this.base_adjustment_negative.equals(other.getBase_adjustment_negative()))) &&
            ((this.base_adjustment_positive==null && other.getBase_adjustment_positive()==null) || 
             (this.base_adjustment_positive!=null &&
              this.base_adjustment_positive.equals(other.getBase_adjustment_positive()))) &&
            ((this.base_shipping_discount_amount==null && other.getBase_shipping_discount_amount()==null) || 
             (this.base_shipping_discount_amount!=null &&
              this.base_shipping_discount_amount.equals(other.getBase_shipping_discount_amount()))) &&
            ((this.base_subtotal_incl_tax==null && other.getBase_subtotal_incl_tax()==null) || 
             (this.base_subtotal_incl_tax!=null &&
              this.base_subtotal_incl_tax.equals(other.getBase_subtotal_incl_tax()))) &&
            ((this.base_total_due==null && other.getBase_total_due()==null) || 
             (this.base_total_due!=null &&
              this.base_total_due.equals(other.getBase_total_due()))) &&
            ((this.payment_authorization_amount==null && other.getPayment_authorization_amount()==null) || 
             (this.payment_authorization_amount!=null &&
              this.payment_authorization_amount.equals(other.getPayment_authorization_amount()))) &&
            ((this.shipping_discount_amount==null && other.getShipping_discount_amount()==null) || 
             (this.shipping_discount_amount!=null &&
              this.shipping_discount_amount.equals(other.getShipping_discount_amount()))) &&
            ((this.subtotal_incl_tax==null && other.getSubtotal_incl_tax()==null) || 
             (this.subtotal_incl_tax!=null &&
              this.subtotal_incl_tax.equals(other.getSubtotal_incl_tax()))) &&
            ((this.total_due==null && other.getTotal_due()==null) || 
             (this.total_due!=null &&
              this.total_due.equals(other.getTotal_due()))) &&
            ((this.customer_dob==null && other.getCustomer_dob()==null) || 
             (this.customer_dob!=null &&
              this.customer_dob.equals(other.getCustomer_dob()))) &&
            ((this.customer_middlename==null && other.getCustomer_middlename()==null) || 
             (this.customer_middlename!=null &&
              this.customer_middlename.equals(other.getCustomer_middlename()))) &&
            ((this.customer_prefix==null && other.getCustomer_prefix()==null) || 
             (this.customer_prefix!=null &&
              this.customer_prefix.equals(other.getCustomer_prefix()))) &&
            ((this.customer_suffix==null && other.getCustomer_suffix()==null) || 
             (this.customer_suffix!=null &&
              this.customer_suffix.equals(other.getCustomer_suffix()))) &&
            ((this.customer_taxvat==null && other.getCustomer_taxvat()==null) || 
             (this.customer_taxvat!=null &&
              this.customer_taxvat.equals(other.getCustomer_taxvat()))) &&
            ((this.discount_description==null && other.getDiscount_description()==null) || 
             (this.discount_description!=null &&
              this.discount_description.equals(other.getDiscount_description()))) &&
            ((this.ext_customer_id==null && other.getExt_customer_id()==null) || 
             (this.ext_customer_id!=null &&
              this.ext_customer_id.equals(other.getExt_customer_id()))) &&
            ((this.ext_order_id==null && other.getExt_order_id()==null) || 
             (this.ext_order_id!=null &&
              this.ext_order_id.equals(other.getExt_order_id()))) &&
            ((this.hold_before_state==null && other.getHold_before_state()==null) || 
             (this.hold_before_state!=null &&
              this.hold_before_state.equals(other.getHold_before_state()))) &&
            ((this.hold_before_status==null && other.getHold_before_status()==null) || 
             (this.hold_before_status!=null &&
              this.hold_before_status.equals(other.getHold_before_status()))) &&
            ((this.original_increment_id==null && other.getOriginal_increment_id()==null) || 
             (this.original_increment_id!=null &&
              this.original_increment_id.equals(other.getOriginal_increment_id()))) &&
            ((this.relation_child_id==null && other.getRelation_child_id()==null) || 
             (this.relation_child_id!=null &&
              this.relation_child_id.equals(other.getRelation_child_id()))) &&
            ((this.relation_child_real_id==null && other.getRelation_child_real_id()==null) || 
             (this.relation_child_real_id!=null &&
              this.relation_child_real_id.equals(other.getRelation_child_real_id()))) &&
            ((this.relation_parent_id==null && other.getRelation_parent_id()==null) || 
             (this.relation_parent_id!=null &&
              this.relation_parent_id.equals(other.getRelation_parent_id()))) &&
            ((this.relation_parent_real_id==null && other.getRelation_parent_real_id()==null) || 
             (this.relation_parent_real_id!=null &&
              this.relation_parent_real_id.equals(other.getRelation_parent_real_id()))) &&
            ((this.x_forwarded_for==null && other.getX_forwarded_for()==null) || 
             (this.x_forwarded_for!=null &&
              this.x_forwarded_for.equals(other.getX_forwarded_for()))) &&
            ((this.customer_note==null && other.getCustomer_note()==null) || 
             (this.customer_note!=null &&
              this.customer_note.equals(other.getCustomer_note()))) &&
            ((this.total_item_count==null && other.getTotal_item_count()==null) || 
             (this.total_item_count!=null &&
              this.total_item_count.equals(other.getTotal_item_count()))) &&
            ((this.customer_gender==null && other.getCustomer_gender()==null) || 
             (this.customer_gender!=null &&
              this.customer_gender.equals(other.getCustomer_gender()))) &&
            ((this.hidden_tax_amount==null && other.getHidden_tax_amount()==null) || 
             (this.hidden_tax_amount!=null &&
              this.hidden_tax_amount.equals(other.getHidden_tax_amount()))) &&
            ((this.base_hidden_tax_amount==null && other.getBase_hidden_tax_amount()==null) || 
             (this.base_hidden_tax_amount!=null &&
              this.base_hidden_tax_amount.equals(other.getBase_hidden_tax_amount()))) &&
            ((this.shipping_hidden_tax_amount==null && other.getShipping_hidden_tax_amount()==null) || 
             (this.shipping_hidden_tax_amount!=null &&
              this.shipping_hidden_tax_amount.equals(other.getShipping_hidden_tax_amount()))) &&
            ((this.base_shipping_hidden_tax_amount==null && other.getBase_shipping_hidden_tax_amount()==null) || 
             (this.base_shipping_hidden_tax_amount!=null &&
              this.base_shipping_hidden_tax_amount.equals(other.getBase_shipping_hidden_tax_amount()))) &&
            ((this.hidden_tax_invoiced==null && other.getHidden_tax_invoiced()==null) || 
             (this.hidden_tax_invoiced!=null &&
              this.hidden_tax_invoiced.equals(other.getHidden_tax_invoiced()))) &&
            ((this.base_hidden_tax_invoiced==null && other.getBase_hidden_tax_invoiced()==null) || 
             (this.base_hidden_tax_invoiced!=null &&
              this.base_hidden_tax_invoiced.equals(other.getBase_hidden_tax_invoiced()))) &&
            ((this.hidden_tax_refunded==null && other.getHidden_tax_refunded()==null) || 
             (this.hidden_tax_refunded!=null &&
              this.hidden_tax_refunded.equals(other.getHidden_tax_refunded()))) &&
            ((this.base_hidden_tax_refunded==null && other.getBase_hidden_tax_refunded()==null) || 
             (this.base_hidden_tax_refunded!=null &&
              this.base_hidden_tax_refunded.equals(other.getBase_hidden_tax_refunded()))) &&
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
            ((this.base_customer_balance_invoiced==null && other.getBase_customer_balance_invoiced()==null) || 
             (this.base_customer_balance_invoiced!=null &&
              this.base_customer_balance_invoiced.equals(other.getBase_customer_balance_invoiced()))) &&
            ((this.customer_balance_invoiced==null && other.getCustomer_balance_invoiced()==null) || 
             (this.customer_balance_invoiced!=null &&
              this.customer_balance_invoiced.equals(other.getCustomer_balance_invoiced()))) &&
            ((this.base_customer_balance_refunded==null && other.getBase_customer_balance_refunded()==null) || 
             (this.base_customer_balance_refunded!=null &&
              this.base_customer_balance_refunded.equals(other.getBase_customer_balance_refunded()))) &&
            ((this.customer_balance_refunded==null && other.getCustomer_balance_refunded()==null) || 
             (this.customer_balance_refunded!=null &&
              this.customer_balance_refunded.equals(other.getCustomer_balance_refunded()))) &&
            ((this.base_customer_balance_total_refunded==null && other.getBase_customer_balance_total_refunded()==null) || 
             (this.base_customer_balance_total_refunded!=null &&
              this.base_customer_balance_total_refunded.equals(other.getBase_customer_balance_total_refunded()))) &&
            ((this.customer_balance_total_refunded==null && other.getCustomer_balance_total_refunded()==null) || 
             (this.customer_balance_total_refunded!=null &&
              this.customer_balance_total_refunded.equals(other.getCustomer_balance_total_refunded()))) &&
            ((this.gift_cards==null && other.getGift_cards()==null) || 
             (this.gift_cards!=null &&
              this.gift_cards.equals(other.getGift_cards()))) &&
            ((this.base_gift_cards_amount==null && other.getBase_gift_cards_amount()==null) || 
             (this.base_gift_cards_amount!=null &&
              this.base_gift_cards_amount.equals(other.getBase_gift_cards_amount()))) &&
            ((this.gift_cards_amount==null && other.getGift_cards_amount()==null) || 
             (this.gift_cards_amount!=null &&
              this.gift_cards_amount.equals(other.getGift_cards_amount()))) &&
            ((this.base_gift_cards_invoiced==null && other.getBase_gift_cards_invoiced()==null) || 
             (this.base_gift_cards_invoiced!=null &&
              this.base_gift_cards_invoiced.equals(other.getBase_gift_cards_invoiced()))) &&
            ((this.gift_cards_invoiced==null && other.getGift_cards_invoiced()==null) || 
             (this.gift_cards_invoiced!=null &&
              this.gift_cards_invoiced.equals(other.getGift_cards_invoiced()))) &&
            ((this.base_gift_cards_refunded==null && other.getBase_gift_cards_refunded()==null) || 
             (this.base_gift_cards_refunded!=null &&
              this.base_gift_cards_refunded.equals(other.getBase_gift_cards_refunded()))) &&
            ((this.gift_cards_refunded==null && other.getGift_cards_refunded()==null) || 
             (this.gift_cards_refunded!=null &&
              this.gift_cards_refunded.equals(other.getGift_cards_refunded()))) &&
            ((this.reward_points_balance==null && other.getReward_points_balance()==null) || 
             (this.reward_points_balance!=null &&
              this.reward_points_balance.equals(other.getReward_points_balance()))) &&
            ((this.base_reward_currency_amount==null && other.getBase_reward_currency_amount()==null) || 
             (this.base_reward_currency_amount!=null &&
              this.base_reward_currency_amount.equals(other.getBase_reward_currency_amount()))) &&
            ((this.reward_currency_amount==null && other.getReward_currency_amount()==null) || 
             (this.reward_currency_amount!=null &&
              this.reward_currency_amount.equals(other.getReward_currency_amount()))) &&
            ((this.base_reward_currency_amount_invoiced==null && other.getBase_reward_currency_amount_invoiced()==null) || 
             (this.base_reward_currency_amount_invoiced!=null &&
              this.base_reward_currency_amount_invoiced.equals(other.getBase_reward_currency_amount_invoiced()))) &&
            ((this.reward_currency_amount_invoiced==null && other.getReward_currency_amount_invoiced()==null) || 
             (this.reward_currency_amount_invoiced!=null &&
              this.reward_currency_amount_invoiced.equals(other.getReward_currency_amount_invoiced()))) &&
            ((this.base_reward_currency_amount_refunded==null && other.getBase_reward_currency_amount_refunded()==null) || 
             (this.base_reward_currency_amount_refunded!=null &&
              this.base_reward_currency_amount_refunded.equals(other.getBase_reward_currency_amount_refunded()))) &&
            ((this.reward_currency_amount_refunded==null && other.getReward_currency_amount_refunded()==null) || 
             (this.reward_currency_amount_refunded!=null &&
              this.reward_currency_amount_refunded.equals(other.getReward_currency_amount_refunded()))) &&
            ((this.reward_points_balance_refunded==null && other.getReward_points_balance_refunded()==null) || 
             (this.reward_points_balance_refunded!=null &&
              this.reward_points_balance_refunded.equals(other.getReward_points_balance_refunded()))) &&
            ((this.reward_points_balance_to_refund==null && other.getReward_points_balance_to_refund()==null) || 
             (this.reward_points_balance_to_refund!=null &&
              this.reward_points_balance_to_refund.equals(other.getReward_points_balance_to_refund()))) &&
            ((this.reward_salesrule_points==null && other.getReward_salesrule_points()==null) || 
             (this.reward_salesrule_points!=null &&
              this.reward_salesrule_points.equals(other.getReward_salesrule_points()))) &&
            ((this.firstname==null && other.getFirstname()==null) || 
             (this.firstname!=null &&
              this.firstname.equals(other.getFirstname()))) &&
            ((this.lastname==null && other.getLastname()==null) || 
             (this.lastname!=null &&
              this.lastname.equals(other.getLastname()))) &&
            ((this.telephone==null && other.getTelephone()==null) || 
             (this.telephone!=null &&
              this.telephone.equals(other.getTelephone()))) &&
            ((this.postcode==null && other.getPostcode()==null) || 
             (this.postcode!=null &&
              this.postcode.equals(other.getPostcode())));
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
        if (getStore_id() != null) {
            _hashCode += getStore_id().hashCode();
        }
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getUpdated_at() != null) {
            _hashCode += getUpdated_at().hashCode();
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
        if (getCoupon_code() != null) {
            _hashCode += getCoupon_code().hashCode();
        }
        if (getProtect_code() != null) {
            _hashCode += getProtect_code().hashCode();
        }
        if (getBase_discount_canceled() != null) {
            _hashCode += getBase_discount_canceled().hashCode();
        }
        if (getBase_discount_invoiced() != null) {
            _hashCode += getBase_discount_invoiced().hashCode();
        }
        if (getBase_discount_refunded() != null) {
            _hashCode += getBase_discount_refunded().hashCode();
        }
        if (getBase_shipping_canceled() != null) {
            _hashCode += getBase_shipping_canceled().hashCode();
        }
        if (getBase_shipping_invoiced() != null) {
            _hashCode += getBase_shipping_invoiced().hashCode();
        }
        if (getBase_shipping_refunded() != null) {
            _hashCode += getBase_shipping_refunded().hashCode();
        }
        if (getBase_shipping_tax_amount() != null) {
            _hashCode += getBase_shipping_tax_amount().hashCode();
        }
        if (getBase_shipping_tax_refunded() != null) {
            _hashCode += getBase_shipping_tax_refunded().hashCode();
        }
        if (getBase_subtotal_canceled() != null) {
            _hashCode += getBase_subtotal_canceled().hashCode();
        }
        if (getBase_subtotal_invoiced() != null) {
            _hashCode += getBase_subtotal_invoiced().hashCode();
        }
        if (getBase_subtotal_refunded() != null) {
            _hashCode += getBase_subtotal_refunded().hashCode();
        }
        if (getBase_tax_canceled() != null) {
            _hashCode += getBase_tax_canceled().hashCode();
        }
        if (getBase_tax_invoiced() != null) {
            _hashCode += getBase_tax_invoiced().hashCode();
        }
        if (getBase_tax_refunded() != null) {
            _hashCode += getBase_tax_refunded().hashCode();
        }
        if (getBase_total_invoiced_cost() != null) {
            _hashCode += getBase_total_invoiced_cost().hashCode();
        }
        if (getDiscount_canceled() != null) {
            _hashCode += getDiscount_canceled().hashCode();
        }
        if (getDiscount_invoiced() != null) {
            _hashCode += getDiscount_invoiced().hashCode();
        }
        if (getDiscount_refunded() != null) {
            _hashCode += getDiscount_refunded().hashCode();
        }
        if (getShipping_canceled() != null) {
            _hashCode += getShipping_canceled().hashCode();
        }
        if (getShipping_invoiced() != null) {
            _hashCode += getShipping_invoiced().hashCode();
        }
        if (getShipping_refunded() != null) {
            _hashCode += getShipping_refunded().hashCode();
        }
        if (getShipping_tax_amount() != null) {
            _hashCode += getShipping_tax_amount().hashCode();
        }
        if (getShipping_tax_refunded() != null) {
            _hashCode += getShipping_tax_refunded().hashCode();
        }
        if (getSubtotal_canceled() != null) {
            _hashCode += getSubtotal_canceled().hashCode();
        }
        if (getSubtotal_invoiced() != null) {
            _hashCode += getSubtotal_invoiced().hashCode();
        }
        if (getSubtotal_refunded() != null) {
            _hashCode += getSubtotal_refunded().hashCode();
        }
        if (getTax_canceled() != null) {
            _hashCode += getTax_canceled().hashCode();
        }
        if (getTax_invoiced() != null) {
            _hashCode += getTax_invoiced().hashCode();
        }
        if (getTax_refunded() != null) {
            _hashCode += getTax_refunded().hashCode();
        }
        if (getCan_ship_partially() != null) {
            _hashCode += getCan_ship_partially().hashCode();
        }
        if (getCan_ship_partially_item() != null) {
            _hashCode += getCan_ship_partially_item().hashCode();
        }
        if (getEdit_increment() != null) {
            _hashCode += getEdit_increment().hashCode();
        }
        if (getForced_do_shipment_with_invoice() != null) {
            _hashCode += getForced_do_shipment_with_invoice().hashCode();
        }
        if (getPayment_authorization_expiration() != null) {
            _hashCode += getPayment_authorization_expiration().hashCode();
        }
        if (getPaypal_ipn_customer_notified() != null) {
            _hashCode += getPaypal_ipn_customer_notified().hashCode();
        }
        if (getQuote_address_id() != null) {
            _hashCode += getQuote_address_id().hashCode();
        }
        if (getAdjustment_negative() != null) {
            _hashCode += getAdjustment_negative().hashCode();
        }
        if (getAdjustment_positive() != null) {
            _hashCode += getAdjustment_positive().hashCode();
        }
        if (getBase_adjustment_negative() != null) {
            _hashCode += getBase_adjustment_negative().hashCode();
        }
        if (getBase_adjustment_positive() != null) {
            _hashCode += getBase_adjustment_positive().hashCode();
        }
        if (getBase_shipping_discount_amount() != null) {
            _hashCode += getBase_shipping_discount_amount().hashCode();
        }
        if (getBase_subtotal_incl_tax() != null) {
            _hashCode += getBase_subtotal_incl_tax().hashCode();
        }
        if (getBase_total_due() != null) {
            _hashCode += getBase_total_due().hashCode();
        }
        if (getPayment_authorization_amount() != null) {
            _hashCode += getPayment_authorization_amount().hashCode();
        }
        if (getShipping_discount_amount() != null) {
            _hashCode += getShipping_discount_amount().hashCode();
        }
        if (getSubtotal_incl_tax() != null) {
            _hashCode += getSubtotal_incl_tax().hashCode();
        }
        if (getTotal_due() != null) {
            _hashCode += getTotal_due().hashCode();
        }
        if (getCustomer_dob() != null) {
            _hashCode += getCustomer_dob().hashCode();
        }
        if (getCustomer_middlename() != null) {
            _hashCode += getCustomer_middlename().hashCode();
        }
        if (getCustomer_prefix() != null) {
            _hashCode += getCustomer_prefix().hashCode();
        }
        if (getCustomer_suffix() != null) {
            _hashCode += getCustomer_suffix().hashCode();
        }
        if (getCustomer_taxvat() != null) {
            _hashCode += getCustomer_taxvat().hashCode();
        }
        if (getDiscount_description() != null) {
            _hashCode += getDiscount_description().hashCode();
        }
        if (getExt_customer_id() != null) {
            _hashCode += getExt_customer_id().hashCode();
        }
        if (getExt_order_id() != null) {
            _hashCode += getExt_order_id().hashCode();
        }
        if (getHold_before_state() != null) {
            _hashCode += getHold_before_state().hashCode();
        }
        if (getHold_before_status() != null) {
            _hashCode += getHold_before_status().hashCode();
        }
        if (getOriginal_increment_id() != null) {
            _hashCode += getOriginal_increment_id().hashCode();
        }
        if (getRelation_child_id() != null) {
            _hashCode += getRelation_child_id().hashCode();
        }
        if (getRelation_child_real_id() != null) {
            _hashCode += getRelation_child_real_id().hashCode();
        }
        if (getRelation_parent_id() != null) {
            _hashCode += getRelation_parent_id().hashCode();
        }
        if (getRelation_parent_real_id() != null) {
            _hashCode += getRelation_parent_real_id().hashCode();
        }
        if (getX_forwarded_for() != null) {
            _hashCode += getX_forwarded_for().hashCode();
        }
        if (getCustomer_note() != null) {
            _hashCode += getCustomer_note().hashCode();
        }
        if (getTotal_item_count() != null) {
            _hashCode += getTotal_item_count().hashCode();
        }
        if (getCustomer_gender() != null) {
            _hashCode += getCustomer_gender().hashCode();
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
        if (getBase_shipping_hidden_tax_amount() != null) {
            _hashCode += getBase_shipping_hidden_tax_amount().hashCode();
        }
        if (getHidden_tax_invoiced() != null) {
            _hashCode += getHidden_tax_invoiced().hashCode();
        }
        if (getBase_hidden_tax_invoiced() != null) {
            _hashCode += getBase_hidden_tax_invoiced().hashCode();
        }
        if (getHidden_tax_refunded() != null) {
            _hashCode += getHidden_tax_refunded().hashCode();
        }
        if (getBase_hidden_tax_refunded() != null) {
            _hashCode += getBase_hidden_tax_refunded().hashCode();
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
        if (getBase_customer_balance_invoiced() != null) {
            _hashCode += getBase_customer_balance_invoiced().hashCode();
        }
        if (getCustomer_balance_invoiced() != null) {
            _hashCode += getCustomer_balance_invoiced().hashCode();
        }
        if (getBase_customer_balance_refunded() != null) {
            _hashCode += getBase_customer_balance_refunded().hashCode();
        }
        if (getCustomer_balance_refunded() != null) {
            _hashCode += getCustomer_balance_refunded().hashCode();
        }
        if (getBase_customer_balance_total_refunded() != null) {
            _hashCode += getBase_customer_balance_total_refunded().hashCode();
        }
        if (getCustomer_balance_total_refunded() != null) {
            _hashCode += getCustomer_balance_total_refunded().hashCode();
        }
        if (getGift_cards() != null) {
            _hashCode += getGift_cards().hashCode();
        }
        if (getBase_gift_cards_amount() != null) {
            _hashCode += getBase_gift_cards_amount().hashCode();
        }
        if (getGift_cards_amount() != null) {
            _hashCode += getGift_cards_amount().hashCode();
        }
        if (getBase_gift_cards_invoiced() != null) {
            _hashCode += getBase_gift_cards_invoiced().hashCode();
        }
        if (getGift_cards_invoiced() != null) {
            _hashCode += getGift_cards_invoiced().hashCode();
        }
        if (getBase_gift_cards_refunded() != null) {
            _hashCode += getBase_gift_cards_refunded().hashCode();
        }
        if (getGift_cards_refunded() != null) {
            _hashCode += getGift_cards_refunded().hashCode();
        }
        if (getReward_points_balance() != null) {
            _hashCode += getReward_points_balance().hashCode();
        }
        if (getBase_reward_currency_amount() != null) {
            _hashCode += getBase_reward_currency_amount().hashCode();
        }
        if (getReward_currency_amount() != null) {
            _hashCode += getReward_currency_amount().hashCode();
        }
        if (getBase_reward_currency_amount_invoiced() != null) {
            _hashCode += getBase_reward_currency_amount_invoiced().hashCode();
        }
        if (getReward_currency_amount_invoiced() != null) {
            _hashCode += getReward_currency_amount_invoiced().hashCode();
        }
        if (getBase_reward_currency_amount_refunded() != null) {
            _hashCode += getBase_reward_currency_amount_refunded().hashCode();
        }
        if (getReward_currency_amount_refunded() != null) {
            _hashCode += getReward_currency_amount_refunded().hashCode();
        }
        if (getReward_points_balance_refunded() != null) {
            _hashCode += getReward_points_balance_refunded().hashCode();
        }
        if (getReward_points_balance_to_refund() != null) {
            _hashCode += getReward_points_balance_to_refund().hashCode();
        }
        if (getReward_salesrule_points() != null) {
            _hashCode += getReward_salesrule_points().hashCode();
        }
        if (getFirstname() != null) {
            _hashCode += getFirstname().hashCode();
        }
        if (getLastname() != null) {
            _hashCode += getLastname().hashCode();
        }
        if (getTelephone() != null) {
            _hashCode += getTelephone().hashCode();
        }
        if (getPostcode() != null) {
            _hashCode += getPostcode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderListEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderListEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("increment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "increment_id"));
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
        elemField.setFieldName("coupon_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coupon_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protect_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "protect_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_discount_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_discount_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_discount_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_discount_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_discount_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_discount_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_refunded"));
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
        elemField.setFieldName("base_shipping_tax_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_tax_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_subtotal_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_subtotal_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_subtotal_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_tax_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_tax_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_tax_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_tax_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_tax_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_tax_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_total_invoiced_cost");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_invoiced_cost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_refunded"));
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
        elemField.setFieldName("shipping_tax_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_tax_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtotal_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtotal_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtotal_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("can_ship_partially");
        elemField.setXmlName(new javax.xml.namespace.QName("", "can_ship_partially"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("can_ship_partially_item");
        elemField.setXmlName(new javax.xml.namespace.QName("", "can_ship_partially_item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edit_increment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "edit_increment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forced_do_shipment_with_invoice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "forced_do_shipment_with_invoice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payment_authorization_expiration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payment_authorization_expiration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paypal_ipn_customer_notified");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paypal_ipn_customer_notified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quote_address_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quote_address_id"));
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
        elemField.setFieldName("adjustment_positive");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adjustment_positive"));
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
        elemField.setFieldName("base_adjustment_positive");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_adjustment_positive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_discount_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_discount_amount"));
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
        elemField.setFieldName("base_total_due");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_total_due"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payment_authorization_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payment_authorization_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_discount_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_discount_amount"));
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
        elemField.setFieldName("total_due");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_due"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_dob");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_dob"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_middlename");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_middlename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_prefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_prefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_suffix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_suffix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_taxvat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_taxvat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount_description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ext_customer_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ext_customer_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ext_order_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ext_order_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hold_before_state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hold_before_state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hold_before_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hold_before_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_increment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "original_increment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relation_child_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relation_child_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relation_child_real_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relation_child_real_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relation_parent_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relation_parent_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relation_parent_real_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relation_parent_real_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("x_forwarded_for");
        elemField.setXmlName(new javax.xml.namespace.QName("", "x_forwarded_for"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_note");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_item_count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_item_count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_gender"));
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
        elemField.setFieldName("base_shipping_hidden_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_hidden_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hidden_tax_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hidden_tax_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_hidden_tax_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_hidden_tax_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hidden_tax_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hidden_tax_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_hidden_tax_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_hidden_tax_refunded"));
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
        elemField.setFieldName("base_customer_balance_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_customer_balance_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_balance_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_balance_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_customer_balance_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_customer_balance_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_balance_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_balance_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_customer_balance_total_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_customer_balance_total_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_balance_total_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_balance_total_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gift_cards");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_cards"));
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
        elemField.setFieldName("base_gift_cards_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_gift_cards_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gift_cards_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_cards_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_gift_cards_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_gift_cards_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gift_cards_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_cards_refunded"));
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
        elemField.setFieldName("base_reward_currency_amount_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_reward_currency_amount_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_currency_amount_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_currency_amount_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_reward_currency_amount_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_reward_currency_amount_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_currency_amount_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_currency_amount_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_points_balance_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_points_balance_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_points_balance_to_refund");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_points_balance_to_refund"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reward_salesrule_points");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reward_salesrule_points"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telephone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
