/**
 * CatalogProductRequestAttributes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductRequestAttributes  implements java.io.Serializable {
    private java.lang.String[] attributes;

    private java.lang.String[] additional_attributes;

    public CatalogProductRequestAttributes() {
    }

    public CatalogProductRequestAttributes(
           java.lang.String[] attributes,
           java.lang.String[] additional_attributes) {
           this.attributes = attributes;
           this.additional_attributes = additional_attributes;
    }


    /**
     * Gets the attributes value for this CatalogProductRequestAttributes.
     * 
     * @return attributes
     */
    public java.lang.String[] getAttributes() {
        return attributes;
    }


    /**
     * Sets the attributes value for this CatalogProductRequestAttributes.
     * 
     * @param attributes
     */
    public void setAttributes(java.lang.String[] attributes) {
        this.attributes = attributes;
    }


    /**
     * Gets the additional_attributes value for this CatalogProductRequestAttributes.
     * 
     * @return additional_attributes
     */
    public java.lang.String[] getAdditional_attributes() {
        return additional_attributes;
    }


    /**
     * Sets the additional_attributes value for this CatalogProductRequestAttributes.
     * 
     * @param additional_attributes
     */
    public void setAdditional_attributes(java.lang.String[] additional_attributes) {
        this.additional_attributes = additional_attributes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductRequestAttributes)) return false;
        CatalogProductRequestAttributes other = (CatalogProductRequestAttributes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attributes==null && other.getAttributes()==null) || 
             (this.attributes!=null &&
              java.util.Arrays.equals(this.attributes, other.getAttributes()))) &&
            ((this.additional_attributes==null && other.getAdditional_attributes()==null) || 
             (this.additional_attributes!=null &&
              java.util.Arrays.equals(this.additional_attributes, other.getAdditional_attributes())));
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
        if (getAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAdditional_attributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditional_attributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditional_attributes(), i);
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
        new org.apache.axis.description.TypeDesc(CatalogProductRequestAttributes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductRequestAttributes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional_attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additional_attributes"));
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
