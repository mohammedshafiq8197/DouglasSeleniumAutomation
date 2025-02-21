package DouglasWebsiteAutomation.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import DouglasWebsiteAutomation.PageObjects.BasePage;
//import io.github.bonigarcia.wdm.WebDriverManager;
import DouglasWebsiteAutomation.PageObjects.HomePage;

public class DriverFactory {

	public WebDriver driver;
	public BasePage basePage;

	public WebDriver initializeDriver() throws IOException

	{
		// properties class

		 Properties properties = new Properties();
		FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//DouglasWebsiteAutomation//Utils//GlobalData.properties");
		properties.load(fileInput);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :properties.getProperty("browser");
		String baseUrl = System.getProperty("baseUrl")!=null ? System.getProperty("baseUrl") :properties.getProperty("baseUrl");
		//prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")){
			options.addArguments("headless");
			options.addArguments("--diisable-cookies");
			}		
			driver = new ChromeDriver(options);
			

		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxProfile ffProfile = new FirefoxProfile();
			driver = new FirefoxDriver();
			// Firefox
		} else if (browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	public FirefoxOptions setFireFoxProfileAndOptions(FirefoxProfile profile)
	{
		FirefoxOptions capabilities = new FirefoxOptions();
		
		profile.setPreference("browser.download.useDownloadDir", true);
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
		profile.setPreference("browser.download.manager.useWindow", false);
		
		capabilities.setProfile(profile);
		capabilities.setCapability("browserName", "Firefox");
		 HashMap<String, Object> ltOptions = new HashMap<String, Object>();
	        ltOptions.put("username", "");
	        ltOptions.put("accessKey", "");
	        ltOptions.put("platformName", "MacOS Sequoia");
	        ltOptions.put("browserVersion", "latest.0");
	        ltOptions.put("build", "Selenium Java Firefox Profile");
	        ltOptions.put("test", "Test 1");
	        ltOptions.put("w3c", true);
	        capabilities.setCapability("LT:Options", ltOptions);
		
		
		return capabilities;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	

	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public BasePage launchApplication() throws IOException, InterruptedException
	{
		
		 driver = initializeDriver();
		 basePage = new BasePage(driver);
		 basePage.goTo();
		 Thread.sleep(5000);
		 basePage.acceptCookieConsent();	
		return basePage;
	
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
