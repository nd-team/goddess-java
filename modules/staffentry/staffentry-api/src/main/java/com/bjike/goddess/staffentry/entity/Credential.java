package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 证书情况
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 10:23]
 * @Description: [证书情况]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "staffentry_credential")
public class Credential extends BaseEntity {

	/**
	 * 证书名称
	 */
	@Column( columnDefinition = "VARCHAR(255) COMMENT '证书名称'")
	private String name;

	/**
	 * 获得证书时间
	 */
	@Column(columnDefinition = "DATE COMMENT '获得证书时间'" )
	private LocalDate obtainTime;


	/**
	 * 员工入职登记外键
	 */
	@ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	@JoinColumn(name = "entryRegister_id", columnDefinition = "VARCHAR(36) COMMENT '员工入职登记外键' ")
	private EntryRegister entryRegister;


	public Credential(){

	}

	public String getName () {
		return name;
	}
	public void setName (String name) {
		this.name =name;
	}

	public LocalDate getObtainTime() {
		return obtainTime;
	}

	public void setObtainTime(LocalDate obtainTime) {
		this.obtainTime = obtainTime;
	}

	public EntryRegister getEntryRegister() {
		return entryRegister;
	}

	public void setEntryRegister(EntryRegister entryRegister) {
		this.entryRegister = entryRegister;
	}

}
