package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.CustomerRequestDto;
import com.ws.masterhelpdesk.dto.insert.CustomerRequestInsert;

public interface ICustomerRequestService {

	public void insert(CustomerRequestInsert customerRequestInsert) throws Exception;

	public List<CustomerRequestDto> findAllEnabledCustomerRequest();
}
