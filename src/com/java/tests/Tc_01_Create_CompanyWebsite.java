package com.java.tests;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.java.main.Commonmethods;
import com.java.main.Utility;
import com.java.main.WebgenericActions;
import com.java.utility.ExcelUtilities;

public class Tc_01_Create_CompanyWebsite {

	public static WebgenericActions gen;

	public static void main(String[] args) throws Exception {

		System.out.println("Execution started ");

		Utility us= new Utility();
		
		HashMap<String, String>credentialsData=Utility.getPropertiesData("Production");
		

		Commonmethods cm = new Commonmethods();

		Commonmethods.launchApplication(credentialsData.get("browser"), credentialsData.get("url"));

		gen = new WebgenericActions();
		

		startHere_CRM();
		Login_Into_Free_CRM_Application(credentialsData.get("Username"), credentialsData.get("password"));
		create_Company_Website();
	}

	public static void startHere_CRM() throws Exception {

		WebgenericActions.click("xpath", "//a[@href=\"https://ui.cogmento.com/register/\"]");

	}

	public static void Login_Into_Free_CRM_Application(String userName, String password) {

		WebgenericActions.enterText("xpath", "(//input[@name=\"email\"])[1]", userName);

		WebgenericActions.enterText("xpath", "(//input[@name=\"password\"])[1]", password);

		WebgenericActions.click("xpath", "(//div[text()='Login'])[1]");

		WebgenericActions.exist("xpath", "(//span[text()='Sanan Smileysanan'])[1]");

	}
	
	
	public static void create_Company_Website() {
		
		WebgenericActions.hoveronElement("xpath", "(//a[@href=\"/companies\"])[1]");
		WebgenericActions.click("xpath", "(//a[@href=\"/companies\"])[1]//following::button[1]");
		
		
		WebgenericActions.enterText("xpath", "(//label[text()='Name']//following::input[@name=\"name\"][1])[1]", "Chandrasekhar");
		
		WebElement accessType=gen.driver.findElement(By.xpath("//label[text()='Access']//following::button[1]"));
		
		
		if(accessType.getText().trim().equals("Public")) {
			
			System.out.println(" Access Type : "+accessType.getText().trim());
			
			accessType.click();
		}
		
		WebgenericActions.click("xpath", "((//div[text()='Select users allowed access.'])[1]//following::i[1])[1]");
		
		
		WebgenericActions.click("xpath", "//div[@class=\"visible menu transition\"]/div/span[text()='Sanan Smileysanan'][1]");

		
		WebgenericActions.enterText("xpath", "(//input[@placeholder=\"Street Address\"])[1]", "Dharmavaram");
		
		WebgenericActions.enterText("xpath", "(//input[@placeholder=\"City\"])[1]", "ATP");
		
		WebgenericActions.enterText("xpath", "(//input[@placeholder=\"State / County\"])[1]", "AP");

		WebgenericActions.enterText("xpath", "(//input[@placeholder=\"Post Code\"])[1]", "515672");
		
		WebgenericActions.scrollIntoViewElement("xpath", "//input[@name=\"num_employees\"][1]");
		
		WebgenericActions.enterText("xpath", "//input[@name=\"num_employees\"][1]", "50");

		WebgenericActions.scrollIntoViewElement("xpath", "//input[@name=\"annual_revenue\"][1]");
		
		WebgenericActions.enterText("xpath", "//input[@name=\"annual_revenue\"][1]", "1000");

		WebgenericActions.click("xpath", "//label[text()='Status']//following::div[1]/i[1]");
		
		WebgenericActions.click("xpath", "//label[text()='Status']//following::div[1]/i[1]/following-sibling::div/div/span[text()='New']");



	}

}
