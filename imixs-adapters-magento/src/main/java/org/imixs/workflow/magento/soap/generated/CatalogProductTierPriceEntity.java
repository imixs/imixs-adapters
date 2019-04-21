/**
 * CatalogProductTierPriceEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductTierPriceEntity  implements java.io.Serializable {
    private java.lang.String customer_group_id;

    private java.lang.String website;

    private java.lang.Integer qty;

    private java.lang.Double price;

    public CatalogProductTierPriceEntity() {
    }

    public CatalogProductTierPriceEntity(
           java.lang.String customer_group_id,
           java.lang.String website,
           java.lang.Integer qty,
           java.lang.Double price) {
           this.customer_group_id = customer_group_id;
           this.website = website;
           this.qty = qty;
           this.price = price;
    }


    /**
     * Gets the customer_group_id value for this CatalogProductTierPriceEntity.
     * 
     * @return customer_group_id
     */
    public java.lang.String getCustomer_group_id() {
        return customer_group_id;
    }


    /**
     * Sets the customer_group_id value for this CatalogProductTierPriceEntity.
     * 
     * @param customer_group_id
     */
    public void setCustomer_group_id(java.lang.String customer_group_id) {
        this.customer_group_id = customer_group_id;
    }


    /**
     * Gets the website value for this CatalogProductTierPriceEntity.
     * 
     * @return website
     */
    public java.lang.String getWebsite() {
        return website;
    }


    /**
     * Sets the website value for this CatalogProductTierPriceEntity.
     * 
     * @param website
     */
    public void setWebsite(java.lang.String website) {
        this.website = website;
    }


    /**
     * Gets the qty value for this CatalogProductTierPriceEntity.
     * 
     * @return qty
     */
    public java.lang.Integer getQty() {
        return qty;
    }


    /**
     * Sets the qty value for this CatalogProductTierPriceEntity.
     * 
     * @param qty
     */
    public void setQty(java.lang.Integer qty) {
        this.qty = qty;
    }


    /**
     * Gets the price value for this CatalogProductTierPriceEntity.
     * 
     * @return price
     */
    public java.lang.Double getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CatalogProductTierPriceEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.Double price) {
        this.price = price;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductTierPriceEntity)) return false;
        CatalogProductTierPriceEntity other = (CatalogProductTierPriceEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customer_group_id==null && other.getCustomer_group_id()==null) || 
             (this.customer_group_id!=null &&
              this.customer_group_id.equals(other.getCustomer_group_id()))) &&
            ((this.website==null && other.getWebsite()==null) || 
             (this.website!=null &&
              this.website.equals(other.getWebsite()))) &&
            ((this.qty==null && other.getQty()==null) || 
             (this.qty!=null &&
              this.qty.equals(other.getQty()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice())));
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
        if (getCustomer_group_id() != null) {
            _hashCode += getCustomer_group_id().hashCode();
        }
        if (getWebsite() != null) {
            _hashCode += getWebsite().hashCode();
        }
        if (getQty() != null) {
            _hashCode += getQty().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogProductTierPriceEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductTierPriceEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_group_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_group_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("website");
        elemField.setXmlName(new javax.xml.namespace.QName("", "website"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
