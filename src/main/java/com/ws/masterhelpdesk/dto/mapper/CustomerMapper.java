package com.ws.masterhelpdesk.dto.mapper;

import org.springframework.stereotype.Component;

import com.ws.masterhelpdesk.dto.CustomerDto;
import com.ws.masterhelpdesk.model.entity.Customer;
import com.ws.masterhelpdesk.model.entity.Gender;
import com.ws.masterhelpdesk.model.service.IUserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerMapper {

	private final IUserService userService;

	public CustomerDto mapEntityToDto(Customer customer) {
		return CustomerDto.builder().id(customer.getCustomerId()).name(customer.getName())
				.surname(customer.getSurname()).email(customer.getEmail()).cellphone(customer.getCellphone())
				.gender(customer.getGender().toString()).enabled(customer.getEnabled())
				.createdAt(customer.getCreatedAt()).userId(customer.getUser().getUserId()).build();
	}

	public Customer mapDtoToEntity(CustomerDto customerDto, Customer customer) {

		customer.setName(customerDto.getName());
		customer.setSurname(customerDto.getSurname());
		customer.setEmail(customerDto.getEmail());
		customer.setCellphone(customerDto.getCellphone());
		customer.setEnabled(customerDto.getEnabled());
		customer.setUser(userService.getUserById(customerDto.getUserId()));

		for (Gender gender : Gender.values()) {
			if (gender.toString().equalsIgnoreCase(customerDto.getGender())) {
				customer.setGender(gender);
			}
		}
		return customer;
	}
}
