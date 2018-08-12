package io.autor.content;

import io.autor.IdentifiableRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stephan Grundner
 */
@Repository
public interface AuthorRepository extends IdentifiableRepository<Author> {

    Author findByUsername(String username);
}
