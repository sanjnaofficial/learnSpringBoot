package com.example.dvdRentalApp.document;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "sakila_film_actor")
public class FilmActorDocument {

    @Id
    private String id;

    private Integer filmId;
    private String filmTitle;
    private String filmDescription;

    private Integer actorId;
    private String actorName;
}
