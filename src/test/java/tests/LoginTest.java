package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest{
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException {
		String filePath=System.getProperty("user.dir")+"/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "sheet1");
		int rowCount= ExcelUtils.getRowCount();
		Object[][] data=new Object[rowCount-1][2];
		for(int i=1;i<rowCount;i++) {
			
			data[i-1][0]=ExcelUtils.getCellData(i,0);  //username
			data[i-1][1]=ExcelUtils.getCellData(i, 1);  //password
			
		}
		ExcelUtils.closeExcel();
		return data;
	
	}
	
	@DataProvider(name="LoginData2")
	public Object[][] getData(){
		
		return new Object[][] {
			
			{"user1","pass1"},
			{"user2","pass2"},
			{"user3","pass3"}
			
		};
	}
	
	
	//@Test(dataProvider = "LoginData2")
	
	//@Test
	//@Parameters({"username","password"})
	@Test
	public void testValidLogin() {
		
		Log.info("Start Loging Test...");
		test=ExtentReportManager.createTest("Login Test" );
		
		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);
		
		Log.info("Adding Credentials..");
		test.info("Adding Credentials");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		
//		loginPage.enterUsername(username);
//		loginPage.enterPassword(password);
		test.info("Clicking on Login Button");
		loginPage.clickLogin();
		
		System.out.println("Title of the page is : " +driver.getTitle());
		Log.info("Verifying Page Title");
		test.info("Verifying page title");
		
		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
		test.pass("Login Successfully");
		//Assert.assertEquals(driver.getTitle(), "Just a moment...");
		//driver.quit();
	}
//@Test	
//public void testLoginWithInvalidCredentials() {
//		
//		Log.info("Start Loging Teast...");
//		test=ExtentReportManager.createTest("Login Test with Invalid Credentials");
//		
//		test.info("Navigating to URL");
//		LoginPage loginPage = new LoginPage(driver);
//		
//		Log.info("Adding Credentials..");
//		test.info("Adding Credentials");
//		loginPage.enterUsername("admin123@yourstore.com");
//		loginPage.enterPassword("admin123");
//		test.info("Clicking on Login Button");
//		loginPage.clickLogin();
//		
//		System.out.println("Title of the page is : " +driver.getTitle());
//		Log.info("Verifying Page Title");
//		test.info("Verifying page title");
//		
//		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//		test.pass("Login Fail");
//		//Assert.assertEquals(driver.getTitle(), "Just a moment...");
//		//driver.quit();
//	}

}
