package org.imixs.workflow.wopi;

import java.io.Serializable;
import java.util.List;

/*******************************************************************************
 *  Imixs Workflow Technology
 *  Copyright (C) 2003, 2008 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 2 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika
 *  
 *******************************************************************************/

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.jwt.JWTException;
import org.imixs.workflow.FileData;
import org.imixs.workflow.faces.data.WorkflowEvent;
import org.imixs.workflow.faces.fileupload.FileUploadController;
import org.imixs.workflow.faces.util.ResourceBundleHandler;

/**
 * The WopiController is a front end controller providing the access endpoint
 * for a file.
 * <p>
 * The controller expects the environment variable 'WOPI_HOST_ENDPOINT' with the
 * internal URL the WopiClient can contact the WopiHostService
 * <p>
 * The controller also listens on the WorklfowEvent to update modified file
 * content into the workItem before processing.
 * 
 * @author rsoika
 * 
 */
@Named
@ConversationScoped
public class WopiController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(WopiController.class.getName());

    public static final String ITEM_WOPI_AUTO_OPEN = "wopi.auto.open";

    public static final String WOPI_CONFIRM_CHANGES = "WOPI_CONFIRM_CHANGES";

    private String accessToken = null;
    private boolean enabled = false;

    @Inject
    @ConfigProperty(name = "wopi.host.endpoint", defaultValue = "none")
    String wopiHostEndpoint;

    @Inject
    @ConfigProperty(name = "wopi.file.extensions", defaultValue = ".odt,.doc,.docx,.docm,.rtf,.ods,.xls,.xlsx,.odp,.ppt,.pptx,.odg,.dxf,.emf,.wmf,.vsd,.vsdx")
    String wopiFileExtensions;

    @Inject
    WopiAccessHandler wopiAccessHandler;

    @Inject
    ResourceBundleHandler resourceBundleHandler;

    @Inject
    FileUploadController fileUploadController;

    /**
     * PostContruct event - generate a jwt password to compute the access tokens.
     * 
     */
    @PostConstruct
    void init() {
        if (wopiHostEndpoint != null && !"none".equals(ITEM_WOPI_AUTO_OPEN)) {
            enabled = true;
        }
    }

    /**
     * Indicates if the wopi feature is enabled
     * 
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }

    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method generates a new access token.
     * <p>
     * A access token is specific to a userid and username
     * 
     * @return
     */
    private String generateAccessToken(String userid, String username) {
        // if a accessToken already exists, do not generate a new one
        if (accessToken != null) {
            return accessToken;
        }

        try {
            logger.finest("...... generating new access token");
            accessToken = wopiAccessHandler.generateAccessToken(userid, username);
        } catch (JWTException e) {
            logger.severe("Failed to generate access token: " + e.getMessage());
        }
        return accessToken;
    }

    /**
     * Clears an existing access token.
     */
    public void clearAccessToken() {
        if (accessToken != null) {
            wopiAccessHandler.clearFileCache(accessToken);
        }
        accessToken = null;
    }

    /**
     * Returns the access url for the wopi client.
     * <p>
     * The method creates an accessToken (JWT) including the username.
     * 
     * https://localhost:9980/{libreoffice-editor}.html?WOPISrc=http://wopi-app:8080/api/wopi/files/{your-file}
     * 
     */
    public String getWopiAccessURL(String uniqueid, String file, String userid, String username) {
        if (!enabled) {
            return null;
        }

        // compute the access base url
        String baseURL = wopiAccessHandler.getClientEndpointByFilename(file);
        if (baseURL == null) {
            logger.warning("...no wopi client endpoint found!");
            return null;
        }

        // test file extension
        String[] extensions = wopiFileExtensions.split(",");
        boolean supported = false;
        for (String ext : extensions) {
            if (file.endsWith(ext)) {
                supported = true;
                break;
            }
        }
        if (!supported) {
            logger.fine("...filextension '" + file + "' is not supported.");
            return null;
        }

        // init query string....
        if (!baseURL.contains("?")) {
            baseURL = baseURL + "?";
        }
        if (!baseURL.endsWith("?") && !baseURL.endsWith("&")) {
            baseURL = baseURL + "&";
        }

        String token = generateAccessToken(userid, username);
        baseURL = baseURL + "WOPISrc=" + wopiHostEndpoint + uniqueid + "/files/" + file + "&access_token=" + token;
        if (baseURL.startsWith("http://")) {
            logger.fine("...WOPI Client is running without SSL - this is not recommended for production!");
        }

        return baseURL;
    }

    /**
     * This method transfers all cached file updates form the local wopi file cache
     * into the current workitem, before the workitem is processed. The method also
     * clears the file cache.
     * <p>
     * To access the filedata object, the controller uses the access token
     * 
     */
    public void onWorkflowEvent(@Observes WorkflowEvent workflowEvent) {
        if (workflowEvent == null || workflowEvent.getWorkitem() == null) {
            return;
        }

        int eventType = workflowEvent.getEventType();

        // Update usericon, signature image imformation
        if (WorkflowEvent.WORKITEM_BEFORE_PROCESS == eventType && getAccessToken() != null) {
            List<FileData> files = wopiAccessHandler.getAllFileData(getAccessToken());
            for (FileData fileData : files) {
                logger.info(".....updating " + fileData.getName() + "...");
                fileUploadController.addAttachedFile(fileData);
            }
            // clear file cache
            wopiAccessHandler.clearFileCache(accessToken);

            // reset wopi.auto.open
            workflowEvent.getWorkitem().setItemValue(ITEM_WOPI_AUTO_OPEN, "");
        }
    }

    /**
     * This helper method returns the confirm message string used in case the
     * LibreOffice Online Editor is closed, but contains changes .
     * 
     * @return
     */
    public String getConfirmMessage() {

        String message = resourceBundleHandler.get(WOPI_CONFIRM_CHANGES);
        if (message == null || message.isEmpty()) {
            message = WOPI_CONFIRM_CHANGES;
        }
        return message;

    }
}
