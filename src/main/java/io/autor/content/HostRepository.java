package io.autor.content;

import io.autor.IdentifiableRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stephan Grundner
 */
@Repository
public interface HostRepository extends IdentifiableRepository<Host> {

    Host findByName(String name);
}
