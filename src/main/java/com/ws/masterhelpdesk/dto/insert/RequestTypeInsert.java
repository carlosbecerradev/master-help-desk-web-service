package com.ws.masterhelpdesk.dto.insert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class RequestTypeInsert {

	@NotBlank
	@Size(min = 5, max = 255)
	private String name;
}
