package com.krushit.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_ed.EligibilityDetailsEntity;

public interface IEligibilityDeterminationRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {
	public boolean existsByCaseNoAndPlanName(Integer caseNo, String planName);
}
