package com.krushit.repository_ed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_ed.DcChildrenEntity;

public interface IDcChildrenRepository extends JpaRepository<DcChildrenEntity, Integer> {
	public List<DcChildrenEntity> findByCaseNo(int caseNo);
}
