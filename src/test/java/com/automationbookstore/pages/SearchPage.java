package com.automationbookstore.pages;
import com.automationbookstore.utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;
    Utilities u;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.u = new Utilities(this.driver);
        this.wait = u.getWaitElement();
        PageFactory.initElements(this.driver, this);
    }
    @FindBy(id = "searchBar")
    WebElement searchBar;

    @FindBy(tagName = "li")
    List<WebElement> books;

    @FindBy(id = "page-title")
    WebElement title;

    public void searchBook(String bookName){
        WebElement searchbarElement = wait.until(ExpectedConditions.visibilityOf( searchBar));
        searchbarElement.clear();
        searchbarElement.sendKeys(bookName);
    }

    public List<WebElement> getSearchedBooks() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> searchedBooks = books.stream()
                .filter((WebElement book) -> book.isDisplayed())
                .collect(Collectors.toList());
        return searchedBooks;
    }

    public String getPageTitle(){
        String titleText = title.getText();
        return titleText;
    }
}
