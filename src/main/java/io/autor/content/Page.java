package io.autor.content;

import io.autor.AbstractIdentifiable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stephan Grundner
 */
@Entity
public class Page extends AbstractIdentifiable {

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Bundle bundle;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Fragment content;

    private String label;
    private String url;

    @ManyToOne
    private Author modifiedBy;

    private LocalDateTime publishOn;
    private LocalDateTime publishedUntil;

    @OneToMany(cascade = {})
    @JoinTable(name = "page_host",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "host_id"))
    private final Set<Host> hosts = new HashSet<>();

    public Bundle getBundle() {
        return bundle;
    }

    public Fragment getContent() {
        return content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Author getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Author modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getPublishOn() {
        return publishOn;
    }

    public void setPublishOn(LocalDateTime publishOn) {
        this.publishOn = publishOn;
    }

    public LocalDateTime getPublishedUntil() {
        return publishedUntil;
    }

    public void setPublishedUntil(LocalDateTime publishedUntil) {
        this.publishedUntil = publishedUntil;
    }

    @Transient
    public boolean isPublished() {
        return bundle.getPublished() == this;
    }

    public Set<Host> getHosts() {
        return hosts;
    }

    public Page(Bundle bundle, Fragment content) {
        this.bundle = bundle;
        this.content = content;
    }

    public Page() { }
}
