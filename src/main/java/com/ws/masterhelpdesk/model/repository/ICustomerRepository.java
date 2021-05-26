package com.ws.masterhelpdesk.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
