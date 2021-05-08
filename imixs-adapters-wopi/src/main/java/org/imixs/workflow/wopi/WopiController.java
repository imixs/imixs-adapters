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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * The WopiController is front end controller proivdint the access endpoint for
 * a file.
 * <p>
 * The controller expects the environment variable 'WOPI_HOST_ENDPOINT' with the
 * internal URL the WopiClient can contact the WopiHostService
 * 
 * @author rsoika
 * 
 */
@Named
@RequestScoped
public class WopiController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(WopiController.class.getName());

    @Inject
    @ConfigProperty(name = "wopi.host.endpoint", defaultValue = "http://localhost:8080/api/wopi/")
    String wopiHostEndpoint;

    @Inject
    WopiAccessHandler wopiAccessHandler;

    /**
     * PostContruct event - generate a jwt password to compute the access tokens.
     * 
     */
    @PostConstruct
    void init() {
        if (!wopiHostEndpoint.endsWith("/")) {
            wopiHostEndpoint = wopiHostEndpoint + "/";
        }

    }

    /**
     * Returns the access url for the wopi client
     * 
     * https://localhost:9980/{libreoffice-editor}.html?WOPISrc=http://wopi-app:8080/api/wopi/files/{your-file}
     * 
     */
    public String getWopiAccessURLByFileName(String filename) {

        // compute the access base url
        String baseURL = wopiAccessHandler.getClientEndpointByFilename(filename);
        if (baseURL == null) {
            logger.warning("...no wopi client endpoint found!");
            return null;
        }

        // init query string....
        if (!baseURL.contains("?")) {
            baseURL = baseURL + "?";
        }
        if (!baseURL.endsWith("?") && !baseURL.endsWith("&")) {
            baseURL = baseURL + "&";
        }

        baseURL = baseURL + wopiHostEndpoint + "files/" + filename;

        if (baseURL.startsWith("http://")) {
            logger.warning("...WOPI Client is running without SSL - this is not recommended for production!" );
        }
        
        
        return baseURL;
    }

}
