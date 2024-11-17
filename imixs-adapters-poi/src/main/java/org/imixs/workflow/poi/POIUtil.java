package org.imixs.workflow.poi;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * The POIUtil is a helper class to insert columns and rows into a given
 * template.
 * 
 * @version 1.0
 * @author rsoika
 */
public class POIUtil {

    private static Logger logger = Logger.getLogger(POIUtil.class.getName());

    /**
     * Insert n rows in a XSSFSheet from a given cell reference.
     * 
     * @param sheet
     * @param cellReference
     * @param numberOfRows
     */
    public static void insertRows(XSSFSheet sheet, String cellReference, int numberOfRows) {

        CellReference refCell = new CellReference(cellReference);
        XSSFRow refRow = sheet.getRow(refCell.getRow());

        int startRow = refRow.getRowNum();
        // logger.info("---- start Row=" + startRow);

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

        logger.info("---- start Column=" + startColumn);

        // // Find the last column by checking all rows
        int lastColumn = -1;
        for (Row row : sheet) {
            if (row.getLastCellNum() > lastColumn) {
                lastColumn = row.getLastCellNum() - 1;
            }
        }

        // Store original column widths before shifting
        Map<Integer, Integer> columnWidths = new HashMap<>();
        for (int i = startColumn; i <= endColumn; i++) {
            columnWidths.put(i, sheet.getColumnWidth(i));
            logger.info("  column width " + i + " = " + sheet.getColumnWidth(i));
        }

        sheet.shiftColumns(startColumn, lastColumn, 1);

        // Restore column widths for shifted columns
        for (int i = startColumn + 1; i <= endColumn + 1; i++) {
            Integer width = columnWidths.get(i - 1);
            logger.info("  restore width " + i + " = " + width);
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
     * Insert a columns in a XSSFSheet from a given start column (beginning by 0).
     * The end column defines the last column in the sheet to be shifted.
     * 
     * @param sheet
     * @param cellReference
     * @param numberOfColumns
     */
    public static void insertColumnSuperTolll(XSSFSheet sheet, int startColumn, int endColumn) {

        // Convert cell reference to column index
        // CellReference refCellStart = new CellReference(cellReferenceStart);
        // CellReference refCellEnd = new CellReference(cellReferenceEnd);
        // int startColumn = refCellStart.getCol();
        // // int lastColumn = refCellEnd.getCol();

        logger.info("---- start Column=" + startColumn);

        // lastColumn = 16000;
        // // Find the last column by checking all rows
        int lastColumn = -1;
        for (Row row : sheet) {
            if (row.getLastCellNum() > lastColumn) {
                lastColumn = row.getLastCellNum() - 1;
            }
        }

        // lastColumn = 100;
        // Only proceed if there are columns to shift
        // if (lastColumn >= startColumn) {
        // Shift columns to the right
        sheet.shiftColumns(startColumn, lastColumn, 1);
        // }

        // Create CellCopyPolicy
        CellCopyPolicy copyPolicy = new CellCopyPolicy.Builder()
                .cellStyle(true)
                .cellFormula(true)
                .mergedRegions(false) // Merging is already handled by shiftColumns
                .build();

        // Copy formatting from reference column to new columns
        // for (Row row : sheet) {
        // if (row != null) {
        // XSSFCell refCell = ((XSSFRow) row).getCell(startColumn + 1);
        // if (refCell != null) {
        // for (int i = startColumn; i < startColumn + 1; i++) {
        // XSSFCell newCell = ((XSSFRow) row).createCell(i);
        // newCell.copyCellFrom(refCell, copyPolicy);
        // }
        // }
        // }
        // }

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

    public static void insertColumnGehtNoch(XSSFSheet sheet, int startColumn, int endColumn) {

        // Convert cell reference to column index
        // CellReference refCellStart = new CellReference(cellReferenceStart);
        // CellReference refCellEnd = new CellReference(cellReferenceEnd);
        // int startColumn = refCellStart.getCol();
        // // int lastColumn = refCellEnd.getCol();

        logger.info("---- start Column=" + startColumn);

        // lastColumn = 16000;
        // // Find the last column by checking all rows
        int lastColumn = -1;
        for (Row row : sheet) {
            if (row.getLastCellNum() > lastColumn) {
                lastColumn = row.getLastCellNum() - 1;
            }
        }

        // lastColumn = 100;
        // Only proceed if there are columns to shift
        // if (lastColumn >= startColumn) {
        // Shift columns to the right
        sheet.shiftColumns(startColumn, lastColumn, 1);
        // }

        // Create CellCopyPolicy
        CellCopyPolicy copyPolicy = new CellCopyPolicy.Builder()
                .cellStyle(true)
                .cellFormula(true)
                .mergedRegions(false) // Merging is already handled by shiftColumns
                .build();

        // Copy formatting from reference column to new columns
        for (Row row : sheet) {
            if (row != null) {
                XSSFCell refCell = ((XSSFRow) row).getCell(startColumn + 1);
                if (refCell != null) {
                    for (int i = startColumn; i < startColumn + 1; i++) {
                        XSSFCell newCell = ((XSSFRow) row).createCell(i);
                        newCell.copyCellFrom(refCell, copyPolicy);
                    }
                }
            }
        }
    }

}