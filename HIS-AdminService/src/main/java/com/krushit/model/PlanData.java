package com.krushit.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanData {
	private Integer planId;
	private String planName;
	private String planFullForm;
	private String planDescription;
	private String planMembership;
	private LocalDate startDate;
	private LocalDate endDate;
}
