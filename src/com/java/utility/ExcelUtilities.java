package com.java.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	
	public  HashMap<String, HashMap<String, String>> readDatafromExcel(String filepath, String sheetName)
			throws Exception {

		HashMap<String, HashMap<String, String>> allrowsData = new HashMap<String, HashMap<String, String>>();

		try {

			File f = new File(filepath);

			if (f.exists()) {

				FileInputStream fis = new FileInputStream(f);

				XSSFWorkbook wb = new XSSFWorkbook(fis);

				XSSFSheet sh = wb.getSheet("Sheet1");

				int lastRow = sh.getLastRowNum();

				ArrayList<String> headers = new ArrayList<String>();

				XSSFRow headersRow = sh.getRow(0);

				for (Cell cell : headersRow) {

					headers.add(cell.getStringCellValue());
				}

				for (int l = 1; l <= lastRow; l++) {

					XSSFRow row = sh.getRow(l);

					HashMap<String, String> rowData = new HashMap<String, String>();

					String testcaseID = null;

					for (int i = 0; i <= row.getLastCellNum(); i++) {

						if (row.getCell(i) != null) {

							Cell cell = row.getCell(i);

							String values = getCellValue(cell);
						//	System.out.println(values);

							rowData.put(headers.get(i), values);

							if (i == 0) {

								testcaseID = values;
							}
						}

					}

					if (testcaseID != null) {

						allrowsData.put(testcaseID, rowData);

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}

		return allrowsData;

	}

	private static String getCellValue(Cell cell) {

		switch (cell.getCellType()) {
		case STRING:

			return cell.getStringCellValue();

		case NUMERIC:

			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {

				return String.valueOf(cell.getNumericCellValue());
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
