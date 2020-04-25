package framework.pageObjects.discovery;

import framework.selfheal.discovery.BrowserNotAvailableException;
import framework.selfheal.discovery.Tag;
import framework.selfheal.discovery.WebAction;
import framework.selfheal.discovery.controllers.DocumentController;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import framework.selfheal.discovery.controllers.WebController;
import org.openqa.selenium.WebDriver;

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
