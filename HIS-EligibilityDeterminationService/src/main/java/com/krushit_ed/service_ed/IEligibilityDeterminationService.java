package com.krushit_ed.service_ed;

import com.krushit_ed.bindings_ed.EligibilityDetailsOutput;

public interface IEligibilityDeterminationService {
	public EligibilityDetailsOutput determineEligibility(Integer caseNo);
}
