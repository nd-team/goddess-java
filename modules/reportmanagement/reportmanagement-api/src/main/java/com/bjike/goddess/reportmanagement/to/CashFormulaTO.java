package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 项目公式
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-21 05:09 ]
* @Description:	[ 项目公式 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CashFormulaTO extends BaseTO { 

/**
* 项目id
*/
 private String  projectId; 

/**
* 项目
*/
 private String  projectName; 

/**
* 公式
*/
 private String  form; 



 public String getProjectId () { 
 return projectId;
 } 
 public void setProjectId (String projectId ) { 
 this.projectId = projectId ; 
 } 
 public String getProjectName () { 
 return projectName;
 } 
 public void setProjectName (String projectName ) { 
 this.projectName = projectName ; 
 } 
 public String getForm () { 
 return form;
 } 
 public void setForm (String form ) { 
 this.form = form ; 
 } 
 }