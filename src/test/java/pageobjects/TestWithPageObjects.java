package pageobjects;

import base.BaseTest;
import framework.pageObjects.classic.RegistrationForm;
import framework.selfheal.discovery.controllers.WebController;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TestWithPageObjects extends BaseTest {

    @Test
    public void pageObjectsTest() {
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
    RegistrationForm registrationForm = new RegistrationForm(WebController.getInstance().getDriver());
}