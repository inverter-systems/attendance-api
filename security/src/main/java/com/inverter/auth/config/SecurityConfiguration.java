package com.inverter.auth.config;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inverter.auth.CustomAuthenticationEntryPoint;
import com.inverter.auth.FilterToken;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	 static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
			 "/favicon.ico",
			 "/api/auth",
			 "/api/auth/user/create", 
			 "/api/auth/user/activate", 
			 "/api/auth/user/forgot-password", 
			 "/api/auth/reset/reset-password",
			 "/api/auth/reset/reset-password-ui"};

     static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {"/users/test"};

     static final String [] ENDPOINTS_CUSTOMER = {"/users/test/customer"};

     static final String [] ENDPOINTS_ADMIN = {"/users/test/administrator"};

	@Bean
	SecurityFilterChain securityFilterChain1(HttpSecurity http, FilterToken filter) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
						.requestMatchers(ENDPOINTS_ADMIN).hasAuthority("ROLE_ADMINISTRATOR") 
			            .requestMatchers(ENDPOINTS_CUSTOMER).hasAuthority("ROLE_CUSTOMER") 
						.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
		                .anyRequest().denyAll())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.formLogin(form -> form.disable()) // Desabilita o formulário de login padrão
				.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()))
				.build();
	}
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();    
	}
	
	public static Stream<String> getEndpointsWithAutenticationNotReuired() {
		return Arrays.stream(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED);
	}
}
