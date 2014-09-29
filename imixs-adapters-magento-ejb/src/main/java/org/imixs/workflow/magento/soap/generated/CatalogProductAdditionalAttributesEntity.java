/**
 * CatalogProductAdditionalAttributesEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductAdditionalAttributesEntity  implements java.io.Serializable {
    private AssociativeMultiEntity[] multi_data;

    private AssociativeEntity[] single_data;

    public CatalogProductAdditionalAttributesEntity() {
    }

    public CatalogProductAdditionalAttributesEntity(
           AssociativeMultiEntity[] multi_data,
           AssociativeEntity[] single_data) {
           this.multi_data = multi_data;
           this.single_data = single_data;
    }


    /**
     * Gets the multi_data value for this CatalogProductAdditionalAttributesEntity.
     * 
     * @return multi_data
     */
    public AssociativeMultiEntity[] getMulti_data() {
        return multi_data;
    }


    /**
     * Sets the multi_data value for this CatalogProductAdditionalAttributesEntity.
     * 
     * @param multi_data
     */
    public void setMulti_data(AssociativeMultiEntity[] multi_data) {
        this.multi_data = multi_data;
    }


    /**
     * Gets the single_data value for this CatalogProductAdditionalAttributesEntity.
     * 
     * @return single_data
     */
    public AssociativeEntity[] getSingle_data() {
        return single_data;
    }


    /**
     * Sets the single_data value for this CatalogProductAdditionalAttributesEntity.
     * 
     * @param single_data
     */
    public void setSingle_data(AssociativeEntity[] single_data) {
        this.single_data = single_data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductAdditionalAttributesEntity)) return false;
        CatalogProductAdditionalAttributesEntity other = (CatalogProductAdditionalAttributesEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.multi_data==null && other.getMulti_data()==null) || 
             (this.multi_data!=null &&
              java.util.Arrays.equals(this.multi_data, other.getMulti_data()))) &&
            ((this.single_data==null && other.getSingle_data()==null) || 
             (this.single_data!=null &&
              java.util.Arrays.equals(this.single_data, other.getSingle_data())));
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
        if (getMulti_data() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMulti_data());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMulti_data(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSingle_data() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSingle_data());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSingle_data(), i);
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
        new org.apache.axis.description.TypeDesc(CatalogProductAdditionalAttributesEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductAdditionalAttributesEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multi_data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "multi_data"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "associativeMultiEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("single_data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "single_data"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "associativeEntity"));
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
