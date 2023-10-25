package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage2 {

	WebDriver driver;

	By homePageHeader = By.xpath("//span[text()='Homepage']");
	By loggedInUser = By.xpath("//span[@class='hidden-xs']");

	public HomePage2(WebDriver driver) {
		this.driver = driver;
	}
	public String getHomePageTitle()
	{
		return driver.getTitle();
	}
	public String getHomePageHeader() {
		return driver.findElement(homePageHeader).getText();
	}

	public String isUserLoggedIn() {
		return driver.findElement(loggedInUser).getText();
	}
}
