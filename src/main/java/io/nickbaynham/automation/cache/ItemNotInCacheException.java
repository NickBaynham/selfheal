package io.nickbaynham.automation.cache;

public class ItemNotInCacheException extends Exception {
    public ItemNotInCacheException(String message) {
        super(message);
    }
}
