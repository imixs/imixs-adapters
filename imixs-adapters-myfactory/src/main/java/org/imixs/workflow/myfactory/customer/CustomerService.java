package org.imixs.workflow.myfactory.customer;

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
 * Jakarta EE service for searching customers via MyFactory SOAP API.
 * 
 * Supports searching by:
 * - Customer number (Kundennummer)
 * - Company name (Firmenname)
 * - Matchcode
 * - City, PostalCode, etc.
 */
@ApplicationScoped
public class CustomerService {

    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

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
    // Public API - Customer Search
    // ========================================================================

    /**
     * Searches for customers using server-side filtering.
     *
     * @param condition The search criteria
     * @return List of matching customers
     * @throws CustomerException  if the operation fails
     * @throws MyFactoryException
     */
    public List<Customer> searchCustomers(CustomerSearchCondition condition)
            throws MyFactoryException {
        logger.info(() -> "Searching customers with condition: " + condition);

        SOAPConnection soapConnection = null;
        try {
            soapConnection = createSoapConnection();
            SOAPMessage request = createGetCustomersByConditionRequest(condition);
            SOAPMessage response = soapConnection.call(request, this.getEndpointUrl());
            return parseGetCustomersResponse(response);
        } catch (MyFactorySessionException e) {
            logger.severe("Session error: " + e.getMessage());
            throw new MyFactoryException("Session error - please retry", e);
        } catch (SOAPException e) {
            logger.severe("SOAP error: " + e.getMessage());
            throw new MyFactoryException("Failed to search customers", e);
        } finally {
            closeSoapConnection(soapConnection);
        }
    }

    /**
     * Searches customers by customer number (exact match).
     */
    public List<Customer> searchByCustomerNumber(String customerNumber) throws MyFactoryException {
        return searchCustomers(new CustomerSearchCondition().setCustomerNumber(customerNumber));
    }

    /**
     * Searches customers by company name (Name1 field).
     */
    public List<Customer> searchByCompanyName(String companyName) throws MyFactoryException {
        return searchCustomers(new CustomerSearchCondition().setName1(companyName));
    }

    /**
     * Searches customers by matchcode.
     */
    public List<Customer> searchByMatchcode(String matchcode) throws MyFactoryException {
        return searchCustomers(new CustomerSearchCondition().setMatchcode(matchcode));
    }

    /**
     * Searches customers by city.
     */
    public List<Customer> searchByCity(String city) throws MyFactoryException {
        return searchCustomers(new CustomerSearchCondition().setCity(city));
    }

    /**
     * Gets a single customer by customer ID.
     */
    public Customer getCustomerById(int customerId) throws MyFactoryException {
        logger.info(() -> "Getting customer by ID: " + customerId);

        SOAPConnection soapConnection = null;
        try {
            soapConnection = createSoapConnection();
            SOAPMessage request = createGetCustomerRequest(customerId);
            SOAPMessage response = soapConnection.call(request, this.getEndpointUrl());
            List<Customer> customers = parseGetCustomersResponse(response);
            return customers.isEmpty() ? null : customers.get(0);
        } catch (MyFactorySessionException e) {
            logger.severe("Session error: " + e.getMessage());
            throw new MyFactoryException("Session error - please retry", e);
        } catch (SOAPException e) {
            logger.severe("SOAP error: " + e.getMessage());
            throw new MyFactoryException("Failed to get customer", e);
        } finally {
            closeSoapConnection(soapConnection);
        }
    }

    // ========================================================================
    // SOAP Request Builders
    // ========================================================================

    /**
     * Creates GetCustomersByCondition request.
     */
    private SOAPMessage createGetCustomersByConditionRequest(CustomerSearchCondition condition)
            throws SOAPException, MyFactorySessionException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // Set SOAPAction header
        soapMessage.getMimeHeaders().addHeader("SOAPAction",
                "http://www.myfactory.com/Services/SoapAPI/GetCustomersByCondition");

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement operation = soapBody.addChildElement("GetCustomersByCondition", "tns");

        // Add client ID
        addChildElement(operation, "sClientID", sessionManager.getClientId());

        // Add CustomerCondition
        SOAPElement customerCondition = operation.addChildElement("CustomerCondition", "tns");

