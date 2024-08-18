package com.krushit.service_ar;

import com.krushit.exception_ar.InvalidSSNException;
import com.krushit.model_ar.CitizenRegistrationInputs;

public interface ICitizenAppRegistrationService {
	public Integer registerCitizenApplication(CitizenRegistrationInputs inputs) throws InvalidSSNException;
}
