package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepo extends JpaRepository<Actor,Integer> {

}
