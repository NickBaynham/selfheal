package selfHealingExamples.tests.registration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class RegistrationJSOUP extends BaseTest {

    @Test
    public void TestRegistration() {
        getDriver().get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = getDriver().getPageSource();
        Document document = Jsoup.parse(HTML_SOURCE_CODE);
        int inputs = document.body().getElementsByTag("input").size();
        assertEquals(7, inputs);
    }
}