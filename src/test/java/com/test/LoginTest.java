package com.test;

import java.io.IOException;

import javax.naming.InitialContext;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.qa.base.TestBase;
import com.qa.extentreports.ExtentReportsListner;
import com.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class LoginTest extends TestBase {
	
	public LoginPage loginPage;
	public ExtentReportsListner extentRL;
	public ExtentTest extentTest;
	public ExtentReports extent;
	public String credential;
	public String credentialInfo[];
	

	public LoginTest() {
		super();
	
	}
	
	
	@BeforeTest
	public void setExtent() {
		
		extent=new ExtentReports(System.getProperty("user.dir") +"/test-output/Extent.html",true);
		extent.addSystemInfo("UserName:","Vivek Chaudhary");
		extent.addSystemInfo("Environment:","QA");
		extent.addSystemInfo("HostName:","Windows");
		
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage=new LoginPage();
		extentRL=new ExtentReportsListner();
	}
	
	@Test(priority=1)
	public void verifyFreeCRMLogoTest() {
		extentTest=extent.startTest("verifyFreeCRMLogoTest");
		boolean b=loginPage.freeCRMLogo();
		Assert.assertTrue(b);
		
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithAdmin() {
		extentTest=extent.startTest("verifyLoginWithAdmin");
		credential=loginPage.loginCredential().get("admin");
		credentialInfo=credential.split("_");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(credentialInfo[0]);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(credentialInfo[1]);
		
		WebElement element=driver.findElement(By.xpath("//input[@type='submit']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithCustomer() {
		extentTest=extent.startTest("verifyLoginWithCustomer");
		credential=loginPage.loginCredential().get("customer");
		credentialInfo=credential.split("_");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(credentialInfo[0]);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(credentialInfo[1]);
		
		WebElement element=driver.findElement(By.xpath("//input[@type='submit']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS:" +result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS:" +result.getThrowable());
			String screenshotPath=	extentRL.failedScreenshot(driver,result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
			extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
		}else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "TEST CASE IS SKIPPED IS:" +result.getName());
		}else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS:" +result.getName());
		}
		extent.endTest(extentTest);
		driver.quit();
	}
	
	
	

}
