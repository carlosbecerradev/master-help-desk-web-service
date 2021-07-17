package com.ws.masterhelpdesk.model.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.CustomerRequest;

@Repository
public interface ICustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {

	List<CustomerRequest> findByEnabled(Boolean enabled);;

	@Query("SELECT COUNT(cr.customerRequestId) FROM CustomerRequest cr WHERE cr.createdAt BETWEEN :start AND :end")
	Integer countCustomerRequestBetweenTodayAndTomorrow(@Param("start") Instant start, @Param("end") Instant end);

	@Query("select COUNT(cr.customerRequestId) FROM CustomerRequest cr WHERE MONTH(cr.createdAt) = :mes AND YEAR(cr.createdAt) = :anio")
	Integer countCustomerRequestByMonth(@Param("mes") Integer mes, @Param("anio") Integer anio);

	@Query("SELECT COUNT(cr.customerRequestId) FROM CustomerRequest cr WHERE cr.requestType.name = :requestTypeName")
	Integer countCustomerRequestByRequestTypeName(@Param("requestTypeName") String requestTypeName);
}
