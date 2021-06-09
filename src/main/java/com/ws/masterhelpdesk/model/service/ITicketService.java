package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.TicketDto;
import com.ws.masterhelpdesk.dto.insert.TicketInsert;

public interface ITicketService {

	public void insert(TicketInsert ticketInsert);
	
	public List<TicketDto> findAllPendienteTicketsByEmployee();
}
