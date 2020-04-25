package workflow;

import base.BaseTest;
import framework.selfheal.discovery.controllers.WebController;
import org.testng.annotations.Test;
import framework.workflow.RegistrationWorkflow;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class RegistrationWorkflowTest extends BaseTest {

    @Test
    public void workflowTest() {
        RegistrationWorkflow registrationWorkflow = new RegistrationWorkflow();
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