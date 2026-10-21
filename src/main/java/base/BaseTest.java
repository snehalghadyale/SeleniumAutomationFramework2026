package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
        
		ChromeOptions options = new ChromeOptions();
        
		//Open command prompt and run this command:- start chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\selenium_chrome_profile"
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222"); 
		driver=new ChromeDriver(options);
		//driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://admin-demo.nopcommerce.com/login");

	}
	 @AfterMethod
	public void tearDown() {
		
			//driver.quit();
		
	}
	
	
}
