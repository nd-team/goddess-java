package com.bjike.goddess.student.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 学生信息类业务传输对象
* @Author:			[ chentianpin ]
* @Date:			[  2018-04-30 11:14 ]
* @Description:	[ 学生信息类业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class StudentBO extends BaseBO { 

/**
* 学生名
*/
 private String  name; 

/**
* 年龄
*/
 private String  age; 



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