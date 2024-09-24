package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity.EligibilityDetailsEntity;

public interface IEligibilityDeterminationRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {
	public boolean existsByCaseNoAndPlanName(Integer caseNo, String planName);
}
