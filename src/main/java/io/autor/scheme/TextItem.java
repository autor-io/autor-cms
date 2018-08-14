package io.autor.scheme;

import io.autor.content.Payload;
import io.autor.content.Text;

/**
 * @author Stephan Grundner
 */
public class TextItem extends ValueItem {

    private boolean multiline;

    public boolean isMultiline() {
        return multiline;
    }

    @Override
    public Payload createPayload() {
        return new Text();
    }

    public TextItem(int capacity, int required, boolean multiline) {
        super(capacity, required);
        this.multiline = multiline;
    }
}
