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

package org.imixs.workflow.datev.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.TransformerException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.datev.DatevHelper;
import org.imixs.workflow.datev.imports.DatevService;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.xml.XMLDataCollectionAdapter;
import org.imixs.workflow.xml.XSLHandler;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;

/**
 * Der DatevExportService stellt methoden für den Datev export bereit
 * <p>
 * Unter anderem werden metadaten berechnet.
 * <p>
 * _stapelzeitraum_start und _stapelzeitraum_ende markieren die äterste und die
 * jüngste Rechnung. Diese Daten weren für die DATEV Kopf Datei benötigt.
 * <p>
 * Change: 13.01.2021
 * <p>
 * Belegbilder dürfen immer nur einmal in der documents.xml drin stehen auch
 * wenn diese mehrfach in verschiedenne Buchungssätzen verwendet werden. Daher
 * prüfen wir beim Aufbau der documents.xml ob das belegbild schon generiert
 * wurde.
 * 
 * @author rsoika
 * @version 1.0
 */
@DeclareRoles({ "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Stateless
@RunAs("org.imixs.ACCESSLEVEL.MANAGERACCESS")
@LocalBean
public class DatevExportService {

    public static final String ITEM_DATEV_KONTENLAENGE = "datev.sachkontennummernlaenge";

    public static final String ITEM_FTP_HOST = "datev.ftp.host";
    public static final String ITEM_FTP_PORT = "datev.ftp.port";
    public static final String ITEM_FTP_USER = "datev.ftp.userid";
    public static final String ITEM_FTP_PASSWORD = "datev.ftp.password";
    public static final String ITEM_FTP_PATH_UPLOAD = "_datev.ftp.path.upload";
    public static final String ITEM_FTP_ERROR = "_ftp_error";

    public static final String ITEM_DATEV_CLIENT_ID = "datev.client.id";
    public static final String ITEM_DATEV_CLIENT_NAME = "datev.client.name";
    public static final String ITEM_DATEV_BOOKING_PERIOD = "datev.booking_period";
    public static final String ITEM_DATEV_CONSULTANT_ID = "datev.consultant.id";
    public static final String ITEM_DATEV_FISCAL_START = "datev.fiscal_start";

    // public static final String ITEM_DBTR_NAME = "dbtr.name";

    public static final String DEFAULT_CONFIG_NAME = "DATEV_CONFIGURATION";

    public static final String CHILD_ITEM_PROPERTY = "_ChildItems";

    public static final String REPORT_ERROR = "REPORT_ERROR";
    public static final String MODEL_ERROR = "MODEL_ERROR";

    @Inject
    WorkflowService workflowService;

    @Inject
    DocumentService documentService;

    @Inject
    ReportService reportService;

    private static Logger logger = Logger.getLogger(DatevExportService.class.getName());

    /**
     * This method Updates the export workitem
     * <p>
     * The datev client id is fetched from the first invoice of the invoice data
     * collection.
     * <p>
     * 
     * 
     * @param configuration
     * @param modelVersion
     * @param taskID
     * @param data
     * @return
     */
    public ItemCollection updateExportWorkitem(ItemCollection datevExport,
            ItemCollection configuration,
            List<ItemCollection> data) {

        // set _datev_fiscal_start (date), clientID and Sachkontenlaenge from first
        // invoice if available...
        if (data != null && data.size() > 0) {
            ItemCollection firstInvoice = data.get(0);
            if (firstInvoice.hasItem(ITEM_DATEV_FISCAL_START)) {
                datevExport.setItemValue(ITEM_DATEV_FISCAL_START,
                        firstInvoice.getItemValue(ITEM_DATEV_FISCAL_START));
                datevExport.setItemValue(ITEM_DATEV_CLIENT_ID,
                        firstInvoice.getItemValue(ITEM_DATEV_CLIENT_ID));
                // copy sachkontenlaenge from first invoice..
                datevExport.setItemValue(ITEM_DATEV_KONTENLAENGE,
                        firstInvoice.getItemValue(ITEM_DATEV_KONTENLAENGE));
            }
        }

        // haben wir bereits eine Consultant ID von der Rechnung erhalten?
        if (datevExport.getItemValueString(ITEM_DATEV_CONSULTANT_ID).isEmpty()) {
            // nein - wir haben keine also nehemen wir die Zetral gepflegte
            datevExport.setItemValue(ITEM_DATEV_CONSULTANT_ID,
                    configuration.getItemValue(ITEM_DATEV_CONSULTANT_ID));
        }
        return datevExport;
    }

    /**
     * This method builds the datev zip file containing the XML documents definition
     * and also all attachments. The zip-file is finally attached into the
     * datevExport workitem.
     * <p>
     * Change: 13.01.2021
     * <p>
     * Belegbilder dürfen immer nur einmal in der documents.xml drin stehen auch
     * wenn diese mehrfach in verschiedenne Buchungssätzen verwendet werden. Daher
     * prüfen wir beim Aufbau der documents.xml ob das belegbild schon generiert
     * wurde.
     * 
     * 
     * @param data
     * @return
     * @throws PluginException
     */
    public void buildDocumentsZipFile(ItemCollection datevExport, List<ItemCollection> data, String key,
            ItemCollection configuration) throws PluginException {
        int documentCount = 0;
        ZipOutputStream datevZip = null;
        ByteArrayOutputStream zipOutputStream = null;

        DatevHelper.logMessage(
                "... Document export started (ClientID="
                        + datevExport.getItemValueString(ITEM_DATEV_CLIENT_ID) + ") ...",
                configuration, datevExport);

        // load report configuration....
        String reportNameDocuments = configuration.getItemValueString("report.documents");
        ItemCollection documentsReport = reportService.findReport(reportNameDocuments);
        if (documentsReport == null) {
            throw new PluginException(this.getClass().getClass().getName(), REPORT_ERROR,
                    "unable to load documents report definition '" + reportNameDocuments
                            + "'. Please check the configuration");
        }

        try {
            // out put file
            zipOutputStream = new ByteArrayOutputStream();
            datevZip = new ZipOutputStream(zipOutputStream);

            String xslDocuments = documentsReport.getItemValueString("XSL").trim();
            if (xslDocuments.isEmpty()) {
                throw new PluginException(this.getClass().getClass().getName(), REPORT_ERROR,
                        "Failed to build DATEV zip archive '"
                                + documentsReport.getItemValueString("txtname") + " XSL content is missing.");
            }

            String encoding = documentsReport.getItemValueString("encoding");
            for (ItemCollection exportDataEntity : data) {
                // now we add the attachment.....
                // the attachment in this case in only available from the origin invoice and not
                // from the current data-export entity! So we need to lookup the origin invoice
                // first.

                FileData fileData = getWorkItemFileFromWorkitem(exportDataEntity);
                if (fileData != null) {
                    // 1) alte Datei entfernen
                    exportDataEntity.removeFile(fileData.getName());
                    // Change 27.9.2021 - der Dateiname wird auf UUID.pdf geändert, da DATEV mit
                    // Umlauten
                    // in Dateinamen nicht korrekt umgehen kann und es hier in DATEV zu Problemen
                    // kam.
                    // Siehe Mails Herr Spindler 27.9.2021
                    fileData.setName(exportDataEntity.getUniqueID() + ".pdf");

                    // es kann vorkommen das der selbe Beleg an zwei unterschiedlichen Rechnungen
                    // hängt. In diesem Fall fangen wir die ZIPException ab
                    try {
                        // name the file inside the zip file and add a new entry
                        datevZip.putNextEntry(new ZipEntry(fileData.getName()));
                        // write data and close entry
                        datevZip.write(fileData.getContent());
                        datevZip.closeEntry();
                        documentCount++;
                        // write log
                        DatevHelper.logMessage("......." + fileData.getName() + " added. ", configuration,
                                datevExport);
                    } catch (java.util.zip.ZipException ezip) {
                        // Date konnte nicht hinzugefügt werden!
                        DatevHelper.logMessage(
                                ".......WARNING : " + ezip.getClass().getSimpleName() + " -> " + ezip.getMessage(),
                                configuration, datevExport);
                    }

                    // zuletzt noch die Filedaten an das exportDataEntity element hängen.
                    // diese werden für die XSL Transformation benötigt!....
                    byte[] empty = {};
                    fileData.setContent(empty); // wir benötigen hier nur den namen und nicht den content
                    exportDataEntity.addFileData(fileData);

                }

            }

            // now we need to construct the document.xml file containing the attachment
            // information
            ByteArrayOutputStream outputStream = null;
            try {

                // Hier muss sichergestellt weden das Dateien nur einmal in die document.xml
                // exportiert werden,
                // da die GUID in der document.xml eindeutig sein muss.
                // Daher bauen wir eine zweite Kopie der Datenquelle auf und filtern duppleten
                // heraus (diese
                // können durchaus durch splitbuchungen vorkommen
                List<ItemCollection> uniqueDataSource = new ArrayList<ItemCollection>();
                List<String> idList = new ArrayList<String>();
                for (ItemCollection inovice : data) {
                    if (!idList.contains(inovice.getUniqueID())) {
                        uniqueDataSource.add(inovice);
                        idList.add(inovice.getUniqueID());
                    }
                }

                // byte[] xmlData = XMLDataCollectionAdapter.writeItemCollection(data);
                byte[] xmlData = XMLDataCollectionAdapter.writeItemCollection(uniqueDataSource);
                outputStream = new ByteArrayOutputStream();
                String xml = new String(xmlData);
                XSLHandler.transform(new String(xml), xslDocuments, encoding, outputStream);
                byte[] byteData = outputStream.toByteArray();

                // name the file inside the zip file and add a new entry
                datevZip.putNextEntry(new ZipEntry("document.xml"));
                // write data and close entry
                datevZip.write(byteData);
                datevZip.closeEntry();
            } catch (IOException | TransformerException | JAXBException e) {
                throw new PluginException(DatevExportService.class.getName(), REPORT_ERROR,
                        "Failed to build DATEV zip archive '"
                                + documentsReport.getItemValueString("txtname") + "' : " + e.getMessage(),
                        e);
            }

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmm");
            String datevFileName = "datev_documents_" + key + "_" + df.format(new Date()) + ".zip";

            datevZip.finish(); // good practice
            datevZip.close();
            zipOutputStream.close();
            FileData zipFileData = new FileData(datevFileName, zipOutputStream.toByteArray(), "application/zip", null);
            datevExport.addFileData(zipFileData);

        } catch (IOException e) {
            throw new PluginException(DatevExportService.class.getName(), REPORT_ERROR,
                    "Failed to create Documents archive '" + documentsReport.getItemValueString("txtname") + "' : "
                            + e.getClass().getName() + " -> " + e.getMessage(),
                    e);
        } finally {
            try {
                // try to close the streams (unclear if necessary here...)
                if (datevZip != null) {
                    datevZip.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                throw new PluginException(DatevExportService.class.getName(), REPORT_ERROR,
                        "Failed to close DATEV archive '"
                                + documentsReport.getItemValueString("txtname") + "' : " + e.getMessage(),
                        e);
            }

        }

        DatevHelper.logMessage("... Document export completed (ClientID="
                + datevExport.getItemValueString(ITEM_DATEV_CLIENT_ID) + ", " + documentCount
                + " documents)", configuration, datevExport);

    }

    /**
     * This method builds an DATEV CSV export file containing all given invoices
     * 
     * @param data
     * @return
     * @throws PluginException
     */
    public void buildCSVFile(ItemCollection datevExport, List<ItemCollection> data, String key,
            ItemCollection configuration) throws PluginException {
        String clientID = datevExport.getItemValueString(ITEM_DATEV_CLIENT_ID);

        // load the report for CSV export
        String reportNameInvoices = configuration.getItemValueString("report.invoices");

        DatevHelper.logMessage(
                "... CSV export started (ClientID="
                        + clientID + ") Report=" + reportNameInvoices + "...",
                configuration, datevExport);

        ItemCollection invoiceReport = null;
        invoiceReport = reportService.findReport(reportNameInvoices);
        if (invoiceReport == null) {
            throw new PluginException(DatevExportService.class.getName(), REPORT_ERROR,
                    "unable to load invoice report definition '"
                            + reportNameInvoices + "'. Please check the configuration");
        }

        // link invoices with export workitem and find the earliest invoice.date ...
        LocalDateTime stapelZeitraumStart = null;
        LocalDateTime stapelZeitraumEnde = null;
        for (ItemCollection invoice : data) {
            Date baseDate = invoice.getItemValueDate("accounting.date");
            if (baseDate == null) {
                // fallback auf invoice date
                baseDate = invoice.getItemValueDate("invoice.date");
            }
            LocalDateTime invoiceDate = new Date(baseDate.getTime()).toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            if (stapelZeitraumStart == null || stapelZeitraumStart.isAfter(invoiceDate)) {
                stapelZeitraumStart = invoiceDate;
            }
            if (stapelZeitraumEnde == null || invoiceDate.isAfter(stapelZeitraumEnde)) {
                stapelZeitraumEnde = invoiceDate;
            }
        }
        // update Stapelzeitruam begin und Ende
        if (stapelZeitraumStart != null) {
            // Begin stapelzeitrum
            datevExport.replaceItemValue("_stapelzeitraum_start",
                    Date.from(stapelZeitraumStart.atZone(ZoneId.systemDefault()).toInstant()));

            // Ende Stapelzeitraum
            datevExport.replaceItemValue("_stapelzeitraum_ende",
                    Date.from(stapelZeitraumEnde.atZone(ZoneId.systemDefault()).toInstant()));

        }
        // here we add a new temporal data collection in which the datevExport
        // ItemColleciton is added. This data collection is used to generate the csv
        // export.
        List<ItemCollection> tmp_data = new ArrayList<ItemCollection>();
        tmp_data.addAll(data);
        // finally we add the datev export document to the data collection
        tmp_data.add(datevExport);

        // create the attachment based on the report definition
        // write a file to workitem
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmm");
        String datevFileName = "EXTF_Buchungsstapel_" + key + "_" + df.format(new Date()) + ".csv";

        FileData filedata;
        try {
            // xsl transformation based on our tmp_data collection....
            filedata = reportService.transformDataSource(invoiceReport, tmp_data, datevFileName);
        } catch (JAXBException | TransformerException | IOException e) {
            throw new PluginException(DatevExportService.class.getName(), REPORT_ERROR, "Failed to execute CSV report '"
                    + invoiceReport.getItemValueString("txtname") + "' : " + e.getMessage(), e);
        }

        // attach the file
        // set content type
        filedata.setContentType("text/csv");
        datevExport.addFileData(filedata);

        // write log
        DatevHelper.logMessage("... CSV export completed (ClientID="
                + datevExport.getItemValueString(ITEM_DATEV_CLIENT_ID) + ", " + data.size()
                + " invoices)", configuration, datevExport);

    }

    /**
     * This method exports a fildata object to a ftp server defined by the
     * configuration ItemCollection.
     * <p>
     * Das Zielverzeichnis wird mit der MAndanten ID ergänzt!
     * 
     * @param timer
     * @throws QueryException
     */
    public boolean putFileData(FileData fileData, ItemCollection configuration, String subDirectory,
            ItemCollection datevExport, int ftpType) throws PluginException {

        boolean result = false;
        String ftpServer = configuration.getItemValueString(ITEM_FTP_HOST);
        String datevClientID = datevExport.getItemValueString(ITEM_DATEV_CLIENT_ID);

        // if no server is given we exit
        if (ftpServer.isEmpty()) {
            return false;
        }

        String ftpPort = configuration.getItemValueString(ITEM_FTP_PORT);
        if (ftpPort.isEmpty()) {
            ftpPort = "21";
        }
        String ftpUser = configuration.getItemValueString(ITEM_FTP_USER);
        String ftpPassword = configuration.getItemValueString(ITEM_FTP_PASSWORD);

        // Zielverzeichnis : TARGET/SUBFOLDER/MANDANT/
        String ftpPathReports = configuration.getItemValueString(ITEM_FTP_PATH_UPLOAD);
        String ftpParentPath = null;
        if (!ftpPathReports.startsWith("/")) {
            ftpPathReports = "/" + ftpPathReports;
        }
        if (!ftpPathReports.endsWith("/")) {
            ftpPathReports = ftpPathReports + "/";
        }

        ftpPathReports = ftpPathReports + subDirectory;
        if (!ftpPathReports.endsWith("/")) {
            ftpPathReports = ftpPathReports + "/";
        }
        ftpParentPath = ftpPathReports;

        ftpPathReports = ftpPathReports + datevClientID;
        if (!ftpPathReports.endsWith("/")) {
            ftpPathReports = ftpPathReports + "/";
        }

        InputStream writer = null;

        FTPClient ftpClient = null;
        try {
            logger.finest("......put " + fileData.getName() + " to FTP server: " + ftpServer + "...");
            // TLS Hetzner
            // https://wiki.hetzner.de/index.php/Storage_Boxes#FTP.2FFTPS
            ftpClient = new FTPSClient("TLS", false);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(ftpServer, Integer.parseInt(ftpPort));
            if (ftpClient.login(ftpUser, ftpPassword) == false) {
                throw new PluginException(DatevExportService.class.getName(), ITEM_FTP_ERROR,
                        "FTP file transfer failed: login failed!");
            }

            ftpClient.enterLocalPassiveMode();
            logger.finest("...... FileType=" + ftpType);
            ftpClient.setFileType(ftpType);
            ftpClient.setControlEncoding("UTF-8");
            // upload file to FTP server.

            writer = new ByteArrayInputStream(fileData.getContent());

            // try to create the subdirectory...

            FTPFile[] subDirectories = ftpClient.listDirectories(ftpParentPath);
            boolean bSubDirFound = false;
            for (FTPFile ftpFile : subDirectories) {
                if (ftpFile.isDirectory() && ftpFile.getName().equals(datevClientID)) {
                    bSubDirFound = true;
                    break;
                }
            }
            if (!bSubDirFound) {
                logger.info("... creating subdirectory " + datevClientID);
                if (ftpClient.makeDirectory(ftpPathReports)) {
                    logger.info("... subdirectory '" + datevClientID + "' created");
                }
            } else {
                logger.info("...  subdirectory " + datevClientID + " already exists");

            }

            result = ftpClient.storeFile(ftpPathReports + fileData.getName(), writer);

            if (result == true) {
                DatevHelper.logMessage(
                        "...FTP file transfer '" + ftpPathReports + fileData.getName() + "' successfull", configuration,
                        datevExport);
            } else {
                throw new PluginException(DatevExportService.class.getName(), ITEM_FTP_ERROR,
                        "FTP file transfer failed: unable to write '" + ftpPathReports + fileData.getName() + "'");
            }

        } catch (IOException e) {
            logger.severe("FTP I/O Error: " + e.getMessage());
            int r = ftpClient.getReplyCode();
            logger.severe("FTP ReplyCode=" + r);

            throw new PluginException(DatevExportService.class.getName(), ITEM_FTP_ERROR,
                    "FTP file transfer failed (replyCode=" + r + ") : " + e.getMessage(), e);
        } finally {
            // do logout....
            try {
                if (writer != null) {
                    writer.close();
                }
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new PluginException(DatevExportService.class.getName(), ITEM_FTP_ERROR,
                        "FTP file transfer failed: " + e.getMessage(), e);
            }
        }

        return result;

    }

    /**
     * This method builds the export workitem. This method is only used by the
     * IW24DatevImportControler!
     * <p>
     * The datev client id is fetched from the first invoice of the invoice data
     * collection.
     * <p>
     * Change 5.5.2022
     * 
     * Die Beraternummer kann nun auch auf Corporate Ebene gepflegt werden. Ist das
     * der Fall, dann gewinnt diese Nummer. Andernfalls wird weiterhin die
     * Beraternummer aus der Zentralen DATEV Config genommen.
     * 
     * @param configuration
     * @param modelVersion
     * @param taskID
     * @param data
     * @return
     */
    public ItemCollection buildExportWorkitem(ItemCollection configuration, String modelVersion, int taskID,
            List<ItemCollection> data) {
        ItemCollection datevExport;
        datevExport = new ItemCollection().model(modelVersion).task(taskID);
        datevExport.replaceItemValue(WorkflowKernel.CREATED, new Date());
        datevExport.replaceItemValue(WorkflowKernel.MODIFIED, new Date());
        // set unqiueid, needed for xslt
        datevExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());
        datevExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, "DATEV-Export");

        // set _datev_fiscal_start (date), clientID and Sachkontenlaenge from first
        // invoice if available...
        if (data != null && data.size() > 0) {
            ItemCollection firstInvoice = data.get(0);
            if (firstInvoice.hasItem(ITEM_DATEV_FISCAL_START)) {
                datevExport.setItemValue(ITEM_DATEV_FISCAL_START,
                        firstInvoice.getItemValue(ITEM_DATEV_FISCAL_START));
                datevExport.setItemValue(ITEM_DATEV_CLIENT_ID,
                        firstInvoice.getItemValue(ITEM_DATEV_CLIENT_ID));
                // copy sachkontenlaenge from first invoice..
                datevExport.setItemValue(ITEM_DATEV_KONTENLAENGE,
                        firstInvoice.getItemValue(ITEM_DATEV_KONTENLAENGE));

                // Copy Consulten ID if available (die nummer ist optional auf Corporate Ebene
                // gepflegt)
                datevExport.setItemValue(ITEM_DATEV_CONSULTANT_ID,
                        firstInvoice.getItemValue(ITEM_DATEV_CONSULTANT_ID));
            }
        }

        // haben wir bereits eine COnsultent ID von der Rechnung erhalten?
        if (datevExport.getItemValueString(ITEM_DATEV_CONSULTANT_ID).isEmpty()) {
            // nein - wir haben keine also nehemen wir die Zetral gepflegte
            datevExport.setItemValue(ITEM_DATEV_CONSULTANT_ID,
                    configuration.getItemValue(ITEM_DATEV_CONSULTANT_ID));
        }
        return datevExport;
    }

    /**
     * Loads the DATEV configuration entity by name. The method returns null if no
     * configuration exits.
     * 
     * @return
     */
    public ItemCollection loadConfiguration(String name) {
        try {
            // support deprecated txtname attribure
            String sQuery = "(type:\"" + DatevService.DOCUMENT_TYPE + "\" AND (name:\"" + name + "\" OR txtname:\""
                    + name + "\" ) )";
            Collection<ItemCollection> col = documentService.find(sQuery, 1, 0);
            // check if we found a configuration
            if (col.size() > 0) {
                ItemCollection configuration = col.iterator().next();
                return configuration;
            }
        } catch (QueryException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * Helper Method to copute the grouping key
     * 
     * (MandantID+Buchungsperiode)
     * 
     * @return
     */
    public String computeKey(ItemCollection invoice, String datevClientID) {

        Date datInvoice = invoice.getItemValueDate("invoice.date");

        // Berechnung der Buchungsperiode
        DateFormat df = new SimpleDateFormat("yyyyMM");
        String keyPeriode = df.format(datInvoice);
        String key = keyPeriode + "_" + datevClientID;

        return key;
    }

    /**
     * Prüft alle offenen Datev Exporte und gibt den neuesten zur angegebenen Datev
     * Client ID zurück, oder null falls es keinen Offenen Datev Export gibt.
     * 
     * @param datevKey
     * @return
     * @throws QueryException
     */
    public ItemCollection findDatevExport(String datevKey) throws QueryException {
        String query = "(type:workitem) AND ($taskid:1000) AND ($modelversion:datev-export-de-2*) ";
        List<ItemCollection> resultList = documentService.find(query, 999, 0, "$modified", true);

        for (ItemCollection export : resultList) {
            if (datevKey.equals(export.getItemValueString("name"))) {
                return export;
            }
        }
        // no datev export found
        return null;
    }

    /**
     * Aktualisiert den DATEV Export mit Manager Rechten
     * 
     * @param datevExport
     * @return
     * @throws ModelException
     * @throws PluginException
     * @throws ProcessingErrorException
     * @throws AccessDeniedException
     */
    public ItemCollection processDatevExport(ItemCollection datevExport)
            throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {
        return workflowService.processWorkItem(datevExport);
    }

    /**
     * This method first lookups the origin workItem from the given export
     * data entity. Then the method returns the first PDF fileData from a snapshot
     * by a
     * resolved invoice workItem.
     * <p>
     * If multiple files are attached, the method returns the latest FileData object
     * <p>
     * 
     * @param uniqueid
     * @param file     - file name
     * @return FileData object for the given filename.
     * @throws PluginException
     */
    public FileData getWorkItemFileFromWorkitem(ItemCollection invoice) throws PluginException {
        String snapshotID;

        if (invoice != null) {
            // we found the corresponding invoice workitem!

            // test if we have a $snapshotid
            snapshotID = invoice.getItemValueString("$snapshotid");
            ItemCollection snapshot = documentService.load(snapshotID);
            if (snapshot != null) {
                // return the latest fileData object
                FileData lastFileData = null;
                List<FileData> fileDataList = snapshot.getFileData();
                for (FileData fileData : fileDataList) {
                    // we are only interested in files with sufix .pdf
                    if (!fileData.getName().toLowerCase().endsWith(".pdf")) {
                        // not a PDF file...
                        continue;
                    }
                    if (lastFileData == null) {
                        lastFileData = fileData;
                    } else {
                        // compare the $creation date....
                        ItemCollection attributes = new ItemCollection(fileData.getAttributes());
                        ItemCollection lastAttributes = new ItemCollection(lastFileData.getAttributes());

                        Date date = attributes.getItemValueDate("$created");
                        Date lastDate = lastAttributes.getItemValueDate("$created");
                        if (date != null && lastDate != null && (date.compareTo(lastDate) > 0)) {
                            // this is a newer fileData!
                            lastFileData = fileData;
                        }
                    }
                }

                return lastFileData;
            }

        } else {
            throw new PluginException(DatevExportService.class.getName(), MODEL_ERROR,
                    "Invoice Document not defined");
        }

        // no file found!
        return null;
    }

}
