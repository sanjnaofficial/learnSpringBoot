package com.example.weatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainEntity {
    private Double temp;
    private Double pressure;
    private Double humidity;
}