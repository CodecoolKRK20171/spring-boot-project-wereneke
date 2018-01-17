package com.codecool.wereneke.library.book;

import com.codecool.wereneke.library.common.NoSuchIdException;
import com.codecool.wereneke.library.common.Service;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookService implements Service<Book> {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {

        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Integer id, Book newValues) throws NoSuchIdException{

        Book book = this.bookRepository.findOne(id);
        if (book.getArchived()) throw new NoSuchIdException();
        book = newValues;
        book.setId(id);
        return this.bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) throws NoSuchIdException {
        if (!this.bookRepository.exists(id)) throw new NoSuchIdException();
        if (this.bookRepository.findOne(id).getArchived()) throw new NoSuchIdException();
        this.bookRepository.delete(id);
    }

    @Override
    public Book findOne(Integer id) throws NoSuchIdException {
        Book book = this.bookRepository.findOne(id);
        if (book == null) throw new NoSuchIdException();
        if (book.getArchived()) throw new NoSuchIdException();
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

        if (archived) return archivedBooks;
        return unarchivedBooks;
    }

    public Book archive(Integer id) throws NoSuchIdException {

        Book book = this.bookRepository.findOne(id);
        if (book == null) throw new NoSuchIdException();
        book.setArchived(true);
        this.bookRepository.save(book);
        return book;
    }

}
