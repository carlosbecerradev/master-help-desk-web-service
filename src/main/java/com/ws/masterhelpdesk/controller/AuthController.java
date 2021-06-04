package com.ws.masterhelpdesk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.LoginRequest;
import com.ws.masterhelpdesk.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	private final AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

		String response = authenticationService.validateAuthentication(loginRequest);

		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
