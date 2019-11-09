package io.nickbaynham.automation.selfhealing.controllers;

import java.util.ArrayList;
import java.util.List;

public class ElementController {
    private static List<ElementObject> elements = new ArrayList<>();

    public static List<ElementObject> getElements() {
        return elements;
    }

    static void add(ElementObject elementObject) {
        elements.add(elementObject);
    }
}
