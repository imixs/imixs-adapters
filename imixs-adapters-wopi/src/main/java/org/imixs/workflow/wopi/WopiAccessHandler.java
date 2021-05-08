package org.imixs.workflow.wopi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
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
     * Returns a WOPI Client endpoint by a file extension
     * @param ext
     * @return
     */
    public String getClientEndpointByFilename(String filename) {
        if (extensions!=null && filename.contains(".")) {
            String ext=filename.substring(filename.lastIndexOf('.')+1);
            return extensions.get(ext);
        }
        return null;
    }
    
    /**
     * Returns a WOPI Client endpoint by a file mime type
     * @param ext
     * @return
     */
    public String getClientEndpointByMimeType(String mimeType) {
        if (mimeTypes!=null) {
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
