package io.nickbaynham.automation.selfhealing;

import io.nickbaynham.automation.selfhealing.controllers.ElementNotFoundException;

public interface Locate {

    String getLocator(Tag tag, String matcher) throws ElementNotFoundException;
}
