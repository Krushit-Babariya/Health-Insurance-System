package com.krushit.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.krushit.entity.EligibilityDetailsEntity;
import com.krushit.model.EligibilityDetails;
import com.krushit.processor.EDDetailsProcessor;
import com.krushit.repository.IEligibilityDeterminationRepository;

@Component
public class BatchConfig {

	@Autowired
	private IEligibilityDeterminationRepository elgiRepo;

	@Autowired
	private EDDetailsProcessor processor;

	/*    // Reader
	@Bean(name = "reader")
	public RepositoryItemReader<EligibilityDetailsEntity> creatReader() {
	    return new RepositoryItemReaderBuilder<EligibilityDetailsEntity>()
	            .repository(elgiRepo)
	            .methodName("findAll")
	            .build();
	}*/

	@Bean(name = "reader")
	public RepositoryItemReader<EligibilityDetailsEntity> creatReader() {
		// Define sorting parameters
		Map<String, Sort.Direction> sorts = new HashMap<>();
		sorts.put("caseNo", Sort.Direction.ASC); // Sort by 'caseNo' in ascending order

		return new RepositoryItemReaderBuilder<EligibilityDetailsEntity>().name("eligibilityDetailsReader") // Set a unique name for the reader
				.repository(elgiRepo).methodName("findAll").sorts(sorts) // Add sorts map
				.saveState(true) // Save state between job executions
				.build();
	}

	// Writer
	@Bean(name = "writer")
	public FlatFileItemWriter<EligibilityDetails> createWriter() {
		return new FlatFileItemWriterBuilder<EligibilityDetails>().name("file-writer")
				.resource(new FileSystemResource("beneficial_list.csv")).lineSeparator("\r\n").delimited()
				.delimiter(",").names("caseNo", "holderName", "holderSSN", "planName", "planStatus", "planStartDate",
						"planEndDate", "benifitAmt", "denialReason", "bankAccHolderName", "bankAccNo", "bankName")
				.build();
	}

	// Step
	@Bean(name = "step1")
	public Step createStep1(JobRepository jobRepo, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepo)
				.<EligibilityDetailsEntity, EligibilityDetails>chunk(3, transactionManager).reader(creatReader())
				.processor(processor).writer(createWriter()).build();
	}

	// Job
	@Bean(name = "job1")
	public Job createJob1(JobRepository jobRepo, Step step1) {
		return new JobBuilder("job1", jobRepo).incrementer(new RunIdIncrementer()).start(step1).build();
	}
}
