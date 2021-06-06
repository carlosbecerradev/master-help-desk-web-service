package com.ws.masterhelpdesk.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.CustomerRequest;

@Repository
public interface ICustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {

}
