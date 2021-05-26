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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assessment")
public class Assessment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assessment_id")
	private Long assessmentId;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = true)
	private AssessmentType assessment_type;

	@Column(length = 255, nullable = false, unique = true)
	private String token;

	@Column(length = 255, nullable = true)
	private String observation;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private Instant createdAt;

	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = false)
	private Ticket ticket;
}
