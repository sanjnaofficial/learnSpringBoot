package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepo extends JpaRepository<Actor, Integer> {
    Optional<Actor> findByFirstName(String firstName);
}
