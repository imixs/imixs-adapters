/**
 * ShoppingCartShippingMethodEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartShippingMethodEntity  implements java.io.Serializable {
    private java.lang.String code;

    private java.lang.String carrier;

    private java.lang.String carrier_title;

    private java.lang.String method;

    private java.lang.String method_title;

    private java.lang.String method_description;

    private java.lang.Double price;

    public ShoppingCartShippingMethodEntity() {
    }

    public ShoppingCartShippingMethodEntity(
           java.lang.String code,
           java.lang.String carrier,
           java.lang.String carrier_title,
           java.lang.String method,
           java.lang.String method_title,
           java.lang.String method_description,
           java.lang.Double price) {
           this.code = code;
           this.carrier = carrier;
           this.carrier_title = carrier_title;
           this.method = method;
           this.method_title = method_title;
           this.method_description = method_description;
           this.price = price;
    }


    /**
     * Gets the code value for this ShoppingCartShippingMethodEntity.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ShoppingCartShippingMethodEntity.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the carrier value for this ShoppingCartShippingMethodEntity.
     * 
     * @return carrier
     */
    public java.lang.String getCarrier() {
        return carrier;
    }


    /**
     * Sets the carrier value for this ShoppingCartShippingMethodEntity.
     * 
     * @param carrier
     */
    public void setCarrier(java.lang.String carrier) {
        this.carrier = carrier;
    }


    /**
     * Gets the carrier_title value for this ShoppingCartShippingMethodEntity.
     * 
     * @return carrier_title
     */
    public java.lang.String getCarrier_title() {
        return carrier_title;
    }


    /**
     * Sets the carrier_title value for this ShoppingCartShippingMethodEntity.
     * 
     * @param carrier_title
     */
    public void setCarrier_title(java.lang.String carrier_title) {
        this.carrier_title = carrier_title;
    }


    /**
     * Gets the method value for this ShoppingCartShippingMethodEntity.
     * 
     * @return method
     */
    public java.lang.String getMethod() {
        return method;
    }


    /**
     * Sets the method value for this ShoppingCartShippingMethodEntity.
     * 
     * @param method
     */
    public void setMethod(java.lang.String method) {
        this.method = method;
    }


    /**
     * Gets the method_title value for this ShoppingCartShippingMethodEntity.
     * 
     * @return method_title
     */
    public java.lang.String getMethod_title() {
        return method_title;
    }


    /**
     * Sets the method_title value for this ShoppingCartShippingMethodEntity.
     * 
     * @param method_title
     */
    public void setMethod_title(java.lang.String method_title) {
        this.method_title = method_title;
    }


    /**
     * Gets the method_description value for this ShoppingCartShippingMethodEntity.
     * 
     * @return method_description
     */
    public java.lang.String getMethod_description() {
        return method_description;
    }


    /**
     * Sets the method_description value for this ShoppingCartShippingMethodEntity.
     * 
     * @param method_description
     */
    public void setMethod_description(java.lang.String method_description) {
        this.method_description = method_description;
    }


    /**
     * Gets the price value for this ShoppingCartShippingMethodEntity.
     * 
     * @return price
     */
    public java.lang.Double getPrice() {
        return price;
    }


    /**
     * Sets the price value for this ShoppingCartShippingMethodEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.Double price) {
        this.price = price;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartShippingMethodEntity)) return false;
        ShoppingCartShippingMethodEntity other = (ShoppingCartShippingMethodEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.carrier==null && other.getCarrier()==null) || 
             (this.carrier!=null &&
              this.carrier.equals(other.getCarrier()))) &&
            ((this.carrier_title==null && other.getCarrier_title()==null) || 
             (this.carrier_title!=null &&
              this.carrier_title.equals(other.getCarrier_title()))) &&
            ((this.method==null && other.getMethod()==null) || 
             (this.method!=null &&
              this.method.equals(other.getMethod()))) &&
            ((this.method_title==null && other.getMethod_title()==null) || 
             (this.method_title!=null &&
              this.method_title.equals(other.getMethod_title()))) &&
            ((this.method_description==null && other.getMethod_description()==null) || 
             (this.method_description!=null &&
              this.method_description.equals(other.getMethod_description()))) &&
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getCarrier() != null) {
            _hashCode += getCarrier().hashCode();
        }
        if (getCarrier_title() != null) {
            _hashCode += getCarrier_title().hashCode();
        }
        if (getMethod() != null) {
            _hashCode += getMethod().hashCode();
        }
        if (getMethod_title() != null) {
            _hashCode += getMethod_title().hashCode();
        }
        if (getMethod_description() != null) {
            _hashCode += getMethod_description().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartShippingMethodEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartShippingMethodEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "carrier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrier_title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "carrier_title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method");
        elemField.setXmlName(new javax.xml.namespace.QName("", "method"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method_title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "method_title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method_description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "method_description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
