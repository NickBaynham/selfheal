package discovery;

import base.BaseTest;
import framework.selfheal.discovery.Tag;
import framework.selfheal.discovery.controllers.DocumentController;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import framework.selfheal.discovery.controllers.WebController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DiscoveryScript extends BaseTest {

    @Test
    public void seleniumScriptUsingAutoDiscoveryTest() throws ElementNotFoundException {
        WebDriver driver = WebController.getInstance().getDriver();
        driver.get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = driver.getPageSource();
        DocumentController documentController = DocumentController.getInstance(HTML_SOURCE_CODE);
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "First Name"))).clear();
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "First Name"))).sendKeys("Ada");
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Last Name"))).clear();
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Last Name"))).sendKeys("Lovelace");
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Username"))).clear();
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Username"))).sendKeys("AdaLovelace");
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "City"))).clear();
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "City"))).sendKeys("Orlando");
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "State"))).clear();
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "State"))).sendKeys("FL");
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Zip"))).clear();
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Zip"))).sendKeys("32832");
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Accept Terms"))).click();
        driver.findElement(By.cssSelector(documentController.getLocator(Tag.input, "Register"))).click();
        String title = driver.getTitle();
        assertEquals("Self Healing Test Page", title);
    }
}