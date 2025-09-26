package com.example.dvdRentalSystem.config;

import com.example.dvdRentalSystem.model.Role;
import com.example.dvdRentalSystem.repository.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepo roleRepo;

    public DataSeeder(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepo.count() == 0) {
            Role admin = new Role();
            admin.setName("ROLE_ADMIN");

            Role user = new Role();
            user.setName("ROLE_USER");

            roleRepo.save(admin);
            roleRepo.save(user);

            System.out.println("Roles seeded successfully!");
        }
    }
}
