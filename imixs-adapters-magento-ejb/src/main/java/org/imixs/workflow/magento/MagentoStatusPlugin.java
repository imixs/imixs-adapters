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

package org.imixs.workflow.magento;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.Plugin;
import org.imixs.workflow.WorkflowContext;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.plugins.ResultPlugin;
import org.imixs.workflow.plugins.jee.AbstractPlugin;

/**
 * This plugin allows to change the status for an existing Sales Order in a
 * magento shop. The Plugin evaluates the activity result items
 * 
 * txtMagentoStatus - new state
 * 
 * and
 * 
 * txtMagentoComment - optional comment
 * 
 * keyMagentoNotify - indicates if a customer notification should be send
 * 
 * 
 * The magento uses the SOAPClient to send a comment with a new state
 * 
 * 
 * @author rsoika
 * 
 */
public class MagentoStatusPlugin extends AbstractPlugin {

	public final static String MAGENTOSERVICE_NOT_BOUND = "MAGENTOSERVICE_NOT_BOUND";

	public final static String MAGENTO_STATUS_PROPERTY = "txtMagentoStatus";
	public final static String MAGENTO_COMMENT_PROPERTY = "txtMagentoComment";
	public final static String MAGENTO_NOTIFY_PROPERTY = "keyMagentoNotify";

	ItemCollection documentContext;

	private MagentoService magentoService = null;

	private static Logger logger = Logger.getLogger(MagentoStatusPlugin.class
			.getName());

	/**
	 * 
	 */
	@Override
	public void init(WorkflowContext actx) throws PluginException {
		super.init(actx);
		try {
			// lookup PropertyService
			InitialContext ictx = new InitialContext();
			Context ctx = (Context) ictx.lookup("java:comp/env");
			String jndiName = "ejb/MagentoService";
			magentoService = (MagentoService) ctx.lookup(jndiName);
		} catch (NamingException e) {
			throw new PluginException(
					MagentoStatusPlugin.class.getSimpleName(),
					MAGENTOSERVICE_NOT_BOUND, "MagentoService not bound", e);
		}

	}

	/**
	 * This method evaluates the activity result and sends a new comment
	 * 
	 * <code>
	     <itemValue name="txtMagentoStatus">pending</itemValue>
		 <itemValue name="txtMagentoComment">some comment</itemValue>
       </code>
	 */
	@Override
	public int run(ItemCollection workitem, ItemCollection documentActivity)
			throws PluginException {

		// evaluate activity....
		String sResult = documentActivity
				.getItemValueString("txtActivityResult");
		ItemCollection evalItemCollection = new ItemCollection();
		ResultPlugin.evaluate(sResult, evalItemCollection);
		String sMagentoorderIncrementId = workitem
				.getItemValueString("txtMagentoOrderID");
		String sNewMagentoStatus = evalItemCollection
				.getItemValueString(MAGENTO_STATUS_PROPERTY);
		String sNewMagentoComment = evalItemCollection
				.getItemValueString(MAGENTO_COMMENT_PROPERTY);
		
		boolean notify = evalItemCollection
				.getItemValueBoolean(MAGENTO_NOTIFY_PROPERTY);

		// try to send the new status
		// if it breaks we throw a plugin exception
		if (!sMagentoorderIncrementId.isEmpty() && !sNewMagentoStatus.isEmpty()) {
			logger.fine("[MagentoStatusPlugin] add new comment: "
					+ sMagentoorderIncrementId + "=" + sNewMagentoStatus + " ("
					+ sNewMagentoComment + ")");
			MagentoClient magentoClient = magentoService.getSOAPClient(workitem.getItemValueString("txtMagentoConfiguration"));
			magentoClient.addOrderComment(sMagentoorderIncrementId,
					sNewMagentoStatus, sNewMagentoComment,notify);
		}
		return Plugin.PLUGIN_OK;
	}

	@Override
	public void close(int arg0) throws PluginException {

		// no op

	}

}
