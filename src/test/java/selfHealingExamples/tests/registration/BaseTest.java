package selfHealingExamples.tests.registration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Selenium/chromedriver");
        driver.set(new ChromeDriver());
    }

    @AfterMethod
    public void wrapUp() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
    WebDriver getDriver() {
        return driver.get();
    }
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
}
