package base;

import framework.selfheal.discovery.controllers.WebController;
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
