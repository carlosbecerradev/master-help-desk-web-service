package com.ws.masterhelpdesk.model.repository;

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
}
