package io.autor.content;

import io.autor.AbstractIdentifiable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Stephan Grundner
 */
@Entity
public class Host extends AbstractIdentifiable {

    public static final String FK_COLUMN_NAME = "host_id";

    @Column(name = "fqhn", unique = true)
    private String name;

    public static String getFkColumnName() {
        return FK_COLUMN_NAME;
    }

    public Host(String name) {
        this.name = name;
    }

    protected Host() { }
}
