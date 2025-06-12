package com.example.dvdRentalApp.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.dvdRentalApp.document.FilmActorDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class FilmActorSearchController {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @GetMapping
    public List<FilmActorDocument> search(@RequestParam String keyword) {
        try {
            SearchResponse<FilmActorDocument> response = elasticsearchClient.search(s -> s
                            .index("sakila_film_actor")
                            .query(q -> q
                                    .multiMatch(m -> m
                                            .query(keyword)
                                            .fields("filmTitle^2", "filmDescription", "actorName^1.5")
                                            .fuzziness("AUTO")
                                    )
                            ),
                    FilmActorDocument.class
            );

            return response.hits().hits()
                    .stream()
                    .map(hit -> hit.source())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Failed to search", e);
        }
    }

}

