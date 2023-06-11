package com.learn.model.book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bookId;
    @Column(nullable = false)
    private String title;
    private Long isbn;
    private Integer totalPages;
    private Double rating;
    private Date publishedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Collection<Author> authors;
}
