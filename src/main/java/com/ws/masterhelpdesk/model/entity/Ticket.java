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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;

	@Column(name = "estimated_time", nullable = true, updatable = false, columnDefinition = "TIMESTAMP")
	private Instant estimatedTime;

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private Instant createdAt;

	@Column(name = "finished_at", nullable = true, updatable = false, columnDefinition = "TIMESTAMP")
	private Instant finishedAt;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private TicketStatus ticketStatus;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@OneToOne
	@JoinColumn(name = "customer_request_id", nullable = false)
	private CustomerRequest customerRequest;
}
