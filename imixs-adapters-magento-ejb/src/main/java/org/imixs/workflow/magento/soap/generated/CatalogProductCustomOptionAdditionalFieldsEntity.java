/**
 * CatalogProductCustomOptionAdditionalFieldsEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductCustomOptionAdditionalFieldsEntity  implements java.io.Serializable {
    private java.lang.String title;

    private java.lang.String price;

    private java.lang.String price_type;

    private java.lang.String sku;

    private java.lang.String max_characters;

    private java.lang.String sort_order;

    private java.lang.String file_extension;

    private java.lang.String image_size_x;

    private java.lang.String image_size_y;

    private java.lang.String value_id;

    public CatalogProductCustomOptionAdditionalFieldsEntity() {
    }

    public CatalogProductCustomOptionAdditionalFieldsEntity(
           java.lang.String title,
           java.lang.String price,
           java.lang.String price_type,
           java.lang.String sku,
           java.lang.String max_characters,
           java.lang.String sort_order,
           java.lang.String file_extension,
           java.lang.String image_size_x,
           java.lang.String image_size_y,
           java.lang.String value_id) {
           this.title = title;
           this.price = price;
           this.price_type = price_type;
           this.sku = sku;
           this.max_characters = max_characters;
           this.sort_order = sort_order;
           this.file_extension = file_extension;
           this.image_size_x = image_size_x;
           this.image_size_y = image_size_y;
           this.value_id = value_id;
    }


    /**
     * Gets the title value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the price value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return price
     */
    public java.lang.String getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.String price) {
        this.price = price;
    }


    /**
     * Gets the price_type value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return price_type
     */
    public java.lang.String getPrice_type() {
        return price_type;
    }


    /**
     * Sets the price_type value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param price_type
     */
    public void setPrice_type(java.lang.String price_type) {
        this.price_type = price_type;
    }


    /**
     * Gets the sku value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return sku
     */
    public java.lang.String getSku() {
        return sku;
    }


    /**
     * Sets the sku value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param sku
     */
    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }


    /**
     * Gets the max_characters value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return max_characters
     */
    public java.lang.String getMax_characters() {
        return max_characters;
    }


    /**
     * Sets the max_characters value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param max_characters
     */
    public void setMax_characters(java.lang.String max_characters) {
        this.max_characters = max_characters;
    }


    /**
     * Gets the sort_order value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return sort_order
     */
    public java.lang.String getSort_order() {
        return sort_order;
    }


    /**
     * Sets the sort_order value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param sort_order
     */
    public void setSort_order(java.lang.String sort_order) {
        this.sort_order = sort_order;
    }


    /**
     * Gets the file_extension value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return file_extension
     */
    public java.lang.String getFile_extension() {
        return file_extension;
    }


    /**
     * Sets the file_extension value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param file_extension
     */
    public void setFile_extension(java.lang.String file_extension) {
        this.file_extension = file_extension;
    }


    /**
     * Gets the image_size_x value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return image_size_x
     */
    public java.lang.String getImage_size_x() {
        return image_size_x;
    }


    /**
     * Sets the image_size_x value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param image_size_x
     */
    public void setImage_size_x(java.lang.String image_size_x) {
        this.image_size_x = image_size_x;
    }


    /**
     * Gets the image_size_y value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return image_size_y
     */
    public java.lang.String getImage_size_y() {
        return image_size_y;
    }


    /**
     * Sets the image_size_y value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param image_size_y
     */
    public void setImage_size_y(java.lang.String image_size_y) {
        this.image_size_y = image_size_y;
    }


    /**
     * Gets the value_id value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @return value_id
     */
    public java.lang.String getValue_id() {
        return value_id;
    }


    /**
     * Sets the value_id value for this CatalogProductCustomOptionAdditionalFieldsEntity.
     * 
     * @param value_id
     */
    public void setValue_id(java.lang.String value_id) {
        this.value_id = value_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductCustomOptionAdditionalFieldsEntity)) return false;
        CatalogProductCustomOptionAdditionalFieldsEntity other = (CatalogProductCustomOptionAdditionalFieldsEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.price_type==null && other.getPrice_type()==null) || 
             (this.price_type!=null &&
              this.price_type.equals(other.getPrice_type()))) &&
            ((this.sku==null && other.getSku()==null) || 
             (this.sku!=null &&
              this.sku.equals(other.getSku()))) &&
            ((this.max_characters==null && other.getMax_characters()==null) || 
             (this.max_characters!=null &&
              this.max_characters.equals(other.getMax_characters()))) &&
            ((this.sort_order==null && other.getSort_order()==null) || 
             (this.sort_order!=null &&
              this.sort_order.equals(other.getSort_order()))) &&
            ((this.file_extension==null && other.getFile_extension()==null) || 
             (this.file_extension!=null &&
              this.file_extension.equals(other.getFile_extension()))) &&
            ((this.image_size_x==null && other.getImage_size_x()==null) || 
             (this.image_size_x!=null &&
              this.image_size_x.equals(other.getImage_size_x()))) &&
            ((this.image_size_y==null && other.getImage_size_y()==null) || 
             (this.image_size_y!=null &&
              this.image_size_y.equals(other.getImage_size_y()))) &&
            ((this.value_id==null && other.getValue_id()==null) || 
             (this.value_id!=null &&
              this.value_id.equals(other.getValue_id())));
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
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getPrice_type() != null) {
            _hashCode += getPrice_type().hashCode();
        }
        if (getSku() != null) {
            _hashCode += getSku().hashCode();
        }
        if (getMax_characters() != null) {
            _hashCode += getMax_characters().hashCode();
        }
        if (getSort_order() != null) {
            _hashCode += getSort_order().hashCode();
        }
        if (getFile_extension() != null) {
            _hashCode += getFile_extension().hashCode();
        }
        if (getImage_size_x() != null) {
            _hashCode += getImage_size_x().hashCode();
        }
        if (getImage_size_y() != null) {
            _hashCode += getImage_size_y().hashCode();
        }
        if (getValue_id() != null) {
            _hashCode += getValue_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogProductCustomOptionAdditionalFieldsEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductCustomOptionAdditionalFieldsEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
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
        elemField.setFieldName("price_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price_type"));
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
        elemField.setFieldName("max_characters");
        elemField.setXmlName(new javax.xml.namespace.QName("", "max_characters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sort_order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sort_order"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file_extension");
        elemField.setXmlName(new javax.xml.namespace.QName("", "file_extension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("image_size_x");
        elemField.setXmlName(new javax.xml.namespace.QName("", "image_size_x"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("image_size_y");
        elemField.setXmlName(new javax.xml.namespace.QName("", "image_size_y"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value_id"));
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
