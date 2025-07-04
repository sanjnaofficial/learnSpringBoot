package com.example.dvdRentalSystem.modal;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Data
@Entity
@Table(name = "film_category")
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryId id;

    @MapsId("filmId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}