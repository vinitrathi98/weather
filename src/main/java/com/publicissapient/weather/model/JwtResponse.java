package com.publicissapient.weather.model;

import java.io.Serializable;

/**
 * The Class JwtResponse.
 *
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}