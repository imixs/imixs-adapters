/**
 * CatalogProductDownloadableLinkAddEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductDownloadableLinkAddEntity  implements java.io.Serializable {
    private java.lang.String title;

    private java.lang.String price;

    private java.lang.Integer is_unlimited;

    private java.lang.Integer number_of_downloads;

    private java.lang.Integer is_shareable;

    private CatalogProductDownloadableLinkAddSampleEntity sample;

    private java.lang.String type;

    private CatalogProductDownloadableLinkFileEntity file;

    private java.lang.String link_url;

    private java.lang.String sample_url;

    private java.lang.Integer sort_order;

    public CatalogProductDownloadableLinkAddEntity() {
    }

    public CatalogProductDownloadableLinkAddEntity(
           java.lang.String title,
           java.lang.String price,
           java.lang.Integer is_unlimited,
           java.lang.Integer number_of_downloads,
           java.lang.Integer is_shareable,
           CatalogProductDownloadableLinkAddSampleEntity sample,
           java.lang.String type,
           CatalogProductDownloadableLinkFileEntity file,
           java.lang.String link_url,
           java.lang.String sample_url,
           java.lang.Integer sort_order) {
           this.title = title;
           this.price = price;
           this.is_unlimited = is_unlimited;
           this.number_of_downloads = number_of_downloads;
           this.is_shareable = is_shareable;
           this.sample = sample;
           this.type = type;
           this.file = file;
           this.link_url = link_url;
           this.sample_url = sample_url;
           this.sort_order = sort_order;
    }


    /**
     * Gets the title value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the price value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return price
     */
    public java.lang.String getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.String price) {
        this.price = price;
    }


    /**
     * Gets the is_unlimited value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return is_unlimited
     */
    public java.lang.Integer getIs_unlimited() {
        return is_unlimited;
    }


    /**
     * Sets the is_unlimited value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param is_unlimited
     */
    public void setIs_unlimited(java.lang.Integer is_unlimited) {
        this.is_unlimited = is_unlimited;
    }


    /**
     * Gets the number_of_downloads value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return number_of_downloads
     */
    public java.lang.Integer getNumber_of_downloads() {
        return number_of_downloads;
    }


    /**
     * Sets the number_of_downloads value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param number_of_downloads
     */
    public void setNumber_of_downloads(java.lang.Integer number_of_downloads) {
        this.number_of_downloads = number_of_downloads;
    }


    /**
     * Gets the is_shareable value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return is_shareable
     */
    public java.lang.Integer getIs_shareable() {
        return is_shareable;
    }


    /**
     * Sets the is_shareable value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param is_shareable
     */
    public void setIs_shareable(java.lang.Integer is_shareable) {
        this.is_shareable = is_shareable;
    }


    /**
     * Gets the sample value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return sample
     */
    public CatalogProductDownloadableLinkAddSampleEntity getSample() {
        return sample;
    }


    /**
     * Sets the sample value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param sample
     */
    public void setSample(CatalogProductDownloadableLinkAddSampleEntity sample) {
        this.sample = sample;
    }


    /**
     * Gets the type value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the file value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return file
     */
    public CatalogProductDownloadableLinkFileEntity getFile() {
        return file;
    }


    /**
     * Sets the file value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param file
     */
    public void setFile(CatalogProductDownloadableLinkFileEntity file) {
        this.file = file;
    }


    /**
     * Gets the link_url value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return link_url
     */
    public java.lang.String getLink_url() {
        return link_url;
    }


    /**
     * Sets the link_url value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param link_url
     */
    public void setLink_url(java.lang.String link_url) {
        this.link_url = link_url;
    }


    /**
     * Gets the sample_url value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return sample_url
     */
    public java.lang.String getSample_url() {
        return sample_url;
    }


    /**
     * Sets the sample_url value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param sample_url
     */
    public void setSample_url(java.lang.String sample_url) {
        this.sample_url = sample_url;
    }


    /**
     * Gets the sort_order value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @return sort_order
     */
    public java.lang.Integer getSort_order() {
        return sort_order;
    }


    /**
     * Sets the sort_order value for this CatalogProductDownloadableLinkAddEntity.
     * 
     * @param sort_order
     */
    public void setSort_order(java.lang.Integer sort_order) {
        this.sort_order = sort_order;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductDownloadableLinkAddEntity)) return false;
        CatalogProductDownloadableLinkAddEntity other = (CatalogProductDownloadableLinkAddEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.is_unlimited==null && other.getIs_unlimited()==null) || 
             (this.is_unlimited!=null &&
              this.is_unlimited.equals(other.getIs_unlimited()))) &&
            ((this.number_of_downloads==null && other.getNumber_of_downloads()==null) || 
             (this.number_of_downloads!=null &&
              this.number_of_downloads.equals(other.getNumber_of_downloads()))) &&
            ((this.is_shareable==null && other.getIs_shareable()==null) || 
             (this.is_shareable!=null &&
              this.is_shareable.equals(other.getIs_shareable()))) &&
            ((this.sample==null && other.getSample()==null) || 
             (this.sample!=null &&
              this.sample.equals(other.getSample()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.file==null && other.getFile()==null) || 
             (this.file!=null &&
              this.file.equals(other.getFile()))) &&
            ((this.link_url==null && other.getLink_url()==null) || 
             (this.link_url!=null &&
              this.link_url.equals(other.getLink_url()))) &&
            ((this.sample_url==null && other.getSample_url()==null) || 
             (this.sample_url!=null &&
              this.sample_url.equals(other.getSample_url()))) &&
            ((this.sort_order==null && other.getSort_order()==null) || 
             (this.sort_order!=null &&
              this.sort_order.equals(other.getSort_order())));
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
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getIs_unlimited() != null) {
            _hashCode += getIs_unlimited().hashCode();
        }
        if (getNumber_of_downloads() != null) {
            _hashCode += getNumber_of_downloads().hashCode();
        }
        if (getIs_shareable() != null) {
            _hashCode += getIs_shareable().hashCode();
        }
        if (getSample() != null) {
            _hashCode += getSample().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getFile() != null) {
            _hashCode += getFile().hashCode();
        }
        if (getLink_url() != null) {
            _hashCode += getLink_url().hashCode();
        }
        if (getSample_url() != null) {
            _hashCode += getSample_url().hashCode();
        }
        if (getSort_order() != null) {
            _hashCode += getSort_order().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogProductDownloadableLinkAddEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkAddEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_unlimited");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_unlimited"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_of_downloads");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number_of_downloads"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_shareable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_shareable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sample");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sample"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkAddSampleEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file");
        elemField.setXmlName(new javax.xml.namespace.QName("", "file"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkFileEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link_url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "link_url"));
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
        elemField.setFieldName("sort_order");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sort_order"));
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
