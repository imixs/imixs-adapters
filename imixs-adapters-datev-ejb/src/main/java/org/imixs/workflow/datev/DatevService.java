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

package org.imixs.workflow.datev;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.jee.ejb.WorkflowService;
import org.imixs.workflow.jee.util.PropertyService;

/**
 * This EJB provides methods to interact with a magento instance through the
 * MagentoClient. This service EJB is also used by the MagentoPlugin class.
 * 
 * The service initialize a Client Implementation based on a configuration.
 * 
 * The Service EJB provides to both client types the MagentoSOAPClient and the
 * MagnetoRestClient. The clients are lazy loaded in the getter methods.
 * 
 * @author rsoika
 */

@DeclareRoles({ "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Stateless
@RunAs("org.imixs.ACCESSLEVEL.MANAGERACCESS")
@LocalBean
public class DatevService {

	public final static String ERROR_MESSAGE = "ERROR_MESSAGE";
	public final static String FILE_NOT_FOUND = "FILE_NOT_FOUND";

	final static public String TYPE = "datev";

	@EJB
	PropertyService propertyService = null;

	@EJB
	WorkflowService workflowService = null;
	private static Logger logger = Logger.getLogger(DatevService.class.getName());

	/**
	 * initial setup the magento client implementation
	 */
	@PostConstruct
	public void init() {

	}

	/***
	 * retruns a list of all existing Magento Shop Configurations
	 * 
	 * @return
	 */
	public List<ItemCollection> findAllConfigurations() {
		// load all configurations...
		String sQuery = "SELECT config FROM Entity AS config " + " JOIN config.textItems t1" + " WHERE config.type = '"
				+ DatevService.TYPE + "'" + " AND t1.itemName='txtname'" + " ORDER BY t1.itemValue";
		List<ItemCollection> col = workflowService.getEntityService().findAllEntities(sQuery, 0, -1);
		return col;
	}

	/**
	 * This method loads a datev configuration. If no configuration entity yet
	 * exists the method returns an empty ItemCollection. The method updates the
	 * timer details netxtTimeout and timeRemaining of a running timer service.
	 * 
	 * The method uses a caching mechanism to store still loaded conigurations.
	 * 
	 * @return configuration ItemCollection
	 */
	public ItemCollection loadConfiguration(String id) {

		if (id == null || id.isEmpty()) {
			logger.warning("[DatevService] invalid shop configuration id=" + id);
		}
		ItemCollection configItemCollection = null;
		// try to load....
		String sQuery = "SELECT config FROM Entity AS config " + " JOIN config.textItems AS t2"
				+ " WHERE config.type = '" + TYPE + "'" + " AND t2.itemName = 'txtname'" + " AND t2.itemValue = '" + id
				+ "'" + " ORDER BY t2.itemValue asc";
		Collection<ItemCollection> col = workflowService.getEntityService().findAllEntities(sQuery, 0, 1);

		if (col.size() > 0) {
			configItemCollection = col.iterator().next();
			logger.fine("[DatevService] datev configuration id=" + id + " loaded");

		} else {
			logger.warning("[DatevService] datev configuration id=" + id + " not defined!");

		}

		return configItemCollection;
	}

	/**
	 * This method finds a workitem for a magento order id. If no worktiem exits
	 * the method returns null.
	 * 
	 * The order ID is stored in the proeprty txtName with the following format:
	 * 
	 * <code>
	 *    magento:order:[SHOPID]:1
	 *  </code>
	 * 
	 * @return workitem or null if no workitem exits
	 */
	@Deprecated
	public ItemCollection findWorkitemByOrder(ItemCollection order) {

		String sKey = "";
		String sQuery = "SELECT wi FROM Entity as wi";
		sQuery += " JOIN wi.textItems as t ";
		sQuery += " WHERE wi.type IN ('workitem','workitemarchive')";
		sQuery += " AND t.itemName='txtname' AND t.itemValue='" + sKey + "'";
		Collection<ItemCollection> col = workflowService.getEntityService().findAllEntities(sQuery, 0, 1);
		if (col.size() > 0) {
			return col.iterator().next();
		}
		// no order found
		return null;

	}

	/**
	 * This method adds the properties form a magento entity to an existing
	 * workitem. Each property of the magento entity will be prafixed with 'm_'.
	 * 
	 * <code>
	 *   entity_id => m_entity_id
	 * </code>
	 * 
	 * The method also clears all existing magento properties before!
	 * 
	 * @param workitem
	 *            - a workItem instance
	 * @param magentoEntity
	 *            - holds the properties to be added into the workItem
	 */

	@Deprecated
	@SuppressWarnings("unchecked")
	public ItemCollection addDatevEntity(ItemCollection workitem, ItemCollection magentoEntity) {

		// add magento properties
		Iterator<String> keys = magentoEntity.getAllItems().keySet().iterator();
		while (keys.hasNext()) {
			String sName = keys.next();
			workitem.replaceItemValue("m_" + sName, magentoEntity.getItemValue(sName));
		}

		// now we need to verify if the workitem has more magento properties as
		// the magento Entity.
		Vector<String> removeItemList = new Vector<String>();
		keys = workitem.getAllItems().keySet().iterator();
		while (keys.hasNext()) {
			String sName = keys.next();
			// magento property??
			if (sName.startsWith("m_")) {
				String sMagento = sName.substring(2);
				if (!magentoEntity.hasItem(sMagento)) {
					removeItemList.add(sName);
				}
			}
		}
		for (String aName : removeItemList) {
			workitem.removeItem(aName);
		}

		return workitem;
	}

	/**
	 * This method imports all entities from a csv file.
	 * 
	 * The Paremeter start and count can be used to import only a part of the
	 * file.
	 * 
	 * 
	 * @param configuration
	 *            - the configuration entity for the Datev import
	 * 
	 * @param start
	 *            - optional start position
	 * @param count
	 *            - optional count (default =-1)
	 * @throws PluginException
	 */
	@SuppressWarnings("unchecked")
	public void importEntities(ItemCollection configuration, int start, int count) throws DatevException {

		String modelversion = null;
		String filename = null;
		String encoding=null;
		int processID, activityID;
		Long lastImport = null;
		long modifiedTime = 0;

		String sDatevStatus = null;
		String sDatevID = configuration.getItemValueString("txtName");
		logger.info("[DatevSchedulerSerivce] import Datev id= " + sDatevID);

		filename = configuration.getItemValueString("_datev_path");
		modelversion = configuration.getItemValueString("_datev_modelversion");
		encoding = configuration.getItemValueString("_datev_encoding");
		if (encoding.isEmpty()) {
			encoding="UTF-8";
		}

		List<?> vtime = configuration.getItemValue("_datev_lLastImport");
		if (vtime.size() == 0)
			lastImport = new Long(0);
		else {
			try {
				lastImport = (Long) vtime.get(0);
			} catch (ClassCastException e) {
				lastImport = new Long(0);
			}
		}
		
		processID = Integer.parseInt(configuration.getItemValueString("_datev_processid"));
		activityID = Integer.parseInt(configuration.getItemValueString("_datev_activityid"));

		File file = new File(filename);
		modifiedTime = file.lastModified();
		if (modifiedTime == 0)
			throw new DatevException(sDatevID,FILE_NOT_FOUND,"Datev importfile '" + filename + "' not found!");

		if (lastImport < modifiedTime) {
			DataInputStream in = null;
			try {
				FileInputStream fis = new FileInputStream(filename);
			
				in = new DataInputStream(fis);

				BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));

				String strLine;

				// read the first line containing the field names
				String fieldnames = br.readLine();

				// skipp start pos....
				if (start > 0) {
					for (int i = 0; i < start; i++) {
						// skip line
						if (br.readLine() == null)
							break;
					}
				}

			} catch (IOException ex) {

				ex.printStackTrace();
			} finally {
				// Close the input stream
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	
	/**
	 * This method parses a DATEV field description (first line of the csv file)
	 * @return list of fieldnames
	 */
	public List<String> parseFieldList(String data) {
		List<String> result = new ArrayList<String>(); 
		StringTokenizer st=new StringTokenizer(data, ";");
		while (st.hasMoreTokens()) {
			String field=st.nextToken();
			if (!field.isEmpty()) {
				field=field.replace(' ','_');
				field=field.replace('/','_');
				field=field.replace('\\','_');
				field=field.replace('.','_');
				result.add(field);
			} else {
				// add dummy entry
				result.add(null);
			}
			
		}
		return result;
	}

}
