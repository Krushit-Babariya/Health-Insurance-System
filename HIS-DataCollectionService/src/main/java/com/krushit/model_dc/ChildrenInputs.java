package com.krushit.model_dc;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ChildrenInputs {
	private Integer childId;
	private LocalDate childDOB;
	private String name;
	private String gender;
	private Long childSSN;
	private Integer caseNo;
}
