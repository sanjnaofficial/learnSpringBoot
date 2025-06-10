package com.example.weatherApp.controller;

import com.example.weatherApp.entity.WeatherAppResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class WeatherAppController {
    @Value("${openweather.api.url}")
    private String openWeatherApiUrl;

    @Value("${openweather.api.key}")
    private String openWeatherApiKey;

    private final RestTemplate restTemplate ;
    public WeatherAppController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherAppResponse> getWeather(@RequestParam String city) {
        String url= String.format("%s?q=%s&appid=%s&units=metric",openWeatherApiUrl,city, openWeatherApiKey);
        try{
            WeatherAppResponse response = restTemplate.getForObject(url, WeatherAppResponse.class);
            return ResponseEntity.ok().body(response);
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
}
