package io.nickbaynham.automation.selfhealing;

public class Strategy {
    private ElementTag tag = ElementTag.input;
    private Attribute attribute = Attribute.id;
    private String css;

    public void setTag(ElementTag tag) {
        this.tag = tag;
    }

    ElementTag getTag() {
        return tag;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
