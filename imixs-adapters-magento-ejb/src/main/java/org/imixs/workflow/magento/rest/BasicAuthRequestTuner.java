/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011, 2012, 2013, 2014 Imixs Software Solutions GmbH,  
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
 *  Project: 
 *  	http://www.imixs.org
 *  	https://github.com/imixs
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.magento.rest;

import org.imixs.workflow.util.Base64;
import org.scribe.model.Request;
import org.scribe.model.RequestTuner;

/**
 * This class extends the abstract class RequestTuner and adds a basic
 * authentication to the request haeder. This can be used in cases a OAuth
 * request needs a Basic Autentication.
 * 
 * <code>
 * 	Response response = request.send(new BasicAuthRequestTuner(user,password));
 * </code>
 * 
 * @author rsoika
 * 
 */
public class BasicAuthRequestTuner extends RequestTuner {

	private String user = null;
	private String password = null;

	/**
	 * Initializes a requestTuner with basic authentication information (user
	 * and password)
	 * 
	 * @param user
	 * @param password
	 */
	public BasicAuthRequestTuner(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	/**
	 * This method verifies if a user and password for basic authentication is
	 * provided.
	 */
	@Override
	public void tune(Request request) {

		if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
			String sURLAccess = "";
			// UserID:Passwort
			String sUserCode = user + ":" + password;
			// base64 converting String
			char[] authcode = Base64.encode(sUserCode.getBytes());
			sURLAccess = String.valueOf(authcode);
			// add header -
			// Authorization: Basic aHR0cHdhdGNoOmY=
			request.addHeader("Authorization", "Basic " + sURLAccess);
		}

	}
	
}
