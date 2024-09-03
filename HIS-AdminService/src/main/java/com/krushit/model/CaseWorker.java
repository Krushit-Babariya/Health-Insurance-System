package com.krushit.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CaseWorker {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String gender;
    private Long ssn;
    private LocalDate birthDate;
	private String province;
	private String officeName;
	private String officeAddress;
}
