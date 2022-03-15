package com.automationbookstore.tests;
import com.automationbookstore.base.Config;
import com.automationbookstore.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

public class SearchTest extends Config {

    public WebDriver driver;


    @BeforeTest
    void beforeTest(ITestContext context) throws IOException {
        this.driver = setup();
        context.setAttribute("WebDriver", this.driver);
    }

    @AfterTest
    void afterTest()
    {
        teardown(this.driver);
    }

    @Test(dataProvider = "getExcelData", dataProviderClass = com.automationbookstore.dataprovider.GetExcelData.class)
    public void searchTest(String bookName) throws InterruptedException {
        SearchPage sp = new SearchPage(this.driver);
        sp.searchBook(bookName);
        Assert.assertTrue(sp.getSearchedBooks().size() > 0);
    }

}
