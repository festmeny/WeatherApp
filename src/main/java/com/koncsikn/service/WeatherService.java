package com.koncsikn.service;

import com.koncsikn.dto.WeatherResponse;

public interface WeatherService {

    WeatherResponse getCurrentWeatherInCity(String city);

}
