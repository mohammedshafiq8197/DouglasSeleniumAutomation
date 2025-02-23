package DouglasWebsiteAutomation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DouglasWebsiteAutomation.PageObjects.FilterPage;
import DouglasWebsiteAutomation.TestComponents.RetryTests;
import DouglasWebsiteAutomation.Utils.DriverFactory;

public class FilterTests extends DriverFactory {
    //private WebDriver driver;
    private  FilterPage filterPage; 
    
    @Test(dataProvider = "getData", groups= {"filterTest"}, retryAnalyzer = RetryTests.class)
    public void testApplyFilter(HashMap<String,String> input) {
    	String filterName = input.get("filterName");
        int index = Integer.parseInt(input.get("dropdownIndex"));
        filterPage = basePage.goToFilterPage(filterName);
        filterPage.applyFilters(filterName, index);
                    
        
        // Verify that the filter is applied
        Assert.assertTrue(filterPage.verifyFilterApplied(), "Filter tags  should be applied");   
        Assert.assertTrue(filterPage.VerifyProductDisplay(filterName,input.get("filterType")), "Product '" + filterName + "' should be displayed");
    }
    
//    @Test(dependsOnMethods = {"testApplyFilter"})
//    public void checkFilterApplied()
//    {
//    	 Assert.assertTrue(filterPage.verifyFilterApplied(), "Filter tags  should be applied");   
//    }
//    
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//DouglasWebsiteAutomation//data//FiltersData.json");
		return new Object[][]  {{data.get(0)}, {data.get(1)}, {data.get(2)} };
    }
    

}
