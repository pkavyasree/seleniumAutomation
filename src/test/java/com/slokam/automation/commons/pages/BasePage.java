package com.slokam.automation.commons.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.slokam.automation.scripts.BaseTestCase;

public class BasePage implements TopBarPage{

	protected WebDriver driver;
	private WebDriverWait wait;
	public  BasePage() {
		driver  = BaseTestCase.getDriver();
		wait = new WebDriverWait(driver,30);
	}
	@Override
	public void clickOnLoginButton() {
		findElement(By.xpath("//span[text()='My Account']")).click();
		findElement(By.xpath("//a[text()='Login']")).click();
	}

	@Override
	public void clickOnWishList() {
		
	}

	@Override
	public void register() {
		
	}
	
	
	protected WebElement findElement(By by){
		ExpectedConditions.elementToBeClickable(by);
		WebElement element;
		if(wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(by),
												ExpectedConditions.elementToBeClickable(by),
												ExpectedConditions.visibilityOfElementLocated(by)
				))) {
			
			element = driver.findElement(by);
		}else {
			element = null;
			//log --> error
		}
		return element;
		
	}
	
}
