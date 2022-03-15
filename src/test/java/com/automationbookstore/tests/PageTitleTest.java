package com.automationbookstore.tests;
import com.automationbookstore.base.Config;
import com.automationbookstore.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class PageTitleTest extends Config {

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

    @Test
    public void pageTitleTest(){
        SearchPage sp = new SearchPage(this.driver);
        Assert.assertTrue(sp.getPageTitle().equals("Automation Bookstore"));
    }
}
