package discovery;

import base.BaseTest;
import framework.pageObjects.discovery.RegistrationFormAutoDiscovery;
import framework.selfheal.discovery.BrowserNotAvailableException;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import framework.selfheal.discovery.controllers.WebController;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DiscoveryPageObjects extends BaseTest {

    @Test
    public void discoveryPageObjectsTest() throws ElementNotFoundException, BrowserNotAvailableException {
        RegistrationFormAutoDiscovery registration = new RegistrationFormAutoDiscovery(WebController.getInstance().getDriver());
        registration.goToPage();
        registration.enterText("First Name", "Ada");
        registration.enterText("Last Name", "Lovelace");
        registration.enterText("Username", "AdaL");
        registration.enterText("City", "Orlando");
        registration.enterText("State", "FL");
        registration.enterText("Zip", "32832");
        registration.clickCheckBox("Accept Terms");
        registration.clickButton("Register");
        assertEquals("Self Healing Test Page", registration.getTitle());
    }
}