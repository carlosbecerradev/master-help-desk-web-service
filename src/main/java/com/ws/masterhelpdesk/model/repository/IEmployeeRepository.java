package com.ws.masterhelpdesk.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.Employee;
import com.ws.masterhelpdesk.model.entity.User;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select et from Employee et where et.user.authority = 'TECNICO' and et.enabled = true")
	List<Employee> findByRolTECNICOAndEnabledTrue();

	Optional<Employee> findByUser(User user);

}
