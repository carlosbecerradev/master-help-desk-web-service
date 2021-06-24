package com.ws.masterhelpdesk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AssessmentDto {

	private String token;

	private String assessmentType;

	private String observation;

	private TicketDto ticketDto;

}
