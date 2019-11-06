package io.nickbaynham.automation.selfhealing;

import java.util.ArrayList;
import java.util.List;

class Strategies {

    private List<Strategy> strategies = new ArrayList<>();

    List<Strategy> getStrategies() {
        return strategies;
    }

    Strategies() {
        strategies.add(new Strategy());
    }
}