package com.publicissapient.weather.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The implementation class for UserDetailsService interface.
 * 
 * @see com.publicissapient.weather.service.WeatherService
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("vinitrathi".equals(username)) {
			return new User("vinitrathi", "$2a$04$Eftteb4qFL8Rm4r.fCKA8uZQWt7w6nytfaYC0u7l5PfVFyRp81h0C",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}