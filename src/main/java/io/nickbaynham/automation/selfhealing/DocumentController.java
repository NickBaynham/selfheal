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
    private static String pageSource;
    private static Elements inputs;
    private static final String[] TAG_NAMES = {
        "input",
        "button"
    };

    private String html;
    private Document document;
    private Map<String, Elements> elements = new HashMap<>();

    public DocumentController(String html) {
        this.html = html;
        document = Jsoup.parse(html);
        elementsByTag();
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

    public static String getPageSource() {
        return pageSource;
    }

    @Deprecated
    public static Elements getInputs() {
        return inputs;
    }

    @Deprecated
    public static List<ElementObject> getElements() {
        return ElementController.getElements();
    }

    public static void setPageSource(String pageSource) {
        DocumentController.pageSource = pageSource;
        Document document = Jsoup.parse(pageSource);
        inputs = document.getElementsByTag("input");
        //elements.put("input", inputs);

        for (Element element : inputs) {
            ElementObject elementObject = new ElementObject();
            elementObject.setTag(ElementTag.valueOf(element.tagName()));
            elementObject.setCss(element.cssSelector());
            ElementController.add(elementObject);
        }
    }


    public Elements getElementsByTagName(String key) {
        return elements.get(key);
    }

    public static String[] getTagNames() {
        return TAG_NAMES;
    }

    public String getFuzzyMatch(String[] attributes, String tag, String value) {

        // Initialize Result Set
        int score = 0;
        String cssSelector = null;
        Elements tagElements = elements.get(tag);
        if (tagElements == null) return null;
        List<String> values = new ArrayList<>();

        // Fuzzy Search by Inner Text

        for (Element tagElement : tagElements) {
            values.add(tagElement.wholeText());
        }

        ExtractedResult result = FuzzySearch.extractOne(value, values);
        score = result.getScore();
        cssSelector = tagElements.get(result.getIndex()).cssSelector();

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
        return cssSelector;
    }
}

