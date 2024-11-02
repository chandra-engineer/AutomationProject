package com.java.utility;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.InputStream;

public class LocatorReader {

    private JSONObject locators;

    public LocatorReader(String filePath) {
        try (InputStream is = new FileInputStream(filePath)) {
            JSONTokener tokener = new JSONTokener(is);
            locators = new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public By getLocator(String pageName, String elementName) {
        JSONObject page = locators.getJSONObject(pageName);
        JSONObject element = page.getJSONObject(elementName);
        String type = element.getString("type");
        String value = element.getString("value");

        switch (type.toLowerCase()) {
            case "id":
                return By.id(value);
            case "name":
                return By.name(value);
            case "xpath":
                return By.xpath(value);
            case "css":
                return By.cssSelector(value);
            case "linktext":
                return By.linkText(value);
            case "partiallinktext":
                return By.partialLinkText(value);
            case "tagname":
                return By.tagName(value);
            case "classname":
                return By.className(value);
            default:
                throw new IllegalArgumentException("Unknown locator type: " + type);
        }
    }

    public static void main(String[] args) {
        LocatorReader locatorReader = new LocatorReader("path/to/locators.json");
        
        // Example usage
        By loginButton = locatorReader.getLocator("loginPage", "loginButton");
        System.out.println("Locator for login button: " + loginButton);
        
        By searchBox = locatorReader.getLocator("homePage", "searchBox");
        System.out.println("Locator for search box: " + searchBox);
    }
}
