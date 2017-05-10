package com.bjike.goddess.projectmarketfee.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 等级设计
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-09 04:55 ]
* @Description:	[ 等级设计 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class GradeTO extends BaseTO { 

/**
* 备注
*/
 private String  note; 

/**
* 等级
*/
 private String  grade; 



 public String getNote () { 
 return note;
 } 
 public void setNote (String note ) { 
 this.note = note ; 
 } 
 public String getGrade () { 
 return grade;
 } 
 public void setGrade (String grade ) { 
 this.grade = grade ; 
 } 
 }