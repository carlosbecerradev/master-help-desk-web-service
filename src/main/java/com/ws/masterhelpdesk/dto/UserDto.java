package com.ws.masterhelpdesk.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

	private Long id;

	private String username;

	private String password;

	private String authority;

	private Boolean enabled;

	private Instant createdAt;
}
