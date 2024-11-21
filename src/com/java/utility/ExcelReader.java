package com.java.utility;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public static void main(String[] args) {
        String excelFilePath = "/Users/chandrasekhark/Workspace/AutomationProject/TestData/Create Account.xlsx";
        String testCaseId = "TC001"; // Input Test Case ID
        
        try {
            Map<String, Map<String, String>> dataMap = readExcelData(excelFilePath);
            Map<String, String> testCaseData = dataMap.get(testCaseId);
            
            if (testCaseData != null) {
                System.out.println("Data for Test Case ID " + testCaseId + ": " + testCaseData);
            } else {
                System.out.println("Test Case ID " + testCaseId + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
        }
    }

    private static Map<String, Map<String, String>> readExcelData(String filePath) throws IOException {
        Map<String, Map<String, String>> dataMap = new HashMap<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            
            // Get headers from the first row
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IOException("Header row is missing in the Excel file.");
            }
            
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }
            
            // Read each subsequent row and map data
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                String testCaseId = null;

                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = getCellValue(cell);

                    if (j == 0) { // Assuming TestCaseID is the first column
                        testCaseId = cellValue;
                    }
                    rowData.put(headers.get(j), cellValue);
                }
                if (testCaseId != null) {
                    dataMap.put(testCaseId, rowData);
                }
            }
        }
        return dataMap;
    }

    private static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
