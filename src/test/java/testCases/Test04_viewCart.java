package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageObjects.viewCartPage;

public class Test04_viewCart extends Test03_AddtoCart {

    @Test
    public void verifyViewCart() {
        viewCartPage cartPage = new viewCartPage(driver);

        cartPage.clickCart();

        // Verify the cart price
        String cartPriceText = cartPage.getCartPriceText();
        Assert.assertTrue(cartPriceText.contains("Rs. 9500"), "Cart price is incorrect.");

        // Optionally, you can add assertions after clicking the checkout to verify the next page.
        cartPage.clickCheckout();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
