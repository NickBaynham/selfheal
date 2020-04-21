package io.nickbaynham.automation.selfhealing.controllers;

/**
 *   CachedLocatorNotFound - Exception thrown if the required locator is not in the cache
 */
class CachedLocatorNotFound extends Exception {
    CachedLocatorNotFound(String message) {
        super(message);
    }
}
