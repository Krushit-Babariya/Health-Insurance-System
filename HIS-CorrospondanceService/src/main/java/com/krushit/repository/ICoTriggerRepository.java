package com.krushit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity.CoTriggersEntity;

public interface ICoTriggerRepository extends JpaRepository<CoTriggersEntity, Integer> {
	public List<CoTriggersEntity> findByTriggerStatus(String status);
	public CoTriggersEntity findByCaseNo(int caseNo);
}
