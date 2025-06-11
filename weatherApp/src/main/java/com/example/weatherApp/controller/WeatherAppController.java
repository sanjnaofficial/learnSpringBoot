package com.example.weatherApp.controller;

import com.example.weatherApp.entity.WeatherAppResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/api")
public class WeatherAppController {
    @Value("${openweather.api.url}")
    private String openWeatherApiUrl;

    @Value("${openweather.api.key}")
    private String openWeatherApiKey;

    private final RestClient restClient;
    public WeatherAppController(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public WeatherAppResponse getWeather(String city) {
        String url= String.format("%s?q=%s&appid=%s&units=metric",openWeatherApiUrl,city, openWeatherApiKey);
        try{
            return restClient.get().uri(url).retrieve().body(WeatherAppResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable(value = "weatherCache",key = "#city")
    @GetMapping("/weather")
    public WeatherAppResponse getWeatherResponse(@RequestParam String city) {
        WeatherAppResponse response = getWeather(city);
        return response;
    }
}
