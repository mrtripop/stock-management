package com.mrtripop.inventory.product.models.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "product",
    indexes = {
      @Index(name = "product_code", columnList = "code"),
      @Index(name = "product_created_at", columnList = "created_at"),
      @Index(name = "product_updated_at", columnList = "updated_at"),
    })
@EntityListeners(AuditingEntityListener.class)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
  @SequenceGenerator(name = "product_seq", allocationSize = 1)
  private Long id;

  /**
   * ProductCode: Besides the ProductID, products are usually identified by an internal code (also
   * called an SKU or Stock Keeping Unit). This code consists of letters and numbers that identify
   * characteristics about each product, such as manufacturer, brand, style, color, and size.
   */
  @Column(name = "code", columnDefinition = "TEXT", nullable = false)
  private String code;

  /**
   * Barcode: This external product code (also known as the UPC or Universal Product Code) is
   * standardized for universal use by any company.
   */
  @Column(name = "barcode", columnDefinition = "TEXT")
  private String barcode;

  /** ProductName: The product's name. */
  @Column(name = "name", columnDefinition = "TEXT")
  private String name;

  /** ProductDescription: A more detailed description of the product. */
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  /** ProductCategory: The product’s category and should be normalized. */
  @Column(name = "category", columnDefinition = "TEXT")
  private String category;

  /**
   * ReorderQuantity: Some products cannot be ordered by units. You need to purchase them in
   * packages.
   */
  @Column(name = "reorder_quantity", columnDefinition = "SMALLINT")
  private Integer reorderQuantity;

  /**
   * PackedWeight: Product’s weight, including packaging. This may be required to define storage
   * location.
   */
  @Column(name = "packed_weight", columnDefinition = "DECIMAL")
  private Double packedWeight;

  /**
   * PackedWeight: Product’s height, including packaging. This may be required to define storage
   * location.
   */
  @Column(name = "packed_height", columnDefinition = "DECIMAL")
  private Double packedHeight;

  /**
   * PackedWeight: Product’s width, including packaging. This may be required to define storage
   * location.
   */
  @Column(name = "packed_width", columnDefinition = "DECIMAL")
  private Double packedWidth;

  /**
   * PackedWeight: Product’s depth, including packaging. This may be required to define storage
   * location.
   */
  @Column(name = "packed_depth", columnDefinition = "DECIMAL")
  private Double packedDepth;

  /** IsActive: Is status of the product is selling or not */
  @Column(name = "is_active", columnDefinition = "BOOLEAN")
  private Boolean isActive;

  /** CreatedAt: Time that product is created */
  @CreatedDate
  @Column(name = "created_at", columnDefinition = "BIGINT")
  private Long createdAt;

  /** UpdatedAt: Time that product is updated */
  @LastModifiedDate
  @Column(name = "updated_at", columnDefinition = "BIGINT")
  private Long updatedAt;
}
