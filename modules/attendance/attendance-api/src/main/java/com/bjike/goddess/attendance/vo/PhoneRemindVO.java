package com.bjike.goddess.attendance.vo;

/**
* 手机提醒打卡表现层对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-03 09:28 ]
* @Description:	[ 手机提醒打卡表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PhoneRemindVO { 

/**
* id
*/
 private String  id; 
/**
* 姓名
*/
 private String  name; 

/**
* 启用
*/
 private Boolean  enable; 



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
 public Boolean getEnable () { 
 return enable;
 } 
 public void setEnable (Boolean enable ) { 
 this.enable = enable ; 
 } 
 }