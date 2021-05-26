package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.CustomerDto;
import com.ws.masterhelpdesk.dto.mapper.CustomerMapper;
import com.ws.masterhelpdesk.model.entity.Customer;
import com.ws.masterhelpdesk.model.entity.Gender;
import com.ws.masterhelpdesk.model.repository.ICustomerRepository;
import com.ws.masterhelpdesk.model.service.ICustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	private final ICustomerRepository iCustomerRepository;
	private final CustomerMapper customerMapper;

	@Override
	public Gender[] getEnumGenders() {
		return Gender.values();
	}

	@Override
	public List<String> getAllGenders() {
		List<String> list = new ArrayList<String>();
		for (Gender gender : getEnumGenders()) {
			list.add(gender.toString());
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDto> getAllCustomersDto() {
		return iCustomerRepository.findAll().stream().map(customerMapper::mapEntityToDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Customer getCustomerById(Long id) {
		return iCustomerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer with ID: " + id + " is not found."));
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerDto getCustomerDtoById(Long id) {
		return customerMapper.mapEntityToDto(getCustomerById(id));
	}

	@Override
	@Transactional(readOnly = false)
	public void insertCustomer(CustomerDto customerDto) {
		Customer newCustomer = new Customer();
		newCustomer = customerMapper.mapDtoToEntity(customerDto, newCustomer);
		newCustomer.setCreatedAt(Instant.now());
		iCustomerRepository.save(newCustomer);
		System.out.println("insert: " + newCustomer.toString());
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCustomer(CustomerDto customerDto) {
		Customer customer = getCustomerById(customerDto.getId());
		customer = customerMapper.mapDtoToEntity(customerDto, customer);
		iCustomerRepository.save(customer);
		System.out.println("update: " + customer.toString());
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteCustomer(Long id) {
		iCustomerRepository.deleteById(id);
	}

}
