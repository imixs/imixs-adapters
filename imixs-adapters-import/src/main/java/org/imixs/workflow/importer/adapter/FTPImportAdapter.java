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

package org.imixs.workflow.importer.adapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.ws.rs.core.MediaType;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.ModelService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.importer.DocumentImportEvent;
import org.imixs.workflow.importer.DocumentImportService;

/**
 * The FTPImportAdapter reacts on DocumentImportEvent and processes a FTP data
 * source.
 * 
 * @author rsoika
 *
 */ 
@Stateless
public class FTPImportAdapter {

    private static Logger logger = Logger.getLogger(FTPImportAdapter.class.getName());

    @EJB
    WorkflowService workflowService;

    @EJB
    ModelService modelService;

    @EJB
    DocumentImportService documentImportService;

    /**
     * This method reacts on a CDI ImportEvent and reads documents form a ftp
     * server.
     * 
     *  
     */
    public void onEvent(@Observes DocumentImportEvent event) {

        // check if source is already completed
        if (event.getResult() == DocumentImportEvent.PROCESSING_COMPLETED) {
            logger.finest("...... import source already completed - no processing will be performed.");
            return;
        }

        if (!"FTP".equalsIgnoreCase(event.getSource().getItemValueString("type"))) {
            // ignore data source
            logger.finest("...... type '" + event.getSource().getItemValueString("type") + "' skiped.");
            return;
        }

        String ftpServer = event.getSource().getItemValueString(DocumentImportService.SOURCE_ITEM_SERVER);
        String ftpPort = event.getSource().getItemValueString(DocumentImportService.SOURCE_ITEM_PORT);
        String ftpUser = event.getSource().getItemValueString(DocumentImportService.SOURCE_ITEM_USER);
        String ftpPassword = event.getSource().getItemValueString(DocumentImportService.SOURCE_ITEM_PASSWORD);
        String ftpPath = event.getSource().getItemValueString(DocumentImportService.SOURCE_ITEM_SELECTOR);

        if (!ftpPath.startsWith("/")) {
            ftpPath = "/" + ftpPath;
        }
        if (!ftpPath.endsWith("/")) {
            ftpPath = ftpPath + "/";
        }

        // if no server is given we exit
        if (ftpServer.isEmpty()) {
            logger.warning("...... no server specified!");
            return;
        }

        if (ftpPort.isEmpty()) {
            // set default port
            ftpPort = "21";
        }

        FTPClient ftpClient = null;
        try {
            logger.finest("......read directories ...");
            // TLS
            ftpClient = new FTPSClient("TLS", false);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(ftpServer, Integer.parseInt(ftpPort));
            if (ftpClient.login(ftpUser, ftpPassword) == false) {
                documentImportService.logMessage("FTP file transfer failed: login failed!",event);
                event.setResult(DocumentImportEvent.PROCESSING_ERROR);
                return;
            }

            ftpClient.enterLocalPassiveMode();
            logger.finest("...... FileType=" + FTP.BINARY_FILE_TYPE);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");

            logger.finest("......scan files from: " + ftpPath);
            /*
             * create a new workitem for each document
             */
            FTPFile[] allFiles = ftpClient.listFiles(ftpPath);
            int count = 0;
            if (allFiles.length > 0) {
                documentImportService.logMessage(allFiles.length + " files found ", event);

                for (FTPFile file : allFiles) {
                    // if this is a directory or symlink then we do ignore this entry
                    if (!file.isFile()) {
                        documentImportService.logMessage(
                                "'" + file.getName() + "' os not a valid file, object will be ignored!",
                                event);
                        continue;
                    }
                    logger.info("import file " + file.getName() + "...");
                    String fullFileName = ftpPath + "/" + file.getName();
                    try (ByteArrayOutputStream is = new ByteArrayOutputStream();) {
                        ftpClient.retrieveFile(fullFileName, is);
                        byte[] rawData = is.toByteArray();
                        if (rawData != null && rawData.length > 0) {
                            logger.finest("......file '" + file.getName() + "' successfull read - bytes size = "
                                    + rawData.length);
                            // create new workitem
                            createWorkitem(event.getSource(), file.getName(), rawData);
                        documentImportService.logMessage("....imported '" + file.getName() + "'",event);
                            count++;
                        } else {
                            documentImportService.logMessage(
                                    "Warning - invalid file content '" + file.getName() + "' - file will be deleted!",
                                    event);
                        }
                        // finally delete the file....
                        ftpClient.deleteFile(fullFileName);
                    } catch (AccessDeniedException | ProcessingErrorException | PluginException | ModelException e) {

                        documentImportService.logMessage("FTP import failed: " + e.getMessage(),event);
                        event.setResult(DocumentImportEvent.PROCESSING_ERROR);
                        return;
                    }
                }
                documentImportService.logMessage(count + " new files imported.", event);
            } else {
                documentImportService.logMessage(ftpPath + "No files found, directory is empty", event);
            }

        } catch (IOException e) {
            logger.severe("FTP I/O Error: " + e.getMessage());
            int r = ftpClient.getReplyCode();
            logger.severe("FTP ReplyCode=" + r);

            documentImportService.logMessage("FTP file transfer failed (replyCode=" + r + ") : " + e.getMessage(),event);
            event.setResult(DocumentImportEvent.PROCESSING_ERROR);
            return;

        } finally {
            // do logout....
            try {

                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                documentImportService.logMessage("FTP file transfer failed: " + e.getMessage(),event);
                event.setResult(DocumentImportEvent.PROCESSING_ERROR);
                return;
            }
        }

        // completed
        event.setResult(DocumentImportEvent.PROCESSING_COMPLETED);

    }

    /**
     * Creates and processes a new workitem with a given filedata
     * 
     * @return
     * @throws ModelException
     * @throws PluginException
     * @throws ProcessingErrorException
     * @throws AccessDeniedException
     */
    public ItemCollection createWorkitem(ItemCollection source, String fileName, byte[] rawData)
            throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {
        ItemCollection workitem = new ItemCollection();
        workitem.model(source.getItemValueString(DocumentImportService.SOURCE_ITEM_MODELVERSION));
        workitem.task(source.getItemValueInteger(DocumentImportService.SOURCE_ITEM_TASK));
        workitem.event(source.getItemValueInteger(DocumentImportService.SOURCE_ITEM_EVENT));
        workitem.setWorkflowGroup(source.getItemValueString("workflowgroup"));

        // apply options if available....
        documentImportService.evalOptions(source, workitem);

        String contentType = MediaType.WILDCARD;
        if (fileName.toLowerCase().endsWith(".pdf")) {
            contentType = "Application/PDF";
        }
        // set file data
        FileData fileData = new FileData(fileName, rawData, contentType, null);
        workitem.addFileData(fileData);
        workitem = workflowService.processWorkItemByNewTransaction(workitem);

        return workitem;
    }

}
