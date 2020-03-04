package framework.pageObjects;

import io.nickbaynham.automation.selfhealing.controllers.AutoDiscover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 *   Registration Form (with Self Healing Locators)
 *
 *   Demonstrates use of the new AutoDiscovery controller Class (includes Cache and Self Heal)
 *   Remember that Driver Manager must save the cache before the test completes
 */
public class RegistrationFormSelfHealing {

/*  We don't need these static locators anymore
    @FindBy(xpath = "//label[contains(.,'First name')]/../input")
    private WebElement firstName;

    @FindBy(xpath = "//label[contains(.,'Last name')]/../input")
    private WebElement lastName;

    @FindBy(xpath = "//label[contains(.,'Username')]/..//input")
    private WebElement username;

    @FindBy(xpath = "//label[contains(.,'City')]/../input")
    private WebElement city;

    @FindBy(xpath = "//label[contains(.,'State')]/../input")
    private WebElement state;

    @FindBy(xpath = "//label[contains(.,'Zip')]/../input")
    private WebElement zip;

    @FindBy(xpath = "//label[contains(.,'Agree to terms and conditions')]/../input")
    private WebElement acceptTerms;

    @FindBy(xpath = "//button[contains(.,'Register')]")
    private WebElement registerButton;
*/

    /**
     * Constructor - No Need to Initialize Any Page Elements
     * @param driver - the current driver session
     */
    public RegistrationFormSelfHealing(WebDriver driver) {
        this.driver = driver;
        // PageFactory.initElements(driver, this);  since we don't have any static locators, we don't need this initialization anymore
    }

    /**
     *   No Change, since no elements are involved
     */
    public void goToPage() {
        driver.get("http://localhost:7800/bootstrap1.html#");
    }

    /**
     * We can provide a wrapper so that the Page Objects interface is the same as before
     * @param firstName - text to enter in the field
     * @throws Exception
     */
    public void enterFirstName(String firstName) throws Exception {
/*      We will replace these steps with the auto discovery steps
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
*/
        enterText("First name", firstName);
    }

    public void enterLastName(String lastName) throws Exception {
/*
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
*/
        enterText("Last name", lastName);
    }

    public void enterUsername(String username) throws Exception {
/*
        this.username.clear();
        this.username.sendKeys(username);
*/
        enterText("Username", username);
    }

    public void enterCity(String city) throws Exception {
/*
        this.city.clear();
        this.city.sendKeys(city);
*/
        enterText("City", city);
    }

    public void enterState(String state) throws Exception {
/*
        this.state.clear();
        this.state.sendKeys(state);
*/
        enterText("State", state);
    }

    public void enterZip(String zip) throws Exception {
/*
        this.zip.clear();
        this.zip.sendKeys(zip);
*/
        enterText("Zip", zip);
    }

    public void clickAcceptTerms() throws Exception {
/*
        this.acceptTerms.click();
*/
        clickCheckBox("Accept Terms");
    }

    public void clickRegisterButton() throws Exception {
/*
        this.registerButton.click();
*/
        clickButton("Register");
    }

    /**
     * New Generalized method to find an element on the page that is an input accepting text
     * Simplies the wrapper by factoring out common code
     * @param label - The Label Text tells us how to find the input on the page
     * @param text - Text to type into the field must be provided
     * @throws Exception - When we are not able to find a matching element based on the characteristics provided
     */
    private void enterText(String label, String text) throws Exception {
        WebElement element = driver.findElement(By.cssSelector(AutoDiscover.getLocator(getInputCharacteristics(label))));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * A discovery method for check boxes
     * @param label - How to find it
     * @throws Exception - If we can't find it
     */
    private void clickCheckBox(String label) throws Exception {
        WebElement element = driver.findElement(By.cssSelector(AutoDiscover.getLocator(getInputCharacteristics(label))));
        element.click();
    }

    /**
     * A discovery method for a button
     * @param text - The label on the button is used to find it
     * @throws Exception - When we failed to find it
     */
    private void clickButton(String text) throws Exception {
        WebElement element = driver.findElement(By.cssSelector(AutoDiscover.getLocator(getButtonCharacteristics(text))));
        element.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * This method provides a strategy for finding a button on the page
     *
     * @param text - the button label
     * @return - A Map containing the characteristics to use for finding an element
     */
    private static Map<String, String> getButtonCharacteristics(String text) {
        Map<String, String> characteristics = new HashMap<>();
        characteristics.put("InnerText", text);
        characteristics.put("shouldBePresent", "true");
        characteristics.put("shouldBeVisible", "true");
        characteristics.put("shouldBeEnabled", "true");
        return characteristics;
    }

    /**
     * Method for providing a strategy for finding an input field on the page
     * @param label - The text of the label for the element on the page
     * @return - a Map of Characteristics for Auto Discovery
     */
    private static Map<String, String> getInputCharacteristics(String label) {
        Map<String, String> characteristics = new HashMap<>();
        characteristics.put("Label", label);
        characteristics.put("shouldBePresent", "true");
        characteristics.put("shouldBeVisible", "true");
        characteristics.put("shouldBeEnabled", "true");
        return characteristics;
    }

    private WebDriver driver;
}
