package com.krushit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HisBanificialAmountIssueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HisBanificialAmountIssueServiceApplication.class, args);
	}

}
