package selfHealingExamples.workflows;

import framework.pageObjects.RegistrationFormSelfHealing;
import org.openqa.selenium.WebDriver;

public class RegistrationSelfHealingPageObjectsWorkflow {
    private WebDriver driver;
    public RegistrationSelfHealingPageObjectsWorkflow(WebDriver driver) {
        this.driver = driver;
    }

    public boolean completeRegistration(String firstName, String lastName, String username, String city, String state, String zip, boolean acceptTerms) throws Exception {
        RegistrationFormSelfHealing registrationForm = new RegistrationFormSelfHealing(driver);
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
