package org.imixs.workflow.poi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.imixs.archive.core.SnapshotService;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.util.XMLParser;

/**
 * This POICopyAdapter can be used to find and extract text fragments from a MS
 * Excel (xlsx) document.
 * <p>
 * The adapter can be configured by the event workflow result:
 * <p>
 * 
 * <pre>
 * {@code
      <poi-copy name=
        "filename">PLA Membership Agreement-<itemvalue>numsequencenumber</itemvalue>.xlsx</poi-update>
        <poi-copy name="copy">
               <find>F:4</find>
               <item>invoice.total</item>
        </poi-copy>
        <poi-copy name="copy">
               <find>F:4</find>
               <item>invoice.total</item>
               <type>number</type>
        </poi-copy>
   }
 * </pre>
 * 
 * 
 * 
 * 
 * 
 * @version 1.0
 * @author rsoika
 */
public class POICopyContentAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(POICopyContentAdapter.class.getName());
    public static final String DOCUMENT_ERROR = "DOCUMENT_ERROR";
    public static final String CONFIG_ERROR = "CONFIG_ERROR";

    @Inject
    WorkflowService workflowService;

    @Inject
    SnapshotService snapshotService;

    @Inject
    ReportService reportService;

    /**
     * This method computes the findreplace configuration and updates an attachments
     * form type .docx
     * 
     * @throws PluginException
     */
    @SuppressWarnings("unchecked")
    @Override
    public ItemCollection execute(ItemCollection document, ItemCollection event)
            throws AdapterException, PluginException {

        // read the config
        ItemCollection poiConfig = workflowService.evalWorkflowResult(event, "poi-copy", document, false);
        if (poiConfig == null || !poiConfig.hasItem("copy")) {
            throw new PluginException(POICopyContentAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "wrong poi configuration");
        }
        String fileName = poiConfig.getItemValueString("filename");
        fileName = workflowService.adaptText(fileName, document);

        if (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx")) {
            throw new PluginException(POICopyContentAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "Only .xls and .xlsx files are supported!");
        }

        List<String> copyList = poiConfig.getItemValue("copy");
        // String eval = poiConfig.getItemValueString("eval");
        if (copyList.size() == 0) {
            throw new PluginException(POICopyContentAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "wrong poi configuration - missing 'copy' definition");
        }

        logger.fine("......starting copy file data from: " + fileName + "...");
        // XWPFDocument doc = null;
        try {

            // First we test if the fileName is unique. If not found we test regular
            // expressions...
            boolean foundFile = false;
            FileData fileData = document.getFileData(fileName);
            if (fileData != null) {
                // file data found - so we can updated it....
                foundFile = true;
                copyFileData(fileData, document, copyList);
            } else {
                // not found, we can test regular expressions...
                List<String> fileNames = document.getFileNames();
                Pattern pattern = Pattern.compile(fileName);
                // get all fileNames....
                for (String aFileName : fileNames) {
                    // test if aFilename matches the pattern or the pattern is null
                    if (pattern.matcher(aFileName).find()) {
                        // fetch the file
                        fileData = document.getFileData(aFileName);
                        if (fileData != null) {
                            // file data found - so we can updated it....
                            foundFile = true;
                            copyFileData(fileData, document, copyList);
                        }

                    }
                }
            }

            // did we found at least one file
            if (foundFile == false) {
                throw new PluginException(POICopyContentAdapter.class.getSimpleName(), CONFIG_ERROR,
                        "wrong poi configuration - no file found matching '" + fileName + "' !");
            }

        } catch (IOException e) {
            throw new PluginException(POICopyContentAdapter.class.getSimpleName(), DOCUMENT_ERROR,
                    "doucment '" + fileName + "' not readable: " + e.getMessage());
        }

        return document;
    }

    /**
     * This helper method copy the content of a given excel document object with a
     * given itemList
     * <p>
     * 
     * 
     * @param fileData - the fileData object containing the text or calc file
     * @param itemList - list of text markers or cell positions to be replaced
     * @throws IOException
     * @throws PluginException
     * 
     */
    void copyFileData(FileData fileData, ItemCollection document, List<String> itemList)
            throws IOException, PluginException {

        String fileName = fileData.getName();
        if (fileData.getContent() == null || fileData.getContent().length < 3) {
            // load the snapshot
            fileData = snapshotService.getWorkItemFile(document.getUniqueID(), fileName);
        }
        InputStream imputStream = new ByteArrayInputStream(fileData.getContent());

        // xlsx files....
        if (fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx")) {
            XSSFWorkbook doc = new XSSFWorkbook(imputStream);
            logger.info("XSSFWorkbook loaded");
            // NOTE: we only take the first sheet !
            XSSFSheet sheet = doc.getSheetAt(0);
            for (String entityDev : itemList) {
                ItemCollection entityData = XMLParser.parseItemStructure(entityDev);
                if (entityData != null) {
                    String find = entityData.getItemValueString("find");
                    String item = entityData.getItemValueString("item");
                    String type = entityData.getItemValueString("type");
                    Object o = findCellValueXSSFSheet(doc, sheet, find, type);
                    document.setItemValue(item, o);
                }
            }
            logger.fine("copy completed");
            doc.close();
        }
    }

    /**
     * Helph method finds a given cell of a XSSFSheet and returns the value
     * 
     * @throws PluginException
     */
    private Object findCellValueXSSFSheet(XSSFWorkbook doc, XSSFSheet sheet, String cellName, String type)
            throws PluginException {

        logger.finest("find cell " + cellName);
        // first we test if the cellName is a named cell
        Name aNamedCell = doc.getName(cellName);
        if (aNamedCell != null) {
            // yes its a named cell so we need to get the referrer Formula
            logger.info("...resolving named cell = " + aNamedCell.getNameName());
            cellName = aNamedCell.getRefersToFormula();
            logger.info("...cell = " + cellName);
            // now we can find the cell by its ref
        }

        XSSFCell cell = POIFindReplaceAdapter.getCellByRef(sheet, cellName);
        if (cell != null) {
            try {
                Object cellValue;
                if ("date".equalsIgnoreCase(type)) {
                    cellValue = cell.getDateCellValue();

                } else if ("number".equalsIgnoreCase(type)) {
                    cellValue = cell.getNumericCellValue();
                } else {
                    cellValue = cell.getStringCellValue();
                }
                return cellValue;

            } catch (Exception e) {
                logger.warning("failed to read cell value: " + e.getMessage());
            }
        } else {
            logger.warning("......cell " + cellName + " not found! Please check your configuration.");
        }
        return null;
    }

}