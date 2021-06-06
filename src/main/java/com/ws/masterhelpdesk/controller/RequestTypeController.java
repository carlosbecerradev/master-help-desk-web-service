package com.ws.masterhelpdesk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.model.service.IRequestTypeService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer_request_type")
@AllArgsConstructor
public class RequestTypeController {

	private final IRequestTypeService iRequestTypeService;

	@GetMapping("/names")
	public ResponseEntity<List<String>> getAllRequestTypeNames() {
		try {
			List<String> list = iRequestTypeService.getAllNames();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
