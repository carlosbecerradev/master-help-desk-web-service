package com.ws.masterhelpdesk.model.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "customer_request")
public class CustomerRequest implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_request_id")
	private Long customerRequestId;

	@Column(length = 255, nullable = false)
	private String description;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private Instant createdAt;

	@ManyToOne
	@JoinColumn(name = "request_type_id", nullable = false)
	private RequestType requestType;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
}
