package com.example.weatherApp.controller;

import com.example.weatherApp.entity.WeatherAppResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
@Slf4j
@Validated
public class WeatherForecastController {

    private final RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    public WeatherForecastController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getWeatherForecast")
    public ResponseEntity<?> getWeatherForecast(@RequestParam @NotBlank String city,
                                                                 @RequestParam(defaultValue = "metric") String units) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", units)
                .toUriString();
        log.info("Fetching weather data for city: {} with units: {}", city, units);

        try {
            WeatherAppResponse response = restTemplate.getForObject(url, WeatherAppResponse.class);
            if (response != null) {
                log.info("Received weather data: {}", response);
                return ResponseEntity.ok(response);
            } else {
                log.warn("Weather data not found for city: {}", city);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Weather data not available");
            }
        } catch (HttpClientErrorException e) {
            log.error("Client error while fetching weather data: {}", e.getMessage());
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error fetching weather data: " + e.getStatusText());
        } catch (Exception e) {
            log.error("Unexpected error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error");
        }
    }
}