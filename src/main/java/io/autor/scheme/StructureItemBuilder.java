package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public class StructureItemBuilder extends ItemBuilder<StructureItem, StructureItemBuilder> {

//    public StructureItemBuilder setMultiline(boolean multiline) {
//        this.multiline = multiline;
//
//        return this;
//    }

    @Override
    protected StructureItem buildItem() {
        return new StructureItem(capacity, required);
    }

    public StructureItemBuilder(StructureBuilder structureBuilder) {
        super(structureBuilder);
    }
}
