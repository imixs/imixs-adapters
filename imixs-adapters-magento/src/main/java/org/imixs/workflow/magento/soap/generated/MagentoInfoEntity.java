/**
 * MagentoInfoEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class MagentoInfoEntity  implements java.io.Serializable {
    private java.lang.String magento_version;

    private java.lang.String magento_edition;

    public MagentoInfoEntity() {
    }

    public MagentoInfoEntity(
           java.lang.String magento_version,
           java.lang.String magento_edition) {
           this.magento_version = magento_version;
           this.magento_edition = magento_edition;
    }


    /**
     * Gets the magento_version value for this MagentoInfoEntity.
     * 
     * @return magento_version
     */
    public java.lang.String getMagento_version() {
        return magento_version;
    }


    /**
     * Sets the magento_version value for this MagentoInfoEntity.
     * 
     * @param magento_version
     */
    public void setMagento_version(java.lang.String magento_version) {
        this.magento_version = magento_version;
    }


    /**
     * Gets the magento_edition value for this MagentoInfoEntity.
     * 
     * @return magento_edition
     */
    public java.lang.String getMagento_edition() {
        return magento_edition;
    }


    /**
     * Sets the magento_edition value for this MagentoInfoEntity.
     * 
     * @param magento_edition
     */
    public void setMagento_edition(java.lang.String magento_edition) {
        this.magento_edition = magento_edition;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MagentoInfoEntity)) return false;
        MagentoInfoEntity other = (MagentoInfoEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.magento_version==null && other.getMagento_version()==null) || 
             (this.magento_version!=null &&
              this.magento_version.equals(other.getMagento_version()))) &&
            ((this.magento_edition==null && other.getMagento_edition()==null) || 
             (this.magento_edition!=null &&
              this.magento_edition.equals(other.getMagento_edition())));
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
        if (getMagento_version() != null) {
            _hashCode += getMagento_version().hashCode();
        }
        if (getMagento_edition() != null) {
            _hashCode += getMagento_edition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MagentoInfoEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "magentoInfoEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("magento_version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "magento_version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("magento_edition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "magento_edition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
