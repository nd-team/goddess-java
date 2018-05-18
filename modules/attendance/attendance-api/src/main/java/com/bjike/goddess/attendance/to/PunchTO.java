package com.bjike.goddess.attendance.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 打卡
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-22 03:21 ]
* @Description:	[ 打卡 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PunchTO extends BaseTO { 

/**
* 日期
*/
 private String  date; 

/**
* 项目组
*/
 private String  project; 

/**
* 姓名
*/
 private String  name; 



 public String getDate () { 
 return date;
 } 
 public void setDate (String date ) { 
 this.date = date ; 
 } 
 public String getProject () { 
 return project;
 } 
 public void setProject (String project ) { 
 this.project = project ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 }