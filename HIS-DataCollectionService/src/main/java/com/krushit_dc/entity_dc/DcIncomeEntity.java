package com.krushit_dc.entity_dc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_DC_INCOME")
public class DcIncomeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer incomeId;
	private Integer caseNo;
	private Double empIncome;
	private Double propertyIncome;

}