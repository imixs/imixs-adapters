/**
 * SalesOrderShipmentEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderShipmentEntity  implements java.io.Serializable {
    private java.lang.String increment_id;

    private java.lang.String parent_id;

    private java.lang.String store_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String is_active;

    private java.lang.String shipping_address_id;

    private java.lang.String shipping_firstname;

    private java.lang.String shipping_lastname;

    private java.lang.String order_id;

    private java.lang.String order_increment_id;

    private java.lang.String order_created_at;

    private java.lang.String total_qty;

    private java.lang.String shipment_id;

    private SalesOrderShipmentItemEntity[] items;

    private SalesOrderShipmentTrackEntity[] tracks;

    private SalesOrderShipmentCommentEntity[] comments;

    public SalesOrderShipmentEntity() {
    }

    public SalesOrderShipmentEntity(
           java.lang.String increment_id,
           java.lang.String parent_id,
           java.lang.String store_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String is_active,
           java.lang.String shipping_address_id,
           java.lang.String shipping_firstname,
           java.lang.String shipping_lastname,
           java.lang.String order_id,
           java.lang.String order_increment_id,
           java.lang.String order_created_at,
           java.lang.String total_qty,
           java.lang.String shipment_id,
           SalesOrderShipmentItemEntity[] items,
           SalesOrderShipmentTrackEntity[] tracks,
           SalesOrderShipmentCommentEntity[] comments) {
           this.increment_id = increment_id;
           this.parent_id = parent_id;
           this.store_id = store_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.is_active = is_active;
           this.shipping_address_id = shipping_address_id;
           this.shipping_firstname = shipping_firstname;
           this.shipping_lastname = shipping_lastname;
           this.order_id = order_id;
           this.order_increment_id = order_increment_id;
           this.order_created_at = order_created_at;
           this.total_qty = total_qty;
           this.shipment_id = shipment_id;
           this.items = items;
           this.tracks = tracks;
           this.comments = comments;
    }


    /**
     * Gets the increment_id value for this SalesOrderShipmentEntity.
     * 
     * @return increment_id
     */
    public java.lang.String getIncrement_id() {
        return increment_id;
    }


    /**
     * Sets the increment_id value for this SalesOrderShipmentEntity.
     * 
     * @param increment_id
     */
    public void setIncrement_id(java.lang.String increment_id) {
        this.increment_id = increment_id;
    }


    /**
     * Gets the parent_id value for this SalesOrderShipmentEntity.
     * 
     * @return parent_id
     */
    public java.lang.String getParent_id() {
        return parent_id;
    }


    /**
     * Sets the parent_id value for this SalesOrderShipmentEntity.
     * 
     * @param parent_id
     */
    public void setParent_id(java.lang.String parent_id) {
        this.parent_id = parent_id;
    }


    /**
     * Gets the store_id value for this SalesOrderShipmentEntity.
     * 
     * @return store_id
     */
    public java.lang.String getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this SalesOrderShipmentEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.String store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the created_at value for this SalesOrderShipmentEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderShipmentEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this SalesOrderShipmentEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderShipmentEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the is_active value for this SalesOrderShipmentEntity.
     * 
     * @return is_active
     */
    public java.lang.String getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this SalesOrderShipmentEntity.
     * 
     * @param is_active
     */
    public void setIs_active(java.lang.String is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the shipping_address_id value for this SalesOrderShipmentEntity.
     * 
     * @return shipping_address_id
     */
    public java.lang.String getShipping_address_id() {
        return shipping_address_id;
    }


    /**
     * Sets the shipping_address_id value for this SalesOrderShipmentEntity.
     * 
     * @param shipping_address_id
     */
    public void setShipping_address_id(java.lang.String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }


    /**
     * Gets the shipping_firstname value for this SalesOrderShipmentEntity.
     * 
     * @return shipping_firstname
     */
    public java.lang.String getShipping_firstname() {
        return shipping_firstname;
    }


    /**
     * Sets the shipping_firstname value for this SalesOrderShipmentEntity.
     * 
     * @param shipping_firstname
     */
    public void setShipping_firstname(java.lang.String shipping_firstname) {
        this.shipping_firstname = shipping_firstname;
    }


    /**
     * Gets the shipping_lastname value for this SalesOrderShipmentEntity.
     * 
     * @return shipping_lastname
     */
    public java.lang.String getShipping_lastname() {
        return shipping_lastname;
    }


    /**
     * Sets the shipping_lastname value for this SalesOrderShipmentEntity.
     * 
     * @param shipping_lastname
     */
    public void setShipping_lastname(java.lang.String shipping_lastname) {
        this.shipping_lastname = shipping_lastname;
    }


    /**
     * Gets the order_id value for this SalesOrderShipmentEntity.
     * 
     * @return order_id
     */
    public java.lang.String getOrder_id() {
        return order_id;
    }


    /**
     * Sets the order_id value for this SalesOrderShipmentEntity.
     * 
     * @param order_id
     */
    public void setOrder_id(java.lang.String order_id) {
        this.order_id = order_id;
    }


    /**
     * Gets the order_increment_id value for this SalesOrderShipmentEntity.
     * 
     * @return order_increment_id
     */
    public java.lang.String getOrder_increment_id() {
        return order_increment_id;
    }


    /**
     * Sets the order_increment_id value for this SalesOrderShipmentEntity.
     * 
     * @param order_increment_id
     */
    public void setOrder_increment_id(java.lang.String order_increment_id) {
        this.order_increment_id = order_increment_id;
    }


    /**
     * Gets the order_created_at value for this SalesOrderShipmentEntity.
     * 
     * @return order_created_at
     */
    public java.lang.String getOrder_created_at() {
        return order_created_at;
    }


    /**
     * Sets the order_created_at value for this SalesOrderShipmentEntity.
     * 
     * @param order_created_at
     */
    public void setOrder_created_at(java.lang.String order_created_at) {
        this.order_created_at = order_created_at;
    }


    /**
     * Gets the total_qty value for this SalesOrderShipmentEntity.
     * 
     * @return total_qty
     */
    public java.lang.String getTotal_qty() {
        return total_qty;
    }


    /**
     * Sets the total_qty value for this SalesOrderShipmentEntity.
     * 
     * @param total_qty
     */
    public void setTotal_qty(java.lang.String total_qty) {
        this.total_qty = total_qty;
    }


    /**
     * Gets the shipment_id value for this SalesOrderShipmentEntity.
     * 
     * @return shipment_id
     */
    public java.lang.String getShipment_id() {
        return shipment_id;
    }


    /**
     * Sets the shipment_id value for this SalesOrderShipmentEntity.
     * 
     * @param shipment_id
     */
    public void setShipment_id(java.lang.String shipment_id) {
        this.shipment_id = shipment_id;
    }


    /**
     * Gets the items value for this SalesOrderShipmentEntity.
     * 
     * @return items
     */
    public SalesOrderShipmentItemEntity[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this SalesOrderShipmentEntity.
     * 
     * @param items
     */
    public void setItems(SalesOrderShipmentItemEntity[] items) {
        this.items = items;
    }


    /**
     * Gets the tracks value for this SalesOrderShipmentEntity.
     * 
     * @return tracks
     */
    public SalesOrderShipmentTrackEntity[] getTracks() {
        return tracks;
    }


    /**
     * Sets the tracks value for this SalesOrderShipmentEntity.
     * 
     * @param tracks
     */
    public void setTracks(SalesOrderShipmentTrackEntity[] tracks) {
        this.tracks = tracks;
    }


    /**
     * Gets the comments value for this SalesOrderShipmentEntity.
     * 
     * @return comments
     */
    public SalesOrderShipmentCommentEntity[] getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this SalesOrderShipmentEntity.
     * 
     * @param comments
     */
    public void setComments(SalesOrderShipmentCommentEntity[] comments) {
        this.comments = comments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderShipmentEntity)) return false;
        SalesOrderShipmentEntity other = (SalesOrderShipmentEntity) obj;
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
            ((this.store_id==null && other.getStore_id()==null) || 
             (this.store_id!=null &&
              this.store_id.equals(other.getStore_id()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.updated_at==null && other.getUpdated_at()==null) || 
             (this.updated_at!=null &&
              this.updated_at.equals(other.getUpdated_at()))) &&
            ((this.is_active==null && other.getIs_active()==null) || 
             (this.is_active!=null &&
              this.is_active.equals(other.getIs_active()))) &&
            ((this.shipping_address_id==null && other.getShipping_address_id()==null) || 
             (this.shipping_address_id!=null &&
              this.shipping_address_id.equals(other.getShipping_address_id()))) &&
            ((this.shipping_firstname==null && other.getShipping_firstname()==null) || 
             (this.shipping_firstname!=null &&
              this.shipping_firstname.equals(other.getShipping_firstname()))) &&
            ((this.shipping_lastname==null && other.getShipping_lastname()==null) || 
             (this.shipping_lastname!=null &&
              this.shipping_lastname.equals(other.getShipping_lastname()))) &&
            ((this.order_id==null && other.getOrder_id()==null) || 
             (this.order_id!=null &&
              this.order_id.equals(other.getOrder_id()))) &&
            ((this.order_increment_id==null && other.getOrder_increment_id()==null) || 
             (this.order_increment_id!=null &&
              this.order_increment_id.equals(other.getOrder_increment_id()))) &&
            ((this.order_created_at==null && other.getOrder_created_at()==null) || 
             (this.order_created_at!=null &&
              this.order_created_at.equals(other.getOrder_created_at()))) &&
            ((this.total_qty==null && other.getTotal_qty()==null) || 
             (this.total_qty!=null &&
              this.total_qty.equals(other.getTotal_qty()))) &&
            ((this.shipment_id==null && other.getShipment_id()==null) || 
             (this.shipment_id!=null &&
              this.shipment_id.equals(other.getShipment_id()))) &&
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems()))) &&
            ((this.tracks==null && other.getTracks()==null) || 
             (this.tracks!=null &&
              java.util.Arrays.equals(this.tracks, other.getTracks()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              java.util.Arrays.equals(this.comments, other.getComments())));
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
        if (getStore_id() != null) {
            _hashCode += getStore_id().hashCode();
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
        if (getShipping_address_id() != null) {
            _hashCode += getShipping_address_id().hashCode();
        }
        if (getShipping_firstname() != null) {
            _hashCode += getShipping_firstname().hashCode();
        }
        if (getShipping_lastname() != null) {
            _hashCode += getShipping_lastname().hashCode();
        }
        if (getOrder_id() != null) {
            _hashCode += getOrder_id().hashCode();
        }
        if (getOrder_increment_id() != null) {
            _hashCode += getOrder_increment_id().hashCode();
        }
        if (getOrder_created_at() != null) {
            _hashCode += getOrder_created_at().hashCode();
        }
        if (getTotal_qty() != null) {
            _hashCode += getTotal_qty().hashCode();
        }
        if (getShipment_id() != null) {
            _hashCode += getShipment_id().hashCode();
        }
        if (getItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTracks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTracks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTracks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComments(), i);
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
        new org.apache.axis.description.TypeDesc(SalesOrderShipmentEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderShipmentEntity"));
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
        elemField.setFieldName("store_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_id"));
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
        elemField.setFieldName("shipping_address_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_address_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_lastname"));
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
        elemField.setFieldName("order_increment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_increment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("order_created_at");
        elemField.setXmlName(new javax.xml.namespace.QName("", "order_created_at"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_qty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "total_qty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderShipmentItemEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tracks");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tracks"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderShipmentTrackEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderShipmentCommentEntity"));
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
