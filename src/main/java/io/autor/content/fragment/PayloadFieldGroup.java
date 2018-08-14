package io.autor.content.fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephan Grundner
 */
public class PayloadFieldGroup implements Renderable {

    private String itemName;

    private final List<Field> fields = new ArrayList<>();

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Field> getFields() {
        return fields;
    }
}
