package io.autor.scheme;

/**
 * @author Stephan Grundner
 */
public interface SchemeBuilder {

    TextItemBuilder addTextItem(String itemName);
//    NumberItemBuilder addNumberItem(String itemName);
    MomentItemBuilder addMomentItem(String itemName);
    StructureItemBuilder addStructureItem(String itemName);
    AssetItemBuilder addAssetItem(String itemName);

    Structure buildAndRegister();
}
