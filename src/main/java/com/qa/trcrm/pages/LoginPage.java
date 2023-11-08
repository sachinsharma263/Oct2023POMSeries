package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	// HomePage homePage;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	By emailId = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.xpath("//input[@type='submit']");

	By signUpNowLink = By.linkText("Sign Up Now2");

	By errorMsg = By.id("error");

	By random = By.id("random");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getLoginPageTitle() {
		return util.waitForPresenceOfTitle(AppConstants.LOGIN_PAGE_TITLE);
	}

	public boolean verifySignUpLink() {
		return util.doIsDisplayed(signUpNowLink);
	}

	public HomePage doLogin(String email, String pass) {
		util.doClear(emailId);
		util.doSendKeys(emailId, email);
		util.doClear(password);
		util.doSendKeys(password, pass);
		util.doClick(loginBtn);

		return new HomePage(driver);
	}

	public HomePage doLogin(Credentials credentials) {
		util.doClear(emailId);
		util.doSendKeys(emailId, credentials.getEmailId());
		util.doClear(password);
		util.doSendKeys(password, credentials.getPassword());
		util.doClick(loginBtn);

		return new HomePage(driver);
	}

	public boolean checkLoginErrorMsg() {
		return util.doIsDisplayed(errorMsg);
	}
}
