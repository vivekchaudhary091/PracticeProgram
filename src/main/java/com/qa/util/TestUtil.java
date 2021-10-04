package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	
	public static long PAGE_LOAD_TIMEOUT=30;
	public static long IMPLICIT_WAIT=20;
	
	public Sheet sheet;
	public Workbook book;
	
	public String DATA_XML_PATH="C:\\eclipse\\eclipse_workspace\\PracticeProgram\\src\\main\\java\\com\\qa\\data\\FreeCrmTestData.xlsx";
	
	public Object[][] readDataFromExcel(String sheetname){
		
		FileInputStream file=null;
		
		try {
			file=new FileInputStream(DATA_XML_PATH);
		}catch(IOException ex) {
			ex.printStackTrace();
		}try {
			book=WorkbookFactory.create(file);
			
		}catch(InvalidFormatException ex) {
		ex.printStackTrace();	
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		sheet=book.getSheet(sheetname);
		
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0; i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
	public String screenshot() throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		String destination=System.getProperty("user.dir" +"/test-output/screenshot/" +".png");
		
		File finalDestination=new File(destination);
		
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
