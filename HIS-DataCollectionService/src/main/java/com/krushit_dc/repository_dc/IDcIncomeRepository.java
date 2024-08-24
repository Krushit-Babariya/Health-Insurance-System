package com.krushit_dc.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_dc.entity_dc.DcIncomeEntity;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity, Integer> {
	public DcIncomeEntity findByCaseNo(int caseNo);
}
