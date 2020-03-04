package selfHealingExamples.tests.registration;

import io.nickbaynham.automation.selfhealing.BrowserNotAvailableException;
import io.nickbaynham.automation.selfhealing.controllers.ElementNotFoundException;
import io.nickbaynham.automation.selfhealing.controllers.WebController;
import org.testng.annotations.Test;
import selfHealingExamples.workflows.RegistrationDiscoveryWorkflow;
import selfHealingExamples.workflows.RegistrationWorkflow;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationDiscoveryWorkflowTest extends BaseTest {

    @Test
    public void TestRegistrationDiscoveryWorkflow() throws BrowserNotAvailableException, ElementNotFoundException {
        RegistrationDiscoveryWorkflow registrationWorkflow = new RegistrationDiscoveryWorkflow(WebController.getInstance().getDriver());
        assertTrue(registrationWorkflow.completeRegistration(
                "John",
                "Jones",
                "JJ",
                "Alberqurque",
                "NM",
                "87109",
                false));
    }
}