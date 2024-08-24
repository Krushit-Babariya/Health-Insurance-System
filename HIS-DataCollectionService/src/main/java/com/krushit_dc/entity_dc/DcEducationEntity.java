package com.krushit_dc.entity_dc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_DC_EDUCATION")
public class DcEducationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer educationId;
	private Integer caseNo;
	@Column(length = 40)
	private String highestQfy;
	private Integer passOutYear;

}
