package selfHealingExamples.workflows;

import framework.pageObjects.RegistrationForm;
import org.openqa.selenium.WebDriver;

public class RegistrationWorkflow {
    private WebDriver driver;
    public RegistrationWorkflow(WebDriver driver) {
        this.driver = driver;
    }

    public boolean completeRegistration(String firstName, String lastName, String username, String city, String state, String zip, boolean acceptTerms) {
        RegistrationForm registrationForm = new RegistrationForm(driver);
        registrationForm.goToPage();
        registrationForm.enterFirstName("Ada");
        registrationForm.enterLastName("Lovelace");
        registrationForm.enterUsername("ALovelace");
        registrationForm.enterCity("Orlando");
        registrationForm.enterState("FL");
        registrationForm.enterZip("32832");
        registrationForm.clickAcceptTerms();
        registrationForm.clickRegisterButton();
        return (registrationForm.getTitle().equals("Self Healing Test Page"));
    }
}
