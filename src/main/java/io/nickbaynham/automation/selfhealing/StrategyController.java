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
}
