package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.insert.TicketInsert;
import com.ws.masterhelpdesk.model.entity.Ticket;
import com.ws.masterhelpdesk.model.entity.TicketPriority;
import com.ws.masterhelpdesk.model.entity.TicketStatus;
import com.ws.masterhelpdesk.model.repository.ITicketRepository;
import com.ws.masterhelpdesk.model.service.ICustomerRequestService;
import com.ws.masterhelpdesk.model.service.IEmployeeService;
import com.ws.masterhelpdesk.model.service.ITicketService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements ITicketService {

	private final ITicketRepository iTicketRepository;
	private final IEmployeeService iEmployeeService;
	private final ICustomerRequestService iCustomerRequestService;

	@Override
	@Transactional(readOnly = false)
	public void insert(TicketInsert ticketInsert) {
		Ticket newTicket = new Ticket();

		newTicket.setCustomerRequest(
				iCustomerRequestService.findCustomerRequestById(ticketInsert.getCustomerRequestId()));
		newTicket.setEmployee(iEmployeeService.getEmployeeById(ticketInsert.getTecnicoEmployeeId()));
		newTicket.setTicketPriority(TicketPriority.valueOf(ticketInsert.getTicketPriority()));
		newTicket.setTicketStatus(TicketStatus.PENDIENTE);
		newTicket.setEstimatedTime(ticketInsert.getEstimatedTime());
		newTicket.setFinishedAt(null);
		newTicket.setCreatedAt(Instant.now());

		iTicketRepository.save(newTicket);
	}

}
