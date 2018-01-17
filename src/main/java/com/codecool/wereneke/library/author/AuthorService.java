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
    public Author create(Author author) {

            return this.authorRepository.save(author);
    }

    @Override
    public Author update(Integer id, Author newValues) throws NoSuchIdException {

        Author author = this.authorRepository.findOne(id);
        if (author == null) throw new NoSuchIdException();
        author = newValues;
        author.setId(id);
        return this.authorRepository.save(author);
    }

    @Override
    public void delete(Integer id) throws NoSuchIdException {

        if (!this.authorRepository.exists(id)) throw new NoSuchIdException();

        Author author = this.authorRepository.findOne(id);
        this.authorRepository.delete(author);

    }

    @Override
    public Author findOne(Integer id) throws NoSuchIdException {

        Author author = this.authorRepository.findOne(id);
        if (author == null) throw new NoSuchIdException();
        return author;
    }

    @Override
    public Iterable<Author> findAll(boolean arch) {
        return this.authorRepository.findAll();
    }
}
