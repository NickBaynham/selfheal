package io.nickbaynham.automation.selfhealing.controllers;

import io.nickbaynham.automation.selfhealing.BrowserNotAvailableException;
import io.nickbaynham.automation.selfhealing.Tag;
import io.nickbaynham.automation.selfhealing.WebAction;
import io.nickbaynham.automation.selfhealing.WebQuery;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class WebController implements WebAction, WebQuery {
    private WebDriver driver;
    private static WebController webController;

    public WebController(Browser browser) throws BrowserNotAvailableException {
        this.setup(browser);
    }

    public WebController(WebDriver driver) {
        this.driver = driver;
    }

    public static WebController getInstance() {
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/drivers/chromedriver.exe");
        if (webController == null) webController = new WebController(new ChromeDriver());
        return webController;
    }

    @Override
    public void inject(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

    @Override
    public void highlight(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow;');", driver.findElement(By.cssSelector(locator)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', 'background: white;');", driver.findElement(By.cssSelector(locator)));
    }

    @Override
    public void enterText(String locator, String text) {
        driver.findElement(By.cssSelector(locator)).sendKeys(text);
    }

    @Override
    public void click(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    @Override
    public void selectRadio(String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    @Override
    public void select(String token, String locator) throws ElementNotFoundException {
        WebElement element = driver.findElement(By.cssSelector(locator));
        Select select = new Select(element);
        String optionValue = getOptionValue(select.getOptions(), token);
        select.selectByValue(optionValue);
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public void setup(Browser browser) throws BrowserNotAvailableException {
        switch(browser) {
            case Chrome:
                chrome();
                break;
            case Firefox:
            case IE:
            case Edge:
            case Safari:
            default: throw new BrowserNotAvailableException(browser + " is not available at this time.");
        }
    }

    @Override
    public void close() {
        if (driver != null) driver.quit();
    }

    private void chrome() {
        System.setProperty("webdriver.chrome.driver", "/selenium/chromedriver");
        driver = new ChromeDriver();
    }

    private void injectBootstrap() {

        // jQuery
        String jQueryScript =
                "var head = document.getElementsByTagName('head')[0];" +
                        "var script = document.createElement('script');" +
                        "script.type = 'text/javascript';" +
                        "script.src = \"https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js\";" +
                        "head.appendChild(script);";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jQueryScript);

        // Bootstrap

        String bootstrapScript =
                "var head = document.getElementsByTagName('head')[0];" +
                        "var script = document.createElement('script');" +
                        "script.type = 'text/javascript';" +
                        "script.src = \"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\";" +
                        "head.appendChild(script);";
        js = (JavascriptExecutor) driver;
        js.executeScript(bootstrapScript);

        String bootstrapCss =
                "var head = document.getElementsByTagName('head')[0];" +
                        "var link = document.createElement('link');" +
                        "link.rel = 'stylesheet';" +
                        "link.href = 'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css';" +
                        "head.appendChild(link);";
        js = (JavascriptExecutor) driver;
        js.executeScript(bootstrapCss);

        // class container required

        String script =
                "document.getElementsByTagName('body')[0].setAttribute('class', 'container');";
        js = (JavascriptExecutor) driver;
        js.executeScript(script);

    }

    public void injectPopover2(String locator, String label, String text, String tag) {
        String dataContentScript =
                "arguments[0].setAttribute('data-content', 'CSS Locator is " + locator + "');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(dataContentScript, driver.findElement(By.cssSelector(locator)));

        String titleScript =
                "arguments[0].setAttribute('data-original-title', 'Enter %1 into %2 labelled as %3');"
                        .replace("%1", text)
                        .replace("%2", tag)
                        .replace("%3", label);
        js = (JavascriptExecutor) driver;
        js.executeScript(titleScript, driver.findElement(By.cssSelector(locator)));

        String eventScript =
                "arguments[0].setAttribute('data-trigger', 'hover');";
        js = (JavascriptExecutor) driver;
        js.executeScript(eventScript, driver.findElement(By.cssSelector(locator)));

        String toggleScript =
                "arguments[0].setAttribute('data-toggle', 'popover');";
        js = (JavascriptExecutor) driver;
        js.executeScript(toggleScript, driver.findElement(By.cssSelector(locator)));

        // Mouse over to show the popover

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(locator));
        actions.moveToElement(element).perform();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void injectPopover(String locator, String label, String text, String tag) {
        String popoverScript =
                "arguments[0].setAttribute('data-content', 'CSS Locator is " + locator + "');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(popoverScript, driver.findElement(By.cssSelector(locator)));

        popoverScript =
                "arguments[0].setAttribute('data-original-title', 'Enter %1 into %2 labelled as %3');"
                        .replace("%1", text)
                        .replace("%2", tag)
                        .replace("%3", label);
        js = (JavascriptExecutor) driver;
        js.executeScript(popoverScript, driver.findElement(By.cssSelector(locator)));

        // Mouse over to show the popover

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(locator));
        actions.moveToElement(element).perform();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isVisible(String locator) {
        return driver.findElement(By.cssSelector(locator)).isDisplayed();
    }

    @Override
    public boolean isEnabled(String locator) {
        return driver.findElement(By.cssSelector(locator)).isEnabled();
    }

    public boolean isSelected(String locator) {
        return driver.findElement(By.cssSelector(locator)).isSelected();
    }

    @Override
    public boolean isPresent(String locator) {
        return driver.findElements(By.cssSelector(locator)).size() == 1;
    }

    @Override
    public String getValue(String element) {
        return null;
    }

    @Override
    public String getInnerText(String element) {
        return null;
    }

    @Override
    public String getDefaultValue(String element) {
        return null;
    }

    @Override
    public String getHtml() {
        return driver.getPageSource();
    }

    private String getOptionValue(List<WebElement> options, String matcher) throws ElementNotFoundException {

        // Initialize Result Set

        List<String> optionValues = new ArrayList<>();
        List<String> optionsVisibleText = new ArrayList<>();

        for (WebElement option : options) {
            optionValues.add(option.getAttribute("value"));
            optionsVisibleText.add(option.getText());
        }

        if (optionValues.isEmpty() || optionsVisibleText.isEmpty()) throw new ElementNotFoundException("Element Not Found: " + "=" + matcher);

        // Fuzzy Search by Inner Text

        ExtractedResult resultValues = FuzzySearch.extractOne(matcher, optionValues);
        ExtractedResult resultVisibleText = FuzzySearch.extractOne(matcher, optionsVisibleText);
        int scoreValues = resultValues.getScore();
        int scoreVisibleText = resultVisibleText.getScore();
        if (scoreValues > scoreVisibleText) {
            return options.get(resultValues.getIndex()).getAttribute("value");
        } else {
            return options.get(resultVisibleText.getIndex()).getAttribute("value");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
