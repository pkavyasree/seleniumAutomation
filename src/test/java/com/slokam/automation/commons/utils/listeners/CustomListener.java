package com.slokam.automation.commons.utils.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.slokam.automation.commons.utils.AutomationUtils;
import com.slokam.automation.commons.utils.report.CaptureScreenshot;
import com.slokam.automation.scripts.BaseTestCase;

public class CustomListener extends TestListenerAdapter {
	private WebDriver driver = null;
	private ExtentReports extent = null;
	private ExtentTest test = null;

	@Override
	public void onTestSuccess(ITestResult tr) {

	}

	@Override
	public void onTestFailure(ITestResult tr) {
		String timestamp = AutomationUtils.getCurrentDate();
		String fileName = tr.getMethod().getMethodName() +"_"+ timestamp+ ".jpg";
		driver = BaseTestCase.getDriver();
		extent = BaseTestCase.extent;
		test = BaseTestCase.test;
		new CaptureScreenshot().takeScreenshot(driver, fileName);
		System.out.println(fileName);
		try {
			test.addScreenCaptureFromPath("./"+fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (tr.getStatus() == ITestResult.FAILURE) {
			test.fail(tr.getThrowable().getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {

	}

	@Override
	public void onFinish(ITestContext testContext) {
		
	}

}
