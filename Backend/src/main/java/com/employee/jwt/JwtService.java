package com.employee.jwt;


import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.employee.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String SCRECT_KEY = "9fd71547bdef3a1c16a96720a6320c6df37a616208463fe8321698f7e60fd780";
	
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public boolean isValid(String token, UserDetails user) {
		String username = extractUsername(token);
		return (username.equals(user.getUsername()) && isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	public<T> T extractClaims(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
	    return Jwts
	            .parserBuilder()
	            .setSigningKey(getSigninKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}

	
	public String generateToken(User user) {
		String token = Jwts
				.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 24*60*60*10000))
				.signWith(getSigninKey())
				.compact();
		return token;
	}

	private Key getSigninKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SCRECT_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
