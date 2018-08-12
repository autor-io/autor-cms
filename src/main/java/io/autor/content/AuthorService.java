package io.autor.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Stephan Grundner
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findAuthorByUsername(String username) {
        return authorRepository.findByUsername(username);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author findOrCreateAuthorByUsername(String username) {
        Author author = findAuthorByUsername(username);
        if (author == null) {
            author = new Author(username);
            author = saveAuthor(author);
        }

        return author;
    }

    public Author currentAuthor() {
        String username = "";
        return findOrCreateAuthorByUsername(username);
    }
}
