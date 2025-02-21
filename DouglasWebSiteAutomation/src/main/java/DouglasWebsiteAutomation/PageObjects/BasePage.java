package DouglasWebsiteAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.sukgu.*;

import DouglasWebsiteAutomation.CommonComponents.CommonComponents;

public class BasePage {
	
	WebDriver driver;
	//private By cookieAcceptButton = By.xpath("//div[@data-testid='uc-buttons-container']//button[@data-testid='uc-accept-all-button']");
	private By cookieAcceptButton = By.cssSelector("button[data-testid*='accept-all");
	private By shadowHostElement = By.xpath("//div[@id= 'usercentrics-root']");
	JavascriptExecutor jsScript;
	//Shadow shadow;
	CommonComponents commonComponents;
	
	public BasePage(WebDriver driver)
	{
		
		this.driver = driver;
		this.commonComponents =new CommonComponents(this.driver);
		//this.shadow = new Shadow(driver);
	}
	
    public void goTo() 
    {
        driver.get("https://www.douglas.de/de");
    }
    
     public SearchResultsPage goToSearchPage(String searchItem, String resultItem)
     {
    	 return new SearchResultsPage(driver, searchItem, resultItem);
     }
     
     public PerfumePage goToPerfumePage(String categoryName,String categoryLabel)
     {
    	 return new PerfumePage(driver,categoryName,categoryLabel);
     }
     public FilterPage goToFilterPage(String filterName)
     {
    	 return new FilterPage(driver,filterName);
     }
	
	public void elementClick(By by)
	{
		commonComponents.waitForElementToAppear(by);
		commonComponents.find_Element(by).click();
	}
	
	public void elementClick(WebElement element)
	{
		commonComponents.waitForWebElementToAppear(element);
		element.click();
	}
	
	public void sendKeys(By by, String valueToEnter)
	{
		commonComponents.waitForElementToAppear(by);
		commonComponents.find_Element(by).sendKeys(valueToEnter);
		
	}
	
	public void scrollToViewElement(WebElement elementToScroll)
	{
		jsScript = (JavascriptExecutor)driver;
		jsScript.executeScript("arguments[0].scrollIntoView();", elementToScroll);
	}
	
	public void acceptCookieConsent() throws InterruptedException
	{
		
		WebElement shadowHost = commonComponents.find_Element(shadowHostElement);
		SearchContext shadowRoot = shadowHost.getShadowRoot();
		WebElement acceptCookies = shadowRoot.findElement(cookieAcceptButton);
		acceptCookies.click();	
		Thread.sleep(3000);
		
	}
	
	public WebElement getElementFromList(By by, int index)
	{
		WebElement element = commonComponents.find_Elements(by).get(index);
		return element;
	}
	public List<WebElement> getElementList(By by)
	{
		List<WebElement> element = commonComponents.find_Elements(by);
		return element;
	}
	public WebElement getElement(By by)
	{
		WebElement element = commonComponents.find_Element(by);
		return element;
	}
	
	public void scrollDown()
	{
		jsScript = (JavascriptExecutor)driver;
		jsScript.executeScript("window.scrollBy(0,1000)");
	}
	public void jsExecutorOperations(WebElement element, String operationToPerform)
	{
		jsScript = (JavascriptExecutor)driver;
		
		switch(operationToPerform)
		{
		case "click":
			jsScript.executeScript("arguments[0].click();", element);
			break;
		case "remove":
			jsScript.executeScript("arguments[0].parentNode.removeChild(arguments[0]);", element);
			break;
		case "scrollBy":
			jsScript.executeScript("window.scrollBy(0,1000)");
			
		}
	}
	public void mouseHover(By by)
	{
		WebElement elementToHover = commonComponents.find_Element(by);
		Actions action = new Actions(driver);
		commonComponents.waitForWebElementToAppear(elementToHover);
		action.moveToElement(elementToHover).build().perform();
	}
	
	public void selectDropdownValue(WebElement selectElement,String valueType,String textOrValue)
	{
		Select selectDropDown = new Select(selectElement);
		
		switch(valueType) 
		{
		  
		case "value":
			selectDropDown.selectByValue(textOrValue);
			break;
			
		case "text":
			selectDropDown.selectByVisibleText(textOrValue);
			break;
			
		case "index":
			selectDropDown.selectByIndex(Integer.parseInt(textOrValue));
			break;
		default:
			
			
		
		}
	}
}
