package com.ws.masterhelpdesk.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

	private Long id;

	@NotBlank
	@Size(min = 6, max = 20)
	private String username;

	@NotBlank
	@Size(min = 6, max = 60)
	private String password;

	@NotBlank
	@Size(max = 20)
	private String authority;

	@NotNull
	private Boolean enabled;

	private Instant createdAt;
}
