package org.imixs.workflow.myfactory.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.workflow.myfactory.MyFactoryException;
import org.imixs.workflow.myfactory.MyFactorySessionManager;
import org.imixs.workflow.myfactory.MyFactorySessionManager.MyFactorySessionException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

/**
 * Jakarta EE service for managing contacts via SOAP API.
 * Uses the correct API operations with proper condition objects for efficient
 * server-side filtering.
 * 
 * WSDL:
 * http://<SERVER>/myfactory/services/SoapAPI/AddressesSuppliersCustomers.asmx.wsdl
 */
@ApplicationScoped
public class ContactService {

    private static final Logger logger = Logger.getLogger(ContactService.class.getName());

    private static final String NAMESPACE_URI = "http://www.myfactory.com/Services/SoapAPI";

    @Inject
    private MyFactorySessionManager sessionManager;

    // ========================================================================
    // Setters for testing
    // ========================================================================
    public void setSessionManager(MyFactorySessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * This method computes the service endpoint based on the base url form the
     * MyFactorySessionManager endpoint URL
     * 
     * services/services.asmx -> services/SoapAPI/AddressesSuppliersCustomers.asmx
     * 
     * @throws MyFactoryException
     */
    public String getEndpointUrl() throws MyFactoryException {
        String url = sessionManager.getEndpointUrl();
        if (url == null || url.trim().isEmpty()) {
            throw new MyFactoryException("Endpoint URL is not configured");
        }
        url = url.replace("/services.asmx", "/SoapAPI/AddressesSuppliersCustomers.asmx");
        return url;
    }

    // ========================================================================
    // Public API - Contact Search
    // ========================================================================

    /**
     * Searches for contacts using server-side filtering.
     * This is the efficient way - the API filters on the server.
     *
     * @param condition The search criteria
     * @return List of matching contacts
     * @throws MyFactoryException if the operation fails
     */
    public List<Contact> searchContacts(ContactSearchCondition condition) throws MyFactoryException {
        logger.info(() -> "Searching contacts with condition: " + condition);

        SOAPConnection soapConnection = null;
        try {
            soapConnection = createSoapConnection();
            SOAPMessage request = createGetContactsRequest(condition);
            SOAPMessage response = soapConnection.call(request, this.getEndpointUrl());
            return parseGetContactsResponse(response);
        } catch (MyFactorySessionException e) {
            logger.severe("Session error: " + e.getMessage());
            throw new MyFactoryException("Session error - please retry", e);
        } catch (SOAPException e) {
            logger.severe("SOAP error: " + e.getMessage());
            throw new MyFactoryException("Failed to search contacts", e);
        } finally {
            closeSoapConnection(soapConnection);
        }
    }

    /**
     * Searches contacts by last name.
     */
    public List<Contact> searchContactsByLastName(String lastName) throws MyFactoryException {
        return searchContacts(new ContactSearchCondition().setLastName(lastName));
    }

    /**
     * Searches contacts by email.
     */
    public List<Contact> searchContactsByEmail(String email) throws MyFactoryException {
        return searchContacts(new ContactSearchCondition().setEmail(email));
    }

    /**
     * Searches contacts by first and last name.
     */
    public List<Contact> searchContactsByName(String firstName, String lastName) throws MyFactoryException {
        return searchContacts(new ContactSearchCondition()
                .setFirstName(firstName)
                .setLastName(lastName));
    }

    /**
     * Gets all contacts for a specific address.
     */
    public List<Contact> getContactsByAddressId(int addressId) throws MyFactoryException {
        return searchContacts(new ContactSearchCondition().setAddressId(addressId));
    }

    /**
     * Gets a single contact by ID.
     */
    public Contact getContactById(int contactId) throws MyFactoryException {
        List<Contact> contacts = searchContacts(new ContactSearchCondition().setContactId(contactId));
        return contacts.isEmpty() ? null : contacts.get(0);
    }

    // ========================================================================
    // Public API - Address Search
    // ========================================================================

    /**
     * Searches for addresses using server-side filtering.
     *
     * @param condition The search criteria
     * @return List of matching address IDs
     * @throws MyFactoryException if the operation fails
     */
    public List<Integer> searchAddressIds(AddressSearchCondition condition) throws MyFactoryException {
        logger.info(() -> "Searching address IDs with condition: " + condition);

        SOAPConnection soapConnection = null;
        try {
            soapConnection = createSoapConnection();
            SOAPMessage request = createGetAddressIDsRequest(condition);
            SOAPMessage response = soapConnection.call(request, this.getEndpointUrl());
            return parseGetAddressIDsResponse(response);
        } catch (MyFactorySessionException e) {
            logger.severe("Session error: " + e.getMessage());
            throw new MyFactoryException("Session error - please retry", e);
        } catch (SOAPException e) {
            logger.severe("SOAP error: " + e.getMessage());
            throw new MyFactoryException("Failed to search addresses", e);
        } finally {
            closeSoapConnection(soapConnection);
        }
    }

    /**
     * Searches addresses by company name (Name1 field).
     */
    public List<Integer> searchAddressesByName(String name) throws MyFactoryException {
        return searchAddressIds(new AddressSearchCondition().setName1(name));
    }

    /**
     * Searches addresses by matchcode.
     */
    public List<Integer> searchAddressesByMatchcode(String matchcode) throws MyFactoryException {
        return searchAddressIds(new AddressSearchCondition().setMatchcode(matchcode));
    }

    /**
     * Searches addresses by city.
     */
    public List<Integer> searchAddressesByCity(String city) throws MyFactoryException {
        return searchAddressIds(new AddressSearchCondition().setCity(city));
    }

    // ========================================================================
    // SOAP Request Builders
    // ========================================================================

    /**
     * Creates GetContacts request with ContactCondition.
     */
    private SOAPMessage createGetContactsRequest(ContactSearchCondition condition)
            throws SOAPException, MyFactorySessionException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // Set SOAPAction header - REQUIRED!
        soapMessage.getMimeHeaders().addHeader("SOAPAction",
                "http://www.myfactory.com/Services/SoapAPI/GetContacts");

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement getContacts = soapBody.addChildElement("GetContacts", "tns");

        // Add client ID
        addChildElement(getContacts, "sClientID", sessionManager.getClientId());

        // Add ContactCondition
        SOAPElement contactCondition = getContacts.addChildElement("ContactCondition", "tns");

        if (condition.getContactId() != null) {
            addChildElement(contactCondition, "ContactID", String.valueOf(condition.getContactId()));
        }
        if (condition.getAddressId() != null) {
            addChildElement(contactCondition, "AddressID", String.valueOf(condition.getAddressId()));
        }
        if (condition.getAddressNumber() != null) {
            addChildElement(contactCondition, "AddressNumber", condition.getAddressNumber());
        }
        if (condition.getFirstName() != null) {
            addChildElement(contactCondition, "FirstName", condition.getFirstName());
        }
        if (condition.getLastName() != null) {
            addChildElement(contactCondition, "LastName", condition.getLastName());
        }
        if (condition.getEmail() != null) {
            addChildElement(contactCondition, "EMail", condition.getEmail());
        }
        if (condition.getPhoneNr() != null) {
            addChildElement(contactCondition, "PhoneNr", condition.getPhoneNr());
        }
        if (condition.getPosition() != null) {
            addChildElement(contactCondition, "Position", condition.getPosition());
        }

        soapMessage.saveChanges();
        logSoapMessage("GetContacts Request", soapMessage);
        return soapMessage;
    }

