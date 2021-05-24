/*  
 *  Imixs-Workflow 
 *  
 *  Copyright (C) 2001-2020 Imixs Software Solutions GmbH,  
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
 *      https://www.imixs.org
 *      https://github.com/imixs/imixs-workflow
 *  
 *  Contributors:  
 *      Imixs Software Solutions GmbH - Project Management
 *      Ralph Soika - Software Developer
 */

package org.imixs.workflow.wopi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;

/**
 * This adapter class is used to import a office document template from the
 * local file system. The office document can be edited by the Wopi Adapter
 * technology.
 * <p>
 * The Adapter class can be configured through the model by defining a workflow
 * result tag named 'office-template'.
 * <p>
 * Example:
 * 
 * <pre>
 * {@code
<office-template name="source-path">./my-templates/invoice-template.odt</office-template>
<office-template name="target-name">invoice-2020.odt</office-template>
 * }
 * </pre>
 * 
 * @author Ralph Soika
 * @version 1.0
 *
 */

public class OfficeTemplateAdapter implements SignalAdapter {

    public static final String API_ERROR = "API_ERROR";

    private static Logger logger = Logger.getLogger(OfficeTemplateAdapter.class.getName());

    @Inject
    @ConfigProperty(name = "wopi.templates", defaultValue = "/tmp/wopi/templates/") // default template directory
    String templatePath;

    @Inject
    private WorkflowService workflowService;

    /**
     * This method imports a office-template into the current workitem.
     */
    public ItemCollection execute(ItemCollection document, ItemCollection event) throws AdapterException {
        boolean debug = logger.isLoggable(Level.FINE);
        debug = true;

        logger.finest("...running api adapter...");

        // read optional configuration form the model or imixs.properties....
        try {
            ItemCollection officeTemplateConfig = workflowService.evalWorkflowResult(event, "office-template", document,
                    false);

            String sourcePath = officeTemplateConfig.getItemValueString("source-path");
            String targetName = officeTemplateConfig.getItemValueString("target-name");
            boolean autoOpen=officeTemplateConfig.getItemValueBoolean("auto-open");

            if (sourcePath.isEmpty()) {
                throw new ProcessingErrorException(OfficeTemplateAdapter.class.getSimpleName(), API_ERROR,
                        "missing source-path definition!");
            }
            if (targetName.isEmpty()) {
                throw new ProcessingErrorException(OfficeTemplateAdapter.class.getSimpleName(), API_ERROR,
                        "missing target-name definition!");
            } else {
                // adapt text....
                targetName = workflowService.adaptText(targetName, document);
            }

            if (!templatePath.endsWith("/")) {
                templatePath=templatePath+"/";
            }
            if (sourcePath.startsWith("/")) {
                sourcePath=sourcePath.substring(1);
            }
            // load templage from filesystem....
            Path filepath = Paths.get(templatePath+sourcePath);
            if (debug) {
                logger.finest("......load office template from filepath=" + filepath);
            }
            byte[] content;
            try {
                content = Files.readAllBytes(filepath);
                FileData fileData = new FileData(targetName, content, null, null);
                // attache filedata
                if (debug) {
                    logger.finest("......adding new fileData object: " + targetName);
                }
                document.addFileData(fileData);
                
                // Set auot-open file?
                if (autoOpen) {
                    document.setItemValue(WopiController.ITEM_WOPI_AUTO_OPEN, targetName);
                }
            } catch (IOException e) {
                // no file was found
                throw new ProcessingErrorException(OfficeTemplateAdapter.class.getSimpleName(), API_ERROR,
                       "...no file found in template path: " + filepath);
            }

        } catch (PluginException e) {
            logger.warning("Unable to parse item definitions for 'office-template', verify model - " + e.getMessage());
        }

        return document;
    }

}
