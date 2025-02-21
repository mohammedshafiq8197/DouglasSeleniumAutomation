package DouglasWebsiteAutomation.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import DouglasWebsiteAutomation.CommonComponents.CommonComponents;

public class SearchResultsPage extends BasePage {
	
	public WebDriver driver;
	CommonComponents commonComponents;
	String searchItem;
	String resultItem;
	
	//private By searchInputText = By.xpath("(//div[contains(@class,'typeAhead-search')])[1])");
	 private By searchInput = By.id("typeAhead-input"); 
	
    private By searchResultItem; 
  
	private By resultsContainer; 

	    public SearchResultsPage(WebDriver driver, String searchItem, String resultItem)
	    {
	    	super(driver);
	        this.driver = driver;
//	        this.searchItem = searchItem;
//	        this.resultItem = resultItem;
	        this.searchResultItem = By.xpath(("(//div[contains(@data-testid,'text-suggest')]//*[contains(text(),'"+ searchItem+"')])[1]"));
	        this.resultsContainer= By.xpath("(//*[contains(text(),'"+ resultItem+"')])[1]");
	        this.commonComponents = new CommonComponents(this.driver);
	    }
	    public void selectSearchItem(String valueToEnter) throws InterruptedException
	    {
	    	//commonComponents.find_Element(searchInput).click();
	    	elementClick(searchInput);
	    	sendKeys(searchInput, valueToEnter);
	    	Thread.sleep(2000);
	    	System.out.println(searchResultItem);
	    	commonComponents.find_Element(searchResultItem).click();
	    }


	    public boolean isResultsDisplayed(){
	    
	        return commonComponents.find_Element(resultsContainer).isDisplayed();
	    }
	    
	 
}
