package io.nickbaynham.automation.selfhealing;

import java.util.ArrayList;
import java.util.List;

public class Strategies {

    private List<Strategy> strategies = new ArrayList<>();

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public Strategies() {
        strategies.add(new Strategy());
    }
}



