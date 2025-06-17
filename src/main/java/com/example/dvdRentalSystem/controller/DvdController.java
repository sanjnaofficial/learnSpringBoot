package com.example.dvdRentalSystem.controller;

import com.example.dvdRentalSystem.elasticsearch.ActorDocument;
import com.example.dvdRentalSystem.elasticsearch.FilmDocument;
import com.example.dvdRentalSystem.repository.ActorElasticRepo;
import com.example.dvdRentalSystem.repository.FilmElasticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dvd/search")
public class DvdController {
    @Autowired
    private ActorElasticRepo actorElasticRepo;
    @Autowired
    private FilmElasticRepo filmElasticRepo;

    @GetMapping("/actors")
    public List<ActorDocument> getActors(@RequestParam String name) {
        return actorElasticRepo.findByFirstNameContainingOrLastNameContaining(name, name);
    }

    @GetMapping("/films")
    public List<FilmDocument> getFilms(@RequestParam String title) {
        return filmElasticRepo.findByTitleContaining(title);
    }

    @GetMapping("/search/fuzzy")
    public List<ActorDocument> searchFuzzy(@RequestParam String name) {
        return actorElasticRepo.searchByNameFuzzy(name);
    }



}
