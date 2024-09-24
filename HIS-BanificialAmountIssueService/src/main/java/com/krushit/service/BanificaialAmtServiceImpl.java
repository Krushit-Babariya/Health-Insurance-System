package com.krushit.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanificaialAmtServiceImpl implements IBanificialAmtService {

	@Autowired
	private JobLauncher launcher;

	@Autowired
	private Job job;

	@Override
	public JobExecution sendAmount() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// prapare job parameter
		JobParameter<Date> param = new JobParameter<Date>(new Date(0), Date.class);
		Map<String, JobParameter<?>> map = new HashMap<>();
		JobParameters params = new JobParameters(map);
		JobExecution execution = launcher.run(job, params);
		System.out.println("Job Execution Status :: " + execution.getExitStatus());
		return execution;
	}
}
