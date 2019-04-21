/**
 * SalesOrderItemEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderItemEntity  implements java.io.Serializable {
    private java.lang.String item_id;

    private java.lang.String order_id;

    private java.lang.String quote_item_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String product_id;

    private java.lang.String product_type;

    private java.lang.String product_options;

    private java.lang.String weight;

    private java.lang.String is_virtual;

    private java.lang.String sku;

    private java.lang.String name;

    private java.lang.String applied_rule_ids;

    private java.lang.String free_shipping;

    private java.lang.String is_qty_decimal;

    private java.lang.String no_discount;

    private java.lang.String qty_canceled;

    private java.lang.String qty_invoiced;

    private java.lang.String qty_ordered;

    private java.lang.String qty_refunded;

    private java.lang.String qty_shipped;

    private java.lang.String cost;

    private java.lang.String price;

    private java.lang.String base_price;

    private java.lang.String original_price;

    private java.lang.String base_original_price;

    private java.lang.String tax_percent;

    private java.lang.String tax_amount;

    private java.lang.String base_tax_amount;

    private java.lang.String tax_invoiced;

    private java.lang.String base_tax_invoiced;

    private java.lang.String discount_percent;

    private java.lang.String discount_amount;

    private java.lang.String base_discount_amount;

    private java.lang.String discount_invoiced;

    private java.lang.String base_discount_invoiced;

    private java.lang.String amount_refunded;

    private java.lang.String base_amount_refunded;

    private java.lang.String row_total;

    private java.lang.String base_row_total;

    private java.lang.String row_invoiced;

    private java.lang.String base_row_invoiced;

    private java.lang.String row_weight;

    private java.lang.String gift_message_id;

    private java.lang.String gift_message;

    private java.lang.String gift_message_available;

    private java.lang.String base_tax_before_discount;

    private java.lang.String tax_before_discount;

    private java.lang.String weee_tax_applied;

    private java.lang.String weee_tax_applied_amount;

    private java.lang.String weee_tax_applied_row_amount;

    private java.lang.String base_weee_tax_applied_amount;

    private java.lang.String base_weee_tax_applied_row_amount;

    private java.lang.String weee_tax_disposition;

    private java.lang.String weee_tax_row_disposition;

    private java.lang.String base_weee_tax_disposition;

    private java.lang.String base_weee_tax_row_disposition;

    public SalesOrderItemEntity() {
    }

    public SalesOrderItemEntity(
           java.lang.String item_id,
           java.lang.String order_id,
           java.lang.String quote_item_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String product_id,
           java.lang.String product_type,
           java.lang.String product_options,
           java.lang.String weight,
           java.lang.String is_virtual,
           java.lang.String sku,
           java.lang.String name,
           java.lang.String applied_rule_ids,
           java.lang.String free_shipping,
           java.lang.String is_qty_decimal,
           java.lang.String no_discount,
           java.lang.String qty_canceled,
           java.lang.String qty_invoiced,
           java.lang.String qty_ordered,
           java.lang.String qty_refunded,
           java.lang.String qty_shipped,
           java.lang.String cost,
           java.lang.String price,
           java.lang.String base_price,
           java.lang.String original_price,
           java.lang.String base_original_price,
           java.lang.String tax_percent,
           java.lang.String tax_amount,
           java.lang.String base_tax_amount,
           java.lang.String tax_invoiced,
           java.lang.String base_tax_invoiced,
           java.lang.String discount_percent,
           java.lang.String discount_amount,
           java.lang.String base_discount_amount,
           java.lang.String discount_invoiced,
           java.lang.String base_discount_invoiced,
           java.lang.String amount_refunded,
           java.lang.String base_amount_refunded,
           java.lang.String row_total,
           java.lang.String base_row_total,
           java.lang.String row_invoiced,
           java.lang.String base_row_invoiced,
           java.lang.String row_weight,
           java.lang.String gift_message_id,
           java.lang.String gift_message,
           java.lang.String gift_message_available,
           java.lang.String base_tax_before_discount,
           java.lang.String tax_before_discount,
           java.lang.String weee_tax_applied,
           java.lang.String weee_tax_applied_amount,
           java.lang.String weee_tax_applied_row_amount,
           java.lang.String base_weee_tax_applied_amount,
           java.lang.String base_weee_tax_applied_row_amount,
           java.lang.String weee_tax_disposition,
           java.lang.String weee_tax_row_disposition,
           java.lang.String base_weee_tax_disposition,
           java.lang.String base_weee_tax_row_disposition) {
           this.item_id = item_id;
           this.order_id = order_id;
           this.quote_item_id = quote_item_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.product_id = product_id;
           this.product_type = product_type;
           this.product_options = product_options;
           this.weight = weight;
           this.is_virtual = is_virtual;
           this.sku = sku;
           this.name = name;
           this.applied_rule_ids = applied_rule_ids;
           this.free_shipping = free_shipping;
           this.is_qty_decimal = is_qty_decimal;
           this.no_discount = no_discount;
           this.qty_canceled = qty_canceled;
           this.qty_invoiced = qty_invoiced;
           this.qty_ordered = qty_ordered;
           this.qty_refunded = qty_refunded;
           this.qty_shipped = qty_shipped;
           this.cost = cost;
           this.price = price;
           this.base_price = base_price;
           this.original_price = original_price;
           this.base_original_price = base_original_price;
           this.tax_percent = tax_percent;
           this.tax_amount = tax_amount;
           this.base_tax_amount = base_tax_amount;
           this.tax_invoiced = tax_invoiced;
           this.base_tax_invoiced = base_tax_invoiced;
           this.discount_percent = discount_percent;
           this.discount_amount = discount_amount;
           this.base_discount_amount = base_discount_amount;
           this.discount_invoiced = discount_invoiced;
           this.base_discount_invoiced = base_discount_invoiced;
           this.amount_refunded = amount_refunded;
           this.base_amount_refunded = base_amount_refunded;
           this.row_total = row_total;
           this.base_row_total = base_row_total;
           this.row_invoiced = row_invoiced;
           this.base_row_invoiced = base_row_invoiced;
           this.row_weight = row_weight;
           this.gift_message_id = gift_message_id;
           this.gift_message = gift_message;
           this.gift_message_available = gift_message_available;
           this.base_tax_before_discount = base_tax_before_discount;
           this.tax_before_discount = tax_before_discount;
           this.weee_tax_applied = weee_tax_applied;
           this.weee_tax_applied_amount = weee_tax_applied_amount;
           this.weee_tax_applied_row_amount = weee_tax_applied_row_amount;
           this.base_weee_tax_applied_amount = base_weee_tax_applied_amount;
           this.base_weee_tax_applied_row_amount = base_weee_tax_applied_row_amount;
           this.weee_tax_disposition = weee_tax_disposition;
           this.weee_tax_row_disposition = weee_tax_row_disposition;
           this.base_weee_tax_disposition = base_weee_tax_disposition;
           this.base_weee_tax_row_disposition = base_weee_tax_row_disposition;
    }


    /**
     * Gets the item_id value for this SalesOrderItemEntity.
     * 
     * @return item_id
     */
    public java.lang.String getItem_id() {
        return item_id;
    }


    /**
     * Sets the item_id value for this SalesOrderItemEntity.
     * 
     * @param item_id
     */
    public void setItem_id(java.lang.String item_id) {
        this.item_id = item_id;
    }


    /**
     * Gets the order_id value for this SalesOrderItemEntity.
     * 
     * @return order_id
     */
    public java.lang.String getOrder_id() {
        return order_id;
    }


    /**
     * Sets the order_id value for this SalesOrderItemEntity.
     * 
     * @param order_id
     */
    public void setOrder_id(java.lang.String order_id) {
        this.order_id = order_id;
    }


    /**
     * Gets the quote_item_id value for this SalesOrderItemEntity.
     * 
     * @return quote_item_id
     */
    public java.lang.String getQuote_item_id() {
        return quote_item_id;
    }


    /**
     * Sets the quote_item_id value for this SalesOrderItemEntity.
     * 
     * @param quote_item_id
     */
    public void setQuote_item_id(java.lang.String quote_item_id) {
        this.quote_item_id = quote_item_id;
    }


    /**
     * Gets the created_at value for this SalesOrderItemEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderItemEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this SalesOrderItemEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderItemEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the product_id value for this SalesOrderItemEntity.
     * 
     * @return product_id
     */
    public java.lang.String getProduct_id() {
        return product_id;
    }


    /**
     * Sets the product_id value for this SalesOrderItemEntity.
     * 
     * @param product_id
     */
    public void setProduct_id(java.lang.String product_id) {
        this.product_id = product_id;
    }


    /**
     * Gets the product_type value for this SalesOrderItemEntity.
     * 
     * @return product_type
     */
    public java.lang.String getProduct_type() {
        return product_type;
    }


    /**
     * Sets the product_type value for this SalesOrderItemEntity.
     * 
     * @param product_type
     */
    public void setProduct_type(java.lang.String product_type) {
        this.product_type = product_type;
    }


    /**
     * Gets the product_options value for this SalesOrderItemEntity.
     * 
     * @return product_options
     */
    public java.lang.String getProduct_options() {
        return product_options;
    }


    /**
     * Sets the product_options value for this SalesOrderItemEntity.
     * 
     * @param product_options
     */
    public void setProduct_options(java.lang.String product_options) {
        this.product_options = product_options;
    }


    /**
     * Gets the weight value for this SalesOrderItemEntity.
     * 
     * @return weight
     */
    public java.lang.String getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this SalesOrderItemEntity.
     * 
     * @param weight
     */
    public void setWeight(java.lang.String weight) {
        this.weight = weight;
    }


    /**
     * Gets the is_virtual value for this SalesOrderItemEntity.
     * 
     * @return is_virtual
     */
    public java.lang.String getIs_virtual() {
        return is_virtual;
    }


    /**
     * Sets the is_virtual value for this SalesOrderItemEntity.
     * 
     * @param is_virtual
     */
    public void setIs_virtual(java.lang.String is_virtual) {
        this.is_virtual = is_virtual;
    }


    /**
     * Gets the sku value for this SalesOrderItemEntity.
     * 
     * @return sku
     */
    public java.lang.String getSku() {
        return sku;
    }


    /**
     * Sets the sku value for this SalesOrderItemEntity.
     * 
     * @param sku
     */
    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }


    /**
     * Gets the name value for this SalesOrderItemEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this SalesOrderItemEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the applied_rule_ids value for this SalesOrderItemEntity.
     * 
     * @return applied_rule_ids
     */
    public java.lang.String getApplied_rule_ids() {
        return applied_rule_ids;
    }


    /**
     * Sets the applied_rule_ids value for this SalesOrderItemEntity.
     * 
     * @param applied_rule_ids
     */
    public void setApplied_rule_ids(java.lang.String applied_rule_ids) {
        this.applied_rule_ids = applied_rule_ids;
    }


    /**
     * Gets the free_shipping value for this SalesOrderItemEntity.
     * 
     * @return free_shipping
     */
    public java.lang.String getFree_shipping() {
        return free_shipping;
    }


    /**
     * Sets the free_shipping value for this SalesOrderItemEntity.
     * 
     * @param free_shipping
     */
    public void setFree_shipping(java.lang.String free_shipping) {
        this.free_shipping = free_shipping;
    }


    /**
     * Gets the is_qty_decimal value for this SalesOrderItemEntity.
     * 
     * @return is_qty_decimal
     */
    public java.lang.String getIs_qty_decimal() {
        return is_qty_decimal;
    }


    /**
     * Sets the is_qty_decimal value for this SalesOrderItemEntity.
     * 
     * @param is_qty_decimal
     */
    public void setIs_qty_decimal(java.lang.String is_qty_decimal) {
        this.is_qty_decimal = is_qty_decimal;
    }


    /**
     * Gets the no_discount value for this SalesOrderItemEntity.
     * 
     * @return no_discount
     */
    public java.lang.String getNo_discount() {
        return no_discount;
    }


    /**
     * Sets the no_discount value for this SalesOrderItemEntity.
     * 
     * @param no_discount
     */
    public void setNo_discount(java.lang.String no_discount) {
        this.no_discount = no_discount;
    }


    /**
     * Gets the qty_canceled value for this SalesOrderItemEntity.
     * 
     * @return qty_canceled
     */
    public java.lang.String getQty_canceled() {
        return qty_canceled;
    }


    /**
     * Sets the qty_canceled value for this SalesOrderItemEntity.
     * 
     * @param qty_canceled
     */
    public void setQty_canceled(java.lang.String qty_canceled) {
        this.qty_canceled = qty_canceled;
    }


    /**
     * Gets the qty_invoiced value for this SalesOrderItemEntity.
     * 
     * @return qty_invoiced
     */
    public java.lang.String getQty_invoiced() {
        return qty_invoiced;
    }


    /**
     * Sets the qty_invoiced value for this SalesOrderItemEntity.
     * 
     * @param qty_invoiced
     */
    public void setQty_invoiced(java.lang.String qty_invoiced) {
        this.qty_invoiced = qty_invoiced;
    }


    /**
     * Gets the qty_ordered value for this SalesOrderItemEntity.
     * 
     * @return qty_ordered
     */
    public java.lang.String getQty_ordered() {
        return qty_ordered;
    }


    /**
     * Sets the qty_ordered value for this SalesOrderItemEntity.
     * 
     * @param qty_ordered
     */
    public void setQty_ordered(java.lang.String qty_ordered) {
        this.qty_ordered = qty_ordered;
    }


    /**
     * Gets the qty_refunded value for this SalesOrderItemEntity.
     * 
     * @return qty_refunded
     */
    public java.lang.String getQty_refunded() {
        return qty_refunded;
    }


    /**
     * Sets the qty_refunded value for this SalesOrderItemEntity.
     * 
     * @param qty_refunded
     */
    public void setQty_refunded(java.lang.String qty_refunded) {
        this.qty_refunded = qty_refunded;
    }


    /**
     * Gets the qty_shipped value for this SalesOrderItemEntity.
     * 
     * @return qty_shipped
     */
    public java.lang.String getQty_shipped() {
        return qty_shipped;
    }


    /**
     * Sets the qty_shipped value for this SalesOrderItemEntity.
     * 
     * @param qty_shipped
     */
    public void setQty_shipped(java.lang.String qty_shipped) {
        this.qty_shipped = qty_shipped;
    }


    /**
     * Gets the cost value for this SalesOrderItemEntity.
     * 
     * @return cost
     */
    public java.lang.String getCost() {
        return cost;
    }


    /**
     * Sets the cost value for this SalesOrderItemEntity.
     * 
     * @param cost
     */
    public void setCost(java.lang.String cost) {
        this.cost = cost;
    }


    /**
     * Gets the price value for this SalesOrderItemEntity.
     * 
     * @return price
     */
    public java.lang.String getPrice() {
        return price;
    }


    /**
     * Sets the price value for this SalesOrderItemEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.String price) {
        this.price = price;
    }


    /**
     * Gets the base_price value for this SalesOrderItemEntity.
     * 
     * @return base_price
     */
    public java.lang.String getBase_price() {
        return base_price;
    }


    /**
     * Sets the base_price value for this SalesOrderItemEntity.
     * 
     * @param base_price
     */
    public void setBase_price(java.lang.String base_price) {
        this.base_price = base_price;
    }


    /**
     * Gets the original_price value for this SalesOrderItemEntity.
     * 
     * @return original_price
     */
    public java.lang.String getOriginal_price() {
        return original_price;
    }


    /**
     * Sets the original_price value for this SalesOrderItemEntity.
     * 
     * @param original_price
     */
    public void setOriginal_price(java.lang.String original_price) {
        this.original_price = original_price;
    }


    /**
     * Gets the base_original_price value for this SalesOrderItemEntity.
     * 
     * @return base_original_price
     */
    public java.lang.String getBase_original_price() {
        return base_original_price;
    }


    /**
     * Sets the base_original_price value for this SalesOrderItemEntity.
     * 
     * @param base_original_price
     */
    public void setBase_original_price(java.lang.String base_original_price) {
        this.base_original_price = base_original_price;
    }


    /**
     * Gets the tax_percent value for this SalesOrderItemEntity.
     * 
     * @return tax_percent
     */
    public java.lang.String getTax_percent() {
        return tax_percent;
    }


    /**
     * Sets the tax_percent value for this SalesOrderItemEntity.
     * 
     * @param tax_percent
     */
    public void setTax_percent(java.lang.String tax_percent) {
        this.tax_percent = tax_percent;
    }


    /**
     * Gets the tax_amount value for this SalesOrderItemEntity.
     * 
     * @return tax_amount
     */
    public java.lang.String getTax_amount() {
        return tax_amount;
    }


    /**
     * Sets the tax_amount value for this SalesOrderItemEntity.
     * 
     * @param tax_amount
     */
    public void setTax_amount(java.lang.String tax_amount) {
        this.tax_amount = tax_amount;
    }


    /**
     * Gets the base_tax_amount value for this SalesOrderItemEntity.
     * 
     * @return base_tax_amount
     */
    public java.lang.String getBase_tax_amount() {
        return base_tax_amount;
    }


    /**
     * Sets the base_tax_amount value for this SalesOrderItemEntity.
     * 
     * @param base_tax_amount
     */
    public void setBase_tax_amount(java.lang.String base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }


    /**
     * Gets the tax_invoiced value for this SalesOrderItemEntity.
     * 
     * @return tax_invoiced
     */
    public java.lang.String getTax_invoiced() {
        return tax_invoiced;
    }


    /**
     * Sets the tax_invoiced value for this SalesOrderItemEntity.
     * 
     * @param tax_invoiced
     */
    public void setTax_invoiced(java.lang.String tax_invoiced) {
        this.tax_invoiced = tax_invoiced;
    }


    /**
     * Gets the base_tax_invoiced value for this SalesOrderItemEntity.
     * 
     * @return base_tax_invoiced
     */
    public java.lang.String getBase_tax_invoiced() {
        return base_tax_invoiced;
    }


    /**
     * Sets the base_tax_invoiced value for this SalesOrderItemEntity.
     * 
     * @param base_tax_invoiced
     */
    public void setBase_tax_invoiced(java.lang.String base_tax_invoiced) {
        this.base_tax_invoiced = base_tax_invoiced;
    }


    /**
     * Gets the discount_percent value for this SalesOrderItemEntity.
     * 
     * @return discount_percent
     */
    public java.lang.String getDiscount_percent() {
        return discount_percent;
    }


    /**
     * Sets the discount_percent value for this SalesOrderItemEntity.
     * 
     * @param discount_percent
     */
    public void setDiscount_percent(java.lang.String discount_percent) {
        this.discount_percent = discount_percent;
    }


    /**
     * Gets the discount_amount value for this SalesOrderItemEntity.
     * 
     * @return discount_amount
     */
    public java.lang.String getDiscount_amount() {
        return discount_amount;
    }


    /**
     * Sets the discount_amount value for this SalesOrderItemEntity.
     * 
     * @param discount_amount
     */
    public void setDiscount_amount(java.lang.String discount_amount) {
        this.discount_amount = discount_amount;
    }


    /**
     * Gets the base_discount_amount value for this SalesOrderItemEntity.
     * 
     * @return base_discount_amount
     */
    public java.lang.String getBase_discount_amount() {
        return base_discount_amount;
    }


    /**
     * Sets the base_discount_amount value for this SalesOrderItemEntity.
     * 
     * @param base_discount_amount
     */
    public void setBase_discount_amount(java.lang.String base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }


    /**
     * Gets the discount_invoiced value for this SalesOrderItemEntity.
     * 
     * @return discount_invoiced
     */
    public java.lang.String getDiscount_invoiced() {
        return discount_invoiced;
    }


    /**
     * Sets the discount_invoiced value for this SalesOrderItemEntity.
     * 
     * @param discount_invoiced
     */
    public void setDiscount_invoiced(java.lang.String discount_invoiced) {
        this.discount_invoiced = discount_invoiced;
    }


    /**
     * Gets the base_discount_invoiced value for this SalesOrderItemEntity.
     * 
     * @return base_discount_invoiced
     */
    public java.lang.String getBase_discount_invoiced() {
        return base_discount_invoiced;
    }


    /**
     * Sets the base_discount_invoiced value for this SalesOrderItemEntity.
     * 
     * @param base_discount_invoiced
     */
    public void setBase_discount_invoiced(java.lang.String base_discount_invoiced) {
        this.base_discount_invoiced = base_discount_invoiced;
    }


    /**
     * Gets the amount_refunded value for this SalesOrderItemEntity.
     * 
     * @return amount_refunded
     */
    public java.lang.String getAmount_refunded() {
        return amount_refunded;
    }


    /**
     * Sets the amount_refunded value for this SalesOrderItemEntity.
     * 
     * @param amount_refunded
     */
    public void setAmount_refunded(java.lang.String amount_refunded) {
        this.amount_refunded = amount_refunded;
    }


    /**
     * Gets the base_amount_refunded value for this SalesOrderItemEntity.
     * 
     * @return base_amount_refunded
     */
    public java.lang.String getBase_amount_refunded() {
        return base_amount_refunded;
    }


    /**
     * Sets the base_amount_refunded value for this SalesOrderItemEntity.
     * 
     * @param base_amount_refunded
     */
    public void setBase_amount_refunded(java.lang.String base_amount_refunded) {
        this.base_amount_refunded = base_amount_refunded;
    }


    /**
     * Gets the row_total value for this SalesOrderItemEntity.
     * 
     * @return row_total
     */
    public java.lang.String getRow_total() {
        return row_total;
    }


    /**
     * Sets the row_total value for this SalesOrderItemEntity.
     * 
     * @param row_total
     */
    public void setRow_total(java.lang.String row_total) {
        this.row_total = row_total;
    }


    /**
     * Gets the base_row_total value for this SalesOrderItemEntity.
     * 
     * @return base_row_total
     */
    public java.lang.String getBase_row_total() {
        return base_row_total;
    }


    /**
     * Sets the base_row_total value for this SalesOrderItemEntity.
     * 
     * @param base_row_total
     */
    public void setBase_row_total(java.lang.String base_row_total) {
        this.base_row_total = base_row_total;
    }


    /**
     * Gets the row_invoiced value for this SalesOrderItemEntity.
     * 
     * @return row_invoiced
     */
    public java.lang.String getRow_invoiced() {
        return row_invoiced;
    }


    /**
     * Sets the row_invoiced value for this SalesOrderItemEntity.
     * 
     * @param row_invoiced
     */
    public void setRow_invoiced(java.lang.String row_invoiced) {
        this.row_invoiced = row_invoiced;
    }


    /**
     * Gets the base_row_invoiced value for this SalesOrderItemEntity.
     * 
     * @return base_row_invoiced
     */
    public java.lang.String getBase_row_invoiced() {
        return base_row_invoiced;
    }


    /**
     * Sets the base_row_invoiced value for this SalesOrderItemEntity.
     * 
     * @param base_row_invoiced
     */
    public void setBase_row_invoiced(java.lang.String base_row_invoiced) {
        this.base_row_invoiced = base_row_invoiced;
    }


    /**
     * Gets the row_weight value for this SalesOrderItemEntity.
     * 
     * @return row_weight
     */
    public java.lang.String getRow_weight() {
        return row_weight;
    }


    /**
     * Sets the row_weight value for this SalesOrderItemEntity.
     * 
     * @param row_weight
     */
    public void setRow_weight(java.lang.String row_weight) {
        this.row_weight = row_weight;
    }


    /**
     * Gets the gift_message_id value for this SalesOrderItemEntity.
     * 
     * @return gift_message_id
     */
    public java.lang.String getGift_message_id() {
        return gift_message_id;
    }


    /**
     * Sets the gift_message_id value for this SalesOrderItemEntity.
     * 
     * @param gift_message_id
     */
    public void setGift_message_id(java.lang.String gift_message_id) {
        this.gift_message_id = gift_message_id;
    }


    /**
     * Gets the gift_message value for this SalesOrderItemEntity.
     * 
     * @return gift_message
     */
    public java.lang.String getGift_message() {
        return gift_message;
    }


    /**
     * Sets the gift_message value for this SalesOrderItemEntity.
     * 
     * @param gift_message
     */
    public void setGift_message(java.lang.String gift_message) {
        this.gift_message = gift_message;
    }


    /**
     * Gets the gift_message_available value for this SalesOrderItemEntity.
     * 
     * @return gift_message_available
     */
    public java.lang.String getGift_message_available() {
        return gift_message_available;
    }


    /**
     * Sets the gift_message_available value for this SalesOrderItemEntity.
     * 
     * @param gift_message_available
     */
    public void setGift_message_available(java.lang.String gift_message_available) {
        this.gift_message_available = gift_message_available;
    }


    /**
     * Gets the base_tax_before_discount value for this SalesOrderItemEntity.
     * 
     * @return base_tax_before_discount
     */
    public java.lang.String getBase_tax_before_discount() {
        return base_tax_before_discount;
    }


    /**
     * Sets the base_tax_before_discount value for this SalesOrderItemEntity.
     * 
     * @param base_tax_before_discount
     */
    public void setBase_tax_before_discount(java.lang.String base_tax_before_discount) {
        this.base_tax_before_discount = base_tax_before_discount;
    }


    /**
     * Gets the tax_before_discount value for this SalesOrderItemEntity.
     * 
     * @return tax_before_discount
     */
    public java.lang.String getTax_before_discount() {
        return tax_before_discount;
    }


    /**
     * Sets the tax_before_discount value for this SalesOrderItemEntity.
     * 
     * @param tax_before_discount
     */
    public void setTax_before_discount(java.lang.String tax_before_discount) {
        this.tax_before_discount = tax_before_discount;
    }


    /**
     * Gets the weee_tax_applied value for this SalesOrderItemEntity.
     * 
     * @return weee_tax_applied
     */
    public java.lang.String getWeee_tax_applied() {
        return weee_tax_applied;
    }


    /**
     * Sets the weee_tax_applied value for this SalesOrderItemEntity.
     * 
     * @param weee_tax_applied
     */
    public void setWeee_tax_applied(java.lang.String weee_tax_applied) {
        this.weee_tax_applied = weee_tax_applied;
    }


    /**
     * Gets the weee_tax_applied_amount value for this SalesOrderItemEntity.
     * 
     * @return weee_tax_applied_amount
     */
    public java.lang.String getWeee_tax_applied_amount() {
        return weee_tax_applied_amount;
    }


    /**
     * Sets the weee_tax_applied_amount value for this SalesOrderItemEntity.
     * 
     * @param weee_tax_applied_amount
     */
    public void setWeee_tax_applied_amount(java.lang.String weee_tax_applied_amount) {
        this.weee_tax_applied_amount = weee_tax_applied_amount;
    }


    /**
     * Gets the weee_tax_applied_row_amount value for this SalesOrderItemEntity.
     * 
     * @return weee_tax_applied_row_amount
     */
    public java.lang.String getWeee_tax_applied_row_amount() {
        return weee_tax_applied_row_amount;
    }


    /**
     * Sets the weee_tax_applied_row_amount value for this SalesOrderItemEntity.
     * 
     * @param weee_tax_applied_row_amount
     */
    public void setWeee_tax_applied_row_amount(java.lang.String weee_tax_applied_row_amount) {
        this.weee_tax_applied_row_amount = weee_tax_applied_row_amount;
    }


    /**
     * Gets the base_weee_tax_applied_amount value for this SalesOrderItemEntity.
     * 
     * @return base_weee_tax_applied_amount
     */
    public java.lang.String getBase_weee_tax_applied_amount() {
        return base_weee_tax_applied_amount;
    }


    /**
     * Sets the base_weee_tax_applied_amount value for this SalesOrderItemEntity.
     * 
     * @param base_weee_tax_applied_amount
     */
    public void setBase_weee_tax_applied_amount(java.lang.String base_weee_tax_applied_amount) {
        this.base_weee_tax_applied_amount = base_weee_tax_applied_amount;
    }


    /**
     * Gets the base_weee_tax_applied_row_amount value for this SalesOrderItemEntity.
     * 
     * @return base_weee_tax_applied_row_amount
     */
    public java.lang.String getBase_weee_tax_applied_row_amount() {
        return base_weee_tax_applied_row_amount;
    }


    /**
     * Sets the base_weee_tax_applied_row_amount value for this SalesOrderItemEntity.
     * 
     * @param base_weee_tax_applied_row_amount
     */
    public void setBase_weee_tax_applied_row_amount(java.lang.String base_weee_tax_applied_row_amount) {
        this.base_weee_tax_applied_row_amount = base_weee_tax_applied_row_amount;
    }


    /**
     * Gets the weee_tax_disposition value for this SalesOrderItemEntity.
     * 
     * @return weee_tax_disposition
     */
    public java.lang.String getWeee_tax_disposition() {
        return weee_tax_disposition;
    }


    /**
     * Sets the weee_tax_disposition value for this SalesOrderItemEntity.
     * 
     * @param weee_tax_disposition
     */
    public void setWeee_tax_disposition(java.lang.String weee_tax_disposition) {
        this.weee_tax_disposition = weee_tax_disposition;
    }


    /**
     * Gets the weee_tax_row_disposition value for this SalesOrderItemEntity.
     * 
     * @return weee_tax_row_disposition
     */
    public java.lang.String getWeee_tax_row_disposition() {
        return weee_tax_row_disposition;
    }


    /**
     * Sets the weee_tax_row_disposition value for this SalesOrderItemEntity.
     * 
     * @param weee_tax_row_disposition
     */
    public void setWeee_tax_row_disposition(java.lang.String weee_tax_row_disposition) {
        this.weee_tax_row_disposition = weee_tax_row_disposition;
    }


    /**
     * Gets the base_weee_tax_disposition value for this SalesOrderItemEntity.
     * 
     * @return base_weee_tax_disposition
     */
    public java.lang.String getBase_weee_tax_disposition() {
        return base_weee_tax_disposition;
    }


    /**
     * Sets the base_weee_tax_disposition value for this SalesOrderItemEntity.
     * 
     * @param base_weee_tax_disposition
     */
    public void setBase_weee_tax_disposition(java.lang.String base_weee_tax_disposition) {
        this.base_weee_tax_disposition = base_weee_tax_disposition;
    }


    /**
     * Gets the base_weee_tax_row_disposition value for this SalesOrderItemEntity.
     * 
     * @return base_weee_tax_row_disposition
     */
    public java.lang.String getBase_weee_tax_row_disposition() {
        return base_weee_tax_row_disposition;
    }


    /**
     * Sets the base_weee_tax_row_disposition value for this SalesOrderItemEntity.
     * 
     * @param base_weee_tax_row_disposition
     */
    public void setBase_weee_tax_row_disposition(java.lang.String base_weee_tax_row_disposition) {
        this.base_weee_tax_row_disposition = base_weee_tax_row_disposition;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderItemEntity)) return false;
        SalesOrderItemEntity other = (SalesOrderItemEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.item_id==null && other.getItem_id()==null) || 
             (this.item_id!=null &&
              this.item_id.equals(other.getItem_id()))) &&
            ((this.order_id==null && other.getOrder_id()==null) || 
             (this.order_id!=null &&
              this.order_id.equals(other.getOrder_id()))) &&
            ((this.quote_item_id==null && other.getQuote_item_id()==null) || 
             (this.quote_item_id!=null &&
              this.quote_item_id.equals(other.getQuote_item_id()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
            ((this.product_id==null && other.getProduct_id()==null) || 
             (this.product_id!=null &&
              this.product_id.equals(other.getProduct_id()))) &&
            ((this.product_type==null && other.getProduct_type()==null) || 
             (this.product_type!=null &&
              this.product_type.equals(other.getProduct_type()))) &&
            ((this.product_options==null && other.getProduct_options()==null) || 
             (this.product_options!=null &&
              this.product_options.equals(other.getProduct_options()))) &&
            ((this.weight==null && other.getWeight()==null) || 
             (this.weight!=null &&
              this.weight.equals(other.getWeight()))) &&
            ((this.is_virtual==null && other.getIs_virtual()==null) || 
             (this.is_virtual!=null &&
              this.is_virtual.equals(other.getIs_virtual()))) &&
            ((this.sku==null && other.getSku()==null) || 
             (this.sku!=null &&
              this.sku.equals(other.getSku()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.applied_rule_ids==null && other.getApplied_rule_ids()==null) || 
             (this.applied_rule_ids!=null &&
              this.applied_rule_ids.equals(other.getApplied_rule_ids()))) &&
            ((this.free_shipping==null && other.getFree_shipping()==null) || 
             (this.free_shipping!=null &&
              this.free_shipping.equals(other.getFree_shipping()))) &&
            ((this.is_qty_decimal==null && other.getIs_qty_decimal()==null) || 
             (this.is_qty_decimal!=null &&
              this.is_qty_decimal.equals(other.getIs_qty_decimal()))) &&
            ((this.no_discount==null && other.getNo_discount()==null) || 
             (this.no_discount!=null &&
              this.no_discount.equals(other.getNo_discount()))) &&
            ((this.qty_canceled==null && other.getQty_canceled()==null) || 
             (this.qty_canceled!=null &&
              this.qty_canceled.equals(other.getQty_canceled()))) &&
            ((this.qty_invoiced==null && other.getQty_invoiced()==null) || 
             (this.qty_invoiced!=null &&
              this.qty_invoiced.equals(other.getQty_invoiced()))) &&
            ((this.qty_ordered==null && other.getQty_ordered()==null) || 
             (this.qty_ordered!=null &&
              this.qty_ordered.equals(other.getQty_ordered()))) &&
            ((this.qty_refunded==null && other.getQty_refunded()==null) || 
             (this.qty_refunded!=null &&
              this.qty_refunded.equals(other.getQty_refunded()))) &&
            ((this.qty_shipped==null && other.getQty_shipped()==null) || 
             (this.qty_shipped!=null &&
              this.qty_shipped.equals(other.getQty_shipped()))) &&
            ((this.cost==null && other.getCost()==null) || 
             (this.cost!=null &&
              this.cost.equals(other.getCost()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.base_price==null && other.getBase_price()==null) || 
             (this.base_price!=null &&
              this.base_price.equals(other.getBase_price()))) &&
            ((this.original_price==null && other.getOriginal_price()==null) || 
             (this.original_price!=null &&
              this.original_price.equals(other.getOriginal_price()))) &&
            ((this.base_original_price==null && other.getBase_original_price()==null) || 
             (this.base_original_price!=null &&
              this.base_original_price.equals(other.getBase_original_price()))) &&
            ((this.tax_percent==null && other.getTax_percent()==null) || 
             (this.tax_percent!=null &&
              this.tax_percent.equals(other.getTax_percent()))) &&
            ((this.tax_amount==null && other.getTax_amount()==null) || 
             (this.tax_amount!=null &&
              this.tax_amount.equals(other.getTax_amount()))) &&
            ((this.base_tax_amount==null && other.getBase_tax_amount()==null) || 
             (this.base_tax_amount!=null &&
              this.base_tax_amount.equals(other.getBase_tax_amount()))) &&
            ((this.tax_invoiced==null && other.getTax_invoiced()==null) || 
             (this.tax_invoiced!=null &&
              this.tax_invoiced.equals(other.getTax_invoiced()))) &&
            ((this.base_tax_invoiced==null && other.getBase_tax_invoiced()==null) || 
             (this.base_tax_invoiced!=null &&
              this.base_tax_invoiced.equals(other.getBase_tax_invoiced()))) &&
            ((this.discount_percent==null && other.getDiscount_percent()==null) || 
             (this.discount_percent!=null &&
              this.discount_percent.equals(other.getDiscount_percent()))) &&
            ((this.discount_amount==null && other.getDiscount_amount()==null) || 
             (this.discount_amount!=null &&
              this.discount_amount.equals(other.getDiscount_amount()))) &&
            ((this.base_discount_amount==null && other.getBase_discount_amount()==null) || 
             (this.base_discount_amount!=null &&
              this.base_discount_amount.equals(other.getBase_discount_amount()))) &&
            ((this.discount_invoiced==null && other.getDiscount_invoiced()==null) || 
             (this.discount_invoiced!=null &&
              this.discount_invoiced.equals(other.getDiscount_invoiced()))) &&
            ((this.base_discount_invoiced==null && other.getBase_discount_invoiced()==null) || 
             (this.base_discount_invoiced!=null &&
              this.base_discount_invoiced.equals(other.getBase_discount_invoiced()))) &&
            ((this.amount_refunded==null && other.getAmount_refunded()==null) || 
             (this.amount_refunded!=null &&
              this.amount_refunded.equals(other.getAmount_refunded()))) &&
            ((this.base_amount_refunded==null && other.getBase_amount_refunded()==null) || 
             (this.base_amount_refunded!=null &&
              this.base_amount_refunded.equals(other.getBase_amount_refunded()))) &&
            ((this.row_total==null && other.getRow_total()==null) || 
             (this.row_total!=null &&
              this.row_total.equals(other.getRow_total()))) &&
            ((this.base_row_total==null && other.getBase_row_total()==null) || 
             (this.base_row_total!=null &&
              this.base_row_total.equals(other.getBase_row_total()))) &&
            ((this.row_invoiced==null && other.getRow_invoiced()==null) || 
             (this.row_invoiced!=null &&
              this.row_invoiced.equals(other.getRow_invoiced()))) &&
            ((this.base_row_invoiced==null && other.getBase_row_invoiced()==null) || 
             (this.base_row_invoiced!=null &&
              this.base_row_invoiced.equals(other.getBase_row_invoiced()))) &&
            ((this.row_weight==null && other.getRow_weight()==null) || 
             (this.row_weight!=null &&
              this.row_weight.equals(other.getRow_weight()))) &&
            ((this.gift_message_id==null && other.getGift_message_id()==null) || 
             (this.gift_message_id!=null &&
              this.gift_message_id.equals(other.getGift_message_id()))) &&
            ((this.gift_message==null && other.getGift_message()==null) || 
             (this.gift_message!=null &&
              this.gift_message.equals(other.getGift_message()))) &&
            ((this.gift_message_available==null && other.getGift_message_available()==null) || 
             (this.gift_message_available!=null &&
              this.gift_message_available.equals(other.getGift_message_available()))) &&
            ((this.base_tax_before_discount==null && other.getBase_tax_before_discount()==null) || 
             (this.base_tax_before_discount!=null &&
              this.base_tax_before_discount.equals(other.getBase_tax_before_discount()))) &&
            ((this.tax_before_discount==null && other.getTax_before_discount()==null) || 
             (this.tax_before_discount!=null &&
              this.tax_before_discount.equals(other.getTax_before_discount()))) &&
            ((this.weee_tax_applied==null && other.getWeee_tax_applied()==null) || 
             (this.weee_tax_applied!=null &&
              this.weee_tax_applied.equals(other.getWeee_tax_applied()))) &&
            ((this.weee_tax_applied_amount==null && other.getWeee_tax_applied_amount()==null) || 
             (this.weee_tax_applied_amount!=null &&
              this.weee_tax_applied_amount.equals(other.getWeee_tax_applied_amount()))) &&
            ((this.weee_tax_applied_row_amount==null && other.getWeee_tax_applied_row_amount()==null) || 
             (this.weee_tax_applied_row_amount!=null &&
              this.weee_tax_applied_row_amount.equals(other.getWeee_tax_applied_row_amount()))) &&
            ((this.base_weee_tax_applied_amount==null && other.getBase_weee_tax_applied_amount()==null) || 
             (this.base_weee_tax_applied_amount!=null &&
              this.base_weee_tax_applied_amount.equals(other.getBase_weee_tax_applied_amount()))) &&
            ((this.base_weee_tax_applied_row_amount==null && other.getBase_weee_tax_applied_row_amount()==null) || 
             (this.base_weee_tax_applied_row_amount!=null &&
              this.base_weee_tax_applied_row_amount.equals(other.getBase_weee_tax_applied_row_amount()))) &&
            ((this.weee_tax_disposition==null && other.getWeee_tax_disposition()==null) || 
             (this.weee_tax_disposition!=null &&
              this.weee_tax_disposition.equals(other.getWeee_tax_disposition()))) &&
            ((this.weee_tax_row_disposition==null && other.getWeee_tax_row_disposition()==null) || 
             (this.weee_tax_row_disposition!=null &&
              this.weee_tax_row_disposition.equals(other.getWeee_tax_row_disposition()))) &&
            ((this.base_weee_tax_disposition==null && other.getBase_weee_tax_disposition()==null) || 
             (this.base_weee_tax_disposition!=null &&
              this.base_weee_tax_disposition.equals(other.getBase_weee_tax_disposition()))) &&
            ((this.base_weee_tax_row_disposition==null && other.getBase_weee_tax_row_disposition()==null) || 
             (this.base_weee_tax_row_disposition!=null &&
              this.base_weee_tax_row_disposition.equals(other.getBase_weee_tax_row_disposition())));
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
        if (getItem_id() != null) {
            _hashCode += getItem_id().hashCode();
        }
        if (getOrder_id() != null) {
            _hashCode += getOrder_id().hashCode();
        }
        if (getQuote_item_id() != null) {
            _hashCode += getQuote_item_id().hashCode();
        }
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getUpdated_at() != null) {
            _hashCode += getUpdated_at().hashCode();
        }
        if (getProduct_id() != null) {
            _hashCode += getProduct_id().hashCode();
        }
        if (getProduct_type() != null) {
            _hashCode += getProduct_type().hashCode();
        }
        if (getProduct_options() != null) {
            _hashCode += getProduct_options().hashCode();
        }
        if (getWeight() != null) {
            _hashCode += getWeight().hashCode();
        }
        if (getIs_virtual() != null) {
            _hashCode += getIs_virtual().hashCode();
        }
        if (getSku() != null) {
            _hashCode += getSku().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getApplied_rule_ids() != null) {
            _hashCode += getApplied_rule_ids().hashCode();
        }
        if (getFree_shipping() != null) {
            _hashCode += getFree_shipping().hashCode();
        }
        if (getIs_qty_decimal() != null) {
            _hashCode += getIs_qty_decimal().hashCode();
        }
        if (getNo_discount() != null) {
            _hashCode += getNo_discount().hashCode();
        }
        if (getQty_canceled() != null) {
            _hashCode += getQty_canceled().hashCode();
        }
        if (getQty_invoiced() != null) {
            _hashCode += getQty_invoiced().hashCode();
        }
        if (getQty_ordered() != null) {
            _hashCode += getQty_ordered().hashCode();
        }
        if (getQty_refunded() != null) {
            _hashCode += getQty_refunded().hashCode();
        }
        if (getQty_shipped() != null) {
            _hashCode += getQty_shipped().hashCode();
        }
        if (getCost() != null) {
            _hashCode += getCost().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getBase_price() != null) {
            _hashCode += getBase_price().hashCode();
        }
        if (getOriginal_price() != null) {
            _hashCode += getOriginal_price().hashCode();
        }
        if (getBase_original_price() != null) {
            _hashCode += getBase_original_price().hashCode();
        }
        if (getTax_percent() != null) {
            _hashCode += getTax_percent().hashCode();
        }
        if (getTax_amount() != null) {
            _hashCode += getTax_amount().hashCode();
        }
        if (getBase_tax_amount() != null) {
            _hashCode += getBase_tax_amount().hashCode();
        }
        if (getTax_invoiced() != null) {
            _hashCode += getTax_invoiced().hashCode();
        }
        if (getBase_tax_invoiced() != null) {
            _hashCode += getBase_tax_invoiced().hashCode();
        }
        if (getDiscount_percent() != null) {
            _hashCode += getDiscount_percent().hashCode();
        }
        if (getDiscount_amount() != null) {
            _hashCode += getDiscount_amount().hashCode();
        }
        if (getBase_discount_amount() != null) {
            _hashCode += getBase_discount_amount().hashCode();
        }
        if (getDiscount_invoiced() != null) {
            _hashCode += getDiscount_invoiced().hashCode();
        }
        if (getBase_discount_invoiced() != null) {
            _hashCode += getBase_discount_invoiced().hashCode();
        }
        if (getAmount_refunded() != null) {
            _hashCode += getAmount_refunded().hashCode();
        }
        if (getBase_amount_refunded() != null) {
            _hashCode += getBase_amount_refunded().hashCode();
        }
        if (getRow_total() != null) {
            _hashCode += getRow_total().hashCode();
        }
        if (getBase_row_total() != null) {
            _hashCode += getBase_row_total().hashCode();
        }
        if (getRow_invoiced() != null) {
            _hashCode += getRow_invoiced().hashCode();
        }
        if (getBase_row_invoiced() != null) {
            _hashCode += getBase_row_invoiced().hashCode();
        }
        if (getRow_weight() != null) {
            _hashCode += getRow_weight().hashCode();
        }
        if (getGift_message_id() != null) {
            _hashCode += getGift_message_id().hashCode();
        }
        if (getGift_message() != null) {
            _hashCode += getGift_message().hashCode();
        }
        if (getGift_message_available() != null) {
            _hashCode += getGift_message_available().hashCode();
        }
        if (getBase_tax_before_discount() != null) {
            _hashCode += getBase_tax_before_discount().hashCode();
        }
        if (getTax_before_discount() != null) {
            _hashCode += getTax_before_discount().hashCode();
        }
        if (getWeee_tax_applied() != null) {
            _hashCode += getWeee_tax_applied().hashCode();
        }
        if (getWeee_tax_applied_amount() != null) {
            _hashCode += getWeee_tax_applied_amount().hashCode();
        }
        if (getWeee_tax_applied_row_amount() != null) {
            _hashCode += getWeee_tax_applied_row_amount().hashCode();
        }
        if (getBase_weee_tax_applied_amount() != null) {
            _hashCode += getBase_weee_tax_applied_amount().hashCode();
        }
        if (getBase_weee_tax_applied_row_amount() != null) {
            _hashCode += getBase_weee_tax_applied_row_amount().hashCode();
        }
        if (getWeee_tax_disposition() != null) {
            _hashCode += getWeee_tax_disposition().hashCode();
        }
        if (getWeee_tax_row_disposition() != null) {
            _hashCode += getWeee_tax_row_disposition().hashCode();
        }
        if (getBase_weee_tax_disposition() != null) {
            _hashCode += getBase_weee_tax_disposition().hashCode();
        }
        if (getBase_weee_tax_row_disposition() != null) {
            _hashCode += getBase_weee_tax_row_disposition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderItemEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderItemEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "item_id"));
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
        elemField.setFieldName("quote_item_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quote_item_id"));
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
        elemField.setFieldName("product_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product_options");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_options"));
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
        elemField.setFieldName("is_virtual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_virtual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sku");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sku"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
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
        elemField.setFieldName("free_shipping");
        elemField.setXmlName(new javax.xml.namespace.QName("", "free_shipping"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_qty_decimal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_qty_decimal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("no_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "no_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty_canceled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty_canceled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty_ordered");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty_ordered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty_shipped");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty_shipped"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cost");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "original_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_original_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_original_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_percent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_percent"));
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
        elemField.setFieldName("tax_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_invoiced"));
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
        elemField.setFieldName("discount_percent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_percent"));
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
        elemField.setFieldName("discount_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_invoiced"));
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
        elemField.setFieldName("amount_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amount_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_amount_refunded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_amount_refunded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("row_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "row_total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_row_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_row_total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("row_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "row_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_row_invoiced");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_row_invoiced"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("row_weight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "row_weight"));
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
        elemField.setFieldName("gift_message_available");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gift_message_available"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_tax_before_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_tax_before_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_before_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_before_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_applied");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_applied"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_applied_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_applied_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_applied_row_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_applied_row_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_applied_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_applied_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_applied_row_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_applied_row_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_disposition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_row_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_row_disposition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_disposition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_row_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_row_disposition"));
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
