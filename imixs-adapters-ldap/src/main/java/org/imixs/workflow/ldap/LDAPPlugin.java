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

import jakarta.ejb.EJB;

import org.imixs.workflow.ItemCollection;
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

    @EJB
    private LDAPLookupService ldapLokupService;

    /**
     * Run only on Profile Entities
     * <p>
     * The method load the user object form the LDAP Service and compares the
     * attributes (defined in the imixs.properties 'ldap.user-attributes') with the
     * current values. If necessary the attributes will be automatically updated.
     */
    @Override
    public ItemCollection run(ItemCollection documentContext, ItemCollection event) throws PluginException {

        // validate profile..
        if ("profile".equals(documentContext.getItemValueString("type"))) {
            String sUserID = documentContext.getItemValueString("txtname");
            ldapLokupService.updateProfileLDAPData(sUserID, documentContext);
        }
        return documentContext;
    }

}
