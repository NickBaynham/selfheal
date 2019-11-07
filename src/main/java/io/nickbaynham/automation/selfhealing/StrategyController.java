package io.nickbaynham.automation.selfhealing;

public class StrategyController {

    private static final String[] attributes = {
            "id",
            "name",
            "something",
            "cool",
            "placeholder"
    };

    public static String getInputLocator(String token) throws ElementNotFoundException {
        return DocumentController.getInstance("").getFuzzyMatch(attributes, "input", token);
    }

    public static String getButtonLocator(String token) throws ElementNotFoundException {
        return DocumentController.getInstance("").getFuzzyMatch(attributes, "button", token);
    }
}
