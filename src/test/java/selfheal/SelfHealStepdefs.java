package selfheal;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Given("I have a {string} instance")
    public void iHaveAInstance(String driver) {
        setupBrowser();
    }

    private void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver_v78\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Then("I close the browser")
    public void iCloseTheBrowser() {
        webDriver.quit();
    }

    @Then("I can print out all the element ID's")
    public void iCanPrintOutAllTheElementIDS() {
        Inputs inputs = new Inputs(document);
        for (ElementObject elementObject : inputs.getElements()) {
            System.out.print("ID: " + elementObject.getId());
            System.out.println(", CSS Selector: " + elementObject.getCss());
        }
    }
}

class Inputs {
    private Elements inputs;
    private List<ElementObject> elements = new ArrayList<>();

    Inputs(Document document) {
        inputs = document.getElementsByTag("input");
        for (Element input : inputs) {
            String tag = input.tagName();
            String id = input.id();
            String name = input.attr("name");
            String text = input.text();
            String css = input.cssSelector();
            ElementObject elementObject = new ElementObject();
            elementObject.setTag(tag);
            elementObject.setId(id);
            elementObject.setCss(css);
            elements.add(elementObject);
        }
    }

    List<ElementObject> getElements() {
        return elements;
    }

    Elements getMatches(String matcher) {
        return inputs;
    }
}

class ElementObject {
    private String tag;
    private String id;
    private String name;
    private String text;
    private String css;

    void setCss(String css) {
        this.css = css;
    }

    String getCss() {
        return css;
    }

    void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    void setId(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }
}