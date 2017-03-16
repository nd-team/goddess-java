package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 工作经历
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 10:23]
 * @Description: [工作经历]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "staffentry_workexperience")
public class WorkExperience  extends BaseEntity {

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
	 * 公司名称
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '公司名称'")
	private String firm;

	/**
	 * 工作描述
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '工作描述'")
	private String jobDescription;

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

	public String getFirm () {
		return firm;
	}
	public void setFirm (String firm) {
		this.firm =firm;
	}
	
 	public String getJobDescription () {
		return jobDescription;
	}
	public void setJobDescription (String jobDescription) {
		this.jobDescription =jobDescription;
	}

	public EntryRegister getEntryRegister() {
		return entryRegister;
	}

	public void setEntryRegister(EntryRegister entryRegister) {
		this.entryRegister = entryRegister;
	}


}
