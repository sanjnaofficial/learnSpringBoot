package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAppResponse {

    @JsonProperty("name")
    private String city;

    private Double temperature;
    private Double pressure;
    private Double humidity;

    @JsonIgnore
    private MainEntity main;

    private WindEntity wind;

    @JsonProperty("main")
    public void unpackMain(MainEntity main) {
        this.main = main;
        if (main != null) {
            this.temperature = main.getTemp();
            this.pressure = main.getPressure();
            this.humidity = main.getHumidity();
        }
    }

}
