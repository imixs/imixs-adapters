package org.imixs.workflow.myfactory.salesorder;

import java.util.logging.Logger;

import org.imixs.workflow.myfactory.MyFactoryException;
import org.imixs.workflow.myfactory.MyFactorySessionManager;
import org.imixs.workflow.myfactory.MyFactorySessionManager.MyFactorySessionException;

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
 * Jakarta EE service for managing sales orders via SOAP API.
 * This service handles order approval by updating the ManualOrderStateID.
 * 
 * IMPORTANT: Uses MyFactorySessionManager for automatic login/logout handling.
 */
@ApplicationScoped
public class SalesOrderService {

    private static final Logger LOGGER = Logger.getLogger(SalesOrderService.class.getName());

    private static final String NAMESPACE_URI = "http://www.myfactory.com/Services/SoapAPI";
    private static final int STATE_APPROVED = 1; // "Order confirmed"
    private static final int STATE_TO_REVIEW = 2; // "Order review"

    @Inject
    private MyFactorySessionManager sessionManager;

    /**
     * Approves a sales order by changing status from "to review" to "approved"
     * 
     * @param orderId The order ID to approve
     * @return SalesOrderResponse object containing the result
     * @throws SalesOrderException if the operation fails
     */
    public SalesOrderResponse approveSalesOrder(int orderId) throws SalesOrderException {
        LOGGER.info(() -> "Approving sales order: " + orderId);
        return updateOrderStatus(orderId, STATE_APPROVED);
    }

    /**
     * This method computes the service endpoint based on the base url form the
     * MyFactorySessionManager endpoint URL
     * 
     * services/services.asmx -> services/SoapAPI/SalesOrders.asmx
     * 
     * @throws MyFactoryException
     */
    public String getEndpointUrl() throws MyFactoryException {
        String url = sessionManager.getEndpointUrl();
        if (url == null || url.trim().isEmpty()) {
            throw new MyFactoryException("Endpoint URL is not configured");
        }
        url = url.replace("/services.asmx", "/SoapAPI/SalesOrders.asmx");
        return url;
    }

    /**
     * Approves a sales order by OrderNumber (e.g. "AB123456")
     * This method first retrieves the OrderID from the OrderNumber, then approves
     * it.
     * 
     * @param orderNumber The order number (e.g. "AB123456")
     * @return SalesOrderResponse object containing the result
     * @throws SalesOrderException if the operation fails or order is not found
     */
    public SalesOrderResponse approveSalesOrderByNumber(String orderNumber) throws SalesOrderException {
        LOGGER.info(() -> "Approving sales order by number: " + orderNumber);

        // First, get the OrderID from the OrderNumber
        int orderId = getOrderIdByNumber(orderNumber);

        // Then approve it
        return approveSalesOrder(orderId);
    }

    /**
     * Sets a sales order to "review" status
     * 
     * @param orderId The order ID to set to review
     * @return SalesOrderResponse object containing the result
     * @throws SalesOrderException if the operation fails
     */
    public SalesOrderResponse setOrderToReview(int orderId) throws SalesOrderException {
        LOGGER.info(() -> "Setting sales order to review: " + orderId);
        return updateOrderStatus(orderId, STATE_TO_REVIEW);
    }

    /**
     * Sets a sales order to "review" status by OrderNumber (e.g. "AB123456")
     * This method first retrieves the OrderID from the OrderNumber, then updates
     * it.
     * 
     * @param orderNumber The order number (e.g. "AB123456")
     * @return SalesOrderResponse object containing the result
     * @throws SalesOrderException if the operation fails or order is not found
     */
    public SalesOrderResponse setOrderToReviewByNumber(String orderNumber) throws SalesOrderException {
        LOGGER.info(() -> "Setting sales order to review by number: " + orderNumber);

        // First, get the OrderID from the OrderNumber
        int orderId = getOrderIdByNumber(orderNumber);

        // Then set to review
        return setOrderToReview(orderId);
    }

