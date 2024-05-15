package org.nibejpi.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nibejpi.app.constant.Constants;

/**
 * Utility class for working with Excel files.
 */
public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    /**
     * Set the Excel file to work with.
     *
     * @param Path      The path of the Excel file.
     * @param SheetName The name of the sheet in the Excel file.
     * @throws Exception If an error occurs while setting the Excel file.
     */
    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            System.out.println("Current Excel Sheet: " + SheetName);
        } catch (Exception e) {
            throw new Exception("Error while setting Excel file: " + e.getMessage(), e);
        }
    }

    /**
     * Read data from the specified cell in the Excel file.
     *
     * @param RowNum The row number of the cell.
     * @param ColNum The column number of the cell.
     * @return The data in the specified cell as a string.
     * @throws Exception If an error occurs while reading the cell data.
     */
    
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String cellData = "";

            if (Cell != null) {
                // Check the cell type and handle accordingly
                if (Cell.getCellType() == CellType.STRING) {
                    cellData = Cell.getRichStringCellValue().getString();
                } else if (Cell.getCellType() == CellType.NUMERIC) {
                    // Format the numeric value as an integer
                    cellData = String.format("%.0f", Cell.getNumericCellValue());
                }
            }
            System.out.println("Read from Excel - Row: " + RowNum + ", Column: " + ColNum + " - Data: " + cellData);
            return cellData;
        } catch (Exception e) {
            throw new Exception("Error while reading cell data: " + e.getMessage(), e);
        }
    }


    /**
     * Write data to the specified cell in the Excel file.
     *
     * @param Result  The data to write to the cell.
     * @param RowNum  The row number of the cell.
     * @param ColNum  The column number of the cell.
     * @throws Exception If an error occurs while writing the cell data.
     */
    public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            if (Row == null) {
                Row = ExcelWSheet.createRow(RowNum);
            }
            Cell = Row.createCell(ColNum);
            Cell.setCellValue(Result);
            System.out.println("Write to Excel - Row: " + RowNum + ", Column: " + ColNum + " - Data: " + Result);

            // Save the changes to the Excel file
            FileOutputStream fileOut = new FileOutputStream(Constants.PRO_TEST_DATA);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw new Exception("Error while writing cell data: " + e.getMessage(), e);
        }
    }

    public static int getRowCount() {
        return ExcelWSheet.getPhysicalNumberOfRows();
    }

    public static int getColumnCount() {
        return ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
    }
}
