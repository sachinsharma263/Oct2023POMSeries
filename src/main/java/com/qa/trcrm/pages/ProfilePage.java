package com.qa.trcrm.pages;

import org.openqa.selenium.WebDriver;

public class ProfilePage {

	WebDriver driver;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	public void m2() {
		System.out.println("remote");
	}

	public void m1() {
		System.out.println("local");

	}
}
