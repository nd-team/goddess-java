package com.bjike.goddess.workhandover.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 工作交接时间规范业务传输对象
* @Author:			[ chenyang ]
* @Date:			[  2017-11-13 06:26 ]
* @Description:	[ 工作交接时间规范业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ObligationsBO extends BaseBO { 

/**
* 对象
*/
 private String  objects; 

/**
* 责任与义务
*/
 private String  responsibilitiesAndObligations; 



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