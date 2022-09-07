package com.publicissapient.weather;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.publicissapient.weather.model.JwtRequest;

import feign.Response;

/**
 * The Class WeatherControllerIntegrationTest.
 * 
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */


@SpringBootTest
@AutoConfigureMockMvc
public class JwtControllerIntegrationTest {

    /** The mvc. */
    @Autowired
    private MockMvc mvc;

    /** The web application context. */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * Setup.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    public void setup() throws Exception {
        this.mvc = webAppContextSetup(webApplicationContext).build();
    }
 

    /**
     * @throws Exception the exception
     */
    @Test
    public void getAuthenticateSuccess() throws Exception {
    	
    	JwtRequest request = new JwtRequest();
    	request.setUsername("vinitrathi");
    	request.setPassword("Password123");
    	
    	 Gson gson = new Gson();
    	 String json = gson.toJson(request);
    	
        mvc.perform(MockMvcRequestBuilders.post("/authenticate").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andReturn();
    }
    
    @Test
    public void getAuthenticateFail() throws Exception {
    	
    	JwtRequest request = new JwtRequest();
    	request.setUsername("vinitrathi1");
    	request.setPassword("Password@123");
    	
    	 Gson gson = new Gson();
    	 String json = gson.toJson(request);
    	
        mvc.perform(MockMvcRequestBuilders.post("/authenticate").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json)).andExpect( status().isInternalServerError()).andReturn();
    }
    

}
