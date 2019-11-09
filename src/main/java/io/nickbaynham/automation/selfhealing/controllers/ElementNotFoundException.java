package io.nickbaynham.automation.selfhealing.controllers;

public class ElementNotFoundException extends Exception {
    ElementNotFoundException(String value) {
        super(value);
    }
}
