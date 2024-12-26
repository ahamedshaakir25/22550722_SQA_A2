package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddtoCartPage extends BasePage {

    public AddtoCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Web elements
    @FindBy(xpath = "//*[@id='search_bar_id']")
    WebElement searchbar;

    @FindBy(xpath = "//*[@id='search_btn']")
    WebElement clickSearch;

 //   @FindBy(xpath = "//div[@class='catalogueV2Repeater']//div[contains(@class, 'catalogueV2heading') and text()='Spa Ceylon Ceylon Tea Home Spa Set']")
   // WebElement productLink;

    @FindBy(xpath = "//*[@id='addtocartbutton']")
    WebElement addToCartButton;

    @FindBy(xpath = "//*[@id='continue_shopping_popup']")
    WebElement continueShoppingButton;

    @FindBy(xpath = "(//div[@class='catalogueV2textBlock'])[1]")
    WebElement firstProduct;

    // Methods
    public void pressEnterToSearch(String productName) {
        searchbar.clear();
        searchbar.sendKeys(productName + "\uE007"); // Simulate Enter key

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout

        try {
            handlePopupIfExists(); // Handle popups before proceeding
          //  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'/buyonline/spa-ceylon-undersea-paradise')]")));
            //wait.until(ExpectedConditions.visibilityOf(productLink));
        } catch (TimeoutException e) {
            throw new RuntimeException("Product link did not appear. Verify the XPath and page behavior.", e);
        }
    }


    public void searchProduct(String productName) {
        searchbar.sendKeys(productName);
        clickSearch.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     //   wait.until(ExpectedConditions.visibilityOf(productLink)); // Ensure search results are displayed
    }

    public void handlePopupIfExists() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement popup = wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
            if (popup.isDisplayed()) {
                popup.click();
            }
        } catch (Exception e) {
            // Ignore if popup is not found
        }
    }


    public void selectFirstProduct() {
        handlePopupIfExists(); // Handle any popups before interacting with the page

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait until the element is present in the DOM and visible
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='catalogueV2textBlock'])[1]")));
            wait.until(ExpectedConditions.visibilityOf(firstProduct));

            // Scroll the element into view
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", firstProduct);

            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(firstProduct));

            // Perform an action-based click
            Actions actions = new Actions(driver);
            actions.moveToElement(firstProduct).click().perform();
        } catch (Exception e) {

            // Fallback: Attempt JavaScript click
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", firstProduct);
            } catch (Exception jsEx) {
                throw new RuntimeException("Failed to click the first product using fallback method.", jsEx);
            }
        }
    }





    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

    public void continueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }
}
