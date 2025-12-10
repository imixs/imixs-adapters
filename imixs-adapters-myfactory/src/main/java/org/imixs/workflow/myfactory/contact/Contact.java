package org.imixs.workflow.myfactory.contact;

/**
 * Represents a contact from the MyFactory ERP system.
 * Maps to the Contact element in the SOAP response.
 */
public class Contact {

    private int contactId;
    private int addressId;
    private String salutation;
    private String title;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private String phone;
    private String mobile;
    private String fax;
    private String email;
    private boolean mainContact;

    // Default constructor
    public Contact() {
    }

    // Getters and Setters
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMainContact() {
        return mainContact;
    }

    public void setMainContact(boolean mainContact) {
        this.mainContact = mainContact;
    }

    /**
     * Returns the full name of the contact.
     */
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (title != null && !title.isEmpty()) {
            sb.append(title).append(" ");
        }
        if (firstName != null && !firstName.isEmpty()) {
            sb.append(firstName).append(" ");
        }
        if (lastName != null && !lastName.isEmpty()) {
            sb.append(lastName);
        }
        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", addressId=" + addressId +
                ", name='" + getFullName() + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", mainContact=" + mainContact +
                '}';
    }
}