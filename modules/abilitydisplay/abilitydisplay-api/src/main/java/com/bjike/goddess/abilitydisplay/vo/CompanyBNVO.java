package com.bjike.goddess.abilitydisplay.vo;

import com.bjike.goddess.abilitydisplay.entity.ComProject;

/**
* 公司业务表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 03:15 ]
* @Description:	[ 公司业务表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CompanyBNVO { 

/**
* id
*/
 private String  id; 
/**
* 公司核心业务介绍
*/
 private String  introduction; 

/**
* 公司业务类型
*/
 private String  type; 

/**
* 公司参与项目
*/
 private ComProject project;



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getIntroduction () { 
 return introduction;
 } 
 public void setIntroduction (String introduction ) { 
 this.introduction = introduction ; 
 } 
 public String getType () { 
 return type;
 } 
 public void setType (String type ) { 
 this.type = type ; 
 } 
 public ComProject getProject () { 
 return project;
 } 
 public void setProject (ComProject project ) { 
 this.project = project ; 
 } 
 }