package com.automationbookstore.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Config {

    public static Properties readPropertiesFile() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop;
    }
    public static WebDriver setup() throws IOException {
        Properties prop = readPropertiesFile();
        WebDriver driver;
        if(prop.get("capabilities").equals("remote")){
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), chromeOptions);
        }else{
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        String url = (String) prop.get("url");
        driver.get(url);
        return driver;
    }

    public static void teardown(WebDriver driver){
        driver.quit();
    }

}
