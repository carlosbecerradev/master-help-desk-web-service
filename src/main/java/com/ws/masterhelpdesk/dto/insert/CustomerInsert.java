package com.ws.masterhelpdesk.dto.insert;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerInsert {

	private String name;

	private String surname;

	private String email;

	private String cellphone;

	private String gender;

	private Boolean enabled;

	private String username;

	private String password;
}
