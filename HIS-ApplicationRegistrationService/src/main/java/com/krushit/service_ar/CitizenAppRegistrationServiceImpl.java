package com.krushit.service_ar;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.krushit.entity_ar.CitizenRegistrationEntity;
import com.krushit.exception_ar.InvalidSSNException;
import com.krushit.model_ar.CitizenRegistrationInputs;
import com.krushit.repository_ar.ICitizenAppRegistrationRepository;

import reactor.core.publisher.Mono;

@Service
public class CitizenAppRegistrationServiceImpl implements ICitizenAppRegistrationService {

	@Autowired
	private ICitizenAppRegistrationRepository citizenRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient client;

	@Value("${ar.ssa-web.url}")
	private String endpointUrl;

	/*@Value("${ar.state}")
	private List<String> targetStates;*/

	@Value("${ar.state}")
	private String targetState;

	//	private final Map<String, String> map;
	//
	//	public CitizenAppRegistrationServiceImpl(AppConfigProperties config) {
	//	    this.map = config.getMessages();
	//	}

	/*	@Override
		public Integer registerCitizenApplication(CitizenRegistrationInputs inputs) throws InvalidSSNException {
			// Perform WebService call to check whether SSN is valid or not and to get the state name
			ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.GET, null, String.class,
					inputs.getSsn());
			String stateName = response.getBody();
	
			// Perform WebService call to check whether SSN is valid or not and to get the state name using web client
			//String stateName = client.get().uri(endpointUrl, inputs.getSsn()).retrieve().bodyToMono(String.class).block();
	
			// Get state name
			Mono<String> response = client.get().uri(endpointUrl, inputs.getSsn()).retrieve()
					.onStatus(HttpStatus.BAD_REQUEST::equals,
							res -> res.bodyToMono(String.class).map(InvalidSSNException::new))
					.bodyToMono(String.class);
	
			String stateName = response.block();
	
			// Register citizen if they belong to the target state
			//		if (targetStates.stream().anyMatch(state -> state.equalsIgnoreCase(stateName))) {
			if (targetState.equalsIgnoreCase(stateName)) {
				// Prepare the Entity object
				CitizenRegistrationEntity entity = new CitizenRegistrationEntity();
				BeanUtils.copyProperties(inputs, entity);
	
				// Save the object
				Integer applId = citizenRepo.save(entity).getAppId();
				return applId;
			}
	
			throw new InvalidSSNException("Please enter valid SSN");
		}*/

	@Override
	public Integer registerCitizenApplication(CitizenRegistrationInputs inputs) throws InvalidSSNException {
		Mono<String> response = client.get().uri(endpointUrl, inputs.getSsn()).retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals,
						res -> res.bodyToMono(String.class).map(InvalidSSNException::new))
				.bodyToMono(String.class);

		String stateName = response.block();

		if (targetState.equalsIgnoreCase(stateName)) {
			CitizenRegistrationEntity entity = new CitizenRegistrationEntity();
			BeanUtils.copyProperties(inputs, entity);
			Integer applId = citizenRepo.save(entity).getAppId();
			return applId;
		}

		throw new InvalidSSNException("Please enter valid SSN or the scheme is only for California State Citizen");
	}

}
