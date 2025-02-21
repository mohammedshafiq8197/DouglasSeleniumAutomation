package DouglasWebsiteAutomation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HomePage extends BasePage{
	
	//public WebDriver driver;
	
	
    private By searchInput = By.id("typeAhead-input"); 
   
    public HomePage(WebDriver driver)
    {
    	super(driver);
        this.driver = driver;
    }

    public void goTo() 
    {
        driver.get("https://www.douglas.de/de");
    }
    

    
  



}
