package script;

import base.BaseTest;
import framework.selfheal.discovery.controllers.WebController;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GettingHTML extends BaseTest {

    @Test
    public void getHTMLTest() {
        WebController.getInstance().getDriver().get("http://localhost:7800/bootstrap1.html#");
        final String HTML_SOURCE_CODE = WebController.getInstance().getDriver().getPageSource();
        System.out.println(HTML_SOURCE_CODE);
    }
}