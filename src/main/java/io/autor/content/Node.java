package io.autor.content;

import io.autor.AbstractIdentifiable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stephan Grundner
 */
@Entity
public class Node extends AbstractIdentifiable {

    @ManyToOne(optional = false)
    private Hierarchy hierarchy;

    @ManyToOne
    private Node parent;

    @OneToMany(mappedBy = "parent")
    private final List<Node> children = new ArrayList<>();

    @ManyToOne
    private Bundle bundle;

    private String url;

    @ManyToOne
    private Author createdBy;

    @ManyToOne
    private Author modifiedBy;

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

//    public void setHierarchy(Hierarchy hierarchy) {
//        this.hierarchy = hierarchy;
//    }

    public Node getParent() {
        return parent;
    }

//    public void setParent(Node parent) {
//        this.parent = parent;
//    }

    public List<Node> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
