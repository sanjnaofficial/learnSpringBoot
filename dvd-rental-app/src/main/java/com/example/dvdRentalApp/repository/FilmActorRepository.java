package com.example.dvdRentalApp.repository;

import com.example.dvdRentalApp.entity.FilmActor;
import com.example.dvdRentalApp.entity.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
}