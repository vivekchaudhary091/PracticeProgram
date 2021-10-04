package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'User: vivek chaudhary ')]")
	WebElement header;

	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealLink;

	@FindBy(xpath="//a[contains(text(),'Calendar')]")
	WebElement calender;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public ContactPage clickOnContactsLink() {
		
		contactsLink.click();
		
		return new ContactPage();
	}
	
	public CalendarPage clickOnCalenderLink() {
		calender.click();
		return new CalendarPage();
	}
	
	public DealPage clickOnDealLink() {
		
		dealLink.click();
		return new DealPage();
		
	}
	
	public boolean headerLogo() {
		boolean b=header.isDisplayed();
		return b;
	}

}
