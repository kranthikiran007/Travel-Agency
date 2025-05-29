package com.kk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(
	    info = @Info(
	        title = "Travel Agency Application",
	        version = "1.0",
	        description = "Spring Boot Application for Performing Secured CRUD Operations over a Travel Agency Database "
	    ),
	    security = @SecurityRequirement(name = "BearerAuth") // Apply BearerAuth globally
	)
	@SecurityScheme(
	    name = "BearerAuth", // Name of the security scheme
	    type = SecuritySchemeType.HTTP,
	    scheme = "bearer",
	    bearerFormat = "JWT", // Optional, for documentation purposes
	    description = "JWT authentication using a Bearer token"
	)
public class TravelAgencySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencySystemApplication.class, args);
	}

}
