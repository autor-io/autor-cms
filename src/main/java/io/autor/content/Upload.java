package io.autor.content;

import io.autor.AbstractIdentifiable;

import javax.persistence.Entity;

/**
 * @author Stephan Grundner
 */
@Entity
public class Upload extends AbstractIdentifiable {

    private String filename;
    private String checksum;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
