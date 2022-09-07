package com.publicissapient.weather.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.weather.exception.ResourceNotFoundException;
import com.publicissapient.weather.model.WeatherForecast;
import com.publicissapient.weather.service.WeatherService;
import com.publicissapient.weather.util.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * The Class WeatherController.
 * 
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */
@RestController()
@RequestMapping("/v1/weather")
public class WeatherController {

    /** The logger. */
    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    /**
     * end-point to get weather forecast of a city for next three days.
     * 
     * This end-point retrieves the weather forecast by {city} from OpenWeatherMap
     * Api and then checks for city temperature and weather condition.
     *
     * @param city the city for which weather forecast is needed.
     * @return the list of WeatherForecast
     * @see com.publicissapient.weather.model.WeatherForecast
     */
    
    @Operation(summary = "Get the 3 days weather forecast by the city")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "showing the successful 3 days weather forecast response", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = WeatherForecast.class)) }) })
    @GetMapping("/{city}")
    public ResponseEntity<List<WeatherForecast>> getWeatherByCity
                                    (@PathVariable String city) {
        LOG.trace("Entering getWeatherByCity(city={})", city);

        if (city == null || city.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
        	return new ResponseEntity<List<WeatherForecast>>(
                    weatherService.getCityWeather(city), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(Constants.WEATHER_NOT_FOUND + city);
        }
    }

}
