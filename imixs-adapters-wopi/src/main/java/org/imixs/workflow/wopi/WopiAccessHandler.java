package org.imixs.workflow.wopi;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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
import org.imixs.workflow.FileData;
import org.imixs.workflow.WorkflowKernel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The WopiAccessHandler provides methods to generate 'access_token's and also
 * computes the WOPI Client endpoints from the discovery URL.
 * <p>
 * The wopi client endpoint can be set by the environment variable
 * WOPI_PUBLIC_ENDPOINT
 * <p>
 * Optional a discoveryEndpoint (WOPI_DISCOVERY_ENDPOINT) can be set to resolve
 * the WOPI Client endpoints dynamically. In most cases this is not necessary
 * and a wopi.public.endpoint can be set.
 * <p>
 * The method cacheFileData and fetchFileData are used to store a modified file
 * object temporarily into the wopi local file cache.
 * 
 * @author rsoika
 * @version 1.0
 */
@ApplicationScoped
public class WopiAccessHandler {

    private String jwtPassword;
    private Map<String, String> extensions = null;
    private Map<String, String> mimeTypes = null;

    @Inject
    @ConfigProperty(name = "wopi.discovery.endpoint")
    Optional<String> wopiDiscoveryEndpoint;

    @Inject
    @ConfigProperty(name = "wopi.public.endpoint")
    Optional<String> wopiPublicEndpoint;

    @Inject
    @ConfigProperty(name = "wopi.access.token.expiration", defaultValue = "3600") // 1 hour
    long wopiAccessTokenExpiration; // seconds

    @Inject
    @ConfigProperty(name = "wopi.file.cache", defaultValue = "/tmp/wopi/") // default cache directory
    String wopiFileCache;

    private static Logger logger = Logger.getLogger(WopiAccessHandler.class.getName());

    /**
     * PostContruct event - generate a jwt password to compute the access tokens.
     * <p>
     * Optional a discoveryEndpoint can be set to resolve the WOPI Client endpoints
     * dynamically. In most cases this is not necessary and a wopi.public.endpoint
     * can be set.
     *
     */
    @PostConstruct
    void init() {
        jwtPassword = WorkflowKernel.generateUniqueID();

        if (wopiPublicEndpoint == null || !wopiPublicEndpoint.isPresent() || wopiPublicEndpoint.get().isEmpty()) {
            // no public wopi endpoint was defined. In this case the wopi endpoints are
            // resolved by parsing the wopi discovery endpoint
            if (wopiDiscoveryEndpoint != null && wopiDiscoveryEndpoint.isPresent()
                    && !wopiDiscoveryEndpoint.get().isEmpty()) {
                try {
                    parseDiscoveryURL(wopiDiscoveryEndpoint.get());
                } catch (SAXException | IOException | ParserConfigurationException e) {
                    logger.severe("Failed to parse discovery endpoint '" + wopiDiscoveryEndpoint.get() + "' Error: "
                            + e.getMessage());
                    e.printStackTrace();
                    extensions = null;
                    mimeTypes = null;
                }
            } else {
                logger.warning("...unable to parse discovery endpoint - parameter ' not provided!");
            }
        }

    }

    /**
     * This method caches a fileData temporarily into the servers filesystem. The
     * cached fileData is identified by the accesstoken+filename (with a hash
     * value).
     * <p>
     * The method also deletes outdated cached files. 
     * 
     * @param jsessionid
     * @param file
     * @throws IOException
     */
    public void cacheFileData(String accessToken, FileData fileData) throws IOException {
        // test cache folder existence....
        if (!Files.exists(Paths.get(wopiFileCache))) {
            logger.info("...creating wopi cache folder '" + wopiFileCache + "'...");
            Files.createDirectories(Paths.get(wopiFileCache));
        }
        Path filepath = getCacheFilePath(accessToken, fileData.getName());
        logger.finest("......cache filepath=" + filepath);
        Files.write(filepath, fileData.getContent());

        // clean old files
        deleteFilesOlderThanNSeconds(wopiAccessTokenExpiration, wopiFileCache);
    }

