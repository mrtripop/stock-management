package com.learn.model.book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "photo")
@NoArgsConstructor
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long photoId;
    private String urlSmall;
    private String urlMedium;
    private String urlLarge;

    @OneToOne(mappedBy = "photo")
    private Book book;
}
