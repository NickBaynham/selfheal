package selfHealingExamples.tests.registration;

import io.nickbaynham.automation.selfhealing.controllers.WebController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        WebController.getInstance();
    }

    @AfterMethod
    public void wrapUp() {
        WebController.getInstance().close();
    }
}
