package com.codecool.wereneke.library.author;

import com.codecool.wereneke.library.book.Book;
import com.codecool.wereneke.library.common.NoSuchIdException;
import com.codecool.wereneke.library.common.Controller;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@RestController
@RequestMapping("/authors")
public class AuthorController implements Controller<Author> {

    private AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping("")
    public Iterable<Author> index() {
        return this.authorService.findAll(false);
    }

    @Override
    @GetMapping("/{id}")
    public Author show(@PathVariable Integer id) throws NoSuchIdException {
        return this.authorService.findOne(id);
    }

    @Override
    @PostMapping("")
    public Author create(@RequestBody Author author) {
        this.authorService.create(author);
        return author;
    }

    @Override
    @PutMapping(path = "/{id}")
    public Author update(@PathVariable Integer id, @RequestBody Author author) throws NoSuchIdException {
        return this.authorService.update(id, author);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) throws NoSuchIdException {
        this.authorService.delete(id);
    }

    @GetMapping("/{id}/books")
    public Set<Book> showBooks(@PathVariable Integer id) throws NoSuchIdException {
        return this.authorService.showBooks(id);
    }
}
