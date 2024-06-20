package com.example.api.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		
		 config.setAllowedOrigins(Arrays.asList("*")); // todo delete "*"
	     config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
	     config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     source.registerCorsConfiguration("/**", config);
	     return source;
	}

}
