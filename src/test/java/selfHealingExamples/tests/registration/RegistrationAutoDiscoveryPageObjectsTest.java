package selfHealingExamples.tests.registration;

import framework.pageObjects.RegistrationFormAutoDiscovery;
import io.nickbaynham.automation.selfhealing.BrowserNotAvailableException;
import io.nickbaynham.automation.selfhealing.Tag;
import io.nickbaynham.automation.selfhealing.controllers.DocumentController;
import io.nickbaynham.automation.selfhealing.controllers.ElementNotFoundException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RegistrationAutoDiscoveryPageObjectsTest extends BaseTest {

    @Test
    public void TestRegistrationPageObjectsWithAutoDiscovery() throws ElementNotFoundException, BrowserNotAvailableException {
        RegistrationFormAutoDiscovery registration = new RegistrationFormAutoDiscovery(getDriver());
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