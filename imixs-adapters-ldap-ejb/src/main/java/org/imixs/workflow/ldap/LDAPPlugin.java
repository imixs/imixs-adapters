/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011 Imixs Software Solutions GmbH,  
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
 *  	http://java.net/projects/imixs-workflow
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.ldap;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.WorkflowContext;
import org.imixs.workflow.engine.PropertyService;
import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;

/**
 * This Plugin runs on Profile Entities. The plugin makes a ldap lookup to
 * update the properties
 * 
 * txtUserName, txtEmail
 * 
 * The ldap property attribute-names are read from the imixs.propries file
 * 
 * @author rsoika
 * 
 */
public class LDAPPlugin extends AbstractPlugin {
	public final static String LDAPSERVICE_NOT_BOUND = "LDAPSERVICE_NOT_BOUND";
	public final static String PROPERTYSERVICE_NOT_BOUND = "PROPERTYSERVICE_NOT_BOUND";

	private ItemCollection documentContext;
	private LDAPLookupService lookupService = null;
	private PropertyService propertyService = null;

	private static Logger logger = Logger.getLogger(LDAPPlugin.class.getName());

	@Override
	public void init(WorkflowContext actx) throws PluginException {
		super.init(actx);

		// get ldapService EJB
		String jndiName = "";

		try {
			InitialContext ictx = new InitialContext();
			Context ctx = (Context) ictx.lookup("java:comp/env");
			jndiName = "ejb/LDAPLookupService";
			lookupService = (LDAPLookupService) ctx.lookup(jndiName);
		} catch (NamingException e) {
			throw new PluginException(LDAPPlugin.class.getSimpleName(),
					LDAPSERVICE_NOT_BOUND,
					"Unable to lookup LDAPLooupService EJB", e);
		}

		try {
			// lookup PropertyService
			InitialContext ictx = new InitialContext();
			Context ctx = (Context) ictx.lookup("java:comp/env");
			jndiName = "ejb/PropertyService";
			propertyService = (PropertyService) ctx.lookup(jndiName);
		} catch (NamingException e) {
			throw new PluginException(LDAPPlugin.class.getSimpleName(),
					PROPERTYSERVICE_NOT_BOUND, "PropertyService not bound", e);
		}

	}

	/**
	 * Run only on Profile Entities
	 */
	@Override
	public ItemCollection run(ItemCollection adocumentContext,
			ItemCollection documentActivity) throws PluginException {
		documentContext = adocumentContext;
		// validate profile..
		if ("profile".equals(documentContext.getItemValueString("type"))) {
			updateLDAPAttributes();
		}

		return documentContext;
	}

	
	/**
	 * Updates txtUserName, txtEmail if ldap entry found
	 * 
	 * 
	 * The ldap property attribute-names are read from the imixs.propries file
	 * 
	 * 
	 */
	private void updateLDAPAttributes() {
		if (lookupService != null) {
			String sUid = documentContext.getItemValueString("txtName");

			if (!sUid.isEmpty()) {
				ItemCollection ldapItemCollection = lookupService
						.findUser(sUid);
				if (ldapItemCollection != null) {

					String sUserNameAttribute = (String) propertyService
							.getProperties().getProperty(
									"ldap.username-attribute", "displayName");
					String sEmailAttribute = (String) propertyService
							.getProperties().getProperty(
									"ldap.email-attribute", "mail");

					String sUserName = ldapItemCollection
							.getItemValueString(sUserNameAttribute);
					String sEmail = ldapItemCollection
							.getItemValueString(sEmailAttribute);

					if (!sUserName.isEmpty()) {
						documentContext.replaceItemValue("txtUserName",
								sUserName);
						logger.fine("[LDAPPlugin] updateLDAPAttribute : "
								+ sUserNameAttribute
								+ "='"
								+ ldapItemCollection
										.getItemValue(sUserNameAttribute)
								+ "' ");
					}
					if (!sEmail.isEmpty()) {

						documentContext.replaceItemValue("txtEmail", sEmail);
						logger.fine("[LDAPPlugin] updateLDAPAttributes : "
								+ sUserNameAttribute
								+ sEmailAttribute
								+ "='"
								+ ldapItemCollection
										.getItemValue(sEmailAttribute) + "'");
					}

				}
			}

		}
	}

}
