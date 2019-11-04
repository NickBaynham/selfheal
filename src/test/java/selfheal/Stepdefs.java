package selfheal;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.jsoup.*;
import static org.junit.Assert.*;

public class Stepdefs {
    private WebDriver webDriver;
    private String today;
    private String actualAnswer;

    static String isItFriday(String today) {
        return "No";
    }

    @Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }

    @When("I ask whether it is Friday or not")
    public void i_ask_whether_it_is_Friday_or_not() {
        actualAnswer = isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(actualAnswer, expectedAnswer);
    }

    @Then("I should be able to pull the HTML and print it")
    public void iShouldBeAbleToPullTheHTMLAndPrintIt() {
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        webDriver.get(url);
        String html = webDriver.getPageSource();
        Document document = Jsoup.parse(html);
        System.out.println(document.title());
        webDriver.quit();
    }

    @Given("I have a {string} instance")
    public void iHaveAInstance(String driver) {
        System.out.println("Driver: " + driver);
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver_v78\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }
}
