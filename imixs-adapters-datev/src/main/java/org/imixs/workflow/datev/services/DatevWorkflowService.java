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

package org.imixs.workflow.datev.services;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ItemCollectionComparator;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.InvalidAccessException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.exceptions.WorkflowException;

/**
 * This EJB provides methods to import data from a DATEV file and convert the
 * objects into a workitem managed by the Imixs-Worklfow Engine.
 * <p>
 * The import file must contain 2 header rows. The 1st row contains the object
 * type, the 2nd row contains the filed names. *
 * <p>
 * The EJB reads a configuration entity with information how to assign a new
 * object to a workflow model
 * 
 * @see DatevImportService to import datev data without assigne the data to a
 *      workflow model.
 * 
 * @author rsoika
 */

@DeclareRoles({ "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Stateless
@RunAs("org.imixs.ACCESSLEVEL.MANAGERACCESS")
@LocalBean
public class DatevWorkflowService {

	public final static String MODEL_ERROR = "MODEL_ERROR";
	public final static String PROCESSING_ERROR = "PROCESSING_ERROR";
	public final static String CONFIG_ERROR = "CONFIG_ERROR";
	public final static String IO_ERROR = "IO_ERROR";
	public final static String FILE_NOT_FOUND = "FILE_NOT_FOUND";

	public final static String ISO8601_FORMAT_DATETIME = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public final static String ISO8601_FORMAT_DATE = "yyyy-MM-dd";

	final static public String TYPE = "datev";

	@EJB
	WorkflowService workflowService = null;
	private static Logger logger = Logger.getLogger(DatevWorkflowService.class.getName());

	/**
	 * initial setup the magento client implementation
	 */
	@PostConstruct
	public void init() {

	}

	/***
	 * returns a list of all existing datev Configurations
	 * 
	 * @return
	 */
	public List<ItemCollection> findAllConfigurations() {
		List<ItemCollection> col = workflowService.getDocumentService().getDocumentsByType(DatevWorkflowService.TYPE);
		// sort by name
		Collections.sort(col, new ItemCollectionComparator("txtname", true));
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
			logger.warning("invalid shop configuration id=" + id);
		}
		ItemCollection configItemCollection = null;
		String searchTerm = "( (type:\"" + TYPE + "\" ) AND txtname:\"" + id + "\")";

		Collection<ItemCollection> col;
		try {
			col = workflowService.getDocumentService().find(searchTerm, 1, 0);
			if (col.size() > 0) {
				configItemCollection = col.iterator().next();
				logger.fine("datev configuration id=" + id + " loaded");

			} else {
				logger.warning("datev configuration id=" + id + " not defined!");

			}

		} catch (QueryException e) {
			logger.warning(e.getMessage());
		}

		return configItemCollection;
	}

	/**
	 * This method finds a workitem by the attribute 'txtName'
	 * 
	 * @return workitem or null if no workitem exits
	 */
	public ItemCollection findWorkitemByName(String sKey) {

		String searchTerm = "( (type:\"workitem\" OR type:\"workitemarchive\") AND txtname:\"" + sKey + "\")";

		Collection<ItemCollection> col;
		try {
			col = workflowService.getDocumentService().find(searchTerm, 1, 0);
			if (col.size() > 0) {
				return col.iterator().next();
			}
		} catch (QueryException e) {
			logger.warning(e.getMessage());
		}

		// no order found
		return null;

	}

	/**
	 * This method imports all entities from a csv file.
	 * 
	 * The method runs in a new transaction so processing exceptions can be caught
	 * and stored in the DATEV configuration entity
	 * 
	 * The parameter start and count can be used to import only a part of the file.
	 * 
	 * 
	 * @param configuration - the configuration entity for the DATEV import
	 * 
	 * @param start         - optional start position
	 * @param count         - optional count (default =-1)
	 * @throws PluginException
	 */
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	public ItemCollection importEntities(ItemCollection configuration, int start, int maxcount) throws DatevException {

		int line = 0;
		String datevLine = null;
		String modelversion = null;
		String filename = null;
		String encoding = null;
		int processID, activityID;
		Long lastImport = null;
		long modifiedTime = 0;

		int workitemsImported = 0;
		int workitemsUpdated = 0;
		int workitemsFailed = 0;
		int workitemsTotal = 0;

		String sDatevID = configuration.getItemValueString("txtName");
		String sDatevPrimaryKey = configuration.getItemValueString("_datev_primarykey");
		filename = configuration.getItemValueString("_datev_path");
		logger.info("DATEV import id= " + sDatevID + " : " + filename);

		modelversion = configuration.getItemValueString("_datev_modelversion");
		encoding = configuration.getItemValueString("_datev_encoding");
		if (encoding.isEmpty()) {
			encoding = "UTF-8";
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

		// validate model information
		if (modelversion.isEmpty() || processID == 0 || activityID == 0) {
			logger.severe("Invalid Model Information: " + modelversion + " " + processID + "." + activityID
					+ " - Verify DATEV configuration " + sDatevID);
			throw new DatevException(MODEL_ERROR, MODEL_ERROR, "Invalid Model Information");
		}

		File file = new File(filename);
		modifiedTime = file.lastModified();
		if (modifiedTime == 0)
			throw new DatevException(sDatevID, FILE_NOT_FOUND, "Datev importfile '" + filename + "' not found!");

		if (lastImport < modifiedTime) {
			DataInputStream in = null;
			try {
				FileInputStream fis = new FileInputStream(filename);
				in = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));

				// skip first line
				br.readLine();
				line++;

				// read the first line containing the field names
				String fieldnames = br.readLine();
				line++;
				List<String> fields = parseFieldList(fieldnames);

				// skipp start pos....
				if (start > 0) {
					for (int i = 0; i < start; i++) {
						// skip line
						if (br.readLine() == null)
							break;
					}
				}

				// now we read all entities until maxcount
				while ((datevLine = br.readLine()) != null) {
					line++;
					workitemsTotal++;
					ItemCollection entity = readEntity(datevLine, fields);

					// replace txtName by the DATEV key field
					entity.replaceItemValue("txtname", entity.getItemValue("_datev_" + sDatevPrimaryKey));

					// test if workitem already exits....
					ItemCollection oldEntity = findWorkitemByName(entity.getItemValueString("txtName"));
					if (oldEntity == null) {
						// create new workitem
						entity.replaceItemValue(WorkflowService.MODELVERSION, modelversion);
						entity.replaceItemValue(WorkflowService.PROCESSID, processID);
						entity.replaceItemValue(WorkflowService.ACTIVITYID, activityID);
						processSingleWorkitem(entity);
						workitemsImported++;
					} else {
						// test if modified....
						if (!isEqualEntity(oldEntity, entity)) {
							logger.fine("update exsting DATV entity: " + oldEntity.getUniqueID());

							// copy all datev entries from the import into the
							// existing entity
							oldEntity.replaceAllItems(entity.getAllItems());
							oldEntity.replaceItemValue(WorkflowService.ACTIVITYID, activityID);
							processSingleWorkitem(oldEntity);
							workitemsUpdated++;
						}
					}
				}

				configuration.replaceItemValue("_datev_lLastImport", modifiedTime);
				configuration.replaceItemValue("_datev_datLastImport", new Date(modifiedTime));
			} catch (IOException ioex) {
				throw new DatevException(DatevWorkflowService.class.getName(), IO_ERROR, "" + ioex, ioex);
			} catch (Exception e) {
				// Catch Workflow Exceptions
				workitemsFailed++;

				logger.severe("DATEV import error at line " + line + ": " + datevLine);
				if (e.getCause() instanceof InvalidAccessException) {
					throw new DatevException(DatevWorkflowService.class.getName(),
							((InvalidAccessException) e.getCause()).getErrorCode(),
							((InvalidAccessException) e.getCause()).getMessage(), e);
				}
				if (e.getCause() instanceof WorkflowException) {
					throw new DatevException(DatevWorkflowService.class.getName(),
							((WorkflowException) e.getCause()).getErrorCode(),
							((WorkflowException) e.getCause()).getMessage(), e);
				}
				throw new DatevException(DatevWorkflowService.class.getName(), PROCESSING_ERROR, "" + e, e);
			}

			finally {
				configuration.replaceItemValue("numWorkItemsImported", workitemsImported);
				configuration.replaceItemValue("numWorkItemsUpdated", workitemsUpdated);
				configuration.replaceItemValue("numWorkItemsFailed", workitemsFailed);
				configuration.replaceItemValue("numWorkitemsTotal", workitemsTotal);

				// Close the input stream
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return configuration;
	}

	/**
	 * This method compares two datev entities based on the datev fields
	 * 
	 * @param oldEntity
	 * @param entity
	 * @param fields
	 * @return
	 */
	private boolean isEqualEntity(ItemCollection oldEntity, ItemCollection entity) {

		Set<String> fields = entity.getAllItems().keySet();
		for (String itemName : fields) {
			if (!entity.getItemValue(itemName).equals(oldEntity.getItemValue(itemName))) {
				// not equal
				return false;
			}
		}
		return true;
	}

	/**
	 * This method parses a DATEV field description (first line of the csv file)
	 * 
	 * @return list of fieldnames
	 */
	public List<String> parseFieldList(String data) {
		List<String> result = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(data, ";");
		while (st.hasMoreTokens()) {
			String field = st.nextToken();
			if (!field.isEmpty()) {
				field = field.replace(' ', '_');
				field = field.replace('/', '_');
				field = field.replace('\\', '_');
				field = field.replace('.', '_');
				field = field.replace('>', '_');
				field = field.replace('<', '_');
				field = field.replace('&', '_');
				result.add(field);
			} else {
				// add dummy entry
				result.add(null);
			}

		}
		return result;
	}

	/**
	 * This method creates a ItemCollection from a csv file data line
	 * 
	 * @param data
	 * @param fieldnames
	 * @return
	 */
	public ItemCollection readEntity(String data, List<String> fieldnames) {
		ItemCollection result = new ItemCollection();
		int iCol = 0;
		// String[] valuList = data.split(";", -1);
		String[] valuList = data.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		for (String itemValue : valuList) {
			// test if the token has content
			if (itemValue != null && !itemValue.isEmpty()) {
				// create a itemvalue with the corresponding fieldname

				// test if value is an ISO date format
				Date dateValue = parseISODate(itemValue);
				if (dateValue != null) {
					result.replaceItemValue("_datev_" + fieldnames.get(iCol), dateValue);
				} else {
					result.replaceItemValue("_datev_" + fieldnames.get(iCol), itemValue);
				}
			} else {
				// empty value
				result.replaceItemValue("_datev_" + fieldnames.get(iCol), "");
			}
			iCol++;
		}
		return result;
	}

	/**
	 * This method parses a string value for an ISO Date/Time format. If the value
	 * is parseable the method returns a Date object. In other case the method
	 * returns null.
	 * 
	 * Supported formats:
	 * 
	 * yyyy-MM-dd'T'HH:mm:ss.SSS
	 * 
	 * yyyy-MM-dd
	 * 
	 * @param itemValue
	 * @return
	 */
	private Date parseISODate(String itemValue) {

		// try to parse datetime
		try {
			DateFormat format = new SimpleDateFormat(ISO8601_FORMAT_DATETIME);
			Date date = format.parse(itemValue);
			if (date != null) {
				return date;
			}
		} catch (Exception e) {
			// unable to parse date
		}

		// try to parse date
		try {
			DateFormat format = new SimpleDateFormat(ISO8601_FORMAT_DATE);
			Date date = format.parse(itemValue);
			if (date != null) {
				return date;
			}
		} catch (Exception e) {
			// unable to parse date
		}

		// string was not parseable
		return null;
	}

	/**
	 * This method process a single workIten in a new transaction. The method is
	 * called by processWorklist()
	 * 
	 * @param aWorkitem
	 * @throws PluginException
	 * @throws ProcessingErrorException
	 * @throws AccessDeniedException
	 * @throws ModelException
	 */
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	public void processSingleWorkitem(ItemCollection aWorkitem)
			throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {
		workflowService.processWorkItem(aWorkitem);
	}

}
