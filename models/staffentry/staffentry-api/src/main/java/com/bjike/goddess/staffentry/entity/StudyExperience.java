package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 学习经历
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 10:23]
 * @Description: [学习经历]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "staffentry_studyexperience")
public class StudyExperience extends BaseEntity {

	/**
	 * 开始时间
	 */
	@Column(nullable = false,columnDefinition = "DATE COMMENT '开始时间'")
 	private LocalDate startTime;

	/**
	 * 结束时间
	 */
	@Column(nullable = false,columnDefinition = "DATE COMMENT '结束时间'")
	private LocalDate endTime;

	/**
	 * 就读学校
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '就读学校'")
 	private String school;

	/**
	 * 毕业证书（学历）
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '毕业证书（学历）'")
 	private String certificate;

	/**
	 * 员工入职登记外键
	 */
	@ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	@JoinColumn(name = "entryRegister_id", columnDefinition = "VARCHAR(36) COMMENT '员工入职登记外键' ")
	private EntryRegister entryRegister;


	public LocalDate getStartTime () {
		return startTime;
	}
	public void setStartTime (LocalDate startTime) {
		this.startTime =startTime;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}

	public String getSchool () {
		return school;
	}
	public void setSchool (String school) {
		this.school =school;
	}
 	
 	public String getCertificate () {
		return certificate;
	}
	public void setCertificate (String certificate) {
		this.certificate =certificate;
	}

	public EntryRegister getEntryRegister() {
		return entryRegister;
	}

	public void setEntryRegister(EntryRegister entryRegister) {
		this.entryRegister = entryRegister;
	}

}
