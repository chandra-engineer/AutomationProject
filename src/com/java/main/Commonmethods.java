package com.java.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Commonmethods {

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		
		System.out.println("System Execution started ");

		System.out.println("System Execution started 2 ");
		
		System.out.println(System.getProperty("os.name"));


		Utility.getPropertiesData("UAT");
		System.out.println("Execution started ");
		launchApplication(Globalvariables.config.get("browser"), Globalvariables.config.get("url"));
		
		login_Into_Application(Globalvariables.config.get("Username"), Globalvariables.config.get("password"));

	}

	public static void launchApplication(String browserName, String url) {

		switch (browserName.trim().toLowerCase()) {

		case "chrome":

			System.out.println(" User selects Chrome Browser  ");

			driver = new ChromeDriver();
			driver.manage().window().maximize();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "firefox":

			System.out.println(" User selects firefox Browser  ");

			driver = new FirefoxDriver();

			driver.manage().window().maximize();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "IE":

			System.out.println(" User selects IE Browser  ");

			driver = new InternetExplorerDriver();

			break;

		case "Safari":
			System.out.println(" User selects Safari Browser  ");

			driver = new SafariDriver();

			break;
		case "mozilafirefox":

			driver = new SafariDriver();

			break;

		default:

			System.out.println(" Please provide Valid browser name ");
			break;
		}

		driver.get(url);

		System.out.println(" Successfully laucnhed Applicarion : " + url);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String title = driver.getTitle().trim().toLowerCase();
		System.out.println(title);

		//driver.quit();

		System.out.println(" Successfully Logged out from browser ");

	}
	
	public static void login_Into_Application(String userName,String passWord) {
		
		WebgenericActions.enterText("xpath", "(//input[@name=\"username\"])[1]", userName);
		
		WebgenericActions.enterText("xpath", "(//input[@name=\"password\"])[1]", passWord);
		
		WebgenericActions.click("xpath", "//button[@type=\"submit\"]");
		
		WebgenericActions.exist("xpath", "(//h6[text()='Dashboard'])[1]");
		
		System.out.println(" Successfully Loggedinto application");
		
		driver.quit();
		
		
	}

}
