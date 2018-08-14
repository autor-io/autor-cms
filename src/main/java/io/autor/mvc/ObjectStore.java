package io.autor.mvc;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Stephan Grundner
 */
public class ObjectStore {

    private final Map<Object, String> ids = new IdentityHashMap<>();

    public String getId(Object object) {
        return ids.get(object);
    }

    public String getOrCreateId(Object object) {
        String id = getId(object);
        if (id == null) {
            id = UUID.randomUUID().toString();
            ids.put(object, id);
        }

        return id;
    }

    public Object findObject(String id) {
        return ids.entrySet().stream()
                .filter(entry -> entry.getValue().equals(id))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
    }
}
