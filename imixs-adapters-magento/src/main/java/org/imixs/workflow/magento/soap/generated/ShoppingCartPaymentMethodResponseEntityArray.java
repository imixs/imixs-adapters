/**
 * ShoppingCartPaymentMethodResponseEntityArray.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartPaymentMethodResponseEntityArray  implements java.io.Serializable {
    private java.lang.String code;

    private java.lang.String title;

    private AssociativeEntity[] cc_types;

    public ShoppingCartPaymentMethodResponseEntityArray() {
    }

    public ShoppingCartPaymentMethodResponseEntityArray(
           java.lang.String code,
           java.lang.String title,
           AssociativeEntity[] cc_types) {
           this.code = code;
           this.title = title;
           this.cc_types = cc_types;
    }


    /**
     * Gets the code value for this ShoppingCartPaymentMethodResponseEntityArray.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ShoppingCartPaymentMethodResponseEntityArray.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the title value for this ShoppingCartPaymentMethodResponseEntityArray.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this ShoppingCartPaymentMethodResponseEntityArray.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the cc_types value for this ShoppingCartPaymentMethodResponseEntityArray.
     * 
     * @return cc_types
     */
    public AssociativeEntity[] getCc_types() {
        return cc_types;
    }


    /**
     * Sets the cc_types value for this ShoppingCartPaymentMethodResponseEntityArray.
     * 
     * @param cc_types
     */
    public void setCc_types(AssociativeEntity[] cc_types) {
        this.cc_types = cc_types;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartPaymentMethodResponseEntityArray)) return false;
        ShoppingCartPaymentMethodResponseEntityArray other = (ShoppingCartPaymentMethodResponseEntityArray) obj;
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
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.cc_types==null && other.getCc_types()==null) || 
             (this.cc_types!=null &&
              java.util.Arrays.equals(this.cc_types, other.getCc_types())));
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
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getCc_types() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCc_types());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCc_types(), i);
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
        new org.apache.axis.description.TypeDesc(ShoppingCartPaymentMethodResponseEntityArray.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartPaymentMethodResponseEntityArray"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code"));
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
        elemField.setFieldName("cc_types");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_types"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "associativeEntity"));
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
