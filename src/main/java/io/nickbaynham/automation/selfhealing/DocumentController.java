package io.nickbaynham.automation.selfhealing;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentController {

    private static DocumentController documentController;
    private static final String[] TAG_NAMES = {
        "input",
        "button",
        "label"
    };

    private String html;
    private Document document;
    private Map<String, Elements> elements = new HashMap<>();

    private DocumentController(String html) {
        this.html = html;
        document = Jsoup.parse(html);
        elementsByTag();
    }

    public static DocumentController getInstance(String html) {
        if (documentController == null) documentController = new DocumentController(html);
        return documentController;
    }

    public DocumentController(File file) throws IOException {
        document = Jsoup.parse(file, "UTF-8", "");
        this.html = document.toString();
        elementsByTag();
    }

    private void elementsByTag() {
        for (String tag : TAG_NAMES) {
            elements.put(tag, document.getElementsByTag(tag));
        }
    }

    public String getElement(String tag, String attribute, String value) {
        Elements tagElements = elements.get(tag);
        for (Element tagElement : tagElements) {
            if (tagElement.attr(attribute).equals(value)) return tagElement.cssSelector();
        }
        return null;
    }

    public String getFuzzyMatch(String tag, String attribute, String value) {
        Elements tagElements = elements.get(tag);
        List<String> values = new ArrayList<>();
        for (Element tagElement : tagElements) {
            values.add(tagElement.attr(attribute));
        }
        ExtractedResult result = FuzzySearch.extractOne(value, values);
        return tagElements.get(result.getIndex()).cssSelector();
    }

    public Elements getElementsByTag(String tag) {
        return elements.get(tag);
    }
    public String getHtml() {
        return html;
    }
    public Document getDocument() {
        return document;
    }
    public String getFuzzyMatch(String[] attributes, String tag, String value) throws ElementNotFoundException {

        // Initialize Result Set

        int score = 0;
        String cssSelector = null;
        List<String> values = new ArrayList<>();
        Elements tagElements = elements.get(tag);
        if (tagElements == null || tagElements.isEmpty()) throw new ElementNotFoundException("Element Not Found: " + tag + "=" + value);

        // Fuzzy Search by Inner Text

        for (Element tagElement : tagElements) {
            values.add(tagElement.wholeText());
        }

        ExtractedResult result = FuzzySearch.extractOne(value, values);
        score = result.getScore();
        cssSelector = tagElements.get(result.getIndex()).cssSelector();

        // Fuzzy Search by Label

        Elements labels = elements.get("label");
        if (labels != null && !labels.isEmpty()) {
            values = new ArrayList<>();
            for (Element label : labels) {
                values.add(label.wholeText());
            }

            result = FuzzySearch.extractOne(value, values);
            if (result.getScore() > score) {
                score = result.getScore();
                String id = labels.get(result.getIndex()).attr("for");
                cssSelector = document.attr("id", id).cssSelector();
            }
        }

        // Fuzzy Search by Attribute Value

        for (String attribute : attributes) {
            values = new ArrayList<>();
            for (Element tagElement : tagElements) {
                values.add(tagElement.attr(attribute));
            }
            result = FuzzySearch.extractOne(value, values);
            if (result.getScore() >= score) {
                score = result.getScore();
                cssSelector = tagElements.get(result.getIndex()).cssSelector();
            }
        }
        if (cssSelector == null) throw new ElementNotFoundException("Element Not Found: " + tag + "=" + value);
        return cssSelector;
    }
}