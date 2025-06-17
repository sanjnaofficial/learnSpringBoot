package com.example.dvdRentalSystem.controller;

import com.example.dvdRentalSystem.model.Actor;
import com.example.dvdRentalSystem.repository.ActorRepo;
import com.example.dvdRentalSystem.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/dvd/actor")
public class ActorController {
    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private SyncService syncService;

    @PostMapping("/add")
    public String addActor(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            Actor actor = new Actor();
            actor.setFirstName(firstName);
            actor.setLastName(lastName);
            actor.setLastUpdate(LocalDateTime.now());
            actor = actorRepo.save(actor);
            syncService.syncSingleActorToElastic(actor);

            return "Actor added and synced successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    @PostMapping("/delete")
    public String deleteActor(@RequestParam int id) {
        try {
            if (!actorRepo.existsById(id)) {
                return "Actor with ID " + id + " does not exist.";
            }
            actorRepo.deleteById(id);
            syncService.deleteActorFromElastic(id);
            return "Actor deleted successfully from both DB and Elasticsearch!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during deletion: " + e.getMessage();
        }
    }

}
