package com.amazon.test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BasicExtentReport {
	ExtentHtmlReporter htmlReporter;
	private WebDriver driver;
    
    ExtentReports extent;
    //helps to generate the logs in test report.
    ExtentTest test;
    ITestResult result;

    @BeforeTest
	public void verifySeleniumBlog() {
	    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	    
	    extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
    	System.setProperty("webdriver.chrome.driver", "/Users/asthasharma/Desktop/SeleniumDriver/chromedriver");

		// Launch the FireFox browser.
		WebDriver driver = new ChromeDriver();
		extent.setSystemInfo("Browser", "chrome");

		driver.manage().window().maximize();
		

		 driver.get("http://www.google.com");

		driver.close();
		
		//test.log(Status.INFO, "Browser closed");

		// close report.
		extent.attachReporter(htmlReporter);
		
		//extent.createTest(getClass().getName());
		// writing everything to document.
		//extent.flush();
	}
	@AfterMethod
    public void getResult(ITestResult result) {
		String expectedResults= "not done";
		String actualResults= "done";
		String[][] resultArray = new String[4][4];
		resultArray[0][0] = "Test Scenario";
		resultArray[0][1] = "Actual";
		resultArray[0][2] = "Expected";
		resultArray[0][3] = "Pass/Fail";
		
		/*
		for(int i=1;i<resultArray.length;i++) {
			for(int j=0;j<resultArray[i].length;j++) {
				resultArray[i][j]=
			}
		}
		*/
		
        if(result.getStatus() == ITestResult.FAILURE) {
            //test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.log(Status.FAIL, MarkupHelper.createTable(resultArray));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            //test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
            test.log(Status.FAIL, MarkupHelper.createTable(resultArray));

        }
        else {
            //test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }  
        
    }

     
    @AfterTest
    public void tearDown() {
    	//to write or update test information to reporter
        extent.flush();
    }
    
    @Test
    public void testCase1() {
        test = extent.createTest("Test Case 1", "PASSED test case");
        Assert.assertTrue(true);
    }
	
}