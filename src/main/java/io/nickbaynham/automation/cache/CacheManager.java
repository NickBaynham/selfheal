package io.nickbaynham.automation.cache;

/**
 *   Manage Cache instance
 */
public class CacheManager {
    private static Cache cache;
    public static Cache getInstance(String identifier) {
        if (cache == null) {
            cache = new Cache(identifier);
        }
        return cache;
    }
}
