package com.learn.main.orderItem;

import com.learn.main.item.Item;
import com.learn.main.product.Product;
import com.learn.main.order.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "order_info_item")
@NoArgsConstructor
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_info_item_seq")
    @SequenceGenerator(name = "order_info_item_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Item item;

    @Column(columnDefinition = "varchar(100)")
    private String sku;
    private Float price;
    private Float discount;
    @Column(columnDefinition = "smallint")
    private Integer quantity;
    @Column(columnDefinition = "text")
    private String content;

    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime createdAt;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime updatedAt;

}
