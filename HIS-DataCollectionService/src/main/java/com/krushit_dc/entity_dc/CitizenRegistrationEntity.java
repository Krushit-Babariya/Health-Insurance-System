package com.krushit_dc.entity_dc;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HIS_CITIZEN_APPLICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenRegistrationEntity {
	@Id
	@SequenceGenerator(name = "citzen_seq_generater", sequenceName = "his_app_id_sequence", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "citzen_seq_generater", strategy = GenerationType.SEQUENCE)
	private Integer appId;

	@Column(length = 20)
	private String firstName;

	@Column(length = 20)
	private String MiddleName;

	@Column(length = 20)
	private String LastName;

	@Column(length = 50)
	private String email;

	@Column(length = 30)
	private String gender;

	private Long phoneNo;

	private Long ssn;

	@Column(length = 30)
	private String stateName;

	private LocalDate dob;

	@Column(length = 30)
	private String createdBy;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate creationDate;

	@Column(length = 30)
	private String updatedBy;

	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

}
