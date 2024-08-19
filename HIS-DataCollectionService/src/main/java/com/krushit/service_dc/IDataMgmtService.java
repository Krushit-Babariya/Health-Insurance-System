package com.krushit.service_dc;

import java.util.List;

import com.krushit.modle_dc.ChildrenInputs;
import com.krushit.modle_dc.DcSummaryReport;
import com.krushit.modle_dc.EducationInputs;
import com.krushit.modle_dc.IncomeInputs;
import com.krushit.modle_dc.PlanSelectionsInput;

public interface IDataMgmtService {
	public Integer generataCaseNo(Integer appId);

	public List<String> showAllPlans();

	public Integer savePlanSelection(PlanSelectionsInput planInput);

	public Integer saveIncomeDetails(IncomeInputs incomeInput);

	public Integer saveEducationDetails(EducationInputs eduInputs);

	public DcSummaryReport showDCSummary(Integer caseNo);

	public Integer saveChildrenDetails(List<ChildrenInputs> childInputs);
}
