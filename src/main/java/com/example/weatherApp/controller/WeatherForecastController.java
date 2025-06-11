package com.example.weatherApp.controller;

import com.example.weatherApp.entity.WeatherAppResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.cache.annotation.Cacheable;

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
    @CrossOrigin(origins = "http://localhost:63342")
    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherAppResponse getWeatherForecast(@RequestParam @NotBlank String city) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .toUriString();
        log.info("Fetching weather data for city: {}", city);

        try {
            WeatherAppResponse response = restTemplate.getForObject(url, WeatherAppResponse.class);

            String rawJson = restTemplate.getForObject(url, String.class);
            log.info("Raw response: {}", rawJson);

            if (response != null) {
                log.info("Received weather data: {}", response);
                return response;
            } else {
                log.warn("Weather data not found for city: {}", city);
                throw new RuntimeException("Weather data not available");
            }
        } catch (HttpClientErrorException e) {
            log.error("Client error while fetching weather data: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Client error: " + e.getStatusText());
        } catch (HttpServerErrorException e) {
            log.error("Server error while fetching weather data: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Server error: " + e.getStatusText());
        } catch (Exception e) {
            log.error("Unknown error occurred: ", e);
            throw new RuntimeException("Internal server error", e);
        }
    }
}