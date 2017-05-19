package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * 家庭成员
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 10:23]
 * @Description: [家庭成员]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "staffentry_familymember")
public class FamilyMember extends BaseEntity {

	/**
	 * 称谓
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '称谓'")
 	private String title;

	/**
	 *姓名
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '姓名'")
	private String name;

	/**
	 *年龄
	 */
	@Range(min = 0 , max = 200 )
	@Column(nullable = false,columnDefinition = "TINYINT COMMENT '年龄'")
	private Integer age;

	/**
	 *单位
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '单位'")
	private String unit;

	/**
	 *职务
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '职务'")
	private String position;

	/**
	 *联系方式
	 */
	@Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '联系方式'")
	private String phone;

	/**
	 * 入职登记外键
	 */
	@ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	@JoinColumn(name = "entryRegister_id", columnDefinition = "VARCHAR(36) COMMENT '入职登记外键' ")
	private EntryRegister entryRegister;

	public FamilyMember(){

	}


	public String getTitle () {
		return title;
	}
	public void setTitle (String title) {
		this.title =title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge () {
		return age;
	}
	public void setAge (Integer age) {
		this.age =age;
	}
 	
 	public String getUnit () {
		return unit;
	}
	public void setUnit (String unit) {
		this.unit =unit;
	}
 	
 	public String getPosition () {
		return position;
	}
	public void setPosition (String position) {
		this.position =position;
	}
 	
 	public String getPhone () {
		return phone;
	}
	public void setPhone (String phone) {
		this.phone =phone;
	}

	public EntryRegister getEntryRegister() {
		return entryRegister;
	}

	public void setEntryRegister(EntryRegister entryRegister) {
		this.entryRegister = entryRegister;
	}


}
