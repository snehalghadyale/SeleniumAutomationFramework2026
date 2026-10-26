package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	
	@BeforeSuite
	public void setupReport() {
		extent=ExtentReportManager.getReportInstance();
	}
	@AfterSuite
	public void tearDownReport() {
		extent.flush();
		String reportPath=ExtentReportManager.reportPath;
		EmailUtils.sendTestReport(reportPath);
	}

	

	@BeforeMethod
	public void setUp() {

		Log.info("Starting WebDriver...");
		ChromeOptions options = new ChromeOptions();
		//jzdq ttxe kwiw jqyd  app password
		// Git Hub linl of full code
		// https://github.com/Raghav-Pal/SeleniumAutomationFramework2025
		// start chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\selenium_chrome_profile" Open command prompt and run this
		// command:-
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
		driver = new ChromeDriver(options);
		// driver=new ChromeDriver();
		//driver.manage().window().maximize();
		Log.info("Navigating to URL....");
		driver.get("https://admin-demo.nopcommerce.com/login");

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()== ITestResult.FAILURE) {
			String screenshotPath= ExtentReportManager.captureScreenshot(driver, "Login Failure");
			test.pass("Test Failed..Check Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			
		}
		
		
		if (driver != null) {

			Log.info("Closing Browser...");
			driver.quit();
		}
	}

}
