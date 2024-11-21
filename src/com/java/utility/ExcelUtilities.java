package com.java.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static void main(String[] args) {
		
		try {
			readDatafromExcel("/Users/chandrasekhark/Workspace/AutomationProject/TestData/Create Account.xlsx", "Sheet1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readDatafromExcel(String filepath, String sheetName) throws Exception {

		try {
			File f = new File(filepath);

			if (f.exists()) {

				FileInputStream fis = new FileInputStream(f);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sh =wb.getSheet("Sheet1");
				
				int lastRow=sh.getLastRowNum();
				
				for(int l=0;l<=lastRow;l++) {
				
				XSSFRow row =sh.getRow(l);
				
				
				for(int i=0;i<=row.getLastCellNum();i++) {
					
					
					if(row.getCell(i)!=null) {
						String values=row.getCell(i).getStringCellValue();
						System.out.println(values);
					}
					
				}
				
				
			}
				
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
