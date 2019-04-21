/**
 * CatalogProductCustomOptionInfoEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductCustomOptionInfoEntity  implements java.io.Serializable {
    private java.lang.String title;

    private java.lang.String type;

    private java.lang.String sort_order;

    private int is_require;

    private CatalogProductCustomOptionAdditionalFieldsEntity[] additional_fields;

    public CatalogProductCustomOptionInfoEntity() {
    }

    public CatalogProductCustomOptionInfoEntity(
           java.lang.String title,
           java.lang.String type,
           java.lang.String sort_order,
           int is_require,
           CatalogProductCustomOptionAdditionalFieldsEntity[] additional_fields) {
           this.title = title;
           this.type = type;
           this.sort_order = sort_order;
           this.is_require = is_require;
           this.additional_fields = additional_fields;
    }


    /**
     * Gets the title value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the type value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the sort_order value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @return sort_order
     */
    public java.lang.String getSort_order() {
        return sort_order;
    }


    /**
     * Sets the sort_order value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @param sort_order
     */
    public void setSort_order(java.lang.String sort_order) {
        this.sort_order = sort_order;
    }


    /**
     * Gets the is_require value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @return is_require
     */
    public int getIs_require() {
        return is_require;
    }


    /**
     * Sets the is_require value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @param is_require
     */
    public void setIs_require(int is_require) {
        this.is_require = is_require;
    }


    /**
     * Gets the additional_fields value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @return additional_fields
     */
    public CatalogProductCustomOptionAdditionalFieldsEntity[] getAdditional_fields() {
        return additional_fields;
    }


    /**
     * Sets the additional_fields value for this CatalogProductCustomOptionInfoEntity.
     * 
     * @param additional_fields
     */
    public void setAdditional_fields(CatalogProductCustomOptionAdditionalFieldsEntity[] additional_fields) {
        this.additional_fields = additional_fields;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductCustomOptionInfoEntity)) return false;
        CatalogProductCustomOptionInfoEntity other = (CatalogProductCustomOptionInfoEntity) obj;
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
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.sort_order==null && other.getSort_order()==null) || 
             (this.sort_order!=null &&
              this.sort_order.equals(other.getSort_order()))) &&
            this.is_require == other.getIs_require() &&
            ((this.additional_fields==null && other.getAdditional_fields()==null) || 
             (this.additional_fields!=null &&
              java.util.Arrays.equals(this.additional_fields, other.getAdditional_fields())));
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
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getSort_order() != null) {
            _hashCode += getSort_order().hashCode();
        }
        _hashCode += getIs_require();
        if (getAdditional_fields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditional_fields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditional_fields(), i);
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
        new org.apache.axis.description.TypeDesc(CatalogProductCustomOptionInfoEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductCustomOptionInfoEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional_fields");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additional_fields"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductCustomOptionAdditionalFieldsEntity"));
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
