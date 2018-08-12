package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public class NumberItem extends ValueItem {

    private final int decimalPoints;

    public NumberItem(int capacity, int required, int decimalPoints) {
        super(capacity, required);
        this.decimalPoints = decimalPoints;
    }
}
