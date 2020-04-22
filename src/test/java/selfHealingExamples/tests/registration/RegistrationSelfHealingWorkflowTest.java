package selfHealingExamples.tests.registration;

import org.testng.annotations.Test;
import selfHealingExamples.workflows.RegistrationSelfHealingPageObjectsWorkflow;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationSelfHealingWorkflowTest extends BaseTest {

    @Test
    public void TestRegistrationWorkflow() throws Exception {
        RegistrationSelfHealingPageObjectsWorkflow registrationWorkflow = new RegistrationSelfHealingPageObjectsWorkflow();
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