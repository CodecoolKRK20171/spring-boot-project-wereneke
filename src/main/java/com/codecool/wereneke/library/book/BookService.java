package com.codecool.wereneke.library.book;

import com.codecool.wereneke.library.common.NoSuchIdException;
import com.codecool.wereneke.library.common.Service;
import org.springframework.stereotype.Component;

@Component
public class BookService implements Service<Book> {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void update(Book book) {

        this.bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        this.bookRepository.delete(book.getId());
    }

    @Override
    public Book findOne(Integer id) throws NoSuchIdException {
        Book book = this.bookRepository.findOne(id);
        if (book == null) throw new NoSuchIdException();
        return book;
    }

    @Override
    public Iterable<Book> findAll() {
        return this.bookRepository.findAll();
    }
}
