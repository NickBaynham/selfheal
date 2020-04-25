package workflow;

import base.BaseTest;
import framework.workflow.RegistrationSelfHealingPageObjectsWorkflow;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class SelfHealingWorkflow extends BaseTest {

    @Test
    public void workflowWithSelfHealingTest() throws Exception {
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