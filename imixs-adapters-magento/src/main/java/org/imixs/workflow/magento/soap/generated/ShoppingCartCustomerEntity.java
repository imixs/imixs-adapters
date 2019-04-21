/**
 * ShoppingCartCustomerEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class ShoppingCartCustomerEntity  implements java.io.Serializable {
    private java.lang.String mode;

    private java.lang.Integer customer_id;

    private java.lang.String email;

    private java.lang.String firstname;

    private java.lang.String lastname;

    private java.lang.String password;

    private java.lang.String confirmation;

    private java.lang.Integer website_id;

    private java.lang.Integer store_id;

    private java.lang.Integer group_id;

    public ShoppingCartCustomerEntity() {
    }

    public ShoppingCartCustomerEntity(
           java.lang.String mode,
           java.lang.Integer customer_id,
           java.lang.String email,
           java.lang.String firstname,
           java.lang.String lastname,
           java.lang.String password,
           java.lang.String confirmation,
           java.lang.Integer website_id,
           java.lang.Integer store_id,
           java.lang.Integer group_id) {
           this.mode = mode;
           this.customer_id = customer_id;
           this.email = email;
           this.firstname = firstname;
           this.lastname = lastname;
           this.password = password;
           this.confirmation = confirmation;
           this.website_id = website_id;
           this.store_id = store_id;
           this.group_id = group_id;
    }


    /**
     * Gets the mode value for this ShoppingCartCustomerEntity.
     * 
     * @return mode
     */
    public java.lang.String getMode() {
        return mode;
    }


    /**
     * Sets the mode value for this ShoppingCartCustomerEntity.
     * 
     * @param mode
     */
    public void setMode(java.lang.String mode) {
        this.mode = mode;
    }


    /**
     * Gets the customer_id value for this ShoppingCartCustomerEntity.
     * 
     * @return customer_id
     */
    public java.lang.Integer getCustomer_id() {
        return customer_id;
    }


    /**
     * Sets the customer_id value for this ShoppingCartCustomerEntity.
     * 
     * @param customer_id
     */
    public void setCustomer_id(java.lang.Integer customer_id) {
        this.customer_id = customer_id;
    }


    /**
     * Gets the email value for this ShoppingCartCustomerEntity.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this ShoppingCartCustomerEntity.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the firstname value for this ShoppingCartCustomerEntity.
     * 
     * @return firstname
     */
    public java.lang.String getFirstname() {
        return firstname;
    }


    /**
     * Sets the firstname value for this ShoppingCartCustomerEntity.
     * 
     * @param firstname
     */
    public void setFirstname(java.lang.String firstname) {
        this.firstname = firstname;
    }


    /**
     * Gets the lastname value for this ShoppingCartCustomerEntity.
     * 
     * @return lastname
     */
    public java.lang.String getLastname() {
        return lastname;
    }


    /**
     * Sets the lastname value for this ShoppingCartCustomerEntity.
     * 
     * @param lastname
     */
    public void setLastname(java.lang.String lastname) {
        this.lastname = lastname;
    }


    /**
     * Gets the password value for this ShoppingCartCustomerEntity.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ShoppingCartCustomerEntity.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the confirmation value for this ShoppingCartCustomerEntity.
     * 
     * @return confirmation
     */
    public java.lang.String getConfirmation() {
        return confirmation;
    }


    /**
     * Sets the confirmation value for this ShoppingCartCustomerEntity.
     * 
     * @param confirmation
     */
    public void setConfirmation(java.lang.String confirmation) {
        this.confirmation = confirmation;
    }


    /**
     * Gets the website_id value for this ShoppingCartCustomerEntity.
     * 
     * @return website_id
     */
    public java.lang.Integer getWebsite_id() {
        return website_id;
    }


    /**
     * Sets the website_id value for this ShoppingCartCustomerEntity.
     * 
     * @param website_id
     */
    public void setWebsite_id(java.lang.Integer website_id) {
        this.website_id = website_id;
    }


    /**
     * Gets the store_id value for this ShoppingCartCustomerEntity.
     * 
     * @return store_id
     */
    public java.lang.Integer getStore_id() {
        return store_id;
    }


    /**
     * Sets the store_id value for this ShoppingCartCustomerEntity.
     * 
     * @param store_id
     */
    public void setStore_id(java.lang.Integer store_id) {
        this.store_id = store_id;
    }


    /**
     * Gets the group_id value for this ShoppingCartCustomerEntity.
     * 
     * @return group_id
     */
    public java.lang.Integer getGroup_id() {
        return group_id;
    }


    /**
     * Sets the group_id value for this ShoppingCartCustomerEntity.
     * 
     * @param group_id
     */
    public void setGroup_id(java.lang.Integer group_id) {
        this.group_id = group_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShoppingCartCustomerEntity)) return false;
        ShoppingCartCustomerEntity other = (ShoppingCartCustomerEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mode==null && other.getMode()==null) || 
             (this.mode!=null &&
              this.mode.equals(other.getMode()))) &&
            ((this.customer_id==null && other.getCustomer_id()==null) || 
             (this.customer_id!=null &&
              this.customer_id.equals(other.getCustomer_id()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.firstname==null && other.getFirstname()==null) || 
             (this.firstname!=null &&
              this.firstname.equals(other.getFirstname()))) &&
            ((this.lastname==null && other.getLastname()==null) || 
             (this.lastname!=null &&
              this.lastname.equals(other.getLastname()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.confirmation==null && other.getConfirmation()==null) || 
             (this.confirmation!=null &&
              this.confirmation.equals(other.getConfirmation()))) &&
            ((this.website_id==null && other.getWebsite_id()==null) || 
             (this.website_id!=null &&
              this.website_id.equals(other.getWebsite_id()))) &&
            ((this.store_id==null && other.getStore_id()==null) || 
             (this.store_id!=null &&
              this.store_id.equals(other.getStore_id()))) &&
            ((this.group_id==null && other.getGroup_id()==null) || 
             (this.group_id!=null &&
              this.group_id.equals(other.getGroup_id())));
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
        if (getMode() != null) {
            _hashCode += getMode().hashCode();
        }
        if (getCustomer_id() != null) {
            _hashCode += getCustomer_id().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFirstname() != null) {
            _hashCode += getFirstname().hashCode();
        }
        if (getLastname() != null) {
            _hashCode += getLastname().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getConfirmation() != null) {
            _hashCode += getConfirmation().hashCode();
        }
        if (getWebsite_id() != null) {
            _hashCode += getWebsite_id().hashCode();
        }
        if (getStore_id() != null) {
            _hashCode += getStore_id().hashCode();
        }
        if (getGroup_id() != null) {
            _hashCode += getGroup_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShoppingCartCustomerEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "shoppingCartCustomerEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customer_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customer_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirmation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("website_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "website_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("store_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "store_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group_id"));
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
