package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class viewCartPage extends AddtoCartPage {

    // Constructor to initialize page elements
    public viewCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Cart icon element
    @FindBy(xpath = "//*[@id='cart']")
    WebElement cart;

    // Element to verify the price of the items in the cart
    @FindBy(xpath = "//span[contains(text(),'Rs.')]")
    WebElement cartPrice;

    // Checkout button element
    @FindBy(xpath = "//*[@id=\"chkoutbutton\"]/button")
    WebElement checkoutButton;

    // Method to check the cart items and prices
    public void clickCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the cart element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(cart));

        try {
            // Regular click
            cart.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if the normal click fails
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", cart);
        }
    }

    // Method to get the cart price after waiting for visibility
    public String getCartPriceText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the cart price element to be visible
        wait.until(ExpectedConditions.visibilityOf(cartPrice));

        return cartPrice.getText();
    }

    // Method to click on the checkout button
    public void clickCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the checkout button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        checkoutButton.click();
    }
}
