/**
 * ShoppingCartProductEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartProductEntity  implements java.io.Serializable {
    private java.lang.String product_id;

    private java.lang.String sku;

    private java.lang.Double qty;

    private AssociativeEntity[] options;

    private AssociativeEntity[] bundle_option;

    private AssociativeEntity[] bundle_option_qty;

    private java.lang.String[] links;

    public ShoppingCartProductEntity() {
    }

    public ShoppingCartProductEntity(
           java.lang.String product_id,
           java.lang.String sku,
           java.lang.Double qty,
           AssociativeEntity[] options,
           AssociativeEntity[] bundle_option,
           AssociativeEntity[] bundle_option_qty,
           java.lang.String[] links) {
           this.product_id = product_id;
           this.sku = sku;
           this.qty = qty;
           this.options = options;
           this.bundle_option = bundle_option;
           this.bundle_option_qty = bundle_option_qty;
           this.links = links;
    }


    /**
     * Gets the product_id value for this ShoppingCartProductEntity.
     * 
     * @return product_id
     */
    public java.lang.String getProduct_id() {
        return product_id;
    }


    /**
     * Sets the product_id value for this ShoppingCartProductEntity.
     * 
     * @param product_id
     */
    public void setProduct_id(java.lang.String product_id) {
        this.product_id = product_id;
    }


    /**
     * Gets the sku value for this ShoppingCartProductEntity.
     * 
     * @return sku
     */
    public java.lang.String getSku() {
        return sku;
    }


    /**
     * Sets the sku value for this ShoppingCartProductEntity.
     * 
     * @param sku
     */
    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }


    /**
     * Gets the qty value for this ShoppingCartProductEntity.
     * 
     * @return qty
     */
    public java.lang.Double getQty() {
        return qty;
    }


    /**
     * Sets the qty value for this ShoppingCartProductEntity.
     * 
     * @param qty
     */
    public void setQty(java.lang.Double qty) {
        this.qty = qty;
    }


    /**
     * Gets the options value for this ShoppingCartProductEntity.
     * 
     * @return options
     */
    public AssociativeEntity[] getOptions() {
        return options;
    }


    /**
     * Sets the options value for this ShoppingCartProductEntity.
     * 
     * @param options
     */
    public void setOptions(AssociativeEntity[] options) {
        this.options = options;
    }


    /**
     * Gets the bundle_option value for this ShoppingCartProductEntity.
     * 
     * @return bundle_option
     */
    public AssociativeEntity[] getBundle_option() {
        return bundle_option;
    }


    /**
     * Sets the bundle_option value for this ShoppingCartProductEntity.
     * 
     * @param bundle_option
     */
    public void setBundle_option(AssociativeEntity[] bundle_option) {
        this.bundle_option = bundle_option;
    }


    /**
     * Gets the bundle_option_qty value for this ShoppingCartProductEntity.
     * 
     * @return bundle_option_qty
     */
    public AssociativeEntity[] getBundle_option_qty() {
        return bundle_option_qty;
    }


    /**
     * Sets the bundle_option_qty value for this ShoppingCartProductEntity.
     * 
     * @param bundle_option_qty
     */
    public void setBundle_option_qty(AssociativeEntity[] bundle_option_qty) {
        this.bundle_option_qty = bundle_option_qty;
    }


    /**
     * Gets the links value for this ShoppingCartProductEntity.
     * 
     * @return links
     */
    public java.lang.String[] getLinks() {
        return links;
    }


    /**
     * Sets the links value for this ShoppingCartProductEntity.
     * 
     * @param links
     */
    public void setLinks(java.lang.String[] links) {
        this.links = links;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartProductEntity)) return false;
        ShoppingCartProductEntity other = (ShoppingCartProductEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.product_id==null && other.getProduct_id()==null) || 
             (this.product_id!=null &&
              this.product_id.equals(other.getProduct_id()))) &&
            ((this.sku==null && other.getSku()==null) || 
             (this.sku!=null &&
              this.sku.equals(other.getSku()))) &&
            ((this.qty==null && other.getQty()==null) || 
             (this.qty!=null &&
              this.qty.equals(other.getQty()))) &&
            ((this.options==null && other.getOptions()==null) || 
             (this.options!=null &&
              java.util.Arrays.equals(this.options, other.getOptions()))) &&
            ((this.bundle_option==null && other.getBundle_option()==null) || 
             (this.bundle_option!=null &&
              java.util.Arrays.equals(this.bundle_option, other.getBundle_option()))) &&
            ((this.bundle_option_qty==null && other.getBundle_option_qty()==null) || 
             (this.bundle_option_qty!=null &&
              java.util.Arrays.equals(this.bundle_option_qty, other.getBundle_option_qty()))) &&
            ((this.links==null && other.getLinks()==null) || 
             (this.links!=null &&
              java.util.Arrays.equals(this.links, other.getLinks())));
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
        if (getProduct_id() != null) {
            _hashCode += getProduct_id().hashCode();
        }
        if (getSku() != null) {
            _hashCode += getSku().hashCode();
        }
        if (getQty() != null) {
            _hashCode += getQty().hashCode();
        }
        if (getOptions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOptions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOptions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBundle_option() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBundle_option());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBundle_option(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBundle_option_qty() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBundle_option_qty());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBundle_option_qty(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartProductEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartProductEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sku");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sku"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options");
        elemField.setXmlName(new javax.xml.namespace.QName("", "options"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "associativeEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bundle_option");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bundle_option"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "associativeEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bundle_option_qty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bundle_option_qty"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "associativeEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("", "links"));
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
