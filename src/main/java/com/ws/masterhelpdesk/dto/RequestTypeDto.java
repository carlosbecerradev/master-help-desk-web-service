package com.ws.masterhelpdesk.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RequestTypeDto {
	@Positive
	private Long id;

	@NotBlank
	@Size(min = 5, max = 255)
	private String name;

	private Boolean enabled;
	
	private Instant createdAt;
}
