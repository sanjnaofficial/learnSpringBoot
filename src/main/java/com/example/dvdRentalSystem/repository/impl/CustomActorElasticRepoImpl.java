package com.example.dvdRentalSystem.repository.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.dvdRentalSystem.elasticsearch.ActorDocument;
import com.example.dvdRentalSystem.repository.ActorElasticRepo;
import com.example.dvdRentalSystem.repository.CustomActorElasticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomActorElasticRepoImpl implements CustomActorElasticRepo {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public List<ActorDocument> searchByNameFuzzy(String name) {
        try {
            Query query = Query.of(q -> q
                    .multiMatch(MultiMatchQuery.of(m -> m
                            .query(name)
                            .fields("firstName", "lastName")
                            .fuzziness("AUTO")
                    ))
            );

            SearchResponse<ActorDocument> response = elasticsearchClient.search(s -> s
                            .index("actor")
                            .query(query),
                    ActorDocument.class);

            return response.hits().hits().stream()
                    .map(hit -> hit.source())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
