package io.autor.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @author Stephan Grundner
 */
@Entity
public class Moment extends Payload {

    @Column(name = "temporal")
    private LocalDateTime temporal;

    public LocalDateTime getTemporal() {
        return temporal;
    }

    public void setTemporal(LocalDateTime temporal) {
        this.temporal = temporal;
    }
}
