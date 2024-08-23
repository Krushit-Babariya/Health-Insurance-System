package com.krushit.entity_dc;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_DC_CHILD")
public class DcChildrenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer childId;
	private LocalDate childDOB;
	private String name;
	private String gender;
	private Long childSSN;
	private Integer caseNo;
}
