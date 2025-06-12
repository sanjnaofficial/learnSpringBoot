package com.example.dvdRentalApp.repository;

import com.example.dvdRentalApp.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}