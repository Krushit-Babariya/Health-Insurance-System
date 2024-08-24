package com.krushit_dc.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_dc.entity_dc.DcCaseEntity;

public interface IDcCaseRepository extends JpaRepository<DcCaseEntity, Integer> {
	public Integer findByCaseNo(int caseNo);
}
