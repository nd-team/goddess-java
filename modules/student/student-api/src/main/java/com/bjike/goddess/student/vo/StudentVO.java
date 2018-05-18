package com.bjike.goddess.student.vo;

/**
* 学生信息类表现层对象
* @Author:			[ chentianpin ]
* @Date:			[  2018-04-30 11:14 ]
* @Description:	[ 学生信息类表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class StudentVO { 

/**
* id
*/
 private String  id; 
/**
* 学生名
*/
 private String  name; 

/**
* 年龄
*/
 private String  age; 



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
 public String getAge () { 
 return age;
 } 
 public void setAge (String age ) { 
 this.age = age ; 
 } 
 }