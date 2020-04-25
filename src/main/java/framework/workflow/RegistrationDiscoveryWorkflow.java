package framework.workflow;

import framework.pageObjects.discovery.RegistrationFormAutoDiscovery;
import framework.selfheal.discovery.BrowserNotAvailableException;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import org.openqa.selenium.WebDriver;

public class RegistrationDiscoveryWorkflow {
    private WebDriver driver;
    public RegistrationDiscoveryWorkflow(WebDriver driver) {
        this.driver = driver;
    }

    public boolean completeRegistration(
            String firstName,
            String lastName,
            String username,
            String city,
            String state,
            String zip,
            boolean acceptTerms
    ) throws BrowserNotAvailableException, ElementNotFoundException {
        RegistrationFormAutoDiscovery registration = new RegistrationFormAutoDiscovery(driver);
        registration.goToPage();
        registration.enterText("First Name", firstName);
        registration.enterText("Last Name", lastName);
        registration.enterText("Username", username);
        registration.enterText("City", city);
        registration.enterText("State", state);
        registration.enterText("Zip", zip);
        registration.clickCheckBox("Accept Terms");
        registration.clickButton("Register");
        return "Self Healing Test Page".equals(registration.getTitle());
    }
}
