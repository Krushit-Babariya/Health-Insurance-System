package com.krushit.ms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushit.model.CaseWorker;
import com.krushit.service.ICaseWorkerService;

@RestController
@RequestMapping("/caseworkers-api")
public class CaseWorkerController {

	@Autowired
	private ICaseWorkerService iCaseWorkerService;

	@PostMapping("/add-worker")
	public ResponseEntity<?> addCaseWorker(@RequestBody CaseWorker caseWorkerDTO) {
		String msg = iCaseWorkerService.addCaseWorker(caseWorkerDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/get-all-workers")
	public ResponseEntity<List<CaseWorker>> viewAllCaseWorkers() {
		List<CaseWorker> caseWorkers = iCaseWorkerService.getAllCaseWorkers();
		return new ResponseEntity<>(caseWorkers, HttpStatus.OK);
	}
}