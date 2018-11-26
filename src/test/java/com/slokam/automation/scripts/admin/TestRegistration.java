package com.slokam.automation.scripts.admin;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.slokam.automation.commons.pages.AppLoginPage;
import com.slokam.automation.commons.pages.HomePage;
import com.slokam.automation.commons.pages.RegisterPage;
import com.slokam.automation.commons.utils.report.ExtentManager;
import com.slokam.automation.scripts.BaseTestCase;

public class TestRegistration extends BaseTestCase {

	HomePage homePage = null;
	AppLoginPage appLoginPage = null;
	RegisterPage registerPage = null;

	@Test(groups = "regression", description = "verifyRegistrationFieldsPrivacyPolicyTest")
	public void verifyRegistrationFieldsPrivacyPolicyTest() {
		test = extent.createTest("verifyRegistrationFieldsPrivacyPolicyTest");
		String expectedErrorMessage = " Warning: You must agree to the Privacy Policy!";
		homePage = new HomePage();
		test.info("Test case execution started");
		homePage.clickOnLoginButton();
		appLoginPage = PageFactory.initElements(driver, AppLoginPage.class);
		appLoginPage.clickOnContinueButton();
		test.info("Clicked on continue button");
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		registerPage.register();
		String actualErrorMessage = registerPage.getErrorMessage();

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}

	@Test(groups = "regression")
	public void verifyRegistrationFieldsEmailTest() {
		test = extent.createTest("verifyRegistrationFieldsEmailTest");
		homePage = new HomePage();
		homePage.clickOnLoginButton();
		appLoginPage = PageFactory.initElements(driver, AppLoginPage.class);
		appLoginPage.clickOnContinueButton();
		registerPage = PageFactory.initElements(driver, RegisterPage.class);
		registerPage.register();
	}
}
