package com.marquitos.pizzeria.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration

@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private final JwtFilter jwtFilter;

	@Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests()
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/customers/**").hasAnyRole("ADMIN","CUSTOMER")
				.requestMatchers(HttpMethod.GET,"/pizza/**").hasAnyRole("ADMIN","CUSTOMER")
				.requestMatchers(HttpMethod.POST,"/pizza/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
				.requestMatchers("/orders/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);


		return http.build();

	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



}
