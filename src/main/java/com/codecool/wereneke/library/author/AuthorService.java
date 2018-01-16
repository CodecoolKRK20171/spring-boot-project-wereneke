package com.codecool.wereneke.library.author;

import com.codecool.wereneke.library.common.Service;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorService implements Service<Author> {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public Author findOne(int id) {

        Author author = this.authorRepository.findOne(id);
        if (author == null); //raise custom exception
        return author;
    }

    @Override
    public Iterable<Author> findAll() {
        return this.authorRepository.findAll();
    }
}
