package com.ws.masterhelpdesk.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.CustomerDto;
import com.ws.masterhelpdesk.dto.RequestTypeDto;
import com.ws.masterhelpdesk.dto.insert.CustomerInsert;
import com.ws.masterhelpdesk.dto.insert.RequestTypeInsert;
import com.ws.masterhelpdesk.exception.ApiError;
import com.ws.masterhelpdesk.model.entity.RequestType;
import com.ws.masterhelpdesk.model.service.IRequestTypeService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/request-types")
@AllArgsConstructor
public class RequestTypeController {

	private final IRequestTypeService iRequestTypeService;

	@GetMapping("/names")
	public ResponseEntity<List<String>> getEnabledRequestTypeNames() {
		try {
			List<String> list = iRequestTypeService.getEnabledNames();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<RequestType>> getAllRequestTypes() {
		return new ResponseEntity<List<RequestType>>(iRequestTypeService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RequestType> getRequestTypeById(
			@PathVariable(value = "id", required = true) @Positive Long id) {
		return new ResponseEntity<RequestType>(iRequestTypeService.findRequestTypeById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> insertRequestType(@RequestBody @Valid RequestTypeInsert requestTypeInsert) {
		iRequestTypeService.insert(requestTypeInsert);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<HttpStatus> updateRequestType(@RequestBody @Valid RequestTypeDto requestTypeDto) {
		iRequestTypeService.update(requestTypeDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteRequestType(
			@PathVariable(value = "id", required = true) @Positive Long id) {
		iRequestTypeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
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
