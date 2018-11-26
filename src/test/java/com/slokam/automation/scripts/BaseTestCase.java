package com.slokam.automation.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.slokam.automation.commons.utils.AutomationUtils;
import com.slokam.automation.commons.utils.data.PropertyHandler;
import com.slokam.automation.commons.utils.listeners.CustomListener;
import com.slokam.automation.commons.utils.report.ExtentManager;


@Listeners(value = {CustomListener.class})
public class BaseTestCase {
	protected PropertyHandler appProps = null;
	protected static WebDriver driver = null;
	protected Actions actions = null;
	protected String adminUrl = "";
	protected String appUrl = "";
	protected String username = "";
	protected String password = "";
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	@BeforeSuite
	public void beforeSuite() {
		AutomationUtils.deleteAndCreateLatestReportsFolder();
	}
	
	@AfterSuite
	public void afterSuite() {
		AutomationUtils.createBackupFolder();
	}
	@BeforeMethod
	public void launchBrowser(ITestContext context) {
		appProps = new PropertyHandler();
		appUrl = appProps.getProperty("appurl");
		username = appProps.getProperty("username");
		password = appProps.getProperty("password");
		driver = DriverInit.getInstance(true).getDriver();
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actions = new Actions(driver);
		extent = ExtentManager.GetExtent();
	}

	public void launchAdminApp() {
		adminUrl = appProps.getProperty("adminurl");
		driver.get(adminUrl);
	}

	public void launchApp() {
		adminUrl = appProps.getProperty("appurl");
		driver.get(adminUrl);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		extent.flush();
		driver.quit();
	}
}
