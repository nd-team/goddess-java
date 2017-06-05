package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 保修状态日汇总
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 02:05 ]
* @Description:	[ 保修状态日汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "materialsummary_warrantystatedaysum")
public class WarrantyStateDaySum extends BaseEntity { 

/**
* 汇总日期
*/
@Column(name = "sumDate",nullable = false,columnDefinition = "DATE   COMMENT '汇总日期'"  ) 
 private LocalDate  sumDate; 

/**
* 是否在保修期
*/
@Column(name = "is_ifWarranty",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否在保修期'"  , insertable = false  ) 
 private Boolean  ifWarranty; 

/**
* 地区
*/
@Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  ) 
 private String  area; 

/**
* 部门(项目组)
*/
@Column(name = "projectGroup",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '部门(项目组)'"  ) 
 private String  projectGroup; 

/**
* 总数量
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '总数量'"  ) 
 private Integer  totalQty; 

/**
* 总金额
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '总金额'"  ) 
 private Integer  amount; 

/**
* 状态
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '状态'"  ) 
 private Status  status; 



 public LocalDate getSumDate () { 
 return sumDate;
 } 
 public void setSumDate (LocalDate sumDate ) { 
 this.sumDate = sumDate ; 
 } 
 public Boolean getIfWarranty () { 
 return ifWarranty;
 } 
 public void setIfWarranty (Boolean ifWarranty ) { 
 this.ifWarranty = ifWarranty ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getProjectGroup () { 
 return projectGroup;
 } 
 public void setProjectGroup (String projectGroup ) { 
 this.projectGroup = projectGroup ; 
 } 
 public Integer getTotalQty () { 
 return totalQty;
 } 
 public void setTotalQty (Integer totalQty ) { 
 this.totalQty = totalQty ; 
 } 
 public Integer getAmount () { 
 return amount;
 } 
 public void setAmount (Integer amount ) { 
 this.amount = amount ; 
 } 
 public Status getStatus () { 
 return status;
 } 
 public void setStatus (Status status ) { 
 this.status = status ; 
 } 
 }