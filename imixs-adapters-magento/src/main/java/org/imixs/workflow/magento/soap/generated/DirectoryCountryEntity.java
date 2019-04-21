/**
 * DirectoryCountryEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class DirectoryCountryEntity  implements java.io.Serializable {
    private java.lang.String country_id;

    private java.lang.String iso2_code;

    private java.lang.String iso3_code;

    private java.lang.String name;

    public DirectoryCountryEntity() {
    }

    public DirectoryCountryEntity(
           java.lang.String country_id,
           java.lang.String iso2_code,
           java.lang.String iso3_code,
           java.lang.String name) {
           this.country_id = country_id;
           this.iso2_code = iso2_code;
           this.iso3_code = iso3_code;
           this.name = name;
    }


    /**
     * Gets the country_id value for this DirectoryCountryEntity.
     * 
     * @return country_id
     */
    public java.lang.String getCountry_id() {
        return country_id;
    }


    /**
     * Sets the country_id value for this DirectoryCountryEntity.
     * 
     * @param country_id
     */
    public void setCountry_id(java.lang.String country_id) {
        this.country_id = country_id;
    }


    /**
     * Gets the iso2_code value for this DirectoryCountryEntity.
     * 
     * @return iso2_code
     */
    public java.lang.String getIso2_code() {
        return iso2_code;
    }


    /**
     * Sets the iso2_code value for this DirectoryCountryEntity.
     * 
     * @param iso2_code
     */
    public void setIso2_code(java.lang.String iso2_code) {
        this.iso2_code = iso2_code;
    }


    /**
     * Gets the iso3_code value for this DirectoryCountryEntity.
     * 
     * @return iso3_code
     */
    public java.lang.String getIso3_code() {
        return iso3_code;
    }


    /**
     * Sets the iso3_code value for this DirectoryCountryEntity.
     * 
     * @param iso3_code
     */
    public void setIso3_code(java.lang.String iso3_code) {
        this.iso3_code = iso3_code;
    }


    /**
     * Gets the name value for this DirectoryCountryEntity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DirectoryCountryEntity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DirectoryCountryEntity)) return false;
        DirectoryCountryEntity other = (DirectoryCountryEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.country_id==null && other.getCountry_id()==null) || 
             (this.country_id!=null &&
              this.country_id.equals(other.getCountry_id()))) &&
            ((this.iso2_code==null && other.getIso2_code()==null) || 
             (this.iso2_code!=null &&
              this.iso2_code.equals(other.getIso2_code()))) &&
            ((this.iso3_code==null && other.getIso3_code()==null) || 
             (this.iso3_code!=null &&
              this.iso3_code.equals(other.getIso3_code()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName())));
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
        if (getCountry_id() != null) {
            _hashCode += getCountry_id().hashCode();
        }
        if (getIso2_code() != null) {
            _hashCode += getIso2_code().hashCode();
        }
        if (getIso3_code() != null) {
            _hashCode += getIso3_code().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DirectoryCountryEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "directoryCountryEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iso2_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iso2_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iso3_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iso3_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
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
