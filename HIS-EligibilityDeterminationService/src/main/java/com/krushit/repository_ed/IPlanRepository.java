package com.krushit.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_ed.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Integer> {
}
