package unit;

import io.nickbaynham.automation.garbage.DocumentController;
import io.nickbaynham.automation.selfhealing.ElementNotFoundException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

public class TestElementFinder {
    @Test
    public void test() {

        // Create a Document Object that represents the actual HTML document in memory
        String html = "<html><body><input id=\"firstName\"></input></body></html>";
        DocumentController documentController = DocumentController.getInstance(html);
        System.out.println(documentController.getHtml());
        System.out.println(documentController.getDocument());
        System.out.println(documentController.getElementsByTag("input"));
        System.out.println(documentController.getElement("input", "id", "firstName"));
        System.out.println(documentController.getFuzzyMatch("input", "id", "First"));
        System.out.println(documentController.getFuzzyMatch("input", "id", "Bob"));         // fuzzy will find a match no matter what!
    }

    @Test
    public void testFromFile() throws IOException {
        File input = new File("src/test/resources/html//example2.html");
        DocumentController documentController = new DocumentController(input);
        System.out.println(documentController.getHtml());
        System.out.println(documentController.getDocument());
        System.out.println(documentController.getElementsByTag("input"));
        System.out.println(documentController.getElement("input", "id", "firstName"));
        System.out.println(documentController.getFuzzyMatch("input", "id", "First"));
        System.out.println(documentController.getFuzzyMatch("input", "id", "Bob"));         // fuzzy will find a match no matter what!
    }

    @Test
    public void testDifferentAttributes() throws IOException {
        File input = new File("src/test/resources/html//example2a.html");
        DocumentController documentController = new DocumentController(input);
        System.out.println(documentController.getFuzzyMatch("input", "id", "First"));
        System.out.println(documentController.getFuzzyMatch("input", "name", "Last Name"));
        System.out.println(documentController.getFuzzyMatch("input", "something", "Your Age"));
        System.out.println(documentController.getFuzzyMatch("input", "cool", "Occu"));
        System.out.println(documentController.getFuzzyMatch("input", "placeholder", "Food Choice"));
    }

    @Test
    public void testMultiAttributeFuzzySearch() throws IOException, ElementNotFoundException {
        String[] attributes = {
                "id",
                "name",
                "something",
                "cool",
                "placeholder"
        };
        File input = new File("src/test/resources/html//example2a.html");
        DocumentController documentController = new DocumentController(input);
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "First"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Last Name"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Your Age"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Occu"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Food Choice"));
    }

    @Test
    public void testFuzzySearchIncludesText() throws IOException, ElementNotFoundException {
        String[] attributes = {
                "id",
                "name",
                "something",
                "cool",
                "placeholder",
                "value"
        };
        File input = new File("src/test/resources/html//example3b.html");
        DocumentController documentController = new DocumentController(input);
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "First"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Last Name"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Your Age"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Occu"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Food Choice"));
        System.out.println(documentController.getFuzzyMatch(attributes, "button", "save"));
        System.out.println(documentController.getFuzzyMatch(attributes, "button", "cancel"));
    }

    @Test
    public void testFuzzySearchIncludesLabel() throws IOException, ElementNotFoundException {
        String[] attributes = {
                "id",
                "name",
                "something",
                "cool",
                "placeholder",
                "value"
        };
        File input = new File("src/test/resources/html//labels.html");
        DocumentController documentController = new DocumentController(input);
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "First Name"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Last Name"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Your Age"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Occu"));
        System.out.println(documentController.getFuzzyMatch(attributes, "input", "Food Choice"));

        // We should get an exception

        try {
            documentController.getFuzzyMatch(attributes, "button", "Save");
        } catch (ElementNotFoundException e) {
            assertEquals(e.getMessage(), "Element Not Found: button=Save");
        }
    }
}
