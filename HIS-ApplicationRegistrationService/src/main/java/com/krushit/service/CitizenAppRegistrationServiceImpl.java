package com.krushit.service;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.krushit.config.AppConfigProperties;
import com.krushit.constants.UserConstants;
import com.krushit.entity.CitizenRegistrationEntity;
import com.krushit.model.CitizenRegistrationInputs;
import com.krushit.repository.ICitizenAppRegistrationRepository;

@Service
public class CitizenAppRegistrationServiceImpl implements ICitizenAppRegistrationService {

	@Autowired
	private ICitizenAppRegistrationRepository citizenRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${ar.ssa-web.url}")
	private String endpointUrl;

	@Value("${ar.state}")
	private String targetState;
	
//	private final Map<String, String> map;
//
//	public CitizenAppRegistrationServiceImpl(AppConfigProperties config) {
//	    this.map = config.getMessages();
//	}

	@Override
	public Integer registerCitizenApplication(CitizenRegistrationInputs inputs) {
		// Perform WebService call to check whether SSN is valid or not and to get the state name
		ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.GET, null, String.class,
				inputs.getSsn());

		// Get state name
		String stateName = response.getBody();

		// Register citizen if they belong to the target state
		if (stateName.equalsIgnoreCase(targetState)) {
			// Prepare the Entity object
			CitizenRegistrationEntity entity = new CitizenRegistrationEntity();
			BeanUtils.copyProperties(inputs, entity);

			// Save the object
			Integer applId = citizenRepo.save(entity).getAppId();
			return applId;
		}

		return 0;
	}
}
