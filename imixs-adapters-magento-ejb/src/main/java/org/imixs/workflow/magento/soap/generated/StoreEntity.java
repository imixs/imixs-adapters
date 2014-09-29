/**
 * StoreEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class StoreEntity  implements java.io.Serializable {
    private int store_id;

    private java.lang.String code;

    private int website_id;

    private int group_id;

    private java.lang.String name;

    private int sort_order;

    private int is_active;

    public StoreEntity() {
    }

    public StoreEntity(
           int store_id,
           java.lang.String code,
           int website_id,
           int group_id,
           java.lang.String name,
           int sort_order,
           int is_active) {
           this.store_id = store_id;
           this.code = code;
           this.website_id = website_id;
           this.group_id = group_id;
           this.name = name;
           this.sort_order = sort_order;
           this.is_active = is_active;
    }


    /**
     * Gets the store_id value for this StoreEntity.
     * 
     * @return store_id
     */
    public int getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this StoreEntity.
     * 
     * @param store_id
     */
    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the code value for this StoreEntity.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this StoreEntity.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the website_id value for this StoreEntity.
     * 
     * @return website_id
     */
    public int getWebsite_id() {
        return website_id;
    }


    /**
     * Sets the website_id value for this StoreEntity.
     * 
     * @param website_id
     */
    public void setWebsite_id(int website_id) {
        this.website_id = website_id;
    }


    /**
     * Gets the group_id value for this StoreEntity.
     * 
     * @return group_id
     */
    public int getGroup_id() {
        return group_id;
    }


    /**
     * Sets the group_id value for this StoreEntity.
     * 
     * @param group_id
     */
    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }


    /**
     * Gets the name value for this StoreEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this StoreEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the sort_order value for this StoreEntity.
     * 
     * @return sort_order
     */
    public int getSort_order() {
        return sort_order;
    }


    /**
     * Sets the sort_order value for this StoreEntity.
     * 
     * @param sort_order
     */
    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }


    /**
     * Gets the is_active value for this StoreEntity.
     * 
     * @return is_active
     */
    public int getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this StoreEntity.
     * 
     * @param is_active
     */
    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StoreEntity)) return false;
        StoreEntity other = (StoreEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.store_id == other.getStore_id() &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            this.website_id == other.getWebsite_id() &&
            this.group_id == other.getGroup_id() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.sort_order == other.getSort_order() &&
            this.is_active == other.getIs_active();
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
        _hashCode += getStore_id();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        _hashCode += getWebsite_id();
        _hashCode += getGroup_id();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += getSort_order();
        _hashCode += getIs_active();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StoreEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "storeEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("website_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "website_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sort_order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sort_order"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_active");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
