package com.learn.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "product_meta")
@NoArgsConstructor
public class ProductMeta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_meta_seq")
    @SequenceGenerator(name = "product_meta_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "varchar(50)")
    private String key;
    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

}
