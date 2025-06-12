package com.example.dvdRentalApp.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.dvdRentalApp.document.FilmActorDocument;
import com.example.dvdRentalApp.entity.FilmActor;
import com.example.dvdRentalApp.repository.FilmActorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataIndexer {

    private final FilmActorRepository filmActorRepo;
    private final ElasticsearchClient esClient;

    public DataIndexer(FilmActorRepository filmActorRepo, ElasticsearchClient esClient) {
        this.filmActorRepo = filmActorRepo;
        this.esClient = esClient;
    }

    public void indexAllFilmActors() {
        List<FilmActor> filmActors = filmActorRepo.findAll();

        for (FilmActor fa : filmActors) {
            FilmActorDocument doc = new FilmActorDocument();
            doc.setId(fa.getId().getFilmId() + "-" + fa.getId().getActorId());
            doc.setFilmId(fa.getFilm().getFilmId());
            doc.setFilmTitle(fa.getFilm().getTitle());
            doc.setFilmDescription(fa.getFilm().getDescription());
            doc.setActorId(fa.getActor().getActorId());
            doc.setActorName(fa.getActor().getFirstName() + " " + fa.getActor().getLastName());

            try {
                esClient.index(i -> i
                        .index("sakila_film_actor")
                        .id(doc.getId())
                        .document(doc)
                );
            } catch (IOException e) {
                throw new RuntimeException("Failed to index document", e);
            }
        }
    }
}
