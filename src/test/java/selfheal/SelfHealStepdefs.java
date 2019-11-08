package selfheal;

import cucumber.api.java.en.*;
import io.nickbaynham.automation.selfhealing.*;
import static io.nickbaynham.automation.selfhealing.StrategyController.*;
import static io.nickbaynham.automation.selfhealing.WebController.*;
import static io.nickbaynham.automation.selfhealing.DocumentController.*;

public class SelfHealStepdefs {

    @Given("I have a {string} browser")
    public void iHaveABrowser(String browserType) {
        chrome();
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        get(url);
        getInstance(html());
    }

    @Then("I close the browser")
    public void iCloseTheBrowser() {
        quit();
    }

    @Then("I enter {string} in the {string} field")
    public void iEnterInTheField(String text, String element) throws ElementNotFoundException {
        highlight(getInputLocator(element));
        enterText(getInputLocator(element), text);
    }

    @Then("I click the {string} button")
    public void iClickTheButton(String token) throws ElementNotFoundException {
        click(getButtonLocator(token));
    }

    @Then("I enter {string} in the {string} field with test info displayed in a popover")
    public void iEnterInTheFieldWithTestInfoDisplayedInAPopover(String text, String label) throws ElementNotFoundException {
        System.out.println("Enter '" + text + "' in an input that is labelled '" + label + "'");
        injectPopover(getInputLocator(label), label, text, "input");
        highlight(getInputLocator(label));
        enterText(getInputLocator(label), text);
        highlight(getInputLocator(label));
    }
}