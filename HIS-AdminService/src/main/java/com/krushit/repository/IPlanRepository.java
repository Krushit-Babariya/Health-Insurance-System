package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Integer> {
}
