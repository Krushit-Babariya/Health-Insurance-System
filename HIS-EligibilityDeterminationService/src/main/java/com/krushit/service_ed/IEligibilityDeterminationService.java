package com.krushit.service_ed;

import com.krushit.bindings_ed.EligibilityDetailsOutput;

public interface IEligibilityDeterminationService {
	public EligibilityDetailsOutput determineEligibility(Integer caseNo);
}
