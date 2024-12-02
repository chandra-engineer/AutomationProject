package com.java.utility;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;


public class LocatorReaderXML {

	private Document document;
	private String pageName;

	/**
	 * Constructor to initialize XML file and page name.
	 *
	 * @param filePath Path to the XML file containing locator data.
	 * @param pageName Name of the page to load locators for.
	 */
	public LocatorReaderXML(String filePath, String pageName) {
		this.pageName = pageName;
		try {
			File xmlFile = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the locator type and value for a given object name.
	 *
	 * @param elementName Name of the object to search for.
	 * @return A string array containing locator type and value, or null if not
	 *         found.
	 */
	public String[] getLocator(String elementName) {
		try {
			Node pageNode = getPageNode(pageName);
			if (pageNode != null) {
				NodeList elementList = ((Element) pageNode).getElementsByTagName(elementName);
				if (elementList.getLength() > 0) {
					Element element = (Element) elementList.item(0);
					String type = element.getElementsByTagName("type").item(0).getTextContent();
					String value = element.getElementsByTagName("value").item(0).getTextContent();
					return new String[] { type, value };
				} else {
					System.out.println("Element '" + elementName + "' not found on page '" + pageName + "'.");
				}
			} else {
				System.out.println("Page '" + pageName + "' not found in the XML file.");
			}
		} catch (Exception e) {
			System.out.println("Error retrieving locator for element '" + elementName + "': " + e.getMessage());
		}
		return null;
	}

	/**
	 * Helper method to get the page node by its name.
	 *
	 * @param pageName Name of the page to get the node for.
	 * @return The page node or null if not found.
	 */
	private Node getPageNode(String pageName) {
		NodeList pageList = document.getElementsByTagName(pageName);
		if (pageList.getLength() > 0) {
			return pageList.item(0);
		}
		return null;
	}

	public static void main(String[] args) {
		// Initialize LocatorReaderXML with file path and page name
		LocatorReaderXML locatorReader = new LocatorReaderXML("src/main/resources/locators.xml", "loginPage");

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
