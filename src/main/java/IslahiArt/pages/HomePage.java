package IslahiArt.pages;


import IslahiArt.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    // --- 1. Page Factory - OR (Object Repository) ---

    // The Search Icon (The SVG you found)
    @FindBy(xpath = "//*[local-name()='svg' and @class='dgwt-wcas-ico-magnifier-handler']")
    WebElement searchIcon;

    // The Main Logo (Using the class you found)
    @FindBy(xpath = "//img[@class='custom-logo']")
    WebElement mainLogo;

    // The Login/Account Icon
    // Strategy: Since the <circle> is hard to click, we target the Link (<a>) 
    // that contains "my-account" in the URL. This is standard for this website type.
    @FindBy(xpath = "//a[contains(@href, 'my-account')]")
    WebElement loginLink;
    
    @FindBy(id = "dgwt-wcas-search-input-1")
    WebElement searchInputBox;
    
    // --- 2. Constructor ---
    // This initializes the elements above when the class is called
    public HomePage() {
        // Change 'driver' to 'getDriver()'
        PageFactory.initElements(getDriver(), this);
    
    }

    // --- 3. Actions / Methods ---

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public boolean validateLogo() {
        return mainLogo.isDisplayed();
    }

    public void clickSearch() {
        searchIcon.click();
    }

    public void clickLogin() {
        loginLink.click();
    }
    public SearchResultsPage searchForProduct(String productName) {
    	searchIcon.click(); 
        searchInputBox.clear(); // Good practice to clear before typing
        searchInputBox.sendKeys(productName);
        searchInputBox.submit();  // We already found this locator earlier!
        // Return the Next Page Object
        return new SearchResultsPage();
    }
}