package DouglasWebsiteAutomation.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import DouglasWebsiteAutomation.CommonComponents.CommonComponents;
import DouglasWebsiteAutomation.Utils.DriverFactory;


public class PerfumePage extends BasePage {
	
	public WebDriver driver;
	String categoryName;
	String categoryLabel;
	CommonComponents commonCommponents;
	
	
	private By perfumeMainMenu = By.cssSelector("[href*='/parfum/01']:first-of-type");
	private By clickOnCategoryLink; 
	private By perfumeList = By.xpath("//div[contains(@class,'product-tile')]");
	private By perfumeListDisplay;
	
	
	
	
	public PerfumePage(WebDriver driver, String categoryName,String categoryLabel)
	{
		super(driver);
		this.driver = driver;
//		this.categoryName = categoryName;
//		this.categoryLabel = categoryLabel;
		this.clickOnCategoryLink = By.xpath("//div[contains(@class,'subcategory-link')]//*[text()='"+ categoryName +"']");
		this.perfumeListDisplay = By.xpath("(//div[contains(@class,'tile__label')] //*[text()='"+categoryLabel+"'])[1]");
		this.commonComponents = new CommonComponents(this.driver);
	}

	
	public void selectCategory()
	{
		
		//WebElement perfumeElement = this.commonCommponents.find_Element(perfumeMainMenu);
		mouseHover(perfumeMainMenu);
		elementClick(clickOnCategoryLink);
		WebElement elementToView = getElementFromList(perfumeListDisplay, 0);
		scrollToViewElement(elementToView);		
		
	}
	
	public Boolean verifyPerfumeListDisplay()
	{
		List<WebElement> perfumelist = getElementList(perfumeList);
		Boolean isDisplayed;
		
		 if(perfumelist.size() > 0)
		 {
			 isDisplayed = true;
		 }
		 else
		 {
			 isDisplayed = false;
		 }
		
		 return isDisplayed;
	
	}
	
	
	public Boolean verifyCategoryLabelDisplay()
	{
//		List<WebElement> perfumelist = getElementList(perfumeListDisplay);
//		Boolean match = false;
//		
//		List<String> labelList = perfumelist.stream().map(product -> product.getText()).collect(Collectors.toList());
//		
//		labelList.forEach(label -> System.out.println(label));
//		return true;
		return getElement(perfumeListDisplay).isDisplayed();
		
	
	}

	   
}
