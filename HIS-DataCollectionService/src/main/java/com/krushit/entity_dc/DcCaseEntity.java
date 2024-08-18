package com.krushit.entity_dc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_DC_CASES")
public class DcCaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer caseId;
	private Integer caseNo;
	private Integer appId;
	private Integer planId;
}