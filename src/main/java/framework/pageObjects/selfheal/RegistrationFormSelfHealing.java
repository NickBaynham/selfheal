package framework.pageObjects.selfheal;

import framework.selfheal.discovery.controllers.AutoDiscover;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import framework.selfheal.discovery.controllers.WebController;
import org.openqa.selenium.By;
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


    /**
     *  goToPage() - Navigate to the Page
     */
    public void goToPage() {
        WebController.getInstance().getDriver().get("http://localhost:7800/bootstrap1.html#");
    }

    /**
     * We can provide a wrapper so that the Page Objects interface is the same as before
     * @param firstName - text to enter in the field
     * @throws Exception - if entering text on input fails
     */
    public void enterFirstName(String firstName) throws Exception {
        enterText("First name", firstName);
    }

    public void enterLastName(String lastName) throws Exception {
        enterText("Last name", lastName);
    }

    public void enterUsername(String username) throws Exception {
        enterText("Username", username);
    }

    public void enterCity(String city) throws Exception {
        enterText("City", city);
    }

    public void enterState(String state) throws Exception {
        enterText("State", state);
    }

    public void enterZip(String zip) throws Exception {
        enterText("Zip", zip);
    }

    public void clickAcceptTerms() throws Exception {
        clickCheckBox("Accept Terms");
    }

    public void clickRegisterButton() throws Exception {
        clickButton("Register");
    }

    /**
     * enterText() - Enter text into an input element
     * Simplies the wrapper by factoring out common code
     * @param label - The Label Text tells us how to find the input on the page
     * @param text - Text to type into the field must be provided
     * @throws Exception - When we are not able to find a matching element based on the characteristics provided
     */
    private void enterText(String label, String text) throws Exception {
        try {
            WebElement element = WebController.getInstance().getDriver().findElement(By.cssSelector(AutoDiscover.getCachedLocator(getInputCharacteristics(label))));
            element.isDisplayed();
            element.isEnabled();
            element.clear();
            element.sendKeys(text);
            return;
        } catch (Exception e) {
            System.out.println("Cached Element Failed or Not Found!");
        }

        // Use discovery and cache new value
        try {
            WebElement element = WebController.getInstance().getDriver().findElement(By.cssSelector(AutoDiscover.getLocator(getInputCharacteristics(label))));
            element.isDisplayed();
            element.isEnabled();
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new ElementNotFoundException("Strategy Failed for input: " + label);
        }
    }

    /**
     * A discovery method for check boxes
     * @param label - How to find it
     * @throws Exception - If we can't find it
     */
    private void clickCheckBox(String label) throws Exception {
        try {
            WebElement element = WebController.getInstance().getDriver().findElement(By.cssSelector(AutoDiscover.getCachedLocator(getInputCharacteristics(label))));
            element.isDisplayed();
            element.isEnabled();
            element.click();
            return;
        } catch (Exception e) {
            System.out.println("Cached Element Failed or Not Found!");
        }

        // Use discovery and cache new value
        try {
            WebElement element = WebController.getInstance().getDriver().findElement(By.cssSelector(AutoDiscover.getLocator(getInputCharacteristics(label))));
            element.isDisplayed();
            element.isEnabled();
            element.click();
        } catch (Exception e) {
            throw new ElementNotFoundException("Strategy Failed for check box: " + label);
        }
    }

    /**
     * A discovery method for a button
     * @param label - The label on the button is used to find it
     * @throws Exception - When we failed to find it
     */
    private void clickButton(String label) throws Exception {
        try {
            WebElement element = WebController.getInstance().getDriver().findElement(By.cssSelector(AutoDiscover.getCachedLocator(getInputCharacteristics(label))));
            element.isDisplayed();
            element.isEnabled();
            element.click();
            return;
        } catch (Exception e) {
            System.out.println("Cached Element Failed or Not Found!");
        }

        // Use discovery and cache new value
        try {
            WebElement element = WebController.getInstance().getDriver().findElement(By.cssSelector(AutoDiscover.getLocator(getInputCharacteristics(label))));
            element.isDisplayed();
            element.isEnabled();
            element.click();
        } catch (Exception e) {
            throw new ElementNotFoundException("Strategy Failed for button: " + label);
        }
    }

    public String getTitle() {
        return WebController.getInstance().getDriver().getTitle();
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
}
