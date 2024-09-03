package com.krushit.service;

import java.util.List;
import java.util.Map;

import com.krushit.entity.PlanEntity;
import com.krushit.model.CaseWorker;
import com.krushit.model.CategoryData;
import com.krushit.model.PlanData;

public interface IPlanMgmtService {
	public String registerPlan(PlanData plan);
	public Map<Integer, String> getPlanCategories();
	public List<PlanData> showAllPlans();
	public PlanEntity showTravelPlanByID(Integer planID);
	public String updatePlan(PlanData plan);
	public String deletePlan(Integer planID);
	public String changePlanStatus(Integer planID, String status);
	String registerPlanCategory(CategoryData plan);
}
