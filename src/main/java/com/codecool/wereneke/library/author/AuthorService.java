package com.codecool.wereneke.library.author;

import com.codecool.wereneke.library.common.NoSuchIdException;
import com.codecool.wereneke.library.common.Service;
import org.springframework.stereotype.Component;

@Component
public class AuthorService implements Service<Author> {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(Author author) {
        if (author.getId() == null) {
            this.authorRepository.save(author);
        }
    }

    @Override
    public void update(Author author) {

        if (author.getId() != null) this.authorRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public Author findOne(Integer id) throws NoSuchIdException {

        Author author = this.authorRepository.findOne(id);
        if (author == null) throw new NoSuchIdException();
        return author;
    }

    @Override
    public Iterable<Author> findAll() {
        return this.authorRepository.findAll();
    }
}
