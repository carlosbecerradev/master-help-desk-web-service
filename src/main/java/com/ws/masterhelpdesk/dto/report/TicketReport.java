package com.ws.masterhelpdesk.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TicketReport {
	private String month;
	private Long week1;
	private Long week2;
	private Long week3;
	private Long week4;
	private Long week5;
	private Long total;
}
