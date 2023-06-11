package com.learn.model.book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @Column(nullable = false)
    private String category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Collection<Category> children;

    @ManyToMany(mappedBy = "categories")
    private Collection<Book> books;
}
