package com.krushit_ed.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit_ed.entity_ed.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Integer> {
}