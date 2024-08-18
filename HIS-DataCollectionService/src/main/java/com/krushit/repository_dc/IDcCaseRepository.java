package com.krushit.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_dc.DcCaseEntity;

public interface IDcCaseRepository extends JpaRepository<DcCaseEntity, Integer> {
}
