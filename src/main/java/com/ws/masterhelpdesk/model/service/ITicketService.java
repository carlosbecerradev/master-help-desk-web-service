package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.TicketDto;
import com.ws.masterhelpdesk.dto.insert.TicketInsert;
import com.ws.masterhelpdesk.model.entity.Ticket;

public interface ITicketService {

	public void insert(TicketInsert ticketInsert);

	public List<TicketDto> findAllPendienteTicketsByEmployee();

	public List<TicketDto> findAllEnAtencionTicketsByEmployee();

	public List<TicketDto> findAllCerradoTicketsByEmployee();

	public Ticket findTIcketById(Long id);

	public void finishTicketById(Long id);

	public void pickTicketById(Long id);
}
