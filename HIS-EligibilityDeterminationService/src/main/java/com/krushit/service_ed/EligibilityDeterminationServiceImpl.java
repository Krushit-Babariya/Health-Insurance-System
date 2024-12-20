package com.krushit.service_ed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushit.bindings_ed.EligibilityDetailsOutput;
import com.krushit.entity_ed.CitizenRegistrationEntity;
import com.krushit.entity_ed.CoTriggersEntity;
import com.krushit.entity_ed.DcCaseEntity;
import com.krushit.entity_ed.DcChildrenEntity;
import com.krushit.entity_ed.DcEducationEntity;
import com.krushit.entity_ed.DcIncomeEntity;
import com.krushit.entity_ed.EligibilityDetailsEntity;
import com.krushit.entity_ed.PlanEntity;
import com.krushit.exception_ed.PlanAlreadyAppliedException;
import com.krushit.repository_ed.ICitizenAppRegistrationRepository;
import com.krushit.repository_ed.ICoTriggerRepository;
import com.krushit.repository_ed.IDcCaseRepository;
import com.krushit.repository_ed.IDcChildrenRepository;
import com.krushit.repository_ed.IDcEducationRepository;
import com.krushit.repository_ed.IDcIncomeRepository;
import com.krushit.repository_ed.IEligibilityDeterminationRepository;
import com.krushit.repository_ed.IPlanRepository;

@Service
public class EligibilityDeterminationServiceImpl implements IEligibilityDeterminationService {

	@Autowired
	private IDcCaseRepository caseRepo;

	@Autowired
	private ICitizenAppRegistrationRepository citizenRepo;

	@Autowired
	private IPlanRepository planRepo;

	@Autowired
	private IDcIncomeRepository incomeRepo;

	@Autowired
	private IDcChildrenRepository childRepo;

	@Autowired
	private IDcEducationRepository eduRepo;

	@Autowired
	private IEligibilityDeterminationRepository edRepo;

	@Autowired
	private ICoTriggerRepository triggerRepo;

	@Override
	public EligibilityDetailsOutput determineEligibility(Integer caseNo) {
		//get planid and appid
		Integer appId = null;
		Integer planId = null;

		Optional<DcCaseEntity> opt = caseRepo.findById(caseNo);

		if (opt.isPresent()) {
			DcCaseEntity entity = opt.get();
			appId = entity.getAppId();
			planId = entity.getPlanId();
		}
		
		//get Citizen datails
		String citizenName = null;
		Long citizenSSN = null;
		Optional<CitizenRegistrationEntity> citizenOpt = citizenRepo.findById(appId);
		if (citizenOpt.isPresent()) {
			CitizenRegistrationEntity entity = citizenOpt.get();
			citizenName = entity.getFirstName() + " " + entity.getLastName();
			citizenSSN = entity.getSsn();

		}

		//get plan name
		String planName = null;
		Optional<PlanEntity> optPlan = planRepo.findById(planId);
		if (optPlan.isPresent()) {
			PlanEntity entity = optPlan.get();
			planName = entity.getPlanName();
		}

		EligibilityDetailsOutput eligOutput = applyPlanCondition(caseNo, planName);

		//set citizen name
		eligOutput.setHolderName(citizenName);
		eligOutput.setHolderSSN(citizenSSN);

		//convert to entity object
		EligibilityDetailsEntity entity = new EligibilityDetailsEntity();
		BeanUtils.copyProperties(eligOutput, entity);
		entity.setCaseNo(caseNo);
		
		if(edRepo.existsByCaseNoAndPlanName(caseNo, planName)) {
			throw new PlanAlreadyAppliedException("Citizen has already applied for this plan.");
		} else {			
			edRepo.save(entity);
		}

		//save triggers
		CoTriggersEntity triggerEntity = new CoTriggersEntity();
		triggerEntity.setCaseNo(caseNo);

		triggerRepo.save(triggerEntity);

		return eligOutput;
	}

	private EligibilityDetailsOutput applyPlanCondition(Integer caseNo, String planName) {
		EligibilityDetailsOutput eligOutput = new EligibilityDetailsOutput();
		eligOutput.setPlanName(planName);

		// Fetch income details based on caseNo
		DcIncomeEntity incomeEntity = incomeRepo.findByCaseNo(caseNo);
		Double empIncome = incomeEntity.getEmpIncome();
		Double propertyIncome = incomeEntity.getPropertyIncome();

		// Fetch children details if needed
		List<DcChildrenEntity> listChilds = childRepo.findByCaseNo(caseNo);

		// Determine eligibility based on the plan name
		switch (planName.toLowerCase()) {
		case "snap":
			System.out.println("Emp Income :: " + empIncome + ", Case NO :: " + caseNo);
			if (empIncome != null && empIncome <= 300) {
				System.out.println("Emp Income :: " + empIncome);
				approvePlan(eligOutput, 200.0);
			} else {
				denyPlan(eligOutput, "Income should be less than $300");
			}
			break;

		case "ccap":
			int eligibleChildrenCount = 0;

			for (DcChildrenEntity child : listChilds) {
				int age = Period.between(child.getChildDOB(), LocalDate.now()).getYears();
				if (age <= 16) {
					eligibleChildrenCount++;
				}
			}

			if (empIncome != null && empIncome <= 300 && eligibleChildrenCount > 0 && listChilds.size() > 0) {
				double benefitAmount = eligibleChildrenCount * 150.0;
				approvePlan(eligOutput, benefitAmount);
			} else {
				denyPlan(eligOutput,
						"Income should be less than $300 and there should be at least one child under 16 years old");
			}

		case "medaid":
			if (empIncome != null && empIncome <= 300 && propertyIncome != null && propertyIncome == 0) {
				approvePlan(eligOutput, 300.0);
			} else {
				denyPlan(eligOutput, "Income should be less than $300 and property income should be $0");
			}
			break;

		case "medcare":
			Optional<CitizenRegistrationEntity> citizenOpt = citizenRepo.findById(caseNo);
			if (citizenOpt.isPresent()) {
				LocalDate dob = citizenOpt.get().getDob();
				int age = Period.between(dob, LocalDate.now()).getYears();
				if (age >= 65) {
					approvePlan(eligOutput, 300.0);
				} else {
					denyPlan(eligOutput, "Age should be 65 or above");
				}
			}
			break;

		case "cajw":
			DcEducationEntity eduEntity = eduRepo.findByCaseNo(caseNo);
			int passoutYear = eduEntity.getPassOutYear();

			if (empIncome == 0 && passoutYear < LocalDate.now().getYear()) {
				approvePlan(eligOutput, 300.0);
			} else {
				denyPlan(eligOutput, "Citizen should be jobless and graduated");
			}

			break;

		case "qhp":
			approvePlan(eligOutput, 300.0);
			break;

		default:
			denyPlan(eligOutput, "Unknown plan name");
			break;
		}

		return eligOutput;

	}

	private void approvePlan(EligibilityDetailsOutput eligOutput, double benefitAmount) {
		eligOutput.setPlanStatus("Approved");
		eligOutput.setBenifitAmt(benefitAmount);
		eligOutput.setPlanStartDate(LocalDateTime.now());
		eligOutput.setPlanEndDate(LocalDateTime.now().plusYears(5));
	}

	private void denyPlan(EligibilityDetailsOutput eligOutput, String denialReason) {
		eligOutput.setPlanStatus("Denial");
		eligOutput.setDenialReason(denialReason);
	}

}
