package com.learn.model.book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "author_id")
    private Long authorId;
    private String firstName;
    private String lastName;
    private Date birthDate;

    @ManyToMany(mappedBy = "authors")
    private Collection<Book> books;
}
