package com.krushit.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushit.model.CitizenRegistrationInputs;
import com.krushit.service.CitizenAppRegistrationServiceImpl;

@RestController
@RequestMapping("CitizenAR-api")
public class CitizenAppRegistrationController {
	@Autowired
	private CitizenAppRegistrationServiceImpl registrationService;

	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(@RequestBody CitizenRegistrationInputs inputs) {
		try {
			Integer appld = registrationService.registerCitizenApplication(inputs);
			return new ResponseEntity<>("Citizen application is registered with ID "+appld, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Citizen must belong to California state", HttpStatus.BAD_REQUEST);
		}
	}
}
