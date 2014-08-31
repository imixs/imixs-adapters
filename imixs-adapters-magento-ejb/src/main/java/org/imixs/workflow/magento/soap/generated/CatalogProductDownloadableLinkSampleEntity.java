/**
 * CatalogProductDownloadableLinkSampleEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductDownloadableLinkSampleEntity  implements java.io.Serializable {
    private java.lang.String sample_id;

    private java.lang.String product_id;

    private java.lang.String sample_file;

    private java.lang.String sample_url;

    private java.lang.String sample_type;

    private java.lang.String sort_order;

    private java.lang.String default_title;

    private java.lang.String store_title;

    private java.lang.String title;

    public CatalogProductDownloadableLinkSampleEntity() {
    }

    public CatalogProductDownloadableLinkSampleEntity(
           java.lang.String sample_id,
           java.lang.String product_id,
           java.lang.String sample_file,
           java.lang.String sample_url,
           java.lang.String sample_type,
           java.lang.String sort_order,
           java.lang.String default_title,
           java.lang.String store_title,
           java.lang.String title) {
           this.sample_id = sample_id;
           this.product_id = product_id;
           this.sample_file = sample_file;
           this.sample_url = sample_url;
           this.sample_type = sample_type;
           this.sort_order = sort_order;
           this.default_title = default_title;
           this.store_title = store_title;
           this.title = title;
    }


    /**
     * Gets the sample_id value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return sample_id
     */
    public java.lang.String getSample_id() {
        return sample_id;
    }


    /**
     * Sets the sample_id value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param sample_id
     */
    public void setSample_id(java.lang.String sample_id) {
        this.sample_id = sample_id;
    }


    /**
     * Gets the product_id value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return product_id
     */
    public java.lang.String getProduct_id() {
        return product_id;
    }


    /**
     * Sets the product_id value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param product_id
     */
    public void setProduct_id(java.lang.String product_id) {
        this.product_id = product_id;
    }


    /**
     * Gets the sample_file value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return sample_file
     */
    public java.lang.String getSample_file() {
        return sample_file;
    }


    /**
     * Sets the sample_file value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param sample_file
     */
    public void setSample_file(java.lang.String sample_file) {
        this.sample_file = sample_file;
    }


    /**
     * Gets the sample_url value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return sample_url
     */
    public java.lang.String getSample_url() {
        return sample_url;
    }


    /**
     * Sets the sample_url value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param sample_url
     */
    public void setSample_url(java.lang.String sample_url) {
        this.sample_url = sample_url;
    }


    /**
     * Gets the sample_type value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return sample_type
     */
    public java.lang.String getSample_type() {
        return sample_type;
    }


    /**
     * Sets the sample_type value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param sample_type
     */
    public void setSample_type(java.lang.String sample_type) {
        this.sample_type = sample_type;
    }


    /**
     * Gets the sort_order value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return sort_order
     */
    public java.lang.String getSort_order() {
        return sort_order;
    }


    /**
     * Sets the sort_order value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param sort_order
     */
    public void setSort_order(java.lang.String sort_order) {
        this.sort_order = sort_order;
    }


    /**
     * Gets the default_title value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return default_title
     */
    public java.lang.String getDefault_title() {
        return default_title;
    }


    /**
     * Sets the default_title value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param default_title
     */
    public void setDefault_title(java.lang.String default_title) {
        this.default_title = default_title;
    }


    /**
     * Gets the store_title value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return store_title
     */
    public java.lang.String getStore_title() {
        return store_title;
    }


    /**
     * Sets the store_title value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param store_title
     */
    public void setStore_title(java.lang.String store_title) {
        this.store_title = store_title;
    }


    /**
     * Gets the title value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CatalogProductDownloadableLinkSampleEntity.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductDownloadableLinkSampleEntity)) return false;
        CatalogProductDownloadableLinkSampleEntity other = (CatalogProductDownloadableLinkSampleEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sample_id==null && other.getSample_id()==null) || 
             (this.sample_id!=null &&
              this.sample_id.equals(other.getSample_id()))) &&
            ((this.product_id==null && other.getProduct_id()==null) || 
             (this.product_id!=null &&
              this.product_id.equals(other.getProduct_id()))) &&
            ((this.sample_file==null && other.getSample_file()==null) || 
             (this.sample_file!=null &&
              this.sample_file.equals(other.getSample_file()))) &&
            ((this.sample_url==null && other.getSample_url()==null) || 
             (this.sample_url!=null &&
              this.sample_url.equals(other.getSample_url()))) &&
            ((this.sample_type==null && other.getSample_type()==null) || 
             (this.sample_type!=null &&
              this.sample_type.equals(other.getSample_type()))) &&
            ((this.sort_order==null && other.getSort_order()==null) || 
             (this.sort_order!=null &&
              this.sort_order.equals(other.getSort_order()))) &&
            ((this.default_title==null && other.getDefault_title()==null) || 
             (this.default_title!=null &&
              this.default_title.equals(other.getDefault_title()))) &&
            ((this.store_title==null && other.getStore_title()==null) || 
             (this.store_title!=null &&
              this.store_title.equals(other.getStore_title()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle())));
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
        if (getSample_id() != null) {
            _hashCode += getSample_id().hashCode();
        }
        if (getProduct_id() != null) {
            _hashCode += getProduct_id().hashCode();
        }
        if (getSample_file() != null) {
            _hashCode += getSample_file().hashCode();
        }
        if (getSample_url() != null) {
            _hashCode += getSample_url().hashCode();
        }
        if (getSample_type() != null) {
            _hashCode += getSample_type().hashCode();
        }
        if (getSort_order() != null) {
            _hashCode += getSort_order().hashCode();
        }
        if (getDefault_title() != null) {
            _hashCode += getDefault_title().hashCode();
        }
        if (getStore_title() != null) {
            _hashCode += getStore_title().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogProductDownloadableLinkSampleEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkSampleEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sample_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sample_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sample_file");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sample_file"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sample_url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sample_url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sample_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sample_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sort_order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sort_order"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("default_title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "default_title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
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
