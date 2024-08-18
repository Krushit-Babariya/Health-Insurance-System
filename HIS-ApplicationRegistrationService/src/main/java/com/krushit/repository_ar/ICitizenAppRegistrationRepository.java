package com.krushit.repository_ar;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_ar.CitizenRegistrationEntity;

public interface ICitizenAppRegistrationRepository extends JpaRepository<CitizenRegistrationEntity, Integer>{

}
