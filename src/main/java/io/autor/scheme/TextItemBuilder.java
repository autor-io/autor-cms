package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public class TextItemBuilder extends ItemBuilder<TextItem, TextItemBuilder> {

    private boolean multiline;

    public TextItemBuilder setMultiline(boolean multiline) {
        this.multiline = multiline;

        return this;
    }

    @Override
    protected TextItem buildItem() {
        return new TextItem(capacity, required, multiline);
    }

    public TextItemBuilder(StructureBuilder structureBuilder) {
        super(structureBuilder);
    }
}
