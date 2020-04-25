package framework.selfheal.cache;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *   Serializable Cache for Storing Framework Data
 */
public class Cache {
    private String id;
    private String fileType = "dat";
    private Map<String, String> cache = new HashMap<>();

    public Cache(String id, String fileType) {
        this.id = id;
        this.fileType = fileType;
    }

    public Cache(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void add(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) throws ItemNotInCacheException {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            throw new ItemNotInCacheException(key);
        }
    }

    public void serializeCache() {
        try {
            FileOutputStream fos =
                    new FileOutputStream(getFileName());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cache);
            oos.close();
            fos.close();
            System.out.println("Serialized Cache data is saved in: " + getFileName());
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void deSerialize() throws NoCacheException {
        cache = null;

        try {
            FileInputStream fis = new FileInputStream(getFileName());
            ObjectInputStream ois = new ObjectInputStream(fis);
            cache = (Map<String, String>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cache was not created.");
            throw new NoCacheException(id);
        } catch(IOException e) {
            e.printStackTrace();
            return;
        } catch(ClassNotFoundException e) {
            System.out.println("Empty Cache...");
            throw new NoCacheException(e.getMessage());
        }

        // Display content using Iterator
        Set<Map.Entry<String, String>> set = cache.entrySet();
        for (Map.Entry<String, String> stringStringEntry : set) {
            System.out.print("key: " + stringStringEntry.getKey() + " & Value: ");
            System.out.println(stringStringEntry.getValue());
        }
    }

    private String getFileName() {
        return id + "." + fileType;
    }
}
