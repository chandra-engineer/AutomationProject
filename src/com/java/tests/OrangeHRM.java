package com.java.tests;

import org.openqa.selenium.WebDriver;

import com.java.main.Commonmethods;
import com.java.main.Globalvariables;
import com.java.main.Utility;
import com.java.main.WebgenericActions;
import com.java.utility.ExcelUtilities;
import com.java.utility.ExtendedReporter;
import com.java.utility.JsonUtility;

import java.util.HashMap;

public class OrangeHRM {

	public static WebgenericActions gen;
	public static HashMap<String, String> OrangeHRMTestData;
	static JsonUtility js;
	static JsonUtility homepage;
	public static ExtendedReporter reporter;

	public static void main(String[] args) throws Exception {

		System.out.println("Execution started ");
		String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
		reporter = new ExtendedReporter(reportPath);

		Utility us = new Utility();
		HashMap<String, String> credentialsData = Utility.getPropertiesData("UAT");

		Commonmethods cm = new Commonmethods();
		ExcelUtilities excelData = new ExcelUtilities("OrangeHRM", "AddEmployee");
		HashMap<String, HashMap<String, String>> Testdata = excelData.readDatafromExcel();

		OrangeHRMTestData = Testdata.get("TC003");

		js = new JsonUtility("LoginPage", "loginPage");

		reporter.startTest("Create Account in OrangeHRM");

		Commonmethods.launchApplication(credentialsData.get("browser"), credentialsData.get("url"));
		reporter.setDriver(Globalvariables.driver); // Set driver after application launch

		reporter.logStep("Launched application with URL: " + credentialsData.get("url"));

		login_Into_OrangeHRM_Application(credentialsData.get("Username"), credentialsData.get("password"));
		navigateToPIMModule();
		addEmployee();
		addPersonalDetails();

		reporter.logTestSuccess("Create Account in OrangeHRM");

		reporter.startTest("Create Account in OrangeHRM2");
		reporter.logStep("Successfully logged out into OrangeHRM Application");

		reporter.logTestSuccess("Create Account in OrangeHRM2");

		reporter.flushReports();
	}

	public static void login_Into_OrangeHRM_Application(String userName, String password) {
		try {
			reporter.logStep("Logging into OrangeHRM with Username: " + userName);
			WebgenericActions.enterText(js.getlocators("userName")[0], js.getlocators("userName")[1], userName);
			WebgenericActions.enterText(js.getlocators("password")[0], js.getlocators("password")[1], password);
			WebgenericActions.click(js.getlocators("loginButton")[0], js.getlocators("loginButton")[1]);

			homepage = new JsonUtility("LoginPage", "homepage");
			WebgenericActions.exist(homepage.getlocators("dashboard")[0], homepage.getlocators("dashboard")[1]);
			reporter.logStep("Successfully logged into OrangeHRM Application");

		} catch (Exception e) {
			reporter.logTestFailure("Login to OrangeHRM");
			e.printStackTrace();
		}
	}

	public static void navigateToPIMModule() {
		reporter.logStep("Navigating to PIM Module");
		WebgenericActions.click(homepage.getlocators("PIM")[0], homepage.getlocators("PIM")[1]);
		reporter.logStep("Successfully navigated to PIM Module");
	}

	public static void addEmployee() {
		JsonUtility addemployess = new JsonUtility("LoginPage", "addEmployee");
		JsonUtility generic = new JsonUtility("LoginPage", "generic");

		reporter.logStep("Adding a new employee");
		WebgenericActions.exist(generic.getlocators("add")[0], generic.getlocators("add")[1]);
		WebgenericActions.click(generic.getlocators("add")[0], generic.getlocators("add")[1]);

		WebgenericActions.enterText(addemployess.getlocators("FirstName")[0], addemployess.getlocators("FirstName")[1],
				OrangeHRMTestData.get("First Name"));
		WebgenericActions.enterText(addemployess.getlocators("MiddleName")[0],
				addemployess.getlocators("MiddleName")[1], OrangeHRMTestData.get("Middle Name"));
		WebgenericActions.enterText(addemployess.getlocators("lastName")[0], addemployess.getlocators("lastName")[1],
				OrangeHRMTestData.get("Last Name"));
		WebgenericActions.click(generic.getlocators("save")[0], generic.getlocators("save")[1]);
		reporter.logStep("Successfully created a new employee");
	}

	public static void addPersonalDetails() {
		JsonUtility addemployess = new JsonUtility("LoginPage", "addEmployee");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			reporter.logStep("Waiting for page to load");
			e.printStackTrace();
		}

		reporter.logStep("Adding personal details for the employee");
		WebgenericActions.click(addemployess.getlocators("SelectNationality")[0],
				addemployess.getlocators("SelectNationality")[1]);

		String finalXpath = addemployess.getlocators("Nationality")[1].replace("#Replace", "Indian");
		WebgenericActions.click(addemployess.getlocators("Nationality")[0], finalXpath);
		reporter.logStep("Successfully added personal details");
	}
}
