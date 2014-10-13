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

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.Plugin;
import org.imixs.workflow.WorkflowContext;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.plugins.jee.AbstractPlugin;

/**
 * This plugin provides methods to interact with a magento instance through the
 * magento rest api.
 * 
 * @author rsoika
 * 
 */
public class MagentoPlugin extends AbstractPlugin {

	public final static String MAGENTOSERVICE_NOT_BOUND = "MAGENTOSERVICE_NOT_BOUND";
	public final static String ERROR_MESSAGE = "ERROR_MESSAGE";
	
	public final static String MAGENTO_CONFIGURATION_ID= "txtMagentoConfiguration";
	public final static int ACTIVITY_MAGENTO_UPDATE = 800; // import magento order data

	ItemCollection documentContext;

	// Basic Authentication
	String user = null;
	String password = null;

	private MagentoService magentoService = null;

	private static Logger logger = Logger.getLogger(MagentoPlugin.class
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
			throw new PluginException(MagentoPlugin.class.getSimpleName(),
					MAGENTOSERVICE_NOT_BOUND, "MagentoService not bound", e);
		}

	}

	/**
	 * This method lookups the order data and updates the magento properties of
	 * the workitem
	 * 
	 * <code>
	    txtMagentoCustomer=magento customer name (First- and Lastname)
		txtMagentoCustomerEmail = E-Mail address of customer
		txtMagentoOrderID = Order id of magento order
		txtMagentoCustomerPhone = Telephone
       </code>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int run(ItemCollection workitem, ItemCollection documentActivity)
			throws PluginException {

		String sKey = workitem.getItemValueString("txtName");
		if (sKey.startsWith("magento:order:")) {

			// create custom magento fields
			workitem.replaceItemValue("txtMagentoOrderID",
					workitem.getItemValueString("m_increment_id"));
			List<Map> addresses = workitem
					.getItemValue("m_addresses");

			// copy address data into Imixs Fields

			for (Map addressMap : addresses) {
				ItemCollection address=new ItemCollection(addressMap);
				// address_type = billing / shipping
				logger.fine("[MagentoPlugin] update magentoCustomer data...");
				workitem.replaceItemValue(
						"txtMagentoCustomer",
						address.getItemValueString("firstname") + " "
								+ address.getItemValueString("lastname"));
				workitem.replaceItemValue("txtMagentoCustomerEmail",
						address.getItemValueString("email"));
				workitem.replaceItemValue("txtMagentoCustomerPhone",
						address.getItemValueString("telephone"));

				// copy all standard fileds
				// prafix field names with type (txtSilling/txtShipping
				copyAddressData(workitem,address);
				
			}

			// if email not defined we need to lookup the customer id...
			if (workitem.getItemValueString("txtMagentoCustomerEmail")
					.isEmpty()) {
				logger.fine("[MagentoPlugin] update magentoCustomer E-Mail...");
				String customerID = workitem
						.getItemValueString("m_customer_id");
				if (!customerID.isEmpty()) {
					ItemCollection customer = magentoService.getRestClient(workitem.getItemValueString(MAGENTO_CONFIGURATION_ID))
							.getCustomerById(new Integer(customerID));
					if (customer != null) {
						workitem.replaceItemValue("txtMagentoCustomerEmail",
								customer.getItemValueString("email"));

						// check phonnumber from addresses
						addresses = customer.getItemValue("addresses");
						for (Map addressMap : addresses) {
							// address_type = billing / shipping
							ItemCollection address=new ItemCollection(addressMap);
							
							workitem.replaceItemValue(
									"txtMagentoCustomerPhone",
									address.getItemValueString("telephone"));
							
							// copy all standard fileds
							// prafix field names with type (txtSilling/txtShipping
							copyAddressData(workitem,address);

						}
					}
				}
			}

		}

		return Plugin.PLUGIN_OK;
	}

	@Override
	public void close(int arg0) throws PluginException {

		// no op

	}

	/**
	 * This method copies the address data fields from a magento address into a
	 * workitem. Each fieldname will be prafixed with the address type
	 * 
	 * e.g. txtMagentoBillingFirstName
	 * 
	 */
	private void copyAddressData(ItemCollection workitem, ItemCollection address) {
		// copy all standard fileds
		// prafix field names with type (txtSilling/txtShipping
		String aType = "txtMagento"
				+ address.getItemValueString("address_type");

		workitem.replaceItemValue(aType + "firstname",
				address.getItemValueString("firstname"));

		workitem.replaceItemValue(aType + "lastname",
				address.getItemValueString("lastname"));

		workitem.replaceItemValue(aType + "email",
				address.getItemValueString("email"));
		workitem.replaceItemValue(aType + "telephone",
				address.getItemValueString("telephone"));
		workitem.replaceItemValue(aType + "company",
				address.getItemValueString("company"));
		workitem.replaceItemValue(aType + "street",
				address.getItemValueString("street"));
		workitem.replaceItemValue(aType + "postcode",
				address.getItemValueString("postcode"));
		workitem.replaceItemValue(aType + "city",
				address.getItemValueString("city"));
	}
}
