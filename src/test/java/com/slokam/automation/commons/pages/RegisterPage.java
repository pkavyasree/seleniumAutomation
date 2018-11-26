package com.slokam.automation.commons.pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

	
	public void register() {
		findElement(By.xpath("//input[@value='Continue']")).click();
	}
	
	public String getErrorMessage() {
		return "";
	}
}
