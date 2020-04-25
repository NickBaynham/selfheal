package discovery;

import base.BaseTest;
import framework.selfheal.discovery.BrowserNotAvailableException;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import framework.selfheal.discovery.controllers.WebController;
import framework.workflow.RegistrationDiscoveryWorkflow;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class DiscoveryWorkflow extends BaseTest {

    @Test
    public void workflowWithDiscoveryTest() throws BrowserNotAvailableException, ElementNotFoundException {
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