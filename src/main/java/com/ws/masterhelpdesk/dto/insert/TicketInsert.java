package com.ws.masterhelpdesk.dto.insert;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class TicketInsert {

	@NotNull
	@Positive
	private Long customerRequestId;

	@NotNull
	@Positive
	private Long tecnicoEmployeeId;
	
	@NotBlank
	@Size(max = 10)
	private String ticketPriority;
	
	private Instant estimatedTime;
}
