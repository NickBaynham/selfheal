package framework.selfheal.discovery;

import framework.selfheal.discovery.controllers.ElementNotFoundException;

public interface Locate {

    String getLocator(Tag tag, String matcher) throws ElementNotFoundException;
}
