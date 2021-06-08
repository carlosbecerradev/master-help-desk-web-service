package com.ws.masterhelpdesk.dto.mapper;

import org.springframework.stereotype.Component;

import com.ws.masterhelpdesk.dto.CustomerRequestDto;
import com.ws.masterhelpdesk.model.entity.CustomerRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerRequestMapper {

	private final CustomerMapper customerMapper;

	public CustomerRequestDto mapEntityToDto(CustomerRequest customerRequest) {
		return CustomerRequestDto.builder().id(customerRequest.getCustomerRequestId())
				.description(customerRequest.getDescription()).enabled(customerRequest.getEnabled())
				.createdAt(customerRequest.getCreatedAt()).requestType(customerRequest.getRequestType())
				.customerDto(customerMapper.mapEntityToDto(customerRequest.getCustomer())).build();
	}
}
