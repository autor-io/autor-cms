package io.autor.scheme;

import io.autor.content.Fragment;
import io.autor.content.Payload;

/**
 * @author Stephan Grundner
 */
public class StructureItem extends Item {

    @Override
    public Payload createPayload() {
        return new Fragment();
    }

    protected StructureItem(int capacity, int required) {
        super(capacity, required);
    }
}
