package com.example.dvdRentalApp.repository;

import com.example.dvdRentalApp.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
