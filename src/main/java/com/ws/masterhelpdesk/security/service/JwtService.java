package com.ws.masterhelpdesk.security.service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final KeyPair KEY_PAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);
	@Value("${jwt.durationInSeconds}")
	private Long jwtDurationInSeconds;
	public static Date jwtExpiration;

	public String generateJWT(Authentication auth) {
		jwtExpiration = Date.from(Instant.now().plusSeconds(jwtDurationInSeconds));

		JwtBuilder jws = Jwts.builder().setSubject(auth.getName()).setExpiration(jwtExpiration)
				.claim("authorities", auth.getAuthorities()).signWith(getPrivateKey());

		String jwt = jws.compact();

		return jwt;
	}

	private PrivateKey getPrivateKey() {
		return this.KEY_PAIR.getPrivate();
	}

	public boolean validateJWT(String jwt) {
		try {
			getClaims(jwt);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public Claims getClaims(String jwt) {
		Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);

		return jws.getBody();
	}

	private PublicKey getPublicKey() {
		return this.KEY_PAIR.getPublic();
	}
}
