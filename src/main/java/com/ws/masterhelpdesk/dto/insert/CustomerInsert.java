package com.ws.masterhelpdesk.dto.insert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerInsert {

	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank
	@Size(min = 2, max = 50)
	private String surname;

	@NotBlank
	@Email
	@Size(max = 100)
	private String email;

	@NotBlank
	@Size(max = 15)
	private String cellphone;

	@NotBlank
	@Size(max = 20)
	private String gender;

	@NotNull
	private Boolean enabled;

	@NotBlank
	@Size(min = 6, max = 20)
	private String username;

	@NotBlank
	@Size(min = 6, max = 60)
	private String password;
}
