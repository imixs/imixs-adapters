package org.imixs.workflow.wopi;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.jwt.HMAC;
import org.imixs.jwt.JWTBuilder;
import org.imixs.jwt.JWTException;
import org.imixs.jwt.JWTParser;
import org.imixs.workflow.WorkflowKernel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The WopiAccessHandler provides methods to generate 'access_token's and also
 * computes the WOPI Client endpoints from the discovery URL.
 * 
 * @author rsoika
 * @version 1.0
 */
@ApplicationScoped
public class WopiAccessHandler {

    private String jwtPassword;

    private Map<String, String> extensions;
    private Map<String, String> mimeTypes;

    @Inject
    @ConfigProperty(name = "wopi.discovery.endpoint")
    Optional<String> wopiDiscoveryEndpoint;
    
    @Inject
    @ConfigProperty(name = "wopi.access.token.expiration", defaultValue = "3600") // 1 hour
    long wopiAccessTokenExpiration;

    private static Logger logger = Logger.getLogger(WopiAccessHandler.class.getName());

    /**
     * PostContruct event - generate a jwt password to compute the access tokens.
     * 
     */
    @PostConstruct
    void init() {
        extensions = new HashMap<String, String>();
        mimeTypes = new HashMap<String, String>();
        jwtPassword = WorkflowKernel.generateUniqueID();

        if (wopiDiscoveryEndpoint.isPresent() && !wopiDiscoveryEndpoint.get().isEmpty()) {
            try {
                parseDiscoveryURL(wopiDiscoveryEndpoint.get());
            } catch (SAXException | IOException | ParserConfigurationException e) {
                logger.severe("Failed to parse discovery endpoint '" + wopiDiscoveryEndpoint.get() + "' Error: "
                        + e.getMessage());
                e.printStackTrace();
            }
        } else {
            logger.warning("...unable to parse discovery endpoint - parameter ' not provided!");
        }

    }

    /**
     * Generates a new access token
     * 
     * @return
     * @throws JWTException
     */
    public String generateAccessToken() throws JWTException {
        SecretKey secretKey = HMAC.createKey("HmacSHA256", jwtPassword.getBytes());
        String payload = "{\"sub\":\"wopi-host\",\"name\":\"imixs-wopi-adapter\"}";
        JWTBuilder builder = new JWTBuilder().setKey(secretKey).setPayload(payload);

        return builder.getToken();
    }

    /**
     * Validates if a given access_token is still valid
     * 
     * @param access_token
     * @return
     */
    public boolean isValidAccessToken(String access_token) {
        // We need the secret key...
        SecretKey secretKey = HMAC.createKey("HmacSHA256", jwtPassword.getBytes());
        try {
            // verify token and get the payload...
            String payload = new JWTParser().setKey(secretKey).setToken(jwtPassword).verify().getPayload();
            
            // seems to be ok, we test the age of the IOT
            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonObject payloadObject = reader.readObject();
            JsonNumber jsonnumber = payloadObject.getJsonNumber("iat");
            
            long lIAT=jsonnumber.longValue(); 
            long lNow=(new Date().getTime() / 1000) ;
            long lTimout=lNow-wopiAccessTokenExpiration;
            if (lTimout > lIAT) {
                logger.warning("access_token has expired!");
                return false;
            }
            // token is valid
            return true;
        } catch (JWTException e) {
            // invalid token!
            logger.severe("...invalid access_token: " + e.getMessage());
        }
        return false;
    }

    /**
     * Returns a WOPI Client endpoint by a file extension
     * 
     * @param ext
     * @return
     */
    public String getClientEndpointByFilename(String filename) {
        if (extensions != null && filename.contains(".")) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1);
            return extensions.get(ext);
        }
        return null;
    }

    /**
     * Returns a WOPI Client endpoint by a file mime type
     * 
     * @param ext
     * @return
     */
    public String getClientEndpointByMimeType(String mimeType) {
        if (mimeTypes != null) {
            return mimeTypes.get(mimeType);
        }
        return null;
    }

    /**
     * This method parses the discovery endpoint
     * <p>
     * https://localhost:9980/hosting/discovery
     * <p>
     * 
     * @throws IOException
     * @throws SAXException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     */
    @SuppressWarnings("unused")
    void parseDiscoveryURL(String endpoint)
            throws MalformedURLException, SAXException, IOException, ParserConfigurationException {

        logger.info("....parseDiscoveryURL: " + endpoint);

        extensions = new HashMap<String, String>();
        mimeTypes = new HashMap<String, String>();

        // pase teh discovery url
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(endpoint).openStream());

        // parse all <app> nodes
        NodeList appList = doc.getElementsByTagName("app");
        for (int i = 0; i < appList.getLength(); i++) {
            Node appNode = appList.item(i);

            if (appNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) appNode;
                String appName = eElement.getAttribute("name");
                logger.info("...app=" + appName);
                // now get all action urls...
                NodeList actionElements = eElement.getElementsByTagName("action");
                for (int j = 0; j < actionElements.getLength(); j++) {
                    Node actionNode = actionElements.item(j);
                    if (actionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eActionElement = (Element) actionNode;
                        String actionExt = eActionElement.getAttribute("ext");
                        String actionName = eActionElement.getAttribute("name");
                        String actionurlsrc = eActionElement.getAttribute("urlsrc");

                        if (actionExt != null && !actionExt.isEmpty()) {
                            extensions.put(actionExt, actionurlsrc);
                        } else {
                            // this can be a mimetype...
                            if (appName.contains("/")) {
                                mimeTypes.put(appName, actionurlsrc);
                            }
                        }
                    }
                }

            }
        }

    }

}
