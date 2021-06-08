package com.ws.masterhelpdesk.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.insert.TicketInsert;
import com.ws.masterhelpdesk.model.service.ITicketService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

	private final ITicketService iTicketService;

	@PostMapping
	public ResponseEntity<HttpStatus> insertTicket(@RequestBody @Valid TicketInsert ticketInsert) {
		iTicketService.insert(ticketInsert);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
}
