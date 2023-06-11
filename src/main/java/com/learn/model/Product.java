package com.learn.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "smallint")
    private Integer type;
    @Column(columnDefinition = "varchar(75)")
    private String title;
    @Column(columnDefinition = "text")
    private String summary;
    @Column(columnDefinition = "text")
    private String content;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime createdAt;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<Category> categories;
}
