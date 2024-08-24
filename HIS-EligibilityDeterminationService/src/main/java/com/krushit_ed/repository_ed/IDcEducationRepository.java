package com.krushit_ed.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_ed.entity_ed.DcEducationEntity;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity, Integer> {
	public DcEducationEntity findByCaseNo(int caseNo);
}
