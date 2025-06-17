package com.example.dvdRentalSystem.repository;

import com.example.dvdRentalSystem.elasticsearch.ActorDocument;

import java.util.List;

public interface CustomActorElasticRepo {
    List<ActorDocument> searchByNameFuzzy(String name);
}
