package com.krushit.service;

import java.util.List;
import java.util.Map;

import com.krushit.entity.PlanEntity;
import com.krushit.model.PlanData;

public interface IPlanMgmtService {
	
	public String registerPlan(PlanData plan);
	public Map<Integer, String> getPlanCategories();
	public List<PlanEntity> showAllTravelPlan();
	public PlanEntity showTravelPlanByID(Integer planID);
	public String updateTravelPlan(PlanEntity plan);
	public String deleteTravelPlan(Integer planID);
	public String changeTravelPlanStatus(Integer planID, String status);
}
