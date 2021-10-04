package com.qa.extentreports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.IReporter;
import com.relevantcodes.extentreports.model.Test;


public class ExtentReportsListner {
	
	WebDriver driver;
	ExtentReports extent;
	ExtentTest extentTest;
	
@BeforeTest
public void setExtent() {
	
	extent=new ExtentReports("user.dir" +"/test-output/Extent.html",true);
	extent.addSystemInfo("UserName:","Vivek Chaudhary");
	extent.addSystemInfo("Environment:","QA");
	extent.addSystemInfo("HostName:","Windows");
	
}

@AfterTest
public void endReport() {
	extent.flush();
	extent.close();
}

public String failedScreenshot(WebDriver driver,String screenshotName) throws IOException {
	
	String dateName=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
	
TakesScreenshot	ts=(TakesScreenshot)driver;
File source=ts.getScreenshotAs(OutputType.FILE);
String destination=System.getProperty("user.dir" +"/failedScreenshot/" +screenshotName +dateName + ".png");

File finalDestination=new File(destination);
FileUtils.copyFile(source, finalDestination);
return destination;

}

}
