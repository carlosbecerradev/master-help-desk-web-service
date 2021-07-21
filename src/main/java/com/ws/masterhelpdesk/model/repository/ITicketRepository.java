package com.ws.masterhelpdesk.model.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.Employee;
import com.ws.masterhelpdesk.model.entity.Ticket;
import com.ws.masterhelpdesk.model.entity.TicketStatus;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByTicketStatusAndEmployee(TicketStatus ticketStatus, Employee employee);

	@Query("select t from Ticket t where t.ticketStatus = 'CERRADO' AND MONTH(t.createdAt) = :mes AND YEAR(t.createdAt) = :anio")
	List<Ticket> findByTicketStatusCERRADOAndMonthAndYear(@Param("mes") Integer mes, @Param("anio") Integer anio);

	@Query("SELECT COUNT(t.ticketId) FROM Ticket t WHERE t.employee = :employee AND t.createdAt BETWEEN :start AND :end")
	Integer countTicketsByEmployeeAndDay(@Param("start") Instant start, @Param("end") Instant end,
			@Param("employee") Employee employee);

	@Query("SELECT COUNT(t.ticketId) FROM Ticket t WHERE t.employee = :employee AND t.ticketStatus = :status AND t.createdAt BETWEEN :start AND :end")
	Integer countTicketsByEmployeeAndTicketStatusAndDay(@Param("start") Instant start, @Param("end") Instant end,
			@Param("employee") Employee employee, @Param("status") TicketStatus status);

	@Query("SELECT COUNT(t.ticketId) FROM Ticket t WHERE t.employee = :employee AND t.ticketStatus = :status")
	Integer countTicketsByTicketStatusAndEmployee(@Param("employee") Employee employee,
			@Param("status") TicketStatus status);

	@Query("select COUNT(t.ticketId) from Ticket t where t.ticketStatus = 'ATENDIENDO' AND t.createdAt BETWEEN :start AND :end")
	Integer countByTicketStatusATENDIENDOAndDay(@Param("start") Instant start, @Param("end") Instant end);

	@Query("SELECT COUNT(t.ticketId) FROM Ticket t WHERE t.createdAt BETWEEN :start AND :end")
	Integer countTicketsBetweenCreatedAt(@Param("start") Instant start, @Param("end") Instant end);

	@Query("SELECT COUNT(t.ticketId) FROM Ticket t WHERE MONTH(t.createdAt) = :mes AND YEAR(t.createdAt) = :anio")
	Integer countTicketsByMonthAndYear(@Param("mes") Integer mes, @Param("anio") Integer anio);

	@Query("SELECT COUNT(t.ticketId) FROM Ticket t")
	Integer countTickets();

}
