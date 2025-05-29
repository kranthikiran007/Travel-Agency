package com.kk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private JWTAuthEntryPoint authEntryPoint;
	@Autowired
	private JWTAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private JWTAuthenticationFilter authenticationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity h) throws Exception {
		h.csrf(config -> config.disable());
//		h.csrf(AbstractHttpConfigurer::disable);
		h.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/travel-agency/auth/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
				.anyRequest().authenticated())
				.exceptionHandling(
						ex -> ex.accessDeniedHandler(accessDeniedHandler))
				.exceptionHandling(ex->ex.authenticationEntryPoint(authEntryPoint))
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return h.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig) throws Exception {
		return authconfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
