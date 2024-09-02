package com.krushit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.krushit.config.AppConfigProperties;
import com.krushit.constatnts.PlanConstants;
import com.krushit.entity.PlanCategory;
import com.krushit.entity.PlanEntity;
import com.krushit.model.PlanData;
import com.krushit.repository.IPlanRepository;

public class PlanMgmtServiceImpl implements IPlanMgmtService{
	
	@Autowired
	private IPlanRepository planRepo;
	
	@Autowired
	private IPlanRepository planCategoryRepo;
	
	private Map<String, String> map;
	
	public PlanMgmtServiceImpl(AppConfigProperties config) {
		map = config.getMessages();
	}

	@Override
	public String registerPlan(PlanData plan) {
		PlanEntity entity = new PlanEntity();
		BeanUtils.copyProperties(plan, entity);
		PlanEntity savedEntity = planRepo.save(entity);
		return savedEntity != null ? map.get(PlanConstants.SAVE_SUCCESS) + savedEntity.getPlanId() : map.get(PlanConstants.SAVE_FALIURE);
	}

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> lst = planCategoryRepo.findAll();
		Map<Integer, String> hm = new HashMap<>();
		lst.forEach(category -> {
			hm.put(category.getCategory_id(), category.getCategory_name());
		});

		return hm;
	}

	@Override
	public List<PlanEntity> showAllTravelPlan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanEntity showTravelPlanByID(Integer planID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateTravelPlan(PlanEntity plan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteTravelPlan(Integer planID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeTravelPlanStatus(Integer planID, String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
