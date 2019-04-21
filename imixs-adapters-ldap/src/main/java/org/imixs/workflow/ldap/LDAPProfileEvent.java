package org.imixs.workflow.ldap;

import org.imixs.workflow.ItemCollection;

/**
 * The LDAPProfileEvent provides a CDI observer pattern. The LDAPProfileEvent is fired by the
 * LDAPLookupService EJB to adapt a profile ItemColleciton. An event observer can adapt the
 * profile.
 * 
 * @author Ralph Soika
 * @version 1.0
 * @see org.imixs.workflow.ldap.LDAPLookupService
 */
public class LDAPProfileEvent {

	private ItemCollection profile;

	public LDAPProfileEvent(ItemCollection profile) {
		this.profile = profile;
	}

	
	public ItemCollection getProfile() {
		return profile;
	}


	public void setProfile(ItemCollection profile) {
		this.profile = profile;
	}


	


}
