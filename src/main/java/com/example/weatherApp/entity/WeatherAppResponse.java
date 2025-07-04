package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAppResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("name")
    private String city;

    @JsonProperty("wind")
    private WeatherWind wind;

    private Double temperature;
    private Double pressure;
    private Double humidity;

    @JsonProperty("main")
    private void setMain(JsonNode main) {
        this.temperature = main.get("temp").asDouble();
        this.pressure = main.get("pressure").asDouble();
        this.humidity = main.get("humidity").asDouble();
    }

    @Data
    private static class WeatherWind implements Serializable {
        private static final long serialVersionUID = 1L;
        @JsonProperty("speed")
        private Double speed;
        @JsonProperty("deg")
        private Double angle;
    }

}
