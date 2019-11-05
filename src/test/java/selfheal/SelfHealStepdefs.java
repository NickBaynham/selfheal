package selfheal;

import cucumber.api.java.en.*;
import io.nickbaynham.automation.selfhealing.*;
import static io.nickbaynham.automation.selfhealing.WebController.*;
import static io.nickbaynham.automation.selfhealing.DocumentController.*;
import static org.junit.Assert.*;

public class SelfHealStepdefs {

    @Then("the number of inputs found should be {string}")
    public void theNumberOfInputsFoundShouldBe(String numberOfInputs) {
        int numberOfInputs1 = getInputs().size();
        assertEquals(numberOfInputs1, Integer.parseInt(numberOfInputs));
    }

    @Given("I have a {string} browser")
    public void iHaveABrowser(String browserType) {
        chrome();
    }

    @Then("I should be able to pull the HTML and print it")
    public void iShouldBeAbleToPullTheHTMLAndPrintIt() {
        setPageSource(html());
        System.out.println(getPageSource());
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        get(url);
        setPageSource(html());
    }

    @Then("I close the browser")
    public void iCloseTheBrowser() {
        quit();
    }

    @Then("I can print out all the element ID's")
    public void iCanPrintOutAllTheElementIDS() {
        for (ElementObject elementObject : ElementController.getElements()) {
            System.out.print("ID: " + elementObject.getId());
            System.out.println(", CSS Selector: " + elementObject.getCss());
        }
    }

    @Then("I enter {string} in the {string} field")
    public void iEnterInTheField(String text, String field) {
        StrategyController.applyFuzzyIdStrategyToInput(field, text);
    }

    @Given("I have a {string} browser open")
    public void iHaveABrowserOpen(String browser) {
        chrome();
    }
}