package com.ws.masterhelpdesk.model.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 50, nullable = false)
	private String surname;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private EmployeeStatus status;

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private Instant createdAt;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
