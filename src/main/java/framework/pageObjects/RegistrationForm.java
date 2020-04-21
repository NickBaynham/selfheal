package framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationForm {

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

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        driver.get("http://localhost:7800/bootstrap1.html#");
    }

    public void enterFirstName(String firstName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
    }

    public void enterUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void enterCity(String city) {
        this.city.clear();
        this.city.sendKeys(city);
    }

    public void enterState(String state) {
        this.state.clear();
        this.state.sendKeys(state);
    }

    public void enterZip(String zip) {
        this.zip.clear();
        this.zip.sendKeys(zip);
    }

    public void clickAcceptTerms() {
        this.acceptTerms.click();
    }

    public void clickAcceptTerms(boolean state) {
        if (state) this.acceptTerms.click();
    }

    public void clickRegisterButton() {
        this.registerButton.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    private WebDriver driver;
}
