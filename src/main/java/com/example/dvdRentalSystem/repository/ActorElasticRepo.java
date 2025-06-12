package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.elasticsearch.ActorDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ActorElasticRepo extends ElasticsearchRepository<ActorDocument, Integer> {
    List<ActorDocument> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
