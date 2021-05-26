package com.ws.masterhelpdesk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDto {

	private Long id;
	
	private String name;
	
	private String surname;

	private String email;

	private String cellphone;

	private String gender;

	private Boolean enabled;

	private Long userId;
}
