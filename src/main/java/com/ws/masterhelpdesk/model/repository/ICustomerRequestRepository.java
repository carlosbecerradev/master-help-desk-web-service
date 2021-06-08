package com.ws.masterhelpdesk.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.CustomerRequest;

@Repository
public interface ICustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {

	List<CustomerRequest> findByEnabled(Boolean enabled);;
}
