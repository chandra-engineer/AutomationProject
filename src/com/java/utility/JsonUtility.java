package com.java.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.java.main.Globalvariables;

public class JsonUtility {

	JSONObject page;
	private String pageName;

	public JsonUtility(String fileName, String pageName) {

		
		try {
			String finaleFilepath = Globalvariables.locatorsPath + fileName + ".json";
			this.pageName = pageName;


			FileInputStream fis = new FileInputStream(new File(finaleFilepath));

			JSONTokener tokerner = new JSONTokener(fis);
			JSONObject obj = new JSONObject(tokerner);

			page = obj.getJSONObject(pageName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String[] getlocators(String elementName) {

		try {

			if (page.has(elementName)) {

				JSONObject elementNode = page.getJSONObject(elementName);

				String type = elementNode.getString("locatorType");

				String value = elementNode.getString("locatorValue");

				return new String[] { type, value };

			} else {

				System.out.println(" Element " + elementName + "not found on page " + pageName + " .");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	public static void readDatafromJson(String filepath, String pageName) throws Exception {

		FileInputStream fis = new FileInputStream(new File(filepath));

		JSONTokener tokerner = new JSONTokener(fis);

		JSONObject obj = new JSONObject(tokerner);

		JSONObject page = obj.getJSONObject(pageName);

		JSONObject element = page.getJSONObject("userName");

		String type = element.getString("locatorType");

		String value = element.getString("locatorValue");

		System.out.println(type + ":" + value);

	}

}
