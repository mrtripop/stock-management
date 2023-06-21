package com.learn.main.address;

import com.learn.main.order.Order;
import com.learn.main.user.User;
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
@Table(name = "address")
@NoArgsConstructor
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
  @SequenceGenerator(name = "address_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Order order;

  @Column(columnDefinition = "varchar(50)")
  private String firstName;

  @Column(columnDefinition = "varchar(50)")
  private String lastName;

  @Column(columnDefinition = "varchar(50)")
  private String username;

  @Column(columnDefinition = "varchar(15)")
  private String mobile;

  @Column(columnDefinition = "varchar(50)")
  private String email;

  @Column(columnDefinition = "varchar(50)")
  private String line1;

  @Column(columnDefinition = "varchar(50)")
  private String line2;

  @Column(columnDefinition = "varchar(50)")
  private String city;

  @Column(columnDefinition = "varchar(50)")
  private String province;

  @Column(columnDefinition = "varchar(50)")
  private String country;

  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  private ZonedDateTime createdAt;

  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  private ZonedDateTime updatedAt;
}
