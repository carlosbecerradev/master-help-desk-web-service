package com.ws.masterhelpdesk.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketDto {

	private Long id;

	private String ticketStatus;

	private String ticketPriority;

	private Instant estimatedTime;

	private Instant createdAt;

	private Instant finishedAt;

	private EmployeeDto employeeDto;

	private CustomerRequestDto customerRequestDto;
}
