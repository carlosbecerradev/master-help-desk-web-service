package com.ws.masterhelpdesk.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ws.masterhelpdesk.model.entity.Assessment;

@Repository
public interface IAssessmentRepository extends JpaRepository<Assessment, Long> {

	Optional<Assessment> findByToken(String token);

	List<Assessment> findByTicketEmployeeEmployeeId(Long employeeId);

}
