package com.krushit_dc.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_dc.entity_dc.DcEducationEntity;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity, Integer> {
	public DcEducationEntity findByCaseNo(int caseNo);
}
