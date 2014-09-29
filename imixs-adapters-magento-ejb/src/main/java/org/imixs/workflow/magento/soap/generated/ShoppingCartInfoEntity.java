/**
 * ShoppingCartInfoEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartInfoEntity  implements java.io.Serializable {
    private java.lang.String store_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String converted_at;

    private java.lang.Integer quote_id;

    private java.lang.Integer is_active;

    private java.lang.Integer is_virtual;

    private java.lang.Integer is_multi_shipping;

    private java.lang.Double items_count;

    private java.lang.Double items_qty;

    private java.lang.String orig_order_id;

    private java.lang.String store_to_base_rate;

    private java.lang.String store_to_quote_rate;

    private java.lang.String base_currency_code;

    private java.lang.String store_currency_code;

    private java.lang.String quote_currency_code;

    private java.lang.String grand_total;

    private java.lang.String base_grand_total;

    private java.lang.String checkout_method;

    private java.lang.String customer_id;

    private java.lang.String customer_tax_class_id;

    private java.lang.Integer customer_group_id;

    private java.lang.String customer_email;

    private java.lang.String customer_prefix;

    private java.lang.String customer_firstname;

    private java.lang.String customer_middlename;

    private java.lang.String customer_lastname;

    private java.lang.String customer_suffix;

    private java.lang.String customer_note;

    private java.lang.String customer_note_notify;

    private java.lang.String customer_is_guest;

    private java.lang.String applied_rule_ids;

    private java.lang.String reserved_order_id;

    private java.lang.String password_hash;

    private java.lang.String coupon_code;

    private java.lang.String global_currency_code;

    private java.lang.Double base_to_global_rate;

    private java.lang.Double base_to_quote_rate;

    private java.lang.String customer_taxvat;

    private java.lang.String customer_gender;

    private java.lang.Double subtotal;

    private java.lang.Double base_subtotal;

    private java.lang.Double subtotal_with_discount;

    private java.lang.Double base_subtotal_with_discount;

    private java.lang.String ext_shipping_info;

    private java.lang.String gift_message_id;

    private java.lang.String gift_message;

    private java.lang.Double customer_balance_amount_used;

    private java.lang.Double base_customer_balance_amount_used;

    private java.lang.String use_customer_balance;

    private java.lang.String gift_cards_amount;

    private java.lang.String base_gift_cards_amount;

    private java.lang.String gift_cards_amount_used;

    private java.lang.String use_reward_points;

    private java.lang.String reward_points_balance;

    private java.lang.String base_reward_currency_amount;

    private java.lang.String reward_currency_amount;

    private ShoppingCartAddressEntity shipping_address;

    private ShoppingCartAddressEntity billing_address;

    private ShoppingCartItemEntity[] items;

    private ShoppingCartPaymentEntity payment;

    public ShoppingCartInfoEntity() {
    }

    public ShoppingCartInfoEntity(
           java.lang.String store_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String converted_at,
           java.lang.Integer quote_id,
           java.lang.Integer is_active,
           java.lang.Integer is_virtual,
           java.lang.Integer is_multi_shipping,
           java.lang.Double items_count,
           java.lang.Double items_qty,
           java.lang.String orig_order_id,
           java.lang.String store_to_base_rate,
           java.lang.String store_to_quote_rate,
           java.lang.String base_currency_code,
           java.lang.String store_currency_code,
           java.lang.String quote_currency_code,
           java.lang.String grand_total,
           java.lang.String base_grand_total,
           java.lang.String checkout_method,
           java.lang.String customer_id,
           java.lang.String customer_tax_class_id,
           java.lang.Integer customer_group_id,
           java.lang.String customer_email,
           java.lang.String customer_prefix,
           java.lang.String customer_firstname,
           java.lang.String customer_middlename,
           java.lang.String customer_lastname,
           java.lang.String customer_suffix,
           java.lang.String customer_note,
           java.lang.String customer_note_notify,
           java.lang.String customer_is_guest,
           java.lang.String applied_rule_ids,
           java.lang.String reserved_order_id,
           java.lang.String password_hash,
           java.lang.String coupon_code,
           java.lang.String global_currency_code,
           java.lang.Double base_to_global_rate,
           java.lang.Double base_to_quote_rate,
           java.lang.String customer_taxvat,
           java.lang.String customer_gender,
           java.lang.Double subtotal,
           java.lang.Double base_subtotal,
           java.lang.Double subtotal_with_discount,
           java.lang.Double base_subtotal_with_discount,
           java.lang.String ext_shipping_info,
           java.lang.String gift_message_id,
           java.lang.String gift_message,
           java.lang.Double customer_balance_amount_used,
           java.lang.Double base_customer_balance_amount_used,
           java.lang.String use_customer_balance,
           java.lang.String gift_cards_amount,
           java.lang.String base_gift_cards_amount,
           java.lang.String gift_cards_amount_used,
           java.lang.String use_reward_points,
           java.lang.String reward_points_balance,
           java.lang.String base_reward_currency_amount,
           java.lang.String reward_currency_amount,
           ShoppingCartAddressEntity shipping_address,
           ShoppingCartAddressEntity billing_address,
           ShoppingCartItemEntity[] items,
           ShoppingCartPaymentEntity payment) {
           this.store_id = store_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.converted_at = converted_at;
           this.quote_id = quote_id;
           this.is_active = is_active;
           this.is_virtual = is_virtual;
           this.is_multi_shipping = is_multi_shipping;
           this.items_count = items_count;
           this.items_qty = items_qty;
           this.orig_order_id = orig_order_id;
           this.store_to_base_rate = store_to_base_rate;
           this.store_to_quote_rate = store_to_quote_rate;
           this.base_currency_code = base_currency_code;
           this.store_currency_code = store_currency_code;
           this.quote_currency_code = quote_currency_code;
           this.grand_total = grand_total;
           this.base_grand_total = base_grand_total;
           this.checkout_method = checkout_method;
           this.customer_id = customer_id;
           this.customer_tax_class_id = customer_tax_class_id;
           this.customer_group_id = customer_group_id;
           this.customer_email = customer_email;
           this.customer_prefix = customer_prefix;
           this.customer_firstname = customer_firstname;
           this.customer_middlename = customer_middlename;
           this.customer_lastname = customer_lastname;
           this.customer_suffix = customer_suffix;
           this.customer_note = customer_note;
           this.customer_note_notify = customer_note_notify;
           this.customer_is_guest = customer_is_guest;
           this.applied_rule_ids = applied_rule_ids;
           this.reserved_order_id = reserved_order_id;
           this.password_hash = password_hash;
           this.coupon_code = coupon_code;
           this.global_currency_code = global_currency_code;
           this.base_to_global_rate = base_to_global_rate;
           this.base_to_quote_rate = base_to_quote_rate;
           this.customer_taxvat = customer_taxvat;
           this.customer_gender = customer_gender;
           this.subtotal = subtotal;
           this.base_subtotal = base_subtotal;
           this.subtotal_with_discount = subtotal_with_discount;
           this.base_subtotal_with_discount = base_subtotal_with_discount;
           this.ext_shipping_info = ext_shipping_info;
           this.gift_message_id = gift_message_id;
           this.gift_message = gift_message;
           this.customer_balance_amount_used = customer_balance_amount_used;
           this.base_customer_balance_amount_used = base_customer_balance_amount_used;
           this.use_customer_balance = use_customer_balance;
           this.gift_cards_amount = gift_cards_amount;
           this.base_gift_cards_amount = base_gift_cards_amount;
           this.gift_cards_amount_used = gift_cards_amount_used;
           this.use_reward_points = use_reward_points;
           this.reward_points_balance = reward_points_balance;
           this.base_reward_currency_amount = base_reward_currency_amount;
           this.reward_currency_amount = reward_currency_amount;
           this.shipping_address = shipping_address;
           this.billing_address = billing_address;
           this.items = items;
           this.payment = payment;
    }


    /**
     * Gets the store_id value for this ShoppingCartInfoEntity.
     * 
     * @return store_id
     */
    public java.lang.String getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this ShoppingCartInfoEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.String store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the created_at value for this ShoppingCartInfoEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this ShoppingCartInfoEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this ShoppingCartInfoEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this ShoppingCartInfoEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the converted_at value for this ShoppingCartInfoEntity.
     * 
     * @return converted_at
     */
    public java.lang.String getConverted_at() {
        return converted_at;
    }


    /**
     * Sets the converted_at value for this ShoppingCartInfoEntity.
     * 
     * @param converted_at
     */
    public void setConverted_at(java.lang.String converted_at) {
        this.converted_at = converted_at;
    }


    /**
     * Gets the quote_id value for this ShoppingCartInfoEntity.
     * 
     * @return quote_id
     */
    public java.lang.Integer getQuote_id() {
        return quote_id;
    }


    /**
     * Sets the quote_id value for this ShoppingCartInfoEntity.
     * 
     * @param quote_id
     */
    public void setQuote_id(java.lang.Integer quote_id) {
        this.quote_id = quote_id;
    }


    /**
     * Gets the is_active value for this ShoppingCartInfoEntity.
     * 
     * @return is_active
     */
    public java.lang.Integer getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this ShoppingCartInfoEntity.
     * 
     * @param is_active
     */
    public void setIs_active(java.lang.Integer is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the is_virtual value for this ShoppingCartInfoEntity.
     * 
     * @return is_virtual
     */
    public java.lang.Integer getIs_virtual() {
        return is_virtual;
    }


    /**
     * Sets the is_virtual value for this ShoppingCartInfoEntity.
     * 
     * @param is_virtual
     */
    public void setIs_virtual(java.lang.Integer is_virtual) {
        this.is_virtual = is_virtual;
    }


    /**
     * Gets the is_multi_shipping value for this ShoppingCartInfoEntity.
     * 
     * @return is_multi_shipping
     */
    public java.lang.Integer getIs_multi_shipping() {
        return is_multi_shipping;
    }


    /**
     * Sets the is_multi_shipping value for this ShoppingCartInfoEntity.
     * 
     * @param is_multi_shipping
     */
    public void setIs_multi_shipping(java.lang.Integer is_multi_shipping) {
        this.is_multi_shipping = is_multi_shipping;
    }


    /**
     * Gets the items_count value for this ShoppingCartInfoEntity.
     * 
     * @return items_count
     */
    public java.lang.Double getItems_count() {
        return items_count;
    }


    /**
     * Sets the items_count value for this ShoppingCartInfoEntity.
     * 
     * @param items_count
     */
    public void setItems_count(java.lang.Double items_count) {
        this.items_count = items_count;
    }


    /**
     * Gets the items_qty value for this ShoppingCartInfoEntity.
     * 
     * @return items_qty
     */
    public java.lang.Double getItems_qty() {
        return items_qty;
    }


    /**
     * Sets the items_qty value for this ShoppingCartInfoEntity.
     * 
     * @param items_qty
     */
    public void setItems_qty(java.lang.Double items_qty) {
        this.items_qty = items_qty;
    }


    /**
     * Gets the orig_order_id value for this ShoppingCartInfoEntity.
     * 
     * @return orig_order_id
     */
    public java.lang.String getOrig_order_id() {
        return orig_order_id;
    }


    /**
     * Sets the orig_order_id value for this ShoppingCartInfoEntity.
     * 
     * @param orig_order_id
     */
    public void setOrig_order_id(java.lang.String orig_order_id) {
        this.orig_order_id = orig_order_id;
    }


    /**
     * Gets the store_to_base_rate value for this ShoppingCartInfoEntity.
     * 
     * @return store_to_base_rate
     */
    public java.lang.String getStore_to_base_rate() {
        return store_to_base_rate;
    }


    /**
     * Sets the store_to_base_rate value for this ShoppingCartInfoEntity.
     * 
     * @param store_to_base_rate
     */
    public void setStore_to_base_rate(java.lang.String store_to_base_rate) {
        this.store_to_base_rate = store_to_base_rate;
    }


    /**
     * Gets the store_to_quote_rate value for this ShoppingCartInfoEntity.
     * 
     * @return store_to_quote_rate
     */
    public java.lang.String getStore_to_quote_rate() {
        return store_to_quote_rate;
    }


    /**
     * Sets the store_to_quote_rate value for this ShoppingCartInfoEntity.
     * 
     * @param store_to_quote_rate
     */
    public void setStore_to_quote_rate(java.lang.String store_to_quote_rate) {
        this.store_to_quote_rate = store_to_quote_rate;
    }


    /**
     * Gets the base_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @return base_currency_code
     */
    public java.lang.String getBase_currency_code() {
        return base_currency_code;
    }


    /**
     * Sets the base_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @param base_currency_code
     */
    public void setBase_currency_code(java.lang.String base_currency_code) {
        this.base_currency_code = base_currency_code;
    }


    /**
     * Gets the store_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @return store_currency_code
     */
    public java.lang.String getStore_currency_code() {
        return store_currency_code;
    }


    /**
     * Sets the store_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @param store_currency_code
     */
    public void setStore_currency_code(java.lang.String store_currency_code) {
        this.store_currency_code = store_currency_code;
    }


    /**
     * Gets the quote_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @return quote_currency_code
     */
    public java.lang.String getQuote_currency_code() {
        return quote_currency_code;
    }


    /**
     * Sets the quote_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @param quote_currency_code
     */
    public void setQuote_currency_code(java.lang.String quote_currency_code) {
        this.quote_currency_code = quote_currency_code;
    }


    /**
     * Gets the grand_total value for this ShoppingCartInfoEntity.
     * 
     * @return grand_total
     */
    public java.lang.String getGrand_total() {
        return grand_total;
    }


    /**
     * Sets the grand_total value for this ShoppingCartInfoEntity.
     * 
     * @param grand_total
     */
    public void setGrand_total(java.lang.String grand_total) {
        this.grand_total = grand_total;
    }


    /**
     * Gets the base_grand_total value for this ShoppingCartInfoEntity.
     * 
     * @return base_grand_total
     */
    public java.lang.String getBase_grand_total() {
        return base_grand_total;
    }


    /**
     * Sets the base_grand_total value for this ShoppingCartInfoEntity.
     * 
     * @param base_grand_total
     */
    public void setBase_grand_total(java.lang.String base_grand_total) {
        this.base_grand_total = base_grand_total;
    }


    /**
     * Gets the checkout_method value for this ShoppingCartInfoEntity.
     * 
     * @return checkout_method
     */
    public java.lang.String getCheckout_method() {
        return checkout_method;
    }


    /**
     * Sets the checkout_method value for this ShoppingCartInfoEntity.
     * 
     * @param checkout_method
     */
    public void setCheckout_method(java.lang.String checkout_method) {
        this.checkout_method = checkout_method;
    }


    /**
     * Gets the customer_id value for this ShoppingCartInfoEntity.
     * 
     * @return customer_id
     */
    public java.lang.String getCustomer_id() {
        return customer_id;
    }


    /**
     * Sets the customer_id value for this ShoppingCartInfoEntity.
     * 
     * @param customer_id
     */
    public void setCustomer_id(java.lang.String customer_id) {
        this.customer_id = customer_id;
    }


    /**
     * Gets the customer_tax_class_id value for this ShoppingCartInfoEntity.
     * 
     * @return customer_tax_class_id
     */
    public java.lang.String getCustomer_tax_class_id() {
        return customer_tax_class_id;
    }


    /**
     * Sets the customer_tax_class_id value for this ShoppingCartInfoEntity.
     * 
     * @param customer_tax_class_id
     */
    public void setCustomer_tax_class_id(java.lang.String customer_tax_class_id) {
        this.customer_tax_class_id = customer_tax_class_id;
    }


    /**
     * Gets the customer_group_id value for this ShoppingCartInfoEntity.
     * 
     * @return customer_group_id
     */
    public java.lang.Integer getCustomer_group_id() {
        return customer_group_id;
    }


    /**
     * Sets the customer_group_id value for this ShoppingCartInfoEntity.
     * 
     * @param customer_group_id
     */
    public void setCustomer_group_id(java.lang.Integer customer_group_id) {
        this.customer_group_id = customer_group_id;
    }


    /**
     * Gets the customer_email value for this ShoppingCartInfoEntity.
     * 
     * @return customer_email
     */
    public java.lang.String getCustomer_email() {
        return customer_email;
    }


    /**
     * Sets the customer_email value for this ShoppingCartInfoEntity.
     * 
     * @param customer_email
     */
    public void setCustomer_email(java.lang.String customer_email) {
        this.customer_email = customer_email;
    }


    /**
     * Gets the customer_prefix value for this ShoppingCartInfoEntity.
     * 
     * @return customer_prefix
     */
    public java.lang.String getCustomer_prefix() {
        return customer_prefix;
    }


    /**
     * Sets the customer_prefix value for this ShoppingCartInfoEntity.
     * 
     * @param customer_prefix
     */
    public void setCustomer_prefix(java.lang.String customer_prefix) {
        this.customer_prefix = customer_prefix;
    }


    /**
     * Gets the customer_firstname value for this ShoppingCartInfoEntity.
     * 
     * @return customer_firstname
     */
    public java.lang.String getCustomer_firstname() {
        return customer_firstname;
    }


    /**
     * Sets the customer_firstname value for this ShoppingCartInfoEntity.
     * 
     * @param customer_firstname
     */
    public void setCustomer_firstname(java.lang.String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }


    /**
     * Gets the customer_middlename value for this ShoppingCartInfoEntity.
     * 
     * @return customer_middlename
     */
    public java.lang.String getCustomer_middlename() {
        return customer_middlename;
    }


    /**
     * Sets the customer_middlename value for this ShoppingCartInfoEntity.
     * 
     * @param customer_middlename
     */
    public void setCustomer_middlename(java.lang.String customer_middlename) {
        this.customer_middlename = customer_middlename;
    }


    /**
     * Gets the customer_lastname value for this ShoppingCartInfoEntity.
     * 
     * @return customer_lastname
     */
    public java.lang.String getCustomer_lastname() {
        return customer_lastname;
    }


    /**
     * Sets the customer_lastname value for this ShoppingCartInfoEntity.
     * 
     * @param customer_lastname
     */
    public void setCustomer_lastname(java.lang.String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }


    /**
     * Gets the customer_suffix value for this ShoppingCartInfoEntity.
     * 
     * @return customer_suffix
     */
    public java.lang.String getCustomer_suffix() {
        return customer_suffix;
    }


    /**
     * Sets the customer_suffix value for this ShoppingCartInfoEntity.
     * 
     * @param customer_suffix
     */
    public void setCustomer_suffix(java.lang.String customer_suffix) {
        this.customer_suffix = customer_suffix;
    }


    /**
     * Gets the customer_note value for this ShoppingCartInfoEntity.
     * 
     * @return customer_note
     */
    public java.lang.String getCustomer_note() {
        return customer_note;
    }


    /**
     * Sets the customer_note value for this ShoppingCartInfoEntity.
     * 
     * @param customer_note
     */
    public void setCustomer_note(java.lang.String customer_note) {
        this.customer_note = customer_note;
    }


    /**
     * Gets the customer_note_notify value for this ShoppingCartInfoEntity.
     * 
     * @return customer_note_notify
     */
    public java.lang.String getCustomer_note_notify() {
        return customer_note_notify;
    }


    /**
     * Sets the customer_note_notify value for this ShoppingCartInfoEntity.
     * 
     * @param customer_note_notify
     */
    public void setCustomer_note_notify(java.lang.String customer_note_notify) {
        this.customer_note_notify = customer_note_notify;
    }


    /**
     * Gets the customer_is_guest value for this ShoppingCartInfoEntity.
     * 
     * @return customer_is_guest
     */
    public java.lang.String getCustomer_is_guest() {
        return customer_is_guest;
    }


    /**
     * Sets the customer_is_guest value for this ShoppingCartInfoEntity.
     * 
     * @param customer_is_guest
     */
    public void setCustomer_is_guest(java.lang.String customer_is_guest) {
        this.customer_is_guest = customer_is_guest;
    }


    /**
     * Gets the applied_rule_ids value for this ShoppingCartInfoEntity.
     * 
     * @return applied_rule_ids
     */
    public java.lang.String getApplied_rule_ids() {
        return applied_rule_ids;
    }


    /**
     * Sets the applied_rule_ids value for this ShoppingCartInfoEntity.
     * 
     * @param applied_rule_ids
     */
    public void setApplied_rule_ids(java.lang.String applied_rule_ids) {
        this.applied_rule_ids = applied_rule_ids;
    }


    /**
     * Gets the reserved_order_id value for this ShoppingCartInfoEntity.
     * 
     * @return reserved_order_id
     */
    public java.lang.String getReserved_order_id() {
        return reserved_order_id;
    }


    /**
     * Sets the reserved_order_id value for this ShoppingCartInfoEntity.
     * 
     * @param reserved_order_id
     */
    public void setReserved_order_id(java.lang.String reserved_order_id) {
        this.reserved_order_id = reserved_order_id;
    }


    /**
     * Gets the password_hash value for this ShoppingCartInfoEntity.
     * 
     * @return password_hash
     */
    public java.lang.String getPassword_hash() {
        return password_hash;
    }


    /**
     * Sets the password_hash value for this ShoppingCartInfoEntity.
     * 
     * @param password_hash
     */
    public void setPassword_hash(java.lang.String password_hash) {
        this.password_hash = password_hash;
    }


    /**
     * Gets the coupon_code value for this ShoppingCartInfoEntity.
     * 
     * @return coupon_code
     */
    public java.lang.String getCoupon_code() {
        return coupon_code;
    }


    /**
     * Sets the coupon_code value for this ShoppingCartInfoEntity.
     * 
     * @param coupon_code
     */
    public void setCoupon_code(java.lang.String coupon_code) {
        this.coupon_code = coupon_code;
    }


    /**
     * Gets the global_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @return global_currency_code
     */
    public java.lang.String getGlobal_currency_code() {
        return global_currency_code;
    }


    /**
     * Sets the global_currency_code value for this ShoppingCartInfoEntity.
     * 
     * @param global_currency_code
     */
    public void setGlobal_currency_code(java.lang.String global_currency_code) {
        this.global_currency_code = global_currency_code;
    }


    /**
     * Gets the base_to_global_rate value for this ShoppingCartInfoEntity.
     * 
     * @return base_to_global_rate
     */
    public java.lang.Double getBase_to_global_rate() {
        return base_to_global_rate;
    }


    /**
     * Sets the base_to_global_rate value for this ShoppingCartInfoEntity.
     * 
     * @param base_to_global_rate
     */
    public void setBase_to_global_rate(java.lang.Double base_to_global_rate) {
        this.base_to_global_rate = base_to_global_rate;
    }


    /**
     * Gets the base_to_quote_rate value for this ShoppingCartInfoEntity.
     * 
     * @return base_to_quote_rate
     */
    public java.lang.Double getBase_to_quote_rate() {
        return base_to_quote_rate;
    }


    /**
     * Sets the base_to_quote_rate value for this ShoppingCartInfoEntity.
     * 
     * @param base_to_quote_rate
     */
    public void setBase_to_quote_rate(java.lang.Double base_to_quote_rate) {
        this.base_to_quote_rate = base_to_quote_rate;
    }


    /**
     * Gets the customer_taxvat value for this ShoppingCartInfoEntity.
     * 
     * @return customer_taxvat
     */
    public java.lang.String getCustomer_taxvat() {
        return customer_taxvat;
    }


    /**
     * Sets the customer_taxvat value for this ShoppingCartInfoEntity.
     * 
     * @param customer_taxvat
     */
    public void setCustomer_taxvat(java.lang.String customer_taxvat) {
        this.customer_taxvat = customer_taxvat;
    }


    /**
     * Gets the customer_gender value for this ShoppingCartInfoEntity.
     * 
     * @return customer_gender
     */
    public java.lang.String getCustomer_gender() {
        return customer_gender;
    }


    /**
     * Sets the customer_gender value for this ShoppingCartInfoEntity.
     * 
     * @param customer_gender
     */
    public void setCustomer_gender(java.lang.String customer_gender) {
        this.customer_gender = customer_gender;
    }


    /**
     * Gets the subtotal value for this ShoppingCartInfoEntity.
     * 
     * @return subtotal
     */
    public java.lang.Double getSubtotal() {
        return subtotal;
    }


    /**
     * Sets the subtotal value for this ShoppingCartInfoEntity.
     * 
     * @param subtotal
     */
    public void setSubtotal(java.lang.Double subtotal) {
        this.subtotal = subtotal;
    }


    /**
     * Gets the base_subtotal value for this ShoppingCartInfoEntity.
     * 
     * @return base_subtotal
     */
    public java.lang.Double getBase_subtotal() {
        return base_subtotal;
    }


    /**
     * Sets the base_subtotal value for this ShoppingCartInfoEntity.
     * 
     * @param base_subtotal
     */
    public void setBase_subtotal(java.lang.Double base_subtotal) {
        this.base_subtotal = base_subtotal;
    }


    /**
     * Gets the subtotal_with_discount value for this ShoppingCartInfoEntity.
     * 
     * @return subtotal_with_discount
     */
    public java.lang.Double getSubtotal_with_discount() {
        return subtotal_with_discount;
    }


    /**
     * Sets the subtotal_with_discount value for this ShoppingCartInfoEntity.
     * 
     * @param subtotal_with_discount
     */
    public void setSubtotal_with_discount(java.lang.Double subtotal_with_discount) {
        this.subtotal_with_discount = subtotal_with_discount;
    }


    /**
     * Gets the base_subtotal_with_discount value for this ShoppingCartInfoEntity.
     * 
     * @return base_subtotal_with_discount
     */
    public java.lang.Double getBase_subtotal_with_discount() {
        return base_subtotal_with_discount;
    }


    /**
     * Sets the base_subtotal_with_discount value for this ShoppingCartInfoEntity.
     * 
     * @param base_subtotal_with_discount
     */
    public void setBase_subtotal_with_discount(java.lang.Double base_subtotal_with_discount) {
        this.base_subtotal_with_discount = base_subtotal_with_discount;
    }


    /**
     * Gets the ext_shipping_info value for this ShoppingCartInfoEntity.
     * 
     * @return ext_shipping_info
     */
    public java.lang.String getExt_shipping_info() {
        return ext_shipping_info;
    }


    /**
     * Sets the ext_shipping_info value for this ShoppingCartInfoEntity.
     * 
     * @param ext_shipping_info
     */
    public void setExt_shipping_info(java.lang.String ext_shipping_info) {
        this.ext_shipping_info = ext_shipping_info;
    }


    /**
     * Gets the gift_message_id value for this ShoppingCartInfoEntity.
     * 
     * @return gift_message_id
     */
    public java.lang.String getGift_message_id() {
        return gift_message_id;
    }


    /**
     * Sets the gift_message_id value for this ShoppingCartInfoEntity.
     * 
     * @param gift_message_id
     */
    public void setGift_message_id(java.lang.String gift_message_id) {
        this.gift_message_id = gift_message_id;
    }


    /**
     * Gets the gift_message value for this ShoppingCartInfoEntity.
     * 
     * @return gift_message
     */
    public java.lang.String getGift_message() {
        return gift_message;
    }


    /**
     * Sets the gift_message value for this ShoppingCartInfoEntity.
     * 
     * @param gift_message
     */
    public void setGift_message(java.lang.String gift_message) {
        this.gift_message = gift_message;
    }


    /**
     * Gets the customer_balance_amount_used value for this ShoppingCartInfoEntity.
     * 
     * @return customer_balance_amount_used
     */
    public java.lang.Double getCustomer_balance_amount_used() {
        return customer_balance_amount_used;
    }


    /**
     * Sets the customer_balance_amount_used value for this ShoppingCartInfoEntity.
     * 
     * @param customer_balance_amount_used
     */
    public void setCustomer_balance_amount_used(java.lang.Double customer_balance_amount_used) {
        this.customer_balance_amount_used = customer_balance_amount_used;
    }


    /**
     * Gets the base_customer_balance_amount_used value for this ShoppingCartInfoEntity.
     * 
     * @return base_customer_balance_amount_used
     */
    public java.lang.Double getBase_customer_balance_amount_used() {
        return base_customer_balance_amount_used;
    }


    /**
     * Sets the base_customer_balance_amount_used value for this ShoppingCartInfoEntity.
     * 
     * @param base_customer_balance_amount_used
     */
    public void setBase_customer_balance_amount_used(java.lang.Double base_customer_balance_amount_used) {
        this.base_customer_balance_amount_used = base_customer_balance_amount_used;
    }


    /**
     * Gets the use_customer_balance value for this ShoppingCartInfoEntity.
     * 
     * @return use_customer_balance
     */
    public java.lang.String getUse_customer_balance() {
        return use_customer_balance;
    }


    /**
     * Sets the use_customer_balance value for this ShoppingCartInfoEntity.
     * 
     * @param use_customer_balance
     */
    public void setUse_customer_balance(java.lang.String use_customer_balance) {
        this.use_customer_balance = use_customer_balance;
    }


    /**
     * Gets the gift_cards_amount value for this ShoppingCartInfoEntity.
     * 
     * @return gift_cards_amount
     */
    public java.lang.String getGift_cards_amount() {
        return gift_cards_amount;
    }


    /**
     * Sets the gift_cards_amount value for this ShoppingCartInfoEntity.
     * 
     * @param gift_cards_amount
     */
    public void setGift_cards_amount(java.lang.String gift_cards_amount) {
        this.gift_cards_amount = gift_cards_amount;
    }


    /**
     * Gets the base_gift_cards_amount value for this ShoppingCartInfoEntity.
     * 
     * @return base_gift_cards_amount
     */
    public java.lang.String getBase_gift_cards_amount() {
        return base_gift_cards_amount;
    }


    /**
     * Sets the base_gift_cards_amount value for this ShoppingCartInfoEntity.
     * 
     * @param base_gift_cards_amount
     */
    public void setBase_gift_cards_amount(java.lang.String base_gift_cards_amount) {
        this.base_gift_cards_amount = base_gift_cards_amount;
    }


    /**
     * Gets the gift_cards_amount_used value for this ShoppingCartInfoEntity.
     * 
     * @return gift_cards_amount_used
     */
    public java.lang.String getGift_cards_amount_used() {
        return gift_cards_amount_used;
    }


    /**
     * Sets the gift_cards_amount_used value for this ShoppingCartInfoEntity.
     * 
     * @param gift_cards_amount_used
     */
    public void setGift_cards_amount_used(java.lang.String gift_cards_amount_used) {
        this.gift_cards_amount_used = gift_cards_amount_used;
    }


    /**
     * Gets the use_reward_points value for this ShoppingCartInfoEntity.
     * 
     * @return use_reward_points
     */
    public java.lang.String getUse_reward_points() {
        return use_reward_points;
    }


    /**
     * Sets the use_reward_points value for this ShoppingCartInfoEntity.
     * 
     * @param use_reward_points
     */
    public void setUse_reward_points(java.lang.String use_reward_points) {
        this.use_reward_points = use_reward_points;
    }


    /**
     * Gets the reward_points_balance value for this ShoppingCartInfoEntity.
     * 
     * @return reward_points_balance
     */
    public java.lang.String getReward_points_balance() {
        return reward_points_balance;
    }


    /**
     * Sets the reward_points_balance value for this ShoppingCartInfoEntity.
     * 
     * @param reward_points_balance
     */
    public void setReward_points_balance(java.lang.String reward_points_balance) {
        this.reward_points_balance = reward_points_balance;
    }


    /**
     * Gets the base_reward_currency_amount value for this ShoppingCartInfoEntity.
     * 
     * @return base_reward_currency_amount
     */
    public java.lang.String getBase_reward_currency_amount() {
        return base_reward_currency_amount;
    }


    /**
     * Sets the base_reward_currency_amount value for this ShoppingCartInfoEntity.
     * 
     * @param base_reward_currency_amount
     */
    public void setBase_reward_currency_amount(java.lang.String base_reward_currency_amount) {
        this.base_reward_currency_amount = base_reward_currency_amount;
    }


    /**
     * Gets the reward_currency_amount value for this ShoppingCartInfoEntity.
     * 
     * @return reward_currency_amount
     */
    public java.lang.String getReward_currency_amount() {
        return reward_currency_amount;
    }


    /**
     * Sets the reward_currency_amount value for this ShoppingCartInfoEntity.
     * 
     * @param reward_currency_amount
     */
    public void setReward_currency_amount(java.lang.String reward_currency_amount) {
        this.reward_currency_amount = reward_currency_amount;
    }


    /**
     * Gets the shipping_address value for this ShoppingCartInfoEntity.
     * 
     * @return shipping_address
     */
    public ShoppingCartAddressEntity getShipping_address() {
        return shipping_address;
    }


    /**
     * Sets the shipping_address value for this ShoppingCartInfoEntity.
     * 
     * @param shipping_address
     */
    public void setShipping_address(ShoppingCartAddressEntity shipping_address) {
        this.shipping_address = shipping_address;
    }


    /**
     * Gets the billing_address value for this ShoppingCartInfoEntity.
     * 
     * @return billing_address
     */
    public ShoppingCartAddressEntity getBilling_address() {
        return billing_address;
    }


    /**
     * Sets the billing_address value for this ShoppingCartInfoEntity.
     * 
     * @param billing_address
     */
    public void setBilling_address(ShoppingCartAddressEntity billing_address) {
        this.billing_address = billing_address;
    }


    /**
     * Gets the items value for this ShoppingCartInfoEntity.
     * 
     * @return items
     */
    public ShoppingCartItemEntity[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this ShoppingCartInfoEntity.
     * 
     * @param items
     */
    public void setItems(ShoppingCartItemEntity[] items) {
        this.items = items;
    }


    /**
     * Gets the payment value for this ShoppingCartInfoEntity.
     * 
     * @return payment
     */
    public ShoppingCartPaymentEntity getPayment() {
        return payment;
    }


    /**
     * Sets the payment value for this ShoppingCartInfoEntity.
     * 
     * @param payment
     */
    public void setPayment(ShoppingCartPaymentEntity payment) {
        this.payment = payment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartInfoEntity)) return false;
        ShoppingCartInfoEntity other = (ShoppingCartInfoEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.store_id==null && other.getStore_id()==null) || 
             (this.store_id!=null &&
              this.store_id.equals(other.getStore_id()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
            ((this.converted_at==null && other.getConverted_at()==null) || 
             (this.converted_at!=null &&
              this.converted_at.equals(other.getConverted_at()))) &&
            ((this.quote_id==null && other.getQuote_id()==null) || 
             (this.quote_id!=null &&
              this.quote_id.equals(other.getQuote_id()))) &&
            ((this.is_active==null && other.getIs_active()==null) || 
             (this.is_active!=null &&
              this.is_active.equals(other.getIs_active()))) &&
            ((this.is_virtual==null && other.getIs_virtual()==null) || 
             (this.is_virtual!=null &&
              this.is_virtual.equals(other.getIs_virtual()))) &&
            ((this.is_multi_shipping==null && other.getIs_multi_shipping()==null) || 
             (this.is_multi_shipping!=null &&
              this.is_multi_shipping.equals(other.getIs_multi_shipping()))) &&
            ((this.items_count==null && other.getItems_count()==null) || 
             (this.items_count!=null &&
              this.items_count.equals(other.getItems_count()))) &&
            ((this.items_qty==null && other.getItems_qty()==null) || 
             (this.items_qty!=null &&
              this.items_qty.equals(other.getItems_qty()))) &&
            ((this.orig_order_id==null && other.getOrig_order_id()==null) || 
             (this.orig_order_id!=null &&
              this.orig_order_id.equals(other.getOrig_order_id()))) &&
            ((this.store_to_base_rate==null && other.getStore_to_base_rate()==null) || 
             (this.store_to_base_rate!=null &&
              this.store_to_base_rate.equals(other.getStore_to_base_rate()))) &&
            ((this.store_to_quote_rate==null && other.getStore_to_quote_rate()==null) || 
             (this.store_to_quote_rate!=null &&
              this.store_to_quote_rate.equals(other.getStore_to_quote_rate()))) &&
            ((this.base_currency_code==null && other.getBase_currency_code()==null) || 
             (this.base_currency_code!=null &&
              this.base_currency_code.equals(other.getBase_currency_code()))) &&
            ((this.store_currency_code==null && other.getStore_currency_code()==null) || 
             (this.store_currency_code!=null &&
              this.store_currency_code.equals(other.getStore_currency_code()))) &&
            ((this.quote_currency_code==null && other.getQuote_currency_code()==null) || 
             (this.quote_currency_code!=null &&
              this.quote_currency_code.equals(other.getQuote_currency_code()))) &&
            ((this.grand_total==null && other.getGrand_total()==null) || 
             (this.grand_total!=null &&
              this.grand_total.equals(other.getGrand_total()))) &&
            ((this.base_grand_total==null && other.getBase_grand_total()==null) || 
             (this.base_grand_total!=null &&
              this.base_grand_total.equals(other.getBase_grand_total()))) &&
            ((this.checkout_method==null && other.getCheckout_method()==null) || 
             (this.checkout_method!=null &&
              this.checkout_method.equals(other.getCheckout_method()))) &&
            ((this.customer_id==null && other.getCustomer_id()==null) || 
             (this.customer_id!=null &&
              this.customer_id.equals(other.getCustomer_id()))) &&
            ((this.customer_tax_class_id==null && other.getCustomer_tax_class_id()==null) || 
             (this.customer_tax_class_id!=null &&
              this.customer_tax_class_id.equals(other.getCustomer_tax_class_id()))) &&
            ((this.customer_group_id==null && other.getCustomer_group_id()==null) || 
             (this.customer_group_id!=null &&
              this.customer_group_id.equals(other.getCustomer_group_id()))) &&
            ((this.customer_email==null && other.getCustomer_email()==null) || 
             (this.customer_email!=null &&
              this.customer_email.equals(other.getCustomer_email()))) &&
            ((this.customer_prefix==null && other.getCustomer_prefix()==null) || 
             (this.customer_prefix!=null &&
              this.customer_prefix.equals(other.getCustomer_prefix()))) &&
            ((this.customer_firstname==null && other.getCustomer_firstname()==null) || 
             (this.customer_firstname!=null &&
              this.customer_firstname.equals(other.getCustomer_firstname()))) &&
            ((this.customer_middlename==null && other.getCustomer_middlename()==null) || 
             (this.customer_middlename!=null &&
              this.customer_middlename.equals(other.getCustomer_middlename()))) &&
            ((this.customer_lastname==null && other.getCustomer_lastname()==null) || 
             (this.customer_lastname!=null &&
              this.customer_lastname.equals(other.getCustomer_lastname()))) &&
            ((this.customer_suffix==null && other.getCustomer_suffix()==null) || 
             (this.customer_suffix!=null &&
              this.customer_suffix.equals(other.getCustomer_suffix()))) &&
            ((this.customer_note==null && other.getCustomer_note()==null) || 
             (this.customer_note!=null &&
              this.customer_note.equals(other.getCustomer_note()))) &&
            ((this.customer_note_notify==null && other.getCustomer_note_notify()==null) || 
             (this.customer_note_notify!=null &&
              this.customer_note_notify.equals(other.getCustomer_note_notify()))) &&
            ((this.customer_is_guest==null && other.getCustomer_is_guest()==null) || 
             (this.customer_is_guest!=null &&
              this.customer_is_guest.equals(other.getCustomer_is_guest()))) &&
            ((this.applied_rule_ids==null && other.getApplied_rule_ids()==null) || 
             (this.applied_rule_ids!=null &&
              this.applied_rule_ids.equals(other.getApplied_rule_ids()))) &&
            ((this.reserved_order_id==null && other.getReserved_order_id()==null) || 
             (this.reserved_order_id!=null &&
              this.reserved_order_id.equals(other.getReserved_order_id()))) &&
            ((this.password_hash==null && other.getPassword_hash()==null) || 
             (this.password_hash!=null &&
              this.password_hash.equals(other.getPassword_hash()))) &&
            ((this.coupon_code==null && other.getCoupon_code()==null) || 
             (this.coupon_code!=null &&
              this.coupon_code.equals(other.getCoupon_code()))) &&
            ((this.global_currency_code==null && other.getGlobal_currency_code()==null) || 
             (this.global_currency_code!=null &&
              this.global_currency_code.equals(other.getGlobal_currency_code()))) &&
            ((this.base_to_global_rate==null && other.getBase_to_global_rate()==null) || 
             (this.base_to_global_rate!=null &&
              this.base_to_global_rate.equals(other.getBase_to_global_rate()))) &&
            ((this.base_to_quote_rate==null && other.getBase_to_quote_rate()==null) || 
             (this.base_to_quote_rate!=null &&
              this.base_to_quote_rate.equals(other.getBase_to_quote_rate()))) &&
            ((this.customer_taxvat==null && other.getCustomer_taxvat()==null) || 
             (this.customer_taxvat!=null &&
              this.customer_taxvat.equals(other.getCustomer_taxvat()))) &&
            ((this.customer_gender==null && other.getCustomer_gender()==null) || 
             (this.customer_gender!=null &&
              this.customer_gender.equals(other.getCustomer_gender()))) &&
            ((this.subtotal==null && other.getSubtotal()==null) || 
             (this.subtotal!=null &&
              this.subtotal.equals(other.getSubtotal()))) &&
            ((this.base_subtotal==null && other.getBase_subtotal()==null) || 
             (this.base_subtotal!=null &&
              this.base_subtotal.equals(other.getBase_subtotal()))) &&
            ((this.subtotal_with_discount==null && other.getSubtotal_with_discount()==null) || 
             (this.subtotal_with_discount!=null &&
              this.subtotal_with_discount.equals(other.getSubtotal_with_discount()))) &&
            ((this.base_subtotal_with_discount==null && other.getBase_subtotal_with_discount()==null) || 
             (this.base_subtotal_with_discount!=null &&
              this.base_subtotal_with_discount.equals(other.getBase_subtotal_with_discount()))) &&
            ((this.ext_shipping_info==null && other.getExt_shipping_info()==null) || 
             (this.ext_shipping_info!=null &&
              this.ext_shipping_info.equals(other.getExt_shipping_info()))) &&
            ((this.gift_message_id==null && other.getGift_message_id()==null) || 
             (this.gift_message_id!=null &&
              this.gift_message_id.equals(other.getGift_message_id()))) &&
            ((this.gift_message==null && other.getGift_message()==null) || 
             (this.gift_message!=null &&
              this.gift_message.equals(other.getGift_message()))) &&
            ((this.customer_balance_amount_used==null && other.getCustomer_balance_amount_used()==null) || 
             (this.customer_balance_amount_used!=null &&
              this.customer_balance_amount_used.equals(other.getCustomer_balance_amount_used()))) &&
            ((this.base_customer_balance_amount_used==null && other.getBase_customer_balance_amount_used()==null) || 
             (this.base_customer_balance_amount_used!=null &&
              this.base_customer_balance_amount_used.equals(other.getBase_customer_balance_amount_used()))) &&
            ((this.use_customer_balance==null && other.getUse_customer_balance()==null) || 
             (this.use_customer_balance!=null &&
              this.use_customer_balance.equals(other.getUse_customer_balance()))) &&
            ((this.gift_cards_amount==null && other.getGift_cards_amount()==null) || 
             (this.gift_cards_amount!=null &&
              this.gift_cards_amount.equals(other.getGift_cards_amount()))) &&
            ((this.base_gift_cards_amount==null && other.getBase_gift_cards_amount()==null) || 
             (this.base_gift_cards_amount!=null &&
              this.base_gift_cards_amount.equals(other.getBase_gift_cards_amount()))) &&
            ((this.gift_cards_amount_used==null && other.getGift_cards_amount_used()==null) || 
             (this.gift_cards_amount_used!=null &&
              this.gift_cards_amount_used.equals(other.getGift_cards_amount_used()))) &&
            ((this.use_reward_points==null && other.getUse_reward_points()==null) || 
             (this.use_reward_points!=null &&
              this.use_reward_points.equals(other.getUse_reward_points()))) &&
            ((this.reward_points_balance==null && other.getReward_points_balance()==null) || 
             (this.reward_points_balance!=null &&
              this.reward_points_balance.equals(other.getReward_points_balance()))) &&
            ((this.base_reward_currency_amount==null && other.getBase_reward_currency_amount()==null) || 
             (this.base_reward_currency_amount!=null &&
              this.base_reward_currency_amount.equals(other.getBase_reward_currency_amount()))) &&
            ((this.reward_currency_amount==null && other.getReward_currency_amount()==null) || 
             (this.reward_currency_amount!=null &&
              this.reward_currency_amount.equals(other.getReward_currency_amount()))) &&
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
              this.payment.equals(other.getPayment())));
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
        if (getStore_id() != null) {
            _hashCode += getStore_id().hashCode();
        }
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getUpdated_at() != null) {
            _hashCode += getUpdated_at().hashCode();
        }
        if (getConverted_at() != null) {
            _hashCode += getConverted_at().hashCode();
        }
        if (getQuote_id() != null) {
            _hashCode += getQuote_id().hashCode();
        }
        if (getIs_active() != null) {
            _hashCode += getIs_active().hashCode();
        }
        if (getIs_virtual() != null) {
            _hashCode += getIs_virtual().hashCode();
        }
        if (getIs_multi_shipping() != null) {
            _hashCode += getIs_multi_shipping().hashCode();
        }
        if (getItems_count() != null) {
            _hashCode += getItems_count().hashCode();
        }
        if (getItems_qty() != null) {
            _hashCode += getItems_qty().hashCode();
        }
        if (getOrig_order_id() != null) {
            _hashCode += getOrig_order_id().hashCode();
        }
        if (getStore_to_base_rate() != null) {
            _hashCode += getStore_to_base_rate().hashCode();
        }
        if (getStore_to_quote_rate() != null) {
            _hashCode += getStore_to_quote_rate().hashCode();
        }
        if (getBase_currency_code() != null) {
            _hashCode += getBase_currency_code().hashCode();
        }
        if (getStore_currency_code() != null) {
            _hashCode += getStore_currency_code().hashCode();
        }
        if (getQuote_currency_code() != null) {
            _hashCode += getQuote_currency_code().hashCode();
        }
        if (getGrand_total() != null) {
            _hashCode += getGrand_total().hashCode();
        }
        if (getBase_grand_total() != null) {
            _hashCode += getBase_grand_total().hashCode();
        }
        if (getCheckout_method() != null) {
            _hashCode += getCheckout_method().hashCode();
        }
        if (getCustomer_id() != null) {
            _hashCode += getCustomer_id().hashCode();
        }
        if (getCustomer_tax_class_id() != null) {
            _hashCode += getCustomer_tax_class_id().hashCode();
        }
        if (getCustomer_group_id() != null) {
            _hashCode += getCustomer_group_id().hashCode();
        }
        if (getCustomer_email() != null) {
            _hashCode += getCustomer_email().hashCode();
        }
        if (getCustomer_prefix() != null) {
            _hashCode += getCustomer_prefix().hashCode();
        }
        if (getCustomer_firstname() != null) {
            _hashCode += getCustomer_firstname().hashCode();
        }
        if (getCustomer_middlename() != null) {
            _hashCode += getCustomer_middlename().hashCode();
        }
        if (getCustomer_lastname() != null) {
            _hashCode += getCustomer_lastname().hashCode();
        }
        if (getCustomer_suffix() != null) {
            _hashCode += getCustomer_suffix().hashCode();
        }
        if (getCustomer_note() != null) {
            _hashCode += getCustomer_note().hashCode();
        }
        if (getCustomer_note_notify() != null) {
            _hashCode += getCustomer_note_notify().hashCode();
        }
        if (getCustomer_is_guest() != null) {
            _hashCode += getCustomer_is_guest().hashCode();
        }
        if (getApplied_rule_ids() != null) {
            _hashCode += getApplied_rule_ids().hashCode();
        }
        if (getReserved_order_id() != null) {
            _hashCode += getReserved_order_id().hashCode();
        }
        if (getPassword_hash() != null) {
            _hashCode += getPassword_hash().hashCode();
        }
        if (getCoupon_code() != null) {
            _hashCode += getCoupon_code().hashCode();
        }
        if (getGlobal_currency_code() != null) {
            _hashCode += getGlobal_currency_code().hashCode();
        }
        if (getBase_to_global_rate() != null) {
            _hashCode += getBase_to_global_rate().hashCode();
        }
        if (getBase_to_quote_rate() != null) {
            _hashCode += getBase_to_quote_rate().hashCode();
        }
        if (getCustomer_taxvat() != null) {
            _hashCode += getCustomer_taxvat().hashCode();
        }
        if (getCustomer_gender() != null) {
            _hashCode += getCustomer_gender().hashCode();
        }
        if (getSubtotal() != null) {
            _hashCode += getSubtotal().hashCode();
        }
        if (getBase_subtotal() != null) {
            _hashCode += getBase_subtotal().hashCode();
        }
        if (getSubtotal_with_discount() != null) {
            _hashCode += getSubtotal_with_discount().hashCode();
        }
        if (getBase_subtotal_with_discount() != null) {
            _hashCode += getBase_subtotal_with_discount().hashCode();
        }
        if (getExt_shipping_info() != null) {
            _hashCode += getExt_shipping_info().hashCode();
        }
        if (getGift_message_id() != null) {
            _hashCode += getGift_message_id().hashCode();
        }
        if (getGift_message() != null) {
            _hashCode += getGift_message().hashCode();
        }
        if (getCustomer_balance_amount_used() != null) {
            _hashCode += getCustomer_balance_amount_used().hashCode();
        }
        if (getBase_customer_balance_amount_used() != null) {
            _hashCode += getBase_customer_balance_amount_used().hashCode();
        }
        if (getUse_customer_balance() != null) {
            _hashCode += getUse_customer_balance().hashCode();
        }
        if (getGift_cards_amount() != null) {
            _hashCode += getGift_cards_amount().hashCode();
        }
        if (getBase_gift_cards_amount() != null) {
            _hashCode += getBase_gift_cards_amount().hashCode();
        }
        if (getGift_cards_amount_used() != null) {
            _hashCode += getGift_cards_amount_used().hashCode();
        }
        if (getUse_reward_points() != null) {
            _hashCode += getUse_reward_points().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartInfoEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartInfoEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("converted_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "converted_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quote_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quote_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_active");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_virtual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_virtual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_multi_shipping");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_multi_shipping"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items_count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items_count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items_qty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items_qty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orig_order_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orig_order_id"));
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
        elemField.setFieldName("store_to_quote_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_to_quote_rate"));
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
        elemField.setFieldName("quote_currency_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quote_currency_code"));
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
        elemField.setFieldName("base_grand_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_grand_total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkout_method");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkout_method"));
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
        elemField.setFieldName("customer_tax_class_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_tax_class_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_group_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_group_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("customer_prefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_prefix"));
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
        elemField.setFieldName("customer_middlename");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_middlename"));
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
        elemField.setFieldName("customer_suffix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_suffix"));
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
        elemField.setFieldName("applied_rule_ids");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applied_rule_ids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reserved_order_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reserved_order_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password_hash");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password_hash"));
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
        elemField.setFieldName("global_currency_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "global_currency_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_to_global_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_to_global_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_to_quote_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_to_quote_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
        elemField.setFieldName("customer_gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_subtotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subtotal_with_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal_with_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_subtotal_with_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_subtotal_with_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ext_shipping_info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ext_shipping_info"));
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
        elemField.setFieldName("customer_balance_amount_used");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_balance_amount_used"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_customer_balance_amount_used");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_customer_balance_amount_used"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("use_customer_balance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "use_customer_balance"));
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
        elemField.setFieldName("base_gift_cards_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_gift_cards_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gift_cards_amount_used");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_cards_amount_used"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("use_reward_points");
        elemField.setXmlName(new javax.xml.namespace.QName("", "use_reward_points"));
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
        elemField.setFieldName("shipping_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartAddressEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billing_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billing_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartAddressEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartItemEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payment"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartPaymentEntity"));
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
