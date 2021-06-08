package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.CustomerRequestDto;
import com.ws.masterhelpdesk.dto.insert.CustomerRequestInsert;
import com.ws.masterhelpdesk.dto.mapper.CustomerRequestMapper;
import com.ws.masterhelpdesk.model.entity.CustomerRequest;
import com.ws.masterhelpdesk.model.repository.ICustomerRequestRepository;
import com.ws.masterhelpdesk.model.service.ICustomerRequestService;
import com.ws.masterhelpdesk.model.service.ICustomerService;
import com.ws.masterhelpdesk.model.service.IRequestTypeService;
import com.ws.masterhelpdesk.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerRequestServiceImpl implements ICustomerRequestService {

	private final ICustomerRequestRepository iCustomerRequestRepository;
	private final IRequestTypeService iRequestTypeService;
	private final AuthenticationService authenticationService;
	private final ICustomerService iCustomerService;
	private final CustomerRequestMapper customerRequestMapper;

	@Override
	@Transactional(readOnly = false)
	public void insert(CustomerRequestInsert customerRequestInsert) throws Exception {
		CustomerRequest cr = new CustomerRequest();
		cr.setRequestType(iRequestTypeService.findRequestTypeByName(customerRequestInsert.getRequestTypeName()));
		cr.setDescription(customerRequestInsert.getDescription());
		cr.setEnabled(true);
		cr.setCreatedAt(Instant.now());
		cr.setCustomer(iCustomerService.findCustomerByUser(authenticationService.getCurrentLoggedInUser()));
		iCustomerRequestRepository.save(cr);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerRequestDto> findAllEnabledCustomerRequest() {
		return iCustomerRequestRepository.findByEnabled(true).stream().map(customerRequestMapper::mapEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerRequest findCustomerRequestById(Long id) {
		return iCustomerRequestRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer Request with id: " + id + " is not found!"));
	}

}
