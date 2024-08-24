package com.krushit_ed.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_ed.entity_ed.DcIncomeEntity;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity, Integer> {
	public DcIncomeEntity findByCaseNo(int caseNo);
}