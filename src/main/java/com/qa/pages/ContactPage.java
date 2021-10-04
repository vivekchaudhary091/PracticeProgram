package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;

public class ContactPage extends TestBase{
	
	Select select;
	
	@FindBy(xpath="//input[@type='text' and @name='cs_name']")
	WebElement nameField;
	
	@FindBy(xpath="//input[@type='text' and @name='cs_company_name']")
	WebElement companyField;

	
	@FindBy(xpath="//input[@type='submit' and @name='cs_submit']")
	WebElement search;
	
	@FindBy(xpath="//input[@type='checkbox' and @name='contact_id' and @value=52827357]")
	WebElement checkBox;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstName;
	
	@FindBy(xpath="//select[@name='category']")
	WebElement category;
	
	
	@FindBy(xpath="//select[@name='status']")
	WebElement status;
	
	
	@FindBy(xpath="//input[@name='surname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement save;
	
	
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void searchContacts(String name, String comp) {
		
		nameField.sendKeys("name");
		companyField.sendKeys("comp");
		search.click();
		
		driver.switchTo().frame("mainpanel");
		checkBox.click();
	}
	
	public void clickOnNewContactLink() {
		
		Actions action =new Actions(driver);
		action.moveToElement(newContactLink).click().build().perform();
	}
	
	public void newContactsDataEntry(String categoty,String fname,String lname,String status) {
		
		select=new Select(driver.findElement(By.xpath("category")));
		select.selectByVisibleText(categoty);
		
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		
		select=new Select(driver.findElement(By.xpath("status")));
		select.selectByVisibleText(status);
		
		save.click();
		
		
		
		
		
	}


}
