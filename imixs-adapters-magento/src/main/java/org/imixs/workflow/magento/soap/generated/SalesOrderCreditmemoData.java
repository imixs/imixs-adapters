/**
 * SalesOrderCreditmemoData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderCreditmemoData  implements java.io.Serializable {
    private OrderItemIdQty[] qtys;

    private java.lang.Double shipping_amount;

    private java.lang.Double adjustment_positive;

    private java.lang.Double adjustment_negative;

    public SalesOrderCreditmemoData() {
    }

    public SalesOrderCreditmemoData(
           OrderItemIdQty[] qtys,
           java.lang.Double shipping_amount,
           java.lang.Double adjustment_positive,
           java.lang.Double adjustment_negative) {
           this.qtys = qtys;
           this.shipping_amount = shipping_amount;
           this.adjustment_positive = adjustment_positive;
           this.adjustment_negative = adjustment_negative;
    }


    /**
     * Gets the qtys value for this SalesOrderCreditmemoData.
     * 
     * @return qtys
     */
    public OrderItemIdQty[] getQtys() {
        return qtys;
    }


    /**
     * Sets the qtys value for this SalesOrderCreditmemoData.
     * 
     * @param qtys
     */
    public void setQtys(OrderItemIdQty[] qtys) {
        this.qtys = qtys;
    }


    /**
     * Gets the shipping_amount value for this SalesOrderCreditmemoData.
     * 
     * @return shipping_amount
     */
    public java.lang.Double getShipping_amount() {
        return shipping_amount;
    }


    /**
     * Sets the shipping_amount value for this SalesOrderCreditmemoData.
     * 
     * @param shipping_amount
     */
    public void setShipping_amount(java.lang.Double shipping_amount) {
        this.shipping_amount = shipping_amount;
    }


    /**
     * Gets the adjustment_positive value for this SalesOrderCreditmemoData.
     * 
     * @return adjustment_positive
     */
    public java.lang.Double getAdjustment_positive() {
        return adjustment_positive;
    }


    /**
     * Sets the adjustment_positive value for this SalesOrderCreditmemoData.
     * 
     * @param adjustment_positive
     */
    public void setAdjustment_positive(java.lang.Double adjustment_positive) {
        this.adjustment_positive = adjustment_positive;
    }


    /**
     * Gets the adjustment_negative value for this SalesOrderCreditmemoData.
     * 
     * @return adjustment_negative
     */
    public java.lang.Double getAdjustment_negative() {
        return adjustment_negative;
    }


    /**
     * Sets the adjustment_negative value for this SalesOrderCreditmemoData.
     * 
     * @param adjustment_negative
     */
    public void setAdjustment_negative(java.lang.Double adjustment_negative) {
        this.adjustment_negative = adjustment_negative;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderCreditmemoData)) return false;
        SalesOrderCreditmemoData other = (SalesOrderCreditmemoData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.qtys==null && other.getQtys()==null) || 
             (this.qtys!=null &&
              java.util.Arrays.equals(this.qtys, other.getQtys()))) &&
            ((this.shipping_amount==null && other.getShipping_amount()==null) || 
             (this.shipping_amount!=null &&
              this.shipping_amount.equals(other.getShipping_amount()))) &&
            ((this.adjustment_positive==null && other.getAdjustment_positive()==null) || 
             (this.adjustment_positive!=null &&
              this.adjustment_positive.equals(other.getAdjustment_positive()))) &&
            ((this.adjustment_negative==null && other.getAdjustment_negative()==null) || 
             (this.adjustment_negative!=null &&
              this.adjustment_negative.equals(other.getAdjustment_negative())));
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
        if (getQtys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQtys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQtys(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getShipping_amount() != null) {
            _hashCode += getShipping_amount().hashCode();
        }
        if (getAdjustment_positive() != null) {
            _hashCode += getAdjustment_positive().hashCode();
        }
        if (getAdjustment_negative() != null) {
            _hashCode += getAdjustment_negative().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderCreditmemoData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderCreditmemoData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtys");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qtys"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "orderItemIdQty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adjustment_positive");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adjustment_positive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adjustment_negative");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adjustment_negative"));
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
