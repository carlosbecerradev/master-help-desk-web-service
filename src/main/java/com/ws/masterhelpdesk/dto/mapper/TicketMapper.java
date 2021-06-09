package com.ws.masterhelpdesk.dto.mapper;

import org.springframework.stereotype.Component;

import com.ws.masterhelpdesk.dto.TicketDto;
import com.ws.masterhelpdesk.model.entity.Ticket;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TicketMapper {

	private final CustomerRequestMapper customerRequestMapper;
	private final EmployeeMapper employeeMapper;

	public TicketDto mapEntityToDto(Ticket ticket) {
		return TicketDto.builder().id(ticket.getTicketId()).ticketStatus(ticket.getTicketStatus().toString())
				.ticketPriority(ticket.getTicketPriority().toString()).estimatedTime(ticket.getEstimatedTime())
				.createdAt(ticket.getCreatedAt()).finishedAt(ticket.getFinishedAt())
				.customerRequestDto(customerRequestMapper.mapEntityToDto(ticket.getCustomerRequest()))
				.employeeDto(employeeMapper.mapEntityToDto(ticket.getEmployee())).build();
	}
}
