/**
 * SalesOrderShipmentTrackEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderShipmentTrackEntity  implements java.io.Serializable {
    private java.lang.String increment_id;

    private java.lang.String parent_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String is_active;

    private java.lang.String carrier_code;

    private java.lang.String title;

    private java.lang.String number;

    private java.lang.String order_id;

    private java.lang.String track_id;

    public SalesOrderShipmentTrackEntity() {
    }

    public SalesOrderShipmentTrackEntity(
           java.lang.String increment_id,
           java.lang.String parent_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String is_active,
           java.lang.String carrier_code,
           java.lang.String title,
           java.lang.String number,
           java.lang.String order_id,
           java.lang.String track_id) {
           this.increment_id = increment_id;
           this.parent_id = parent_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.is_active = is_active;
           this.carrier_code = carrier_code;
           this.title = title;
           this.number = number;
           this.order_id = order_id;
           this.track_id = track_id;
    }


    /**
     * Gets the increment_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @return increment_id
     */
    public java.lang.String getIncrement_id() {
        return increment_id;
    }


    /**
     * Sets the increment_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @param increment_id
     */
    public void setIncrement_id(java.lang.String increment_id) {
        this.increment_id = increment_id;
    }


    /**
     * Gets the parent_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @return parent_id
     */
    public java.lang.String getParent_id() {
        return parent_id;
    }


    /**
     * Sets the parent_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @param parent_id
     */
    public void setParent_id(java.lang.String parent_id) {
        this.parent_id = parent_id;
    }


    /**
     * Gets the created_at value for this SalesOrderShipmentTrackEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderShipmentTrackEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this SalesOrderShipmentTrackEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderShipmentTrackEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the is_active value for this SalesOrderShipmentTrackEntity.
     * 
     * @return is_active
     */
    public java.lang.String getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this SalesOrderShipmentTrackEntity.
     * 
     * @param is_active
     */
    public void setIs_active(java.lang.String is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the carrier_code value for this SalesOrderShipmentTrackEntity.
     * 
     * @return carrier_code
     */
    public java.lang.String getCarrier_code() {
        return carrier_code;
    }


    /**
     * Sets the carrier_code value for this SalesOrderShipmentTrackEntity.
     * 
     * @param carrier_code
     */
    public void setCarrier_code(java.lang.String carrier_code) {
        this.carrier_code = carrier_code;
    }


    /**
     * Gets the title value for this SalesOrderShipmentTrackEntity.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this SalesOrderShipmentTrackEntity.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the number value for this SalesOrderShipmentTrackEntity.
     * 
     * @return number
     */
    public java.lang.String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this SalesOrderShipmentTrackEntity.
     * 
     * @param number
     */
    public void setNumber(java.lang.String number) {
        this.number = number;
    }


    /**
     * Gets the order_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @return order_id
     */
    public java.lang.String getOrder_id() {
        return order_id;
    }


    /**
     * Sets the order_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @param order_id
     */
    public void setOrder_id(java.lang.String order_id) {
        this.order_id = order_id;
    }


    /**
     * Gets the track_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @return track_id
     */
    public java.lang.String getTrack_id() {
        return track_id;
    }


    /**
     * Sets the track_id value for this SalesOrderShipmentTrackEntity.
     * 
     * @param track_id
     */
    public void setTrack_id(java.lang.String track_id) {
        this.track_id = track_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderShipmentTrackEntity)) return false;
        SalesOrderShipmentTrackEntity other = (SalesOrderShipmentTrackEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.increment_id==null && other.getIncrement_id()==null) || 
             (this.increment_id!=null &&
              this.increment_id.equals(other.getIncrement_id()))) &&
            ((this.parent_id==null && other.getParent_id()==null) || 
             (this.parent_id!=null &&
              this.parent_id.equals(other.getParent_id()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
            ((this.is_active==null && other.getIs_active()==null) || 
             (this.is_active!=null &&
              this.is_active.equals(other.getIs_active()))) &&
            ((this.carrier_code==null && other.getCarrier_code()==null) || 
             (this.carrier_code!=null &&
              this.carrier_code.equals(other.getCarrier_code()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.order_id==null && other.getOrder_id()==null) || 
             (this.order_id!=null &&
              this.order_id.equals(other.getOrder_id()))) &&
            ((this.track_id==null && other.getTrack_id()==null) || 
             (this.track_id!=null &&
              this.track_id.equals(other.getTrack_id())));
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
        if (getIncrement_id() != null) {
            _hashCode += getIncrement_id().hashCode();
        }
        if (getParent_id() != null) {
            _hashCode += getParent_id().hashCode();
        }
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getUpdated_at() != null) {
            _hashCode += getUpdated_at().hashCode();
        }
        if (getIs_active() != null) {
            _hashCode += getIs_active().hashCode();
        }
        if (getCarrier_code() != null) {
            _hashCode += getCarrier_code().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getOrder_id() != null) {
            _hashCode += getOrder_id().hashCode();
        }
        if (getTrack_id() != null) {
            _hashCode += getTrack_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderShipmentTrackEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderShipmentTrackEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("increment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "increment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parent_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("created_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "created_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updated_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updated_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_active");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrier_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "carrier_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("track_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "track_id"));
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
