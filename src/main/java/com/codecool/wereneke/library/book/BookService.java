package com.codecool.wereneke.library.book;

import com.codecool.wereneke.library.common.Service;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService implements Service<Book> {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(Book obj) {

    }

    @Override
    public void update(Book obj) {

    }

    @Override
    public void delete(Book obj) {

    }

    @Override
    public Book findOne(int id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }
}
