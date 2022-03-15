package com.automationbookstore.utilities;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

public class Utilities {
    WebDriver driver;

    public Utilities(WebDriver driver){
        this.driver = driver;
    }

    public WebDriverWait getWaitElement(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait;
    }

}
