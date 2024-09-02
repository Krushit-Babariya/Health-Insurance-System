package com.krushit.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "HIS_INSAURENCE_PLANS_CATEGORY")
@Data
public class PlanCategory {
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "cat_seq", initialValue = 2031, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer category_id;
	private String category_name;

	@Column(name = "ACTIVE_SW")
	private String avtiveSW = "active";
	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(name = "UPDATE_DATE", updatable = true, insertable = false)
	@UpdateTimestamp
	private LocalDateTime updateDate;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_BY")
	private String updatedBy;

}
