package com.ws.masterhelpdesk.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.Customer;
import com.ws.masterhelpdesk.model.entity.User;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByUser(User user);
}
