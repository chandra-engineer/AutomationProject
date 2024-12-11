package com.java.utility;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileInputStream;
import java.io.InputStream;

public class LocatorReader {

	private JSONObject locators;
	private String currentPageName;

	/**
	 * @author chandrasekhar k
	 * Constructor to initialize JSON file and default page name.
	 *
	 * @param filePath Path to the JSON file containing locator data.
	 * @param pageName Default page name to start with.
	 */
	public LocatorReader(String filePath, String pageName) {
		try (InputStream is = new FileInputStream(filePath)) {
			JSONTokener tokener = new JSONTokener(is);
			locators = new JSONObject(tokener);
			this.currentPageName = pageName;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author chandrasekhar k
	 * Method to switch the current page.
	 *
	 * @param pageName Name of the page to switch to.
	 */
	public void switchPage(String pageName) {
		if (locators.has(pageName)) {
			this.currentPageName = pageName;
			System.out.println("Switched to page: " + pageName);
		} else {
			throw new IllegalArgumentException("Page '" + pageName + "' not found in the JSON file.");
		}
	}

	/**@author chandrasekhar k
	 * Retrieves the locator type and value for a given locator name on the current
	 * page.
	 *
	 * @param locatorName Name of the locator to search for.
	 * @return A string array containing locator type and value, or null if not
	 *         found.
	 */
	public String[] getLocator(String locatorName) {
		try {
			if (locators.has(currentPageName)) {
				JSONObject page = locators.getJSONObject(currentPageName);
				for (String subModuleName : page.keySet()) {
					JSONObject subModule = page.getJSONObject(subModuleName);
					if (subModule.has(locatorName)) {
						JSONObject locator = subModule.getJSONObject(locatorName);
						String type = locator.getString("type");
						String value = locator.getString("value");
						return new String[] { type, value };
					}
				}
				System.out.println(
						"Locator '" + locatorName + "' not found in any submodule on page '" + currentPageName + "'.");
			} else {
				System.out.println("Page '" + currentPageName + "' not found in the JSON file.");
			}
		} catch (Exception e) {
			System.out.println("Error retrieving locator '" + locatorName + "': " + e.getMessage());
		}
		return null;
	}

	public static void main(String[] args) {
		// Initialize LocatorReader with file path and default page name
		LocatorReader locatorReader = new LocatorReader("src/main/resources/locators.json", "loginPage");

		// Retrieve locator type and value
		String[] usernameField = locatorReader.getLocator("usernameField");
		if (usernameField != null) {
			System.out.println("Locator Type: " + usernameField[0]);
			System.out.println("Locator Value: " + usernameField[1]);
		}

		// Switch to another page
		locatorReader.switchPage("homePage");

		// Retrieve another locator
		String[] searchBox = locatorReader.getLocator("searchBox");
		if (searchBox != null) {
			System.out.println("Locator Type: " + searchBox[0]);
			System.out.println("Locator Value: " + searchBox[1]);
		}
	}
}
