package com.krushit_dc.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_dc.entity_dc.DcChildrenEntity;
import com.krushit_dc.entity_dc.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Integer> {
}
