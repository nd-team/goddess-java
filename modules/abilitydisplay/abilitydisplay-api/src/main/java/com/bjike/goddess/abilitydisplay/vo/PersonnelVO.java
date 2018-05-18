package com.bjike.goddess.abilitydisplay.vo;

/**
* 人员组成表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 02:13 ]
* @Description:	[ 人员组成表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PersonnelVO { 

/**
* id
*/
 private String  id; 
/**
* 姓名
*/
 private String  name; 

/**
* 职位
*/
 private String  position; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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