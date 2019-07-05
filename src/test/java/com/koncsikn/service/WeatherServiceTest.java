package com.koncsikn.service;

import com.koncsikn.configuration.AppConfig;
import com.koncsikn.dto.WeatherParameters;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Slf4j
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void getWeatherValidCity() {
        String city = "Budapest";

        try {
            WeatherParameters weatherParameters = weatherService.getCurrentWeatherInCity(city).getWeatherParameters();

            log.info("The current temperature in {} is {}°C, the min temperature is {}°C," +
                            " the max temperature is {}°C, the pressure is {}hPa, the humidity is {}%",
                    city,
                    weatherParameters.getTemp(),
                    weatherParameters.getTempMin(),
                    weatherParameters.getTempMax(),
                    weatherParameters.getPressure(),
                    weatherParameters.getHumidity()
            );
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void getWeatherInvalidCity() {
        String city = "blablabla";

        try {
            weatherService.getCurrentWeatherInCity(city).getWeatherParameters();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

}
