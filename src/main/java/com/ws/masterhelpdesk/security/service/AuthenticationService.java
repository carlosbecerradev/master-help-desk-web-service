package com.ws.masterhelpdesk.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ws.masterhelpdesk.dto.LoginRequest;
import com.ws.masterhelpdesk.model.entity.User;
import com.ws.masterhelpdesk.model.repository.IUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final IUserRepository iUserRepository;

	public String validateAuthentication(LoginRequest loginRequest) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);

			return jwtService.generateJWT(auth);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Authentication getCurrentAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public User getCurrentLoggedInUser() {
		Authentication auth = getCurrentAuthentication();
		return iUserRepository.findByUsername(auth.getName())
				.orElseThrow(() -> new RuntimeException("User with username: " + auth.getName() + " is not found."));
	}
}
