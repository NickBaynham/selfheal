package io.nickbaynham.automation.selfhealing;

import io.nickbaynham.automation.selfhealing.Strategy;
import static io.nickbaynham.automation.selfhealing.WebController.*;

public class StrategyController {
    public static void applyInputStrategy(String field, String text) {
        for (Strategy strategy : new Strategies().getStrategies()) {
            if (strategy.getTag().equals(ElementTag.input)) {
                for (org.jsoup.nodes.Element input : DocumentController.getInputs()) {
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
}
