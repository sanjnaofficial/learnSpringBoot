package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.elasticsearch.FilmDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface FilmElasticRepo extends ElasticsearchRepository<FilmDocument, Integer> {
    List<FilmDocument> findByTitleContaining(String title);
}
