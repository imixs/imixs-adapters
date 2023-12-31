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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.util.XMLParser;

import jakarta.inject.Inject;

/**
 * This adapter class is used to import a office document template from the
 * local file system. The office document can be edited by the Wopi Adapter
 * technology.
 * <p>
 * The Adapter class can be configured through the model by defining a workflow
 * result tag named 'wopi-template'.
 * <p>
 * Example:
 * 
 * <pre>
 * {@code
 *
 *    <wopi name="./my-templates/invoice-template.odt">
          <target-name>invoice-2020.odt</target-name>
          <auto-open>true</auto-open>
      </wopi>
 * }
 * </pre>
 * <p>
 * The template can optionally be loaded from a office textblock attachment.
 * 
 * <pre>
 * {@code
      <wopi name="<textblock>invoice template</textblock>" >
        .....
      </wopi>
 * }
 * </pre>
 * 
 * In this case the adapter will load the first attachment from the textblock
 * with the name 'inoice template'.
 * <p>
 * 
 * @author Ralph Soika
 * @version 1.0
 *
 */

public class WopiTemplateAdapter implements SignalAdapter {

    public static final String API_ERROR = "API_ERROR";
    public static String SNAPSHOTID = "$snapshotid";
    final String TYPE_TEXTBLOCK = "textblock";

    private static Logger logger = Logger.getLogger(WopiTemplateAdapter.class.getName());

    @Inject
    @ConfigProperty(name = "wopi.templates", defaultValue = "/tmp/wopi/templates/") // default template directory
    String templatePath;

    @Inject
    private WorkflowService workflowService;

    @Inject
    private TextBlockHelperService textBlockHelperService;

    /**
     * This method imports a office document template into the current workitem.
     */
    public ItemCollection execute(ItemCollection document, ItemCollection event) throws AdapterException {
        ItemCollection evalItemCollection = null;
        List<ItemCollection> officeTemplateConfigList = null;

        logger.finest("...running api adapter...");

        // read mandatory configuration form the model....
        try {

            // test for deprecated configuration using the <item> tag....
            if (isDeprecatedConfiguration(document, event)) {
                logger.warning(
                        "WopiTemplateAdapter is using deprecated configuration! Please use <wopi-template> instead of <wopi-tempalte name='source-path'>  - see documentation for details!");
                evalItemCollection = workflowService.evalWorkflowResult(event, "wopi-template", document,
                        false);
                officeTemplateConfigList = new ArrayList<>();
                officeTemplateConfigList.add(evalItemCollection);
            } else {
                String workflowResult = event.getItemValueString("txtActivityResult");
                officeTemplateConfigList = XMLParser.parseTagList(workflowResult, "wopi-template");
            }

            if (officeTemplateConfigList == null || officeTemplateConfigList.size() == 0) {
                throw new ProcessingErrorException(WopiTemplateAdapter.class.getSimpleName(), API_ERROR,
                        "missing wopi-template configuraiton in BPMN event!");
            }

            // iterate over all wopi-template configurations
            for (ItemCollection officeTemplateConfig : officeTemplateConfigList) {

                String sourcePath = officeTemplateConfig.getItemValueString("source-path");
                String targetName = officeTemplateConfig.getItemValueString("target-name");
                boolean autoOpen = officeTemplateConfig.getItemValueBoolean("auto-open");

                if (sourcePath.isEmpty()) {
                    throw new ProcessingErrorException(WopiTemplateAdapter.class.getSimpleName(), API_ERROR,
                            "missing source-path definition!");
                } else {
                    // adapt text but skip the textblock adapter itself....
                    // See issue #138
                    sourcePath = sourcePath.replace("textblock>", "textblockignore>");
                    sourcePath = workflowService.adaptText(sourcePath, document);
                    sourcePath = sourcePath.replace("textblockignore>", "textblock>");
                }

                if (targetName.isEmpty()) {
                    throw new ProcessingErrorException(WopiTemplateAdapter.class.getSimpleName(), API_ERROR,
                            "missing target-name definition!");
                } else {
                    // adapt text....
                    targetName = workflowService.adaptText(targetName, document);
                }

                if (!templatePath.endsWith("/")) {
                    templatePath = templatePath + "/";
                }
                if (sourcePath.startsWith("/")) {
                    sourcePath = sourcePath.substring(1);
                }

                // we can either load the template form the filesystem or from a textblock
                // entity
                FileData fileData = null;
                if (sourcePath.startsWith("<textblock>")) {
                    fileData = readFromTextblock(sourcePath);
                } else {
                    // load template from filesystem....
                    fileData = readFromFilesystem(templatePath + sourcePath);
                }
                if (fileData != null) {
                    logger.info("...adding new fileData object: " + targetName);
                    fileData.setName(targetName);
                    document.addFileData(fileData);

                    // Set auto-open file?
                    if (autoOpen) {
                        document.setItemValue(WopiController.ITEM_WOPI_AUTO_OPEN, targetName);
                    }
                }
            }

        } catch (PluginException e) {
            logger.warning("Unable to parse item definitions for 'wopi-template', verify model - " + e.getMessage());
        }

        return document;
    }

