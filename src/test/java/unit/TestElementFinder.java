package unit;

import io.nickbaynham.automation.selfhealing.DocumentController;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestElementFinder {
    @Test
    public void test() {

        // Create a Document Object that represents the actual HTML document in memory
        String html = "<html><body><input id=\"firstName\"></input></body></html>";
        DocumentController documentController = new DocumentController(html);
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
}
