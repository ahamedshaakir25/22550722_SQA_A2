package testCases;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.searchPage;
import testBase.testBaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test02_search extends testBaseClass {

    private searchPage searchPage;

    @BeforeMethod
    public void setUp() {
        // Initialize the searchandAddtocartPage object before each test
        searchPage = new searchPage(driver);
    }

    @DataProvider(name = "productData")
    public Iterator<Object[]> productData() throws IOException {
        // Update the file path here
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String filePath = "./TestData/SearchData.xlsx"; // Relative path or use absolute path if needed

        // Read Excel file
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Sheet sheet = WorkbookFactory.create(fileInputStream).getSheet("Sheet1"); // Ensure sheet name is correct

        // List to hold product names
        List<Object[]> productNames = new ArrayList<>();

        // Get the row iterator
        Iterator<Row> rowIterator = sheet.iterator();

        // Iterate through rows and get product names
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String productName = row.getCell(0).getStringCellValue(); // Get the product name from the first column
            productNames.add(new Object[] { productName });
        }

        fileInputStream.close();

        // Return an iterator for the product data
        return productNames.iterator();
    }

    @Test(dataProvider = "productData")
    public void testSearch(String productName) {
        logger.info("**Starting testSearch: " + productName + "**");

        try {
            // Search for the product using the Excel data
            searchPage.searchProduct(productName);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


            logger.info("**Successfully Searched the Product**");

            captureScreen("TC02_Searched the Product");


        } catch (Exception e) {
            logger.error("**Error occurred during testSearch**", e);
            Assert.fail("Test failed due to an exception.");

        }

        logger.info("**testSearch completed successfully**");
    }
}