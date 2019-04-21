/**
 * CatalogProductDownloadableLinkEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductDownloadableLinkEntity  implements java.io.Serializable {
    private java.lang.String link_id;

    private java.lang.String title;

    private java.lang.String price;

    private java.lang.Integer number_of_downloads;

    private java.lang.Integer is_unlimited;

    private int is_shareable;

    private java.lang.String link_url;

    private java.lang.String link_type;

    private java.lang.String sample_file;

    private java.lang.String sample_url;

    private java.lang.String sample_type;

    private int sort_order;

    private CatalogProductDownloadableLinkFileInfoEntity[] file_save;

    private CatalogProductDownloadableLinkFileInfoEntity[] sample_file_save;

    public CatalogProductDownloadableLinkEntity() {
    }

    public CatalogProductDownloadableLinkEntity(
           java.lang.String link_id,
           java.lang.String title,
           java.lang.String price,
           java.lang.Integer number_of_downloads,
           java.lang.Integer is_unlimited,
           int is_shareable,
           java.lang.String link_url,
           java.lang.String link_type,
           java.lang.String sample_file,
           java.lang.String sample_url,
           java.lang.String sample_type,
           int sort_order,
           CatalogProductDownloadableLinkFileInfoEntity[] file_save,
           CatalogProductDownloadableLinkFileInfoEntity[] sample_file_save) {
           this.link_id = link_id;
           this.title = title;
           this.price = price;
           this.number_of_downloads = number_of_downloads;
           this.is_unlimited = is_unlimited;
           this.is_shareable = is_shareable;
           this.link_url = link_url;
           this.link_type = link_type;
           this.sample_file = sample_file;
           this.sample_url = sample_url;
           this.sample_type = sample_type;
           this.sort_order = sort_order;
           this.file_save = file_save;
           this.sample_file_save = sample_file_save;
    }


    /**
     * Gets the link_id value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return link_id
     */
    public java.lang.String getLink_id() {
        return link_id;
    }


    /**
     * Sets the link_id value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param link_id
     */
    public void setLink_id(java.lang.String link_id) {
        this.link_id = link_id;
    }


    /**
     * Gets the title value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the price value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return price
     */
    public java.lang.String getPrice() {
        return price;
    }


    /**
     * Sets the price value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param price
     */
    public void setPrice(java.lang.String price) {
        this.price = price;
    }


    /**
     * Gets the number_of_downloads value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return number_of_downloads
     */
    public java.lang.Integer getNumber_of_downloads() {
        return number_of_downloads;
    }


    /**
     * Sets the number_of_downloads value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param number_of_downloads
     */
    public void setNumber_of_downloads(java.lang.Integer number_of_downloads) {
        this.number_of_downloads = number_of_downloads;
    }


    /**
     * Gets the is_unlimited value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return is_unlimited
     */
    public java.lang.Integer getIs_unlimited() {
        return is_unlimited;
    }


    /**
     * Sets the is_unlimited value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param is_unlimited
     */
    public void setIs_unlimited(java.lang.Integer is_unlimited) {
        this.is_unlimited = is_unlimited;
    }


    /**
     * Gets the is_shareable value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return is_shareable
     */
    public int getIs_shareable() {
        return is_shareable;
    }


    /**
     * Sets the is_shareable value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param is_shareable
     */
    public void setIs_shareable(int is_shareable) {
        this.is_shareable = is_shareable;
    }


    /**
     * Gets the link_url value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return link_url
     */
    public java.lang.String getLink_url() {
        return link_url;
    }


    /**
     * Sets the link_url value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param link_url
     */
    public void setLink_url(java.lang.String link_url) {
        this.link_url = link_url;
    }


    /**
     * Gets the link_type value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return link_type
     */
    public java.lang.String getLink_type() {
        return link_type;
    }


    /**
     * Sets the link_type value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param link_type
     */
    public void setLink_type(java.lang.String link_type) {
        this.link_type = link_type;
    }


    /**
     * Gets the sample_file value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return sample_file
     */
    public java.lang.String getSample_file() {
        return sample_file;
    }


    /**
     * Sets the sample_file value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param sample_file
     */
    public void setSample_file(java.lang.String sample_file) {
        this.sample_file = sample_file;
    }


    /**
     * Gets the sample_url value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return sample_url
     */
    public java.lang.String getSample_url() {
        return sample_url;
    }


    /**
     * Sets the sample_url value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param sample_url
     */
    public void setSample_url(java.lang.String sample_url) {
        this.sample_url = sample_url;
    }


    /**
     * Gets the sample_type value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return sample_type
     */
    public java.lang.String getSample_type() {
        return sample_type;
    }


    /**
     * Sets the sample_type value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param sample_type
     */
    public void setSample_type(java.lang.String sample_type) {
        this.sample_type = sample_type;
    }


    /**
     * Gets the sort_order value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return sort_order
     */
    public int getSort_order() {
        return sort_order;
    }


    /**
     * Sets the sort_order value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param sort_order
     */
    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }


    /**
     * Gets the file_save value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return file_save
     */
    public CatalogProductDownloadableLinkFileInfoEntity[] getFile_save() {
        return file_save;
    }


    /**
     * Sets the file_save value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param file_save
     */
    public void setFile_save(CatalogProductDownloadableLinkFileInfoEntity[] file_save) {
        this.file_save = file_save;
    }


    /**
     * Gets the sample_file_save value for this CatalogProductDownloadableLinkEntity.
     * 
     * @return sample_file_save
     */
    public CatalogProductDownloadableLinkFileInfoEntity[] getSample_file_save() {
        return sample_file_save;
    }


    /**
     * Sets the sample_file_save value for this CatalogProductDownloadableLinkEntity.
     * 
     * @param sample_file_save
     */
    public void setSample_file_save(CatalogProductDownloadableLinkFileInfoEntity[] sample_file_save) {
        this.sample_file_save = sample_file_save;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductDownloadableLinkEntity)) return false;
        CatalogProductDownloadableLinkEntity other = (CatalogProductDownloadableLinkEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.link_id==null && other.getLink_id()==null) || 
             (this.link_id!=null &&
              this.link_id.equals(other.getLink_id()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.number_of_downloads==null && other.getNumber_of_downloads()==null) || 
             (this.number_of_downloads!=null &&
              this.number_of_downloads.equals(other.getNumber_of_downloads()))) &&
            ((this.is_unlimited==null && other.getIs_unlimited()==null) || 
             (this.is_unlimited!=null &&
              this.is_unlimited.equals(other.getIs_unlimited()))) &&
            this.is_shareable == other.getIs_shareable() &&
            ((this.link_url==null && other.getLink_url()==null) || 
             (this.link_url!=null &&
              this.link_url.equals(other.getLink_url()))) &&
            ((this.link_type==null && other.getLink_type()==null) || 
             (this.link_type!=null &&
              this.link_type.equals(other.getLink_type()))) &&
            ((this.sample_file==null && other.getSample_file()==null) || 
             (this.sample_file!=null &&
              this.sample_file.equals(other.getSample_file()))) &&
            ((this.sample_url==null && other.getSample_url()==null) || 
             (this.sample_url!=null &&
              this.sample_url.equals(other.getSample_url()))) &&
            ((this.sample_type==null && other.getSample_type()==null) || 
             (this.sample_type!=null &&
              this.sample_type.equals(other.getSample_type()))) &&
            this.sort_order == other.getSort_order() &&
            ((this.file_save==null && other.getFile_save()==null) || 
             (this.file_save!=null &&
              java.util.Arrays.equals(this.file_save, other.getFile_save()))) &&
            ((this.sample_file_save==null && other.getSample_file_save()==null) || 
             (this.sample_file_save!=null &&
              java.util.Arrays.equals(this.sample_file_save, other.getSample_file_save())));
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
        if (getLink_id() != null) {
            _hashCode += getLink_id().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getNumber_of_downloads() != null) {
            _hashCode += getNumber_of_downloads().hashCode();
        }
        if (getIs_unlimited() != null) {
            _hashCode += getIs_unlimited().hashCode();
        }
        _hashCode += getIs_shareable();
        if (getLink_url() != null) {
            _hashCode += getLink_url().hashCode();
        }
        if (getLink_type() != null) {
            _hashCode += getLink_type().hashCode();
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
        _hashCode += getSort_order();
        if (getFile_save() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFile_save());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFile_save(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSample_file_save() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSample_file_save());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSample_file_save(), i);
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
        new org.apache.axis.description.TypeDesc(CatalogProductDownloadableLinkEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "link_id"));
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
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("is_unlimited");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_unlimited"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_shareable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_shareable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link_url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "link_url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "link_type"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file_save");
        elemField.setXmlName(new javax.xml.namespace.QName("", "file_save"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkFileInfoEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sample_file_save");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sample_file_save"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductDownloadableLinkFileInfoEntity"));
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
