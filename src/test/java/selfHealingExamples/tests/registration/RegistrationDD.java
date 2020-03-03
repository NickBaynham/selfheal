package selfHealingExamples.tests.registration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import selfHealingExamples.dataProviders.RegistrationData;
import static org.testng.AssertJUnit.assertEquals;

public class RegistrationDD extends BaseTest {

    @Test(dataProvider = "registration", dataProviderClass= RegistrationData.class)
    public void TestRegistration(String firstName, String lastName, String username, String city, String state, String zip, boolean acceptTerms) {
        getDriver().get("http://localhost:7800/bootstrap1.html#");
        getDriver().findElement(By.xpath("//label[contains(.,'First name')]/../input")).sendKeys(firstName);
        getDriver().findElement(By.xpath("//label[contains(.,'Last name')]/../input")).sendKeys(lastName);
        getDriver().findElement(By.xpath("//label[contains(.,'Username')]/..//input")).sendKeys(username);
        getDriver().findElement(By.xpath("//label[contains(.,'City')]/../input")).sendKeys(city);
        getDriver().findElement(By.xpath("//label[contains(.,'State')]/../input")).sendKeys(state);
        getDriver().findElement(By.xpath("//label[contains(.,'Zip')]/../input")).sendKeys(zip);
        if (acceptTerms) getDriver().findElement(By.xpath("//label[contains(.,'Agree to terms and conditions')]/../input")).click();
        getDriver().findElement(By.xpath("//button[contains(.,'Register')]")).click();
        assertEquals(getDriver().getTitle(), "Self Healing Test Page");
    }
}