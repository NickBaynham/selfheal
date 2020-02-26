package forms;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.util.JSONPObject;
import io.nickbaynham.automation.selfhealing.*;
import io.nickbaynham.automation.selfhealing.controllers.DocumentController;
import io.nickbaynham.automation.selfhealing.controllers.ElementNotFoundException;
import io.nickbaynham.automation.selfhealing.controllers.WebController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FormTests {

    @Test
    public void testForm() throws BrowserNotAvailableException, ElementNotFoundException {

        // Start the Chrome Browser
        actions = new WebController(WebAction.Browser.valueOf("Chrome"));
        query = (WebQuery) actions;

        // Go to the Form Page
        actions.get("http://localhost:7800/example3b.html");
        doc = DocumentController.getInstance(query.getHtml());

        // Enter "Scott" in the "First Name" field
        actions.highlight(doc.getLocator(Tag.input, "First Name"));
        actions.enterText(doc.getLocator(Tag.input, "First Name"), "Scott");

        // Enter "Steele" in the "Last Name" field
        actions.highlight(doc.getLocator(Tag.input, "last"));
        actions.enterText(doc.getLocator(Tag.input, "last"), "Steele");

        // Quit the Browser
        actions.close();
    }

    @Test
    public void FormJSONTest() throws IOException, ParseException, BrowserNotAvailableException, ElementNotFoundException {

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
        }

        // Quit the Browser
        actions.close();
    }

    private WebAction actions;
    private WebQuery query;
    private Locate doc;
}
