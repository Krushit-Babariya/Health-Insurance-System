package com.krushit.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EligibilityDetails {
	private Integer caseNo;
	private String holderName;
	private Long holderSSN;
	private String planName;
	private String planStatus;

	private LocalDateTime planStartDate;
	private LocalDateTime planEndDate;

	private Double benifitAmt;
	private String denialReason;
	private String bankAccHolderName;
	private Integer bankAccNo;
	private String bankName;
}
