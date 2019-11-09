package io.nickbaynham.automation.selfhealing;

public interface WebAction {
    void inject(String script);
    void highlight(String element);
    void enterText(String element,String text);
    void click(String element);
    void select(String element);
    void get(String url);
    void setup(Browser browser) throws BrowserNotAvailableException;
    void close();

    enum Browser {
        Chrome, Firefox, Safari, IE, Edge
    }
}
