package io.nickbaynham.automation.selfhealing;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Inputs {
    private Elements inputs;
    private List<ElementObject> elements = new ArrayList<>();

    public Inputs(Document document) {
        inputs = document.getElementsByTag("input");
        for (Element input : inputs) {
            String tag = input.tagName();
            String id = input.id();
            String name = input.attr("name");
            String text = input.text();
            String css = input.cssSelector();
            String innerText = input.text();
            ElementObject elementObject = new ElementObject();
            elementObject.setTag(ElementTag.valueOf(tag));
            elementObject.setId(id);
            elementObject.setCss(css);
            elements.add(elementObject);
        }
    }

    public List<ElementObject> getElements() {
        return elements;
    }

    public Elements getMatches(String matcher) {
        return inputs;
    }
}
