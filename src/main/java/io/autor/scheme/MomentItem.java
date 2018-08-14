package io.autor.scheme;

import io.autor.content.Moment;
import io.autor.content.Payload;

/**
 * @author Stephan Grundner
 */
public class MomentItem extends ValueItem {

    @Override
    public Payload createPayload() {
        return new Moment();
    }

    public MomentItem(int capacity, int required) {
        super(capacity, required);
    }
}
