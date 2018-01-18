package com.codecool.wereneke.library.book;

import com.codecool.wereneke.library.common.LoggerService;
import com.codecool.wereneke.library.common.NoSuchIdException;
import com.codecool.wereneke.library.common.EntityService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookEntityService implements EntityService<Book> {

    private BookRepository bookRepository;
    private LoggerService loggerService;

    public BookEntityService(BookRepository bookRepository, LoggerService loggerService) {
        this.bookRepository = bookRepository;
        this.loggerService = loggerService;
    }

    @Override
    public Book create(Book book) {

        loggerService.logInfo("Book was created");
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Integer id, Book newValues) throws NoSuchIdException{

        Book book = this.bookRepository.findOne(id);
        if (book.getArchived()) throw new NoSuchIdException();
        book = newValues;
        book.setId(id);
        loggerService.logInfo(String.format("Book %d was updated", id));
        return this.bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) throws NoSuchIdException {
        if (!this.bookRepository.exists(id)) throw new NoSuchIdException();
        if (this.bookRepository.findOne(id).getArchived()) throw new NoSuchIdException();
        loggerService.logInfo(String.format("Book %d was deleted", id));
        this.bookRepository.delete(id);
    }

    @Override
    public Book findOne(Integer id) throws NoSuchIdException {
        Book book = this.bookRepository.findOne(id);
        if (book == null) throw new NoSuchIdException();
        if (book.getArchived()) throw new NoSuchIdException();
        loggerService.logInfo(String.format("Book %d was shown", id));
        return book;
    }

    @Override
    public Iterable<Book> findAll(boolean archived) {

        Iterable<Book> allBooks = this.bookRepository.findAll();
        Set<Book> archivedBooks = new HashSet<>();
        Set<Book> unarchivedBooks = new HashSet<>();

        for (Book book: allBooks) {
            if (book.getArchived()){
                archivedBooks.add(book);
            }
            else unarchivedBooks.add(book);
        }

        loggerService.logInfo(String.format("archived : %s books were shown", archived));
        if (archived) return archivedBooks;
        return unarchivedBooks;
    }

    public Book archive(Integer id) throws NoSuchIdException {

        Book book = this.bookRepository.findOne(id);
        if (book == null) throw new NoSuchIdException();
        book.setArchived(true);
        this.bookRepository.save(book);
        loggerService.logInfo(String.format("Book %d was archived", id));
        return book;
    }

}
