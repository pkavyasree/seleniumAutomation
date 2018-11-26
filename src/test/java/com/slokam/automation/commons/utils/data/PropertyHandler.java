package com.slokam.automation.commons.utils.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PropertyHandler {

	Properties props = null;

	public PropertyHandler(String filename) {
		File file = new File("./src/test/resources/" + filename + ".properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PropertyHandler() {
		File file = new File("./src/test/resources/app.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			Assert.fail("app properties file not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		String value = "";
		if (key == "" || key == null || key.equals("")) {
			Assert.fail(key + " is not availble ");
		} else {
			value = props.getProperty(key);
		}
		return value;
	}

	public String getProperty(String key, String dValue) {
		String value = "";
		value = props.getProperty(key, dValue);
		return value;
	}

	public static void main(String[] args) {

	}
}
