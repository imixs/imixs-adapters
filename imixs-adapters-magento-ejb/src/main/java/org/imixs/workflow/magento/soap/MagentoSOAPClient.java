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
package org.imixs.workflow.magento.soap;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.magento.MagentoClient;
import org.imixs.workflow.magento.MagentoException;
import org.imixs.workflow.magento.soap.generated.AssociativeEntity;
import org.imixs.workflow.magento.soap.generated.CatalogProductEntity;
import org.imixs.workflow.magento.soap.generated.CatalogProductReturnEntity;
import org.imixs.workflow.magento.soap.generated.CustomerCustomerEntity;
import org.imixs.workflow.magento.soap.generated.Filters;
import org.imixs.workflow.magento.soap.generated.Mage_Api_Model_Server_V2_HandlerPortType;
import org.imixs.workflow.magento.soap.generated.MagentoService;
import org.imixs.workflow.magento.soap.generated.MagentoServiceLocator;
import org.imixs.workflow.magento.soap.generated.SalesOrderEntity;
import org.imixs.workflow.magento.soap.generated.SalesOrderListEntity;

public class MagentoSOAPClient implements MagentoClient {

	public final static String CONNECTION_FAILURE = "CONNECTION_FAILURE";
	private String magentoAccessKey = null;
	private String magentoAccessSecret = null;
	private String sessionId = null;
	private Mage_Api_Model_Server_V2_HandlerPortType stub = null;
	private static Logger logger = Logger.getLogger(MagentoSOAPClient.class
			.getName());

	@Override
	public void connect(ItemCollection magentoConfiguration)
			throws MagentoException {
		
		if (sessionId!=null) {
			// already connected
			return;
		}
		magentoAccessKey = magentoConfiguration
				.getItemValueString("txtMagentoSOAPAccessKey");
		magentoAccessSecret = magentoConfiguration
				.getItemValueString("txtMagentoSOAPAccessSecret");
		MagentoService service =null;
		
		
		String soapLocation=magentoConfiguration
				.getItemValueString("txtMagentoSOAPUriApi");
		if (!soapLocation.isEmpty()) {
			service= new MagentoServiceLocator(soapLocation);
		} else {
			service=new MagentoServiceLocator();
		}
		
		try {
			stub = service.getMage_Api_Model_Server_V2_HandlerPort();
			sessionId = stub.login(magentoAccessKey, magentoAccessSecret);

			logger.fine("[MagentoSOAPClient] connected - sessionId="
					+ sessionId);
		} catch (ServiceException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "Connection failed: ", e);
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "Connection failed: ", e);
		}
	}

	@Override
	public void disconnect() {
		sessionId = null;
	}

	@Override
	public void addOrderComment(String orderIncrementId, String status,
			String comment) throws MagentoException {
		logger.fine("[MagentoSOAPClient] getAddOrderComment - sessionId="
				+ sessionId);
		try {
			stub.salesOrderAddComment(sessionId, orderIncrementId, status,
					comment, "false");
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getAddOrderComment failed: ", e);
		}
	}

	@Override
	public List<ItemCollection> getProducts() throws MagentoException {
		logger.fine("[MagentoSOAPClient] getProducts - sessionId=" + sessionId);

		List<ItemCollection> result = new ArrayList<ItemCollection>();
		try {
			CatalogProductEntity[] responseObject = stub.catalogProductList(
					sessionId, null, null);
			if (responseObject != null) {
				for (CatalogProductEntity entity : responseObject) {
					ItemCollection itemCol = MagentoSOAPAdapter.adapt(entity);
					if (itemCol != null) {
						result.add(itemCol);
					}
				}
			}
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getProducts failed: ", e);
		}
		return result;
	}

	@Override
	public ItemCollection getCustomerById(int id) throws MagentoException {
		logger.fine("[MagentoSOAPClient] getCustomerById - sessionId="
				+ sessionId);
		try {
			CustomerCustomerEntity result = stub.customerCustomerInfo(
					sessionId, id, null);

			return MagentoSOAPAdapter.adapt(result);

		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getCustomerById failed: ", e);
		}
	}

	@Override
	public ItemCollection getOrderById(String id) throws MagentoException {
		logger.fine("[MagentoSOAPClient] getOrderById - sessionId=" + sessionId);
		try {
			SalesOrderEntity result = stub.salesOrderInfo(sessionId, id);

			return MagentoSOAPAdapter.adapt(result);
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getCustomerById failed: ", e);
		}
	}

	@Override
	public List<ItemCollection> getOrders(String status)
			throws MagentoException {
		logger.fine("[MagentoSOAPClient] getOrders - sessionId=" + sessionId);

		Filters filters = new Filters();
		filters.setFilter(new AssociativeEntity[] { new AssociativeEntity(
				"status", status) });
		List<ItemCollection> result = new ArrayList<ItemCollection>();
		try {
			SalesOrderListEntity[] responseObject = stub.salesOrderList(
					sessionId, filters);
			if (responseObject != null) {
				for (SalesOrderListEntity entity : responseObject) {
					ItemCollection itemCol = MagentoSOAPAdapter.adapt(entity);
					if (itemCol != null) {
						result.add(itemCol);
					}
				}
			}
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getOrders failed: ", e);
		}
		return result;
	}

	@Override
	public ItemCollection getProductBySKU(String sku) throws MagentoException {
		Set<String> attributes = new HashSet<String>();
		attributes.add("product_id");

		try {
			CatalogProductReturnEntity productEntity = stub.catalogProductInfo(
					sessionId, sku, null, null, "sku");

			return MagentoSOAPAdapter.adapt(productEntity);
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getProductBySKU failed: ", e);
		}
	}
}
