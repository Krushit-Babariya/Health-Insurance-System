package com.krushit.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_dc.DcEducationEntity;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity, Integer> {
	public DcEducationEntity findByCaseNo(int caseNo);
}
