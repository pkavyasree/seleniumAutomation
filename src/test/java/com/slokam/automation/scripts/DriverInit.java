package com.slokam.automation.scripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.slokam.automation.commons.enums.Browser;
import com.slokam.automation.commons.utils.data.PropertyHandler;

public class DriverInit {

	private WebDriver driver;
	private PropertyHandler configProps = new PropertyHandler("config");
	private PropertyHandler appProps = new PropertyHandler();
	private String chromeDriverPath;
	private String internetExplorerPath;
	private String geckoDriverPath;
	private static Map<Long, DriverInit> iMap = Collections.synchronizedMap(new HashMap<Long, DriverInit>());
	private static DriverInit driverInit;
	protected Browser browser ;
	public DriverInit() {
		setDriverPath();
	}

	public static DriverInit getInstance() {
		return getInstance(false);
	}
	
	public static DriverInit getInstance(boolean flag) {
		long threadId = Thread.currentThread().getId();
		if (flag || iMap.get(threadId) == null) {
			driverInit = new DriverInit();
			iMap.put(threadId, driverInit);
		}
		return iMap.get(threadId);
	}

	private void setDriverPath() {
		String osName = System.getProperty("os.name").toLowerCase().contains("win") ? "win" : "linux";
		String driverPath = "./src/test/resources/drivers/";
		System.out.println("os name is " + System.getProperty("os.name").toLowerCase());
		if (osName.equalsIgnoreCase("win")) {
			chromeDriverPath = driverPath + "/" + osName + "/chromedriver.exe";
			geckoDriverPath = driverPath + "/" + osName + "/geckodriver.exe";
			internetExplorerPath = driverPath + "/" + osName + "/IEDriverServer.exe";
		} else {
			chromeDriverPath = driverPath + "/" + osName + "/chromedriver";
			geckoDriverPath = driverPath + "/" + osName + "/geckodriver";
		}
	}

	private boolean getGridValue() {
		String gridString = configProps.getProperty("grid", "false");
		boolean grid = true;
		try {
			if (gridString.equals("false")) {
				grid = false;
			} else {
				return Boolean.valueOf(gridString);
			}
		} catch (Exception e) {
			grid = false;
		}
		return grid;
	}

	public WebDriver getDriver() {
		browser = Browser.fromString(appProps.getProperty("browser"));
		boolean grid = getGridValue();
		if (driver == null) {
			if (grid == true) {
				driver = getGridDriver(browser);
			} else {
				driver = getLocalDriver(browser);
			}

		} else {
			return driver;
		}
		return driver;
	}

	private WebDriver getGridDriver(Browser browser) {
		String hub = configProps.getProperty("gridUrl");
		DesiredCapabilities capability;

		switch (browser) {
		case HTMLUNIT:
			capability = DesiredCapabilities.htmlUnit();
			capability.setJavascriptEnabled(true);
			break;
		case FIREFOX:
			capability = DesiredCapabilities.firefox();
			FirefoxProfile profile = new FirefoxProfile();

			// Wait for 50 seconds to avoid Firefox Unresponsive warning
			profile.setPreference("dom.max_script_run_time", 50);
			profile.setPreference("dom.max_chrome_script_run_time", 50);

			capability.setCapability(FirefoxDriver.PROFILE, profile);

			break;
		case CHROME:
			capability = DesiredCapabilities.chrome();
			break;
		case INTERNET_EXPLORER:
			capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			break;
		default:
			capability = DesiredCapabilities.chrome();
		}

		try {
			driver = new RemoteWebDriver(new URL(hub), capability);
			if (!browser.equals(Browser.HTMLUNIT)) {
				driver.manage().window().maximize();
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}

		return driver;
	}

	private WebDriver getLocalDriver(Browser browser) {
		DesiredCapabilities capabilities;

		switch (browser) {
		case HTMLUNIT:
			capabilities = DesiredCapabilities.htmlUnit();
			capabilities.setJavascriptEnabled(true);
			// driver = new HtmlUnitDriver(capabilities);
			return driver;
		case FIREFOX:
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("security.enable_java", true);
			profile.setPreference("plugin.state.java", 2);
			System.setProperty("webdriver.gecko.driver", geckoDriverPath);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case INTERNET_EXPLORER:

			System.setProperty("webdriver.ie.driver", internetExplorerPath);
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
			driver = new InternetExplorerDriver(options);
			driver.manage().window().maximize();
			break;

		default:
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("disable-infobars");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			chromeOptions.addArguments("start-maximized");
			
			
			driver = new ChromeDriver(chromeOptions);
		}

		return driver;
	}

}
