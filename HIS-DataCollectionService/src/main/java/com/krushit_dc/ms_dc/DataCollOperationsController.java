package com.krushit_dc.ms_dc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushit_dc.model_dc.ChildrenInputs;
import com.krushit_dc.model_dc.DcSummaryReport;
import com.krushit_dc.model_dc.EducationInputs;
import com.krushit_dc.model_dc.IncomeInputs;
import com.krushit_dc.model_dc.PlanSelectionsInput;
import com.krushit_dc.service_dc.DataMgmtServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dc-api")
@Tag(name = "dc-api", description = "Data Collection Module Microservices")
public class DataCollOperationsController {

	@Autowired
	private DataMgmtServiceImpl service;

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> displayPlanNames() {
		List<String> planNames = service.showAllPlans();
		return new ResponseEntity<List<String>>(planNames, HttpStatus.OK);
	}

	@PostMapping("/generateCaseNo/{appId}")
	public ResponseEntity<Integer> generateCaseNo(@PathVariable Integer appId) {
		Integer caseNo = service.generataCaseNo(appId);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);
	}

	@PutMapping("/updatePlanSelection")
	public ResponseEntity<Integer> savePlanSelection(@RequestBody PlanSelectionsInput planInput) {
		Integer caseNo = service.savePlanSelection(planInput);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);

	}

	@PostMapping("/saveIncome")
	public ResponseEntity<Integer> saveIncomeDetails(@RequestBody IncomeInputs incomeInput) {
		Integer caseNo = service.saveIncomeDetails(incomeInput);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.CREATED);
	}

	@PostMapping("/saveEducation")
	public ResponseEntity<Integer> saveEducationDetails(@RequestBody EducationInputs eduInput) {
		Integer caseNo = service.saveEducationDetails(eduInput);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.CREATED);
	}
	
	@PostMapping("/saveChildren")
	public ResponseEntity<Integer> saveChildrenDetails(@RequestBody List<ChildrenInputs> childInputsList) {
		Integer caseNo = service.saveChildrenDetails(childInputsList);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.CREATED);
	}
	
	@GetMapping("/dcSummary/{caseNo}")
    public ResponseEntity<DcSummaryReport> showDCSummary(@PathVariable Integer caseNo) {
        DcSummaryReport summaryReport = service.showDCSummary(caseNo);
        return new ResponseEntity<>(summaryReport, HttpStatus.OK);
    }

}
