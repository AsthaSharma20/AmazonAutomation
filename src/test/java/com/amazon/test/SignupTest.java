package com.amazon.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class SignupTest {
	private static By name = By.xpath("//input[@id='ap_customer_name']");
	private static String username;
	//TestNGDefaultReport testNGDefaultReport;
	
	private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    	System.setProperty("webdriver.chrome.driver", "/Users/asthasharma/Desktop/SeleniumDriver/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() throws Exception {
    	takeScreenshot();
       driver.close();
    }

	@Test
	public void signup() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.amazon.com");
        String property = System.getProperty("user.dir");
       System.out.println("sys prop :"+property);
       // String search_text = "Google Search";     
	}

	public void takeScreenshot() throws Exception {
		String timeStamp;
	    File screenShotName;
	    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    //The below method will save the screen shot in d drive with name "screenshot.png"
	    timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
	    screenShotName = new File("/Users/asthasharma/eclipse-workspace/AmazonAutomation/Screenshots/"+timeStamp+".png");
	    FileUtils.copyFile(scrFile, screenShotName);
	}

}
