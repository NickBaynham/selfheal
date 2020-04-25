package framework.selfheal.discovery.controllers;

/**
 *   NoElementsFoundException - thrown when filtering results in an empty list
 */
class NoElementsFoundException extends Exception {
    NoElementsFoundException(String message) {
        super(message);
    }
}
