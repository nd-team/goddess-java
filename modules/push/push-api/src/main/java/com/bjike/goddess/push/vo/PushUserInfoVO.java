package com.bjike.goddess.push.vo;

/**
* 推送的用户装置信息表现层对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-11 10:18 ]
* @Description:	[ 推送的用户装置信息表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PushUserInfoVO { 

/**
* id
*/
 private String  id; 
/**
* 姓名
*/
 private String  name; 

/**
* 移动端装置的唯一标识
*/
 private String  deviceToken; 



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
 public String getDeviceToken () { 
 return deviceToken;
 } 
 public void setDeviceToken (String deviceToken ) { 
 this.deviceToken = deviceToken ; 
 } 
 }