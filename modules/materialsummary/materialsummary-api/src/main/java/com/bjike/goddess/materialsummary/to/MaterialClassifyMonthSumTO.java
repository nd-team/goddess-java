package com.bjike.goddess.materialsummary.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 物资分类月汇总
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 10:46 ]
* @Description:	[ 物资分类月汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class MaterialClassifyMonthSumTO extends BaseTO { 

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
 private String  deviceType; 

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
 private Double  amoutn; 

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
 public Double getAmoutn () { 
 return amoutn;
 } 
 public void setAmoutn (Double amoutn ) { 
 this.amoutn = amoutn ; 
 } 
 public Status getStatus () { 
 return status;
 } 
 public void setStatus (Status status ) { 
 this.status = status ; 
 } 
 }