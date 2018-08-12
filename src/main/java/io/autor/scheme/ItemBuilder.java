package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public abstract class ItemBuilder<T extends Item, B extends ItemBuilder<T, B>> implements SchemeBuilder {

    private final StructureBuilder structureBuilder;

    protected int capacity = 1;
    protected int required = 1;

    @SuppressWarnings("unchecked")
    public B setCapacity(int capacity) {
        this.capacity = capacity;

        return (B) this;
    }

    @SuppressWarnings("unchecked")
    public B setRequired(int required) {
        this.required = required;

        return (B) this;
    }

    @Override
    public TextItemBuilder addTextItem(String itemName) {
        return structureBuilder.addTextItem(itemName);
    }

    @Override
    public NumberItemBuilder addNumberItem(String itemName) {
        return structureBuilder.addNumberItem(itemName);
    }

    @Override
    public MomentItemBuilder addMomentItem(String itemName) {
        return structureBuilder.addMomentItem(itemName);
    }

    @Override
    public StructureItemBuilder addStructureItem(String itemName) {
        return structureBuilder.addStructureItem(itemName);
    }

    protected abstract T buildItem();

    @Override
    public Structure buildAndRegister() {
        return structureBuilder.buildAndRegister();
    }

    public ItemBuilder(StructureBuilder structureBuilder) {
        this.structureBuilder = structureBuilder;
    }
}
