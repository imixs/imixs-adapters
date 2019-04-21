/**
 * ShoppingCartItemEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartItemEntity  implements java.io.Serializable {
    private java.lang.String item_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String product_id;

    private java.lang.String store_id;

    private java.lang.String parent_item_id;

    private java.lang.Integer is_virtual;

    private java.lang.String sku;

    private java.lang.String name;

    private java.lang.String description;

    private java.lang.String applied_rule_ids;

    private java.lang.String additional_data;

    private java.lang.String free_shipping;

    private java.lang.String is_qty_decimal;

    private java.lang.String no_discount;

    private java.lang.Double weight;

    private java.lang.Double qty;

    private java.lang.Double price;

    private java.lang.Double base_price;

    private java.lang.Double custom_price;

    private java.lang.Double discount_percent;

    private java.lang.Double discount_amount;

    private java.lang.Double base_discount_amount;

    private java.lang.Double tax_percent;

    private java.lang.Double tax_amount;

    private java.lang.Double base_tax_amount;

    private java.lang.Double row_total;

    private java.lang.Double base_row_total;

    private java.lang.Double row_total_with_discount;

    private java.lang.Double row_weight;

    private java.lang.String product_type;

    private java.lang.Double base_tax_before_discount;

    private java.lang.Double tax_before_discount;

    private java.lang.Double original_custom_price;

    private java.lang.Double base_cost;

    private java.lang.Double price_incl_tax;

    private java.lang.Double base_price_incl_tax;

    private java.lang.Double row_total_incl_tax;

    private java.lang.Double base_row_total_incl_tax;

    private java.lang.String gift_message_id;

    private java.lang.String gift_message;

    private java.lang.String gift_message_available;

    private java.lang.Double weee_tax_applied;

    private java.lang.Double weee_tax_applied_amount;

    private java.lang.Double weee_tax_applied_row_amount;

    private java.lang.Double base_weee_tax_applied_amount;

    private java.lang.Double base_weee_tax_applied_row_amount;

    private java.lang.Double weee_tax_disposition;

    private java.lang.Double weee_tax_row_disposition;

    private java.lang.Double base_weee_tax_disposition;

    private java.lang.Double base_weee_tax_row_disposition;

    private java.lang.String tax_class_id;

    public ShoppingCartItemEntity() {
    }

    public ShoppingCartItemEntity(
           java.lang.String item_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String product_id,
           java.lang.String store_id,
           java.lang.String parent_item_id,
           java.lang.Integer is_virtual,
           java.lang.String sku,
           java.lang.String name,
           java.lang.String description,
           java.lang.String applied_rule_ids,
           java.lang.String additional_data,
           java.lang.String free_shipping,
           java.lang.String is_qty_decimal,
           java.lang.String no_discount,
           java.lang.Double weight,
           java.lang.Double qty,
           java.lang.Double price,
           java.lang.Double base_price,
           java.lang.Double custom_price,
           java.lang.Double discount_percent,
           java.lang.Double discount_amount,
           java.lang.Double base_discount_amount,
           java.lang.Double tax_percent,
           java.lang.Double tax_amount,
           java.lang.Double base_tax_amount,
           java.lang.Double row_total,
           java.lang.Double base_row_total,
           java.lang.Double row_total_with_discount,
           java.lang.Double row_weight,
           java.lang.String product_type,
           java.lang.Double base_tax_before_discount,
           java.lang.Double tax_before_discount,
           java.lang.Double original_custom_price,
           java.lang.Double base_cost,
           java.lang.Double price_incl_tax,
           java.lang.Double base_price_incl_tax,
           java.lang.Double row_total_incl_tax,
           java.lang.Double base_row_total_incl_tax,
           java.lang.String gift_message_id,
           java.lang.String gift_message,
           java.lang.String gift_message_available,
           java.lang.Double weee_tax_applied,
           java.lang.Double weee_tax_applied_amount,
           java.lang.Double weee_tax_applied_row_amount,
           java.lang.Double base_weee_tax_applied_amount,
           java.lang.Double base_weee_tax_applied_row_amount,
           java.lang.Double weee_tax_disposition,
           java.lang.Double weee_tax_row_disposition,
           java.lang.Double base_weee_tax_disposition,
           java.lang.Double base_weee_tax_row_disposition,
           java.lang.String tax_class_id) {
           this.item_id = item_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.product_id = product_id;
           this.store_id = store_id;
           this.parent_item_id = parent_item_id;
           this.is_virtual = is_virtual;
           this.sku = sku;
           this.name = name;
           this.description = description;
           this.applied_rule_ids = applied_rule_ids;
           this.additional_data = additional_data;
           this.free_shipping = free_shipping;
           this.is_qty_decimal = is_qty_decimal;
           this.no_discount = no_discount;
           this.weight = weight;
           this.qty = qty;
           this.price = price;
           this.base_price = base_price;
           this.custom_price = custom_price;
           this.discount_percent = discount_percent;
           this.discount_amount = discount_amount;
           this.base_discount_amount = base_discount_amount;
           this.tax_percent = tax_percent;
           this.tax_amount = tax_amount;
           this.base_tax_amount = base_tax_amount;
           this.row_total = row_total;
           this.base_row_total = base_row_total;
           this.row_total_with_discount = row_total_with_discount;
           this.row_weight = row_weight;
           this.product_type = product_type;
           this.base_tax_before_discount = base_tax_before_discount;
           this.tax_before_discount = tax_before_discount;
           this.original_custom_price = original_custom_price;
           this.base_cost = base_cost;
           this.price_incl_tax = price_incl_tax;
           this.base_price_incl_tax = base_price_incl_tax;
           this.row_total_incl_tax = row_total_incl_tax;
           this.base_row_total_incl_tax = base_row_total_incl_tax;
           this.gift_message_id = gift_message_id;
           this.gift_message = gift_message;
           this.gift_message_available = gift_message_available;
           this.weee_tax_applied = weee_tax_applied;
           this.weee_tax_applied_amount = weee_tax_applied_amount;
           this.weee_tax_applied_row_amount = weee_tax_applied_row_amount;
           this.base_weee_tax_applied_amount = base_weee_tax_applied_amount;
           this.base_weee_tax_applied_row_amount = base_weee_tax_applied_row_amount;
           this.weee_tax_disposition = weee_tax_disposition;
           this.weee_tax_row_disposition = weee_tax_row_disposition;
           this.base_weee_tax_disposition = base_weee_tax_disposition;
           this.base_weee_tax_row_disposition = base_weee_tax_row_disposition;
           this.tax_class_id = tax_class_id;
    }


    /**
     * Gets the item_id value for this ShoppingCartItemEntity.
     * 
     * @return item_id
     */
    public java.lang.String getItem_id() {
        return item_id;
    }


    /**
     * Sets the item_id value for this ShoppingCartItemEntity.
     * 
     * @param item_id
     */
    public void setItem_id(java.lang.String item_id) {
        this.item_id = item_id;
    }


    /**
     * Gets the created_at value for this ShoppingCartItemEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this ShoppingCartItemEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this ShoppingCartItemEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this ShoppingCartItemEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the product_id value for this ShoppingCartItemEntity.
     * 
     * @return product_id
     */
    public java.lang.String getProduct_id() {
        return product_id;
    }


    /**
     * Sets the product_id value for this ShoppingCartItemEntity.
     * 
     * @param product_id
     */
    public void setProduct_id(java.lang.String product_id) {
        this.product_id = product_id;
    }


    /**
     * Gets the store_id value for this ShoppingCartItemEntity.
     * 
     * @return store_id
     */
    public java.lang.String getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this ShoppingCartItemEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.String store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the parent_item_id value for this ShoppingCartItemEntity.
     * 
     * @return parent_item_id
     */
    public java.lang.String getParent_item_id() {
        return parent_item_id;
    }


    /**
     * Sets the parent_item_id value for this ShoppingCartItemEntity.
     * 
     * @param parent_item_id
     */
    public void setParent_item_id(java.lang.String parent_item_id) {
        this.parent_item_id = parent_item_id;
    }


    /**
     * Gets the is_virtual value for this ShoppingCartItemEntity.
     * 
     * @return is_virtual
     */
    public java.lang.Integer getIs_virtual() {
        return is_virtual;
    }


    /**
     * Sets the is_virtual value for this ShoppingCartItemEntity.
     * 
     * @param is_virtual
     */
    public void setIs_virtual(java.lang.Integer is_virtual) {
        this.is_virtual = is_virtual;
    }


    /**
     * Gets the sku value for this ShoppingCartItemEntity.
     * 
     * @return sku
     */
    public java.lang.String getSku() {
        return sku;
    }


    /**
     * Sets the sku value for this ShoppingCartItemEntity.
     * 
     * @param sku
     */
    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }


    /**
     * Gets the name value for this ShoppingCartItemEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ShoppingCartItemEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this ShoppingCartItemEntity.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ShoppingCartItemEntity.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the applied_rule_ids value for this ShoppingCartItemEntity.
     * 
     * @return applied_rule_ids
     */
    public java.lang.String getApplied_rule_ids() {
        return applied_rule_ids;
    }


    /**
     * Sets the applied_rule_ids value for this ShoppingCartItemEntity.
     * 
     * @param applied_rule_ids
     */
    public void setApplied_rule_ids(java.lang.String applied_rule_ids) {
        this.applied_rule_ids = applied_rule_ids;
    }


    /**
     * Gets the additional_data value for this ShoppingCartItemEntity.
     * 
     * @return additional_data
     */
    public java.lang.String getAdditional_data() {
        return additional_data;
    }


    /**
     * Sets the additional_data value for this ShoppingCartItemEntity.
     * 
     * @param additional_data
     */
    public void setAdditional_data(java.lang.String additional_data) {
        this.additional_data = additional_data;
    }


    /**
     * Gets the free_shipping value for this ShoppingCartItemEntity.
     * 
     * @return free_shipping
     */
    public java.lang.String getFree_shipping() {
        return free_shipping;
    }


    /**
     * Sets the free_shipping value for this ShoppingCartItemEntity.
     * 
     * @param free_shipping
     */
    public void setFree_shipping(java.lang.String free_shipping) {
        this.free_shipping = free_shipping;
    }


    /**
     * Gets the is_qty_decimal value for this ShoppingCartItemEntity.
     * 
     * @return is_qty_decimal
     */
    public java.lang.String getIs_qty_decimal() {
        return is_qty_decimal;
    }


    /**
     * Sets the is_qty_decimal value for this ShoppingCartItemEntity.
     * 
     * @param is_qty_decimal
     */
    public void setIs_qty_decimal(java.lang.String is_qty_decimal) {
        this.is_qty_decimal = is_qty_decimal;
    }


    /**
     * Gets the no_discount value for this ShoppingCartItemEntity.
     * 
     * @return no_discount
     */
    public java.lang.String getNo_discount() {
        return no_discount;
    }


    /**
     * Sets the no_discount value for this ShoppingCartItemEntity.
     * 
     * @param no_discount
     */
    public void setNo_discount(java.lang.String no_discount) {
        this.no_discount = no_discount;
    }


    /**
     * Gets the weight value for this ShoppingCartItemEntity.
     * 
     * @return weight
     */
    public java.lang.Double getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this ShoppingCartItemEntity.
     * 
     * @param weight
     */
    public void setWeight(java.lang.Double weight) {
        this.weight = weight;
    }


    /**
     * Gets the qty value for this ShoppingCartItemEntity.
     * 
     * @return qty
     */
    public java.lang.Double getQty() {
        return qty;
    }


    /**
     * Sets the qty value for this ShoppingCartItemEntity.
     * 
     * @param qty
     */
    public void setQty(java.lang.Double qty) {
        this.qty = qty;
    }


    /**
     * Gets the price value for this ShoppingCartItemEntity.
     * 
     * @return price
     */
    public java.lang.Double getPrice() {
        return price;
    }


    /**
     * Sets the price value for this ShoppingCartItemEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.Double price) {
        this.price = price;
    }


    /**
     * Gets the base_price value for this ShoppingCartItemEntity.
     * 
     * @return base_price
     */
    public java.lang.Double getBase_price() {
        return base_price;
    }


    /**
     * Sets the base_price value for this ShoppingCartItemEntity.
     * 
     * @param base_price
     */
    public void setBase_price(java.lang.Double base_price) {
        this.base_price = base_price;
    }


    /**
     * Gets the custom_price value for this ShoppingCartItemEntity.
     * 
     * @return custom_price
     */
    public java.lang.Double getCustom_price() {
        return custom_price;
    }


    /**
     * Sets the custom_price value for this ShoppingCartItemEntity.
     * 
     * @param custom_price
     */
    public void setCustom_price(java.lang.Double custom_price) {
        this.custom_price = custom_price;
    }


    /**
     * Gets the discount_percent value for this ShoppingCartItemEntity.
     * 
     * @return discount_percent
     */
    public java.lang.Double getDiscount_percent() {
        return discount_percent;
    }


    /**
     * Sets the discount_percent value for this ShoppingCartItemEntity.
     * 
     * @param discount_percent
     */
    public void setDiscount_percent(java.lang.Double discount_percent) {
        this.discount_percent = discount_percent;
    }


    /**
     * Gets the discount_amount value for this ShoppingCartItemEntity.
     * 
     * @return discount_amount
     */
    public java.lang.Double getDiscount_amount() {
        return discount_amount;
    }


    /**
     * Sets the discount_amount value for this ShoppingCartItemEntity.
     * 
     * @param discount_amount
     */
    public void setDiscount_amount(java.lang.Double discount_amount) {
        this.discount_amount = discount_amount;
    }


    /**
     * Gets the base_discount_amount value for this ShoppingCartItemEntity.
     * 
     * @return base_discount_amount
     */
    public java.lang.Double getBase_discount_amount() {
        return base_discount_amount;
    }


    /**
     * Sets the base_discount_amount value for this ShoppingCartItemEntity.
     * 
     * @param base_discount_amount
     */
    public void setBase_discount_amount(java.lang.Double base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }


    /**
     * Gets the tax_percent value for this ShoppingCartItemEntity.
     * 
     * @return tax_percent
     */
    public java.lang.Double getTax_percent() {
        return tax_percent;
    }


    /**
     * Sets the tax_percent value for this ShoppingCartItemEntity.
     * 
     * @param tax_percent
     */
    public void setTax_percent(java.lang.Double tax_percent) {
        this.tax_percent = tax_percent;
    }


    /**
     * Gets the tax_amount value for this ShoppingCartItemEntity.
     * 
     * @return tax_amount
     */
    public java.lang.Double getTax_amount() {
        return tax_amount;
    }


    /**
     * Sets the tax_amount value for this ShoppingCartItemEntity.
     * 
     * @param tax_amount
     */
    public void setTax_amount(java.lang.Double tax_amount) {
        this.tax_amount = tax_amount;
    }


    /**
     * Gets the base_tax_amount value for this ShoppingCartItemEntity.
     * 
     * @return base_tax_amount
     */
    public java.lang.Double getBase_tax_amount() {
        return base_tax_amount;
    }


    /**
     * Sets the base_tax_amount value for this ShoppingCartItemEntity.
     * 
     * @param base_tax_amount
     */
    public void setBase_tax_amount(java.lang.Double base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }


    /**
     * Gets the row_total value for this ShoppingCartItemEntity.
     * 
     * @return row_total
     */
    public java.lang.Double getRow_total() {
        return row_total;
    }


    /**
     * Sets the row_total value for this ShoppingCartItemEntity.
     * 
     * @param row_total
     */
    public void setRow_total(java.lang.Double row_total) {
        this.row_total = row_total;
    }


    /**
     * Gets the base_row_total value for this ShoppingCartItemEntity.
     * 
     * @return base_row_total
     */
    public java.lang.Double getBase_row_total() {
        return base_row_total;
    }


    /**
     * Sets the base_row_total value for this ShoppingCartItemEntity.
     * 
     * @param base_row_total
     */
    public void setBase_row_total(java.lang.Double base_row_total) {
        this.base_row_total = base_row_total;
    }


    /**
     * Gets the row_total_with_discount value for this ShoppingCartItemEntity.
     * 
     * @return row_total_with_discount
     */
    public java.lang.Double getRow_total_with_discount() {
        return row_total_with_discount;
    }


    /**
     * Sets the row_total_with_discount value for this ShoppingCartItemEntity.
     * 
     * @param row_total_with_discount
     */
    public void setRow_total_with_discount(java.lang.Double row_total_with_discount) {
        this.row_total_with_discount = row_total_with_discount;
    }


    /**
     * Gets the row_weight value for this ShoppingCartItemEntity.
     * 
     * @return row_weight
     */
    public java.lang.Double getRow_weight() {
        return row_weight;
    }


    /**
     * Sets the row_weight value for this ShoppingCartItemEntity.
     * 
     * @param row_weight
     */
    public void setRow_weight(java.lang.Double row_weight) {
        this.row_weight = row_weight;
    }


    /**
     * Gets the product_type value for this ShoppingCartItemEntity.
     * 
     * @return product_type
     */
    public java.lang.String getProduct_type() {
        return product_type;
    }


    /**
     * Sets the product_type value for this ShoppingCartItemEntity.
     * 
     * @param product_type
     */
    public void setProduct_type(java.lang.String product_type) {
        this.product_type = product_type;
    }


    /**
     * Gets the base_tax_before_discount value for this ShoppingCartItemEntity.
     * 
     * @return base_tax_before_discount
     */
    public java.lang.Double getBase_tax_before_discount() {
        return base_tax_before_discount;
    }


    /**
     * Sets the base_tax_before_discount value for this ShoppingCartItemEntity.
     * 
     * @param base_tax_before_discount
     */
    public void setBase_tax_before_discount(java.lang.Double base_tax_before_discount) {
        this.base_tax_before_discount = base_tax_before_discount;
    }


    /**
     * Gets the tax_before_discount value for this ShoppingCartItemEntity.
     * 
     * @return tax_before_discount
     */
    public java.lang.Double getTax_before_discount() {
        return tax_before_discount;
    }


    /**
     * Sets the tax_before_discount value for this ShoppingCartItemEntity.
     * 
     * @param tax_before_discount
     */
    public void setTax_before_discount(java.lang.Double tax_before_discount) {
        this.tax_before_discount = tax_before_discount;
    }


    /**
     * Gets the original_custom_price value for this ShoppingCartItemEntity.
     * 
     * @return original_custom_price
     */
    public java.lang.Double getOriginal_custom_price() {
        return original_custom_price;
    }


    /**
     * Sets the original_custom_price value for this ShoppingCartItemEntity.
     * 
     * @param original_custom_price
     */
    public void setOriginal_custom_price(java.lang.Double original_custom_price) {
        this.original_custom_price = original_custom_price;
    }


    /**
     * Gets the base_cost value for this ShoppingCartItemEntity.
     * 
     * @return base_cost
     */
    public java.lang.Double getBase_cost() {
        return base_cost;
    }


    /**
     * Sets the base_cost value for this ShoppingCartItemEntity.
     * 
     * @param base_cost
     */
    public void setBase_cost(java.lang.Double base_cost) {
        this.base_cost = base_cost;
    }


    /**
     * Gets the price_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @return price_incl_tax
     */
    public java.lang.Double getPrice_incl_tax() {
        return price_incl_tax;
    }


    /**
     * Sets the price_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @param price_incl_tax
     */
    public void setPrice_incl_tax(java.lang.Double price_incl_tax) {
        this.price_incl_tax = price_incl_tax;
    }


    /**
     * Gets the base_price_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @return base_price_incl_tax
     */
    public java.lang.Double getBase_price_incl_tax() {
        return base_price_incl_tax;
    }


    /**
     * Sets the base_price_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @param base_price_incl_tax
     */
    public void setBase_price_incl_tax(java.lang.Double base_price_incl_tax) {
        this.base_price_incl_tax = base_price_incl_tax;
    }


    /**
     * Gets the row_total_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @return row_total_incl_tax
     */
    public java.lang.Double getRow_total_incl_tax() {
        return row_total_incl_tax;
    }


    /**
     * Sets the row_total_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @param row_total_incl_tax
     */
    public void setRow_total_incl_tax(java.lang.Double row_total_incl_tax) {
        this.row_total_incl_tax = row_total_incl_tax;
    }


    /**
     * Gets the base_row_total_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @return base_row_total_incl_tax
     */
    public java.lang.Double getBase_row_total_incl_tax() {
        return base_row_total_incl_tax;
    }


    /**
     * Sets the base_row_total_incl_tax value for this ShoppingCartItemEntity.
     * 
     * @param base_row_total_incl_tax
     */
    public void setBase_row_total_incl_tax(java.lang.Double base_row_total_incl_tax) {
        this.base_row_total_incl_tax = base_row_total_incl_tax;
    }


    /**
     * Gets the gift_message_id value for this ShoppingCartItemEntity.
     * 
     * @return gift_message_id
     */
    public java.lang.String getGift_message_id() {
        return gift_message_id;
    }


    /**
     * Sets the gift_message_id value for this ShoppingCartItemEntity.
     * 
     * @param gift_message_id
     */
    public void setGift_message_id(java.lang.String gift_message_id) {
        this.gift_message_id = gift_message_id;
    }


    /**
     * Gets the gift_message value for this ShoppingCartItemEntity.
     * 
     * @return gift_message
     */
    public java.lang.String getGift_message() {
        return gift_message;
    }


    /**
     * Sets the gift_message value for this ShoppingCartItemEntity.
     * 
     * @param gift_message
     */
    public void setGift_message(java.lang.String gift_message) {
        this.gift_message = gift_message;
    }


    /**
     * Gets the gift_message_available value for this ShoppingCartItemEntity.
     * 
     * @return gift_message_available
     */
    public java.lang.String getGift_message_available() {
        return gift_message_available;
    }


    /**
     * Sets the gift_message_available value for this ShoppingCartItemEntity.
     * 
     * @param gift_message_available
     */
    public void setGift_message_available(java.lang.String gift_message_available) {
        this.gift_message_available = gift_message_available;
    }


    /**
     * Gets the weee_tax_applied value for this ShoppingCartItemEntity.
     * 
     * @return weee_tax_applied
     */
    public java.lang.Double getWeee_tax_applied() {
        return weee_tax_applied;
    }


    /**
     * Sets the weee_tax_applied value for this ShoppingCartItemEntity.
     * 
     * @param weee_tax_applied
     */
    public void setWeee_tax_applied(java.lang.Double weee_tax_applied) {
        this.weee_tax_applied = weee_tax_applied;
    }


    /**
     * Gets the weee_tax_applied_amount value for this ShoppingCartItemEntity.
     * 
     * @return weee_tax_applied_amount
     */
    public java.lang.Double getWeee_tax_applied_amount() {
        return weee_tax_applied_amount;
    }


    /**
     * Sets the weee_tax_applied_amount value for this ShoppingCartItemEntity.
     * 
     * @param weee_tax_applied_amount
     */
    public void setWeee_tax_applied_amount(java.lang.Double weee_tax_applied_amount) {
        this.weee_tax_applied_amount = weee_tax_applied_amount;
    }


    /**
     * Gets the weee_tax_applied_row_amount value for this ShoppingCartItemEntity.
     * 
     * @return weee_tax_applied_row_amount
     */
    public java.lang.Double getWeee_tax_applied_row_amount() {
        return weee_tax_applied_row_amount;
    }


    /**
     * Sets the weee_tax_applied_row_amount value for this ShoppingCartItemEntity.
     * 
     * @param weee_tax_applied_row_amount
     */
    public void setWeee_tax_applied_row_amount(java.lang.Double weee_tax_applied_row_amount) {
        this.weee_tax_applied_row_amount = weee_tax_applied_row_amount;
    }


    /**
     * Gets the base_weee_tax_applied_amount value for this ShoppingCartItemEntity.
     * 
     * @return base_weee_tax_applied_amount
     */
    public java.lang.Double getBase_weee_tax_applied_amount() {
        return base_weee_tax_applied_amount;
    }


    /**
     * Sets the base_weee_tax_applied_amount value for this ShoppingCartItemEntity.
     * 
     * @param base_weee_tax_applied_amount
     */
    public void setBase_weee_tax_applied_amount(java.lang.Double base_weee_tax_applied_amount) {
        this.base_weee_tax_applied_amount = base_weee_tax_applied_amount;
    }


    /**
     * Gets the base_weee_tax_applied_row_amount value for this ShoppingCartItemEntity.
     * 
     * @return base_weee_tax_applied_row_amount
     */
    public java.lang.Double getBase_weee_tax_applied_row_amount() {
        return base_weee_tax_applied_row_amount;
    }


    /**
     * Sets the base_weee_tax_applied_row_amount value for this ShoppingCartItemEntity.
     * 
     * @param base_weee_tax_applied_row_amount
     */
    public void setBase_weee_tax_applied_row_amount(java.lang.Double base_weee_tax_applied_row_amount) {
        this.base_weee_tax_applied_row_amount = base_weee_tax_applied_row_amount;
    }


    /**
     * Gets the weee_tax_disposition value for this ShoppingCartItemEntity.
     * 
     * @return weee_tax_disposition
     */
    public java.lang.Double getWeee_tax_disposition() {
        return weee_tax_disposition;
    }


    /**
     * Sets the weee_tax_disposition value for this ShoppingCartItemEntity.
     * 
     * @param weee_tax_disposition
     */
    public void setWeee_tax_disposition(java.lang.Double weee_tax_disposition) {
        this.weee_tax_disposition = weee_tax_disposition;
    }


    /**
     * Gets the weee_tax_row_disposition value for this ShoppingCartItemEntity.
     * 
     * @return weee_tax_row_disposition
     */
    public java.lang.Double getWeee_tax_row_disposition() {
        return weee_tax_row_disposition;
    }


    /**
     * Sets the weee_tax_row_disposition value for this ShoppingCartItemEntity.
     * 
     * @param weee_tax_row_disposition
     */
    public void setWeee_tax_row_disposition(java.lang.Double weee_tax_row_disposition) {
        this.weee_tax_row_disposition = weee_tax_row_disposition;
    }


    /**
     * Gets the base_weee_tax_disposition value for this ShoppingCartItemEntity.
     * 
     * @return base_weee_tax_disposition
     */
    public java.lang.Double getBase_weee_tax_disposition() {
        return base_weee_tax_disposition;
    }


    /**
     * Sets the base_weee_tax_disposition value for this ShoppingCartItemEntity.
     * 
     * @param base_weee_tax_disposition
     */
    public void setBase_weee_tax_disposition(java.lang.Double base_weee_tax_disposition) {
        this.base_weee_tax_disposition = base_weee_tax_disposition;
    }


    /**
     * Gets the base_weee_tax_row_disposition value for this ShoppingCartItemEntity.
     * 
     * @return base_weee_tax_row_disposition
     */
    public java.lang.Double getBase_weee_tax_row_disposition() {
        return base_weee_tax_row_disposition;
    }


    /**
     * Sets the base_weee_tax_row_disposition value for this ShoppingCartItemEntity.
     * 
     * @param base_weee_tax_row_disposition
     */
    public void setBase_weee_tax_row_disposition(java.lang.Double base_weee_tax_row_disposition) {
        this.base_weee_tax_row_disposition = base_weee_tax_row_disposition;
    }


    /**
     * Gets the tax_class_id value for this ShoppingCartItemEntity.
     * 
     * @return tax_class_id
     */
    public java.lang.String getTax_class_id() {
        return tax_class_id;
    }


    /**
     * Sets the tax_class_id value for this ShoppingCartItemEntity.
     * 
     * @param tax_class_id
     */
    public void setTax_class_id(java.lang.String tax_class_id) {
        this.tax_class_id = tax_class_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartItemEntity)) return false;
        ShoppingCartItemEntity other = (ShoppingCartItemEntity) obj;
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
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
            ((this.product_id==null && other.getProduct_id()==null) || 
             (this.product_id!=null &&
              this.product_id.equals(other.getProduct_id()))) &&
            ((this.store_id==null && other.getStore_id()==null) || 
             (this.store_id!=null &&
              this.store_id.equals(other.getStore_id()))) &&
            ((this.parent_item_id==null && other.getParent_item_id()==null) || 
             (this.parent_item_id!=null &&
              this.parent_item_id.equals(other.getParent_item_id()))) &&
            ((this.is_virtual==null && other.getIs_virtual()==null) || 
             (this.is_virtual!=null &&
              this.is_virtual.equals(other.getIs_virtual()))) &&
            ((this.sku==null && other.getSku()==null) || 
             (this.sku!=null &&
              this.sku.equals(other.getSku()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.applied_rule_ids==null && other.getApplied_rule_ids()==null) || 
             (this.applied_rule_ids!=null &&
              this.applied_rule_ids.equals(other.getApplied_rule_ids()))) &&
            ((this.additional_data==null && other.getAdditional_data()==null) || 
             (this.additional_data!=null &&
              this.additional_data.equals(other.getAdditional_data()))) &&
            ((this.free_shipping==null && other.getFree_shipping()==null) || 
             (this.free_shipping!=null &&
              this.free_shipping.equals(other.getFree_shipping()))) &&
            ((this.is_qty_decimal==null && other.getIs_qty_decimal()==null) || 
             (this.is_qty_decimal!=null &&
              this.is_qty_decimal.equals(other.getIs_qty_decimal()))) &&
            ((this.no_discount==null && other.getNo_discount()==null) || 
             (this.no_discount!=null &&
              this.no_discount.equals(other.getNo_discount()))) &&
            ((this.weight==null && other.getWeight()==null) || 
             (this.weight!=null &&
              this.weight.equals(other.getWeight()))) &&
            ((this.qty==null && other.getQty()==null) || 
             (this.qty!=null &&
              this.qty.equals(other.getQty()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.base_price==null && other.getBase_price()==null) || 
             (this.base_price!=null &&
              this.base_price.equals(other.getBase_price()))) &&
            ((this.custom_price==null && other.getCustom_price()==null) || 
             (this.custom_price!=null &&
              this.custom_price.equals(other.getCustom_price()))) &&
            ((this.discount_percent==null && other.getDiscount_percent()==null) || 
             (this.discount_percent!=null &&
              this.discount_percent.equals(other.getDiscount_percent()))) &&
            ((this.discount_amount==null && other.getDiscount_amount()==null) || 
             (this.discount_amount!=null &&
              this.discount_amount.equals(other.getDiscount_amount()))) &&
            ((this.base_discount_amount==null && other.getBase_discount_amount()==null) || 
             (this.base_discount_amount!=null &&
              this.base_discount_amount.equals(other.getBase_discount_amount()))) &&
            ((this.tax_percent==null && other.getTax_percent()==null) || 
             (this.tax_percent!=null &&
              this.tax_percent.equals(other.getTax_percent()))) &&
            ((this.tax_amount==null && other.getTax_amount()==null) || 
             (this.tax_amount!=null &&
              this.tax_amount.equals(other.getTax_amount()))) &&
            ((this.base_tax_amount==null && other.getBase_tax_amount()==null) || 
             (this.base_tax_amount!=null &&
              this.base_tax_amount.equals(other.getBase_tax_amount()))) &&
            ((this.row_total==null && other.getRow_total()==null) || 
             (this.row_total!=null &&
              this.row_total.equals(other.getRow_total()))) &&
            ((this.base_row_total==null && other.getBase_row_total()==null) || 
             (this.base_row_total!=null &&
              this.base_row_total.equals(other.getBase_row_total()))) &&
            ((this.row_total_with_discount==null && other.getRow_total_with_discount()==null) || 
             (this.row_total_with_discount!=null &&
              this.row_total_with_discount.equals(other.getRow_total_with_discount()))) &&
            ((this.row_weight==null && other.getRow_weight()==null) || 
             (this.row_weight!=null &&
              this.row_weight.equals(other.getRow_weight()))) &&
            ((this.product_type==null && other.getProduct_type()==null) || 
             (this.product_type!=null &&
              this.product_type.equals(other.getProduct_type()))) &&
            ((this.base_tax_before_discount==null && other.getBase_tax_before_discount()==null) || 
             (this.base_tax_before_discount!=null &&
              this.base_tax_before_discount.equals(other.getBase_tax_before_discount()))) &&
            ((this.tax_before_discount==null && other.getTax_before_discount()==null) || 
             (this.tax_before_discount!=null &&
              this.tax_before_discount.equals(other.getTax_before_discount()))) &&
            ((this.original_custom_price==null && other.getOriginal_custom_price()==null) || 
             (this.original_custom_price!=null &&
              this.original_custom_price.equals(other.getOriginal_custom_price()))) &&
            ((this.base_cost==null && other.getBase_cost()==null) || 
             (this.base_cost!=null &&
              this.base_cost.equals(other.getBase_cost()))) &&
            ((this.price_incl_tax==null && other.getPrice_incl_tax()==null) || 
             (this.price_incl_tax!=null &&
              this.price_incl_tax.equals(other.getPrice_incl_tax()))) &&
            ((this.base_price_incl_tax==null && other.getBase_price_incl_tax()==null) || 
             (this.base_price_incl_tax!=null &&
              this.base_price_incl_tax.equals(other.getBase_price_incl_tax()))) &&
            ((this.row_total_incl_tax==null && other.getRow_total_incl_tax()==null) || 
             (this.row_total_incl_tax!=null &&
              this.row_total_incl_tax.equals(other.getRow_total_incl_tax()))) &&
            ((this.base_row_total_incl_tax==null && other.getBase_row_total_incl_tax()==null) || 
             (this.base_row_total_incl_tax!=null &&
              this.base_row_total_incl_tax.equals(other.getBase_row_total_incl_tax()))) &&
            ((this.gift_message_id==null && other.getGift_message_id()==null) || 
             (this.gift_message_id!=null &&
              this.gift_message_id.equals(other.getGift_message_id()))) &&
            ((this.gift_message==null && other.getGift_message()==null) || 
             (this.gift_message!=null &&
              this.gift_message.equals(other.getGift_message()))) &&
            ((this.gift_message_available==null && other.getGift_message_available()==null) || 
             (this.gift_message_available!=null &&
              this.gift_message_available.equals(other.getGift_message_available()))) &&
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
              this.base_weee_tax_row_disposition.equals(other.getBase_weee_tax_row_disposition()))) &&
            ((this.tax_class_id==null && other.getTax_class_id()==null) || 
             (this.tax_class_id!=null &&
              this.tax_class_id.equals(other.getTax_class_id())));
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
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getUpdated_at() != null) {
            _hashCode += getUpdated_at().hashCode();
        }
        if (getProduct_id() != null) {
            _hashCode += getProduct_id().hashCode();
        }
        if (getStore_id() != null) {
            _hashCode += getStore_id().hashCode();
        }
        if (getParent_item_id() != null) {
            _hashCode += getParent_item_id().hashCode();
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getApplied_rule_ids() != null) {
            _hashCode += getApplied_rule_ids().hashCode();
        }
        if (getAdditional_data() != null) {
            _hashCode += getAdditional_data().hashCode();
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
        if (getWeight() != null) {
            _hashCode += getWeight().hashCode();
        }
        if (getQty() != null) {
            _hashCode += getQty().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getBase_price() != null) {
            _hashCode += getBase_price().hashCode();
        }
        if (getCustom_price() != null) {
            _hashCode += getCustom_price().hashCode();
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
        if (getTax_percent() != null) {
            _hashCode += getTax_percent().hashCode();
        }
        if (getTax_amount() != null) {
            _hashCode += getTax_amount().hashCode();
        }
        if (getBase_tax_amount() != null) {
            _hashCode += getBase_tax_amount().hashCode();
        }
        if (getRow_total() != null) {
            _hashCode += getRow_total().hashCode();
        }
        if (getBase_row_total() != null) {
            _hashCode += getBase_row_total().hashCode();
        }
        if (getRow_total_with_discount() != null) {
            _hashCode += getRow_total_with_discount().hashCode();
        }
        if (getRow_weight() != null) {
            _hashCode += getRow_weight().hashCode();
        }
        if (getProduct_type() != null) {
            _hashCode += getProduct_type().hashCode();
        }
        if (getBase_tax_before_discount() != null) {
            _hashCode += getBase_tax_before_discount().hashCode();
        }
        if (getTax_before_discount() != null) {
            _hashCode += getTax_before_discount().hashCode();
        }
        if (getOriginal_custom_price() != null) {
            _hashCode += getOriginal_custom_price().hashCode();
        }
        if (getBase_cost() != null) {
            _hashCode += getBase_cost().hashCode();
        }
        if (getPrice_incl_tax() != null) {
            _hashCode += getPrice_incl_tax().hashCode();
        }
        if (getBase_price_incl_tax() != null) {
            _hashCode += getBase_price_incl_tax().hashCode();
        }
        if (getRow_total_incl_tax() != null) {
            _hashCode += getRow_total_incl_tax().hashCode();
        }
        if (getBase_row_total_incl_tax() != null) {
            _hashCode += getBase_row_total_incl_tax().hashCode();
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
        if (getTax_class_id() != null) {
            _hashCode += getTax_class_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartItemEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartItemEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "item_id"));
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
        elemField.setFieldName("store_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_item_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parent_item_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
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
        elemField.setFieldName("additional_data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additional_data"));
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
        elemField.setFieldName("weight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("custom_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "custom_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount_percent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_percent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discount_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "discount_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_discount_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_discount_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_percent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_percent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_tax_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_tax_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("row_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "row_total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_row_total");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_row_total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("row_total_with_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "row_total_with_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("row_weight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "row_weight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
        elemField.setFieldName("base_tax_before_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_tax_before_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_before_discount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_before_discount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_custom_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "original_custom_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_cost");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_cost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_price_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_price_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("row_total_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "row_total_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_row_total_incl_tax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_row_total_incl_tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
        elemField.setFieldName("weee_tax_applied");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_applied"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_applied_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_applied_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_applied_row_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_applied_row_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_applied_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_applied_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_applied_row_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_applied_row_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_disposition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weee_tax_row_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weee_tax_row_disposition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_disposition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_weee_tax_row_disposition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_weee_tax_row_disposition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax_class_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tax_class_id"));
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
