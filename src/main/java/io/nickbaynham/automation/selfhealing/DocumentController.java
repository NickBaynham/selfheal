package io.nickbaynham.automation.selfhealing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class DocumentController {
    private static String pageSource;
    private static Elements inputs;

    public static String getPageSource() {
        return pageSource;
    }

    public static Elements getInputs() {
        return inputs;
    }

    public static List<ElementObject> getElements() {
        return ElementController.getElements();
    }

    public static void setPageSource(String pageSource) {
        DocumentController.pageSource = pageSource;
        Document document = Jsoup.parse(pageSource);
        inputs = document.getElementsByTag("input");
        for (Element element : inputs) {
            ElementObject elementObject = new ElementObject();
            elementObject.setTag(ElementTag.valueOf(element.tagName()));
            elementObject.setCss(element.cssSelector());
            ElementController.add(elementObject);
        }
    }
}
