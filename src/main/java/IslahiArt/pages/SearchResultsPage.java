package IslahiArt.pages;

import IslahiArt.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends TestBase {

    // Locator for the first product title found
    @FindBy(xpath = "//h2[contains(@class, 'woocommerce-loop-product__title')]")
    WebElement firstProductTitle;

    public SearchResultsPage() {
        PageFactory.initElements(getDriver(), this);
    
    }

    // Validation Method: Check if at least one product is displayed
    public boolean isProductAvailable() {
        return firstProductTitle.isDisplayed();
    }
}
