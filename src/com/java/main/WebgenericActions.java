package com.java.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebgenericActions {

	public static WebDriver driver=Commonmethods.driver;;

	

	public static WebElement getElement(String locatorType, String locatorValue) {

		WebElement element = null;

		try {
			switch (locatorType.trim().toLowerCase()) {

			case "xpath":

				element = driver.findElement(By.xpath(locatorValue));

				break;

			case "name":

				element = driver.findElement(By.name(locatorValue));

				break;

			case "id":

				element = driver.findElement(By.id(locatorValue));

				break;
			case "className":

				element = driver.findElement(By.className(locatorValue));

				break;
			case "tagename":

				element = driver.findElement(By.tagName(locatorValue));

				break;
			case "cssselector":

				element = driver.findElement(By.cssSelector(locatorValue));
				break;
			case "partilalinktext":

				element = driver.findElement(By.partialLinkText(locatorValue));

				break;
			case "linktext":

				element = driver.findElement(By.linkText(locatorValue));

				break;
			default:
				System.out.println(" Please proivde Valid locator Type");
				break;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return element;

	}

	public static WebElement createElement(String locatorType, String locatorValue) {

		WebElement element = null;

		element = getElement(locatorType, locatorValue);

		if (element != null) {

			if (element.isDisplayed()) {

				if (element.isEnabled()) {

					System.out.println("Step Element created successfully with Locator Type : " + locatorType
							+ " Locator value : " + locatorValue);
				} else {

					System.out.println(" Element is not Enabled Locator Type : " + locatorType + " Locator value : "
							+ locatorValue);
				}

			} else {
				System.out.println(
						" Element is not displayed Locator Type : " + locatorType + " Locator value : " + locatorValue);
			}

		} else {

			System.out.println("Please provide validate Locator type and locator value " + locatorType
					+ " Locator value : " + locatorValue);
		}

		return element;

	}

	public static boolean click(String locatorType, String locatorValue) {

		boolean bStatus = false;

		try {

			WebElement element = createElement(locatorType, locatorValue);

			element.click();

			System.out.println(" User clicked on " + locatorType + " Locator value : " + locatorValue);

			bStatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bStatus;

	}

	public static boolean enterText(String locatorType, String locatorValue, String input) {

		boolean bStatus = false;

		try {

			WebElement element = createElement(locatorType, locatorValue);
			element.clear();
			element.click();
			element.sendKeys(input);

			System.out.println(" User entered Value " + input);

			bStatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bStatus;

	}
	
	
	public static WebElement exist(String locatorType, String locatorValue) {

		WebElement element = null;

		element = getElement(locatorType, locatorValue);

		if (element != null) {

			if (element.isDisplayed()) {

				if (element.isEnabled()) {

					System.out.println("Element is available  Locator Type : " + locatorType
							+ " Locator value : " + locatorValue);
				} else {

					System.out.println(" Element is not Enabled Locator Type : " + locatorType + " Locator value : "
							+ locatorValue);
				}

			} else {
				System.out.println(
						" Element is not displayed Locator Type : " + locatorType + " Locator value : " + locatorValue);
			}

		} else {

			System.out.println("Please provide validate Locator type and locator value " + locatorType
					+ " Locator value : " + locatorValue);
		}

		return element;

	}

}
