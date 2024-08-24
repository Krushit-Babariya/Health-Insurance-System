package com.krushit_dc.entity_dc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_DC_CASES")
public class DcCaseEntity {
	@Id
	@SequenceGenerator(name = "case_seq_generater", sequenceName = "his_case_no_sequence", initialValue = 6350, allocationSize = 2)
	@GeneratedValue(generator = "case_seq_generater", strategy = GenerationType.SEQUENCE)
	private Integer caseNo;
	private Integer appId;
	private Integer planId;
}
