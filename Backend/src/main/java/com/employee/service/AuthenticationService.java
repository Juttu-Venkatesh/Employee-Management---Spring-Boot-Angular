package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.dto.AuthenticationResponse;
import com.employee.entity.User;
import com.employee.jwt.JwtService;
import com.employee.repo.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(User request) {
	    User user = new User();
	    user.setUsername(request.getUsername());
	    user.setEmail(request.getEmail());
	    user.setPassword(request.getPassword()); 
	    user.setRole(request.getRole());
	    
	    user = repository.save(user);
	    
	    String token = jwtService.generateToken(user);
	    
	    return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse authenticate(User request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()
						)
				);
		User user = repository.findByUsername(request.getUsername()).orElseThrow();
		String token =  jwtService.generateToken(user);
		return new AuthenticationResponse(token);
	}

}
