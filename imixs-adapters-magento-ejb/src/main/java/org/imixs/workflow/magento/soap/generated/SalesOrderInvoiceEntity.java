/**
 * SalesOrderInvoiceEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderInvoiceEntity  implements java.io.Serializable {
    private java.lang.String increment_id;

    private java.lang.String parent_id;

    private java.lang.String store_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String is_active;

    private java.lang.String global_currency_code;

    private java.lang.String base_currency_code;

    private java.lang.String store_currency_code;

    private java.lang.String order_currency_code;

    private java.lang.String store_to_base_rate;

    private java.lang.String store_to_order_rate;

    private java.lang.String base_to_global_rate;

    private java.lang.String base_to_order_rate;

    private java.lang.String subtotal;

    private java.lang.String base_subtotal;

    private java.lang.String base_grand_total;

    private java.lang.String discount_amount;

    private java.lang.String base_discount_amount;

    private java.lang.String shipping_amount;

    private java.lang.String base_shipping_amount;

    private java.lang.String tax_amount;

    private java.lang.String base_tax_amount;

    private java.lang.String billing_address_id;

    private java.lang.String billing_firstname;

    private java.lang.String billing_lastname;

    private java.lang.String order_id;

    private java.lang.String order_increment_id;

    private java.lang.String order_created_at;

    private java.lang.String state;

    private java.lang.String grand_total;

    private java.lang.String invoice_id;

    private SalesOrderInvoiceItemEntity[] items;

    private SalesOrderInvoiceCommentEntity[] comments;

    public SalesOrderInvoiceEntity() {
    }

    public SalesOrderInvoiceEntity(
           java.lang.String increment_id,
           java.lang.String parent_id,
           java.lang.String store_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String is_active,
           java.lang.String global_currency_code,
           java.lang.String base_currency_code,
           java.lang.String store_currency_code,
           java.lang.String order_currency_code,
           java.lang.String store_to_base_rate,
           java.lang.String store_to_order_rate,
           java.lang.String base_to_global_rate,
           java.lang.String base_to_order_rate,
           java.lang.String subtotal,
           java.lang.String base_subtotal,
           java.lang.String base_grand_total,
           java.lang.String discount_amount,
           java.lang.String base_discount_amount,
           java.lang.String shipping_amount,
           java.lang.String base_shipping_amount,
           java.lang.String tax_amount,
           java.lang.String base_tax_amount,
           java.lang.String billing_address_id,
           java.lang.String billing_firstname,
           java.lang.String billing_lastname,
           java.lang.String order_id,
           java.lang.String order_increment_id,
           java.lang.String order_created_at,
           java.lang.String state,
           java.lang.String grand_total,
           java.lang.String invoice_id,
           SalesOrderInvoiceItemEntity[] items,
           SalesOrderInvoiceCommentEntity[] comments) {
           this.increment_id = increment_id;
           this.parent_id = parent_id;
           this.store_id = store_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.is_active = is_active;
           this.global_currency_code = global_currency_code;
           this.base_currency_code = base_currency_code;
           this.store_currency_code = store_currency_code;
           this.order_currency_code = order_currency_code;
           this.store_to_base_rate = store_to_base_rate;
           this.store_to_order_rate = store_to_order_rate;
           this.base_to_global_rate = base_to_global_rate;
           this.base_to_order_rate = base_to_order_rate;
           this.subtotal = subtotal;
           this.base_subtotal = base_subtotal;
           this.base_grand_total = base_grand_total;
           this.discount_amount = discount_amount;
           this.base_discount_amount = base_discount_amount;
           this.shipping_amount = shipping_amount;
           this.base_shipping_amount = base_shipping_amount;
           this.tax_amount = tax_amount;
           this.base_tax_amount = base_tax_amount;
           this.billing_address_id = billing_address_id;
           this.billing_firstname = billing_firstname;
           this.billing_lastname = billing_lastname;
           this.order_id = order_id;
           this.order_increment_id = order_increment_id;
           this.order_created_at = order_created_at;
           this.state = state;
           this.grand_total = grand_total;
           this.invoice_id = invoice_id;
           this.items = items;
           this.comments = comments;
    }


    /**
     * Gets the increment_id value for this SalesOrderInvoiceEntity.
     * 
     * @return increment_id
     */
    public java.lang.String getIncrement_id() {
        return increment_id;
    }


    /**
     * Sets the increment_id value for this SalesOrderInvoiceEntity.
     * 
     * @param increment_id
     */
    public void setIncrement_id(java.lang.String increment_id) {
        this.increment_id = increment_id;
    }


    /**
     * Gets the parent_id value for this SalesOrderInvoiceEntity.
     * 
     * @return parent_id
     */
    public java.lang.String getParent_id() {
        return parent_id;
    }


    /**
     * Sets the parent_id value for this SalesOrderInvoiceEntity.
     * 
     * @param parent_id
     */
    public void setParent_id(java.lang.String parent_id) {
        this.parent_id = parent_id;
    }


    /**
     * Gets the store_id value for this SalesOrderInvoiceEntity.
     * 
     * @return store_id
     */
    public java.lang.String getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this SalesOrderInvoiceEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.String store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the created_at value for this SalesOrderInvoiceEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderInvoiceEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this SalesOrderInvoiceEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderInvoiceEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the is_active value for this SalesOrderInvoiceEntity.
     * 
     * @return is_active
     */
    public java.lang.String getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this SalesOrderInvoiceEntity.
     * 
     * @param is_active
     */
    public void setIs_active(java.lang.String is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the global_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @return global_currency_code
     */
    public java.lang.String getGlobal_currency_code() {
        return global_currency_code;
    }


    /**
     * Sets the global_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @param global_currency_code
     */
    public void setGlobal_currency_code(java.lang.String global_currency_code) {
        this.global_currency_code = global_currency_code;
    }


    /**
     * Gets the base_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @return base_currency_code
     */
    public java.lang.String getBase_currency_code() {
        return base_currency_code;
    }


    /**
     * Sets the base_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @param base_currency_code
     */
    public void setBase_currency_code(java.lang.String base_currency_code) {
        this.base_currency_code = base_currency_code;
    }


    /**
     * Gets the store_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @return store_currency_code
     */
    public java.lang.String getStore_currency_code() {
        return store_currency_code;
    }


    /**
     * Sets the store_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @param store_currency_code
     */
    public void setStore_currency_code(java.lang.String store_currency_code) {
        this.store_currency_code = store_currency_code;
    }


    /**
     * Gets the order_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @return order_currency_code
     */
    public java.lang.String getOrder_currency_code() {
        return order_currency_code;
    }


    /**
     * Sets the order_currency_code value for this SalesOrderInvoiceEntity.
     * 
     * @param order_currency_code
     */
    public void setOrder_currency_code(java.lang.String order_currency_code) {
        this.order_currency_code = order_currency_code;
    }


    /**
     * Gets the store_to_base_rate value for this SalesOrderInvoiceEntity.
     * 
     * @return store_to_base_rate
     */
    public java.lang.String getStore_to_base_rate() {
        return store_to_base_rate;
    }


    /**
     * Sets the store_to_base_rate value for this SalesOrderInvoiceEntity.
     * 
     * @param store_to_base_rate
     */
    public void setStore_to_base_rate(java.lang.String store_to_base_rate) {
        this.store_to_base_rate = store_to_base_rate;
    }


    /**
     * Gets the store_to_order_rate value for this SalesOrderInvoiceEntity.
     * 
     * @return store_to_order_rate
     */
    public java.lang.String getStore_to_order_rate() {
        return store_to_order_rate;
    }


    /**
     * Sets the store_to_order_rate value for this SalesOrderInvoiceEntity.
     * 
     * @param store_to_order_rate
     */
    public void setStore_to_order_rate(java.lang.String store_to_order_rate) {
        this.store_to_order_rate = store_to_order_rate;
    }


    /**
     * Gets the base_to_global_rate value for this SalesOrderInvoiceEntity.
     * 
     * @return base_to_global_rate
     */
    public java.lang.String getBase_to_global_rate() {
        return base_to_global_rate;
    }


    /**
     * Sets the base_to_global_rate value for this SalesOrderInvoiceEntity.
     * 
     * @param base_to_global_rate
     */
    public void setBase_to_global_rate(java.lang.String base_to_global_rate) {
        this.base_to_global_rate = base_to_global_rate;
    }


    /**
     * Gets the base_to_order_rate value for this SalesOrderInvoiceEntity.
     * 
     * @return base_to_order_rate
     */
    public java.lang.String getBase_to_order_rate() {
        return base_to_order_rate;
    }


    /**
     * Sets the base_to_order_rate value for this SalesOrderInvoiceEntity.
     * 
     * @param base_to_order_rate
     */
    public void setBase_to_order_rate(java.lang.String base_to_order_rate) {
        this.base_to_order_rate = base_to_order_rate;
    }


    /**
     * Gets the subtotal value for this SalesOrderInvoiceEntity.
     * 
     * @return subtotal
     */
    public java.lang.String getSubtotal() {
        return subtotal;
    }


    /**
     * Sets the subtotal value for this SalesOrderInvoiceEntity.
     * 
     * @param subtotal
     */
    public void setSubtotal(java.lang.String subtotal) {
        this.subtotal = subtotal;
    }


    /**
     * Gets the base_subtotal value for this SalesOrderInvoiceEntity.
     * 
     * @return base_subtotal
     */
    public java.lang.String getBase_subtotal() {
        return base_subtotal;
    }


    /**
     * Sets the base_subtotal value for this SalesOrderInvoiceEntity.
     * 
     * @param base_subtotal
     */
    public void setBase_subtotal(java.lang.String base_subtotal) {
        this.base_subtotal = base_subtotal;
    }


    /**
     * Gets the base_grand_total value for this SalesOrderInvoiceEntity.
     * 
     * @return base_grand_total
     */
    public java.lang.String getBase_grand_total() {
        return base_grand_total;
    }


    /**
     * Sets the base_grand_total value for this SalesOrderInvoiceEntity.
     * 
     * @param base_grand_total
     */
    public void setBase_grand_total(java.lang.String base_grand_total) {
        this.base_grand_total = base_grand_total;
    }


    /**
     * Gets the discount_amount value for this SalesOrderInvoiceEntity.
     * 
     * @return discount_amount
     */
    public java.lang.String getDiscount_amount() {
        return discount_amount;
    }


    /**
     * Sets the discount_amount value for this SalesOrderInvoiceEntity.
     * 
     * @param discount_amount
     */
    public void setDiscount_amount(java.lang.String discount_amount) {
        this.discount_amount = discount_amount;
    }


    /**
     * Gets the base_discount_amount value for this SalesOrderInvoiceEntity.
     * 
     * @return base_discount_amount
     */
    public java.lang.String getBase_discount_amount() {
        return base_discount_amount;
    }


    /**
     * Sets the base_discount_amount value for this SalesOrderInvoiceEntity.
     * 
     * @param base_discount_amount
     */
    public void setBase_discount_amount(java.lang.String base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }


    /**
     * Gets the shipping_amount value for this SalesOrderInvoiceEntity.
     * 
     * @return shipping_amount
     */
    public java.lang.String getShipping_amount() {
        return shipping_amount;
    }


    /**
     * Sets the shipping_amount value for this SalesOrderInvoiceEntity.
     * 
     * @param shipping_amount
     */
    public void setShipping_amount(java.lang.String shipping_amount) {
        this.shipping_amount = shipping_amount;
    }


    /**
     * Gets the base_shipping_amount value for this SalesOrderInvoiceEntity.
     * 
     * @return base_shipping_amount
     */
    public java.lang.String getBase_shipping_amount() {
        return base_shipping_amount;
    }


    /**
     * Sets the base_shipping_amount value for this SalesOrderInvoiceEntity.
     * 
     * @param base_shipping_amount
     */
    public void setBase_shipping_amount(java.lang.String base_shipping_amount) {
        this.base_shipping_amount = base_shipping_amount;
    }


    /**
     * Gets the tax_amount value for this SalesOrderInvoiceEntity.
     * 
     * @return tax_amount
     */
    public java.lang.String getTax_amount() {
        return tax_amount;
    }


    /**
     * Sets the tax_amount value for this SalesOrderInvoiceEntity.
     * 
     * @param tax_amount
     */
    public void setTax_amount(java.lang.String tax_amount) {
        this.tax_amount = tax_amount;
    }


    /**
     * Gets the base_tax_amount value for this SalesOrderInvoiceEntity.
     * 
     * @return base_tax_amount
     */
    public java.lang.String getBase_tax_amount() {
        return base_tax_amount;
    }


    /**
     * Sets the base_tax_amount value for this SalesOrderInvoiceEntity.
     * 
     * @param base_tax_amount
     */
    public void setBase_tax_amount(java.lang.String base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }


    /**
     * Gets the billing_address_id value for this SalesOrderInvoiceEntity.
     * 
     * @return billing_address_id
     */
    public java.lang.String getBilling_address_id() {
        return billing_address_id;
    }


    /**
     * Sets the billing_address_id value for this SalesOrderInvoiceEntity.
     * 
     * @param billing_address_id
     */
    public void setBilling_address_id(java.lang.String billing_address_id) {
        this.billing_address_id = billing_address_id;
    }


    /**
     * Gets the billing_firstname value for this SalesOrderInvoiceEntity.
     * 
     * @return billing_firstname
     */
    public java.lang.String getBilling_firstname() {
        return billing_firstname;
    }


    /**
     * Sets the billing_firstname value for this SalesOrderInvoiceEntity.
     * 
     * @param billing_firstname
     */
    public void setBilling_firstname(java.lang.String billing_firstname) {
        this.billing_firstname = billing_firstname;
    }


    /**
     * Gets the billing_lastname value for this SalesOrderInvoiceEntity.
     * 
     * @return billing_lastname
     */
    public java.lang.String getBilling_lastname() {
        return billing_lastname;
    }


    /**
     * Sets the billing_lastname value for this SalesOrderInvoiceEntity.
     * 
     * @param billing_lastname
     */
    public void setBilling_lastname(java.lang.String billing_lastname) {
        this.billing_lastname = billing_lastname;
    }


    /**
     * Gets the order_id value for this SalesOrderInvoiceEntity.
     * 
     * @return order_id
     */
    public java.lang.String getOrder_id() {
        return order_id;
    }


    /**
     * Sets the order_id value for this SalesOrderInvoiceEntity.
     * 
     * @param order_id
     */
    public void setOrder_id(java.lang.String order_id) {
        this.order_id = order_id;
    }


    /**
     * Gets the order_increment_id value for this SalesOrderInvoiceEntity.
     * 
     * @return order_increment_id
     */
    public java.lang.String getOrder_increment_id() {
        return order_increment_id;
    }


    /**
     * Sets the order_increment_id value for this SalesOrderInvoiceEntity.
     * 
     * @param order_increment_id
     */
    public void setOrder_increment_id(java.lang.String order_increment_id) {
        this.order_increment_id = order_increment_id;
    }


    /**
     * Gets the order_created_at value for this SalesOrderInvoiceEntity.
     * 
     * @return order_created_at
     */
    public java.lang.String getOrder_created_at() {
        return order_created_at;
    }


    /**
     * Sets the order_created_at value for this SalesOrderInvoiceEntity.
     * 
     * @param order_created_at
     */
    public void setOrder_created_at(java.lang.String order_created_at) {
        this.order_created_at = order_created_at;
    }


    /**
     * Gets the state value for this SalesOrderInvoiceEntity.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this SalesOrderInvoiceEntity.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the grand_total value for this SalesOrderInvoiceEntity.
     * 
     * @return grand_total
     */
    public java.lang.String getGrand_total() {
        return grand_total;
    }


    /**
     * Sets the grand_total value for this SalesOrderInvoiceEntity.
     * 
     * @param grand_total
     */
    public void setGrand_total(java.lang.String grand_total) {
        this.grand_total = grand_total;
    }


    /**
     * Gets the invoice_id value for this SalesOrderInvoiceEntity.
     * 
     * @return invoice_id
     */
    public java.lang.String getInvoice_id() {
        return invoice_id;
    }


    /**
     * Sets the invoice_id value for this SalesOrderInvoiceEntity.
     * 
     * @param invoice_id
     */
    public void setInvoice_id(java.lang.String invoice_id) {
        this.invoice_id = invoice_id;
    }


    /**
     * Gets the items value for this SalesOrderInvoiceEntity.
     * 
     * @return items
     */
    public SalesOrderInvoiceItemEntity[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this SalesOrderInvoiceEntity.
     * 
     * @param items
     */
    public void setItems(SalesOrderInvoiceItemEntity[] items) {
        this.items = items;
    }


    /**
     * Gets the comments value for this SalesOrderInvoiceEntity.
     * 
     * @return comments
     */
    public SalesOrderInvoiceCommentEntity[] getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this SalesOrderInvoiceEntity.
     * 
     * @param comments
     */
    public void setComments(SalesOrderInvoiceCommentEntity[] comments) {
        this.comments = comments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderInvoiceEntity)) return false;
        SalesOrderInvoiceEntity other = (SalesOrderInvoiceEntity) obj;
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
            ((this.subtotal==null && other.getSubtotal()==null) || 
             (this.subtotal!=null &&
              this.subtotal.equals(other.getSubtotal()))) &&
            ((this.base_subtotal==null && other.getBase_subtotal()==null) || 
             (this.base_subtotal!=null &&
              this.base_subtotal.equals(other.getBase_subtotal()))) &&
            ((this.base_grand_total==null && other.getBase_grand_total()==null) || 
             (this.base_grand_total!=null &&
              this.base_grand_total.equals(other.getBase_grand_total()))) &&
            ((this.discount_amount==null && other.getDiscount_amount()==null) || 
             (this.discount_amount!=null &&
              this.discount_amount.equals(other.getDiscount_amount()))) &&
            ((this.base_discount_amount==null && other.getBase_discount_amount()==null) || 
             (this.base_discount_amount!=null &&
              this.base_discount_amount.equals(other.getBase_discount_amount()))) &&
            ((this.shipping_amount==null && other.getShipping_amount()==null) || 
             (this.shipping_amount!=null &&
              this.shipping_amount.equals(other.getShipping_amount()))) &&
            ((this.base_shipping_amount==null && other.getBase_shipping_amount()==null) || 
             (this.base_shipping_amount!=null &&
              this.base_shipping_amount.equals(other.getBase_shipping_amount()))) &&
            ((this.tax_amount==null && other.getTax_amount()==null) || 
             (this.tax_amount!=null &&
              this.tax_amount.equals(other.getTax_amount()))) &&
            ((this.base_tax_amount==null && other.getBase_tax_amount()==null) || 
             (this.base_tax_amount!=null &&
              this.base_tax_amount.equals(other.getBase_tax_amount()))) &&
            ((this.billing_address_id==null && other.getBilling_address_id()==null) || 
             (this.billing_address_id!=null &&
              this.billing_address_id.equals(other.getBilling_address_id()))) &&
            ((this.billing_firstname==null && other.getBilling_firstname()==null) || 
             (this.billing_firstname!=null &&
              this.billing_firstname.equals(other.getBilling_firstname()))) &&
            ((this.billing_lastname==null && other.getBilling_lastname()==null) || 
             (this.billing_lastname!=null &&
              this.billing_lastname.equals(other.getBilling_lastname()))) &&
            ((this.order_id==null && other.getOrder_id()==null) || 
             (this.order_id!=null &&
              this.order_id.equals(other.getOrder_id()))) &&
            ((this.order_increment_id==null && other.getOrder_increment_id()==null) || 
             (this.order_increment_id!=null &&
              this.order_increment_id.equals(other.getOrder_increment_id()))) &&
            ((this.order_created_at==null && other.getOrder_created_at()==null) || 
             (this.order_created_at!=null &&
              this.order_created_at.equals(other.getOrder_created_at()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.grand_total==null && other.getGrand_total()==null) || 
             (this.grand_total!=null &&
              this.grand_total.equals(other.getGrand_total()))) &&
            ((this.invoice_id==null && other.getInvoice_id()==null) || 
             (this.invoice_id!=null &&
              this.invoice_id.equals(other.getInvoice_id()))) &&
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
        if (getSubtotal() != null) {
            _hashCode += getSubtotal().hashCode();
        }
        if (getBase_subtotal() != null) {
            _hashCode += getBase_subtotal().hashCode();
        }
        if (getBase_grand_total() != null) {
            _hashCode += getBase_grand_total().hashCode();
        }
        if (getDiscount_amount() != null) {
            _hashCode += getDiscount_amount().hashCode();
        }
        if (getBase_discount_amount() != null) {
            _hashCode += getBase_discount_amount().hashCode();
        }
        if (getShipping_amount() != null) {
            _hashCode += getShipping_amount().hashCode();
        }
        if (getBase_shipping_amount() != null) {
            _hashCode += getBase_shipping_amount().hashCode();
        }
        if (getTax_amount() != null) {
            _hashCode += getTax_amount().hashCode();
        }
        if (getBase_tax_amount() != null) {
            _hashCode += getBase_tax_amount().hashCode();
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
        if (getOrder_id() != null) {
            _hashCode += getOrder_id().hashCode();
        }
        if (getOrder_increment_id() != null) {
            _hashCode += getOrder_increment_id().hashCode();
        }
        if (getOrder_created_at() != null) {
            _hashCode += getOrder_created_at().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getGrand_total() != null) {
            _hashCode += getGrand_total().hashCode();
        }
        if (getInvoice_id() != null) {
            _hashCode += getInvoice_id().hashCode();
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
        new org.apache.axis.description.TypeDesc(SalesOrderInvoiceEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderInvoiceEntity"));
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
        elemField.setFieldName("subtotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subtotal"));
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
        elemField.setFieldName("discount_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_amount"));
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
        elemField.setFieldName("shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_amount"));
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
        elemField.setFieldName("tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_amount"));
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
        elemField.setFieldName("order_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order_increment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_increment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order_created_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_created_at"));
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
        elemField.setFieldName("grand_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grand_total"));
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
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderInvoiceItemEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderInvoiceCommentEntity"));
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