    /**
     * Creates GetAddressIDs request with AddressCondition.
     */
    private SOAPMessage createGetAddressIDsRequest(AddressSearchCondition condition)
            throws SOAPException, MyFactorySessionException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement getAddressIDs = soapBody.addChildElement("GetAddressIDs", "tns");

        // Add client ID
        addChildElement(getAddressIDs, "sClientID", sessionManager.getClientId());

        // Add AddressCondition
        SOAPElement addressCondition = getAddressIDs.addChildElement("AddressCondition", "tns");

        if (condition.getAddressId() != null) {
            addChildElement(addressCondition, "AddressID", String.valueOf(condition.getAddressId()));
        }
        if (condition.getAddressNumber() != null) {
            addChildElement(addressCondition, "AddressNumber", condition.getAddressNumber());
        }
        if (condition.getMatchcode() != null) {
            addChildElement(addressCondition, "Matchcode", condition.getMatchcode());
        }
        if (condition.getName1() != null) {
            addChildElement(addressCondition, "Name1", condition.getName1());
        }
        if (condition.getName2() != null) {
            addChildElement(addressCondition, "Name2", condition.getName2());
        }
        if (condition.getStreet() != null) {
            addChildElement(addressCondition, "Street", condition.getStreet());
        }
        if (condition.getPostalCode() != null) {
            addChildElement(addressCondition, "PostalCode", condition.getPostalCode());
        }
        if (condition.getCity() != null) {
            addChildElement(addressCondition, "City", condition.getCity());
        }
        if (condition.getCountry() != null) {
            addChildElement(addressCondition, "Country", condition.getCountry());
        }
        if (condition.getEmail() != null) {
            addChildElement(addressCondition, "EMail", condition.getEmail());
        }

