package io.autor.scheme;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Stephan Grundner
 */
public class StructureBuilder implements SchemeBuilder {

    private final Map<String, ItemBuilder<?, ?>> itemBuilders = new LinkedHashMap<>();

    private final StructureService structureService;
    private final String name;
    private final int revision;

    private <B extends ItemBuilder> B addItem(Class<B> itemBuilderClass, String itemName) {
        try {
            Constructor<B> constructor = itemBuilderClass.getConstructor(StructureBuilder.class);
            B itemBuilder = BeanUtils.instantiateClass(constructor, this);
            itemBuilders.put(itemName, itemBuilder);

            return itemBuilder;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TextItemBuilder addTextItem(String itemName) {
        return addItem(TextItemBuilder.class, itemName);
    }

    @Override
    public NumberItemBuilder addNumberItem(String itemName) {
        return addItem(NumberItemBuilder.class, itemName);
    }

    @Override
    public MomentItemBuilder addMomentItem(String itemName) {
        return addItem(MomentItemBuilder.class, itemName);
    }

    @Override
    public StructureItemBuilder addStructureItem(String itemName) {
        return addItem(StructureItemBuilder.class, itemName);
    }

    public Structure buildStructure() {
        Structure structure = new Structure(name, revision);
        itemBuilders.forEach((itemName, itemBuilder) -> {
            Item item = itemBuilder.buildItem();
            structure.putItem(itemName, item);
        });

        return structure;
    }

    @Override
    public Structure buildAndRegister() {
        Structure structure = buildStructure();
        structureService.registerStructure(structure);

        return structure;
    }

    public StructureBuilder(StructureService structureService, String name, int revision) {
        this.structureService = structureService;
        this.name = name;
        this.revision = revision;
    }
}
