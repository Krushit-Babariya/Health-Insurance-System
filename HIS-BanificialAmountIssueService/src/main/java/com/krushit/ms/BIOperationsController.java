package com.krushit.ms;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krushit.service.BanificaialAmtServiceImpl;

@RestController
@RequestMapping("/bi-api")
public class BIOperationsController {

	@Autowired
	private BanificaialAmtServiceImpl service;

	@GetMapping("/sendAmount")
	public ResponseEntity<?> sendAmount() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobExecution exe = service.sendAmount();
		return new ResponseEntity<String>(exe.getExitStatus().toString(), HttpStatus.OK);
	}
}
