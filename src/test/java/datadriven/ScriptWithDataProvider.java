package datadriven;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import dataproviders.RegistrationData;
import static org.testng.AssertJUnit.assertEquals;

public class ScriptWithDataProvider extends BaseTest {

    @Test(dataProvider = "registration", dataProviderClass= RegistrationData.class)
    public void DataDrivenSeleniumTest(String firstName, String lastName, String username, String city, String state, String zip, boolean acceptTerms) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/mac/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:7800/bootstrap1.html#");
        driver.findElement(By.xpath("//label[contains(.,'First name')]/../input")).sendKeys(firstName);
        driver.findElement(By.xpath("//label[contains(.,'Last name')]/../input")).sendKeys(lastName);
        driver.findElement(By.xpath("//label[contains(.,'Username')]/..//input")).sendKeys(username);
        driver.findElement(By.xpath("//label[contains(.,'City')]/../input")).sendKeys(city);
        driver.findElement(By.xpath("//label[contains(.,'State')]/../input")).sendKeys(state);
        driver.findElement(By.xpath("//label[contains(.,'Zip')]/../input")).sendKeys(zip);
        if (acceptTerms) driver.findElement(By.xpath("//label[contains(.,'Agree to terms and conditions')]/../input")).click();
        driver.findElement(By.xpath("//button[contains(.,'Register')]")).click();
        assertEquals(driver.getTitle(), "Self Healing Test Page");
        driver.quit();
    }
}