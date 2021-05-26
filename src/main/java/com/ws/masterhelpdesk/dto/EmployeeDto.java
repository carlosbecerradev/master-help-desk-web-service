package com.ws.masterhelpdesk.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeDto {

	private Long id;

	private String name;

	private String surname;

	private Boolean enabled;

	private Instant createdAt;

	private Long userId;
}
