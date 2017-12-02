package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 周计划的表头数据业务传输对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:46 ]
* @Description:	[ 周计划的表头数据业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WeekFilesBO extends BaseBO { 

/**
* 周计划的周期id
*/
 private String  weekCycleId; 

/**
* 表头下标
*/
 private Integer  index; 

/**
* 表头
*/
 private String  table; 

/**
* 内容
*/
 private String  context; 

/**
* 父级id
*/
 private String  fatherId; 



 public String getWeekCycleId () { 
 return weekCycleId;
 } 
 public void setWeekCycleId (String weekCycleId ) { 
 this.weekCycleId = weekCycleId ; 
 } 
 public Integer getIndex () { 
 return index;
 } 
 public void setIndex (Integer index ) { 
 this.index = index ; 
 } 
 public String getTable () { 
 return table;
 } 
 public void setTable (String table ) { 
 this.table = table ; 
 } 
 public String getContext () { 
 return context;
 } 
 public void setContext (String context ) { 
 this.context = context ; 
 } 
 public String getFatherId () { 
 return fatherId;
 } 
 public void setFatherId (String fatherId ) { 
 this.fatherId = fatherId ; 
 } 
 }