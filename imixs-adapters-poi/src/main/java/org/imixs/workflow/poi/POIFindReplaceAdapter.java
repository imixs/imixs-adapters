package org.imixs.workflow.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
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
 * This POIFindReplaceAdapter can be used to find and replace text fragements in
 * a MS Word (docx) document.
 * <p>
 * The adapter can be configured by the event workflow result:
 * <p>
 * 
 * <pre>
 * {@code
      <poi-update name=
        "filename">PLA Membership Agreement-<itemvalue>numsequencenumber</itemvalue>.docx</poi-update>
        <poi-update name="findreplace">
               <find>[company.name]</find>
               <replace><itemvalue>company.name</itemvalue></replace>
        </poi-update>
        <poi-update name="findreplace">
               <find>[company.country]</find>
               <replace><itemvalue>company.country</itemvalue></replace>
        </poi-update>
        <poi-update name="findreplace">
               <find>[contract.startdate]</find>
               <replace><itemvalue format=
        "EEE, MMM d, yyyy">contract.start</itemvalue></replace>
       </poi-update>
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
public class POIFindReplaceAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(POIFindReplaceAdapter.class.getName());
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
        ItemCollection poiConfig = workflowService.evalWorkflowResult(event, "poi-update", document, false);
        if (poiConfig == null || !poiConfig.hasItem("findreplace")) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "wrong poi configuration");
        }
        String fileName = poiConfig.getItemValueString("filename");
        fileName = workflowService.adaptText(fileName, document);

        if (!fileName.toLowerCase().endsWith(".docx") && !fileName.toLowerCase().endsWith(".xls")
                && !fileName.toLowerCase().endsWith(".xlsx")) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "Only .docx, .xls and .xlsx files are supported!");
        }
        List<String> replaceDevList = poiConfig.getItemValue("findreplace");
        String eval = poiConfig.getItemValueString("eval");
        if (replaceDevList.size() == 0) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "wrong poi configuration");
        }

        logger.fine("......starting update file: " + fileName + "...");
        // XWPFDocument doc = null;
        try {

            // First we test if the fileName is unique. If not found we test regular
            // expressions...
            boolean foundFile = false;
            FileData fileData = document.getFileData(fileName);
            if (fileData != null) {
                // file data found - so we can updated it....
                foundFile = true;
                updateFileData(fileData, document, replaceDevList,eval);
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
                            updateFileData(fileData, document, replaceDevList,eval);
                        }

                    }
                }
            }

            // did we found at least one file
            if (foundFile == false) {
                throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                        "wrong poi configuration - no file found matching '" + fileName + "' !");
            }

        } catch (IOException e) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), DOCUMENT_ERROR,
                    "doucment '" + fileName + "' not readable: " + e.getMessage());
        }

        return document;
    }

    /**
     * This helper method updates the content of a given FileData object with a
     * replaceDevList
     * <p>
     * The method verifies if the content of the file need to be loaded from the
     * snapshot
     * <p>
     * 
     * @param fileData       - the fileData object containing the text or calc file
     * @param replaceDevList - list of text markers or cell positions to be replaced
     * @param eval           - optional list of cell positions to be evaluated after
     *                       update (this is for XSSFWorkbooks only)
     * @throws IOException
     * @throws PluginException
     * 
     */
    void updateFileData(FileData fileData, ItemCollection document, List<String> replaceDevList, String eval)
            throws IOException, PluginException {
        byte[] newContent = null;

        String fileName = fileData.getName();
        if (fileData.getContent() == null || fileData.getContent().length < 3) {
            // load the snapshot
            fileData = snapshotService.getWorkItemFile(document.getUniqueID(), fileName);
        }
        InputStream imputStream = new ByteArrayInputStream(fileData.getContent());

        // docx files....
        if (fileName.toLowerCase().endsWith(".docx")) {
            XWPFDocument doc = new XWPFDocument(imputStream);

            logger.fine("XWPFDocument loaded");

            for (String entityDev : replaceDevList) {
                ItemCollection entityData = XMLParser.parseItemStructure(entityDev);

                if (entityData != null) {
                    String find = entityData.getItemValueString("find");
                    find = workflowService.adaptText(find, document);
                    String replace = entityData.getItemValueString("replace");
                    replace = workflowService.adaptText(replace, document);
                    replaceXWPFDocument(doc, find, replace);
                }
            }

            logger.fine("findreplace completed");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            doc.write(byteArrayOutputStream);
            doc.close();
            newContent = byteArrayOutputStream.toByteArray();

        }

        // xlsx files....
        if (fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx")) {
            XSSFWorkbook doc = new XSSFWorkbook(imputStream);

            logger.fine("XSSFWorkbook loaded");
            // NOTE: we only take the first sheet !
            XSSFSheet sheet = doc.getSheetAt(0);

            for (String entityDev : replaceDevList) {
                ItemCollection entityData = XMLParser.parseItemStructure(entityDev);

                if (entityData != null) {
                    String find = entityData.getItemValueString("find");
                    String replace = entityData.getItemValueString("replace");
                    replace = workflowService.adaptText(replace, document);
                    replaceXSSFSheet(sheet, find, replace);
                }
            }
            logger.fine("findreplace completed");
            // recalculate formulas
            if (eval!=null && !eval.isEmpty()) {
                // iterate over all cells to be evaluated
                String[] cellPositions=eval.split(";");
                for (String cellPos: cellPositions) {
                    evalXSSFSheet(doc,sheet,cellPos);
                }
                logger.fine("formula evualtion completed");
            }
            // doc.setForceFormulaRecalculation(true);

            //XSSFFormulaEvaluator.evaluateAllFormulaCells(doc);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            doc.write(byteArrayOutputStream);
            doc.close();
            newContent = byteArrayOutputStream.toByteArray();
        }

        FileData fileDataNew = new FileData(fileData.getName(), newContent, fileData.getContentType(), null);
        // update the fileData
        document.addFileData(fileDataNew);
        logger.fine("new document added");
    }

    /**
     * Helph method replaces a given cell of a XSSFSheet
     * 
     * @throws PluginException
     */
    private void replaceXSSFSheet(XSSFSheet sheet, String find, String replace) throws PluginException {
        logger.finest("update cell " + find);
        // this must bei a Cell definition! A:1
        if (find.indexOf(":") == -1) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "wrong replacemet configuration - cell position is expected in format 'A:1'!");
        }
        String[] cellPos = find.split(":");
        XSSFRow row = sheet.getRow(Integer.parseInt(cellPos[1]) - 1);
        XSSFCell cell = row.getCell(cellColumnToNumber(cellPos[0]));
        
        try {
            float f=Float.parseFloat(replace);
            cell.setCellValue(f);
        } catch (Exception e) {
            cell.setCellValue(replace);
            
        }
    }
    
    
    /**
     * Evaluates a given list of cells in a given XSWorkbook
     * @param doc
     * @param sheet
     * @param cell
     * @throws PluginException
     */
    private void evalXSSFSheet(XSSFWorkbook doc,XSSFSheet sheet, String cell) throws PluginException {
        FormulaEvaluator evaluator = doc.getCreationHelper().createFormulaEvaluator();      
        // this must bei a Cell definition! A:1
        if (cell.indexOf(":") == -1) {
            throw new PluginException(POIFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
                    "wrong eval configuration - cell position is expected in format 'A:1'!");
        }
        String[] cellPos = cell.split(":");
        XSSFRow row = sheet.getRow(Integer.parseInt(cellPos[1]) - 1);
        XSSFCell c = row.getCell(cellColumnToNumber(cellPos[0]));
        if (c.getCellType() == CellType.FORMULA) {
            logger.finest("...eval cell " + cell);
            try {
            CellType evalResult = evaluator.evaluateFormulaCell(c);
            if (evalResult== CellType.ERROR ) {
                logger.warning("...unable to evaluate cell " + cell);
            }
            } catch (Exception poie) {
                logger.warning("...failed to evaluate cell " + cell + " : "+poie.getMessage());
            }
        }
    }

    /**
     * Helph method replaces a given text in a XWPFDocument
     */
    private void replaceXWPFDocument(XWPFDocument doc, String find, String replace) {

        for (XWPFParagraph p : doc.getParagraphs()) {
            replaceParagraph(p, find, replace);
        }

        // Now check vor Tables
        for (XWPFTable t : doc.getTables()) {
            List<XWPFTableRow> rows = t.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {

                    // check all paragraphs within the cell
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        replaceParagraph(p, find, replace);
                    }

                }
            }
        }

    }

    /**
     * helper method replacees a text within a single paragraph
     * 
     * @param p
     * @param find
     * @param replace
     */
    void replaceParagraph(XWPFParagraph p, String find, String replace) {
        List<XWPFRun> runs = p.getRuns();
        if (runs != null) {
            for (XWPFRun r : runs) {
                String text = r.getText(0);
                if (text != null && text.contains(find)) {
                    text = text.replace(find, replace);
                    logger.fine("..replace " + find + " with  " + replace);
                    r.setText(text, 0);
                }
            }
        }
    }

    /**
     * Converts a Excel column string to row number startng with 0
     * 
     * @param name
     * @return
     */
    static int cellColumnToNumber(String name) {
        name = name.toUpperCase();
        int number = 0;
        for (int i = 0; i < name.length(); i++) {
            number = number * 26 + (name.charAt(i) - ('A' - 1));
        }
        return number - 1;
    }
}