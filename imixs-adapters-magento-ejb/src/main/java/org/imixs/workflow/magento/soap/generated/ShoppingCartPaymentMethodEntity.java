/**
 * ShoppingCartPaymentMethodEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartPaymentMethodEntity  implements java.io.Serializable {
    private java.lang.String po_number;

    private java.lang.String method;

    private java.lang.String cc_cid;

    private java.lang.String cc_owner;

    private java.lang.String cc_number;

    private java.lang.String cc_type;

    private java.lang.String cc_exp_year;

    private java.lang.String cc_exp_month;

    public ShoppingCartPaymentMethodEntity() {
    }

    public ShoppingCartPaymentMethodEntity(
           java.lang.String po_number,
           java.lang.String method,
           java.lang.String cc_cid,
           java.lang.String cc_owner,
           java.lang.String cc_number,
           java.lang.String cc_type,
           java.lang.String cc_exp_year,
           java.lang.String cc_exp_month) {
           this.po_number = po_number;
           this.method = method;
           this.cc_cid = cc_cid;
           this.cc_owner = cc_owner;
           this.cc_number = cc_number;
           this.cc_type = cc_type;
           this.cc_exp_year = cc_exp_year;
           this.cc_exp_month = cc_exp_month;
    }


    /**
     * Gets the po_number value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return po_number
     */
    public java.lang.String getPo_number() {
        return po_number;
    }


    /**
     * Sets the po_number value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param po_number
     */
    public void setPo_number(java.lang.String po_number) {
        this.po_number = po_number;
    }


    /**
     * Gets the method value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return method
     */
    public java.lang.String getMethod() {
        return method;
    }


    /**
     * Sets the method value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param method
     */
    public void setMethod(java.lang.String method) {
        this.method = method;
    }


    /**
     * Gets the cc_cid value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return cc_cid
     */
    public java.lang.String getCc_cid() {
        return cc_cid;
    }


    /**
     * Sets the cc_cid value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param cc_cid
     */
    public void setCc_cid(java.lang.String cc_cid) {
        this.cc_cid = cc_cid;
    }


    /**
     * Gets the cc_owner value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return cc_owner
     */
    public java.lang.String getCc_owner() {
        return cc_owner;
    }


    /**
     * Sets the cc_owner value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param cc_owner
     */
    public void setCc_owner(java.lang.String cc_owner) {
        this.cc_owner = cc_owner;
    }


    /**
     * Gets the cc_number value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return cc_number
     */
    public java.lang.String getCc_number() {
        return cc_number;
    }


    /**
     * Sets the cc_number value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param cc_number
     */
    public void setCc_number(java.lang.String cc_number) {
        this.cc_number = cc_number;
    }


    /**
     * Gets the cc_type value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return cc_type
     */
    public java.lang.String getCc_type() {
        return cc_type;
    }


    /**
     * Sets the cc_type value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param cc_type
     */
    public void setCc_type(java.lang.String cc_type) {
        this.cc_type = cc_type;
    }


    /**
     * Gets the cc_exp_year value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return cc_exp_year
     */
    public java.lang.String getCc_exp_year() {
        return cc_exp_year;
    }


    /**
     * Sets the cc_exp_year value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param cc_exp_year
     */
    public void setCc_exp_year(java.lang.String cc_exp_year) {
        this.cc_exp_year = cc_exp_year;
    }


    /**
     * Gets the cc_exp_month value for this ShoppingCartPaymentMethodEntity.
     * 
     * @return cc_exp_month
     */
    public java.lang.String getCc_exp_month() {
        return cc_exp_month;
    }


    /**
     * Sets the cc_exp_month value for this ShoppingCartPaymentMethodEntity.
     * 
     * @param cc_exp_month
     */
    public void setCc_exp_month(java.lang.String cc_exp_month) {
        this.cc_exp_month = cc_exp_month;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartPaymentMethodEntity)) return false;
        ShoppingCartPaymentMethodEntity other = (ShoppingCartPaymentMethodEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.po_number==null && other.getPo_number()==null) || 
             (this.po_number!=null &&
              this.po_number.equals(other.getPo_number()))) &&
            ((this.method==null && other.getMethod()==null) || 
             (this.method!=null &&
              this.method.equals(other.getMethod()))) &&
            ((this.cc_cid==null && other.getCc_cid()==null) || 
             (this.cc_cid!=null &&
              this.cc_cid.equals(other.getCc_cid()))) &&
            ((this.cc_owner==null && other.getCc_owner()==null) || 
             (this.cc_owner!=null &&
              this.cc_owner.equals(other.getCc_owner()))) &&
            ((this.cc_number==null && other.getCc_number()==null) || 
             (this.cc_number!=null &&
              this.cc_number.equals(other.getCc_number()))) &&
            ((this.cc_type==null && other.getCc_type()==null) || 
             (this.cc_type!=null &&
              this.cc_type.equals(other.getCc_type()))) &&
            ((this.cc_exp_year==null && other.getCc_exp_year()==null) || 
             (this.cc_exp_year!=null &&
              this.cc_exp_year.equals(other.getCc_exp_year()))) &&
            ((this.cc_exp_month==null && other.getCc_exp_month()==null) || 
             (this.cc_exp_month!=null &&
              this.cc_exp_month.equals(other.getCc_exp_month())));
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
        if (getPo_number() != null) {
            _hashCode += getPo_number().hashCode();
        }
        if (getMethod() != null) {
            _hashCode += getMethod().hashCode();
        }
        if (getCc_cid() != null) {
            _hashCode += getCc_cid().hashCode();
        }
        if (getCc_owner() != null) {
            _hashCode += getCc_owner().hashCode();
        }
        if (getCc_number() != null) {
            _hashCode += getCc_number().hashCode();
        }
        if (getCc_type() != null) {
            _hashCode += getCc_type().hashCode();
        }
        if (getCc_exp_year() != null) {
            _hashCode += getCc_exp_year().hashCode();
        }
        if (getCc_exp_month() != null) {
            _hashCode += getCc_exp_month().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartPaymentMethodEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartPaymentMethodEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("po_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "po_number"));
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
        elemField.setFieldName("cc_cid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_cid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_owner");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_owner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_exp_year");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_exp_year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_exp_month");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_exp_month"));
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
