package com.bjike.goddess.materialsummary.vo;

/**
* 调动类型年汇总表现层对象
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:45 ]
* @Description:	[ 调动类型年汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class TransferTypeYearSumVO { 

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
* 物资类型
*/
 private MaterialState  materialState; 

/**
* 地区
*/
 private String  area; 

/**
* 部门(项目组)
*/
 private String  projectGroup; 

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
 public MaterialState getMaterialState () { 
 return materialState;
 } 
 public void setMaterialState (MaterialState materialState ) { 
 this.materialState = materialState ; 
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