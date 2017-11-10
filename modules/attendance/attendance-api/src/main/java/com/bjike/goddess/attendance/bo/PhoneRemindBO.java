package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 手机提醒打卡业务传输对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-03 09:28 ]
* @Description:	[ 手机提醒打卡业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PhoneRemindBO extends BaseBO { 

/**
* 姓名
*/
 private String  name; 

/**
* 启用
*/
 private Boolean  enable; 



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