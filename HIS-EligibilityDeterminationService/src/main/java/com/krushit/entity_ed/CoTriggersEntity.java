package com.krushit.entity_ed;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "HIS_CO_TRIGGERS")
@Data
@Entity
public class CoTriggersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer triggerId;
	private Integer caseNo;
	@Column(name = "co_notice_pdf", columnDefinition = "LONGBLOB")
	private byte[] coNoticePdf;
	@Column(length = 40)
	private String triggerStatus = "pending";
}
