package com.koncsikn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koncsikn.dto.ErrorResponse;
import com.koncsikn.dto.WeatherResponse;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class OpenWeatherServiceImpl implements WeatherService {

    @Value("${open-weather.api-key}")
    private String apiKey;

    @Value("${open-weather.base-url}")
    private String baseUrl;

    @Override
    public WeatherResponse getCurrentWeatherInCity(String city) {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet getWeatherRequest = new HttpGet(createWeatherQuery(city));
            HttpResponse getWeatherResponse = httpClient.execute(getWeatherRequest);
            String json = EntityUtils.toString(getWeatherResponse.getEntity());
            if (getWeatherResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return new ObjectMapper().readValue(json, WeatherResponse.class);
            } else {
                ErrorResponse e = new ObjectMapper().readValue(json, ErrorResponse.class);
                throw new RuntimeException(e.toString());
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Could not get weather data");
        }
    }

    private URI createWeatherQuery(String city) throws URISyntaxException {
        return new URIBuilder(baseUrl)
                .setParameter("q", city)
                .setParameter("units", "metric")
                .setParameter("APPID", apiKey)
                .build();
    }
}
