package com.learn.main.productMeta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.main.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

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

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Product product;
}
