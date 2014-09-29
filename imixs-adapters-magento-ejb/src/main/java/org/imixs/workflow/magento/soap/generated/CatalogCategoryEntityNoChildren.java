/**
 * CatalogCategoryEntityNoChildren.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogCategoryEntityNoChildren  implements java.io.Serializable {
    private int category_id;

    private int parent_id;

    private java.lang.String name;

    private int is_active;

    private int position;

    private int level;

    public CatalogCategoryEntityNoChildren() {
    }

    public CatalogCategoryEntityNoChildren(
           int category_id,
           int parent_id,
           java.lang.String name,
           int is_active,
           int position,
           int level) {
           this.category_id = category_id;
           this.parent_id = parent_id;
           this.name = name;
           this.is_active = is_active;
           this.position = position;
           this.level = level;
    }


    /**
     * Gets the category_id value for this CatalogCategoryEntityNoChildren.
     * 
     * @return category_id
     */
    public int getCategory_id() {
        return category_id;
    }


    /**
     * Sets the category_id value for this CatalogCategoryEntityNoChildren.
     * 
     * @param category_id
     */
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }


    /**
     * Gets the parent_id value for this CatalogCategoryEntityNoChildren.
     * 
     * @return parent_id
     */
    public int getParent_id() {
        return parent_id;
    }


    /**
     * Sets the parent_id value for this CatalogCategoryEntityNoChildren.
     * 
     * @param parent_id
     */
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }


    /**
     * Gets the name value for this CatalogCategoryEntityNoChildren.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CatalogCategoryEntityNoChildren.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the is_active value for this CatalogCategoryEntityNoChildren.
     * 
     * @return is_active
     */
    public int getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this CatalogCategoryEntityNoChildren.
     * 
     * @param is_active
     */
    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the position value for this CatalogCategoryEntityNoChildren.
     * 
     * @return position
     */
    public int getPosition() {
        return position;
    }


    /**
     * Sets the position value for this CatalogCategoryEntityNoChildren.
     * 
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }


    /**
     * Gets the level value for this CatalogCategoryEntityNoChildren.
     * 
     * @return level
     */
    public int getLevel() {
        return level;
    }


    /**
     * Sets the level value for this CatalogCategoryEntityNoChildren.
     * 
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogCategoryEntityNoChildren)) return false;
        CatalogCategoryEntityNoChildren other = (CatalogCategoryEntityNoChildren) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.category_id == other.getCategory_id() &&
            this.parent_id == other.getParent_id() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.is_active == other.getIs_active() &&
            this.position == other.getPosition() &&
            this.level == other.getLevel();
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
        _hashCode += getCategory_id();
        _hashCode += getParent_id();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += getIs_active();
        _hashCode += getPosition();
        _hashCode += getLevel();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogCategoryEntityNoChildren.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogCategoryEntityNoChildren"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "category_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parent_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_active");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("position");
        elemField.setXmlName(new javax.xml.namespace.QName("", "position"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("", "level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
