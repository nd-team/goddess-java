package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 周计划的合计
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:42 ]
* @Description:	[ 周计划的合计 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WeekTotalTO extends BaseTO { 

/**
* 周计划的周期id
*/
 private String  weekCycleId; 

/**
* 合计
*/
 private String  total; 

/**
* 备注
*/
 private String  remark; 



 public String getWeekCycleId () { 
 return weekCycleId;
 } 
 public void setWeekCycleId (String weekCycleId ) { 
 this.weekCycleId = weekCycleId ; 
 } 
 public String getTotal () { 
 return total;
 } 
 public void setTotal (String total ) { 
 this.total = total ; 
 } 
 public String getRemark () { 
 return remark;
 } 
 public void setRemark (String remark ) { 
 this.remark = remark ; 
 } 
 }