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
@Table(name = "transaction")
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", allocationSize = 1)
    private Long id;

    private Long userId;
    private Long orderId;

    @Column(columnDefinition = "varchar(100)")
    private String code;
    @Column(columnDefinition = "smallint")
    private Integer type;
    @Column(columnDefinition = "smallint")
    private Integer mode;
    @Column(columnDefinition = "smallint")
    private Integer status;
    @Column(columnDefinition = "text")
    private String content;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime createdAt;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime updatedAt;
}
