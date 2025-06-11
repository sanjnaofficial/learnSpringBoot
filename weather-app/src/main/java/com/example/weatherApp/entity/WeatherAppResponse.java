package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAppResponse implements Serializable {
    private static final long serialVersionUID = 1L;

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
        if (main != null) {
            this.temperature = main.has("temp") ? main.get("temp").asDouble() : null;
            this.pressure = main.has("pressure") ? main.get("pressure").asDouble() : null;
            this.humidity = main.has("humidity") ? main.get("humidity").asDouble() : null;
        }
    }

    @JsonProperty("wind")
    private WindEntity wind;

}
