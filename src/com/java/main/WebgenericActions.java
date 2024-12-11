package com.java.main;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebgenericActions {

	public static WebDriver driver = Globalvariables.driver;;

	public static WebElement getElement(String locatorType, String locatorValue) {

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		try {
			switch (locatorType.trim().toLowerCase()) {

			case "xpath":

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));

				// Specify the timout of the wait

				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

				element = driver.findElement(By.xpath(locatorValue));

				break;

			case "name":

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));

				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

				element = driver.findElement(By.name(locatorValue));

				break;

			case "id":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));

				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

				element = driver.findElement(By.id(locatorValue));

				break;
			case "className":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

				element = driver.findElement(By.className(locatorValue));

				break;
			case "tagename":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

				element = driver.findElement(By.tagName(locatorValue));

				break;
			case "cssselector":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

				element = driver.findElement(By.cssSelector(locatorValue));
				break;
			case "partilalinktext":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

				element = driver.findElement(By.partialLinkText(locatorValue));

				break;
			case "linktext":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				wait.withTimeout(Duration.ofSeconds(3));
				// Sepcify polling time
				wait.pollingEvery(Duration.ofMillis(250));
				// Specify what exceptions to ignore
				wait.ignoring(NoSuchElementException.class);

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

			System.out.println(" User clicked on ---> " + locatorValue);

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

			System.out.println(" User entered Value ---> " + input);

			bStatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bStatus;

	}

	public static boolean exist(String locatorType, String locatorValue) {

		boolean bStatus = false;

		WebElement element = null;

		element = getElement(locatorType, locatorValue);

		if (element != null) {

			if (element.isDisplayed()) {

				if (element.isEnabled()) {

					System.out.println(
							"Element is available  Locator Type : " + locatorType + " Locator value : " + locatorValue);
					bStatus = true;
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

		return bStatus;

	}

	public static boolean hoveronElement(String locatorType, String locatorValue) {

		boolean bStatus = false;

		try {

			WebElement element = createElement(locatorType, locatorValue);

			Actions act = new Actions(driver);

			act.moveToElement(element).build().perform();

			System.out.println("Step : Successfully hover to element ");

			bStatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bStatus;

	}

	public static boolean scrollIntoViewElement(String locatorType, String locatorValue) {

		boolean bStatus = false;

		try {

			WebElement element = createElement(locatorType, locatorValue);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].scrollIntoView(true);", element);

			System.out.println("Step : Successfully scroll to element ");

			bStatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bStatus;

	}

	public static boolean validateTextUsingJs(String locatorType, String locatorValue, String expectedTextValue) {

		boolean bStatus = false;

		try {

			WebElement element = createElement(locatorType, locatorValue);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			String theTextIWant = (String) js.executeScript("return arguments[0].value;", element);

			System.out.println("UI Text : " + theTextIWant);

			if (theTextIWant.toLowerCase().trim().contains(expectedTextValue.toLowerCase().trim())) {

				System.out.println(" Validate Text is equal " + theTextIWant + " = " + expectedTextValue);
			} else {
				System.out.println(" Validate Text is not equal " + theTextIWant + " != " + expectedTextValue);

			}

			bStatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bStatus;

	}

	public static boolean validateTextUsing(String locatorType, String locatorValue, String expectedTextValue) {

		boolean bStatus = false;

		try {

			WebElement element = createElement(locatorType, locatorValue);

			String theTextIWant = element.getText().trim().toLowerCase();

			System.out.println("UI Text : " + theTextIWant);

			if (theTextIWant.toLowerCase().trim().contains(expectedTextValue.toLowerCase().trim())) {

				System.out.println(" Validate Text is equal " + theTextIWant + " = " + expectedTextValue);
			} else {
				System.out.println(" Validate Text is not equal " + theTextIWant + " != " + expectedTextValue);

			}

			bStatus = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bStatus;

	}

	public static String getTextUsingJs(String locatorType, String locatorValue) {

		String theTextIWant = null;

		try {

			WebElement element = createElement(locatorType, locatorValue);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			theTextIWant = (String) js.executeScript("return arguments[0].value;", element);

			System.out.println("UI Text : " + theTextIWant);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return theTextIWant.trim();

	}

}
