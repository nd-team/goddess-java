package com.bjike.goddess.materialsummary.vo;

/**
* 保修状态日汇总表现层对象
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 02:05 ]
* @Description:	[ 保修状态日汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WarrantyStateDaySumVO { 

/**
* id
*/
 private String  id; 
/**
* 汇总日期
*/
 private LocalDate  sumDate; 

/**
* 是否在保修期
*/
 private Boolean  ifWarranty; 

/**
* 地区
*/
 private String  area; 

/**
* 部门(项目组)
*/
 private String  projectGroup; 

/**
* 总数量
*/
 private Integer  totalQty; 

/**
* 总金额
*/
 private Integer  amount; 

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