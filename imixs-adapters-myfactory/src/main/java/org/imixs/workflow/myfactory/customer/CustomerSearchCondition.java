package org.imixs.workflow.myfactory.customer;

/**
 * Search condition for customers.
 * Combines CustomerCondition and AddressCondition from WSDL.
 */
public class CustomerSearchCondition {
    // Customer-level fields
    private String customerNumber;
    private String customerGroup;

    // Address-level fields
    private String addressNumber;
    private String matchcode;
    private String name1;
    private String name2;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private String email;

    // Getters and fluent setters

    public String getCustomerNumber() {
        return customerNumber;
    }

    public CustomerSearchCondition setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        return this;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public CustomerSearchCondition setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
        return this;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public CustomerSearchCondition setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
        return this;
    }

    public String getMatchcode() {
        return matchcode;
    }

    public CustomerSearchCondition setMatchcode(String matchcode) {
        this.matchcode = matchcode;
        return this;
    }

    public String getName1() {
        return name1;
    }

    public CustomerSearchCondition setName1(String name1) {
        this.name1 = name1;
        return this;
    }

    public String getName2() {
        return name2;
    }

    public CustomerSearchCondition setName2(String name2) {
        this.name2 = name2;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public CustomerSearchCondition setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public CustomerSearchCondition setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CustomerSearchCondition setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CustomerSearchCondition setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerSearchCondition setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerSearchCondition{" +
                "customerNumber='" + customerNumber + '\'' +
                ", matchcode='" + matchcode + '\'' +
                ", name1='" + name1 + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
