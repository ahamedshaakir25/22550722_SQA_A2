package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class searchPage extends BasePage {

    public searchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//*[@id='search_bar_id']")
            WebElement searchbar;

    @FindBy(xpath = "//*[@id='search_btn']")
    WebElement clickSearch;

    @FindBy(css = "div.product-item")
    private List<WebElement> productItems;

    // Action Methods

    // Method to search a product by its name
    public void searchProduct(String productName) {
        searchbar.clear();
        searchbar.sendKeys(productName);
        clickSearch.click();
    }

    // Method to get the list of product items after search
    public List<WebElement> getProductItems() {
        return productItems; // Return the product items list
    }
}
