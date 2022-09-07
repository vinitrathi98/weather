package com.publicissapient.weather;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.web.context.WebApplicationContext;

import com.publicissapient.weather.config.JwtTokenUtil;

import com.publicissapient.weather.controller.JwtAuthenticationController;
import com.publicissapient.weather.model.JwtRequest;

/**
 * The Class WeatherControllerIntegrationTest.
 * 
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerIntegrationTest {

	/** The mvc. */
	@Autowired
	private MockMvc mvc;

	/** The web application context. */
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	/**
	 * Setup.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setup() throws Exception {
		this.mvc = webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * @throws Exception the exception
	 */

	@Test
	public void getWeatherByCityTest() throws Exception {

		final String jwttoken = getTokenForTesting();
		String token = "Bearer " + jwttoken;

		mvc.perform(MockMvcRequestBuilders.get("/v1/weather/Mumbai").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token)).andExpect(status().isOk())
				.andReturn();
	}

	/**
	 * @throws Exception the exception
	 */
	@Test
	public void getWeatherByCityWithOutToken() throws Exception {
		String city = " ";
		mvc.perform(MockMvcRequestBuilders.get("/v1/weather/" + city).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized()).andReturn();
	}

	@Test
	public void getWeatherByCityWithInvalidToken() throws Exception {
		String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aW5pdHJhdGhpIiwiZXhwIjoxNjYyNDE5MjkyLCJpYXQiOjE2NjI0MDEyOTJ9.qD9I3NT5OKDc82qLvWYyxeoZGn5sPU_syYf9wCKO7987ezUSgABthHryCJ6IJDp0EQnU5xkDfJG1Wv12rW-Rhg";

		mvc.perform(MockMvcRequestBuilders.get("/v1/weather/Mumbai").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
				.andExpect(status().isUnauthorized()).andReturn();
	}

	@Test
	public void getWeatherByCityNoContentTest() throws Exception {
		String city = " ";
		final String jwttoken = getTokenForTesting();
		String token = "Bearer " + jwttoken;

		mvc.perform(MockMvcRequestBuilders.get("/v1/weather/" + city).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
				.andExpect(status().isBadRequest()).andReturn();
	}

	private String getTokenForTesting() {

		JwtRequest jwtRequest = new JwtRequest();
		jwtRequest.setUsername("vinitrathi");
		jwtRequest.setPassword("Password123");

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

		final String jwttoken = jwtTokenUtil.generateToken(userDetails);
		return jwttoken;
	}

}
