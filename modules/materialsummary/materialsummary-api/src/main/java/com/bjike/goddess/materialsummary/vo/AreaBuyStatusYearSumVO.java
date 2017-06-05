package com.bjike.goddess.materialsummary.vo;

/**
* 地区购买情况年汇总表现层对象
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 10:56 ]
* @Description:	[ 地区购买情况年汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AreaBuyStatusYearSumVO { 

/**
* id
*/
 private String  id; 
/**
* 汇总起始时间
*/
 private LocalDateTime  sumStartTime; 

/**
* 汇总结束时间
*/
 private LocalDateTime  sumEndTime; 

/**
* 地区
*/
 private String  area; 

/**
* 部门(项目组)
*/
 private String  projectGroup; 

/**
* 物资类型
*/
 private String  deviceType; 

/**
* 总数量
*/
 private Integer  totalQty; 

/**
* 总金额
*/
 private Double  amount; 

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
 public String getDeviceType () { 
 return deviceType;
 } 
 public void setDeviceType (String deviceType ) { 
 this.deviceType = deviceType ; 
 } 
 public Integer getTotalQty () { 
 return totalQty;
 } 
 public void setTotalQty (Integer totalQty ) { 
 this.totalQty = totalQty ; 
 } 
 public Double getAmount () { 
 return amount;
 } 
 public void setAmount (Double amount ) { 
 this.amount = amount ; 
 } 
 public Status getStatus () { 
 return status;
 } 
 public void setStatus (Status status ) { 
 this.status = status ; 
 } 
 }