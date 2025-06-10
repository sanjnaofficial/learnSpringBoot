package com.example.weatherApp.entity;

import lombok.Data;

@Data
public class WeatherAppResponse {
    Double temperature;
    String city;
    Double pressure;
    Double humidity;
    WindEntity wind;
}
