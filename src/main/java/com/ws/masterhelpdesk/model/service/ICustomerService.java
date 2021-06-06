package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.CustomerDto;
import com.ws.masterhelpdesk.dto.insert.CustomerInsert;
import com.ws.masterhelpdesk.model.entity.Customer;
import com.ws.masterhelpdesk.model.entity.Gender;
import com.ws.masterhelpdesk.model.entity.User;

public interface ICustomerService {

	public Gender[] getEnumGenders();

	public List<String> getAllGenders();

	public List<CustomerDto> getAllCustomersDto();

	public Customer getCustomerById(Long id);

	public CustomerDto getCustomerDtoById(Long id);

	public void insertCustomer(CustomerInsert customerDto);

	public void updateCustomer(CustomerDto customerDto);
	
	public void deleteCustomer(Long id);
	
	public Customer findCustomerByUser(User user);
}
