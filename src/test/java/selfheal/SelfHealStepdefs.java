package selfheal;

import cucumber.api.java.en.*;
import io.nickbaynham.automation.selfhealing.WebAction;
import io.nickbaynham.automation.selfhealing.WebQuery;
import io.nickbaynham.automation.selfhealing.Locate;
import io.nickbaynham.automation.selfhealing.BrowserNotAvailableException;
import io.nickbaynham.automation.selfhealing.Tag;
import io.nickbaynham.automation.selfhealing.controllers.WebController;
import io.nickbaynham.automation.selfhealing.controllers.DocumentController;
import io.nickbaynham.automation.selfhealing.controllers.ElementNotFoundException;

public class SelfHealStepdefs {

    private WebAction actions;
    private WebQuery query;
    private Locate doc;

    @Given("I have a {string} browser")
    public void iHaveABrowser(String browserType) throws BrowserNotAvailableException {
        actions = new WebController(WebAction.Browser.valueOf(browserType));
        query = (WebQuery) actions;
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        actions.get(url);
        doc = DocumentController.getInstance(query.getHtml());
    }

    @Then("I close the browser")
    public void iCloseTheBrowser() {
        actions.close();
    }

    @Then("I enter {string} in the {string} field")
    public void iEnterInTheField(String text, String element) throws ElementNotFoundException {
        actions.highlight(doc.getLocator(Tag.input, element));
        actions.enterText(doc.getLocator(Tag.input, element), text);
    }

    @Then("I click the {string} button")
    public void iClickTheButton(String token) throws ElementNotFoundException {
        actions.click(doc.getLocator(Tag.button, token));
    }

    @Then("I enter {string} in the {string} field with test info displayed in a popover")
    public void iEnterInTheFieldWithTestInfoDisplayedInAPopover(String text, String label) throws ElementNotFoundException {
        System.out.println("Enter '" + text + "' in an input that is labelled '" + label + "'");
        //injectPopover2(getInputLocator(label), label, text, "input");
        actions.highlight(doc.getLocator(Tag.input, label));
        actions.enterText(doc.getLocator(Tag.input, label), text);
        actions.highlight(doc.getLocator(Tag.input, label));
    }

    @And("I inject bootstrap into the page")
    public void iInjectBootsrapIntoThePage() {
        // injectBootstrap();
    }
}