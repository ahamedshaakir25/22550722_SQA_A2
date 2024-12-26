package testCases;

import org.testng.annotations.Test;
import pageObjects.AddtoCartPage;
import testBase.testBaseClass;

public class Test03_AddtoCart extends testBaseClass {

    @Test
    public void addProductToCart() {
        logger.info("**Starting AddToCartTest**");

        try {
            // Initialize AddtoCartPage
            AddtoCartPage addToCartPage = new AddtoCartPage(driver);

            // Step 1: Search for the product and press Enter
            logger.info("**Searching and simulating Enter key for product: Spa Ceylon Undersea Paradise**");
            addToCartPage.pressEnterToSearch("Spa Ceylon Undersea Paradise");
            captureScreen("Test03_SearchProduct");

            // Step 2: Select the first product from the search results
            logger.info("**Selecting the first product from search results**");
            addToCartPage.selectFirstProduct();
            captureScreen("Test03_SelectFirstProduct");

            // Step 3: Add the product to the cart
            logger.info("**Adding product to the cart**");
            addToCartPage.addToCart();
            captureScreen("Test03_AddToCart");

            // Step 4: Continue shopping
            logger.info("**Continuing shopping after adding to the cart**");
            addToCartPage.continueShopping();
            captureScreen("Test03_ContinueShopping");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("**Error occurred in Test03_AddToCartTest**", e);
        }
    }
}
