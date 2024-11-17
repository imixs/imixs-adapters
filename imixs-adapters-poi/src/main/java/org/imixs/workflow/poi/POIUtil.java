package org.imixs.workflow.poi;

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
     * Insert n columns in a XSSFSheet from a given cell reference.
     * 
     * @param sheet
     * @param cellReference
     * @param numberOfColumns
     */
    public static void insertColumns(XSSFSheet sheet, String cellReference, int numberOfColumns) {
        // Convert cell reference to column index
        CellReference refCellStart = new CellReference(cellReference);
        int startColumn = refCellStart.getCol();

        // logger.info("---- start Column=" + startColumn);

        // Find the last column by checking all rows
        int lastColumn = -1;
        for (Row row : sheet) {
            if (row.getLastCellNum() > lastColumn) {
                lastColumn = row.getLastCellNum() - 1;
            }
        }

        // Only proceed if there are columns to shift
        if (lastColumn >= startColumn) {
            // Shift columns to the right
            sheet.shiftColumns(startColumn, lastColumn, numberOfColumns);
        }

        // Create CellCopyPolicy
        CellCopyPolicy copyPolicy = new CellCopyPolicy.Builder()
                .cellStyle(true)
                .cellFormula(true)
                .mergedRegions(false) // Merging is already handled by shiftColumns
                .build();

        // Copy formatting from reference column to new columns
        for (Row row : sheet) {
            if (row != null) {
                XSSFCell refCell = ((XSSFRow) row).getCell(startColumn + numberOfColumns);
                if (refCell != null) {
                    for (int i = startColumn; i < startColumn + numberOfColumns; i++) {
                        XSSFCell newCell = ((XSSFRow) row).createCell(i);
                        newCell.copyCellFrom(refCell, copyPolicy);
                    }
                }
            }
        }
    }

    public static void xxxinsertCells(XSSFSheet sheet, String cellReference, int numberOfColumns) {

        // Convert cell reference to column index
        CellReference refCellStart = new CellReference(cellReference);
        int startColumn = refCellStart.getCol();

        logger.info("---- start Column=" + startColumn);

        // Find the last column by checking all rows
        int lastColumn = -1;
        for (Row row : sheet) {
            if (row.getLastCellNum() > lastColumn) {
                lastColumn = row.getLastCellNum() - 1;
            }
        }

        // Only proceed if there are columns to shift
        if (lastColumn >= startColumn) {
            // Shift columns to the right
            sheet.shiftColumns(startColumn, lastColumn, numberOfColumns);
        }

        // Copy formatting from reference column to new columns
        for (Row row : sheet) {
            if (row != null) {
                XSSFCell refCell = ((XSSFRow) row).getCell(startColumn + numberOfColumns);
                for (int i = startColumn; i < startColumn + numberOfColumns; i++) {
                    XSSFCell newCell = ((XSSFRow) row).createCell(i);
                    if (refCell != null && refCell.getCellStyle() != null) {
                        newCell.setCellStyle(refCell.getCellStyle());
                    }
                }
            }
        }

    }

}