        // Customer-level filters
        if (condition.getCustomerNumber() != null) {
            addChildElement(customerCondition, "CustomerNumber", condition.getCustomerNumber());
        }
        if (condition.getCustomerGroup() != null) {
            addChildElement(customerCondition, "CustomerGroup", condition.getCustomerGroup());
        }

        // Address-level filters (nested AddressCondition)
        if (hasAddressCondition(condition)) {
            SOAPElement addressCondition = customerCondition.addChildElement("AddressCondition", "tns");

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
        }

        soapMessage.saveChanges();
        logSoapMessage("GetCustomersByCondition Request", soapMessage);
        return soapMessage;
    }

    /**
     * Creates GetCustomer request for single customer by ID.
     */
    private SOAPMessage createGetCustomerRequest(int customerId)
            throws SOAPException, MyFactorySessionException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        soapMessage.getMimeHeaders().addHeader("SOAPAction",
                "http://www.myfactory.com/Services/SoapAPI/GetCustomer");

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement operation = soapBody.addChildElement("GetCustomer", "tns");

        addChildElement(operation, "sClientID", sessionManager.getClientId());
        addChildElement(operation, "CustomerID", String.valueOf(customerId));

        soapMessage.saveChanges();
        logSoapMessage("GetCustomer Request", soapMessage);
        return soapMessage;
    }

    /**
     * Checks if any address-level condition is set.
     */
    private boolean hasAddressCondition(CustomerSearchCondition condition) {
        return condition.getAddressNumber() != null
                || condition.getMatchcode() != null
                || condition.getName1() != null
                || condition.getName2() != null
                || condition.getStreet() != null
                || condition.getPostalCode() != null
                || condition.getCity() != null
                || condition.getCountry() != null
                || condition.getEmail() != null;
    }

    // ========================================================================
    // SOAP Response Parser
    // ========================================================================

    private List<Customer> parseGetCustomersResponse(SOAPMessage soapResponse)
            throws SOAPException, MyFactoryException {

        SOAPBody responseBody = soapResponse.getSOAPBody();
        checkForSoapFault(responseBody);

        List<Customer> customers = new ArrayList<>();

        // Find Customer elements
        NodeList customerNodes = responseBody.getElementsByTagNameNS(NAMESPACE_URI, "Customer");
        if (customerNodes.getLength() == 0) {
            customerNodes = responseBody.getElementsByTagName("Customer");
        }

        for (int i = 0; i < customerNodes.getLength(); i++) {
            Node customerNode = customerNodes.item(i);
            Customer customer = parseCustomerNode(customerNode);
            if (customer != null && customer.getCustomerId() > 0) {
                customers.add(customer);
            }
        }

        logger.info(() -> "Found " + customers.size() + " customers");
        return customers;
    }

    private Customer parseCustomerNode(Node customerNode) {
        Customer customer = new Customer();

        NodeList children = customerNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            String nodeName = child.getLocalName() != null ? child.getLocalName() : child.getNodeName();
            String value = child.getTextContent();

            if (value == null || value.trim().isEmpty()) {
                continue;
            }

            value = value.trim();

            switch (nodeName) {
                // Customer-specific fields
                case "CustomerID":
                    customer.setCustomerId(parseIntSafe(value, 0));
                    break;
                case "CustomerNumber":
                    customer.setCustomerNumber(value);
                    break;
                case "CustomerGroup":
                    customer.setCustomerGroup(value);
                    break;

                // Address fields (inherited)
                case "AddressID":
                    customer.setAddressId(parseIntSafe(value, 0));
                    break;
                case "AddressNumber":
                    customer.setAddressNumber(value);
                    break;
                case "Matchcode":
                    customer.setMatchcode(value);
                    break;
                case "Name1":
                    customer.setName1(value);
                    break;
                case "Name2":
                    customer.setName2(value);
                    break;
                case "Street":
                    customer.setStreet(value);
                    break;
                case "PostalCode":
                    customer.setPostalCode(value);
                    break;
                case "City":
                    customer.setCity(value);
                    break;
                case "Country":
                    customer.setCountry(value);
                    break;
                case "PhoneNr":
                    customer.setPhoneNr(value);
                    break;
                case "FaxNr":
                    customer.setFaxNr(value);
                    break;
                case "EMail":
                    customer.setEmail(value);
                    break;
                case "Homepage":
                    customer.setHomepage(value);
                    break;
            }
        }

        return customer;
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

}
