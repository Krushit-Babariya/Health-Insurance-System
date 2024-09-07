package com.krushit.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_ed.DcIncomeEntity;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity, Integer> {
	public DcIncomeEntity findByCaseNo(int caseNo);
}
