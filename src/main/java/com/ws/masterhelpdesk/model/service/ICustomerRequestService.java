package com.ws.masterhelpdesk.model.service;

import com.ws.masterhelpdesk.dto.insert.CustomerRequestInsert;

public interface ICustomerRequestService {

	public void insert(CustomerRequestInsert customerRequestInsert) throws Exception;
}
