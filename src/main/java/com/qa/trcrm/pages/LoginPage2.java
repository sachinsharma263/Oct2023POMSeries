package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.ElementUtil;

public class LoginPage2 extends BasePage {

	WebDriver driver;
	HomePage homePage;
	ElementUtil util;

	By emailId = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.xpath("//input[@type='submit']");

	By signUpNowLink = By.linkText("Sign Up Now");

	By errorMsg = By.id("error");

	public LoginPage2(WebDriver driver) {
		this.driver = driver;
	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean verifySignUpLink() {
		return driver.findElement(signUpNowLink).isDisplayed();
	}

	public HomePage doLogin(String email, String pass) {
		driver.findElement(emailId).clear();
		driver.findElement(emailId).sendKeys(email);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pass);
		driver.findElement(loginBtn).click();

		return new HomePage(driver);
	}

	public boolean checkLoginErrorMsg() {
		return driver.findElement(errorMsg).isDisplayed();
	}
}
