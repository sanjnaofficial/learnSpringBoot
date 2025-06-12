package com.example.dvdRentalSystem.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Data
@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name = "language_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}