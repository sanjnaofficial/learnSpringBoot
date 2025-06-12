package com.example.dvdRentalSystem.service;

import com.example.dvdRentalSystem.elasticsearch.ActorDocument;
import com.example.dvdRentalSystem.elasticsearch.FilmDocument;
import com.example.dvdRentalSystem.modal.Actor;
import com.example.dvdRentalSystem.modal.Film;
import com.example.dvdRentalSystem.repository.ActorElasticRepo;
import com.example.dvdRentalSystem.repository.ActorRepo;
import com.example.dvdRentalSystem.repository.FilmElasticRepo;
import com.example.dvdRentalSystem.repository.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyncService {
    @Autowired private ActorRepo actorRepo;
    @Autowired private FilmRepo filmRepo;
    @Autowired private ActorElasticRepo actorElasticRepo;
    @Autowired private FilmElasticRepo filmElasticRepo;

    public void syncActorsToElastic(){
        List<Actor> actors = actorRepo.findAll();
        List<ActorDocument> docs = actors.stream().map(actor -> {
            ActorDocument actorDocument = new ActorDocument();
            actorDocument.setId(actor.getId());
            actorDocument.setFirstName(actor.getFirstName());
            actorDocument.setLastName(actor.getLastName());
            return actorDocument;
        }).collect(Collectors.toList());
        actorElasticRepo.saveAll(docs);
    }

    public void syncFilmsToElastic(){
        List<Film> films = filmRepo.findAll();
        List<FilmDocument> docs = films.stream().map(film -> {
            FilmDocument filmDocument = new FilmDocument();
            filmDocument.setId(film.getId());
            filmDocument.setTitle(film.getTitle());
            filmDocument.setDescription(film.getDescription());
            filmDocument.setReleaseYear(film.getReleaseYear()!=null?film.getReleaseYear().getValue():null);
            filmDocument.setRating(film.getRating()!=null?film.getRating().toString():null);
            filmDocument.setSpecialFeatures(film.getSpecialFeatures());
            return filmDocument;
        }).collect(Collectors.toList());
        filmElasticRepo.saveAll(docs);
    }
}
