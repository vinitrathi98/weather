package com.publicissapient.weather.service;

import java.util.List;
import com.publicissapient.weather.model.WeatherForecast;

/**
 * The Interface WeatherService represents the service layer for diff api.
 * 
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */
public interface WeatherService {

    List<WeatherForecast> getCityWeather(String city);

}
