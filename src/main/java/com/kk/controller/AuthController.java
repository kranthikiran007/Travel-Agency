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

@RestController
@RequestMapping("/api/travel-agency/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto)
	{
		System.out.println("controller");
		var r = authService.login(loginDto);
		AuthResponse response = new AuthResponse();
		response.setJwtToken(r);
		return ResponseEntity.ok(response);
	}
}
