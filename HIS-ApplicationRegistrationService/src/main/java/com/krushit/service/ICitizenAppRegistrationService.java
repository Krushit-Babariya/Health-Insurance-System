package com.krushit.service;

import com.krushit.exception.InvalidSSNException;
import com.krushit.model.CitizenRegistrationInputs;

public interface ICitizenAppRegistrationService {
	public Integer registerCitizenApplication(CitizenRegistrationInputs inputs) throws InvalidSSNException;
}
