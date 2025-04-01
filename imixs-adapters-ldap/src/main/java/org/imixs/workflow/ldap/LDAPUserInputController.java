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
package org.imixs.workflow.ldap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.marty.profile.ProfileService;
import org.imixs.marty.profile.UserInputController;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ItemCollectionComparator;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Named;

/**
 * The LDAPUserInputController searches user entries directly in the ldap
 * directory. This allows to search for names, even if there are no profiles
 * created for them yet.
 * The class is only used for the name search in user input fields from the
 * imixs-marty project!
 * 
 * The controller replaces the default marty userController as an alternative
 * defined in the beans.xml:
 * 
 * <pre>
 * {@code
	
	<alternatives>
		<class>org.imixs.workflow.ldap.LDAPUserInputController</class>
	</alternatives>
	}
 * </pre>
 * 
 * 
 * @author rsoika
 * @version 1.0
 */

@Named("userInputController")
@Alternative
@SessionScoped
public class LDAPUserInputController extends UserInputController {

	private static final long serialVersionUID = 1L;

	@EJB
	protected LDAPLookupService ldapLookupService;

	@EJB
	protected ProfileService profileService;

	private static Logger logger = Logger.getLogger(LDAPUserInputController.class.getName());

	/**
	 * This method returns a list of profile ItemCollections matching the search
	 * phrase. The search is delegated to the LdapService
	 * 
	 * @param phrase
	 *               - search phrase
	 * @return - list of matching profiles
	 */
	@Override
	public List<ItemCollection> searchProfile(String phrase) {
		List<ItemCollection> searchResult = new ArrayList<ItemCollection>();

		if (phrase == null || phrase.isEmpty()) {
			return searchResult;
		}

		// ldap.enabled ?
		if (!ldapLookupService.isEnabled()) {
			// suche ober normalen profile controller
			return super.searchProfile(phrase);
		} else {
			// suche über ldap...
			List<ItemCollection> result = ldapLookupService.searchUserList(phrase);
			logger.fine("...found " + result.size() + " user objects...");
			return result;
		}
	}

	/**
	 * This method returns a sorted list of users for a given userId list
	 * 
	 * @param aNameList
	 *                  - string list with user ids
	 * @return - list of profiles
	 */
	@Override
	public List<ItemCollection> getSortedProfilelist(List<Object> aNameList) {
		List<ItemCollection> profiles = new ArrayList<ItemCollection>();

		if (aNameList == null)
			return profiles;

		// add all profile objects....
		for (Object aentry : aNameList) {
			if (aentry != null && !aentry.toString().isEmpty()) {
				ItemCollection profile = null;
				// ldap.enabled ?
				if (!ldapLookupService.isEnabled()) {
					// suche ober normalen profile controller
					profile = profileService.findProfileById(aentry.toString());
				} else {
					// use ldap....
					profile = this.ldapLookupService.findUser(aentry.toString());
				}
				if (profile != null) {
					profile.setItemValue("txtname", aentry.toString());
					profiles.add(profile);
				} else {
					// create a dummy entry
					profile = new ItemCollection();
					profile.replaceItemValue("txtName", aentry.toString());
					profile.replaceItemValue("txtUserName", aentry.toString());
					profiles.add(profile);
				}
			}
		}

		// sort by username..
		Collections.sort(profiles, new ItemCollectionComparator("txtUserName", true));

		return profiles;
	}

}
