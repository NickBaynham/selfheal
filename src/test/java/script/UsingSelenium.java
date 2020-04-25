package script;

        import base.BaseTest;
        import framework.selfheal.discovery.controllers.WebController;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.testng.annotations.Test;

        import static org.testng.AssertJUnit.assertEquals;

public class UsingSelenium extends BaseTest {

    @Test
    public void basicSeleniumTest() {
        WebDriver driver = WebController.getInstance().getDriver();
        driver.get("http://localhost:7800/bootstrap1.html#");
        driver.findElement(By.xpath("//label[contains(.,'First name')]/../input")).sendKeys("Ada");
        driver.findElement(By.xpath("//label[contains(.,'Last name')]/../input")).sendKeys("Lovelace");
        driver.findElement(By.xpath("//label[contains(.,'Username')]/..//input")).sendKeys("ALovelace");
        driver.findElement(By.xpath("//label[contains(.,'City')]/../input")).sendKeys("Orlando");
        driver.findElement(By.xpath("//label[contains(.,'State')]/../input")).sendKeys("FL");
        driver.findElement(By.xpath("//label[contains(.,'Zip')]/../input")).sendKeys("32832");
        driver.findElement(By.xpath("//label[contains(.,'Agree to terms and conditions')]/../input")).click();
        driver.findElement(By.xpath("//button[contains(.,'Register')]")).click();
        assertEquals(driver.getTitle(), "Self Healing Test Page");
    }
}