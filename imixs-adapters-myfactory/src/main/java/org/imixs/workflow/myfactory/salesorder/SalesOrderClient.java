package org.imixs.workflow.myfactory.salesorder;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

/**
 * SOAP client for updating sales order status in myfactory system.
 * This client updates the ManualOrderStateID field to approve orders.
 */
public class SalesOrderClient {

    private static final String NAMESPACE_URI = "http://www.myfactory.com/Services/SoapAPI";

    private final String clientId;
    private String serviceURI;

    /**
     * Constructor
     * 
     * The service URI need to be set to the SalesOrders.asmx endpoint. E.g.:
     * 
     * "http://localhost/myfactory/services/SoapAPI/SalesOrders.asmx"
     * 
     * @param clientId Your client ID for authentication
     */
    public SalesOrderClient(String clientId, String serviceURI) {
        this.clientId = clientId;
        this.serviceURI = serviceURI;
    }

    /**
     * Approves a sales order by changing its status from "Beleg überprüfen" (ID 2)
     * to "Beleg genehmigt" (ID 1)
     * 
     * @param orderId The ID of the order to approve
     * @return Response from the SOAP service
     * @throws Exception if the SOAP call fails
     */
    public SOAPMessage approveSalesOrder(int orderId) throws Exception {
        return updateOrderStatus(orderId, 1); // 1 = "Beleg genehmigt"
    }

    /**
     * Updates the manual order state of a sales order
     * 
     * @param orderId            The ID of the order to update
     * @param manualOrderStateId The new state ID (1 = approved, 2 = to review)
     * @return Response from the SOAP service
     * @throws Exception if the SOAP call fails
     */
    public SOAPMessage updateOrderStatus(int orderId, int manualOrderStateId) throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Create SOAP Message
        SOAPMessage soapMessage = createUpdateSalesOrderRequest(orderId, manualOrderStateId);

        // Send SOAP Message to SOAP Server
        SOAPMessage soapResponse = soapConnection.call(soapMessage, serviceURI);

        // Close the connection
        soapConnection.close();

        return soapResponse;
    }

    /**
     * Creates the SOAP request message for UpdateSalesOrder
     * 
     * @param orderId            The order ID to update
     * @param manualOrderStateId The new manual order state ID
     * @return The SOAP message ready to be sent
     * @throws Exception if message creation fails
     */
    private SOAPMessage createUpdateSalesOrderRequest(int orderId, int manualOrderStateId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Set namespace prefix
        envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("tns", NAMESPACE_URI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        // Create UpdateSalesOrder element
        SOAPElement updateSalesOrder = soapBody.addChildElement("UpdateSalesOrder", "tns");

        // Add sClientID element
        SOAPElement clientIdElement = updateSalesOrder.addChildElement("sClientID", "tns");
        clientIdElement.addTextNode(this.clientId);

        // Add Order element
        SOAPElement orderElement = updateSalesOrder.addChildElement("Order", "tns");

        // Add OrderID - this is required
        SOAPElement orderIdElement = orderElement.addChildElement("OrderID", "tns");
        orderIdElement.addTextNode(String.valueOf(orderId));

        // Add ManualOrderStateID - this is what we're updating
        SOAPElement manualOrderStateIdElement = orderElement.addChildElement("ManualOrderStateID", "tns");
        manualOrderStateIdElement.addTextNode(String.valueOf(manualOrderStateId));

        // Save the message
        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     * Prints the SOAP response for debugging
     */
    public static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        System.out.println("SOAP Response:");
        soapResponse.writeTo(System.out);
        System.out.println();
    }

}