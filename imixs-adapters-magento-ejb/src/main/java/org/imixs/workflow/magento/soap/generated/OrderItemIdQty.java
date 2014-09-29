/**
 * OrderItemIdQty.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class OrderItemIdQty  implements java.io.Serializable {
    private int order_item_id;

    private double qty;

    public OrderItemIdQty() {
    }

    public OrderItemIdQty(
           int order_item_id,
           double qty) {
           this.order_item_id = order_item_id;
           this.qty = qty;
    }


    /**
     * Gets the order_item_id value for this OrderItemIdQty.
     * 
     * @return order_item_id
     */
    public int getOrder_item_id() {
        return order_item_id;
    }


    /**
     * Sets the order_item_id value for this OrderItemIdQty.
     * 
     * @param order_item_id
     */
    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }


    /**
     * Gets the qty value for this OrderItemIdQty.
     * 
     * @return qty
     */
    public double getQty() {
        return qty;
    }


    /**
     * Sets the qty value for this OrderItemIdQty.
     * 
     * @param qty
     */
    public void setQty(double qty) {
        this.qty = qty;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrderItemIdQty)) return false;
        OrderItemIdQty other = (OrderItemIdQty) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.order_item_id == other.getOrder_item_id() &&
            this.qty == other.getQty();
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
        _hashCode += getOrder_item_id();
        _hashCode += new Double(getQty()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderItemIdQty.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "orderItemIdQty"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order_item_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_item_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
