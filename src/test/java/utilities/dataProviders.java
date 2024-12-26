package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class dataProviders {

    // data provider 1
    @DataProvider(name = "SearchData")
    public String[][] getData() throws IOException {
        String path = "/TestData/SearchData.xlsx";  // taking xcel file from test data

        Excelutility xlutil = new Excelutility(path); // creating an object for xlutility

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        String searchdata[][] = new String[totalrows][totalcols]; //created two dimension array which can store

        for (int i = 1; i <= totalrows; i++) //read the data from string in two dimensional array
        {
            for (int j = 0; j < totalcols; j++) //i is rows j is col
            {
                searchdata[i - 1][j] = xlutil.getCellData("sheet1", i, j);
            }
        }
        return searchdata; //returning two dimension array
    }


}

