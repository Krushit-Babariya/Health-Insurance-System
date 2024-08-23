package com.krushit.service_dc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushit.entity_dc.CitizenRegistrationEntity;
import com.krushit.entity_dc.DcCaseEntity;
import com.krushit.entity_dc.DcChildrenEntity;
import com.krushit.entity_dc.DcEducationEntity;
import com.krushit.entity_dc.DcIncomeEntity;
import com.krushit.entity_dc.PlanEntity;
import com.krushit.model_dc.ChildrenInputs;
import com.krushit.model_dc.CitizenRegistrationInputs;
import com.krushit.model_dc.DcSummaryReport;
import com.krushit.model_dc.EducationInputs;
import com.krushit.model_dc.IncomeInputs;
import com.krushit.model_dc.PlanSelectionsInput;
import com.krushit.repository_dc.ICitizenAppRegistrationRepository;
import com.krushit.repository_dc.IDcCaseRepository;
import com.krushit.repository_dc.IDcChildrenRepository;
import com.krushit.repository_dc.IDcEducationRepository;
import com.krushit.repository_dc.IDcIncomeRepository;
import com.krushit.repository_dc.IPlanRepository;

@Service
public class DataMgmtServiceImpl implements IDataMgmtService {

	@Autowired
	private IDcCaseRepository caseRepo;
	@Autowired
	private ICitizenAppRegistrationRepository citizenRepo;
	@Autowired
	private IPlanRepository planRepo;
	@Autowired
	private IDcIncomeRepository incomeRepo;
	@Autowired
	private IDcEducationRepository eduRepo;
	@Autowired
	private IDcChildrenRepository childRepo;

	@Override
	public Integer generataCaseNo(Integer appId) {
		Optional<CitizenRegistrationEntity> opt = citizenRepo.findById(appId);
		if (opt.isPresent()) {
			DcCaseEntity caseEntity = new DcCaseEntity();
			caseEntity.setAppId(appId);
			return caseRepo.save(caseEntity).getCaseNo();
		}
		return 0;
	}

	@Override
	public List<String> showAllPlans() {
		List<PlanEntity> list = planRepo.findAll();
		List<String> planList = list.stream().map(p -> p.getPlanName()).collect(Collectors.toList());
		return planList;
	}

	@Override
	public Integer savePlanSelection(PlanSelectionsInput planInput) {
		Optional<DcCaseEntity> opt = caseRepo.findById(planInput.getCaseNo());
		if (opt.isPresent()) {
			DcCaseEntity caseEntity = opt.get();
			caseEntity.setPlanId(planInput.getPlanId());

			return caseRepo.save(caseEntity).getCaseNo();
		}
		return 0;
	}

	@Override
	public Integer saveIncomeDetails(IncomeInputs incomeInput) {
		DcIncomeEntity incomeEntity = new DcIncomeEntity();
		BeanUtils.copyProperties(incomeInput, incomeEntity);
		incomeRepo.save(incomeEntity);
		return incomeInput.getCaseNo();
	}

	@Override
	public Integer saveEducationDetails(EducationInputs eduInputs) {
		DcEducationEntity eduEntity = new DcEducationEntity();
		BeanUtils.copyProperties(eduInputs, eduEntity);
		eduRepo.save(eduEntity);
		return eduInputs.getCaseNo();
	}

	@Override
	public Integer saveChildrenDetails(List<ChildrenInputs> childInputs) {
		childInputs.forEach(child -> {
			DcChildrenEntity childEntity = new DcChildrenEntity();
			BeanUtils.copyProperties(child, childEntity);
			childRepo.save(childEntity);
		});
		// all childs have same case no
		return childInputs.get(0).getCaseNo();
	}

	@Override
	public DcSummaryReport showDCSummary(Integer caseNo) {
		DcIncomeEntity incomeEntity = incomeRepo.findByCaseNo(caseNo);
		DcEducationEntity eduEntity = eduRepo.findByCaseNo(caseNo);
		List<DcChildrenEntity> childEntityList = childRepo.findByCaseNo(caseNo);

		String planName = null;
		Integer appId = null;
		Optional<DcCaseEntity> opt = caseRepo.findById(caseNo);
		if (opt.isPresent()) {
			Integer planId = opt.get().getPlanId();
			appId = opt.get().getAppId();

			Optional<PlanEntity> optPlanEntity = planRepo.findById(planId);
			if (optPlanEntity.isPresent()) {
				planName = optPlanEntity.get().getPlanName();
			}
		}

		Optional<CitizenRegistrationEntity> optCitizenEntity = citizenRepo.findById(appId);
		CitizenRegistrationEntity citizenEntity = null;
		if (optCitizenEntity.isPresent()) {
			citizenEntity = optCitizenEntity.get();
		}

		IncomeInputs income = new IncomeInputs();
		BeanUtils.copyProperties(incomeEntity, income);

		List<ChildrenInputs> child = new ArrayList<>();
		childEntityList.forEach(c -> {
			ChildrenInputs children = new ChildrenInputs();
			BeanUtils.copyProperties(c, children);
			child.add(children);
		});

		EducationInputs edu = new EducationInputs();
		BeanUtils.copyProperties(eduEntity, edu);

		CitizenRegistrationInputs citizen = new CitizenRegistrationInputs();
		BeanUtils.copyProperties(citizenEntity, citizen);

		DcSummaryReport report = new DcSummaryReport();
		report.setChildInputs(child);
		report.setEduInputs(edu);
		report.setIncomeInputs(income);
		report.setPlanName(planName);
		report.setCitizenInputs(citizen);

		return report;
	}
}
