package com.example.weatherApp.controller;

import com.example.weatherApp.entity.WeatherAppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
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
    public ResponseEntity<WeatherAppResponse> getWeatherForecast(@RequestParam String city) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .toUriString();
        try {
            WeatherAppResponse response = restTemplate.getForObject(url, WeatherAppResponse.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}