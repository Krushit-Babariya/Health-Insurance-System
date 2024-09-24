package com.krushit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "HIS_ED_ELIGIBILITY_DETAILS")
@Data
@Entity
public class EligibilityDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edTraceId;
	private Integer caseNo;
	@Column(length = 50)
	private String holderName;
	private Long holderSSN;
	@Column(length = 50)
	private String planName;
	@Column(length = 50)
	private String planStatus;

	private LocalDateTime planStartDate;
	private LocalDateTime planEndDate;

	private Double benifitAmt;
	@Column(length = 60)
	private String denialReason;
	
	@Column(length = 100)
	private String bankAccHolderName;

	private Integer bankAccNo;
	@Column(length = 30)
	private String bankName;
}
