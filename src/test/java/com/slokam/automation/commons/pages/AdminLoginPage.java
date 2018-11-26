package com.slokam.automation.commons.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage extends BasePage {

	@FindBy(id="input-username")
	WebElement userEle ;
	
	@FindBy(id="input-password")
	WebElement passEle;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitEle;
	
	@FindBy(xpath="//a[text()='Forgotten Password']")
	WebElement forgottenEle;
	@FindBy(className="close")
	WebElement Loginalert;
	
	public void login(String username, String password) {
		// login to admin app
		userEle.sendKeys(username);
		passEle.sendKeys(password);
		submitEle.click();
		//sleep
		Loginalert.click();
	}

	public void clickForgottenPasswordLink() {
		forgottenEle.click();
	}
	
	
}
