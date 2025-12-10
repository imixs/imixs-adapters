package org.imixs.workflow.myfactory.contact;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

/**
 * SOAP client for retrieving contacts from myfactory system.
 * Simple standalone client without CDI dependencies.
 */
public class ContactClient {

    private static final String NAMESPACE_URI = "http://www.myfactory.com/Services/SoapAPI";

    private final String endpointUrl;
    private final String clientId;

    /**
     * Constructor with custom endpoint.
     * 
     * @param endpointUrl The SOAP endpoint URL
     * @param clientId    Your client ID for authentication
     */
    public ContactClient(String endpointUrl, String clientId) {
        this.endpointUrl = endpointUrl;
        this.clientId = clientId;
    }

    /**
     * Retrieves all contacts for a specific address ID.
     * 
     * @param addressId The address ID to get contacts for
     * @return List of contacts
     * @throws Exception if the SOAP call fails
     */
    public List<Contact> getContacts(int addressId) throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        try {
            // Create and send SOAP request
            SOAPMessage soapMessage = createGetContactsRequest(addressId);
            SOAPMessage soapResponse = soapConnection.call(soapMessage, endpointUrl);

            // Parse response
            return parseGetContactsResponse(soapResponse);
        } finally {
            soapConnection.close();
        }
    }

    /**
     * Retrieves a single contact by ID.
     * 
     * @param addressId The address ID where the contact belongs
     * @param contactId The contact ID to retrieve
     * @return The contact or null if not found
     * @throws Exception if the SOAP call fails
     */
    public Contact getContactById(int addressId, int contactId) throws Exception {
        List<Contact> contacts = getContacts(addressId);

        return contacts.stream()
                .filter(c -> c.getContactId() == contactId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves address IDs matching a search term.
     * 
     * @param searchTerm The search term
     * @return List of matching address IDs
     * @throws Exception if the SOAP call fails
     */
    public List<Integer> getAddressIds(String searchTerm) throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        try {
            // Create and send SOAP request
            SOAPMessage soapMessage = createGetAddressIDsRequest(searchTerm);
            SOAPMessage soapResponse = soapConnection.call(soapMessage, endpointUrl);

            // Parse response
            return parseGetAddressIDsResponse(soapResponse);
        } finally {
            soapConnection.close();
        }
    }

    /**
     * Searches for contacts by address/company name.
     * 
     * @param searchTerm The search term
     * @return List of contacts from matching addresses
     * @throws Exception if the SOAP call fails
     */
    public List<Contact> searchContacts(String searchTerm) throws Exception {
        List<Integer> addressIds = getAddressIds(searchTerm);
        List<Contact> allContacts = new ArrayList<>();

        for (Integer addressId : addressIds) {
            try {
                allContacts.addAll(getContacts(addressId));
            } catch (Exception e) {
                System.err.println("Failed to get contacts for address " + addressId + ": " + e.getMessage());
            }
        }

        return allContacts;
    }

    /**
     * Creates the SOAP request for GetContacts operation.
     */
    private SOAPMessage createGetContactsRequest(int addressId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Add namespace declarations
        envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        // Build SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement getContacts = soapBody.addChildElement("GetContacts", "tns");

        // Add client ID
        SOAPElement clientIdElement = getContacts.addChildElement("sClientID", "tns");
        clientIdElement.addTextNode(this.clientId);

        // Add AddressID parameter
        SOAPElement addressIdElement = getContacts.addChildElement("AddressID", "tns");
        addressIdElement.addTextNode(String.valueOf(addressId));

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     * Creates the SOAP request for GetAddressIDs operation.
     */
    private SOAPMessage createGetAddressIDsRequest(String searchTerm) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Add namespace declarations
        envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        // Build SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement getAddressIDs = soapBody.addChildElement("GetAddressIDs", "tns");

        // Add client ID
        SOAPElement clientIdElement = getAddressIDs.addChildElement("sClientID", "tns");
        clientIdElement.addTextNode(this.clientId);

        // Add search string parameter
        SOAPElement searchElement = getAddressIDs.addChildElement("SearchString", "tns");
        searchElement.addTextNode(searchTerm != null ? searchTerm : "");

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     * Parses the GetContacts SOAP response.
     */
    private List<Contact> parseGetContactsResponse(SOAPMessage soapResponse) throws Exception {
        SOAPBody responseBody = soapResponse.getSOAPBody();

        // Check for SOAP fault
        if (responseBody.hasFault()) {
            SOAPFault fault = responseBody.getFault();
            throw new Exception("SOAP Fault: " + fault.getFaultString());
        }

        List<Contact> contacts = new ArrayList<>();

        // Find Contact elements
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

        return contacts;
    }

    /**
     * Parses a single Contact XML node into a Contact object.
     */
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
                case "ID":
                    contact.setContactId(parseIntSafe(value));
                    break;
                case "AddressID":
                    contact.setAddressId(parseIntSafe(value));
                    break;
                case "Salutation":
                    contact.setSalutation(value);
                    break;
                case "Title":
                    contact.setTitle(value);
                    break;
                case "FirstName":
                case "Vorname":
                    contact.setFirstName(value);
                    break;
                case "LastName":
                case "Nachname":
                case "Name":
                    contact.setLastName(value);
                    break;
                case "Position":
                case "Funktion":
                    contact.setPosition(value);
                    break;
                case "Department":
                case "Abteilung":
                    contact.setDepartment(value);
                    break;
                case "Phone":
                case "Telefon":
                    contact.setPhone(value);
                    break;
                case "Mobile":
                case "Mobil":
                    contact.setMobile(value);
                    break;
                case "Fax":
                    contact.setFax(value);
                    break;
                case "Email":
                case "EMail":
                    contact.setEmail(value);
                    break;
                case "IsMainContact":
                case "Hauptansprechpartner":
                    contact.setMainContact("true".equalsIgnoreCase(value) || "1".equals(value));
                    break;
            }
        }

        return contact;
    }

    /**
     * Parses the GetAddressIDs SOAP response.
     */
    private List<Integer> parseGetAddressIDsResponse(SOAPMessage soapResponse) throws Exception {
        SOAPBody responseBody = soapResponse.getSOAPBody();

        // Check for SOAP fault
        if (responseBody.hasFault()) {
            SOAPFault fault = responseBody.getFault();
            throw new Exception("SOAP Fault: " + fault.getFaultString());
        }

        List<Integer> addressIds = new ArrayList<>();

        // Try different possible element names
        NodeList idNodes = responseBody.getElementsByTagNameNS(NAMESPACE_URI, "int");
        if (idNodes.getLength() == 0) {
            idNodes = responseBody.getElementsByTagName("int");
        }
        if (idNodes.getLength() == 0) {
            idNodes = responseBody.getElementsByTagNameNS(NAMESPACE_URI, "AddressID");
        }
        if (idNodes.getLength() == 0) {
            idNodes = responseBody.getElementsByTagName("AddressID");
        }

        for (int i = 0; i < idNodes.getLength(); i++) {
            int id = parseIntSafe(idNodes.item(i).getTextContent());
            if (id > 0) {
                addressIds.add(id);
            }
        }

        return addressIds;
    }

    /**
     * Safely parses an integer string.
     */
    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Prints the SOAP response for debugging.
     */
    public static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        System.out.println("SOAP Response:");
        soapResponse.writeTo(System.out);
        System.out.println();
    }

}