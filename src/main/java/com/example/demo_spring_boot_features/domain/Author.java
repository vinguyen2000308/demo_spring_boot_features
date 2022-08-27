package com.example.demo_spring_boot_features.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "author")
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;
    private String name;
    private String genre;
    private int age;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author", orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Author addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
        return this;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", age=" + age +
                '}';
    }

    public Author removeBook(Book book) {
        book.setAuthor(null);
        this.books.remove(book);
        return this;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public Author setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public Author setAge(int age) {
        this.age = age;
        return this;
    }

    public Author setBooks(List<Book> books) {
        this.books = books;
        return this;
    }
}