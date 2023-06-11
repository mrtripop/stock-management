package com.learn.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "item")
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String sku;
    private Float mrp;
    private Float discount;
    private Float price;
    @Column(columnDefinition = "smallint")
    private Integer quantity;
    @Column(columnDefinition = "smallint")
    private Integer sold;
    @Column(columnDefinition = "smallint")
    private Integer available;
    @Column(columnDefinition = "smallint")
    private Integer defective;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime createdAt;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private Long supplierId; // not have table for supplier

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_created_by")
    private User createdBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_updated_by")
    private User updatedBy;

}
