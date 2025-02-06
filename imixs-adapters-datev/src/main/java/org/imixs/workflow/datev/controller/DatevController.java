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
import java.util.ArrayList;
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
import org.imixs.workflow.faces.data.WorkflowController;
import org.imixs.workflow.faces.fileupload.FileUploadController;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The DatevController is used to configure the DatevScheduler. This service is
 * used to generate datev export workitems.
 * <p>
 * The Controller creates a configuration entity:
 * type=configuration name=datev
 * <p>
 * 
 * @author rsoika
 * 
 */
@Named
@SessionScoped
public class DatevController implements Serializable {

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
	WorkflowController workflowController;

	@Inject
	protected FileUploadController fileUploadController;

	/**
	 * This method load the config entity after postContstruct. If no Entity exists
	 * than the ConfigService EJB creates a new config entity.
	 * 
	 */
	@PostConstruct
	public void init() {
		configuration = loadConfiguration();
	}

	public String getName() {
		return DatevService.DATEV_CONFIGURATION;
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
	public ItemCollection loadConfiguration() {

		configuration = datevService.loadConfiguration();

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
	 * Diese Methode liefert eine liste von SelectItem Elementen mit den Werten des
	 * Buchungsschlüssels zurück.
	 * <p>
	 * Der Buchungschlüssel wird in folgendem Format im config entity
	 * DATEV_CONFIGURATION gespeichert
	 * 
	 * {@code
	 *  BEZEICHNUNG | DATEV_BU_SCHLUESSEL | PROZENTSATZ
	 *  }
	 * 
	 * <p>
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SelectItem> getBuSchluesel() throws Exception {

		ArrayList<SelectItem> selection;
		selection = new ArrayList<SelectItem>();

		// check if a value for this param is available...
		// if not return an empty list
		if (!this.configuration.hasItem("datev.buschluessel")) {
			return selection;
		}

		// get value list first value from vector if size >0
		List<String> valueList = this.configuration.getItemValue("datev.buschluessel");
		for (String aValue : valueList) {

			String[] parts = aValue.split("\\|");

			if (parts == null || parts.length < 3) {
				logger.warning(
						"Falsche Konfiguration der _buschluessel - Format: BEZEICHNUNG | DATEV_BU_SCHLUESSEL | PROZENTSATZ");
				continue;
			}
			// Es wird hier der DATEV BU Schlüssel ausgegeben. Das MandantPlugin errechnet
			// später den Prozentzsatz.
			selection.add(new SelectItem(parts[1].trim(), parts[0].trim()));

		}
		return selection;
	}

	/**
	 * Returns costcentre1 from the Corporate Space as an ArrayList of SelectItems
	 * objects. A param entry can be devided by a | into a label and a value
	 * component. Example:
	 * 
	 * <code>
	 *   17431 | Blaue Stadt I
		 17435 | Blaue Stadt I
		 17400 | Overhead/General
	 * </code>
	 * 
	 * <code>
	 * <f:selectItems value="#{datevController.costcentre2}" />
	 * </code>
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SelectItem> getCostcentre1() throws Exception {

		ArrayList<SelectItem> selection;
		selection = new ArrayList<SelectItem>();

		// check if we have a workitem and space
		if (workflowController.getWorkitem() == null) {
			return selection;
		}

		// get value list first value from vector if size >0
		List<?> valueList = configuration.getItemValue("datev.kostenstell1");
		for (Object aValue : valueList) {
			// test if aValue has a | as an delimiter
			String sValue = aValue.toString();
			String sName = sValue;

			if (sValue.indexOf("|") > -1) {
				sValue = sValue.substring(0, sValue.indexOf("|"));
				sName = sName.substring(sName.indexOf("|") + 1);
			}
			selection.add(new SelectItem(sValue.trim(), sName.trim()));

		}

		return selection;
	}

	/**
	 * Gibt die Bezeichnung der Kostenstelle mit nummer - so wie bei suche
	 * 
	 * @param value
	 * @return
	 */
	public String getCostcentre1Read(String value) {

		// get value list first value from vector if size >0
		List<String> valueList = configuration.getItemValue("datev.kostenstell1");

		for (String aValue : valueList) {
			String[] parts = aValue.split("\\|");
			if (parts == null || parts.length < 1) {
				return value;
			}
			String nummer = parts[0];
			String bez = nummer;
			if (parts.length > 1) {
				bez = parts[1];
			}

			if (value.equals(nummer)) {
				if (bez.startsWith(nummer)) {
					return bez;
				} else {
					return nummer + " " + bez;
				}
			}
		}

		return value;
	}

	/**
	 * Returns costcentre2 from the Object Space as an ArrayList of SelectItems
	 * objects. A param entry can be devided by a | into a label and a value
	 * component. Example:
	 * 
	 * <code>
	 *   17431 | Blaue Stadt I
	     17435 | Blaue Stadt I
	     17400 | Overhead/General
	 * </code>
	 * 
	 * <code>
	 * <f:selectItems value="#{datevController.costcentre2}" />
	 * </code>
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SelectItem> getCostcentre2() throws Exception {

		ArrayList<SelectItem> selection;
		selection = new ArrayList<SelectItem>();

		// check if we have a workitem and space
		if (workflowController.getWorkitem() == null) {
			return selection;
		}

		// get value list first value from vector if size >0
		List<?> valueList = configuration.getItemValue("datev.kostenstell2");
		for (Object aValue : valueList) {
			// test if aValue has a | as an delimiter
			String sValue = aValue.toString();
			String sName = sValue;

			if (sValue.indexOf("|") > -1) {
				sValue = sValue.substring(0, sValue.indexOf("|"));
				sName = sName.substring(sName.indexOf("|") + 1);
			}
			selection.add(new SelectItem(sValue.trim(), sName.trim()));

		}

		return selection;
	}

	/**
	 * Gibt die Bezeichnung der Kostenstelle mit nummer - so wie bei suche
	 * 
	 * @param value
	 * @return
	 */
	public String getCostcentre2Read(String value) {

		// get value list first value from vector if size >0
		List<String> valueList = configuration.getItemValue("datev.kostenstell2");

		for (String aValue : valueList) {
			String[] parts = aValue.split("\\|");
			if (parts == null || parts.length < 1) {
				return value;
			}
			String nummer = parts[0];
			String bez = nummer;
			if (parts.length > 1) {
				bez = parts[1];
			}

			if (value.equals(nummer)) {
				if (bez.startsWith(nummer)) {
					return bez;
				} else {
					return nummer + " " + bez;
				}
			}
		}

		return value;
	}

	/**
	 * Itemcollection containing file import
	 * data
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
