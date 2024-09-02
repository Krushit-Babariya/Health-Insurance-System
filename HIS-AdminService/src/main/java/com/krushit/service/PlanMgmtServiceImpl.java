package com.krushit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushit.config.AppConfigProperties;
import com.krushit.constatnts.PlanConstants;
import com.krushit.entity.PlanCategory;
import com.krushit.entity.PlanEntity;
import com.krushit.model.CategoryData;
import com.krushit.model.PlanData;
import com.krushit.repository.IPlanCategoryRepository;
import com.krushit.repository.IPlanRepository;

@Service
public class PlanMgmtServiceImpl implements IPlanMgmtService {

	@Autowired
	private IPlanRepository planRepo;

	@Autowired
	private IPlanCategoryRepository planCategoryRepo;

	private Map<String, String> map;

	public PlanMgmtServiceImpl(AppConfigProperties config) {
		map = config.getMessages();
	}

	@Override
	public String registerPlan(PlanData plan) {
		PlanEntity entity = new PlanEntity();
		BeanUtils.copyProperties(plan, entity);
		PlanEntity savedEntity = planRepo.save(entity);
		return savedEntity != null ? map.get(PlanConstants.SAVE_SUCCESS) + savedEntity.getPlanId()
				: map.get(PlanConstants.SAVE_FALIURE);
	}
	
	@Override
	public String registerPlanCategory(CategoryData plan) {
		PlanCategory entity = new PlanCategory();
		BeanUtils.copyProperties(plan, entity);
		PlanCategory savedEntity = planCategoryRepo.save(entity);
		return savedEntity != null ? map.get(PlanConstants.SAVE_SUCCESS) + savedEntity.getCategory_id()
				: map.get(PlanConstants.SAVE_FALIURE);
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
	public List<PlanData> showAllPlans() {
		List<PlanEntity> listEntity = planRepo.findAll();
		List<PlanData> listData = new ArrayList<PlanData>();
		
		listEntity.forEach(entity -> {
			PlanData data = new PlanData();
			BeanUtils.copyProperties(entity, data);
			listData.add(data);
		});
		return listData;
	}

	@Override
	public PlanEntity showTravelPlanByID(Integer planID) {
		return planRepo.findById(planID)
				.orElseThrow(() -> new IllegalArgumentException(map.get(PlanConstants.FIND_BY_ID_FAILURE)));
	}

	@Override
	public String updatePlan(PlanData plan) {
		PlanEntity entity = new PlanEntity();
		BeanUtils.copyProperties(plan, entity);
		
	    Optional<PlanEntity> existingPlan = planRepo.findById(entity.getPlanId());
	    if (existingPlan.isPresent()) {
	        planRepo.save(entity);
	        return map.get(PlanConstants.UPDATE_SUCCESS) + plan.getPlanId();
	    } else {
	        return map.get(PlanConstants.UPDATE_FAILURE);
	    }
	}

	@Override
	public String deletePlan(Integer planID) {
	    Optional<PlanEntity> existingPlan = planRepo.findById(planID);
	    if (existingPlan.isPresent()) {
	        planRepo.deleteById(planID);
	        return map.get(PlanConstants.UPDATE_SUCCESS);
	    } else {
	        return map.get(PlanConstants.UPDATE_FAILURE);
	    }
	}

	@Override
	public String changePlanStatus(Integer planID, String status) {
		Optional<PlanEntity> opt = planRepo.findById(planID);
	    if (opt.isPresent()) {
	    	PlanEntity plan = opt.get();
	    	if(plan.getAvtive_sw().equalsIgnoreCase(status)) {
	    		return map.get("Plan Status is already ") + status;
	    	}
	    	plan.setAvtive_sw(status);
	    	planRepo.save(plan);
	        return map.get(PlanConstants.STATUS_CHANGE_SUCCESS) + planID;
	    } else {
	        return map.get(PlanConstants.STATUS_CHANGE_FAILURE);
	    }
	}

}
