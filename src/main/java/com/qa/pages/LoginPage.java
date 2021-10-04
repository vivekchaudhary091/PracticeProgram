package com.qa.pages;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage  extends TestBase{
	
	@FindBy(xpath="//input[@name='username']")
	WebElement username;

	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginbtn;
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement loginPageLogo;
	
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public HashMap<String,String> loginCredential() {
		
		HashMap<String,String> userMap= new HashMap<String,String>();
		
		userMap.put("customer", "vivek091_Officer@100");
		userMap.put("admin", "adminvivek_Officer@100");
		
		
		return userMap;
	}
	
	public boolean freeCRMLogo() {
		boolean b=loginPageLogo.isDisplayed();
		return b;
		
	}
	
	public HomePage doLogin(String user, String pwd) {
		
		username.sendKeys("user");
		password.sendKeys("pwd");
		loginbtn.click();
		
		return new HomePage();
		
		
	}
	

}
