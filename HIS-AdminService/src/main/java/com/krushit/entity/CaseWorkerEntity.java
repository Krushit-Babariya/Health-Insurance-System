package com.krushit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_CASEWORKER")
public class CaseWorkerEntity {
	@Id
	@SequenceGenerator(name = "worker_gen", sequenceName = "case_worker_seq", initialValue = 654810, allocationSize = 3)
	@GeneratedValue(generator = "worker_gen", strategy = GenerationType.SEQUENCE)
	private Integer accountId;
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 20)
	private String middleName;
	@Column(length = 300)
	private String email;
	@Column(length = 8)
	private String password;
	@Column(length = 10)
	private String gender;
	@Column(length = 9)
	private Long ssn;
	private LocalDate birthDate;
	@Column(length = 30)
	private String province;
	@Column(length = 20)
	private String officeName;
	@Column(length = 40)
	private String officeAddress;

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