        soapMessage.saveChanges();
        logSoapMessage("GetAddressIDs Request", soapMessage);
        return soapMessage;
    }

    // ========================================================================
    // SOAP Response Parsers
    // ========================================================================

    private List<Contact> parseGetContactsResponse(SOAPMessage soapResponse)
            throws SOAPException, MyFactoryException {

        // DEBUG: Print raw response
        try {
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            soapResponse.writeTo(out);
            System.out.println("=== RAW SOAP RESPONSE ===");
            System.out.println(out.toString("UTF-8").substring(0, Math.min(2000, out.size())));
            System.out.println("=========================");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SOAPBody responseBody = soapResponse.getSOAPBody();
        checkForSoapFault(responseBody);

        List<Contact> contacts = new ArrayList<>();

        // Navigate: GetContactsResult -> Contacts -> Contact
        NodeList contactNodes = responseBody.getElementsByTagNameNS(NAMESPACE_URI, "Contact");
        if (contactNodes.getLength() == 0) {
            contactNodes = responseBody.getElementsByTagName("Contact");
        }

        for (int i = 0; i < contactNodes.getLength(); i++) {
            Node contactNode = contactNodes.item(i);
            Contact contact = parseContactNode(contactNode);
            if (contact != null) {
                contacts.add(contact);
            }
        }

        logger.info(() -> "Found " + contacts.size() + " contacts");
        return contacts;
    }

    private Contact parseContactNode(Node contactNode) {
        Contact contact = new Contact();

        NodeList children = contactNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            String nodeName = child.getLocalName() != null ? child.getLocalName() : child.getNodeName();
            String value = child.getTextContent();

            if (value == null || value.isEmpty()) {
                continue;
            }

            switch (nodeName) {
                case "ContactID":
                    contact.setContactId(parseIntSafe(value, 0));
                    break;
                case "AddressID":
                    contact.setAddressId(parseIntSafe(value, 0));
                    break;
                case "FirstName":
                    contact.setFirstName(value);
                    break;
                case "LastName":
                    contact.setLastName(value);
                    break;
                case "ContactName":
                    // Combined name field
                    if (contact.getLastName() == null) {
                        contact.setLastName(value);
                    }
                    break;
                case "AddrTitle":
                case "ContactTitle":
                    contact.setTitle(value);
                    break;
                case "FormOfAddress":
                    contact.setSalutation(value);
                    break;
                case "Position":
                    contact.setPosition(value);
                    break;
                case "PhoneNr":
                    contact.setPhone(value);
                    break;
                case "MobilePhone":
                    contact.setMobile(value);
                    break;
                case "EMail":
                    contact.setEmail(value);
                    break;
                case "Language":
                    // Could add to Contact if needed
                    break;
                case "InActive":
                    // Could add isActive field
                    break;
            }
        }

        return contact;
    }

    private List<Integer> parseGetAddressIDsResponse(SOAPMessage soapResponse)
            throws SOAPException, MyFactoryException {

        SOAPBody responseBody = soapResponse.getSOAPBody();
        checkForSoapFault(responseBody);

        List<Integer> addressIds = new ArrayList<>();

        // Navigate: GetAddressIDsResult -> AddressIDs -> int
        NodeList idNodes = responseBody.getElementsByTagNameNS(NAMESPACE_URI, "int");
        if (idNodes.getLength() == 0) {
            idNodes = responseBody.getElementsByTagName("int");
        }

        for (int i = 0; i < idNodes.getLength(); i++) {
            int id = parseIntSafe(idNodes.item(i).getTextContent(), -1);
            if (id > 0) {
                addressIds.add(id);
            }
        }

        logger.info(() -> "Found " + addressIds.size() + " address IDs");
        return addressIds;
    }

    // ========================================================================
    // Helper Methods
    // ========================================================================

    private SOAPConnection createSoapConnection() throws SOAPException {
        return SOAPConnectionFactory.newInstance().createConnection();
    }

    private void closeSoapConnection(SOAPConnection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SOAPException e) {
                logger.warning("Failed to close SOAP connection: " + e.getMessage());
            }
        }
    }

    private void addChildElement(SOAPElement parent, String name, String value) throws SOAPException {
        SOAPElement element = parent.addChildElement(name, "tns");
        element.addTextNode(value);
    }

    private void checkForSoapFault(SOAPBody responseBody) throws SOAPException, MyFactoryException {
        if (responseBody.hasFault()) {
            SOAPFault fault = responseBody.getFault();
            throw new MyFactoryException("SOAP Fault: " + fault.getFaultString());
        }
    }

    private int parseIntSafe(String value, int defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private void logSoapMessage(String label, SOAPMessage message) {
        try {
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            message.writeTo(out);
            // logger.fine(() -> label + ":\n" + out.toString("UTF-8"));
        } catch (Exception e) {
            logger.fine(() -> label + ": [could not log message]");
        }
    }

    // ========================================================================
    // Search Condition Classes
    // ========================================================================

    /**
     * Search condition for contacts (maps to ContactCondition in WSDL).
     */
    public static class ContactSearchCondition {
        private Integer contactId;
        private Integer addressId;
        private String addressNumber;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNr;
        private String position;

        public Integer getContactId() {
            return contactId;
        }

        public ContactSearchCondition setContactId(Integer contactId) {
            this.contactId = contactId;
            return this;
        }

        public Integer getAddressId() {
            return addressId;
        }

        public ContactSearchCondition setAddressId(Integer addressId) {
            this.addressId = addressId;
            return this;
        }

        public String getAddressNumber() {
            return addressNumber;
        }

        public ContactSearchCondition setAddressNumber(String addressNumber) {
            this.addressNumber = addressNumber;
            return this;
        }

        public String getFirstName() {
            return firstName;
        }

        public ContactSearchCondition setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public ContactSearchCondition setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public ContactSearchCondition setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getPhoneNr() {
            return phoneNr;
        }

        public ContactSearchCondition setPhoneNr(String phoneNr) {
            this.phoneNr = phoneNr;
            return this;
        }

        public String getPosition() {
            return position;
        }

        public ContactSearchCondition setPosition(String position) {
            this.position = position;
            return this;
        }

        @Override
        public String toString() {
            return "ContactSearchCondition{" +
                    "contactId=" + contactId +
                    ", addressId=" + addressId +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    /**
     * Search condition for addresses (maps to AddressCondition in WSDL).
     */
    public static class AddressSearchCondition {
        private Integer addressId;
        private String addressNumber;
        private String matchcode;
        private String name1;
        private String name2;
        private String street;
        private String postalCode;
        private String city;
        private String country;
        private String email;

        public Integer getAddressId() {
            return addressId;
        }

        public AddressSearchCondition setAddressId(Integer addressId) {
            this.addressId = addressId;
            return this;
        }

        public String getAddressNumber() {
            return addressNumber;
        }

        public AddressSearchCondition setAddressNumber(String addressNumber) {
            this.addressNumber = addressNumber;
            return this;
        }

        public String getMatchcode() {
            return matchcode;
        }

        public AddressSearchCondition setMatchcode(String matchcode) {
            this.matchcode = matchcode;
            return this;
        }

        public String getName1() {
            return name1;
        }

        public AddressSearchCondition setName1(String name1) {
            this.name1 = name1;
            return this;
        }

        public String getName2() {
            return name2;
        }

        public AddressSearchCondition setName2(String name2) {
            this.name2 = name2;
            return this;
        }

        public String getStreet() {
            return street;
        }

        public AddressSearchCondition setStreet(String street) {
            this.street = street;
            return this;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public AddressSearchCondition setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public String getCity() {
            return city;
        }

        public AddressSearchCondition setCity(String city) {
            this.city = city;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public AddressSearchCondition setCountry(String country) {
            this.country = country;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public AddressSearchCondition setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public String toString() {
            return "AddressSearchCondition{" +
                    "addressId=" + addressId +
                    ", matchcode='" + matchcode + '\'' +
                    ", name1='" + name1 + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}