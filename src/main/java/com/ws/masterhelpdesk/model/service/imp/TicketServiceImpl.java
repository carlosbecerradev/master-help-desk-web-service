package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.TicketDto;
import com.ws.masterhelpdesk.dto.insert.TicketInsert;
import com.ws.masterhelpdesk.dto.mapper.TicketMapper;
import com.ws.masterhelpdesk.model.entity.CustomerRequest;
import com.ws.masterhelpdesk.model.entity.Employee;
import com.ws.masterhelpdesk.model.entity.Ticket;
import com.ws.masterhelpdesk.model.entity.TicketPriority;
import com.ws.masterhelpdesk.model.entity.TicketStatus;
import com.ws.masterhelpdesk.model.repository.ITicketRepository;
import com.ws.masterhelpdesk.model.service.ICustomerRequestService;
import com.ws.masterhelpdesk.model.service.IEmployeeService;
import com.ws.masterhelpdesk.model.service.ITicketService;
import com.ws.masterhelpdesk.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements ITicketService {

	private final ITicketRepository iTicketRepository;
	private final IEmployeeService iEmployeeService;
	private final ICustomerRequestService iCustomerRequestService;
	private final TicketMapper ticketMapper;
	private final AuthenticationService authenticationService;

	@Override
	@Transactional(readOnly = false)
	public void insert(TicketInsert ticketInsert) {
		Ticket newTicket = new Ticket();

		CustomerRequest cr = iCustomerRequestService.findCustomerRequestById(ticketInsert.getCustomerRequestId());
		newTicket.setCustomerRequest(cr);
		iCustomerRequestService.disabledCustomerRequest(cr);
		newTicket.setEmployee(iEmployeeService.getEmployeeById(ticketInsert.getTecnicoEmployeeId()));
		newTicket.setTicketPriority(TicketPriority.valueOf(ticketInsert.getTicketPriority()));
		newTicket.setTicketStatus(TicketStatus.PENDIENTE);
		newTicket.setEstimatedTime(ticketInsert.getEstimatedTime());
		newTicket.setFinishedAt(null);
		newTicket.setCreatedAt(Instant.now());

		iTicketRepository.save(newTicket);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TicketDto> findAllPendienteTicketsByEmployee() {
		Employee employee = iEmployeeService.findEmployeeByUser(authenticationService.getCurrentLoggedInUser());
		return iTicketRepository.findByTicketStatusAndEmployee(TicketStatus.PENDIENTE, employee).stream()
				.map(ticketMapper::mapEntityToDto).collect(Collectors.toList());
	}

}
