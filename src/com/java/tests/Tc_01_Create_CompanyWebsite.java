package com.java.tests;

import java.util.HashMap;

import com.java.main.Commonmethods;
import com.java.main.Globalvariables;
import com.java.main.Utility;
import com.java.main.WebgenericActions;

public class Tc_01_Create_CompanyWebsite {

	public static WebgenericActions gen;

	public static void main(String[] args) throws Exception {

		System.out.println("Execution started ");

		Utility us= new Utility();
		
		HashMap<String, String>credentialsData=us.getPropertiesData("Production");
		

		Commonmethods cm = new Commonmethods();

		cm.launchApplication(credentialsData.get("browser"), credentialsData.get("url"));

		gen = new WebgenericActions();

		startHere_CRM();
		Login_Into_Free_CRM_Application(credentialsData.get("Username"), credentialsData.get("password"));
		create_Company_Website();
	}

	public static void startHere_CRM() throws Exception {

		gen.click("xpath", "//a[@href=\"https://ui.cogmento.com/register/\"]");

	}

	public static void Login_Into_Free_CRM_Application(String userName, String password) {

		gen.enterText("xpath", "(//input[@name=\"email\"])[1]", userName);

		gen.enterText("xpath", "(//input[@name=\"password\"])[1]", password);

		gen.click("xpath", "(//div[text()='Login'])[1]");

		gen.exist("xpath", "(//span[text()='Sanan Smileysanan'])[1]");

	}
	
	
	public static void create_Company_Website() {
		
		gen.hoveronElement("xpath", "(//a[@href=\"/companies\"])[1]");
		gen.click("xpath", "(//a[@href=\"/companies\"])[1]//following::button[1]");
		
		gen.enterText("xpath", "(//input[@placeholder=\"Street Address\"])[1]", "Dharmavaram");
		
		gen.enterText("xpath", "(//input[@placeholder=\"City\"])[1]", "ATP");
		
		gen.enterText("xpath", "(//input[@placeholder=\"State / County\"])[1]", "AP");

		gen.enterText("xpath", "(//input[@placeholder=\"Post Code\"])[1]", "515672");


	}

}
