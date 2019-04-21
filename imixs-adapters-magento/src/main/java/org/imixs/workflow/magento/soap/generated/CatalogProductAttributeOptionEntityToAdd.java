/**
 * CatalogProductAttributeOptionEntityToAdd.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductAttributeOptionEntityToAdd  implements java.io.Serializable {
    private CatalogProductAttributeOptionLabelEntity[] label;

    private int order;

    private int is_default;

    public CatalogProductAttributeOptionEntityToAdd() {
    }

    public CatalogProductAttributeOptionEntityToAdd(
           CatalogProductAttributeOptionLabelEntity[] label,
           int order,
           int is_default) {
           this.label = label;
           this.order = order;
           this.is_default = is_default;
    }


    /**
     * Gets the label value for this CatalogProductAttributeOptionEntityToAdd.
     * 
     * @return label
     */
    public CatalogProductAttributeOptionLabelEntity[] getLabel() {
        return label;
    }


    /**
     * Sets the label value for this CatalogProductAttributeOptionEntityToAdd.
     * 
     * @param label
     */
    public void setLabel(CatalogProductAttributeOptionLabelEntity[] label) {
        this.label = label;
    }


    /**
     * Gets the order value for this CatalogProductAttributeOptionEntityToAdd.
     * 
     * @return order
     */
    public int getOrder() {
        return order;
    }


    /**
     * Sets the order value for this CatalogProductAttributeOptionEntityToAdd.
     * 
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }


    /**
     * Gets the is_default value for this CatalogProductAttributeOptionEntityToAdd.
     * 
     * @return is_default
     */
    public int getIs_default() {
        return is_default;
    }


    /**
     * Sets the is_default value for this CatalogProductAttributeOptionEntityToAdd.
     * 
     * @param is_default
     */
    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductAttributeOptionEntityToAdd)) return false;
        CatalogProductAttributeOptionEntityToAdd other = (CatalogProductAttributeOptionEntityToAdd) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              java.util.Arrays.equals(this.label, other.getLabel()))) &&
            this.order == other.getOrder() &&
            this.is_default == other.getIs_default();
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
        if (getLabel() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLabel());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLabel(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getOrder();
        _hashCode += getIs_default();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogProductAttributeOptionEntityToAdd.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductAttributeOptionEntityToAdd"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductAttributeOptionLabelEntity"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_default");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_default"));
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
