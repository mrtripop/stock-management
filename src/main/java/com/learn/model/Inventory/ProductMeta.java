package com.learn.model.Inventory;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product_meta", indexes = {
        @Index(columnList = "productId ASC", name = "idx_meta_product"),
        @Index(columnList = "productId ASC, key ASC", name = "uq_product_meta", unique = true),

}, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"productId"})
})
public class ProductMeta implements Serializable {
    @Id
    @SequenceGenerator(
            name = "product_meta_id_sequence",
            sequenceName = "product_meta_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_meta_id_sequence"
    )
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product productId;

    @Column(name = "key")
    private String key;

    @Column(name = "content")
    private String content;
}