    /**
     * This helper method loads a file from the filesystem and returns a FileData
     * object with the file content.
     * 
     * @param sourcePath
     * @param targetName
     * @return
     */
    private FileData readFromFilesystem(String filePath) {
        boolean debug = logger.isLoggable(Level.FINE);

        Path filepath = Paths.get(filePath);
        if (debug) {
            logger.finest("......load office template from filepath=" + filepath);
        }
        byte[] content;
        try {
            content = Files.readAllBytes(filepath);
            FileData fileData = new FileData(filepath.getFileName().toString(), content, null, null);
            // attache filedata
            logger.finest("...read from Filesystem: " + filepath.getFileName().toString());
            return fileData;
        } catch (IOException e) {
            // no file was found
            throw new ProcessingErrorException(WopiTemplateAdapter.class.getSimpleName(), API_ERROR,
                    "...no file found in template path: " + filepath);
        }
    }

    /**
     * This helper mehtod returns the first filedata object form a textbock with a
     * given name
     * 
     * @param sourcePath
     * @param targetName
     * @return
     */
    private FileData readFromTextblock(String sourcePath) {

        // extract the textbock name
        String textblockName = XMLParser.findTagValue(sourcePath, "textblock");
        if (textblockName != null && !textblockName.isEmpty()) {
            ItemCollection textBlockDocument = textBlockHelperService.loadTextBlock(textblockName);

            if (textBlockDocument != null) {
                if (!"FILE".equals(textBlockDocument.getItemValueString("txtmode"))) {
                    // no file was found
                    throw new ProcessingErrorException(WopiTemplateAdapter.class.getSimpleName(), API_ERROR,
                            "textblock '" + textblockName + "' is not defined as type FILE!");

                }
                // fetch the snapshot for the current attachmentContext
                ItemCollection snapshotWorkitem = workflowService.getDocumentService()
                        .load(textBlockDocument.getItemValueString(SNAPSHOTID));
                if (snapshotWorkitem != null) {
                    textBlockDocument = snapshotWorkitem;
                }

                // return the 1st fileData object
                List<FileData> fileDataList = textBlockDocument.getFileData();
                if (fileDataList != null && fileDataList.size() > 0) {
                    FileData fileData = fileDataList.get(0);
                    logger.finest("...read from TextBlock: " + fileData.getName());
                    return fileData;
                }

            } else {
                throw new ProcessingErrorException(WopiTemplateAdapter.class.getSimpleName(), API_ERROR,
                        "textblock '" + textblockName + "' not found!");
            }
        }

        return null;
    }

    /**
     * This method tests if the BPMN configuration is still using the deprecated tag
     * 
     * <wopi-template name="source-path">....
     * 
     * instead of the new
     * 
     * <wopi name="..">
     * 
     * @param event
     * @return
     * @throws PluginException
     */
    private boolean isDeprecatedConfiguration(ItemCollection workitem, ItemCollection event) throws PluginException {

        String workflowResult = event.getItemValueString("txtActivityResult");
        if (!workflowResult.contains("<wopi-template>")) {
            return true;
        }

        return false;
    }
}
