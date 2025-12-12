package org.imixs.workflow.myfactory;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
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
 * Manages MyFactory SOAP API sessions with automatic login/logout.
 * 
 * IMPORTANT: MyFactory allows only ONE session per user.
 * Always logout after completing operations to avoid session conflicts.
 */
@ApplicationScoped
public class MyFactorySessionManager {

    private static final Logger logger = Logger.getLogger(MyFactorySessionManager.class.getName());

    // Correct namespace from WSDL
    private static final String SOAP_NAMESPACE = "http://www.myfactory.de/";

    @Inject
    @ConfigProperty(name = "myfactory.endpoint.url.auth", defaultValue = "http://localhost/myfactory/services/services.asmx")
    private String endpointUrl;

    @Inject
    @ConfigProperty(name = "myfactory.username")
    private String username;

    @Inject
    @ConfigProperty(name = "myfactory.password")
    private String password;

    @Inject
    @ConfigProperty(name = "myfactory.database")
    private Optional<String> database;

    @Inject
    @ConfigProperty(name = "myfactory.division", defaultValue = "1")
    private int division;

    @Inject
    @ConfigProperty(name = "myfactory.session.timeout.minutes", defaultValue = "5")
    private int sessionTimeoutMinutes;

    private volatile long lastAccessTime;

    // Thread-safe session storage
    private volatile String currentClientId;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Standard-Konstruktor für CDI
     */
    public MyFactorySessionManager() {
    }

    /**
     * Optionaler Konstruktor für JUnit / Standalone
     */
    public MyFactorySessionManager(String endpointUrl, int sessionTimeoutMinutes, String username, String password,
            String database,
            int division) {
        this.endpointUrl = endpointUrl;
        this.sessionTimeoutMinutes = sessionTimeoutMinutes;
        this.username = username;
        this.password = password;
        this.database = Optional.ofNullable(database);
        this.division = division;
    }

