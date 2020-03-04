package selfHealingExamples.tests.registration;

import io.nickbaynham.automation.selfhealing.controllers.WebController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import selfHealingExamples.dataProviders.RegistrationData;
import static org.testng.AssertJUnit.assertEquals;

public class RegistrationDD extends BaseTest {

    @Test(dataProvider = "registration", dataProviderClass= RegistrationData.class)
    public void TestRegistration(String firstName, String lastName, String username, String city, String state, String zip, boolean acceptTerms) {
        WebDriver driver = WebController.getInstance().getDriver();
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
    }
}