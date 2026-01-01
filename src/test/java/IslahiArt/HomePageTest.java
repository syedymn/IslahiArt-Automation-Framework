package IslahiArt;
import IslahiArt.base.TestBase;
import IslahiArt.pages.HomePage;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import IslahiArt.pages.SearchResultsPage;
import org.testng.annotations.DataProvider;

public class HomePageTest extends TestBase {

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    
    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return new Object[][] {
            { "Calligraphy" },  // Row 1
            { "Canvas" },       // Row 2
            { "Tshirt" }         // Row 3
        };
    }

    public HomePageTest() {
        super(); // Calls the TestBase constructor (if we had one)
    }

    @BeforeMethod
    public void setUp() {
        // 1. Initialize Driver and open URL
        initialization(); 
        // 2. Create Object of HomePage so we can use its methods
        homePage = new HomePage();
    }

    @Test(priority = 1)
    public void verifyLogoTest() {
        boolean flag = homePage.validateLogo();
        Assert.assertTrue(flag, "Logo is not displayed on the Homepage!");
    }

    @Test(priority = 2)
    public void verifyTitleTest() {
        String title = homePage.getPageTitle();
        System.out.println("Page Title is: " + title);
        // We verify the title contains "Islahi Art"
        Assert.assertTrue(title.contains("Islahi Art"), "Title does not match!");
    }
    
    @Test(priority = 3, dataProvider = "searchData")
    public void searchTest(String productName) { // 2. Add Argument to catch the data
        
        System.out.println("Testing search for: " + productName);

        // 3. Use the variable instead of "Calligraphy"
        searchResultsPage = homePage.searchForProduct(productName);
        
        // Validation 1: Check URL
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(productName), "URL Wrong for " + productName);

        // Validation 2: Check Product exists
        boolean productExists = searchResultsPage.isProductAvailable();
        Assert.assertTrue(productExists, "No products found for " + productName);
        //Assert.assertFalse(productExists, "No products found for " + productName);
    }
    
    @AfterMethod
    public void tearDown() {
    	super.tearDown(); 
    }
}