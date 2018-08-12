package io.autor.content;

import io.autor.AbstractIdentifiable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Stephan Grundner
 */
@Entity
public class Author extends AbstractIdentifiable {

    @Column(unique = true, nullable = false)
    private String username;

    public String getUsername() {
        return username;
    }

    public Author(String username) {
        this.username = username;
    }

    private Author() { }
}
