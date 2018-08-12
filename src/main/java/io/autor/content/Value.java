package io.autor.content;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Stephan Grundner
 */
@Entity
public class Value extends Payload {

    @Column(name = "value")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
