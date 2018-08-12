package io.autor.content;

import io.autor.AbstractIdentifiable;

import javax.persistence.*;

/**
 * @author Stephan Grundner
 */
@Entity
public class Bundle extends AbstractIdentifiable {

    @OneToOne
    private Page published;

    @OneToOne
    private Page draft;

    @ManyToOne
    private Author createdBy;

    public Page getPublished() {
        return published;
    }

    public void setPublished(Page published) {
        this.published = published;
    }

    public Page getDraft() {
        return draft;
    }

    public void setDraft(Page draft) {
        this.draft = draft;
    }

    public Author getCreatedBy() {
        return createdBy;
    }

    public Bundle(Author createdBy) {
        this.createdBy = createdBy;
    }

    public Bundle() { }
}
