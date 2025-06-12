package com.example.dvdRentalSystem.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

}