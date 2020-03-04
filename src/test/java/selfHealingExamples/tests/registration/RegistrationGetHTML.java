package selfHealingExamples.tests.registration;

import io.nickbaynham.automation.selfhealing.controllers.WebController;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RegistrationGetHTML extends BaseTest {

    @Test
    public void TestRegistration() {
        WebController.getInstance().getDriver().get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = WebController.getInstance().getDriver().getPageSource();
        System.out.println(HTML_SOURCE_CODE);
    }
}