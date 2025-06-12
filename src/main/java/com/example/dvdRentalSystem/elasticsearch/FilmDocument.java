package com.example.dvdRentalSystem.elasticsearch;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "film")
@Data
public class FilmDocument {
    @Id
    private int id;
    private String title;

    private String description;

    @Field(type = FieldType.Date, format = DateFormat.year)
    private Integer releaseYear;

    private String rating;

    private String specialFeatures;
}
