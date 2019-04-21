/**
 * CatalogProductDownloadableLinkInfoEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductDownloadableLinkInfoEntity  implements java.io.Serializable {
    private CatalogProductDownloadableLinkEntity[] links;

    private CatalogProductDownloadableLinkSampleEntity[] samples;

    public CatalogProductDownloadableLinkInfoEntity() {
    }

    public CatalogProductDownloadableLinkInfoEntity(
           CatalogProductDownloadableLinkEntity[] links,
           CatalogProductDownloadableLinkSampleEntity[] samples) {
           this.links = links;
           this.samples = samples;
    }


    /**
     * Gets the links value for this CatalogProductDownloadableLinkInfoEntity.
     * 
     * @return links
     */
    public CatalogProductDownloadableLinkEntity[] getLinks() {
        return links;
    }


    /**
     * Sets the links value for this CatalogProductDownloadableLinkInfoEntity.
     * 
     * @param links
     */
    public void setLinks(CatalogProductDownloadableLinkEntity[] links) {
        this.links = links;
    }


    /**
     * Gets the samples value for this CatalogProductDownloadableLinkInfoEntity.
     * 
     * @return samples
     */
    public CatalogProductDownloadableLinkSampleEntity[] getSamples() {
        return samples;
    }


    /**
     * Sets the samples value for this CatalogProductDownloadableLinkInfoEntity.
     * 
     * @param samples
     */
    public void setSamples(CatalogProductDownloadableLinkSampleEntity[] samples) {
        this.samples = samples;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductDownloadableLinkInfoEntity)) return false;
        CatalogProductDownloadableLinkInfoEntity other = (CatalogProductDownloadableLinkInfoEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.links==null && other.getLinks()==null) || 
             (this.links!=null &&
              java.util.Arrays.equals(this.links, other.getLinks()))) &&
            ((this.samples==null && other.getSamples()==null) || 
             (this.samples!=null &&
              java.util.Arrays.equals(this.samples, other.getSamples())));
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
        if (getLinks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLinks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLinks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSamples() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSamples());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSamples(), i);
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
        new org.apache.axis.description.TypeDesc(CatalogProductDownloadableLinkInfoEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkInfoEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("", "links"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkEntity"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("samples");
        elemField.setXmlName(new javax.xml.namespace.QName("", "samples"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkSampleEntity"));
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
