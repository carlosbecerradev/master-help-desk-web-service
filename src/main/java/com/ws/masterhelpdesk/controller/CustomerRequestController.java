package com.ws.masterhelpdesk.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.CustomerRequestDto;
import com.ws.masterhelpdesk.dto.insert.CustomerRequestInsert;
import com.ws.masterhelpdesk.exception.ApiError;
import com.ws.masterhelpdesk.model.service.ICustomerRequestService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer-requests")
@AllArgsConstructor
public class CustomerRequestController {

	private final ICustomerRequestService iCustomerRequestService;

	@PostMapping
	public ResponseEntity<HttpStatus> insertCustomerRequest(
			@RequestBody @Valid CustomerRequestInsert customerRequestInsert) {
		try {
			iCustomerRequestService.insert(customerRequestInsert);
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/enabled/true")
	public ResponseEntity<List<CustomerRequestDto>> getAllEnabledCustomerRequests() {
		return new ResponseEntity<List<CustomerRequestDto>>(iCustomerRequestService.findAllEnabledCustomerRequest(),
				HttpStatus.OK);
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
		return Collections.singletonList(new ApiError("Customer Not Found", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}

}
