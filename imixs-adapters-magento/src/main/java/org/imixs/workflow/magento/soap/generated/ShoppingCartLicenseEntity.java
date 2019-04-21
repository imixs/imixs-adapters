/**
 * ShoppingCartLicenseEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartLicenseEntity  implements java.io.Serializable {
    private java.lang.String agreement_id;

    private java.lang.String name;

    private java.lang.String content;

    private java.lang.Integer is_active;

    private java.lang.Integer is_html;

    public ShoppingCartLicenseEntity() {
    }

    public ShoppingCartLicenseEntity(
           java.lang.String agreement_id,
           java.lang.String name,
           java.lang.String content,
           java.lang.Integer is_active,
           java.lang.Integer is_html) {
           this.agreement_id = agreement_id;
           this.name = name;
           this.content = content;
           this.is_active = is_active;
           this.is_html = is_html;
    }


    /**
     * Gets the agreement_id value for this ShoppingCartLicenseEntity.
     * 
     * @return agreement_id
     */
    public java.lang.String getAgreement_id() {
        return agreement_id;
    }


    /**
     * Sets the agreement_id value for this ShoppingCartLicenseEntity.
     * 
     * @param agreement_id
     */
    public void setAgreement_id(java.lang.String agreement_id) {
        this.agreement_id = agreement_id;
    }


    /**
     * Gets the name value for this ShoppingCartLicenseEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ShoppingCartLicenseEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the content value for this ShoppingCartLicenseEntity.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this ShoppingCartLicenseEntity.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the is_active value for this ShoppingCartLicenseEntity.
     * 
     * @return is_active
     */
    public java.lang.Integer getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this ShoppingCartLicenseEntity.
     * 
     * @param is_active
     */
    public void setIs_active(java.lang.Integer is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the is_html value for this ShoppingCartLicenseEntity.
     * 
     * @return is_html
     */
    public java.lang.Integer getIs_html() {
        return is_html;
    }


    /**
     * Sets the is_html value for this ShoppingCartLicenseEntity.
     * 
     * @param is_html
     */
    public void setIs_html(java.lang.Integer is_html) {
        this.is_html = is_html;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartLicenseEntity)) return false;
        ShoppingCartLicenseEntity other = (ShoppingCartLicenseEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.agreement_id==null && other.getAgreement_id()==null) || 
             (this.agreement_id!=null &&
              this.agreement_id.equals(other.getAgreement_id()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.is_active==null && other.getIs_active()==null) || 
             (this.is_active!=null &&
              this.is_active.equals(other.getIs_active()))) &&
            ((this.is_html==null && other.getIs_html()==null) || 
             (this.is_html!=null &&
              this.is_html.equals(other.getIs_html())));
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
        if (getAgreement_id() != null) {
            _hashCode += getAgreement_id().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getIs_active() != null) {
            _hashCode += getIs_active().hashCode();
        }
        if (getIs_html() != null) {
            _hashCode += getIs_html().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartLicenseEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartLicenseEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agreement_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agreement_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_active");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_html");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_html"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
