package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAppResponse implements Serializable {
    @JsonProperty("name")
    private String city;

    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("humidity")
    private Double humidity;

    @JsonProperty("main")
    private void setMain(JsonNode main) {
        this.temperature = main.get("temp").asDouble();
        this.pressure = main.get("pressure").asDouble();
        this.humidity = main.get("humidity").asDouble();
    }

    @JsonProperty("wind")
    private WindEntity wind;

}
