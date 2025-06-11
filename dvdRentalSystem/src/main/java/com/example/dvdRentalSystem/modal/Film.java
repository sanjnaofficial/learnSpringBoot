package com.example.dvdRentalSystem.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;

@Entity
@Table(name="film")
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id",columnDefinition = "smallint unsigned")
    private int id;

    @Column(name="title",nullable = false,length = 128)
    private String title;

    @Column(name="description",columnDefinition = "text")
    private String description;

    @Column(name="release_year",columnDefinition = "year")
    private Year releaseYear;

    @Column(name = "language_id", nullable = false)
    private Integer languageId;

    @Column(name = "original_language_id")
    private Integer originalLanguageId;

    @Column(name = "rental_duration", nullable = false)
    private Integer rentalDuration;

    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    private Integer length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @Convert(converter = Rating.RatingConverter.class)
    @Column(columnDefinition = "ENUM('G','PG','PG-13','R','NC-17')")
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public enum Rating {
        G, PG,
        @JsonProperty("PG-13") PG_13("PG-13"),
        R,
        @JsonProperty("NC-17") NC_17("NC-17");

        private final String dbValue;

        Rating() {
            this.dbValue = name();
        }

        Rating(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }

        @Converter
        public static class RatingConverter implements AttributeConverter<Rating, String> {
            @Override
            public String convertToDatabaseColumn(Rating attribute) {
                return attribute != null ? attribute.getDbValue() : null;
            }

            @Override
            public Rating convertToEntityAttribute(String dbData) {
                if (dbData == null) return null;

                return Arrays.stream(Rating.values())
                        .filter(r -> r.getDbValue().equals(dbData))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Unknown rating: " + dbData));
            }
        }
    }
}
