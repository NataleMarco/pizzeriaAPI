package com.marquitos.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig { 

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http
			.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers(HttpMethod.GET,"/pizza/**").hasAnyRole("ADMIN","CUSTOMER")
			.requestMatchers(HttpMethod.POST,"/pizza/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
			.requestMatchers("/orders/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();


		return http.build();

	}

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}

}
