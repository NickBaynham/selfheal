package selfHealingExamples.tests.registration;

import org.testng.annotations.Test;
import selfHealingExamples.workflows.RegistrationSelfHealingPageObjectsWorkflow;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationSelfHealingWorkflowTest extends BaseTest {

    /*
            Given
                A user wishes to register for the web site
            When
                A user navigates to the registration page and submits the required and valid registration data
            Then
                The user sees a confirmation page that informs the user they are now registered
     */

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