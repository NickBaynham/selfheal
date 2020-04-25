package framework.selfheal.discovery;

import framework.selfheal.discovery.controllers.ElementNotFoundException;

public interface WebAction {
    void inject(String script);
    void highlight(String element);
    void enterText(String element,String text);
    void click(String element);
    void selectRadio(String element);
    void select(String token, String element) throws ElementNotFoundException;
    void get(String url);
    void setup(Browser browser) throws BrowserNotAvailableException;
    void close();

    enum Browser {
        Chrome, Firefox, Safari, IE, Edge
    }
}
