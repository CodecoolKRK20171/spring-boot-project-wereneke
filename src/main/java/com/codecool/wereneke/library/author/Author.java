package com.codecool.wereneke.library.author;

import com.codecool.wereneke.library.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ta strategia powoduje, ze generacja jest oddelegowana do bazy danych
    private Integer id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "author", orphanRemoval = true)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("author")
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
