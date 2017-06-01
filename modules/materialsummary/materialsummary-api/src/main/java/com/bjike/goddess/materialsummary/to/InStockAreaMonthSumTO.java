package com.bjike.goddess.materialsummary.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 入库地区月汇总
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:11 ]
* @Description:	[ 入库地区月汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class InStockAreaMonthSumTO extends BaseTO { 

/**
* 汇总起始时间
*/
 private LocalDateTime  sumStartTime; 

/**
* 汇总结束时间
*/
 private LocalDateTime  sumEndTime; 

/**
* 现入库地区
*/
 private String  curInstockArea; 

/**
* 部门(项目组)
*/
 private String  projectGroup; 

/**
* 入库物资状态
*/
 private MaterialState  instockState; 

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
 public String getCurInstockArea () { 
 return curInstockArea;
 } 
 public void setCurInstockArea (String curInstockArea ) { 
 this.curInstockArea = curInstockArea ; 
 } 
 public String getProjectGroup () { 
 return projectGroup;
 } 
 public void setProjectGroup (String projectGroup ) { 
 this.projectGroup = projectGroup ; 
 } 
 public MaterialState getInstockState () { 
 return instockState;
 } 
 public void setInstockState (MaterialState instockState ) { 
 this.instockState = instockState ; 
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