package com.ws.masterhelpdesk.dto;

import java.time.Instant;

import com.ws.masterhelpdesk.model.entity.RequestType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerRequestDto {

	private Long id;

	private String description;

	private Boolean enabled;
	
	private Instant createdAt;
	
	private RequestType requestType;
	
	private CustomerDto customerDto;
}
