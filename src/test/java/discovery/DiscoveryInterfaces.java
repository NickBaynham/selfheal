package discovery;

import framework.selfheal.discovery.BrowserNotAvailableException;
import framework.selfheal.discovery.Locate;
import framework.selfheal.discovery.Tag;
import framework.selfheal.discovery.WebAction;
import framework.selfheal.discovery.WebQuery;
import framework.selfheal.discovery.controllers.DocumentController;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import framework.selfheal.discovery.controllers.WebController;
import org.junit.Test;

public class DiscoveryInterfaces {

    @Test
    public void discoveryWithInterfaces() throws BrowserNotAvailableException, ElementNotFoundException {

        // Start the Chrome Browser
        actions = new WebController(WebAction.Browser.valueOf("Chrome"));
        query = (WebQuery) actions;

        // Go to the Form Page
        actions.get("http://localhost:7800/bootstrap1.html");
        doc = DocumentController.getInstance(query.getHtml());

        // Enter "Jason" in the "First Name" field
        actions.highlight(doc.getLocator(Tag.input, "First Name"));
        actions.enterText(doc.getLocator(Tag.input, "First Name"), "Jason");

        // Enter "Beard" in the "Last Name" field
        actions.highlight(doc.getLocator(Tag.input, "last"));
        actions.enterText(doc.getLocator(Tag.input, "last"), "Beard");

        // Delay so we can see the text was entered
        actions.highlight(doc.getLocator(Tag.input, "last"));

        // Quit the Browser
        actions.close();
    }

    private WebAction actions;
    private WebQuery query;
    private Locate doc;
}
