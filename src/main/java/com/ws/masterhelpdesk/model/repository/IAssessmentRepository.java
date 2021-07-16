package com.ws.masterhelpdesk.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ws.masterhelpdesk.model.entity.Assessment;
import com.ws.masterhelpdesk.model.entity.AssessmentType;
import com.ws.masterhelpdesk.model.entity.Employee;

@Repository
public interface IAssessmentRepository extends JpaRepository<Assessment, Long> {

	Optional<Assessment> findByToken(String token);

	List<Assessment> findByTicketEmployeeEmployeeId(Long employeeId);

	@Query("SELECT COUNT(a.assessmentId) FROM Assessment a WHERE a.ticket.employee = :employee AND a.assessment_type = :assessmentType")
	Integer countAssessmentsByEmployeeAndAssessmentType(@Param("employee") Employee employee,
			@Param("assessmentType") AssessmentType assessmentType);

	@Query("SELECT COUNT(a.assessmentId) FROM Assessment a WHERE a.ticket.employee = :employee AND a.assessment_type is null")
	Integer countAssessmentsByEmployeeAndAssessmentTypeNULL(@Param("employee") Employee employee);
}
