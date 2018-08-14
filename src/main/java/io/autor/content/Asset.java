package io.autor.content;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Stephan Grundner
 */
@Entity
public class Asset extends Payload {

    @ManyToOne
    @JoinColumn(name = "upload_id")
    private Upload upload;

    public Upload getUpload() {
        return upload;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }
}
