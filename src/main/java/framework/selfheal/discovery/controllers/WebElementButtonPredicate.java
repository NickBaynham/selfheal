package framework.selfheal.discovery.controllers;

import org.jsoup.nodes.Element;

public class WebElementButtonPredicate implements WebElementPredicate {
    @Override
    public boolean test(Element element) {
        return element.tagName().equals("button");
    }
}
