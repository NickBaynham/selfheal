package selfHealingExamples.tests.registration;

import io.nickbaynham.automation.selfhealing.Tag;
import io.nickbaynham.automation.selfhealing.controllers.DocumentController;
import io.nickbaynham.automation.selfhealing.controllers.ElementNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RegistrationAutoDiscovery extends BaseTest {

    @Test
    public void TestRegistration() throws ElementNotFoundException {
        getDriver().get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = getDriver().getPageSource();
        DocumentController documentController = DocumentController.getInstance(HTML_SOURCE_CODE);
        String locator = documentController.getLocator(Tag.input, "First Name");
        System.out.println(locator);
    }

    @Test
    public void TestRegistrationWithAutoDiscovery() throws ElementNotFoundException {
        getDriver().get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = getDriver().getPageSource();
        DocumentController documentController = DocumentController.getInstance(HTML_SOURCE_CODE);
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "First Name"))).clear();
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "First Name"))).sendKeys("Ada");
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Last Name"))).clear();
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Last Name"))).sendKeys("Lovelace");
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Username"))).clear();
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Username"))).sendKeys("AdaLovelace");
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "City"))).clear();
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "City"))).sendKeys("Orlando");
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "State"))).clear();
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "State"))).sendKeys("FL");
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Zip"))).clear();
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Zip"))).sendKeys("32832");
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Accept Terms"))).click();
        getDriver().findElement(By.cssSelector(documentController.getLocator(Tag.input, "Register"))).click();
        String title = getDriver().getTitle();
        assertEquals("Self Healing Test Page", title);
    }
}