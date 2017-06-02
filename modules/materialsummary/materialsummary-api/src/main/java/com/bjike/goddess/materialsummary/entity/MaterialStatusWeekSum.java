package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 物资状态周汇总
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:21 ]
* @Description:	[ 物资状态周汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "materialsummary_materialstatusweeksum")
public class MaterialStatusWeekSum extends BaseEntity { 

/**
* 汇总起始时间
*/
@Column(name = "sumStartTime",nullable = false,columnDefinition = "DATETIME   COMMENT '汇总起始时间'"  ) 
 private LocalDateTime  sumStartTime; 

/**
* 汇总结束时间
*/
@Column(name = "sumEndTime",nullable = false,columnDefinition = "DATETIME   COMMENT '汇总结束时间'"  ) 
 private LocalDateTime  sumEndTime; 

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



 public LocalDateTime getSumStartTime () { 
 return sumStartTime;
 } 
 public void setSumStartTime (LocalDateTime sumStartTime ) { 
 this.sumStartTime = sumStartTime ; 
 } 
 public LocalDateTime getSumEndTime () { 
 return sumEndTime;
 } 
 public void setSumEndTime (LocalDateTime sumEndTime ) { 
 this.sumEndTime = sumEndTime ; 
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