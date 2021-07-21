package com.ws.masterhelpdesk.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ws.masterhelpdesk.model.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	@Query("SELECT COUNT(u.userId) FROM User u WHERE u.authority = 'TECNICO'")
	Integer countTecnicos();
}
