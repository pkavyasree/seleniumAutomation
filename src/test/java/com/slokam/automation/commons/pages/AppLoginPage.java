package com.slokam.automation.commons.pages;

import org.openqa.selenium.By;

public class AppLoginPage extends BasePage {

	
	public void clickOnContinueButton() {
		findElement(By.xpath("//a[text()='Continue']")).click();
	}
}
