package discovery;

import framework.selfheal.discovery.BrowserNotAvailableException;
import framework.selfheal.discovery.Locate;
import framework.selfheal.discovery.Tag;
import framework.selfheal.discovery.WebAction;
import framework.selfheal.discovery.WebQuery;
import framework.selfheal.discovery.controllers.DocumentController;
import framework.selfheal.discovery.controllers.ElementNotFoundException;
import framework.selfheal.discovery.controllers.WebController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

public class DiscoveryJson {

    @Test
    public void discoveryWithJson() throws IOException, ParseException, BrowserNotAvailableException, ElementNotFoundException {

        // Start the Chrome Browser
        actions = new WebController(WebAction.Browser.valueOf("Chrome"));
        query = (WebQuery) actions;


        // Go to the Form Page
        FileReader fileReader = new FileReader("src/test/resources/templates/form.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
        String url = jsonObject.get("url").toString();
        System.out.println("Loading: "+ url);
        actions.get(url);
        doc = DocumentController.getInstance(query.getHtml());

        // Using template and data, process the fields
        JSONArray fields = (JSONArray) jsonObject.get("fields");

        for (Object element : fields) {
            String label = ((JSONObject) element).get("label").toString();
            String type = ((JSONObject) element).get("type").toString();
            String data = ((JSONObject) element).get("data").toString();
            System.out.println("Enter '" + data + "' into '" + label + "' field.");
            actions.highlight(doc.getLocator(Tag.input, label));
            actions.enterText(doc.getLocator(Tag.input, label), data);
            actions.highlight(doc.getLocator(Tag.input, label));
        }

        // Quit the Browser
        actions.close();
    }

    private WebAction actions;
    private WebQuery query;
    private Locate doc;
}
