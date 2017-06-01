package com.bjike.goddess.materialsummary.vo;

/**
* 地区部门领用日汇总表现层对象
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:29 ]
* @Description:	[ 地区部门领用日汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AreaDepartReceiveDaySumVO { 

/**
* id
*/
 private String  id; 
/**
* 汇总日期
*/
 private LocalDate  sumDate; 

/**
* 地区
*/
 private String  area; 

/**
* 部门(项目组)
*/
 private String  projectGroup; 

/**
* 物资用途
*/
 private String  materialUse; 

/**
* 总数量
*/
 private Integer  totalQty; 

/**
* 状态
*/
 private Status  status; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public LocalDate getSumDate () { 
 return sumDate;
 } 
 public void setSumDate (LocalDate sumDate ) { 
 this.sumDate = sumDate ; 
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
 public String getMaterialUse () { 
 return materialUse;
 } 
 public void setMaterialUse (String materialUse ) { 
 this.materialUse = materialUse ; 
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