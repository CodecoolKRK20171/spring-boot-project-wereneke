package com.codecool.wereneke.library.book;

import com.codecool.wereneke.library.common.Controller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class BookController implements Controller<Book> {

    private BookService bookService;

    public BookController(@Qualifier("bookService") BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping(path = "")
    public Iterable<Book> index() {
        return this.bookService.findAll();
    }

    @PostMapping(path = "")
    public Book create(@RequestBody Book book) {
        return null;
    }
}
