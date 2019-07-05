package com.koncsikn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherParameters {
    private String temp;
    private String pressure;
    private String humidity;
    @JsonProperty("temp_min")
    private String tempMin;
    @JsonProperty("temp_max")
    private String tempMax;
}
