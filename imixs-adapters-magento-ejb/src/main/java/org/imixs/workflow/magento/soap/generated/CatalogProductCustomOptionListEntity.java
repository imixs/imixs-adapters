/**
 * CatalogProductCustomOptionListEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductCustomOptionListEntity  implements java.io.Serializable {
    private java.lang.String option_id;

    private java.lang.String title;

    private java.lang.String type;

    private java.lang.String sort_order;

    private int is_require;

    public CatalogProductCustomOptionListEntity() {
    }

    public CatalogProductCustomOptionListEntity(
           java.lang.String option_id,
           java.lang.String title,
           java.lang.String type,
           java.lang.String sort_order,
           int is_require) {
           this.option_id = option_id;
           this.title = title;
           this.type = type;
           this.sort_order = sort_order;
           this.is_require = is_require;
    }


    /**
     * Gets the option_id value for this CatalogProductCustomOptionListEntity.
     * 
     * @return option_id
     */
    public java.lang.String getOption_id() {
        return option_id;
    }


    /**
     * Sets the option_id value for this CatalogProductCustomOptionListEntity.
     * 
     * @param option_id
     */
    public void setOption_id(java.lang.String option_id) {
        this.option_id = option_id;
    }


    /**
     * Gets the title value for this CatalogProductCustomOptionListEntity.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CatalogProductCustomOptionListEntity.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the type value for this CatalogProductCustomOptionListEntity.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this CatalogProductCustomOptionListEntity.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the sort_order value for this CatalogProductCustomOptionListEntity.
     * 
     * @return sort_order
     */
    public java.lang.String getSort_order() {
        return sort_order;
    }


    /**
     * Sets the sort_order value for this CatalogProductCustomOptionListEntity.
     * 
     * @param sort_order
     */
    public void setSort_order(java.lang.String sort_order) {
        this.sort_order = sort_order;
    }


    /**
     * Gets the is_require value for this CatalogProductCustomOptionListEntity.
     * 
     * @return is_require
     */
    public int getIs_require() {
        return is_require;
    }


    /**
     * Sets the is_require value for this CatalogProductCustomOptionListEntity.
     * 
     * @param is_require
     */
    public void setIs_require(int is_require) {
        this.is_require = is_require;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductCustomOptionListEntity)) return false;
        CatalogProductCustomOptionListEntity other = (CatalogProductCustomOptionListEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.option_id==null && other.getOption_id()==null) || 
             (this.option_id!=null &&
              this.option_id.equals(other.getOption_id()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.sort_order==null && other.getSort_order()==null) || 
             (this.sort_order!=null &&
              this.sort_order.equals(other.getSort_order()))) &&
            this.is_require == other.getIs_require();
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
        if (getOption_id() != null) {
            _hashCode += getOption_id().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getSort_order() != null) {
            _hashCode += getSort_order().hashCode();
        }
        _hashCode += getIs_require();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogProductCustomOptionListEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductCustomOptionListEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("option_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "option_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sort_order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sort_order"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_require");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_require"));
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
