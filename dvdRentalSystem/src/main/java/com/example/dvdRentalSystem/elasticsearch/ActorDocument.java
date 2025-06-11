package com.example.dvdRentalSystem.elasticsearch;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "actor")
@Data
public class ActorDocument {
    @Id
    private int id;
    private String firstName;
    private String lastName;
}
