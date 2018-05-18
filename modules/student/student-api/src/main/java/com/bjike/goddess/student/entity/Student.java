package com.bjike.goddess.student.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 学生信息类
* @Author:			[ chentianpin ]
* @Date:			[  2018-04-30 11:14 ]
* @Description:	[ 学生信息类 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "student_student")
public class Student extends BaseEntity { 

/**
* 学生名
*/
@Column(name = "name",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '学生名'"  ) 
 private String  name; 

/**
* 年龄
*/
@Column(name = "age",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '年龄'"  ) 
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