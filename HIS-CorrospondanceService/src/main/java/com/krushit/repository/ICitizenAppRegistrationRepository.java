package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity.CitizenRegistrationEntity;

public interface ICitizenAppRegistrationRepository extends JpaRepository<CitizenRegistrationEntity, Integer>{

}
