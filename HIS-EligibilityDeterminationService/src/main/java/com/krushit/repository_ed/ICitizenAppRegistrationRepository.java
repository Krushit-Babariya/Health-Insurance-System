package com.krushit.repository_ed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity_ed.CitizenRegistrationEntity;

public interface ICitizenAppRegistrationRepository extends JpaRepository<CitizenRegistrationEntity, Integer>{

}
