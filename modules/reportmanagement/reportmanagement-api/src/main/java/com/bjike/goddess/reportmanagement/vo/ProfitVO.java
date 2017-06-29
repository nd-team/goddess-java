package com.bjike.goddess.reportmanagement.vo;

/**
* 利润表表现层对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-22 06:03 ]
* @Description:	[ 利润表表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ProfitVO { 

/**
* id
*/
 private String  id; 
/**
* 项目
*/
 private String  project; 

/**
* 本月数
*/
 private Double  currentMonthAmount; 

/**
* 本年累计数
*/
 private Double  currentYearAmount; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getProject () { 
 return project;
 } 
 public void setProject (String project ) { 
 this.project = project ; 
 } 
 public Double getCurrentMonthAmount () { 
 return currentMonthAmount;
 } 
 public void setCurrentMonthAmount (Double currentMonthAmount ) { 
 this.currentMonthAmount = currentMonthAmount ; 
 } 
 public Double getCurrentYearAmount () { 
 return currentYearAmount;
 } 
 public void setCurrentYearAmount (Double currentYearAmount ) { 
 this.currentYearAmount = currentYearAmount ; 
 } 
 }