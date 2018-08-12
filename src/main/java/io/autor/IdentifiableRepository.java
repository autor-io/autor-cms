package io.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Stephan Grundner
 */
@NoRepositoryBean
public interface IdentifiableRepository<T extends Identifiable> extends JpaRepository<T, Long> {

}
