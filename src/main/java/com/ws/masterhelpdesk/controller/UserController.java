package com.ws.masterhelpdesk.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.masterhelpdesk.dto.UserDto;
import com.ws.masterhelpdesk.model.service.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

	private final IUserService iUserService;

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUserDto() {
		return new ResponseEntity<List<UserDto>>(iUserService.getAllUserDto(), HttpStatus.OK);
	}

	@GetMapping("/authorities")
	public ResponseEntity<List<String>> getAllAuthorities() {
		return new ResponseEntity<List<String>>(iUserService.getAllAuthorities(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id", required = true) @Positive Long id) {
		return new ResponseEntity<UserDto>(iUserService.getUserDtoById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> insertUser(@RequestBody @Valid UserDto userDto) {
		iUserService.insertUser(userDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid UserDto userDto) {
		iUserService.updateUser(userDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
