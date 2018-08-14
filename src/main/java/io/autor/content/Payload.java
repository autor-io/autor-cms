package io.autor.content;

import io.autor.AbstractIdentifiable;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author Stephan Grundner
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kind")
public abstract class Payload extends AbstractIdentifiable {

    @Column(name = "item")
    String itemName;

    int ordinal = -1;

    @ManyToOne
    Fragment parent;

    @Access(AccessType.PROPERTY)
    private String path;

    public String getItemName() {
        return itemName;
    }

//    public void setStructureQualifier(String structureQualifier) {
//        this.structureQualifier = structureQualifier;
//    }

    public int getOrdinal() {
        return ordinal;
    }

//    public void setOrdinal(int ordinal) {
//        this.ordinal = ordinal;
//    }

    public Fragment getParent() {
        return parent;
    }

//    public void setParent(Fragment parent) {
//        this.parent = parent;
//    }

    public String getPath() {
        LinkedList<String> segments = new LinkedList<>();

        Payload payload = this;
        do {
            if (payload instanceof Fragment
                    && ((Fragment) payload).getParent() == null)
                break;

            segments.addFirst(String.format("%s[%d]",
                    payload.itemName,
                    payload.ordinal));

            payload = payload.getParent();
        } while (payload != null);

        return segments.stream().collect(Collectors.joining("."));
    }

    private void setPath(String path) {
        this.path = path;
    }
}
