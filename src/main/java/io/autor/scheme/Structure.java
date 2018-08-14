package io.autor.scheme;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Stephan Grundner
 */
public class Structure {

    private final String name;
    private final int revision;
    private final Map<String, Item> items = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public int getRevision() {
        return revision;
    }

    Item putItem(String name, Item item) {
        Item existing = items.put(name, item);
        if (existing != null) {
            existing.structure = null;
            existing.name = null;
        }

        if (item != null) {
            item.structure = this;
            item.name = name;
        }

        return existing;
    }

    public Map<String, Item> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public Structure(String name, int revision) {
        this.name = name;
        this.revision = revision;
    }
}
