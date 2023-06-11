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
@Table(name = "user_info")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info_seq")
    @SequenceGenerator(name = "user_info_seq", allocationSize = 1)
    private Long id;

    private Long roleId;

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
    @Column(columnDefinition = "varchar(32)")
    private String passwordHash;
    @Column(columnDefinition = "text")
    private String intro;
    @Column(columnDefinition = "text")
    private String profile;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime registeredAt;
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime lastLogin;
}
