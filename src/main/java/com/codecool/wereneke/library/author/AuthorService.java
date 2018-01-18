package com.codecool.wereneke.library.author;

import com.codecool.wereneke.library.book.Book;
import com.codecool.wereneke.library.common.EntityService;
import com.codecool.wereneke.library.common.LoggerService;
import com.codecool.wereneke.library.common.NoSuchIdException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorService implements EntityService<Author> {

    private AuthorRepository authorRepository;
    private LoggerService loggerService;

    public AuthorService(AuthorRepository authorRepository, LoggerService loggerService) {
        this.authorRepository = authorRepository;
        this.loggerService = loggerService;
    }

    @Override
    public Author create(Author author) {

        loggerService.logInfo("Author was created");
            return this.authorRepository.save(author);
    }

    @Override
    public Author update(Integer id, Author newValues) throws NoSuchIdException {

        Author author = this.authorRepository.findOne(id);
        if (author == null) throw new NoSuchIdException();
        author = newValues;
        author.setId(id);
        loggerService.logInfo(String.format("Author %d was updated", id));
        return this.authorRepository.save(author);
    }

    @Override
    public void delete(Integer id) throws NoSuchIdException {

        if (!this.authorRepository.exists(id)) throw new NoSuchIdException();

        Author author = this.authorRepository.findOne(id);
        loggerService.logInfo(String.format("Author %d was deleted", id));
        this.authorRepository.delete(author);

    }

    @Override
    public Author findOne(Integer id) throws NoSuchIdException {

        Author author = this.authorRepository.findOne(id);
        if (author == null) throw new NoSuchIdException();
        loggerService.logInfo(String.format("Author %d were shown", id));
        return author;
    }

    @Override
    public Iterable<Author> findAll(boolean arch) {
        loggerService.logInfo("Author's were shown");
        return this.authorRepository.findAll();
    }

    public Set<Book> showBooks(Integer id) throws NoSuchIdException {

        if (!this.authorRepository.exists(id)) throw new NoSuchIdException();
        Author author = this.authorRepository.findOne(id);
        Set<Book> books = author.getBooks();
        Set<Book> unarchived = new HashSet<>();
        for (Book book: books) {
            if (!book.getArchived()) unarchived.add(book);
        }
        loggerService.logInfo(String.format("Books of author's %d shown", id));
        return unarchived;
    }
}
