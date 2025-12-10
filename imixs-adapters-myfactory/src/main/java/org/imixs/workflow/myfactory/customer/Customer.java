package org.imixs.workflow.myfactory.customer;

/**
 * Represents a customer from MyFactory.
 * Contains the most important fields for customer identification and display.
 */
public class Customer {

    // Customer-specific fields
    private int customerId;
    private String customerNumber;
    private String customerGroup;

    // Address fields
    private int addressId;
    private String addressNumber;
    private String matchcode;
    private String name1; // Company name
    private String name2; // Additional name
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNr;
    private String faxNr;
    private String email;
    private String homepage;

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getMatchcode() {
        return matchcode;
    }

    public void setMatchcode(String matchcode) {
        this.matchcode = matchcode;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getFaxNr() {
        return faxNr;
    }

    public void setFaxNr(String faxNr) {
        this.faxNr = faxNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * Returns a display-friendly representation of the customer.
     */
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder();
        if (customerNumber != null) {
            sb.append("[").append(customerNumber).append("] ");
        }
        if (name1 != null) {
            sb.append(name1);
        }
        if (city != null) {
            sb.append(" (").append(city).append(")");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerNumber='" + customerNumber + '\'' +
                ", name1='" + name1 + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}