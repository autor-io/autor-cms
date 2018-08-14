package io.autor.content.fragment;

import org.springframework.core.Conventions;

/**
 * @author Stephan Grundner
 */
public interface Renderable {

    default String getTemplate() {
        return Conventions.getVariableName(this);
    }
}
