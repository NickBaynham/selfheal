package framework.selfheal.discovery.controllers;

import org.jsoup.nodes.Element;

public interface WebElementPredicate {
    boolean test(Element element);
}
