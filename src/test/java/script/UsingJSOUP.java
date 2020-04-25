package script;

import base.BaseTest;
import framework.selfheal.discovery.controllers.WebController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class UsingJSOUP extends BaseTest {

    @Test
    public void jsoupDemoTest() {
        WebDriver driver = WebController.getInstance().getDriver();
        driver.get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = driver.getPageSource();
        Document document = Jsoup.parse(HTML_SOURCE_CODE);
        int numberOfInputs = document.body().getElementsByTag("input").size();
        assertEquals(7, numberOfInputs);
    }
}