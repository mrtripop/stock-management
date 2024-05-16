package com.mrtripop.inventory.location.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "addresses",
    indexes = {
      @Index(name = "addresses_created_at", columnList = "created_at"),
      @Index(name = "addresses_updated_at", columnList = "updated_at"),
    })
@EntityListeners(AuditingEntityListener.class)
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_id_seq")
  @SequenceGenerator(name = "addresses_id_seq", allocationSize = 1)
  @Column(name = "address_id", columnDefinition = "BIGINT")
  private Long addressId;

  @Column(name = "address_name", columnDefinition = "TEXT")
  private String addressName;

  @Column(name = "line1", columnDefinition = "TEXT")
  private String line1;

  @Column(name = "line2", columnDefinition = "TEXT")
  private String line2;

  @Column(name = "city", columnDefinition = "TEXT")
  private String city;

  @Column(name = "province", columnDefinition = "TEXT")
  private String province;

  @Column(name = "country", columnDefinition = "TEXT")
  private String country;

  @Column(name = "postal_code", columnDefinition = "TEXT")
  private String postalCode;

  @OneToMany(mappedBy = "address")
  private List<Warehouse> warehouses;

  @CreatedDate
  @Column(name = "created_at", columnDefinition = "BIGINT")
  private Long createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", columnDefinition = "BIGINT")
  private Long updatedAt;
}
