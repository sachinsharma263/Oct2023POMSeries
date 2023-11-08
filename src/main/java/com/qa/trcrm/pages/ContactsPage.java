package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pojo.Contacts;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	By contactsPageHeader = By.xpath("(//h2[@class='ng-binding'])[1]");

	By addPersonButton = By.xpath("//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']");
	By name = By.name("name");
	By emailId = By.id("email0");
	By saveBtn = By.xpath("//button[@class='btn btn-primary btn-large ng-binding']");

	By personAddedMsg = By.xpath("//span[text()='Person added.']");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);

	}

	public String getContactsPageTitle() {
		return util.doGetTitle();
	}

	public String getContactsPageHeader() {
		util.waitForPresenceOfElementLocated(contactsPageHeader);
		return util.doGetText(contactsPageHeader);
	}

	public String addPerson(String firstName, String email) {
		util.waitForPresenceOfElementLocated(addPersonButton);
		util.doClick(addPersonButton);
		util.waitForPresenceOfElementLocated(name);
		util.doSendKeys(name, firstName);
		util.doSendKeys(emailId, email);
		util.waitForPresenceOfElementLocated(saveBtn);
		util.doClick(saveBtn);

		util.waitForPresenceOfElementLocated(personAddedMsg);
		return util.doGetText(personAddedMsg);

	}

	public String addPerson(Contacts contact) {
		util.waitForPresenceOfElementLocated(addPersonButton);
		util.doClick(addPersonButton);
		util.waitForPresenceOfElementLocated(name);
		util.doSendKeys(name, contact.getName());
		util.doSendKeys(emailId, contact.getEmail());
		util.waitForPresenceOfElementLocated(saveBtn);
		util.doClick(saveBtn);

		util.waitForPresenceOfElementLocated(personAddedMsg);
		return util.doGetText(personAddedMsg);
	}

	public void doQuit() {
		util.doQuit();
	}
}
