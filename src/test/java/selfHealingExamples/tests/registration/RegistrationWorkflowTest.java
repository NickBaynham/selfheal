package selfHealingExamples.tests.registration;

import framework.pageObjects.RegistrationForm;
import io.nickbaynham.automation.selfhealing.controllers.WebController;
import org.testng.annotations.Test;
import selfHealingExamples.workflows.RegistrationWorkflow;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class RegistrationWorkflowTest extends BaseTest {

    @Test
    public void TestRegistrationWorkflow() {
        RegistrationWorkflow registrationWorkflow = new RegistrationWorkflow(WebController.getInstance().getDriver());
        assertTrue(registrationWorkflow.completeRegistration(
                "John",
                "Jones",
                "JJ",
                "Alberqurque",
                "NM",
                "87109",
                true));
    }
}