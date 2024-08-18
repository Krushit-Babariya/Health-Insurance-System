package com.krushit.module_dc;

import lombok.Data;

@Data
public class DcSummaryReport {
	private PlanSelectionsInput planInputs;
	private EducationInputs eduInputs;
	private IncomeInputs incomeInputs;
	private ChildrenInputs childInputs;
}
