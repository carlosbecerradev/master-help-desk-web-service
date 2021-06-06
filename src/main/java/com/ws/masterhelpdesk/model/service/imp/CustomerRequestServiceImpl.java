package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.insert.CustomerRequestInsert;
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

	@Override
	@Transactional(readOnly = false)
	public void insert(CustomerRequestInsert customerRequestInsert) throws Exception {
		CustomerRequest cr = new CustomerRequest();
		cr.setRequestType(iRequestTypeService.findRequestTypeByName(customerRequestInsert.getName()));
		cr.setDescription(customerRequestInsert.getDescription());
		cr.setEnabled(true);
		cr.setCreatedAt(Instant.now());
		cr.setCustomer(iCustomerService.findCustomerByUser(authenticationService.getCurrentLoggedInUser()));
		iCustomerRequestRepository.save(cr);
	}

}
