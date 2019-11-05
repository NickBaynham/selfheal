package io.nickbaynham.automation.selfhealing;

public class ElementObject {
    private ElementTag tag;
    private String id;
    private String name;
    private String text;
    private String css;

    void setCss(String css) {
        this.css = css;
    }

    public String getCss() {
        return css;
    }

    void setTag(ElementTag tag) {
        this.tag = tag;
    }

    public ElementTag getTag() {
        return tag;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}