    /**
     * Updates the manual order state by OrderNumber
     * 
     * @param orderNumber        The order number (e.g. "AB123456")
     * @param manualOrderStateId The new state (1=approved, 2=to review)
     * @return Parsed response
     * @throws SalesOrderException if the operation fails or order is not found
     */
    public SalesOrderResponse updateOrderStatusByNumber(String orderNumber, int manualOrderStateId)
            throws SalesOrderException {

        LOGGER.info(() -> "Updating order status by number: " + orderNumber + " to state: " + manualOrderStateId);

        // First, get the OrderID from the OrderNumber
        int orderId = getOrderIdByNumber(orderNumber);

        // Then update the status
        return updateOrderStatus(orderId, manualOrderStateId);
    }

    /**
     * Updates the manual order state
     * 
     * @param orderId            The order ID
     * @param manualOrderStateId The new state (1=approved, 2=to review)
     * @return Parsed response
     * @throws SalesOrderException if the operation fails
     */
    public SalesOrderResponse updateOrderStatus(int orderId, int manualOrderStateId)
            throws SalesOrderException {

        SOAPConnection soapConnection = null;

        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();

            // Create and send SOAP request
            SOAPMessage soapMessage = createUpdateSalesOrderRequest(orderId, manualOrderStateId);
            SOAPMessage soapResponse = soapConnection.call(soapMessage, this.getEndpointUrl());

            // Parse and return response
            return parseResponse(soapResponse);

        } catch (MyFactorySessionException e) {
            LOGGER.severe("Session error while updating order " + orderId + ": " + e.getMessage());
            throw new SalesOrderException("Session error - please retry", e);
        } catch (SOAPException e) {
            LOGGER.severe("SOAP error while updating order " + orderId + ": " + e.getMessage());
            throw new SalesOrderException("Failed to update order status", e);
        } catch (Exception e) {
            LOGGER.severe("Unexpected error while updating order " + orderId + ": " + e.getMessage());
            throw new SalesOrderException("Unexpected error during order update", e);
        } finally {
            // Always close connection
            if (soapConnection != null) {
                try {
                    soapConnection.close();
                } catch (SOAPException e) {
                    LOGGER.warning("Failed to close SOAP connection: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Retrieves the OrderID for a given OrderNumber
     * 
     * @param orderNumber The order number (e.g. "AB123456")
     * @return The OrderID
     * @throws SalesOrderException if order is not found or error occurs
     */
    private int getOrderIdByNumber(String orderNumber) throws SalesOrderException {
        SOAPConnection soapConnection = null;

        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();

            // Create and send SOAP request
            SOAPMessage soapMessage = createGetSalesOrdersByConditionRequest(orderNumber);
            SOAPMessage soapResponse = soapConnection.call(soapMessage, this.getEndpointUrl());

            // Parse response to extract OrderID
            return extractOrderIdFromResponse(soapResponse, orderNumber);

        } catch (MyFactorySessionException e) {
            LOGGER.severe("Session error while retrieving order by number " + orderNumber + ": " + e.getMessage());
            throw new SalesOrderException("Session error - please retry", e);
        } catch (SOAPException e) {
            LOGGER.severe("SOAP error while retrieving order by number " + orderNumber + ": " + e.getMessage());
            throw new SalesOrderException("Failed to retrieve order by number", e);
        } catch (Exception e) {
            LOGGER.severe("Unexpected error while retrieving order by number " + orderNumber + ": " + e.getMessage());
            throw new SalesOrderException("Unexpected error during order retrieval", e);
        } finally {
            // Always close connection
            if (soapConnection != null) {
                try {
                    soapConnection.close();
                } catch (SOAPException e) {
                    LOGGER.warning("Failed to close SOAP connection: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Creates the SOAP request message
     */
    private SOAPMessage createUpdateSalesOrderRequest(int orderId, int manualOrderStateId)
            throws SOAPException, MyFactorySessionException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Add namespace declarations
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        // Build SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement updateSalesOrder = soapBody.addChildElement("UpdateSalesOrder", "tns");

        // Add client ID from session
        SOAPElement clientIdElement = updateSalesOrder.addChildElement("sClientID", "tns");
        clientIdElement.addTextNode(sessionManager.getClientId());

        // Add Order with OrderID and ManualOrderStateID
        SOAPElement orderElement = updateSalesOrder.addChildElement("Order", "tns");

        SOAPElement orderIdElement = orderElement.addChildElement("OrderID", "tns");
        orderIdElement.addTextNode(String.valueOf(orderId));

        SOAPElement stateIdElement = orderElement.addChildElement("ManualOrderStateID", "tns");
        stateIdElement.addTextNode(String.valueOf(manualOrderStateId));

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     * Creates SOAP request for GetSalesOrdersByCondition to retrieve OrderID by
     * OrderNumber
     */
    private SOAPMessage createGetSalesOrdersByConditionRequest(String orderNumber)
            throws SOAPException, MyFactorySessionException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Add namespace declarations
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        // Build SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement getSalesOrdersByCondition = soapBody.addChildElement("GetSalesOrdersByCondition", "tns");

        // Add client ID from session
        SOAPElement clientIdElement = getSalesOrdersByCondition.addChildElement("sClientID", "tns");
        clientIdElement.addTextNode(sessionManager.getClientId());

        // Add SalesOrderCondition with OrderNumber
        SOAPElement conditionElement = getSalesOrdersByCondition.addChildElement("SalesOrderCondition", "tns");
        SOAPElement orderNumberElement = conditionElement.addChildElement("OrderNumber", "tns");
        orderNumberElement.addTextNode(orderNumber);

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     * Parses the SOAP response
     */
    private SalesOrderResponse parseResponse(SOAPMessage soapResponse) throws SOAPException {
        SOAPBody responseBody = soapResponse.getSOAPBody();

        // Check for SOAP fault
        if (responseBody.hasFault()) {
            SOAPFault fault = responseBody.getFault();
            String faultString = fault.getFaultString();
            String faultCode = fault.getFaultCode();

            LOGGER.severe("SOAP Fault - Code: " + faultCode + ", Message: " + faultString);
            throw new SOAPException("SOAP Fault: " + faultString);
        }

        // Parse successful response
        SalesOrderResponse response = new SalesOrderResponse();
        response.setSuccess(true);

        // Extract response details if needed
        // You can extend this to parse ResponseCode, ErrCode, ErrMsg etc.

        return response;
    }

    /**
     * Extracts OrderID from GetSalesOrdersByCondition response
     */
    private int extractOrderIdFromResponse(SOAPMessage soapResponse, String orderNumber)
            throws SOAPException, SalesOrderException {

        SOAPBody responseBody = soapResponse.getSOAPBody();

        // Check for SOAP fault
        if (responseBody.hasFault()) {
            SOAPFault fault = responseBody.getFault();
            String faultString = fault.getFaultString();
            LOGGER.severe("SOAP Fault while searching for order: " + faultString);
            throw new SalesOrderException("Failed to find order with number: " + orderNumber);
        }

        // Navigate through the response to find OrderID
        // Response structure: GetSalesOrdersByConditionResponse ->
        // GetSalesOrdersByConditionResult -> Orders -> Order -> OrderID
        try {
            // Get all elements with name "OrderID"
            org.w3c.dom.NodeList orderIdNodes = responseBody.getElementsByTagNameNS(NAMESPACE_URI, "OrderID");

            if (orderIdNodes.getLength() == 0) {
                LOGGER.severe("Order not found with number: " + orderNumber);
                throw new SalesOrderException("Order not found with number: " + orderNumber);
            }

            // Get the first OrderID (should be only one for exact OrderNumber match)
            org.w3c.dom.Node orderIdNode = orderIdNodes.item(0);
            String orderIdText = orderIdNode.getTextContent();

            int orderId = Integer.parseInt(orderIdText);
            LOGGER.info(() -> "Found OrderID " + orderId + " for OrderNumber: " + orderNumber);

            return orderId;

        } catch (NumberFormatException e) {
            LOGGER.severe("Invalid OrderID format in response for order: " + orderNumber);
            throw new SalesOrderException("Invalid OrderID in response", e);
        }
    }

    /**
     * Response object for sales order operations
     */
    public static class SalesOrderResponse {
        private boolean success;
        private String responseCode;
        private String errorCode;
        private String errorMessage;

        // Getters and setters
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public String toString() {
            return "SalesOrderResponse{" +
                    "success=" + success +
                    ", responseCode='" + responseCode + '\'' +
                    ", errorCode='" + errorCode + '\'' +
                    ", errorMessage='" + errorMessage + '\'' +
                    '}';
        }
    }

    /**
     * Custom exception for sales order operations
     */
    public static class SalesOrderException extends Exception {
        public SalesOrderException(String message) {
            super(message);
        }

        public SalesOrderException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}