package com.krushit_dc.repository_dc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_dc.entity_dc.DcChildrenEntity;

public interface IDcChildrenRepository extends JpaRepository<DcChildrenEntity, Integer> {
	public List<DcChildrenEntity> findByCaseNo(int caseNo);
}
