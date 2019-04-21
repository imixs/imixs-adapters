/**
 * CatalogInventoryStockItemEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogInventoryStockItemEntity  implements java.io.Serializable {
    private java.lang.String product_id;

    private java.lang.String sku;

    private java.lang.String qty;

    private java.lang.String is_in_stock;

    public CatalogInventoryStockItemEntity() {
    }

    public CatalogInventoryStockItemEntity(
           java.lang.String product_id,
           java.lang.String sku,
           java.lang.String qty,
           java.lang.String is_in_stock) {
           this.product_id = product_id;
           this.sku = sku;
           this.qty = qty;
           this.is_in_stock = is_in_stock;
    }


    /**
     * Gets the product_id value for this CatalogInventoryStockItemEntity.
     * 
     * @return product_id
     */
    public java.lang.String getProduct_id() {
        return product_id;
    }


    /**
     * Sets the product_id value for this CatalogInventoryStockItemEntity.
     * 
     * @param product_id
     */
    public void setProduct_id(java.lang.String product_id) {
        this.product_id = product_id;
    }


    /**
     * Gets the sku value for this CatalogInventoryStockItemEntity.
     * 
     * @return sku
     */
    public java.lang.String getSku() {
        return sku;
    }


    /**
     * Sets the sku value for this CatalogInventoryStockItemEntity.
     * 
     * @param sku
     */
    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }


    /**
     * Gets the qty value for this CatalogInventoryStockItemEntity.
     * 
     * @return qty
     */
    public java.lang.String getQty() {
        return qty;
    }


    /**
     * Sets the qty value for this CatalogInventoryStockItemEntity.
     * 
     * @param qty
     */
    public void setQty(java.lang.String qty) {
        this.qty = qty;
    }


    /**
     * Gets the is_in_stock value for this CatalogInventoryStockItemEntity.
     * 
     * @return is_in_stock
     */
    public java.lang.String getIs_in_stock() {
        return is_in_stock;
    }


    /**
     * Sets the is_in_stock value for this CatalogInventoryStockItemEntity.
     * 
     * @param is_in_stock
     */
    public void setIs_in_stock(java.lang.String is_in_stock) {
        this.is_in_stock = is_in_stock;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogInventoryStockItemEntity)) return false;
        CatalogInventoryStockItemEntity other = (CatalogInventoryStockItemEntity) obj;
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
            ((this.is_in_stock==null && other.getIs_in_stock()==null) || 
             (this.is_in_stock!=null &&
              this.is_in_stock.equals(other.getIs_in_stock())));
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
        if (getIs_in_stock() != null) {
            _hashCode += getIs_in_stock().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogInventoryStockItemEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogInventoryStockItemEntity"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_in_stock");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_in_stock"));
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
