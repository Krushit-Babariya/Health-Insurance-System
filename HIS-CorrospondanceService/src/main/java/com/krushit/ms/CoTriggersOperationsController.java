package com.krushit.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushit.model.CoSummary;
import com.krushit.service.ICorrospondanceMgmtService;

@RestController
@RequestMapping("/co-triggers-api")
public class CoTriggersOperationsController {

	@Autowired
	private ICorrospondanceMgmtService service;

	@GetMapping("/process")
	public ResponseEntity<CoSummary> processAndUpdateTriggers() throws Exception {
		return new ResponseEntity<CoSummary>(service.processPendingTriggers(), HttpStatus.OK);
	}
}
