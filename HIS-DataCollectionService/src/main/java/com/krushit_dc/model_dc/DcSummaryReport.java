package com.krushit_dc.model_dc;

import java.util.List;

import lombok.Data;

@Data
public class DcSummaryReport {
	private String planName;
	private EducationInputs eduInputs;
	private IncomeInputs incomeInputs;
	private List<ChildrenInputs> childInputs;
	private CitizenRegistrationInputs citizenInputs;
}
