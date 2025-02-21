package DouglasWebsiteAutomation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DouglasWebsiteAutomation.PageObjects.BasePage;
import DouglasWebsiteAutomation.PageObjects.SearchResultsPage;
import DouglasWebsiteAutomation.TestComponents.RetryTests;
import DouglasWebsiteAutomation.Utils.DriverFactory;



public class SearchResultsTests extends DriverFactory
{
	//public WebDriver driver;
	public SearchResultsPage searchResultsPage;
	
	
	
	@Test(dataProvider = "getData", groups= {"SearchResults"}, retryAnalyzer = RetryTests.class)
	public void testSearchPage(HashMap<String,String> input) throws InterruptedException
	{
		String searchItem = input.get("searchItem");
		String resultItem = input.get("resultItem");
		
		searchResultsPage = basePage.goToSearchPage(searchItem, resultItem);
		searchResultsPage.selectSearchItem(searchItem);
		
		Assert.assertTrue(searchResultsPage.isResultsDisplayed(), "Following item " + resultItem + " Should be displayed");
		
	}
	
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//DouglasWebsiteAutomation//data//SearchPageData.json");
		return new Object[][]  {{data.get(0)}, {data.get(1)}};
    }
    

}
