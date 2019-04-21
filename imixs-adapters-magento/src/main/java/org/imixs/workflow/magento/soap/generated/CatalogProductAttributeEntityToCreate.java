/**
 * CatalogProductAttributeEntityToCreate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class CatalogProductAttributeEntityToCreate  implements java.io.Serializable {
    private java.lang.String attribute_code;

    private java.lang.String frontend_input;

    private java.lang.String scope;

    private java.lang.String default_value;

    private java.lang.Integer is_unique;

    private java.lang.Integer is_required;

    private java.lang.String[] apply_to;

    private java.lang.Integer is_configurable;

    private java.lang.Integer is_searchable;

    private java.lang.Integer is_visible_in_advanced_search;

    private java.lang.Integer is_comparable;

    private java.lang.Integer is_used_for_promo_rules;

    private java.lang.Integer is_visible_on_front;

    private java.lang.Integer used_in_product_listing;

    private AssociativeEntity[] additional_fields;

    private CatalogProductAttributeFrontendLabelEntity[] frontend_label;

    public CatalogProductAttributeEntityToCreate() {
    }

    public CatalogProductAttributeEntityToCreate(
           java.lang.String attribute_code,
           java.lang.String frontend_input,
           java.lang.String scope,
           java.lang.String default_value,
           java.lang.Integer is_unique,
           java.lang.Integer is_required,
           java.lang.String[] apply_to,
           java.lang.Integer is_configurable,
           java.lang.Integer is_searchable,
           java.lang.Integer is_visible_in_advanced_search,
           java.lang.Integer is_comparable,
           java.lang.Integer is_used_for_promo_rules,
           java.lang.Integer is_visible_on_front,
           java.lang.Integer used_in_product_listing,
           AssociativeEntity[] additional_fields,
           CatalogProductAttributeFrontendLabelEntity[] frontend_label) {
           this.attribute_code = attribute_code;
           this.frontend_input = frontend_input;
           this.scope = scope;
           this.default_value = default_value;
           this.is_unique = is_unique;
           this.is_required = is_required;
           this.apply_to = apply_to;
           this.is_configurable = is_configurable;
           this.is_searchable = is_searchable;
           this.is_visible_in_advanced_search = is_visible_in_advanced_search;
           this.is_comparable = is_comparable;
           this.is_used_for_promo_rules = is_used_for_promo_rules;
           this.is_visible_on_front = is_visible_on_front;
           this.used_in_product_listing = used_in_product_listing;
           this.additional_fields = additional_fields;
           this.frontend_label = frontend_label;
    }


    /**
     * Gets the attribute_code value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return attribute_code
     */
    public java.lang.String getAttribute_code() {
        return attribute_code;
    }


    /**
     * Sets the attribute_code value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param attribute_code
     */
    public void setAttribute_code(java.lang.String attribute_code) {
        this.attribute_code = attribute_code;
    }


    /**
     * Gets the frontend_input value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return frontend_input
     */
    public java.lang.String getFrontend_input() {
        return frontend_input;
    }


    /**
     * Sets the frontend_input value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param frontend_input
     */
    public void setFrontend_input(java.lang.String frontend_input) {
        this.frontend_input = frontend_input;
    }


    /**
     * Gets the scope value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return scope
     */
    public java.lang.String getScope() {
        return scope;
    }


    /**
     * Sets the scope value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param scope
     */
    public void setScope(java.lang.String scope) {
        this.scope = scope;
    }


    /**
     * Gets the default_value value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return default_value
     */
    public java.lang.String getDefault_value() {
        return default_value;
    }


    /**
     * Sets the default_value value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param default_value
     */
    public void setDefault_value(java.lang.String default_value) {
        this.default_value = default_value;
    }


    /**
     * Gets the is_unique value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_unique
     */
    public java.lang.Integer getIs_unique() {
        return is_unique;
    }


    /**
     * Sets the is_unique value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_unique
     */
    public void setIs_unique(java.lang.Integer is_unique) {
        this.is_unique = is_unique;
    }


    /**
     * Gets the is_required value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_required
     */
    public java.lang.Integer getIs_required() {
        return is_required;
    }


    /**
     * Sets the is_required value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_required
     */
    public void setIs_required(java.lang.Integer is_required) {
        this.is_required = is_required;
    }


    /**
     * Gets the apply_to value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return apply_to
     */
    public java.lang.String[] getApply_to() {
        return apply_to;
    }


    /**
     * Sets the apply_to value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param apply_to
     */
    public void setApply_to(java.lang.String[] apply_to) {
        this.apply_to = apply_to;
    }


    /**
     * Gets the is_configurable value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_configurable
     */
    public java.lang.Integer getIs_configurable() {
        return is_configurable;
    }


    /**
     * Sets the is_configurable value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_configurable
     */
    public void setIs_configurable(java.lang.Integer is_configurable) {
        this.is_configurable = is_configurable;
    }


    /**
     * Gets the is_searchable value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_searchable
     */
    public java.lang.Integer getIs_searchable() {
        return is_searchable;
    }


    /**
     * Sets the is_searchable value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_searchable
     */
    public void setIs_searchable(java.lang.Integer is_searchable) {
        this.is_searchable = is_searchable;
    }


    /**
     * Gets the is_visible_in_advanced_search value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_visible_in_advanced_search
     */
    public java.lang.Integer getIs_visible_in_advanced_search() {
        return is_visible_in_advanced_search;
    }


    /**
     * Sets the is_visible_in_advanced_search value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_visible_in_advanced_search
     */
    public void setIs_visible_in_advanced_search(java.lang.Integer is_visible_in_advanced_search) {
        this.is_visible_in_advanced_search = is_visible_in_advanced_search;
    }


    /**
     * Gets the is_comparable value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_comparable
     */
    public java.lang.Integer getIs_comparable() {
        return is_comparable;
    }


    /**
     * Sets the is_comparable value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_comparable
     */
    public void setIs_comparable(java.lang.Integer is_comparable) {
        this.is_comparable = is_comparable;
    }


    /**
     * Gets the is_used_for_promo_rules value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_used_for_promo_rules
     */
    public java.lang.Integer getIs_used_for_promo_rules() {
        return is_used_for_promo_rules;
    }


    /**
     * Sets the is_used_for_promo_rules value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_used_for_promo_rules
     */
    public void setIs_used_for_promo_rules(java.lang.Integer is_used_for_promo_rules) {
        this.is_used_for_promo_rules = is_used_for_promo_rules;
    }


    /**
     * Gets the is_visible_on_front value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return is_visible_on_front
     */
    public java.lang.Integer getIs_visible_on_front() {
        return is_visible_on_front;
    }


    /**
     * Sets the is_visible_on_front value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param is_visible_on_front
     */
    public void setIs_visible_on_front(java.lang.Integer is_visible_on_front) {
        this.is_visible_on_front = is_visible_on_front;
    }


    /**
     * Gets the used_in_product_listing value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return used_in_product_listing
     */
    public java.lang.Integer getUsed_in_product_listing() {
        return used_in_product_listing;
    }


    /**
     * Sets the used_in_product_listing value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param used_in_product_listing
     */
    public void setUsed_in_product_listing(java.lang.Integer used_in_product_listing) {
        this.used_in_product_listing = used_in_product_listing;
    }


    /**
     * Gets the additional_fields value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return additional_fields
     */
    public AssociativeEntity[] getAdditional_fields() {
        return additional_fields;
    }


    /**
     * Sets the additional_fields value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param additional_fields
     */
    public void setAdditional_fields(AssociativeEntity[] additional_fields) {
        this.additional_fields = additional_fields;
    }


    /**
     * Gets the frontend_label value for this CatalogProductAttributeEntityToCreate.
     * 
     * @return frontend_label
     */
    public CatalogProductAttributeFrontendLabelEntity[] getFrontend_label() {
        return frontend_label;
    }


    /**
     * Sets the frontend_label value for this CatalogProductAttributeEntityToCreate.
     * 
     * @param frontend_label
     */
    public void setFrontend_label(CatalogProductAttributeFrontendLabelEntity[] frontend_label) {
        this.frontend_label = frontend_label;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogProductAttributeEntityToCreate)) return false;
        CatalogProductAttributeEntityToCreate other = (CatalogProductAttributeEntityToCreate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attribute_code==null && other.getAttribute_code()==null) || 
             (this.attribute_code!=null &&
              this.attribute_code.equals(other.getAttribute_code()))) &&
            ((this.frontend_input==null && other.getFrontend_input()==null) || 
             (this.frontend_input!=null &&
              this.frontend_input.equals(other.getFrontend_input()))) &&
            ((this.scope==null && other.getScope()==null) || 
             (this.scope!=null &&
              this.scope.equals(other.getScope()))) &&
            ((this.default_value==null && other.getDefault_value()==null) || 
             (this.default_value!=null &&
              this.default_value.equals(other.getDefault_value()))) &&
            ((this.is_unique==null && other.getIs_unique()==null) || 
             (this.is_unique!=null &&
              this.is_unique.equals(other.getIs_unique()))) &&
            ((this.is_required==null && other.getIs_required()==null) || 
             (this.is_required!=null &&
              this.is_required.equals(other.getIs_required()))) &&
            ((this.apply_to==null && other.getApply_to()==null) || 
             (this.apply_to!=null &&
              java.util.Arrays.equals(this.apply_to, other.getApply_to()))) &&
            ((this.is_configurable==null && other.getIs_configurable()==null) || 
             (this.is_configurable!=null &&
              this.is_configurable.equals(other.getIs_configurable()))) &&
            ((this.is_searchable==null && other.getIs_searchable()==null) || 
             (this.is_searchable!=null &&
              this.is_searchable.equals(other.getIs_searchable()))) &&
            ((this.is_visible_in_advanced_search==null && other.getIs_visible_in_advanced_search()==null) || 
             (this.is_visible_in_advanced_search!=null &&
              this.is_visible_in_advanced_search.equals(other.getIs_visible_in_advanced_search()))) &&
            ((this.is_comparable==null && other.getIs_comparable()==null) || 
             (this.is_comparable!=null &&
              this.is_comparable.equals(other.getIs_comparable()))) &&
            ((this.is_used_for_promo_rules==null && other.getIs_used_for_promo_rules()==null) || 
             (this.is_used_for_promo_rules!=null &&
              this.is_used_for_promo_rules.equals(other.getIs_used_for_promo_rules()))) &&
            ((this.is_visible_on_front==null && other.getIs_visible_on_front()==null) || 
             (this.is_visible_on_front!=null &&
              this.is_visible_on_front.equals(other.getIs_visible_on_front()))) &&
            ((this.used_in_product_listing==null && other.getUsed_in_product_listing()==null) || 
             (this.used_in_product_listing!=null &&
              this.used_in_product_listing.equals(other.getUsed_in_product_listing()))) &&
            ((this.additional_fields==null && other.getAdditional_fields()==null) || 
             (this.additional_fields!=null &&
              java.util.Arrays.equals(this.additional_fields, other.getAdditional_fields()))) &&
            ((this.frontend_label==null && other.getFrontend_label()==null) || 
             (this.frontend_label!=null &&
              java.util.Arrays.equals(this.frontend_label, other.getFrontend_label())));
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
        if (getAttribute_code() != null) {
            _hashCode += getAttribute_code().hashCode();
        }
        if (getFrontend_input() != null) {
            _hashCode += getFrontend_input().hashCode();
        }
        if (getScope() != null) {
            _hashCode += getScope().hashCode();
        }
        if (getDefault_value() != null) {
            _hashCode += getDefault_value().hashCode();
        }
        if (getIs_unique() != null) {
            _hashCode += getIs_unique().hashCode();
        }
        if (getIs_required() != null) {
            _hashCode += getIs_required().hashCode();
        }
        if (getApply_to() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApply_to());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApply_to(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIs_configurable() != null) {
            _hashCode += getIs_configurable().hashCode();
        }
        if (getIs_searchable() != null) {
            _hashCode += getIs_searchable().hashCode();
        }
        if (getIs_visible_in_advanced_search() != null) {
            _hashCode += getIs_visible_in_advanced_search().hashCode();
        }
        if (getIs_comparable() != null) {
            _hashCode += getIs_comparable().hashCode();
        }
        if (getIs_used_for_promo_rules() != null) {
            _hashCode += getIs_used_for_promo_rules().hashCode();
        }
        if (getIs_visible_on_front() != null) {
            _hashCode += getIs_visible_on_front().hashCode();
        }
        if (getUsed_in_product_listing() != null) {
            _hashCode += getUsed_in_product_listing().hashCode();
        }
        if (getAdditional_fields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditional_fields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditional_fields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFrontend_label() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFrontend_label());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFrontend_label(), i);
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
        new org.apache.axis.description.TypeDesc(CatalogProductAttributeEntityToCreate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductAttributeEntityToCreate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attribute_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attribute_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frontend_input");
        elemField.setXmlName(new javax.xml.namespace.QName("", "frontend_input"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scope");
        elemField.setXmlName(new javax.xml.namespace.QName("", "scope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("default_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "default_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_unique");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_unique"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_required");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_required"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apply_to");
        elemField.setXmlName(new javax.xml.namespace.QName("", "apply_to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_configurable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_configurable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_searchable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_searchable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_visible_in_advanced_search");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_visible_in_advanced_search"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_comparable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_comparable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_used_for_promo_rules");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_used_for_promo_rules"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_visible_on_front");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is_visible_on_front"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("used_in_product_listing");
        elemField.setXmlName(new javax.xml.namespace.QName("", "used_in_product_listing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional_fields");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additional_fields"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "associativeEntity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frontend_label");
        elemField.setXmlName(new javax.xml.namespace.QName("", "frontend_label"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:Magento", "catalogProductAttributeFrontendLabelEntity"));
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
