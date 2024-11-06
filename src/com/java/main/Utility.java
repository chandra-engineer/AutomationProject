package com.java.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Utility {

	public static HashMap<String, String>getPropertiesData(String env) throws Exception {
		
		
		HashMap<String, String>config=null;

		String envType = "/confgurationEnv.properties";

		String finalEnv = envType.replace("Env", env);

		System.out.println(" Selected Environment " + finalEnv);
		String filepath = System.getProperty("user.dir");

		Properties prop = new Properties();

		File f = new File(filepath + "/Configuration" + finalEnv);
		
		
			try {
			FileInputStream fis = new FileInputStream(f);

			prop.load(fis);

			HashMap<String, String> configData = new HashMap<String, String>();

			for (String key : prop.stringPropertyNames()) {

				String value = prop.getProperty(key);

				// System.out.println(" Key : " + key + " Value :" + value);
				configData.put(key, value);

			}

			Globalvariables.config = configData;
		config=configData;

			System.out.println("Map Data " + configData.toString());
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return config;

	}

}
