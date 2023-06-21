package com.learn.main.category;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "varchar(75)")
    private String title;
    @Column(columnDefinition = "varchar(100)")
    private String metaTitle;
    @Column(columnDefinition = "varchar(100)")
    private String slug;
    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Collection<Category> children;
}
