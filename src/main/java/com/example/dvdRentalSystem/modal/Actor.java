package com.example.dvdRentalSystem.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="actor")
@Data
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id",columnDefinition = "smallint unsigned")
    private int id;

    @Column(name="first_name",nullable = false, length = 45)
    private String firstName;

    @Column(name="last_name",nullable = false, length = 45)
    private String lastName;

    @Column(name="last_update",nullable = false)
    private LocalDateTime lastUpdate;
}
