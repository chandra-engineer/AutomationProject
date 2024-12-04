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

	static JsonUtility homepage;

	public static void main(String[] args) throws Exception {

		System.out.println("Execution started ");

		Utility us = new Utility();

		HashMap<String, String> credentialsData = Utility.getPropertiesData("UAT");

		Commonmethods cm = new Commonmethods();

		ExcelUtilities excelData = new ExcelUtilities("OrangeHRM", "AddEmployee");

		HashMap<String, HashMap<String, String>> Testdata = excelData.readDatafromExcel();

		OrangeHRMTestData = Testdata.get("TC003");

		js = new JsonUtility("LoginPage", "loginPage");

		Commonmethods.launchApplication(credentialsData.get("browser"), credentialsData.get("url"));

		login_Into_OrangeHRM_Application(credentialsData.get("Username"), credentialsData.get("password"));
		navigateToPIMModule();
		addEmployee();
		addPersonalDetails();
	}

	public static void login_Into_OrangeHRM_Application(String userName, String password) {
		try {

			WebgenericActions.enterText(js.getlocators("userName")[0], js.getlocators("userName")[1], userName);

			WebgenericActions.enterText(js.getlocators("password")[0], js.getlocators("password")[1], password);

			WebgenericActions.click(js.getlocators("loginButton")[0], js.getlocators("loginButton")[1]);

			homepage = new JsonUtility("LoginPage", "homepage");

			WebgenericActions.exist(homepage.getlocators("dashboard")[0], homepage.getlocators("dashboard")[1]);

			System.out.println(" Successfully logged into Orange HRM Application");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void navigateToPIMModule() {

		WebgenericActions.click(homepage.getlocators("PIM")[0], homepage.getlocators("PIM")[1]);

		System.out.println(" Successfully Navigated to PIM Module");

	}

	public static void addEmployee() {

		JsonUtility addemployess = new JsonUtility("LoginPage", "addEmployee");

		JsonUtility generic = new JsonUtility("LoginPage", "generic");

		WebgenericActions.exist(generic.getlocators("add")[0], generic.getlocators("add")[1]);

		WebgenericActions.click(generic.getlocators("add")[0], generic.getlocators("add")[1]);

		WebgenericActions.enterText(addemployess.getlocators("FirstName")[0], addemployess.getlocators("FirstName")[1],
				OrangeHRMTestData.get("First Name"));
		WebgenericActions.enterText(addemployess.getlocators("MiddleName")[0],
				addemployess.getlocators("MiddleName")[1], OrangeHRMTestData.get("Middle Name"));

		WebgenericActions.enterText(addemployess.getlocators("lastName")[0], addemployess.getlocators("lastName")[1],
				OrangeHRMTestData.get("Last Name"));

		WebgenericActions.click(generic.getlocators("save")[0], generic.getlocators("save")[1]);

		System.out.println(" Successfully Created Employee");

	}

	public static void addPersonalDetails() {

		JsonUtility addemployess = new JsonUtility("LoginPage", "addEmployee");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebgenericActions.click(addemployess.getlocators("SelectNationality")[0],
				addemployess.getlocators("SelectNationality")[1]);

		String finalXpath = addemployess.getlocators("Nationality")[1].replace("#Replace", "Indian");

		WebgenericActions.click(addemployess.getlocators("Nationality")[0], finalXpath);

	}

}
