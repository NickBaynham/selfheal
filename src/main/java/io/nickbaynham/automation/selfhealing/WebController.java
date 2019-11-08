package io.nickbaynham.automation.selfhealing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class WebController {
    private static WebDriver driver;

    public static void injectPopover(String locator, String label, String text, String tag) {
        String popoverScript =
                "arguments[0].setAttribute('data-content', 'CSS Locator is " + locator + "');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(popoverScript, driver.findElement(By.cssSelector(locator)));

        popoverScript =
                "arguments[0].setAttribute('data-original-title', 'Enter %1 into %2 labelled as %3');"
                    .replace("%1", text)
                    .replace("%2", tag)
                    .replace("%3", label);
        js = (JavascriptExecutor) driver;
        js.executeScript(popoverScript, driver.findElement(By.cssSelector(locator)));

        // Mouse over to show the popover

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(locator));
        actions.moveToElement(element).perform();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void highlight(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow;');", driver.findElement(By.cssSelector(locator)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', 'background: white;');", driver.findElement(By.cssSelector(locator)));
    }

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
