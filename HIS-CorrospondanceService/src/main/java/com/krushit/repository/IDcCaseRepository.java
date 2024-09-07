package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity.DcCaseEntity;

public interface IDcCaseRepository extends JpaRepository<DcCaseEntity, Integer> {
	public Integer findByCaseNo(int caseNo);
}
