package com.slokam.automation.commons.utils.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.slokam.automation.commons.utils.AutomationUtils;

public class CaptureScreenshot {

	public String takeScreenshot(WebDriver driver, String fileName) {
		
		String file = AutomationUtils.LATEST_REPORTS + fileName;
		File destPath = new File(file);
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			// log error
		}
		return destPath.toPath().toString();
	}

}
