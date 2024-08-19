package com.krushit.entity_dc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_INSAURENCE_PLANS")
public class PlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	@Column(length = 30)
	private String planName;
	@Column(length = 100)
	private String planDescription;
	@Column(length = 10)
	private String planMembership;
	private LocalDate startDate;
	private LocalDate endDate;
	private String avtive_sw;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updationDate;

	@Column(length = 100)
	private String createdBy;
	@Column(length = 100)
	private String updatesBy;
}
