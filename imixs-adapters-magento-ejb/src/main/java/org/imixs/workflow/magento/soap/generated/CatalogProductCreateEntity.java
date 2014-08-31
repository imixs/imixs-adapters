/**
 * CatalogProductCreateEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductCreateEntity  implements java.io.Serializable {
    private java.lang.String[] categories;

    private java.lang.String[] websites;

    private java.lang.String name;

    private java.lang.String description;

    private java.lang.String short_description;

    private java.lang.String weight;

    private java.lang.String status;

    private java.lang.String url_key;

    private java.lang.String url_path;

    private java.lang.String visibility;

    private java.lang.String[] category_ids;

    private java.lang.String[] website_ids;

    private java.lang.String has_options;

    private java.lang.String gift_message_available;

    private java.lang.String price;

    private java.lang.String special_price;

    private java.lang.String special_from_date;

    private java.lang.String special_to_date;

    private java.lang.String tax_class_id;

    private CatalogProductTierPriceEntity[] tier_price;

    private java.lang.String meta_title;

    private java.lang.String meta_keyword;

    private java.lang.String meta_description;

    private java.lang.String custom_design;

    private java.lang.String custom_layout_update;

    private java.lang.String options_container;

    private CatalogProductAdditionalAttributesEntity additional_attributes;

    private CatalogInventoryStockItemUpdateEntity stock_data;

    public CatalogProductCreateEntity() {
    }

    public CatalogProductCreateEntity(
           java.lang.String[] categories,
           java.lang.String[] websites,
           java.lang.String name,
           java.lang.String description,
           java.lang.String short_description,
           java.lang.String weight,
           java.lang.String status,
           java.lang.String url_key,
           java.lang.String url_path,
           java.lang.String visibility,
           java.lang.String[] category_ids,
           java.lang.String[] website_ids,
           java.lang.String has_options,
           java.lang.String gift_message_available,
           java.lang.String price,
           java.lang.String special_price,
           java.lang.String special_from_date,
           java.lang.String special_to_date,
           java.lang.String tax_class_id,
           CatalogProductTierPriceEntity[] tier_price,
           java.lang.String meta_title,
           java.lang.String meta_keyword,
           java.lang.String meta_description,
           java.lang.String custom_design,
           java.lang.String custom_layout_update,
           java.lang.String options_container,
           CatalogProductAdditionalAttributesEntity additional_attributes,
           CatalogInventoryStockItemUpdateEntity stock_data) {
           this.categories = categories;
           this.websites = websites;
           this.name = name;
           this.description = description;
           this.short_description = short_description;
           this.weight = weight;
           this.status = status;
           this.url_key = url_key;
           this.url_path = url_path;
           this.visibility = visibility;
           this.category_ids = category_ids;
           this.website_ids = website_ids;
           this.has_options = has_options;
           this.gift_message_available = gift_message_available;
           this.price = price;
           this.special_price = special_price;
           this.special_from_date = special_from_date;
           this.special_to_date = special_to_date;
           this.tax_class_id = tax_class_id;
           this.tier_price = tier_price;
           this.meta_title = meta_title;
           this.meta_keyword = meta_keyword;
           this.meta_description = meta_description;
           this.custom_design = custom_design;
           this.custom_layout_update = custom_layout_update;
           this.options_container = options_container;
           this.additional_attributes = additional_attributes;
           this.stock_data = stock_data;
    }


    /**
     * Gets the categories value for this CatalogProductCreateEntity.
     * 
     * @return categories
     */
    public java.lang.String[] getCategories() {
        return categories;
    }


    /**
     * Sets the categories value for this CatalogProductCreateEntity.
     * 
     * @param categories
     */
    public void setCategories(java.lang.String[] categories) {
        this.categories = categories;
    }


    /**
     * Gets the websites value for this CatalogProductCreateEntity.
     * 
     * @return websites
     */
    public java.lang.String[] getWebsites() {
        return websites;
    }


    /**
     * Sets the websites value for this CatalogProductCreateEntity.
     * 
     * @param websites
     */
    public void setWebsites(java.lang.String[] websites) {
        this.websites = websites;
    }


    /**
     * Gets the name value for this CatalogProductCreateEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CatalogProductCreateEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this CatalogProductCreateEntity.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this CatalogProductCreateEntity.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the short_description value for this CatalogProductCreateEntity.
     * 
     * @return short_description
     */
    public java.lang.String getShort_description() {
        return short_description;
    }


    /**
     * Sets the short_description value for this CatalogProductCreateEntity.
     * 
     * @param short_description
     */
    public void setShort_description(java.lang.String short_description) {
        this.short_description = short_description;
    }


    /**
     * Gets the weight value for this CatalogProductCreateEntity.
     * 
     * @return weight
     */
    public java.lang.String getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this CatalogProductCreateEntity.
     * 
     * @param weight
     */
    public void setWeight(java.lang.String weight) {
        this.weight = weight;
    }


    /**
     * Gets the status value for this CatalogProductCreateEntity.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CatalogProductCreateEntity.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the url_key value for this CatalogProductCreateEntity.
     * 
     * @return url_key
     */
    public java.lang.String getUrl_key() {
        return url_key;
    }


    /**
     * Sets the url_key value for this CatalogProductCreateEntity.
     * 
     * @param url_key
     */
    public void setUrl_key(java.lang.String url_key) {
        this.url_key = url_key;
    }


    /**
     * Gets the url_path value for this CatalogProductCreateEntity.
     * 
     * @return url_path
     */
    public java.lang.String getUrl_path() {
        return url_path;
    }


    /**
     * Sets the url_path value for this CatalogProductCreateEntity.
     * 
     * @param url_path
     */
    public void setUrl_path(java.lang.String url_path) {
        this.url_path = url_path;
    }


    /**
     * Gets the visibility value for this CatalogProductCreateEntity.
     * 
     * @return visibility
     */
    public java.lang.String getVisibility() {
        return visibility;
    }


    /**
     * Sets the visibility value for this CatalogProductCreateEntity.
     * 
     * @param visibility
     */
    public void setVisibility(java.lang.String visibility) {
        this.visibility = visibility;
    }


    /**
     * Gets the category_ids value for this CatalogProductCreateEntity.
     * 
     * @return category_ids
     */
    public java.lang.String[] getCategory_ids() {
        return category_ids;
    }


    /**
     * Sets the category_ids value for this CatalogProductCreateEntity.
     * 
     * @param category_ids
     */
    public void setCategory_ids(java.lang.String[] category_ids) {
        this.category_ids = category_ids;
    }


    /**
     * Gets the website_ids value for this CatalogProductCreateEntity.
     * 
     * @return website_ids
     */
    public java.lang.String[] getWebsite_ids() {
        return website_ids;
    }


    /**
     * Sets the website_ids value for this CatalogProductCreateEntity.
     * 
     * @param website_ids
     */
    public void setWebsite_ids(java.lang.String[] website_ids) {
        this.website_ids = website_ids;
    }


    /**
     * Gets the has_options value for this CatalogProductCreateEntity.
     * 
     * @return has_options
     */
    public java.lang.String getHas_options() {
        return has_options;
    }


    /**
     * Sets the has_options value for this CatalogProductCreateEntity.
     * 
     * @param has_options
     */
    public void setHas_options(java.lang.String has_options) {
        this.has_options = has_options;
    }


    /**
     * Gets the gift_message_available value for this CatalogProductCreateEntity.
     * 
     * @return gift_message_available
     */
    public java.lang.String getGift_message_available() {
        return gift_message_available;
    }


    /**
     * Sets the gift_message_available value for this CatalogProductCreateEntity.
     * 
     * @param gift_message_available
     */
    public void setGift_message_available(java.lang.String gift_message_available) {
        this.gift_message_available = gift_message_available;
    }


    /**
     * Gets the price value for this CatalogProductCreateEntity.
     * 
     * @return price
     */
    public java.lang.String getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CatalogProductCreateEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.String price) {
        this.price = price;
    }


    /**
     * Gets the special_price value for this CatalogProductCreateEntity.
     * 
     * @return special_price
     */
    public java.lang.String getSpecial_price() {
        return special_price;
    }


    /**
     * Sets the special_price value for this CatalogProductCreateEntity.
     * 
     * @param special_price
     */
    public void setSpecial_price(java.lang.String special_price) {
        this.special_price = special_price;
    }


    /**
     * Gets the special_from_date value for this CatalogProductCreateEntity.
     * 
     * @return special_from_date
     */
    public java.lang.String getSpecial_from_date() {
        return special_from_date;
    }


    /**
     * Sets the special_from_date value for this CatalogProductCreateEntity.
     * 
     * @param special_from_date
     */
    public void setSpecial_from_date(java.lang.String special_from_date) {
        this.special_from_date = special_from_date;
    }


    /**
     * Gets the special_to_date value for this CatalogProductCreateEntity.
     * 
     * @return special_to_date
     */
    public java.lang.String getSpecial_to_date() {
        return special_to_date;
    }


    /**
     * Sets the special_to_date value for this CatalogProductCreateEntity.
     * 
     * @param special_to_date
     */
    public void setSpecial_to_date(java.lang.String special_to_date) {
        this.special_to_date = special_to_date;
    }


    /**
     * Gets the tax_class_id value for this CatalogProductCreateEntity.
     * 
     * @return tax_class_id
     */
    public java.lang.String getTax_class_id() {
        return tax_class_id;
    }


    /**
     * Sets the tax_class_id value for this CatalogProductCreateEntity.
     * 
     * @param tax_class_id
     */
    public void setTax_class_id(java.lang.String tax_class_id) {
        this.tax_class_id = tax_class_id;
    }


    /**
     * Gets the tier_price value for this CatalogProductCreateEntity.
     * 
     * @return tier_price
     */
    public CatalogProductTierPriceEntity[] getTier_price() {
        return tier_price;
    }


    /**
     * Sets the tier_price value for this CatalogProductCreateEntity.
     * 
     * @param tier_price
     */
    public void setTier_price(CatalogProductTierPriceEntity[] tier_price) {
        this.tier_price = tier_price;
    }


    /**
     * Gets the meta_title value for this CatalogProductCreateEntity.
     * 
     * @return meta_title
     */
    public java.lang.String getMeta_title() {
        return meta_title;
    }


    /**
     * Sets the meta_title value for this CatalogProductCreateEntity.
     * 
     * @param meta_title
     */
    public void setMeta_title(java.lang.String meta_title) {
        this.meta_title = meta_title;
    }


    /**
     * Gets the meta_keyword value for this CatalogProductCreateEntity.
     * 
     * @return meta_keyword
     */
    public java.lang.String getMeta_keyword() {
        return meta_keyword;
    }


    /**
     * Sets the meta_keyword value for this CatalogProductCreateEntity.
     * 
     * @param meta_keyword
     */
    public void setMeta_keyword(java.lang.String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }


    /**
     * Gets the meta_description value for this CatalogProductCreateEntity.
     * 
     * @return meta_description
     */
    public java.lang.String getMeta_description() {
        return meta_description;
    }


    /**
     * Sets the meta_description value for this CatalogProductCreateEntity.
     * 
     * @param meta_description
     */
    public void setMeta_description(java.lang.String meta_description) {
        this.meta_description = meta_description;
    }


    /**
     * Gets the custom_design value for this CatalogProductCreateEntity.
     * 
     * @return custom_design
     */
    public java.lang.String getCustom_design() {
        return custom_design;
    }


    /**
     * Sets the custom_design value for this CatalogProductCreateEntity.
     * 
     * @param custom_design
     */
    public void setCustom_design(java.lang.String custom_design) {
        this.custom_design = custom_design;
    }


    /**
     * Gets the custom_layout_update value for this CatalogProductCreateEntity.
     * 
     * @return custom_layout_update
     */
    public java.lang.String getCustom_layout_update() {
        return custom_layout_update;
    }


    /**
     * Sets the custom_layout_update value for this CatalogProductCreateEntity.
     * 
     * @param custom_layout_update
     */
    public void setCustom_layout_update(java.lang.String custom_layout_update) {
        this.custom_layout_update = custom_layout_update;
    }


    /**
     * Gets the options_container value for this CatalogProductCreateEntity.
     * 
     * @return options_container
     */
    public java.lang.String getOptions_container() {
        return options_container;
    }


    /**
     * Sets the options_container value for this CatalogProductCreateEntity.
     * 
     * @param options_container
     */
    public void setOptions_container(java.lang.String options_container) {
        this.options_container = options_container;
    }


    /**
     * Gets the additional_attributes value for this CatalogProductCreateEntity.
     * 
     * @return additional_attributes
     */
    public CatalogProductAdditionalAttributesEntity getAdditional_attributes() {
        return additional_attributes;
    }


    /**
     * Sets the additional_attributes value for this CatalogProductCreateEntity.
     * 
     * @param additional_attributes
     */
    public void setAdditional_attributes(CatalogProductAdditionalAttributesEntity additional_attributes) {
        this.additional_attributes = additional_attributes;
    }


    /**
     * Gets the stock_data value for this CatalogProductCreateEntity.
     * 
     * @return stock_data
     */
    public CatalogInventoryStockItemUpdateEntity getStock_data() {
        return stock_data;
    }


    /**
     * Sets the stock_data value for this CatalogProductCreateEntity.
     * 
     * @param stock_data
     */
    public void setStock_data(CatalogInventoryStockItemUpdateEntity stock_data) {
        this.stock_data = stock_data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductCreateEntity)) return false;
        CatalogProductCreateEntity other = (CatalogProductCreateEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.categories==null && other.getCategories()==null) || 
             (this.categories!=null &&
              java.util.Arrays.equals(this.categories, other.getCategories()))) &&
            ((this.websites==null && other.getWebsites()==null) || 
             (this.websites!=null &&
              java.util.Arrays.equals(this.websites, other.getWebsites()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.short_description==null && other.getShort_description()==null) || 
             (this.short_description!=null &&
              this.short_description.equals(other.getShort_description()))) &&
            ((this.weight==null && other.getWeight()==null) || 
             (this.weight!=null &&
              this.weight.equals(other.getWeight()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.url_key==null && other.getUrl_key()==null) || 
             (this.url_key!=null &&
              this.url_key.equals(other.getUrl_key()))) &&
            ((this.url_path==null && other.getUrl_path()==null) || 
             (this.url_path!=null &&
              this.url_path.equals(other.getUrl_path()))) &&
            ((this.visibility==null && other.getVisibility()==null) || 
             (this.visibility!=null &&
              this.visibility.equals(other.getVisibility()))) &&
            ((this.category_ids==null && other.getCategory_ids()==null) || 
             (this.category_ids!=null &&
              java.util.Arrays.equals(this.category_ids, other.getCategory_ids()))) &&
            ((this.website_ids==null && other.getWebsite_ids()==null) || 
             (this.website_ids!=null &&
              java.util.Arrays.equals(this.website_ids, other.getWebsite_ids()))) &&
            ((this.has_options==null && other.getHas_options()==null) || 
             (this.has_options!=null &&
              this.has_options.equals(other.getHas_options()))) &&
            ((this.gift_message_available==null && other.getGift_message_available()==null) || 
             (this.gift_message_available!=null &&
              this.gift_message_available.equals(other.getGift_message_available()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.special_price==null && other.getSpecial_price()==null) || 
             (this.special_price!=null &&
              this.special_price.equals(other.getSpecial_price()))) &&
            ((this.special_from_date==null && other.getSpecial_from_date()==null) || 
             (this.special_from_date!=null &&
              this.special_from_date.equals(other.getSpecial_from_date()))) &&
            ((this.special_to_date==null && other.getSpecial_to_date()==null) || 
             (this.special_to_date!=null &&
              this.special_to_date.equals(other.getSpecial_to_date()))) &&
            ((this.tax_class_id==null && other.getTax_class_id()==null) || 
             (this.tax_class_id!=null &&
              this.tax_class_id.equals(other.getTax_class_id()))) &&
            ((this.tier_price==null && other.getTier_price()==null) || 
             (this.tier_price!=null &&
              java.util.Arrays.equals(this.tier_price, other.getTier_price()))) &&
            ((this.meta_title==null && other.getMeta_title()==null) || 
             (this.meta_title!=null &&
              this.meta_title.equals(other.getMeta_title()))) &&
            ((this.meta_keyword==null && other.getMeta_keyword()==null) || 
             (this.meta_keyword!=null &&
              this.meta_keyword.equals(other.getMeta_keyword()))) &&
            ((this.meta_description==null && other.getMeta_description()==null) || 
             (this.meta_description!=null &&
              this.meta_description.equals(other.getMeta_description()))) &&
            ((this.custom_design==null && other.getCustom_design()==null) || 
             (this.custom_design!=null &&
              this.custom_design.equals(other.getCustom_design()))) &&
            ((this.custom_layout_update==null && other.getCustom_layout_update()==null) || 
             (this.custom_layout_update!=null &&
              this.custom_layout_update.equals(other.getCustom_layout_update()))) &&
            ((this.options_container==null && other.getOptions_container()==null) || 
             (this.options_container!=null &&
              this.options_container.equals(other.getOptions_container()))) &&
            ((this.additional_attributes==null && other.getAdditional_attributes()==null) || 
             (this.additional_attributes!=null &&
              this.additional_attributes.equals(other.getAdditional_attributes()))) &&
            ((this.stock_data==null && other.getStock_data()==null) || 
             (this.stock_data!=null &&
              this.stock_data.equals(other.getStock_data())));
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
        if (getCategories() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCategories());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCategories(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWebsites() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWebsites());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWebsites(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getShort_description() != null) {
            _hashCode += getShort_description().hashCode();
        }
        if (getWeight() != null) {
            _hashCode += getWeight().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getUrl_key() != null) {
            _hashCode += getUrl_key().hashCode();
        }
        if (getUrl_path() != null) {
            _hashCode += getUrl_path().hashCode();
        }
        if (getVisibility() != null) {
            _hashCode += getVisibility().hashCode();
        }
        if (getCategory_ids() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCategory_ids());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCategory_ids(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWebsite_ids() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWebsite_ids());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWebsite_ids(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHas_options() != null) {
            _hashCode += getHas_options().hashCode();
        }
        if (getGift_message_available() != null) {
            _hashCode += getGift_message_available().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getSpecial_price() != null) {
            _hashCode += getSpecial_price().hashCode();
        }
        if (getSpecial_from_date() != null) {
            _hashCode += getSpecial_from_date().hashCode();
        }
        if (getSpecial_to_date() != null) {
            _hashCode += getSpecial_to_date().hashCode();
        }
        if (getTax_class_id() != null) {
            _hashCode += getTax_class_id().hashCode();
        }
        if (getTier_price() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTier_price());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTier_price(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMeta_title() != null) {
            _hashCode += getMeta_title().hashCode();
        }
        if (getMeta_keyword() != null) {
            _hashCode += getMeta_keyword().hashCode();
        }
        if (getMeta_description() != null) {
            _hashCode += getMeta_description().hashCode();
        }
        if (getCustom_design() != null) {
            _hashCode += getCustom_design().hashCode();
        }
        if (getCustom_layout_update() != null) {
            _hashCode += getCustom_layout_update().hashCode();
        }
        if (getOptions_container() != null) {
            _hashCode += getOptions_container().hashCode();
        }
        if (getAdditional_attributes() != null) {
            _hashCode += getAdditional_attributes().hashCode();
        }
        if (getStock_data() != null) {
            _hashCode += getStock_data().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogProductCreateEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductCreateEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categories");
        elemField.setXmlName(new javax.xml.namespace.QName("", "categories"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("websites");
        elemField.setXmlName(new javax.xml.namespace.QName("", "websites"));
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
        elemField.setFieldName("short_description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "short_description"));
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
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url_key");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url_key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url_path");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url_path"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visibility");
        elemField.setXmlName(new javax.xml.namespace.QName("", "visibility"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category_ids");
        elemField.setXmlName(new javax.xml.namespace.QName("", "category_ids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("website_ids");
        elemField.setXmlName(new javax.xml.namespace.QName("", "website_ids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("has_options");
        elemField.setXmlName(new javax.xml.namespace.QName("", "has_options"));
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
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "special_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_from_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "special_from_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_to_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "special_to_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tier_price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tier_price"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductTierPriceEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meta_title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "meta_title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meta_keyword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "meta_keyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meta_description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "meta_description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("custom_design");
        elemField.setXmlName(new javax.xml.namespace.QName("", "custom_design"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("custom_layout_update");
        elemField.setXmlName(new javax.xml.namespace.QName("", "custom_layout_update"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options_container");
        elemField.setXmlName(new javax.xml.namespace.QName("", "options_container"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional_attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additional_attributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductAdditionalAttributesEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stock_data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stock_data"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogInventoryStockItemUpdateEntity"));
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
