package selfHealingExamples.tests.registration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RegistrationGetHTML extends BaseTest {

    @Test
    public void TestRegistration() {
        getDriver().get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = getDriver().getPageSource();
        System.out.println(HTML_SOURCE_CODE);
    }
}