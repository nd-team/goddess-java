package com.bjike.goddess.workhandover.vo;

/**
* 工作交接时间规范表现层对象
* @Author:			[ chenyang ]
* @Date:			[  2017-11-13 06:26 ]
* @Description:	[ 工作交接时间规范表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ObligationsVO { 

/**
* id
*/
 private String  id; 
/**
* 对象
*/
 private String  objects; 

/**
* 责任与义务
*/
 private String  responsibilitiesAndObligations; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getObjects () { 
 return objects;
 } 
 public void setObjects (String objects ) { 
 this.objects = objects ; 
 } 
 public String getResponsibilitiesAndObligations () { 
 return responsibilitiesAndObligations;
 } 
 public void setResponsibilitiesAndObligations (String responsibilitiesAndObligations ) { 
 this.responsibilitiesAndObligations = responsibilitiesAndObligations ; 
 } 
 }