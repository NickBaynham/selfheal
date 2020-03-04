package io.nickbaynham.automation.selfhealing.controllers;

import org.jsoup.nodes.Element;

public interface WebElementPredicate {
    boolean test(Element element);
}
