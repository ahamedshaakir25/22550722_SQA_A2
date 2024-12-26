package testCases;

import org.testng.annotations.Test;
import pageObjects.productListingPage;
import testBase.testBaseClass;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class Test01_ProductListing extends testBaseClass {

    @Test
    public void verifyProductListDetails() {
        logger.info("**Starting TC01_ProductListingVerification**");

        try {
            // Initialize ProductListingPage
            productListingPage productListingPage = new productListingPage(driver);

            // Verify product list is displayed
            List<WebElement> products = productListingPage.getProducts();
            if (products.size() > 0) {
                logger.info("**TC01_Product list is displayed**");
                captureScreen("TC01_Product List is Displayed");
            } else {
                logger.info("**TC01_No products are displayed on the page.**");
                captureScreen("TC01_Product List is not Displayed");
                return; // Exit test as no products are displayed
            }

            // Verify each product's details (image, name, price)
            for (WebElement product : products) {
                // Verify product details through methods in ProductListingPage
                verifyProductDetails(productListingPage, product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("**Error occurred in TC01_ProductListingVerification**", e);
        }
    }

    private void verifyProductDetails(productListingPage productListingPage, WebElement product) {
        try {
            // Verify product image
            if (productListingPage.isProductImageDisplayed(product)) {
                logger.info("**TC01_Product image is displayed.**");
                captureScreen("TC01_Product image is Displayed");
            } else {
                logger.info("**TC01_Product image is not displayed.**");
                captureScreen("TC01_Product Image is not Displayed");
            }

            // Verify product name
            if (productListingPage.isProductNameDisplayed(product)) {
                logger.info("**TC01_Product name is displayed.**");
                captureScreen("TC01_Product Name is Displayed");
            } else {
                logger.info("**TC01_Product name is not displayed.**");
                captureScreen("TC01_Product Name is not Displayed");
            }

            // Verify product price
            if (productListingPage.isProductPriceDisplayed(product)) {
                logger.info("**TC01_Product price is displayed.**");
                captureScreen("TC01_Product Price is Displayed");
            } else {
                logger.info("**TC01_Product price is not displayed.**");
                captureScreen("TC01_Product Price is not Displayed");
            }
        } catch (IOException e) {
            logger.error("IOException occurred while capturing the screenshot", e);
        }
    }

}
