package com.bjike.goddess.attendance.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 手机提醒打卡
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-03 09:28 ]
* @Description:	[ 手机提醒打卡 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PhoneRemindTO extends BaseTO { 

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