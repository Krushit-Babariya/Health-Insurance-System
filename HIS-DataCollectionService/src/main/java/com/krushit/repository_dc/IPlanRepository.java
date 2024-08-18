package com.krushit.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_dc.DcChildrenEntity;
import com.krushit.entity_dc.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Integer> {
}
