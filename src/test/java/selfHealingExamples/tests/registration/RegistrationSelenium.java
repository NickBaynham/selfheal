package selfHealingExamples.tests.registration;

        import org.openqa.selenium.By;
        import org.testng.annotations.Test;

        import static org.testng.AssertJUnit.assertEquals;

public class RegistrationSelenium extends BaseTest {

    @Test
    public void TestRegistration() {
        getDriver().get("http://localhost:7800/bootstrap1.html#");
        getDriver().findElement(By.xpath("//label[contains(.,'First name')]/../input")).sendKeys("Ada");
        getDriver().findElement(By.xpath("//label[contains(.,'Last name')]/../input")).sendKeys("Lovelace");
        getDriver().findElement(By.xpath("//label[contains(.,'Username')]/..//input")).sendKeys("ALovelace");
        getDriver().findElement(By.xpath("//label[contains(.,'City')]/../input")).sendKeys("Orlando");
        getDriver().findElement(By.xpath("//label[contains(.,'State')]/../input")).sendKeys("FL");
        getDriver().findElement(By.xpath("//label[contains(.,'Zip')]/../input")).sendKeys("32832");
        getDriver().findElement(By.xpath("//label[contains(.,'Agree to terms and conditions')]/../input")).click();
        getDriver().findElement(By.xpath("//button[contains(.,'Register')]")).click();
        assertEquals(getDriver().getTitle(), "Self Healing Test Page");
    }
}