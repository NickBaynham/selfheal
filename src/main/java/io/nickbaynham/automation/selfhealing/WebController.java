package io.nickbaynham.automation.selfhealing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebController {
    private static WebDriver driver;

    public static void enterText(String locator, String text) {
        driver.findElement(By.cssSelector(locator)).sendKeys(text);
    }

    public static void click(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public static void chrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver_v78\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void quit() {
        if (driver != null) driver.quit();
    }

    public static String html() {
        if (driver != null) return driver.getPageSource();
        else return "<html></html>";
    }

    public static void get(String url) {
        driver.get(url);
    }
}
