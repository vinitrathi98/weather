package com.publicissapient.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The Class WeatherApplication.
 * 
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */


@SpringBootApplication
@EnableFeignClients(basePackages = { "com.publicissapient.weather.client" })
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
