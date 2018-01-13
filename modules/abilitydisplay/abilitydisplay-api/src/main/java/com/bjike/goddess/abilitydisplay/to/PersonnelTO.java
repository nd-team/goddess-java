package com.bjike.goddess.abilitydisplay.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 人员组成
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 02:13 ]
* @Description:	[ 人员组成 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PersonnelTO extends BaseTO { 

/**
* 姓名
*/
 private String  name; 

/**
* 职位
*/
 private String  position; 



 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public String getPosition () { 
 return position;
 } 
 public void setPosition (String position ) { 
 this.position = position ; 
 } 
 }