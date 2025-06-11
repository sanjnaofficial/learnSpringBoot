package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonProperty("speed")
    private Double speed;
    @JsonProperty("deg")
    private Double angle;
}
