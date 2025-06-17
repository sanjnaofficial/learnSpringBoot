package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.elasticsearch.ActorDocument;
import com.example.dvdRentalSystem.repository.impl.CustomActorElasticRepoImpl;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ActorElasticRepo extends ElasticsearchRepository<ActorDocument, Integer>, CustomActorElasticRepo {
    List<ActorDocument> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

}
