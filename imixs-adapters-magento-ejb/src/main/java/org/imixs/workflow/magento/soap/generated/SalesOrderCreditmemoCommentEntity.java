/**
 * SalesOrderCreditmemoCommentEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderCreditmemoCommentEntity  implements java.io.Serializable {
    private java.lang.String parent_id;

    private java.lang.String created_at;

    private java.lang.String comment;

    private java.lang.String is_customer_notified;

    private java.lang.String comment_id;

    private java.lang.String is_visible_on_front;

    public SalesOrderCreditmemoCommentEntity() {
    }

    public SalesOrderCreditmemoCommentEntity(
           java.lang.String parent_id,
           java.lang.String created_at,
           java.lang.String comment,
           java.lang.String is_customer_notified,
           java.lang.String comment_id,
           java.lang.String is_visible_on_front) {
           this.parent_id = parent_id;
           this.created_at = created_at;
           this.comment = comment;
           this.is_customer_notified = is_customer_notified;
           this.comment_id = comment_id;
           this.is_visible_on_front = is_visible_on_front;
    }


    /**
     * Gets the parent_id value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @return parent_id
     */
    public java.lang.String getParent_id() {
        return parent_id;
    }


    /**
     * Sets the parent_id value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @param parent_id
     */
    public void setParent_id(java.lang.String parent_id) {
        this.parent_id = parent_id;
    }


    /**
     * Gets the created_at value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the comment value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the is_customer_notified value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @return is_customer_notified
     */
    public java.lang.String getIs_customer_notified() {
        return is_customer_notified;
    }


    /**
     * Sets the is_customer_notified value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @param is_customer_notified
     */
    public void setIs_customer_notified(java.lang.String is_customer_notified) {
        this.is_customer_notified = is_customer_notified;
    }


    /**
     * Gets the comment_id value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @return comment_id
     */
    public java.lang.String getComment_id() {
        return comment_id;
    }


    /**
     * Sets the comment_id value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @param comment_id
     */
    public void setComment_id(java.lang.String comment_id) {
        this.comment_id = comment_id;
    }


    /**
     * Gets the is_visible_on_front value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @return is_visible_on_front
     */
    public java.lang.String getIs_visible_on_front() {
        return is_visible_on_front;
    }


    /**
     * Sets the is_visible_on_front value for this SalesOrderCreditmemoCommentEntity.
     * 
     * @param is_visible_on_front
     */
    public void setIs_visible_on_front(java.lang.String is_visible_on_front) {
        this.is_visible_on_front = is_visible_on_front;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderCreditmemoCommentEntity)) return false;
        SalesOrderCreditmemoCommentEntity other = (SalesOrderCreditmemoCommentEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parent_id==null && other.getParent_id()==null) || 
             (this.parent_id!=null &&
              this.parent_id.equals(other.getParent_id()))) &&
            ((this.created_at==null && other.getCreated_at()==null) || 
             (this.created_at!=null &&
              this.created_at.equals(other.getCreated_at()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.is_customer_notified==null && other.getIs_customer_notified()==null) || 
             (this.is_customer_notified!=null &&
              this.is_customer_notified.equals(other.getIs_customer_notified()))) &&
            ((this.comment_id==null && other.getComment_id()==null) || 
             (this.comment_id!=null &&
              this.comment_id.equals(other.getComment_id()))) &&
            ((this.is_visible_on_front==null && other.getIs_visible_on_front()==null) || 
             (this.is_visible_on_front!=null &&
              this.is_visible_on_front.equals(other.getIs_visible_on_front())));
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
        if (getParent_id() != null) {
            _hashCode += getParent_id().hashCode();
        }
        if (getCreated_at() != null) {
            _hashCode += getCreated_at().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getIs_customer_notified() != null) {
            _hashCode += getIs_customer_notified().hashCode();
        }
        if (getComment_id() != null) {
            _hashCode += getComment_id().hashCode();
        }
        if (getIs_visible_on_front() != null) {
            _hashCode += getIs_visible_on_front().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderCreditmemoCommentEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderCreditmemoCommentEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_customer_notified");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_customer_notified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_visible_on_front");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_visible_on_front"));
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
