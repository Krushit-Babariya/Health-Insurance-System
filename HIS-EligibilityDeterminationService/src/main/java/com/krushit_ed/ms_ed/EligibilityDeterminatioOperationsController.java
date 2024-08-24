package com.krushit_ed.ms_ed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushit_ed.bindings_ed.EligibilityDetailsOutput;
import com.krushit_ed.service_ed.EligibilityDeterminationServiceImpl;

@RestController
@RequestMapping("/ed-api")
public class EligibilityDeterminatioOperationsController {

	@Autowired
	private EligibilityDeterminationServiceImpl service;

	@GetMapping("/determine/{caseno}")
	public ResponseEntity<EligibilityDetailsOutput> checkPlanEligibility(@PathVariable Integer caseno) {
		EligibilityDetailsOutput output = service.determineEligibility(caseno);
		return new ResponseEntity<EligibilityDetailsOutput>(output, HttpStatus.OK);
	}

}
