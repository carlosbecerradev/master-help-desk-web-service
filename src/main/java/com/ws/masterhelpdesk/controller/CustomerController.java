package com.ws.masterhelpdesk.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.CustomerDto;
import com.ws.masterhelpdesk.model.service.ICustomerService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {
	
	private final ICustomerService iCustomerService;
	

	@GetMapping
	public ResponseEntity<List<CustomerDto>> getAllUserDto() {
		return new ResponseEntity<List<CustomerDto>>(iCustomerService.getAllCustomersDto(), HttpStatus.OK);
	}

	@GetMapping("/genders")
	public ResponseEntity<List<String>> getAllGenders() {
		return new ResponseEntity<List<String>>(iCustomerService.getAllGenders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> geCustomerrById(@PathVariable(value = "id", required = true) @Positive Long id) {
		return new ResponseEntity<CustomerDto>(iCustomerService.getCustomerDtoById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> insertCustomer(@RequestBody @Valid CustomerDto customerDto) {
		iCustomerService.insertCustomer(customerDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid CustomerDto customerDto) {
		iCustomerService.updateCustomer(customerDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "id", required = true) @Positive Long id) {
		iCustomerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
