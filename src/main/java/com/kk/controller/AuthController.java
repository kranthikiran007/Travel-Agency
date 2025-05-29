package com.kk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kk.dtos.AuthResponse;
import com.kk.dtos.LoginDto;
import com.kk.service.AuthService;

// This controller handles authentication-related endpoints for the Travel Agency System.
@RestController
@RequestMapping("/api/travel-agency/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	// This endpoint allows users to log in by providing their credentials.
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto)
	{
		var r = authService.login(loginDto);
		AuthResponse response = new AuthResponse();
		response.setJwtToken(r);
		response.setType("Bearer");
		return ResponseEntity.ok(response);
	}
}
