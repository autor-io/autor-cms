package io.autor.scheme;

import java.util.Map;
import java.util.Objects;

/**
 * @author Stephan Grundner
 */
public abstract class Item {

    Structure structure;

    private final int capacity;
    private final int required;

    public Structure getStructure() {
        return structure;
    }

    public String getName() {
        return structure.getItems().entrySet().stream()
                .filter(it -> Objects.equals(it.getValue(), this))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRequired() {
        return required;
    }

    protected Item(int capacity, int required) {
        this.capacity = capacity;
        this.required = required;
    }
}
