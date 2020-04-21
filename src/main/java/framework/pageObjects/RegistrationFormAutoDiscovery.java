package framework.pageObjects;

import io.nickbaynham.automation.selfhealing.BrowserNotAvailableException;
import io.nickbaynham.automation.selfhealing.Tag;
import io.nickbaynham.automation.selfhealing.WebAction;
import io.nickbaynham.automation.selfhealing.WebQuery;
import io.nickbaynham.automation.selfhealing.controllers.DocumentController;
import io.nickbaynham.automation.selfhealing.controllers.ElementNotFoundException;
import io.nickbaynham.automation.selfhealing.controllers.WebController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationFormAutoDiscovery {

    public RegistrationFormAutoDiscovery(WebDriver driver) throws BrowserNotAvailableException {
        this.driver = driver;
        goToPage();
        final String HTML_SOURCE_CODE = driver.getPageSource();
        documentController = DocumentController.getInstance(HTML_SOURCE_CODE);
        actions = new WebController(driver);
    }

    public void goToPage() {
        driver.get("http://localhost:7800/bootstrap1.html#");
    }

    public void enterText(String label, String value) throws ElementNotFoundException {
        String locator = documentController.getLocator(Tag.input, label);
        actions.enterText(locator, value);
    }

    public void clickCheckBox(String label) throws ElementNotFoundException {
        String locator = documentController.getLocator(Tag.input, label);
        actions.click(locator);
    }

    public void clickButton(String label) throws ElementNotFoundException {
        String locator = documentController.getLocator(Tag.input, label);
        actions.click(locator);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    DocumentController documentController;
    private WebAction actions;
    private WebDriver driver;
}
