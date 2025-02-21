package DouglasWebsiteAutomation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DouglasWebsiteAutomation.PageObjects.BasePage;
import DouglasWebsiteAutomation.PageObjects.FilterPage;
import DouglasWebsiteAutomation.PageObjects.HomePage;
import DouglasWebsiteAutomation.PageObjects.PerfumePage;
import DouglasWebsiteAutomation.TestComponents.RetryTests;
import DouglasWebsiteAutomation.Utils.DriverFactory;

public class PerfumeTests extends DriverFactory {
    //private WebDriver driver;
    private PerfumePage perfumePage;
    //public BasePage basePage;
    
    @Test(dataProvider = "getData", groups= {"PerfumeTest"}, retryAnalyzer=RetryTests.class)
    public void testPerfumePage(HashMap<String,String> input) {
    	String categoryName = input.get("categoryName");
        String categoryLabel = input.get("categoryLabel");
       
        perfumePage = basePage.goToPerfumePage(categoryName,categoryLabel);
        perfumePage.selectCategory();
       
        Assert.assertTrue(perfumePage.verifyPerfumeListDisplay(), "Perfume List Should be displayed");
      
        // Verify that the filter is applied
        Assert.assertTrue(perfumePage.verifyCategoryLabelDisplay(), "Product '" + categoryLabel + "' should be displayed");
    }
    
    
    
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//DouglasWebsiteAutomation//data//PerfumePageData.json");
		return new Object[][]  {{data.get(0)}, {data.get(1)} };
    }
    

}
