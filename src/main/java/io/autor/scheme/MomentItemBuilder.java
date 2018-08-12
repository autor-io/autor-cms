package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public class MomentItemBuilder extends ItemBuilder<MomentItem, MomentItemBuilder> {

    @Override
    protected MomentItem buildItem() {
        return new MomentItem(capacity, required);
    }

    public MomentItemBuilder(StructureBuilder structureBuilder) {
        super(structureBuilder);
    }
}
