package io.nickbaynham.automation.selfhealing;

import io.nickbaynham.automation.selfhealing.Strategy;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.nickbaynham.automation.selfhealing.WebController.*;

public class StrategyController {

    public static void applyInputStrategy(String field, String text) {
        for (Strategy strategy : new Strategies().getStrategies()) {
            if (strategy.getTag().equals(ElementTag.input)) {
                for (Element input : DocumentController.getInputs()) {
                    String css = input.cssSelector();
                    String id = input.id();
                    if (id.equals(field)) {
                        enterText(css, text);
                        return;
                    }
                }
            }
        }
    }

    public static void applyFuzzyIdStrategyToInput(String field, String text) {

        // we need a map of inputs by their Id Text
        List<String> inputIds = new ArrayList<>();
        Map<String, Element> inputElements = new HashMap<>();
        for (Element input : DocumentController.getInputs()) {
            inputIds.add(input.id());
            inputElements.put(input.id(), input);
        }

        // Next we need to generate a fuzzy search on the Id Text
        final ExtractedResult extractedResult = FuzzySearch.extractOne(field, inputIds);
        String css = inputElements.get(extractedResult.getString()).cssSelector();
        enterText(css, text);
    }

    public static void applyFuzzyTextStrategyToButton(String field) {

        // we need a map of buttons by their inner text (labels)
        List<String> buttonText = new ArrayList<>();
        Map<String, Element> buttonElements = new HashMap<>();
        for (Element button : DocumentController.getInputs()) {
            buttonText.add(button.text());
            buttonElements.put(button.text(), button);
        }

        // Next we need to generate a fuzzy search on the Id Text
        final ExtractedResult extractedResult = FuzzySearch.extractOne(field, buttonText);
        String css = buttonElements.get(extractedResult.getString()).cssSelector();
        click(css);
    }

    public static void applyFuzzyTextStrategyAllAttributes(String matcher, String text) {

        // Find the best Input

        List<String> idList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        List<Element> elementList = new ArrayList<>();
        for (Element element : DocumentController.getInputs()) {
            idList.add(element.id());
            nameList.add(element.attr("name"));
            valueList.add(element.val());
            elementList.add(element);
        }

        List<String> bestList = new ArrayList<>();
        List<Element> bestElements = new ArrayList<>();
        ExtractedResult best = FuzzySearch.extractOne(matcher, idList);
        bestList.add(best.getString());
        bestElements.add(elementList.get(best.getIndex()));
        bestList.add(FuzzySearch.extractOne(matcher, nameList).getString());

        best = FuzzySearch.extractOne(matcher, nameList);
        bestList.add(best.getString());
        bestElements.add(elementList.get(best.getIndex()));

        best = FuzzySearch.extractOne(matcher, valueList);
        bestList.add(best.getString());
        bestElements.add(elementList.get(best.getIndex()));
        final ExtractedResult bestResult = FuzzySearch.extractOne(matcher, bestList);

        String css = bestElements.get(bestResult.getIndex()).cssSelector();
        enterText(css, text);
    }

//    private static String getCssSelector(String matcher) {
//        List<String> keys = new ArrayList<>();
//        Map<String, Element> elements = new HashMap<>();
//        for (Element element : DocumentController.getInputs()) {
//            //inputIds.add(input.id());
//            //inputElements.put(input.id(), input);
//        }
//
//        // Next we need to generate a fuzzy search on the Id Text
//        final ExtractedResult extractedResult = FuzzySearch.extractOne(field, inputIds);
//        //String css = inputElements.get(extractedResult.getString()).cssSelector();
//        return css;
//    }
}
