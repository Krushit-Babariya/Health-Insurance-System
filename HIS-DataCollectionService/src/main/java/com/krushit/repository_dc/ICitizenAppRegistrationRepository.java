package com.krushit.repository_dc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_dc.CitizenRegistrationEntity;

public interface ICitizenAppRegistrationRepository extends JpaRepository<CitizenRegistrationEntity, Integer>{

}
