package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public class TextItem extends ValueItem {

    private boolean multiline;

    public boolean isMultiline() {
        return multiline;
    }

    public TextItem(int capacity, int required, boolean multiline) {
        super(capacity, required);
        this.multiline = multiline;
    }
}
