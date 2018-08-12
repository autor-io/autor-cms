package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public class NumberItemBuilder extends ItemBuilder<NumberItem, NumberItemBuilder> {

    private int decimalPoints;

    public NumberItemBuilder setDecimalPoints(int decimalPoints) {
        this.decimalPoints = decimalPoints;

        return this;
    }

    @Override
    protected NumberItem buildItem() {
        return new NumberItem(capacity, required, decimalPoints);
    }

    public NumberItemBuilder(StructureBuilder structureBuilder) {
        super(structureBuilder);
    }
}
