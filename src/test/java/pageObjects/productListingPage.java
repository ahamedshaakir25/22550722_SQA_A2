package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class productListingPage extends BasePage {

    public productListingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(css = "div.product-item") // Replace with actual CSS selector for each product item
    private List<WebElement> productItems;

    @FindBy(css = "div.product-item img") // Replace with actual selector for product image
    private List<WebElement> productImages;

    @FindBy(css = "div.product-item .product-name") // Replace with actual selector for product name
    private List<WebElement> productNames;

    @FindBy(css = "div.product-item .product-price") // Replace with actual selector for product price
    private List<WebElement> productPrices;

    // Actions
    public List<WebElement> getProducts() {
        return productItems;
    }

    public boolean isProductImageDisplayed(WebElement product) {
        WebElement image = product.findElement(By.cssSelector("img")); // Adjust selector as needed
        return image.isDisplayed();
    }

    public boolean isProductNameDisplayed(WebElement product) {
        WebElement name = product.findElement(By.cssSelector(".product-name")); // Adjust selector as needed
        return name.isDisplayed();
    }

    public boolean isProductPriceDisplayed(WebElement product) {
        WebElement price = product.findElement(By.cssSelector(".product-price")); // Adjust selector as needed
        return price.isDisplayed();
    }
}
