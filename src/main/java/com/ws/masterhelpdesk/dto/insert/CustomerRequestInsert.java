package com.ws.masterhelpdesk.dto.insert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class CustomerRequestInsert {
	@NotBlank
	@Size(max = 255)
	private String name;

	@NotBlank
	@Size(max = 255)
	private String description;
}
