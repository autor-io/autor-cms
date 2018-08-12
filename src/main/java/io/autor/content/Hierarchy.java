package io.autor.content;

import javax.persistence.*;

/**
 * @author Stephan Grundner
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {Host.FK_COLUMN_NAME, "name"}))
public class Hierarchy {

    public static final String DEFAULT_HIERARHY_NAME = "default";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = Host.FK_COLUMN_NAME)
    private Host host;

    private String name = DEFAULT_HIERARHY_NAME;
}
