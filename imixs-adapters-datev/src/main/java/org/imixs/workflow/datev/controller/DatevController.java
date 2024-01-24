package org.imixs.workflow.datev.controller;

import java.io.ByteArrayInputStream;

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

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.datev.imports.DatevImportService;
import org.imixs.workflow.datev.imports.DatevService;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.index.UpdateService;
import org.imixs.workflow.engine.scheduler.Scheduler;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.faces.fileupload.FileUploadController;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The DatevController is used to configure the DatevScheduler. This service is
 * used to generate datev export workitems.
 * <p>
 * The Controller creates a configuration entity "type=configuration;
 * name=datev".
 * <p>
 * The following config items are defined:
 * 
 * The following config items are defined:
 * 
 * <pre>
 * _model_version = model version for the SEPA export
 * _initial_task = inital task ID
 * </pre>
 * 
 * 
 * @author rsoika
 * 
 */
@Named
@SessionScoped
public class DatevController implements Serializable {

	public static final String DATEV_CONFIGURATION = "DATEV_CONFIGURATION";

	private ItemCollection importData;

	private ItemCollection configuration = null;

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DatevController.class.getName());

	@Inject
	DocumentService documentService;

	@Inject
	UpdateService updateService;

	@Inject
	DatevService datevService;

	@Inject
	DatevImportService datevImportService;

	@Inject
	protected FileUploadController fileUploadController;

	/**
	 * This method load the config entity after postContstruct. If no Entity exists
	 * than the ConfigService EJB creates a new config entity.
	 * 
	 */
	@PostConstruct
	public void init() {
		configuration = loadConfiguration(getName());
	}

	public String getName() {
		return DATEV_CONFIGURATION;
	}

	public ItemCollection getConfiguration() {
		if (configuration == null) {
			configuration = new ItemCollection();
			configuration.setItemValue("$workflowsummary", getName());
			configuration.setItemValue(Scheduler.ITEM_SCHEDULER_NAME, getName());
		}
		return configuration;
	}

	public void setConfiguration(ItemCollection configuration) {
		this.configuration = configuration;
	}

	/**
	 * Loads the scheduler configuration entity by name. The method returns null if
	 * no scheduler configuration exits.
	 * 
	 * @return
	 */
	public ItemCollection loadConfiguration(String name) {

		configuration = datevService.loadConfiguration(name);

		return configuration;
	}

	/**
	 * This method saves the DATEV configuration. The method ensures that the
	 * following properties are set to default.
	 * <ul>
	 * <li>type</li>
	 * <li>name</li>
	 * <li>$writeAccess</li>
	 * <li>$readAccess</li>
	 * </ul>
	 * The method also updates the timer details of a running timer.
	 * 
	 * @return
	 * @throws AccessDeniedException
	 */
	public ItemCollection saveConfiguration() {

		configuration = datevService.saveConfiguration(configuration);

		return configuration;
	}

	/**
	 * Itemcollection containing file import data
	 * 
	 * @return
	 */
	public ItemCollection getImportData() {
		if (importData == null) {
			importData = new ItemCollection();
		}
		return importData;
	}

	public void setImportData(ItemCollection importData) {
		this.importData = importData;
	}

	/**
	 * This method imports a DATEV Kreditoren.csv attachment added to the
	 * ItemCollection 'importData'
	 * <p>
	 * It may be possible but not typically that a user uploads multiple files to
	 * import. The method can handle multiple file uploads.
	 * <p>
	 * For each file the method calls the DatevImportService to import the data
	 * 
	 */
	public void startImport() throws PluginException {

		List<FileData> fileList = importData.getFileData();
		if (fileList == null) {
			return;
		}

		for (FileData file : fileList) {
			logger.info("Import: " + file.getName());

			// test if supported CSV file?
			if (file.getName().toLowerCase().endsWith(".csv")) {
				ByteArrayInputStream input = new ByteArrayInputStream(file.getContent());
				String result = datevImportService.importData(input, "ISO-8859-1");
				getImportData().replaceItemValue("log", result);
			} else {
				throw new PluginException(this.getClass().getName(), DatevImportService.IMPORT_ERROR,
						"File Format not supported: " + file.getName());
			}

		}
		// Explicit flush the lucene search event log
		updateService.updateIndex();

		// clear import data
		importData.removeItem("$file");

	}
}
