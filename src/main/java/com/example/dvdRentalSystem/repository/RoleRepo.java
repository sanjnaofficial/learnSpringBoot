package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name); // Find a role by its name, e.g., "ROLE_ADMIN"
}
