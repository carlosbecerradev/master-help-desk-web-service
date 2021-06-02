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

import com.ws.masterhelpdesk.dto.EmployeeDto;
import com.ws.masterhelpdesk.dto.insert.EmployeeInsert;
import com.ws.masterhelpdesk.model.service.IEmployeeService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

	private final IEmployeeService iEmployeeService;

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAlEmployeeDto() {
		return new ResponseEntity<List<EmployeeDto>>(iEmployeeService.getAllEmployeeDto(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(value = "id", required = true) @Positive Long id) {
		return new ResponseEntity<EmployeeDto>(iEmployeeService.getEmployeeDtoById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> insertEmployee(@RequestBody @Valid EmployeeInsert employeeInsert) {
		iEmployeeService.insertEmployee(employeeInsert);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<HttpStatus> updateEmployee(@RequestBody @Valid EmployeeDto EmployeeDto) {
		iEmployeeService.updateEmployee(EmployeeDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id", required = true) @Positive Long id) {
		iEmployeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