    /**
     * This method tries the fetch a file content from the local wopi file cache.
     * <p>
     * If no cached file data exits, the method returns null.
     * 
     * @param accessToken
     * @return cached fileData object or null if no object was found for the given
     *         accessToken
     * @throws IOException
     */
    public FileData fetchFileData(String accessToken, String filename) {
        Path filepath = getCacheFilePath(accessToken, filename);
        logger.finest("......fetchData from filepath=" + filepath);
        byte[] content;
        try {
            content = Files.readAllBytes(filepath);
            FileData fileData = new FileData(filename, content, null, null);
            return fileData;
        } catch (IOException e) {
            logger.finest("...no file found in cache: " + filepath);
            // no cached file was found
        }
        return null;
    }

    /**
     * This method is called by the WopiController before a workitem is processed.
     * The method returns all modified cached files in a list of FileData objects.
     * 
     * @param accessToken
     * @return
     */
    public List<FileData> getAllFileData(String accessToken) {
        List<FileData> result = new ArrayList<FileData>();
        if (accessToken==null || accessToken.isEmpty()) {
            // no data
            return result;
        }

        Path searchPath = Paths.get(wopiFileCache);
        String prafix = accessToken.hashCode() + "";
        logger.finest("......getAllFileData by prafix " + prafix);
        try {

            File dir = new File(searchPath.toString());
            File[] foundFiles = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith(prafix);
                }
            });

            for (File file : foundFiles) {
                // Process file
                byte[] content = Files.readAllBytes(Paths.get(wopiFileCache + file.getName()));
                String filename=file.getName();
                // cut prefix
                filename=filename.substring(prafix.length()+1);
                logger.finest("......found cached file : " + filename);
                FileData fileData = new FileData(filename, content, null, null);
                result.add(fileData);
            }

        } catch (IOException e) {
            logger.severe("Failed to read file: " + e.getMessage());
        }

        return result;
    }

    
    /**
     * Deletes all existing files cached for a given token.
     * @param accessToken
     */
    public void clearFileCache(String accessToken) {

        if (accessToken==null || accessToken.isEmpty()) {
            // no data
            return;
        }
        Path searchPath = Paths.get(wopiFileCache);
        String prafix = accessToken.hashCode() + "";
        logger.finest("......clearFileCache by prafix " + prafix);
        try {

            File dir = new File(searchPath.toString());
            File[] foundFiles = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith(prafix);
                }
            });
            // delete files...
            for (File file : foundFiles) {
                Files.delete(Paths.get(wopiFileCache + file.getName()));
            }
        } catch (IOException e) {
            logger.severe("..failed to delete file: " + e.getMessage());
        }
    }
    
    /**
     * Generates a new access token
     * 
     * @return
     * @throws JWTException
     */
    public String generateAccessToken(String userid, String username) throws JWTException {
        SecretKey secretKey = HMAC.createKey("HmacSHA256", jwtPassword.getBytes());
        String payload = "{\"sub\":\"wopi-host\"";
        payload = payload + ",\"userid\":\"" + userid + "\"";
        payload = payload + ",\"username\":\"" + username + "\"";
        payload = payload + "}";
        JWTBuilder builder = new JWTBuilder().setKey(secretKey).setPayload(payload);

        return builder.getToken();
    }

    /**
     * Validates if a given access_token is still valid.
     * <p>
     * The method also clears the token form invalid query params. For some reasons
     * Collabora sends additional query params starting with '?' which is not
     * expected here!
     * <p>
     * In case the token is valid, the method returns the paylod, otherwise the
     * method returns null.
     * 
     * @param access_token
     * @return the token payload or null if the token is not valid
     */
    public JsonObject validateAccessToken(String access_token) {

        if (access_token == null || access_token.isEmpty()) {
            logger.warning("...missing access_token!");
            return null;
        }

        // clean unexpected query params
        access_token = purgeAccessToken(access_token);

        // We need the secret key...
        SecretKey secretKey = HMAC.createKey("HmacSHA256", jwtPassword.getBytes());
        try {
            // verify token and get the payload...
            String payload = new JWTParser().setKey(secretKey).setToken(access_token).verify().getPayload();

            // seems to be ok, we test the age of the IOT
            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonObject payloadObject = reader.readObject();
            JsonNumber jsonnumber = payloadObject.getJsonNumber("iat");

            long lIAT = jsonnumber.longValue();
            long lNow = (new Date().getTime() / 1000);
            long lTimout = lNow - wopiAccessTokenExpiration;
            if (lTimout > lIAT) {
                logger.warning("access_token has expired!");
                return null;
            }
            // token is valid
            return payloadObject;
        } catch (JWTException e) {
            // invalid token!
            logger.severe("...invalid access_token: " + e.getMessage());
        }
        return null;
    }

    /**
     * For some reason the LibreOffice Online adds additional query params starting
     * with '?' into the access token, with is not expected. Thes helper method
     * removes such parts of the token.
     * 
     * @param accesstoken
     * @return clean access token
     */
    public String purgeAccessToken(String access_token) {
        if (access_token == null || access_token.isEmpty()) {
            return access_token;
        }
        // test if a '?' is included and remove that part
        if (access_token.contains("?")) {
            // clean token....
            access_token = access_token.substring(0, access_token.indexOf("?"));
        }
        return access_token;

    }

    /**
     * Returns a WOPI Client endpoint by a file extension
     * <p>
     * If wopi.public.endpoint is set than this endpoint will be returned. If not
     * the endpoint is resolved dynamically by the wopi.desovery.endpoint
     * 
     * @param ext
     * @return
     */
    public String getClientEndpointByFilename(String filename) {

        // do we have a public wopi client endpoint?
        if (wopiPublicEndpoint != null || wopiPublicEndpoint.isPresent() && !wopiPublicEndpoint.get().isEmpty()) {
            return wopiPublicEndpoint.get();
        }

        // resolving endpoint by discovery url.....
        if (extensions == null) {
            // lazy initalizing...
            init();
        }

        if (extensions != null && filename.contains(".")) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1);
            return extensions.get(ext);
        }

        logger.warning("...no wopi client endpoint could be resolved!");
        return null;
    }

    /**
     * Returns a WOPI Client endpoint by a file mime type
     * <p>
     * If wopi.public.endpoint is set than this endpoint will be returned. If not
     * the endpoint is resolved dynamically by the wopi.desovery.endpoint
     * 
     * @param ext
     * @return
     */
    public String getClientEndpointByMimeType(String mimeType) {
        // do we have a public wopi client endpoint?
        if (wopiPublicEndpoint != null || wopiPublicEndpoint.isPresent() && !wopiPublicEndpoint.get().isEmpty()) {
            return wopiPublicEndpoint.get();
        }

        // resolving endpoint by discovery url.....
        if (extensions == null) {
            // lazy initalizing...
            init();
        }

        if (mimeTypes != null) {
            return mimeTypes.get(mimeType);
        }

        logger.warning("...no wopi client endpoint could be resolved!");
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

        logger.info("...parsing wopi.discovery.endpoint: " + endpoint);

        extensions = new HashMap<String, String>();
        mimeTypes = new HashMap<String, String>();

        // parse the discovery URL
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
                            logger.info("...ext=" + actionExt + " -> " + actionurlsrc);
                        } else {
                            // this can be a mimetype...
                            if (appName.contains("/")) {
                                mimeTypes.put(appName, actionurlsrc);
                                logger.info("...mimetype=" + appName + " -> " + actionurlsrc);
                            }
                        }
                    }
                }

            }
        }

    }

    /**
     * Returns a Path object pointing to a cached file version of a given file an
     * accesstoken. The accesstoken is hashed. In this way the WopiController can
     * also fetch all cached files before the workitem is processed or saved by the
     * user.
     * 
     * @param accessToken
     * @param filename
     * @return
     */
    private Path getCacheFilePath(String accessToken, String filename) {
        if (!wopiFileCache.endsWith("/")) {
            wopiFileCache = wopiFileCache + "/";
        }
        return Paths.get(wopiFileCache + accessToken.hashCode() + "_" + filename);
    }

    /**
     * Non recursive helper method to delete all files in a given folder that are
     * older than N minutes (ignores sub folders):
     * 
     * @param minutes
     * @param dirPath
     * @throws IOException
     */
    private void deleteFilesOlderThanNSeconds(long seconds, String dirPath) throws IOException {
        long cutOff = System.currentTimeMillis() - (seconds * 1000);
        Files.list(Paths.get(dirPath)).filter(path -> {
            try {
                return Files.isRegularFile(path) && Files.getLastModifiedTime(path).to(TimeUnit.MILLISECONDS) < cutOff;
            } catch (IOException ex) {
                // log here and move on
                return false;
            }
        }).forEach(path -> {
            try {
                logger.info("...delete deprecated wopi file: " + path);
                Files.delete(path);
            } catch (IOException ex) {
                // log here and move on
            }
        });
    }

}
