package org.imixs.workflow.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.util.XMLParser;

/**
 * The POIXSSFUtil is a helper class to insert columns and rows into a
 * XSSFWorkbook
 * 
 * @version 1.0
 * @author rsoika
 */
public class XSSFUtil {

    private static Logger logger = Logger.getLogger(XSSFUtil.class.getName());
    public static final String ERROR_CONFIG = "CONFIG_ERROR";

    /**
     * Insert n rows in a XSSFSheet from a given cell reference.
     * 
     * @param sheet
     * @param cellReference
     * @param numberOfRows
     */
    public static void insertRows(XSSFSheet sheet, String cellReference, int numberOfRows) {

        if (sheet == null || numberOfRows == 0 || cellReference == null || cellReference.isEmpty()) {
            // no op
            return;
        }
        CellReference refCell = new CellReference(cellReference);
        XSSFRow refRow = sheet.getRow(refCell.getRow());

        int startRow = refRow.getRowNum();

        // copy rows
        sheet.shiftRows(startRow, sheet.getLastRowNum(), numberOfRows, true, true);

        // copy formatting
        int rowPos = startRow;
        for (int i = startRow; i < startRow + numberOfRows; i++) {
            XSSFRow row = sheet.createRow(rowPos);
            row.copyRowFrom(refRow, new CellCopyPolicy());
            rowPos++;
        }

    }

    /**
     * Inserts one column in a XSSFSheet before a given start column (beginning
     * by 0).
     * The end column defines the last column in the sheet to be shifted (which is
     * the last column in the sheet).
     * 
     * @param sheet
     * @param startColumn - column before the new column should be inserted
     * @param endColumn   - last column in the sheet
     */
    public static void insertColumn(XSSFSheet sheet, int startColumn, int endColumn) {

        if (sheet == null || endColumn == 0) {
            // no op
            return;
        }

        logger.fine("start Column=" + startColumn);

        // // Find the last column by checking all rows
        int lastColumn = -1;
        for (Row row : sheet) {
            if (row.getLastCellNum() > lastColumn) {
                lastColumn = row.getLastCellNum() - 1;
            }
        }

        logger.fine("last  Column=" + lastColumn);
        if (lastColumn > 1000) { // oder eine andere sinnvolle Grenze
            logger.warning("Excel Template could be corrupted! - Unexpected last lastColumn: " + lastColumn);
            // Eventuell alternative Strategie anwenden oder Warnung ausgeben
        }

        // Store original column widths before shifting
        Map<Integer, Integer> columnWidths = new HashMap<>();
        for (int i = startColumn; i <= endColumn; i++) {
            columnWidths.put(i, sheet.getColumnWidth(i));
            logger.fine("  column width " + i + " = " + sheet.getColumnWidth(i));
        }

        sheet.shiftColumns(startColumn, lastColumn, 1);

        // Restore column widths for shifted columns
        for (int i = startColumn + 1; i <= endColumn + 1; i++) {
            Integer width = columnWidths.get(i - 1);
            logger.fine("  restore width " + i + " = " + width);
            sheet.setColumnWidth(i, width);
        }

        // Create CellCopyPolicy
        CellCopyPolicy copyPolicy = new CellCopyPolicy.Builder()
                .cellStyle(true)
                .cellFormula(true)
                .mergedRegions(false) // Merging is already handled by shiftColumns
                .build();

        for (Row row : sheet) {
            if (row != null) {
                XSSFCell refCell = ((XSSFRow) row).getCell(startColumn + 1);
                if (refCell != null) {
                    XSSFCell newCell = ((XSSFRow) row).createCell(startColumn);
                    newCell.copyCellFrom(refCell, copyPolicy);
                }
            }
        }
    }

    /**
     * Resolves column and row number into a excel reference.
     * 
     * @param col
     * @param row
     * @return
     */
    public static String getCellReference(int col, int row) {
        // convert column into letters
        StringBuilder colRef = new StringBuilder();
        int temp = col;
        while (temp >= 0) {
            int remainder = temp % 26;
            colRef.insert(0, (char) (65 + remainder)); // 65 ASCII for 'A'
            temp = (temp / 26) - 1;
        }
        // append row number starting at 1
        return colRef.toString() + (row + 1);
    }

