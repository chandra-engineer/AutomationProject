package com.java.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Commonmethods {

	public static void main(String[] args) throws Exception {

		System.out.println("System Execution started ");

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

			Globalvariables.driver = new ChromeDriver();
			Globalvariables.driver.manage().window().maximize();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "firefox":

			System.out.println(" User selects firefox Browser  ");

			Globalvariables.driver = new FirefoxDriver();

			Globalvariables.driver.manage().window().maximize();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "IE":

			System.out.println(" User selects IE Browser  ");

			Globalvariables.driver = new InternetExplorerDriver();

			break;

		case "Safari":
			System.out.println(" User selects Safari Browser  ");

			Globalvariables.driver = new SafariDriver();

			break;
		case "mozilafirefox":

			Globalvariables.driver = new SafariDriver();

			break;

		default:

			System.out.println(" Please provide Valid browser name ");
			break;
		}

		Globalvariables.driver.get(url);

		System.out.println(" Successfully laucnhed Applicarion : " + url);

		Globalvariables.driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

		String title = Globalvariables.driver.getTitle().trim().toLowerCase();
		System.out.println(title);

		// driver.quit();

		// System.out.println(" Successfully Logged out from browser ");

	}

	public static void login_Into_Application(String userName, String passWord) {

		WebgenericActions.enterText("xpath", "(//input[@name=\"username\"])[1]", userName);

		WebgenericActions.enterText("xpath", "(//input[@name=\"password\"])[1]", passWord);

		WebgenericActions.click("xpath", "//button[@type=\"submit\"]");

		WebgenericActions.exist("xpath", "(//h6[text()='Dashboard'])[1]");

		System.out.println(" Successfully Loggedinto application");

		Globalvariables.driver.quit();

	}

}
