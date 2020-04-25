package framework.selfheal.discovery;

public interface WebQuery {
    boolean isVisible(String element);
    boolean isEnabled(String element);
    boolean isPresent(String element);
    boolean isSelected(String element);
    String getValue(String element);
    String getInnerText(String element);
    String getDefaultValue(String element);
    String getHtml();
}