    /**
     * Returns a Cell by name or an optional absolute cell postion
     * <p>
     * Examples for refs are 'A1', 'AB3', 'MyCell' where 'MyCell' is a named cell.
     * <p>
     *
     */
    public static XSSFCell getCellByRef(XSSFWorkbook doc, XSSFSheet sheet, String cellReference) {
        XSSFCell cell = null;

        // first we test if the cellName is a named cell
        Name aNamedCell = doc.getName(cellReference);
        if (aNamedCell != null) {
            // yes its a named cell so we need to get the referrer Formula
            logger.finest("...resolving named cell = " + aNamedCell.getNameName());
            cellReference = aNamedCell.getRefersToFormula();
            // now we can find the cell by its ref
        }

        CellReference cr = new CellReference(cellReference);
        XSSFRow row = sheet.getRow(cr.getRow());
        if (row == null) {
            logger.severe("Unable to resolve cell ref '" + cellReference + "'!");
            return null;
        }
        cell = row.getCell(cr.getCol());
        return cell;
    }

    /**
     * This method updates the XSSFWorkbook document. The method can be overwritten
     * by subclasses to add additional logic
     * 
     * @param workbook
     * @param workitem
     * @param replaceDevList
     * @throws PluginException
     */
    public static void updateXSSFWorkbook(XSSFWorkbook workbook, ItemCollection workitem, List<String> replaceDevList,
            WorkflowService workflowService)
            throws PluginException {

        logger.fine("XSSFWorkbook loaded");
        // NOTE: we only take the first sheet !
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (String entityDev : replaceDevList) {
            ItemCollection entityData = XMLParser.parseItemStructure(entityDev);

            if (entityData != null) {
                String find = entityData.getItemValueString("find");
                String replace = entityData.getItemValueString("replace");
                replace = workflowService.adaptText(replace, workitem);
                // optional itename
                String itemname = entityData.getItemValueString("itemname");

                // replace with item value?
                if (!itemname.isEmpty()) {
                    List<?> valueList = workitem.getItemValue(itemname);
                    if (valueList.size() > 0) {
                        // provide the first value only
                        replaceXSSFSheetItemValue(workbook, sheet, find, valueList.get(0));
                    }
                } else {
                    replaceXSSFSheetStringValue(workbook, sheet, find, replace);
                }

            }
        }
    }

    /**
     * Helper method replaces a given cell of a XSSFSheet with a typed item value
     * 
     * @throws PluginException
     */
    public static void replaceXSSFSheetItemValue(XSSFWorkbook doc, XSSFSheet sheet, String find, Object itemValue)
            throws PluginException {
        logger.finest("update cell " + find);
        XSSFCell cell = getCellByRef(doc, sheet, find);
        if (cell == null) {
            logger.warning("Cell " + find + " not found.");
            return;
        }
        if (itemValue instanceof Date) {
            cell.setCellValue((Date) itemValue);
        } else if (itemValue instanceof Double) {
            cell.setCellValue((Double) itemValue);
        } else {
            // default to text
            cell.setCellValue(itemValue.toString());
        }
    }

    /**
     * Helper method replaces a given cell of a XSSFSheet with a string value
     * 
     * @throws PluginException
     */
    private static void replaceXSSFSheetStringValue(XSSFWorkbook doc, XSSFSheet sheet, String find, String replace)
            throws PluginException {
        logger.finest("update cell " + find);
        XSSFCell cell = getCellByRef(doc, sheet, find);
        if (cell == null) {
            logger.warning("Cell " + find + " not found.");
            return;
        }
        try {
            // we try to set first as float value if possible
            float f = Float.parseFloat(replace);
            cell.setCellValue(f);
        } catch (NumberFormatException e) {
            // set value as string
            cell.setCellValue(replace);
        }
    }

    /**
     * Evaluates a given list of cells in a given XSWorkbook
     * 
     * @param doc
     * @param sheet
     * @param cell
     * @throws PluginException
     */
    public static void evalXSSFSheet(XSSFWorkbook doc, XSSFSheet sheet, String cell) throws PluginException {
        FormulaEvaluator evaluator = doc.getCreationHelper().createFormulaEvaluator();
        XSSFCell c = getCellByRef(doc, sheet, cell);
        if (c == null) {
            logger.warning("Cell " + cell + " not found.");
            return;
        }
        if (c.getCellType() == CellType.FORMULA) {
            logger.finest("...eval cell " + cell);
            try {
                CellType evalResult = evaluator.evaluateFormulaCell(c);
                if (evalResult == CellType.ERROR) {
                    logger.warning("...unable to evaluate cell " + cell);
                }
            } catch (Exception poie) {
                logger.warning("...failed to evaluate cell " + cell + " : " + poie.getMessage());
            }
        }
    }

