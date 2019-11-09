package io.nickbaynham.automation.selfhealing.controllers;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(String value) {
        super(value);
    }
}
