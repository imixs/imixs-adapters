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

import java.util.Map;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.WorkflowContext;
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

	private LDAPLookupService ldapLokupService = null;

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
			ldapLokupService = (LDAPLookupService) ctx.lookup(jndiName);
		} catch (NamingException e) {
			throw new PluginException(LDAPPlugin.class.getSimpleName(),
					LDAPSERVICE_NOT_BOUND,
					"Unable to lookup LDAPLookupService EJB", e);
		}

	}

	/**
	 * Run only on Profile Entities
	 * 
	 * The method load the user object form the LDAP Service and compares
	 * the attributes (defined in the imixs.properties 'ldap.user-attributes') with the current values.
	 * If necessary the atributes will be automatically updated. 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ItemCollection run(ItemCollection adocumentContext,
			ItemCollection documentActivity) throws PluginException {
		ItemCollection profile = adocumentContext;
		// validate profile..
		if ("profile".equals(profile.getItemValueString("type"))) {
			
			String sUserID=profile.getItemValueString("txtname");
			
			// compare attributes....
			ItemCollection ldapUser = ldapLokupService.findUser(sUserID);
			if (ldapUser != null) {
				logger.fine("ldap entry found, verifing attributes...");
				// print all
				Map<String, Object> items = (Map<String, Object>) ldapUser.getItemList();

				for (Map.Entry<String, Object> entry : items.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					logger.finest(" ...... " + key + "=" + value);

					if (!profile.getItemValue(key).equals(ldapUser.getItemValue(key))) {
						profile.replaceItemValue(key, ldapUser.getItemValue(key));
					}
				}
			} else {
				logger.warning("userid " + sUserID + " not found!");
			}
		
		}

		return profile;
	}

	
}
