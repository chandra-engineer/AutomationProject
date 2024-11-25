package com.java.utility;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;

public class LocatorReader {

    private JSONObject locators;
    private String pageName;

    /**
     * Constructor to initialize JSON file and page name.
     *
     * @param filePath Path to the JSON file containing locator data.
     * @param pageName Name of the page to load locators for.
     */
    public LocatorReader(String filePath, String pageName) {
        this.pageName = pageName;
        try (InputStream is = new FileInputStream(filePath)) {
            JSONTokener tokener = new JSONTokener(is);
            locators = new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    /**
     * Retrieves the locator type and value for a given object name.
     *
     * @param elementName Name of the object to search for.
     * @return A string array containing locator type and value, or null if not found.
     */
    public String[] getLocator(String elementName) {
        try {
            if (locators.has(pageName)) {
                JSONObject page = locators.getJSONObject(pageName);

                if (page.has(elementName)) {
                    JSONObject element = page.getJSONObject(elementName);
                    String type = element.getString("type");
                    String value = element.getString("value");
                    return new String[]{type, value};
                } else {
                    System.out.println("Element '" + elementName + "' not found on page '" + pageName + "'.");
                }
            } else {
                System.out.println("Page '" + pageName + "' not found in the JSON file.");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving locator for element '" + elementName + "': " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        // Initialize LocatorReader with file path and page name
        LocatorReader locatorReader = new LocatorReader("./Locators/Locators.json", "loginPage");

        // Retrieve locator type and value for an element
        String[] loginButtonLocator = locatorReader.getLocator("loginButton");
        if (loginButtonLocator != null) {
            System.out.println("Locator Type: " + loginButtonLocator[0]);
            System.out.println("Locator Value: " + loginButtonLocator[1]);
        } else {
            System.out.println("Locator not found for the given object.");
        }

        // Retrieve locator type and value for another element
        String[] usernameFieldLocator = locatorReader.getLocator("usernameField");
        if (usernameFieldLocator != null) {
            System.out.println("Locator Type: " + usernameFieldLocator[0]);
            System.out.println("Locator Value: " + usernameFieldLocator[1]);
        }
    }
}
