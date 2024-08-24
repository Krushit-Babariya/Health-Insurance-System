package com.krushit_ed.repository_ed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_ed.entity_ed.DcChildrenEntity;

public interface IDcChildrenRepository extends JpaRepository<DcChildrenEntity, Integer> {
	public List<DcChildrenEntity> findByCaseNo(int caseNo);
}
