package framework.selfheal.discovery.controllers;

public class ElementNotFoundException extends Exception {
    ElementNotFoundException(String value) {
        super(value);
    }
}
