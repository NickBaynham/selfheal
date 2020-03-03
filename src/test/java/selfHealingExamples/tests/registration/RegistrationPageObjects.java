package selfHealingExamples.tests.registration;

import framework.pageObjects.RegistrationForm;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RegistrationPageObjects extends BaseTest {

    @Test
    public void TestRegistration() {
        RegistrationForm registrationForm = new RegistrationForm(getDriver());
        registrationForm.goToPage();
        registrationForm.enterFirstName("Ada");
        registrationForm.enterLastName("Lovelace");
        registrationForm.enterUsername("ALovelace");
        registrationForm.enterCity("Orlando");
        registrationForm.enterState("FL");
        registrationForm.enterZip("32832");
        registrationForm.clickAcceptTerms();
        registrationForm.clickRegisterButton();
        assertEquals(registrationForm.getTitle(), "Self Healing Test Page");
    }
}