package com.java.tests;

import java.util.HashMap;

import com.java.main.Commonmethods;
import com.java.main.Utility;
import com.java.main.WebgenericActions;
import com.java.utility.ExcelUtilities;
import com.java.utility.JsonUtility;

public class TC002_CreateAccountInOrangeHRM {

	public static WebgenericActions gen;

	public static HashMap<String, String> OrangeHRMTestData;
	
	static JsonUtility js;

	public static void main(String[] args) throws Exception {

		System.out.println("Execution started ");

		Utility us = new Utility();

		HashMap<String, String> credentialsData = Utility.getPropertiesData("UAT");

		Commonmethods cm = new Commonmethods();

		ExcelUtilities excelData = new ExcelUtilities("OrangeHRM", "AddEmployee");

		HashMap<String, HashMap<String, String>> Testdata = excelData.readDatafromExcel();

		OrangeHRMTestData = Testdata.get("TC003");
		
		
		 js= new JsonUtility("LoginPage", "loginPage");
		
		
	

		Commonmethods.launchApplication(credentialsData.get("browser"), credentialsData.get("url"));

		login_Into_OrangeHRM_Application(credentialsData.get("Username"), credentialsData.get("password"));

	}
	
	
	

	public static void login_Into_OrangeHRM_Application(String userName, String password) {
		try {
			
			

			WebgenericActions.enterText(js.getlocators("userName")[0], js.getlocators("userName")[1], userName);

			WebgenericActions.enterText(js.getlocators("password")[0], js.getlocators("password")[1], password);

			WebgenericActions.click("xpath", "//button[text()=' Login ']");

			WebgenericActions.exist("xpath", "//h6[text()='Dashboard']");

			System.out.println(" Successfully logged into Orange HRM Application");

			navigateToPIMModule();
			addEmployee();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void navigateToPIMModule() {

		WebgenericActions.click("xpath", "//span[text()='PIM']");

		System.out.println(" Successfully Navigated to PIM Module");

	}

	public static void addEmployee() {

		WebgenericActions.exist("xpath", "(//button[text()=' Add '])[1]");

		WebgenericActions.click("xpath", "(//button[text()=' Add '])[1]");

		WebgenericActions.enterText("xpath", "//input[@name=\"firstName\"][1]", OrangeHRMTestData.get("First Name"));
		WebgenericActions.enterText("xpath", "//input[@name=\"middleName\"][1]", OrangeHRMTestData.get("Middle Name"));

		WebgenericActions.enterText("xpath", "//input[@name=\"lastName\"][1]", OrangeHRMTestData.get("Last Name"));

		WebgenericActions.click("xpath", "//button[text()=' Save '][1]");

		System.out.println(" Successfully Created Employee");

	}

}
