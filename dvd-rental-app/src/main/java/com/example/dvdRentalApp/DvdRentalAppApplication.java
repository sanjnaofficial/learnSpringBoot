package com.example.dvdRentalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.dvdRentalApp.repository")
@EntityScan("com.example.dvdRentalApp.entity")
public class DvdRentalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DvdRentalAppApplication.class, args);
    }

}
