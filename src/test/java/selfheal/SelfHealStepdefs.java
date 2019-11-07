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
        enterText(getInputLocator(element), text);
    }

    @Then("I click the {string} button")
    public void iClickTheButton(String token) throws ElementNotFoundException {
        click(getButtonLocator(token));
    }
}