    /**
     * This helper method applies the POI update defintiions a row for each invoice
     *
     * @throws PluginException
     */
    public static void poiUpdate(ItemCollection dataDefinition, FileData fileData,
            ItemCollection poiConfig, WorkflowService workflowService) throws PluginException {

        // update $modified for Now function
        dataDefinition.setItemValue("$modified", new Date());

        if (poiConfig == null || !poiConfig.hasItem("findreplace")) {
            // no config found
            return;
        }
        List<String> replaceDevList = poiConfig.getItemValue("findreplace");
        String eval = poiConfig.getItemValueString("eval");

        // load XSSFWorkbook
        try (InputStream imputStream = new ByteArrayInputStream(fileData.getContent())) {
            XSSFWorkbook workbook = new XSSFWorkbook(imputStream);
            // NOTE: we only take the first sheet !
            XSSFSheet sheet = workbook.getSheetAt(0);

            updateXSSFWorkbook(workbook, dataDefinition, replaceDevList, workflowService);

            // Update Eval list
            if (eval != null && !eval.isEmpty()) {
                // iterate over all cells to be evaluated
                String[] cellPositions = eval.split(";");
                for (String cellPos : cellPositions) {
                    evalXSSFSheet(workbook, sheet, cellPos);
                }
                logger.fine("formula evaluation completed");
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // write back the file
            workbook.write(byteArrayOutputStream);
            workbook.close();
            byte[] newContent = byteArrayOutputStream.toByteArray();
            fileData.setContent(newContent);

        } catch (IOException e) {
            throw new PluginException(XSSFUtil.class.getSimpleName(), ERROR_CONFIG,
                    "failed to update excel export: " + e.getMessage());
        }
    }

    /**
     * This helper method inserts a row for each invoice
     *
     * @throws PluginException
     */
    public static void insertDataRows(List<ItemCollection> dataset, String referenceCell,
            List<ItemCollection> viewItemDefinitions,
            FileData fileData) throws PluginException {
        // load XSSFWorkbook
        try (InputStream imputStream = new ByteArrayInputStream(fileData.getContent())) {
            XSSFWorkbook doc = new XSSFWorkbook(imputStream);
            // NOTE: we only take the first sheet !
            XSSFSheet sheet = doc.getSheetAt(0);

            CellReference cr = new CellReference(referenceCell);
            XSSFRow referenceRow = sheet.getRow(cr.getRow());
            int referenceRowPos = referenceRow.getRowNum() + 1;
            int rowPos = referenceRowPos;
            // int lastRow = sheet.getLastRowNum();
            int lastRow = 999;
            logger.finest("Last rownum=" + lastRow);
            sheet.shiftRows(rowPos, lastRow, dataset.size(), true, true);

            for (ItemCollection workitem : dataset) {
                logger.finest("......copy row...");

                // now create a new line..
                XSSFRow row = sheet.createRow(rowPos);
                row.copyRowFrom(referenceRow, new CellCopyPolicy());
                // insert values
                int cellNum = 0;
                for (ItemCollection itemDef : viewItemDefinitions) {
                    String type = itemDef.getItemValueString("item.type");
                    String name = itemDef.getItemValueString("item.name");
                    switch (type) {
                        case "xs:double":
                            row.getCell(cellNum).setCellValue(workitem.getItemValueDouble(name));
                            break;
                        case "xs:float":
                            row.getCell(cellNum).setCellValue(workitem.getItemValueFloat(name));
                            break;
                        case "xs:int":
                            row.getCell(cellNum).setCellValue(workitem.getItemValueInteger(name));
                            break;
                        case "xs:date":
                            row.getCell(cellNum).setCellValue(workitem.getItemValueDate(name));
                            break;

                        default:
                            row.getCell(cellNum).setCellValue(workitem.getItemValueString(name));
                    }
                    cellNum++;
                }

                rowPos++;
            }
            // delete reference row
            sheet.shiftRows(referenceRowPos, lastRow + dataset.size(), -1, true, true);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // write back the file
            doc.write(byteArrayOutputStream);
            doc.close();
            byte[] newContent = byteArrayOutputStream.toByteArray();
            fileData.setContent(newContent);

        } catch (IOException e) {
            throw new PluginException(XSSFUtil.class.getSimpleName(), ERROR_CONFIG,
                    "failed to update excel export: " + e.getMessage());
        }
    }

}