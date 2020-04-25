package framework.workflow;

import framework.pageObjects.classic.RegistrationForm;
import org.openqa.selenium.WebDriver;

public class RegistrationWorkflow {
    private WebDriver driver;
    public RegistrationWorkflow(WebDriver driver) {
        this.driver = driver;
    }

    public boolean completeRegistration(String firstName, String lastName, String username, String city, String state, String zip, boolean acceptTerms) {
        RegistrationForm registrationForm = new RegistrationForm(driver);
        registrationForm.goToPage();
        registrationForm.enterFirstName(firstName);
        registrationForm.enterLastName(lastName);
        registrationForm.enterUsername(username);
        registrationForm.enterCity(city);
        registrationForm.enterState(state);
        registrationForm.enterZip(zip);
        registrationForm.clickAcceptTerms(acceptTerms);
        registrationForm.clickRegisterButton();
        return (registrationForm.getTitle().equals("Self Healing Test Page"));
    }
}
