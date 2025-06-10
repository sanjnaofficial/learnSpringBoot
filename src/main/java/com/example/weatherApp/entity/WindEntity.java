package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindEntity {
    @JsonProperty("speed")
    private Double speed;
    @JsonProperty("deg")
    private Double angle;
}
