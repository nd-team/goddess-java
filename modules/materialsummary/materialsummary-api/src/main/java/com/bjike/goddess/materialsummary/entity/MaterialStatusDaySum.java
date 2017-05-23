package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 物资状态日汇总
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:18 ]
* @Description:	[ 物资状态日汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "materialsummary_materialstatusdaysum")
public class MaterialStatusDaySum extends BaseEntity { 

/**
* 汇总日期
*/
@Column(name = "sumDate",nullable = false,columnDefinition = "DATE   COMMENT '汇总日期'"  ) 
 private LocalDate  sumDate; 

/**
* 物资状态
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '物资状态'"  ) 
 private MaterialState  instockState; 

/**
* 物资分类
*/
@Column(name = "deviceType",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '物资分类'"  ) 
 private String  deviceType; 

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
 public MaterialState getInstockState () { 
 return instockState;
 } 
 public void setInstockState (MaterialState instockState ) { 
 this.instockState = instockState ; 
 } 
 public String getDeviceType () { 
 return deviceType;
 } 
 public void setDeviceType (String deviceType ) { 
 this.deviceType = deviceType ; 
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
 public Status getStatus () { 
 return status;
 } 
 public void setStatus (Status status ) { 
 this.status = status ; 
 } 
 }