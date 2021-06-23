package com.ws.masterhelpdesk.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserSecurityService {

	private final PasswordEncoder passwordEncoder;

	public String encodePassword(String password) {
		if (password.length() < 60) {
			return passwordEncoder.encode(password);
		} else {
			return password;
		}
	}
}
