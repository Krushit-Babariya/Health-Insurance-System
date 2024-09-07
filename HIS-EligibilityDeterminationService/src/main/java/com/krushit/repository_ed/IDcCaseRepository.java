package com.krushit.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_ed.DcCaseEntity;

public interface IDcCaseRepository extends JpaRepository<DcCaseEntity, Integer> {
	public Integer findByCaseNo(int caseNo);
}
