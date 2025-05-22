package com.kk.security;

import java.security.Key;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.kk.exception.CustomerServiceException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTokenProvider {

	@Value("${api.jwt.key}")
	private String secretKey;
	@Value("${api.jwt.expiryInMills}")
	private long expiry;

	public String generateToken(Authentication authentication) {
		String token;
		String userName = authentication.getName();
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + expiry);
		token = Jwts.builder().subject(userName).issuedAt(currentDate).expiration(expiryDate)
				.signWith((SecretKey) key()).compact();

		return token;
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	public String getUserName(String token) {
		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
		} catch (MalformedJwtException | SignatureException ex) {
			throw new CustomerServiceException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new CustomerServiceException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new CustomerServiceException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new CustomerServiceException(HttpStatus.BAD_REQUEST, "Token claim String null or empty");
		}
		return true;
	}

}
