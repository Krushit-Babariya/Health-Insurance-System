package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krushit.entity.CaseWorkerEntity;

public interface CaseWorkerRepository extends JpaRepository<CaseWorkerEntity, Integer> {
}
