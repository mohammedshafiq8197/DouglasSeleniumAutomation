package DouglasWebsiteAutomation.CommonComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DouglasWebsiteAutomation.PageObjects.BasePage;
import DouglasWebsiteAutomation.PageObjects.PerfumePage;


public class CommonComponents{
	
	WebDriver driver;
	
	
	public CommonComponents(WebDriver driver)
	{
		
		this.driver = driver;
	}
	
	public WebElement find_Element(By by)
	{
		
		WebElement element = this.driver.findElement(by);
		return element;
	}
	
	public List<WebElement> find_Elements(By by)
	{
		List<WebElement> elements = this.driver.findElements(by);
		return elements;
	}
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	public void waitForWebElementToBeClickabler(By findBy) {

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
		
	WebElement acceptAllButton =	wait.until(ExpectedConditions.elementToBeClickable(findBy));
	  acceptAllButton.click();

	}
	
	public Boolean isSelected(By by)
	{
	
		return true;
		
	}
	
	public Boolean isElementPresent(By by)
	{
	WebElement elementToCheck = find_Element(by);
		
		if(elementToCheck != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

}
