package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.modal.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo extends JpaRepository<Film,Integer> {
}
