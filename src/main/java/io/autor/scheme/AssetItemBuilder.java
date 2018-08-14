package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public class AssetItemBuilder extends ItemBuilder<AssetItem, AssetItemBuilder> {

    @Override
    protected AssetItem buildItem() {
        return new AssetItem(capacity, required);
    }

    public AssetItemBuilder(StructureBuilder structureBuilder) {
        super(structureBuilder);
    }
}
