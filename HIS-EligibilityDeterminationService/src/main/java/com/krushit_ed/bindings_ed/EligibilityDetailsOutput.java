package com.krushit_ed.bindings_ed;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EligibilityDetailsOutput {

	private String holderName;
	private Long holderSSN;
	private String planName;
	private String planStatus;

	private LocalDateTime planStartDate;
	private LocalDateTime planEndDate;

	private Double benifitAmt;
	private String denialReason;
}
 