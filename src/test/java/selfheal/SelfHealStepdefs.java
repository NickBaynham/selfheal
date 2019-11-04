package selfheal;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class SelfHealStepdefs {
    private WebDriver webDriver;
    private String pageSource;
    private Document document;
    private int numberOfInputs;

    @Then("the number of inputs found should be {string}")
    public void theNumberOfInputsFoundShouldBe(String numberOfInputs) {
        Elements inputs = document.getElementsByTag("input");
        this.numberOfInputs = inputs.size();
        assertEquals(this.numberOfInputs, Integer.parseInt(numberOfInputs));
    }

    @Given("I have a {string} browser")
    public void iHaveABrowser(String browserType) {
        setupBrowser();
    }

    @Then("I should be able to pull the HTML and print it")
    public void iShouldBeAbleToPullTheHTMLAndPrintIt() {
        pageSource = webDriver.getPageSource();
        document = Jsoup.parse(pageSource);
        System.out.println(pageSource);
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        webDriver.get(url);
        webDriver.quit();
    }

    @Given("I have a {string} instance")
    public void iHaveAInstance(String driver) {
        setupBrowser();
    }

    private void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver_v78\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }
}
