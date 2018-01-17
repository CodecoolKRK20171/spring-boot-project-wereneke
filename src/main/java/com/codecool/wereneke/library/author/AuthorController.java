package com.codecool.wereneke.library.author;

import com.codecool.wereneke.library.common.NoSuchIdException;
import com.codecool.wereneke.library.common.Service;
import com.codecool.wereneke.library.common.Controller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/authors")
public class AuthorController implements Controller<Author> {

    private Service authorService;
    public AuthorController(@Qualifier("authorService") Service authorService) {
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
        return (Author) this.authorService.findOne(id);
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
        return (Author) this.authorService.update(id, author);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) throws NoSuchIdException {
        this.authorService.delete(id);
    }
}
