package DouglasWebsiteAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import DouglasWebsiteAutomation.CommonComponents.CommonComponents;

public class FilterPage extends BasePage {
	
	WebDriver driver;
	CommonComponents commonCommponents; 
	String filterName;
	
	private By perfumeMainMenu = By.cssSelector("[href*='/parfum/01']:first-of-type");
	private By searchField = By.xpath("(//input[@class=\"input__input\"])[1]");
	private By filterDropdown = By.xpath("//div[@class='facet__title']");
	private By itemToSelect; 
	private By filterCloseButton = By.cssSelector("[class*=facet__close-button]");
	private By productType = By.xpath("//div[@class='text category']");
	//private By itemSelected = By.xpath("//a[@role='checkbox' and  @aria-checked='true']");
	private By products = By.xpath("//div[@class='text top-brand']");
	private By filterTags = By.xpath("//button[@class='selected-facets__value']");
	//private By products = By.xpath("(//div[@class='text top-brand' and contains(text(),"+filterName+")])[1]");
	
	
	public FilterPage(WebDriver driver, String filterName)
	{
		super(driver);
		this.driver = driver;
		this.commonCommponents= new CommonComponents(this.driver);
		//this.filterName = filterName;
		this.itemToSelect= By.xpath("//div[@class='rc-scrollbars-view']//*[contains(text(),'"+filterName+"')]");
	}


	public void applyFilters(String searchItem, int index)
	{
		try
		{
			elementClick(perfumeMainMenu);
			WebElement elementToScroll = getElement(filterDropdown);
			scrollToViewElement(elementToScroll);
			WebElement filterToSelect = commonComponents.find_Elements(filterDropdown).get(index);
			elementClick(filterToSelect);
			sendKeys(searchField, searchItem);
			commonCommponents.waitForElementToAppear(itemToSelect);
			elementClick(itemToSelect);
		    Boolean isElementPresent = commonCommponents.isElementPresent(filterCloseButton);
		 
			 if(isElementPresent)
			 {
				elementClick(filterCloseButton);
			 }
				
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public Boolean verifyFilterApplied()
	{
		//scrollDown();
		List<WebElement> filterList = commonCommponents.find_Elements(filterTags);
		 
		 if(filterList.size() > 0)
		 {
			 return true;
		 }
		 else
		 {
		     return false;
		 }
				
	}
	public Boolean VerifyProductDisplay(String filterName, String filterType)
	{
		List<WebElement> prodList = this.getProductList(filterType);
		
		if(prodList.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
				
	}
	
	public List<WebElement> getProductList(String filterType) 
	{
		
		List<WebElement> productResults = null;
		
		
		if(filterType.equalsIgnoreCase("brand"))
		{
			productResults = getElementList(productType);
		}
		else
		{
			productResults = getElementList(products);
		
		}
		//match = productResults.stream().anyMatch(product -> product.getText().equalsIgnoreCase(filterName));
		return productResults;

	}
}
