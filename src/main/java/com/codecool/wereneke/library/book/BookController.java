package com.codecool.wereneke.library.book;

import com.codecool.wereneke.library.common.Controller;
import com.codecool.wereneke.library.common.NoSuchIdException;
import com.codecool.wereneke.library.common.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class BookController implements Controller<Book> {

    private Service bookService;

    public BookController(@Qualifier("bookService") BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping(path = "")
    public Iterable<Book> index() throws NoSuchIdException {
        return this.bookService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Book show(@PathVariable Integer id) throws NoSuchIdException{
        return (Book) this.bookService.findOne(id);
    }

    @PostMapping(path = "")
    public Book create(@RequestBody Book book) {
        this.bookService.create(book);
        return book;
    }
}
