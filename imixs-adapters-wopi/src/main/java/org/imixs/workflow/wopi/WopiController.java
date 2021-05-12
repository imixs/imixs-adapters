package org.imixs.workflow.wopi;

import java.io.Serializable;

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
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.jwt.JWTException;
import org.imixs.workflow.FileData;
import org.imixs.workflow.faces.fileupload.FileUploadController;

/**
 * The WopiController is a front end controller providing the access endpoint
 * for a file.
 * <p>
 * The controller expects the environment variable 'WOPI_HOST_ENDPOINT' with the
 * internal URL the WopiClient can contact the WopiHostService
 * <p>
 * The controller also provides a method to update a file saved by the wopi
 * client. For that reason, the controller is ConversationScoped
 * 
 * @author rsoika
 * 
 */
@Named
@ConversationScoped
public class WopiController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(WopiController.class.getName());

    private String accessToken = null;
    private boolean enabled = false;

    @Inject
    @ConfigProperty(name = "wopi.host.endpoint", defaultValue="")
    String wopiHostEndpoint;

    @Inject
    @ConfigProperty(name = "wopi.file.extensions",defaultValue = ".odt,.doc,.docx,.docm,.rtf,.ods,.xls,.xlsx,.odp,.ppt,.pptx,.odg,.dxf,.emf,.wmf,.vsd,.vsdx")
    String wopiFileExtensions;

    
    @Inject
    WopiAccessHandler wopiAccessHandler;

    @Inject
    FileUploadController fileUploadController;

    /**
     * PostContruct event - generate a jwt password to compute the access tokens.
     * 
     */
    @PostConstruct
    void init() {
        if (wopiHostEndpoint != null  && !wopiHostEndpoint.isEmpty()) {
            enabled = true;
        }
    }

    /**
     * Indicates if the wopi feature is enabled
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
        try {
            accessToken = wopiAccessHandler.generateAccessToken(userid, username);
        } catch (JWTException e) {
            logger.severe("Failed to generate access token: " + e.getMessage());
        }
        return accessToken;
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
        boolean supported=false;
        for (String ext: extensions) {
            if (file.endsWith(ext)) {
                supported=true;
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
        baseURL = baseURL + "WOPISrc=" + wopiHostEndpoint + uniqueid + "/files/" + file + "?access_token=" + token;

        // baseURL = baseURL + "&NotWOPIButIframe=true";

        if (baseURL.startsWith("http://")) {
            logger.warning("...WOPI Client is running without SSL - this is not recommended for production!");
        }

        return baseURL;
    }

    /**
     * This method is called by the javascript imixs-wopi.js library to fetch the
     * updated fileData object.
     * <p>
     * To access the filedata object, the controller uses the access token
     * 
     */
    public void updateFile() {

        logger.info("...update fileData...");

        FileData fileData = wopiAccessHandler.fetchFileData(getAccessToken());
        if (fileData != null) {
            fileUploadController.addAttachedFile(fileData);
        } else {
            logger.warning("...no updated fileData object found");
        }
    }

}
