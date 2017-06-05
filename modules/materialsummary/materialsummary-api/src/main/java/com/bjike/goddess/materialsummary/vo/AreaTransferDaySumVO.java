package com.bjike.goddess.materialsummary.vo;

/**
* 地区调动日汇总表现层对象
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:50 ]
* @Description:	[ 地区调动日汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AreaTransferDaySumVO { 

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
* 物资类型
*/
 private String  deviceType; 

/**
* 原存储地区总数量
*/
 private Integer  oriStorageAreaTotalQty; 

/**
* 调入地区总数量
*/
 private Integer  transferredAreaTotalQty; 

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
 public String getDeviceType () { 
 return deviceType;
 } 
 public void setDeviceType (String deviceType ) { 
 this.deviceType = deviceType ; 
 } 
 public Integer getOriStorageAreaTotalQty () { 
 return oriStorageAreaTotalQty;
 } 
 public void setOriStorageAreaTotalQty (Integer oriStorageAreaTotalQty ) { 
 this.oriStorageAreaTotalQty = oriStorageAreaTotalQty ; 
 } 
 public Integer getTransferredAreaTotalQty () { 
 return transferredAreaTotalQty;
 } 
 public void setTransferredAreaTotalQty (Integer transferredAreaTotalQty ) { 
 this.transferredAreaTotalQty = transferredAreaTotalQty ; 
 } 
 public Status getStatus () { 
 return status;
 } 
 public void setStatus (Status status ) { 
 this.status = status ; 
 } 
 }