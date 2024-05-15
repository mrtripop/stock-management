package com.mrtripop.inventory.address.models;

import jakarta.persistence.*;
import java.io.Serializable;
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
    name = "address",
    indexes = {
      @Index(name = "address_created_at", columnList = "created_at"),
      @Index(name = "address_updated_at", columnList = "updated_at"),
    })
@EntityListeners(AuditingEntityListener.class)
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
  @SequenceGenerator(name = "address_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", columnDefinition = "TEXT")
  private String name;

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

  @CreatedDate
  @Column(name = "created_at", columnDefinition = "BIGINT")
  private Long createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", columnDefinition = "BIGINT")
  private Long updatedAt;
}
