package com.learn.model.Inventory;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "user", indexes = {
        @Index(columnList = "username ASC", name = "uq_username", unique = true),
        @Index(columnList = "mobile ASC", name = "uq_mobile", unique = true),
        @Index(columnList = "email ASC", name = "uq_email", unique = true),
})
public class User implements Serializable {
    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "roleId")
    private Integer roleId; // Admin, Supplier, Salesperson, and Customer

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "registeredAt")
    private Date registeredAt;

    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "intro")
    private String intro;

    @Column(name = "profile")
    private String profile;
}
