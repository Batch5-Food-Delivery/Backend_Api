package com.hostmdy.food.config;

import java.util.Arrays;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hostmdy.food.service.impl.UserSecurityService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private final UserSecurityService userSecurityService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JwtTokenProvider tokenProvider() {
		return new JwtTokenProvider();
	}
	
	@Bean
	JwtAuthenticationFilter authFilter() {
		return new JwtAuthenticationFilter(userSecurityService,tokenProvider());
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/user/login").permitAll()
					.requestMatchers("/user/create").permitAll()
					.requestMatchers(HttpMethod.GET, "/restaurant/**").permitAll()
					.requestMatchers("/restaurant/pendingRestaurants").hasRole("ADMIN")
					.requestMatchers("/restaurant/acceptRestaurant").hasRole("ADMIN")
					.requestMatchers(HttpMethod.GET, "/menu/**").permitAll() 
					.requestMatchers(HttpMethod.GET, "/food/**").permitAll()
					.requestMatchers("/driver").hasRole("DRIVER")
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(authFilter(),UsernamePasswordAuthenticationFilter.class);

		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	AuthenticationProvider daoProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setUserDetailsService(userSecurityService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}
