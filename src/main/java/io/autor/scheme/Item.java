package io.autor.scheme;

import io.autor.content.Payload;

/**
 * @author Stephan Grundner
 */
public abstract class Item {

    Structure structure;
    String name;

    private final int capacity;
    private final int required;

    public Structure getStructure() {
        return structure;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRequired() {
        return required;
    }

    public abstract Payload createPayload();

    protected Item(int capacity, int required) {
        this.capacity = capacity;
        this.required = required;
    }
}
