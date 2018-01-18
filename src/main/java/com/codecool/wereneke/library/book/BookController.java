package com.codecool.wereneke.library.book;

import com.codecool.wereneke.library.common.Controller;
import com.codecool.wereneke.library.common.NoSuchIdException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class BookController implements Controller<Book> {

    private BookEntityService bookService;

    public BookController(BookEntityService bookService) {
        this.bookService = bookService;
    }

    //method returns unarchived books
    @Override
    @GetMapping(path = "")
    public Iterable<Book> index() {
        return this.bookService.findAll(false);
    }

    @GetMapping(path = "/archived")
    public Iterable<Book> archivedIndex() {
        return this.bookService.findAll(true);
    }

    @Override
    @GetMapping(path = "/{id}")
    public Book show(@PathVariable Integer id) throws NoSuchIdException{
        return this.bookService.findOne(id);
    }

    @Override
    @PostMapping(path = "")
    public Book create(@RequestBody Book book) {
        return this.bookService.create(book);
    }

    @Override
    @PutMapping(path = "/{id}")
    public Book update(@PathVariable Integer id, @RequestBody Book book) throws NoSuchIdException {
        return this.bookService.update(id, book);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) throws NoSuchIdException {
        this.bookService.delete(id);
    }

    @PatchMapping(path = "/{id}")
    public Book archive(@PathVariable Integer id) throws NoSuchIdException {
        return this.bookService.archive(id);
    }
}
