package com.ws.masterhelpdesk.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.RequestType;

@Repository
public interface IRequestTypeRepository extends JpaRepository<RequestType, Long> {

	@Query("select rt.name from RequestType rt where rt.enabled = true")
	List<String> findEnabledNames();
	
	Optional<RequestType> findByName(String name);
}
