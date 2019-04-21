/**
 * SalesOrderPaymentEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap.generated;

public class SalesOrderPaymentEntity  implements java.io.Serializable {
    private java.lang.String increment_id;

    private java.lang.String parent_id;

    private java.lang.String created_at;

    private java.lang.String updated_at;

    private java.lang.String is_active;

    private java.lang.String amount_ordered;

    private java.lang.String shipping_amount;

    private java.lang.String base_amount_ordered;

    private java.lang.String base_shipping_amount;

    private java.lang.String method;

    private java.lang.String po_number;

    private java.lang.String cc_type;

    private java.lang.String cc_number_enc;

    private java.lang.String cc_last4;

    private java.lang.String cc_owner;

    private java.lang.String cc_exp_month;

    private java.lang.String cc_exp_year;

    private java.lang.String cc_ss_start_month;

    private java.lang.String cc_ss_start_year;

    private java.lang.String payment_id;

    public SalesOrderPaymentEntity() {
    }

    public SalesOrderPaymentEntity(
           java.lang.String increment_id,
           java.lang.String parent_id,
           java.lang.String created_at,
           java.lang.String updated_at,
           java.lang.String is_active,
           java.lang.String amount_ordered,
           java.lang.String shipping_amount,
           java.lang.String base_amount_ordered,
           java.lang.String base_shipping_amount,
           java.lang.String method,
           java.lang.String po_number,
           java.lang.String cc_type,
           java.lang.String cc_number_enc,
           java.lang.String cc_last4,
           java.lang.String cc_owner,
           java.lang.String cc_exp_month,
           java.lang.String cc_exp_year,
           java.lang.String cc_ss_start_month,
           java.lang.String cc_ss_start_year,
           java.lang.String payment_id) {
           this.increment_id = increment_id;
           this.parent_id = parent_id;
           this.created_at = created_at;
           this.updated_at = updated_at;
           this.is_active = is_active;
           this.amount_ordered = amount_ordered;
           this.shipping_amount = shipping_amount;
           this.base_amount_ordered = base_amount_ordered;
           this.base_shipping_amount = base_shipping_amount;
           this.method = method;
           this.po_number = po_number;
           this.cc_type = cc_type;
           this.cc_number_enc = cc_number_enc;
           this.cc_last4 = cc_last4;
           this.cc_owner = cc_owner;
           this.cc_exp_month = cc_exp_month;
           this.cc_exp_year = cc_exp_year;
           this.cc_ss_start_month = cc_ss_start_month;
           this.cc_ss_start_year = cc_ss_start_year;
           this.payment_id = payment_id;
    }


    /**
     * Gets the increment_id value for this SalesOrderPaymentEntity.
     * 
     * @return increment_id
     */
    public java.lang.String getIncrement_id() {
        return increment_id;
    }


    /**
     * Sets the increment_id value for this SalesOrderPaymentEntity.
     * 
     * @param increment_id
     */
    public void setIncrement_id(java.lang.String increment_id) {
        this.increment_id = increment_id;
    }


    /**
     * Gets the parent_id value for this SalesOrderPaymentEntity.
     * 
     * @return parent_id
     */
    public java.lang.String getParent_id() {
        return parent_id;
    }


    /**
     * Sets the parent_id value for this SalesOrderPaymentEntity.
     * 
     * @param parent_id
     */
    public void setParent_id(java.lang.String parent_id) {
        this.parent_id = parent_id;
    }


    /**
     * Gets the created_at value for this SalesOrderPaymentEntity.
     * 
     * @return created_at
     */
    public java.lang.String getCreated_at() {
        return created_at;
    }


    /**
     * Sets the created_at value for this SalesOrderPaymentEntity.
     * 
     * @param created_at
     */
    public void setCreated_at(java.lang.String created_at) {
        this.created_at = created_at;
    }


    /**
     * Gets the updated_at value for this SalesOrderPaymentEntity.
     * 
     * @return updated_at
     */
    public java.lang.String getUpdated_at() {
        return updated_at;
    }


    /**
     * Sets the updated_at value for this SalesOrderPaymentEntity.
     * 
     * @param updated_at
     */
    public void setUpdated_at(java.lang.String updated_at) {
        this.updated_at = updated_at;
    }


    /**
     * Gets the is_active value for this SalesOrderPaymentEntity.
     * 
     * @return is_active
     */
    public java.lang.String getIs_active() {
        return is_active;
    }


    /**
     * Sets the is_active value for this SalesOrderPaymentEntity.
     * 
     * @param is_active
     */
    public void setIs_active(java.lang.String is_active) {
        this.is_active = is_active;
    }


    /**
     * Gets the amount_ordered value for this SalesOrderPaymentEntity.
     * 
     * @return amount_ordered
     */
    public java.lang.String getAmount_ordered() {
        return amount_ordered;
    }


    /**
     * Sets the amount_ordered value for this SalesOrderPaymentEntity.
     * 
     * @param amount_ordered
     */
    public void setAmount_ordered(java.lang.String amount_ordered) {
        this.amount_ordered = amount_ordered;
    }


    /**
     * Gets the shipping_amount value for this SalesOrderPaymentEntity.
     * 
     * @return shipping_amount
     */
    public java.lang.String getShipping_amount() {
        return shipping_amount;
    }


    /**
     * Sets the shipping_amount value for this SalesOrderPaymentEntity.
     * 
     * @param shipping_amount
     */
    public void setShipping_amount(java.lang.String shipping_amount) {
        this.shipping_amount = shipping_amount;
    }


    /**
     * Gets the base_amount_ordered value for this SalesOrderPaymentEntity.
     * 
     * @return base_amount_ordered
     */
    public java.lang.String getBase_amount_ordered() {
        return base_amount_ordered;
    }


    /**
     * Sets the base_amount_ordered value for this SalesOrderPaymentEntity.
     * 
     * @param base_amount_ordered
     */
    public void setBase_amount_ordered(java.lang.String base_amount_ordered) {
        this.base_amount_ordered = base_amount_ordered;
    }


    /**
     * Gets the base_shipping_amount value for this SalesOrderPaymentEntity.
     * 
     * @return base_shipping_amount
     */
    public java.lang.String getBase_shipping_amount() {
        return base_shipping_amount;
    }


    /**
     * Sets the base_shipping_amount value for this SalesOrderPaymentEntity.
     * 
     * @param base_shipping_amount
     */
    public void setBase_shipping_amount(java.lang.String base_shipping_amount) {
        this.base_shipping_amount = base_shipping_amount;
    }


    /**
     * Gets the method value for this SalesOrderPaymentEntity.
     * 
     * @return method
     */
    public java.lang.String getMethod() {
        return method;
    }


    /**
     * Sets the method value for this SalesOrderPaymentEntity.
     * 
     * @param method
     */
    public void setMethod(java.lang.String method) {
        this.method = method;
    }


    /**
     * Gets the po_number value for this SalesOrderPaymentEntity.
     * 
     * @return po_number
     */
    public java.lang.String getPo_number() {
        return po_number;
    }


    /**
     * Sets the po_number value for this SalesOrderPaymentEntity.
     * 
     * @param po_number
     */
    public void setPo_number(java.lang.String po_number) {
        this.po_number = po_number;
    }


    /**
     * Gets the cc_type value for this SalesOrderPaymentEntity.
     * 
     * @return cc_type
     */
    public java.lang.String getCc_type() {
        return cc_type;
    }


    /**
     * Sets the cc_type value for this SalesOrderPaymentEntity.
     * 
     * @param cc_type
     */
    public void setCc_type(java.lang.String cc_type) {
        this.cc_type = cc_type;
    }


    /**
     * Gets the cc_number_enc value for this SalesOrderPaymentEntity.
     * 
     * @return cc_number_enc
     */
    public java.lang.String getCc_number_enc() {
        return cc_number_enc;
    }


    /**
     * Sets the cc_number_enc value for this SalesOrderPaymentEntity.
     * 
     * @param cc_number_enc
     */
    public void setCc_number_enc(java.lang.String cc_number_enc) {
        this.cc_number_enc = cc_number_enc;
    }


    /**
     * Gets the cc_last4 value for this SalesOrderPaymentEntity.
     * 
     * @return cc_last4
     */
    public java.lang.String getCc_last4() {
        return cc_last4;
    }


    /**
     * Sets the cc_last4 value for this SalesOrderPaymentEntity.
     * 
     * @param cc_last4
     */
    public void setCc_last4(java.lang.String cc_last4) {
        this.cc_last4 = cc_last4;
    }


    /**
     * Gets the cc_owner value for this SalesOrderPaymentEntity.
     * 
     * @return cc_owner
     */
    public java.lang.String getCc_owner() {
        return cc_owner;
    }


    /**
     * Sets the cc_owner value for this SalesOrderPaymentEntity.
     * 
     * @param cc_owner
     */
    public void setCc_owner(java.lang.String cc_owner) {
        this.cc_owner = cc_owner;
    }


    /**
     * Gets the cc_exp_month value for this SalesOrderPaymentEntity.
     * 
     * @return cc_exp_month
     */
    public java.lang.String getCc_exp_month() {
        return cc_exp_month;
    }


    /**
     * Sets the cc_exp_month value for this SalesOrderPaymentEntity.
     * 
     * @param cc_exp_month
     */
    public void setCc_exp_month(java.lang.String cc_exp_month) {
        this.cc_exp_month = cc_exp_month;
    }


    /**
     * Gets the cc_exp_year value for this SalesOrderPaymentEntity.
     * 
     * @return cc_exp_year
     */
    public java.lang.String getCc_exp_year() {
        return cc_exp_year;
    }


    /**
     * Sets the cc_exp_year value for this SalesOrderPaymentEntity.
     * 
     * @param cc_exp_year
     */
    public void setCc_exp_year(java.lang.String cc_exp_year) {
        this.cc_exp_year = cc_exp_year;
    }


    /**
     * Gets the cc_ss_start_month value for this SalesOrderPaymentEntity.
     * 
     * @return cc_ss_start_month
     */
    public java.lang.String getCc_ss_start_month() {
        return cc_ss_start_month;
    }


    /**
     * Sets the cc_ss_start_month value for this SalesOrderPaymentEntity.
     * 
     * @param cc_ss_start_month
     */
    public void setCc_ss_start_month(java.lang.String cc_ss_start_month) {
        this.cc_ss_start_month = cc_ss_start_month;
    }


    /**
     * Gets the cc_ss_start_year value for this SalesOrderPaymentEntity.
     * 
     * @return cc_ss_start_year
     */
    public java.lang.String getCc_ss_start_year() {
        return cc_ss_start_year;
    }


    /**
     * Sets the cc_ss_start_year value for this SalesOrderPaymentEntity.
     * 
     * @param cc_ss_start_year
     */
    public void setCc_ss_start_year(java.lang.String cc_ss_start_year) {
        this.cc_ss_start_year = cc_ss_start_year;
    }


    /**
     * Gets the payment_id value for this SalesOrderPaymentEntity.
     * 
     * @return payment_id
     */
    public java.lang.String getPayment_id() {
        return payment_id;
    }


    /**
     * Sets the payment_id value for this SalesOrderPaymentEntity.
     * 
     * @param payment_id
     */
    public void setPayment_id(java.lang.String payment_id) {
        this.payment_id = payment_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderPaymentEntity)) return false;
        SalesOrderPaymentEntity other = (SalesOrderPaymentEntity) obj;
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
            ((this.amount_ordered==null && other.getAmount_ordered()==null) || 
             (this.amount_ordered!=null &&
              this.amount_ordered.equals(other.getAmount_ordered()))) &&
            ((this.shipping_amount==null && other.getShipping_amount()==null) || 
             (this.shipping_amount!=null &&
              this.shipping_amount.equals(other.getShipping_amount()))) &&
            ((this.base_amount_ordered==null && other.getBase_amount_ordered()==null) || 
             (this.base_amount_ordered!=null &&
              this.base_amount_ordered.equals(other.getBase_amount_ordered()))) &&
            ((this.base_shipping_amount==null && other.getBase_shipping_amount()==null) || 
             (this.base_shipping_amount!=null &&
              this.base_shipping_amount.equals(other.getBase_shipping_amount()))) &&
            ((this.method==null && other.getMethod()==null) || 
             (this.method!=null &&
              this.method.equals(other.getMethod()))) &&
            ((this.po_number==null && other.getPo_number()==null) || 
             (this.po_number!=null &&
              this.po_number.equals(other.getPo_number()))) &&
            ((this.cc_type==null && other.getCc_type()==null) || 
             (this.cc_type!=null &&
              this.cc_type.equals(other.getCc_type()))) &&
            ((this.cc_number_enc==null && other.getCc_number_enc()==null) || 
             (this.cc_number_enc!=null &&
              this.cc_number_enc.equals(other.getCc_number_enc()))) &&
            ((this.cc_last4==null && other.getCc_last4()==null) || 
             (this.cc_last4!=null &&
              this.cc_last4.equals(other.getCc_last4()))) &&
            ((this.cc_owner==null && other.getCc_owner()==null) || 
             (this.cc_owner!=null &&
              this.cc_owner.equals(other.getCc_owner()))) &&
            ((this.cc_exp_month==null && other.getCc_exp_month()==null) || 
             (this.cc_exp_month!=null &&
              this.cc_exp_month.equals(other.getCc_exp_month()))) &&
            ((this.cc_exp_year==null && other.getCc_exp_year()==null) || 
             (this.cc_exp_year!=null &&
              this.cc_exp_year.equals(other.getCc_exp_year()))) &&
            ((this.cc_ss_start_month==null && other.getCc_ss_start_month()==null) || 
             (this.cc_ss_start_month!=null &&
              this.cc_ss_start_month.equals(other.getCc_ss_start_month()))) &&
            ((this.cc_ss_start_year==null && other.getCc_ss_start_year()==null) || 
             (this.cc_ss_start_year!=null &&
              this.cc_ss_start_year.equals(other.getCc_ss_start_year()))) &&
            ((this.payment_id==null && other.getPayment_id()==null) || 
             (this.payment_id!=null &&
              this.payment_id.equals(other.getPayment_id())));
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
        if (getAmount_ordered() != null) {
            _hashCode += getAmount_ordered().hashCode();
        }
        if (getShipping_amount() != null) {
            _hashCode += getShipping_amount().hashCode();
        }
        if (getBase_amount_ordered() != null) {
            _hashCode += getBase_amount_ordered().hashCode();
        }
        if (getBase_shipping_amount() != null) {
            _hashCode += getBase_shipping_amount().hashCode();
        }
        if (getMethod() != null) {
            _hashCode += getMethod().hashCode();
        }
        if (getPo_number() != null) {
            _hashCode += getPo_number().hashCode();
        }
        if (getCc_type() != null) {
            _hashCode += getCc_type().hashCode();
        }
        if (getCc_number_enc() != null) {
            _hashCode += getCc_number_enc().hashCode();
        }
        if (getCc_last4() != null) {
            _hashCode += getCc_last4().hashCode();
        }
        if (getCc_owner() != null) {
            _hashCode += getCc_owner().hashCode();
        }
        if (getCc_exp_month() != null) {
            _hashCode += getCc_exp_month().hashCode();
        }
        if (getCc_exp_year() != null) {
            _hashCode += getCc_exp_year().hashCode();
        }
        if (getCc_ss_start_month() != null) {
            _hashCode += getCc_ss_start_month().hashCode();
        }
        if (getCc_ss_start_year() != null) {
            _hashCode += getCc_ss_start_year().hashCode();
        }
        if (getPayment_id() != null) {
            _hashCode += getPayment_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderPaymentEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:Magento", "salesOrderPaymentEntity"));
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
        elemField.setFieldName("amount_ordered");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amount_ordered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shipping_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_amount_ordered");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_amount_ordered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_shipping_amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_shipping_amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method");
        elemField.setXmlName(new javax.xml.namespace.QName("", "method"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("po_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "po_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_number_enc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_number_enc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_last4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_last4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_owner");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_owner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_exp_month");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_exp_month"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_exp_year");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_exp_year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_ss_start_month");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_ss_start_month"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cc_ss_start_year");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cc_ss_start_year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payment_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payment_id"));
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
