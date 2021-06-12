package com.ws.masterhelpdesk.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.TicketDto;
import com.ws.masterhelpdesk.dto.insert.TicketInsert;
import com.ws.masterhelpdesk.exception.ApiError;
import com.ws.masterhelpdesk.model.entity.TicketPriority;
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

	@GetMapping("/priorities")
	public ResponseEntity<List<String>> getAllTicketPriotities() {
		List<String> list = new ArrayList<String>();
		for (TicketPriority ticketPriority : TicketPriority.values()) {
			list.add(ticketPriority.toString());
		}
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}

	@GetMapping("/ticketStatus=PENDIENTE")
	public ResponseEntity<List<TicketDto>> getAllPendienteTicketsByUserLoggedin() {
		return new ResponseEntity<List<TicketDto>>(iTicketService.findAllPendienteTicketsByEmployee(), HttpStatus.OK);
	}

	@GetMapping("/ticketStatus=ATENDIENDO")
	public ResponseEntity<List<TicketDto>> getAllEnAtencionTicketsByUserLoggedin() {
		return new ResponseEntity<List<TicketDto>>(iTicketService.findAllEnAtencionTicketsByEmployee(), HttpStatus.OK);
	}

	@GetMapping("/ticketStatus=CERRADO")
	public ResponseEntity<List<TicketDto>> getAllCerradoTicketsByUserLoggedin() {
		return new ResponseEntity<List<TicketDto>>(iTicketService.findAllCerradoTicketsByEmployee(), HttpStatus.OK);
	}

	@PostMapping("/change/ticketStatus/{ticketId}")
	public ResponseEntity<HttpStatus> updateTicketStatusById(@PathVariable("ticketId") @Positive Long ticketId,
			@RequestBody String ticketStatus) {
		iTicketService.updateTicketStatusById(ticketId, ticketStatus);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@PostMapping("/finish/{id}")
	public ResponseEntity<HttpStatus> finishTicketById(@PathVariable("id") @Positive Long id) {
		iTicketService.finishTicketById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream()
				.map(err -> new ApiError(err.getCodes(), err.getDefaultMessage())).collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public List<ApiError> handleValidationExceptions(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(err -> new ApiError(err.getPropertyPath().toString(), err.getMessage()))
				.collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RuntimeException.class)
	public List<ApiError> handleNotFoundExceptions(RuntimeException ex) {
		return Collections.singletonList(new ApiError("Ticket Not Found", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}

}