    /**
     * Cleanup on application shutdown.
     * Ensures the MyFactory session is properly closed when the server stops.
     */
    @PreDestroy
    void destroy() {
        if (currentClientId != null) {
            logger.info("├── Application shutting down, logging out from MyFactory...");
            try {
                logout();
            } catch (MyFactorySessionException e) {
                logger.warning("├── Logout during shutdown failed: " + e.getMessage());
            }
        }
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    /**
     * Get the current client ID. Login if not already logged in.
     * 
     * @return The current client ID
     * @throws MyFactorySessionException if login fails
     */
    public String getClientId() throws MyFactorySessionException {
        lock.writeLock().lock();
        try {
            // Session expired?
            if (currentClientId != null && isSessionExpired()) {
                logger.info("├── Session expired, logging out...");
                logout();
                currentClientId = null;
            }

            // do we have a login?
            if (currentClientId == null) {
                currentClientId = login();
                logger.info("Login successful. ClientID: " + currentClientId);
            }

            // update Timestamp
            lastAccessTime = System.currentTimeMillis();
            return currentClientId;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private boolean isSessionExpired() {
        return System.currentTimeMillis() - lastAccessTime > (sessionTimeoutMinutes * 60 * 1000L);
    }

    /**
     * Login and get a client ID for subsequent API calls.
     * 
     * @return The client ID (sClientID) for this session
     * @throws MyFactorySessionException if login fails
     */
    public String login() throws MyFactorySessionException {
        lock.writeLock().lock();
        logger.info("├── MyFactory API Login...");
        try {
            // Check if already logged in
            if (currentClientId != null) {
                logger.warning("├── Already logged in with clientID: " + currentClientId);
                return currentClientId;
            }

            logger.info("│   ├── user: " + username);
            logger.info("│   ├── endpointUrl: " + endpointUrl);
            logger.info("│   ├── database: " + database.orElse(""));
            logger.info("│   ├── division: " + division);

            SOAPConnection soapConnection = null;
            try {
                SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
                soapConnection = factory.createConnection();

                SOAPMessage loginRequest = createLoginRequest();

                // Debug output
                if (logger.isLoggable(java.util.logging.Level.FINE)) {
                    logSoapMessage("Login Request", loginRequest);
                }

                SOAPMessage loginResponse = soapConnection.call(loginRequest, endpointUrl);

                // Debug output
                if (logger.isLoggable(java.util.logging.Level.FINE)) {
                    logSoapMessage("Login Response", loginResponse);
                }

                String clientId = extractClientId(loginResponse);

                if (clientId == null || clientId.isEmpty()) {
                    throw new MyFactorySessionException("Login failed: No client ID received");
                }

                this.currentClientId = clientId;
                logger.info("├── Login successful. ClientID: " + clientId);

                return clientId;

            } catch (SOAPException e) {
                logger.severe("├── SOAP error during login: " + e.getMessage());
                throw new MyFactorySessionException("Login failed", e);
            } finally {
                if (soapConnection != null) {
                    try {
                        soapConnection.close();
                    } catch (SOAPException e) {
                        logger.warning("├── Failed to close SOAP connection: " + e.getMessage());
                    }
                }
            }

        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Logout and invalidate the current session.
     * IMPORTANT: Always call this to free the user session!
     * 
     * @throws MyFactorySessionException if logout fails
     */
    public void logout() throws MyFactorySessionException {
        lock.writeLock().lock();
        try {
            if (currentClientId == null) {
                logger.warning("└── No active session to logout");
                return;
            }

            String clientIdToLogout = currentClientId;
            logger.info("├── Logging out from MyFactory API. ClientID: " + clientIdToLogout);

            SOAPConnection soapConnection = null;
            try {
                SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
                soapConnection = factory.createConnection();

                SOAPMessage logoutRequest = createLogoutRequest(clientIdToLogout);

                // Debug output
                if (logger.isLoggable(java.util.logging.Level.FINE)) {
                    logSoapMessage("Logout Request", logoutRequest);
                }

                SOAPMessage logoutResponse = soapConnection.call(logoutRequest, endpointUrl);

                // Debug output
                if (logger.isLoggable(java.util.logging.Level.FINE)) {
                    logSoapMessage("Logout Response", logoutResponse);
                }

                // Check if logout was successful
                checkLogoutResponse(logoutResponse);

                this.currentClientId = null;
                logger.info("└── Logout successful");

            } catch (SOAPException e) {
                logger.severe("SOAP error during logout: " + e.getMessage());
                // Still clear the client ID to avoid stuck sessions
                this.currentClientId = null;
                throw new MyFactorySessionException("Logout failed", e);
            } finally {
                if (soapConnection != null) {
                    try {
                        soapConnection.close();
                    } catch (SOAPException e) {
                        logger.warning("Failed to close SOAP connection: " + e.getMessage());
                    }
                }
            }

        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Check if currently logged in
     */
    public boolean isLoggedIn() {
        lock.readLock().lock();
        try {
            return currentClientId != null;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Force clear the session (use when session is stuck)
     */
    public void clearSession() {
        lock.writeLock().lock();
        try {
            logger.warning("Forcefully clearing session. ClientID was: " + currentClientId);
            this.currentClientId = null;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Creates SOAP login request according to WSDL specification
     * Operation: gsLogin
     */
    private SOAPMessage createLoginRequest() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // Set MIME headers - CRITICAL for .NET ASMX services
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", SOAP_NAMESPACE + "gsLogin");
        headers.addHeader("Content-Type", "text/xml; charset=utf-8");

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Add namespace declaration
        envelope.addNamespaceDeclaration("tns", SOAP_NAMESPACE);

        SOAPBody soapBody = envelope.getBody();

        // Create gsLogin element
        SOAPElement gsLogin = soapBody.addChildElement("gsLogin", "tns");

        // Add sUser parameter
        SOAPElement sUser = gsLogin.addChildElement("sUser", "tns");
        sUser.addTextNode(username);

        // Add sPassword parameter
        SOAPElement sPassword = gsLogin.addChildElement("sPassword", "tns");
        sPassword.addTextNode(password);

        // Add sDatabase parameter (can be empty)
        SOAPElement sDatabase = gsLogin.addChildElement("sDatabase", "tns");
        if (database != null && !database.isEmpty()) {
            sDatabase.addTextNode(database.orElse(""));
        }

        // Add lDivision parameter
        SOAPElement lDivision = gsLogin.addChildElement("lDivision", "tns");
        lDivision.addTextNode(String.valueOf(division));

        // Add sError parameter (empty for request)
        SOAPElement sError = gsLogin.addChildElement("sError", "tns");

        soapMessage.saveChanges();
        return soapMessage;
    }

    /**
     * Creates SOAP logout request according to WSDL specification
     * Operation: gbLogoff
     */
    private SOAPMessage createLogoutRequest(String clientId) throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // Set MIME headers - CRITICAL for .NET ASMX services
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", SOAP_NAMESPACE + "gbLogoff");
        headers.addHeader("Content-Type", "text/xml; charset=utf-8");

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // Add namespace declaration
        envelope.addNamespaceDeclaration("tns", SOAP_NAMESPACE);

        SOAPBody soapBody = envelope.getBody();

        // Create gbLogoff element
        SOAPElement gbLogoff = soapBody.addChildElement("gbLogoff", "tns");

        // Add sClientID parameter
        SOAPElement sClientID = gbLogoff.addChildElement("sClientID", "tns");
        sClientID.addTextNode(clientId);

        soapMessage.saveChanges();
        return soapMessage;
    }

    /**
     * Extract client ID from login response
     */
    private String extractClientId(SOAPMessage response) throws SOAPException, MyFactorySessionException {
        SOAPBody responseBody = response.getSOAPBody();

        if (responseBody.hasFault()) {
            SOAPFault fault = responseBody.getFault();
            String faultString = fault.getFaultString();
            logger.severe("Login SOAP Fault: " + faultString);
            throw new MyFactorySessionException("Login failed: " + faultString);
        }

        // Extract gsLoginResult from response
        org.w3c.dom.NodeList nodes = responseBody.getElementsByTagNameNS("*", "gsLoginResult");

        if (nodes.getLength() > 0) {
            String clientId = nodes.item(0).getTextContent();

            // Check if there was an error
            org.w3c.dom.NodeList errorNodes = responseBody.getElementsByTagNameNS("*", "sError");
            if (errorNodes.getLength() > 0) {
                String error = errorNodes.item(0).getTextContent();
                if (error != null && !error.trim().isEmpty()) {
                    logger.severe("Login error from API: " + error);
                    throw new MyFactorySessionException("Login failed: " + error);
                }
            }

            return clientId;
        }

        throw new MyFactorySessionException("No client ID found in login response");
    }

    /**
     * Check logout response for errors
     */
    private void checkLogoutResponse(SOAPMessage response) throws SOAPException, MyFactorySessionException {
        SOAPBody responseBody = response.getSOAPBody();

        if (responseBody.hasFault()) {
            SOAPFault fault = responseBody.getFault();
            String faultString = fault.getFaultString();
            logger.severe("Logout SOAP Fault: " + faultString);
            throw new MyFactorySessionException("Logout failed: " + faultString);
        }

        // Extract gbLogoffResult
        org.w3c.dom.NodeList nodes = responseBody.getElementsByTagNameNS("*", "gbLogoffResult");

        if (nodes.getLength() > 0) {
            String result = nodes.item(0).getTextContent();
            if (!"true".equalsIgnoreCase(result)) {
                logger.warning("Logout returned false");
            }
        }
    }

    /**
     * Helper method to log SOAP messages for debugging
     */
    private void logSoapMessage(String label, SOAPMessage message) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            message.writeTo(out);
            logger.fine(label + ":\n" + out.toString("UTF-8"));
        } catch (Exception e) {
            logger.warning("Failed to log SOAP message: " + e.getMessage());
        }
    }

    /**
     * Custom exception for session management
     */
    public static class MyFactorySessionException extends Exception {
        public MyFactorySessionException(String message) {
            super(message);
        }

        public MyFactorySessionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}